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

import beans.LitroBarril;
import beans.ResumenCuentaBean;
import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class ListadoLitros extends JFrame {

	private JPanel contentPane;
	private JLabel lblFechaDesde;
	private JLabel lblFechaHasta;
	private JTextField textDesde;
	private JTextField textHasta;
	private JScrollPane jScrollPane1;
	private TableModel jTable1Model;
	private JTable jTable1;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoLitros frame = new ListadoLitros();
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
	public ListadoLitros() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 693, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 681, 384);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblFechaDesde = new JLabel("FECHA DESDE");
		lblFechaDesde.setBounds(6, 15, 108, 16);
		panel.add(lblFechaDesde);
		
		lblFechaHasta = new JLabel("FECHA HASTA");
		lblFechaHasta.setBounds(6, 51, 108, 16);
		panel.add(lblFechaHasta);
		
		textDesde = new JTextField();
		textDesde.setBounds(126, 10, 130, 26);
		panel.add(textDesde);
		textDesde.setColumns(10);
		textDesde.setEditable(false);
		
		textHasta = new JTextField();
		textHasta.setBounds(126, 46, 130, 26);
		panel.add(textHasta);
		textHasta.setColumns(10);
		textHasta.setEditable(false);
		
		JCheckBox chckbxSinBonificaciones = new JCheckBox("SIN BONIFICACIONES");
		chckbxSinBonificaciones.setBounds(405, 11, 176, 23);
		panel.add(chckbxSinBonificaciones);
		
		JButton btnDesde = new JButton("ASIGNAR");
		btnDesde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fe=new DatePicker().setPickedDate();
				textDesde.setText(fe);
			}
		});
		btnDesde.setBounds(264, 10, 117, 29);
		panel.add(btnDesde);
		
		JButton btnAsignarHasta = new JButton("ASIGNAR");
		btnAsignarHasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fe=new DatePicker().setPickedDate();
				textHasta.setText(fe);
			}
		});
		btnAsignarHasta.setBounds(264, 46, 117, 29);
		panel.add(btnAsignarHasta);
		
		jScrollPane1 = new JScrollPane();
		panel.add(jScrollPane1);
		jScrollPane1.setBounds(10, 98, 665, 209);
		jTable1Model = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Estilo", "Litros","Barriles 50 litros"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
		jTable1 = new JTable();
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
		jTable1.setBounds(12, 207, 555, 194);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textDesde.getText().isEmpty() && !textHasta.getText().isEmpty()){
					Vector<LitroBarril> listado=null;
					if(!chckbxSinBonificaciones.isSelected()) {
						listado=Controlador.getInstancia().obtenerConsumoLitros(textDesde.getText(),textHasta.getText());
					}else {
						listado=Controlador.getInstancia().obtenerConsumoLitrosSinBonificacion(textDesde.getText(),textHasta.getText());
					}
					if(listado!=null && listado.size()>0){
						int litros=0;
						float barriles=0;
						DefaultTableModel temp1 = (DefaultTableModel) jTable1.getModel();
						while(jTable1.getRowCount()>0){
							temp1.removeRow(jTable1.getRowCount()-1);
						}
						for(int i=0;i<listado.size();i++) {
							LitroBarril lb=listado.get(i);
							DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();
					    	if(lb.getNombre().contains("LATA")) {
					    		Object nuevo[]= {lb.getNombre(),(lb.getLitros()*0.5),(lb.getBarriles()/2)};
						    	temp.addRow(nuevo);
					    		litros+=lb.getLitros()*0.5;
						    	barriles+=lb.getBarriles()/2;
					    	}else {
					    		Object nuevo[]= {lb.getNombre(),lb.getLitros(),lb.getBarriles()};
					    		litros+=lb.getLitros();
						    	barriles+=lb.getBarriles();
						    	temp.addRow(nuevo);
					    	}
					    	
					    	
						}
						if(listado.size()>0) {
							Object nuevo[]= {"","",""};
					    	DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();
					    	temp.addRow(nuevo);
					    	Object nuevo2[]= {"",litros,barriles};
					    	temp.addRow(nuevo2);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Se ha producido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe ingresar la fecha desde y hasta.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.setBounds(393, 46, 117, 29);
		panel.add(btnBuscar);
		
		DefaultTableCellRenderer  modelocentrarPrexpansion = new DefaultTableCellRenderer ();
		modelocentrarPrexpansion.setHorizontalAlignment(SwingConstants.CENTER);
		jTable1.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrarPrexpansion);
	}
}
