package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import beans.ClienteSaldoBean;
import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoPendienteDeCobro extends JFrame {

	private JPanel contentPane;
	private JButton btnActualizar;
	private JScrollPane jScrollPane1;
	private TableModel jTable1Model;
	private JTable jTable1;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoPendienteDeCobro frame = new ListadoPendienteDeCobro();
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
	public ListadoPendienteDeCobro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<ClienteSaldoBean> saldos=Controlador.getInstancia().obtenerSaldos();
				Collections.sort(saldos);
				for(int i=0;i<saldos.size();i++){
					ClienteSaldoBean c=saldos.get(i);
					float deuda=c.getImpuestos()+c.getRemitos()-c.getPagos();
					Object nuevo[]= {c.getCliente(),deuda};
			    	DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();			
			    	temp.addRow(nuevo);
				}
			}
		});
		btnActualizar.setBounds(195, 18, 117, 41);
		contentPane.add(btnActualizar);
		
		jScrollPane1 = new JScrollPane();
		contentPane.add(jScrollPane1);
		jScrollPane1.setBounds(10, 71, 818, 412);
		jTable1Model = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Cliente","Saldo"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
		jTable1 = new JTable();
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
		jTable1.setBounds(12, 207, 555, 194);
		
		btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(363, 18, 117, 41);
		contentPane.add(btnSalir);
		DefaultTableCellRenderer  modelocentrarPrexpansion = new DefaultTableCellRenderer ();
		modelocentrarPrexpansion.setHorizontalAlignment(SwingConstants.CENTER);
		jTable1.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrarPrexpansion);
	}
}
