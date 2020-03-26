package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import beans.ResumenCuentaBean;
import controlador.Controlador;
import utils.Estado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ListadoRemusenCuentaCliente extends JFrame {

	private JPanel contentPane;
	private JLabel lblCliente;
	private JScrollPane jScrollPane1;
	private TableModel jTable1Model;
	private JTable jTable1;
	private JComboBox comboBoxClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoRemusenCuentaCliente frame = new ListadoRemusenCuentaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListadoRemusenCuentaCliente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 890, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 878, 418);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblCliente = new JLabel("CLIENTE");
		lblCliente.setBounds(10, 10, 75, 16);
		panel.add(lblCliente);
		
		comboBoxClientes = new JComboBox();
		comboBoxClientes.setBounds(73, 6, 295, 27);
		comboBoxClientes.addItem("NINGUNO");
		Vector<String> clientes = Controlador.getInstancia().obtenerClientes();
		for(int i=0;i<clientes.size();i++) {
			comboBoxClientes.addItem(clientes.get(i));
		}
		panel.add(comboBoxClientes);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboBoxClientes.getSelectedItem().toString().equals("NINGUNO")) {
					Vector<ResumenCuentaBean> listado=Controlador.getInstancia().obtenerListadoResumenCuentaCliente(comboBoxClientes.getSelectedItem().toString());
					if(listado != null) {
						float debe=0;
						float haber=0;
						DefaultTableModel temp1 = (DefaultTableModel) jTable1.getModel();
						while(jTable1.getRowCount()>0){
							temp1.removeRow(jTable1.getRowCount()-1);
						}
						for(int i=0;i<listado.size();i++){
							ResumenCuentaBean resumenCuenta = listado.get(i);
							if(resumenCuenta.getTipo().equals(Estado.RECIBO)) {
								Object nuevo[]= {resumenCuenta.getFecha(),resumenCuenta.getTipo(),resumenCuenta.getRemito(),resumenCuenta.getCliente(),"-","-","-",resumenCuenta.getHaber()};
						    	DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();			
						    	temp.addRow(nuevo);
						    	haber+=resumenCuenta.getHaber();
							}else {
								if(resumenCuenta.getTipo().equals(Estado.REMITO)) {
									Object nuevo[]= {resumenCuenta.getFecha(),resumenCuenta.getTipo(),resumenCuenta.getRemito(),resumenCuenta.getCliente(),resumenCuenta.getEstilo(),resumenCuenta.getLitros(),resumenCuenta.getDebe(),"-"};
							    	DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();			
							    	temp.addRow(nuevo);
							    	debe+=resumenCuenta.getDebe();
								}else {
									Object nuevo[]= {resumenCuenta.getFecha(),resumenCuenta.getTipo(),resumenCuenta.getRemito(),resumenCuenta.getCliente(),"-","-",resumenCuenta.getImpuestos(),"-"};
							    	DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();			
							    	temp.addRow(nuevo);
							    	debe+=resumenCuenta.getImpuestos();
								}
								
							}
						}
						if(jTable1.getRowCount()>0) {
							Object nuevo[]= {"","","","","","","",""};
							DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();
					    	temp.addRow(nuevo);
					    	Object nuevo2[]= {"","","","","","SUBTOTALES",debe,haber};
					    	temp.addRow(nuevo2);
					    	Object nuevo3[]= {"","","","","","TOTAL",debe-haber,""};
					    	temp.addRow(nuevo3);
					    	
							
						}
					}else {
						JOptionPane.showMessageDialog(null, "Se ha producido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.setBounds(380, 5, 117, 29);
		panel.add(btnBuscar);
		
		jScrollPane1 = new JScrollPane();
		panel.add(jScrollPane1);
		jScrollPane1.setBounds(10, 45, 862, 367);
		jTable1Model = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Fecha","Tipo","Numero","Cliente","Estilo","Litros","Debe","Haber"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
		jTable1 = new JTable();
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
		jTable1.setBounds(12, 207, 555, 194);
		
		JButton btnExportar = new JButton("EXPORTAR");
		btnExportar.setBounds(509, 5, 117, 29);
		panel.add(btnExportar);
		
		DefaultTableCellRenderer  modelocentrarPrexpansion = new DefaultTableCellRenderer ();
		modelocentrarPrexpansion.setHorizontalAlignment(SwingConstants.CENTER);
		jTable1.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(modelocentrarPrexpansion);
	}
}
