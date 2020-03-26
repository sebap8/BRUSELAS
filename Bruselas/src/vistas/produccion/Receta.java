package vistas.produccion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import beans.ClarificanteBean;
import beans.GranoBean;
import beans.LupuloBean;
import beans.SalBean;
import controlador.Controlador;

import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JLabel;

public class Receta extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JScrollPane jScrollPane1;
	private TableModel jTable1Model;
	private JTable jTable1;
	private JScrollPane jScrollPane2;
	private TableModel jTable2Model;
	private JTable jTable2;
	private JScrollPane jScrollPane3;
	private TableModel jTable3Model;
	private JTable jTable3;
	private JScrollPane jScrollPane4;
	private TableModel jTable4Model;
	private JTable jTable4;
	private JLabel lblGranos;
	private JLabel lblSales;
	private JLabel lblClarificantesYNutrientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receta frame = new Receta("PRUEBA");
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
	public Receta(String estilo) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 803, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 788, 526);
		contentPane.add(panel);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RECETA - "+estilo.toUpperCase(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		panel.setLayout(null);
		jTable1Model = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Lúpulo","Tiempo","Cantidad"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
		jTable2Model = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Granos","Cantidad"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
	    jTable3Model = 
		new DefaultTableModel(
				new String[][] {  },
				new String[] {"Sal","Cantidad"}){public boolean isCellEditable(int row, int column)
			    {
				      return false;//This causes all cells to be not editable
				    }};
	    jTable4Model = 
	    		new DefaultTableModel(
	    				new String[][] {  },
	    				new String[] {"Producto","Tiempo","Cantidad"}){public boolean isCellEditable(int row, int column)
	    			    {
	    				      return false;//This causes all cells to be not editable
	    				    }};
		
		jScrollPane1 = new JScrollPane();
		jScrollPane1.setBounds(400, 41, 368, 211);
		panel.add(jScrollPane1);
		jTable1 = new JTable();
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
		jTable1.setBounds(12, 207, 200, 1);
		Vector<LupuloBean> lupulos=Controlador.getInstancia().obtenerLupulos(estilo);
		for(int i=0;i<lupulos.size();i++) {
			Object nuevo[]= {lupulos.get(i).getNombre(),lupulos.get(i).getTiempo(), lupulos.get(i).getCantidad()};
			DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();
			temp.addRow(nuevo);
		}
		
		JLabel lblLupulos = new JLabel("LÚPULOS");
		lblLupulos.setBounds(538, 20, 61, 16);
		panel.add(lblLupulos);
		
		jScrollPane2 = new JScrollPane();
		jScrollPane2.setBounds(21, 41, 368, 211);
		panel.add(jScrollPane2);
		jTable2 = new JTable();
		jScrollPane2.setViewportView(jTable2);
		jTable2.setModel(jTable2Model);
		jTable2.setBounds(12, 207, 200, 1);
		Vector<GranoBean> granos=Controlador.getInstancia().obtenerGranos(estilo);
		for(int i=0;i<granos.size();i++) {
			Object nuevo[]= {granos.get(i).getNombre(),granos.get(i).getCantidad()};
			DefaultTableModel temp = (DefaultTableModel) jTable2.getModel();
			temp.addRow(nuevo);
		}
		
		lblGranos = new JLabel("GRANOS");
		lblGranos.setBounds(172, 20, 61, 16);
		panel.add(lblGranos);
		
		lblSales = new JLabel("SALES");
		lblSales.setBounds(172, 264, 61, 16);
		panel.add(lblSales);
		
		lblClarificantesYNutrientes = new JLabel("CLARIFICANTES Y NUTRIENTES");
		lblClarificantesYNutrientes.setBounds(466, 264, 217, 16);
		panel.add(lblClarificantesYNutrientes);
		
		jScrollPane3 = new JScrollPane();
		jScrollPane3.setBounds(21, 292, 368, 211);
		panel.add(jScrollPane3);
		jTable3 = new JTable();
		jScrollPane3.setViewportView(jTable3);
		jTable3.setModel(jTable3Model);
		jTable3.setBounds(12, 207, 200, 1);
		Vector<SalBean> sales= Controlador.getInstancia().obtenerSales(estilo);
		for(int i=0;i<sales.size();i++) {
			Object nuevo[]= {sales.get(i).getNombre(),sales.get(i).getCantidad()};
			DefaultTableModel temp = (DefaultTableModel) jTable3.getModel();
			temp.addRow(nuevo);
		}
		
		jScrollPane4 = new JScrollPane();
		jScrollPane4.setBounds(400, 292, 368, 211);
		panel.add(jScrollPane4);
		jTable4 = new JTable();
		jScrollPane4.setViewportView(jTable4);
		jTable4.setModel(jTable4Model);
		jTable4.setBounds(12, 207, 200, 1);
		Vector<ClarificanteBean> clarificante= Controlador.getInstancia().obtenerClarificantes(estilo);
		for(int i=0;i<clarificante.size();i++) {
			Object nuevo[]= {clarificante.get(i).getNombre(),clarificante.get(i).getTiempo(), clarificante.get(i).getCantidad()};
			DefaultTableModel temp = (DefaultTableModel) jTable4.getModel();
			temp.addRow(nuevo);
		}
		
		
		DefaultTableCellRenderer  modelocentrarPrexpansion = new DefaultTableCellRenderer ();
		modelocentrarPrexpansion.setHorizontalAlignment(SwingConstants.CENTER);

		
        jTable1.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
		jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrarPrexpansion);
		jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrarPrexpansion);
		jTable2.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
		jTable2.getColumnModel().getColumn(1).setCellRenderer(modelocentrarPrexpansion);
		jTable3.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
		jTable3.getColumnModel().getColumn(1).setCellRenderer(modelocentrarPrexpansion);
		jTable4.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
		jTable4.getColumnModel().getColumn(1).setCellRenderer(modelocentrarPrexpansion);
		jTable4.getColumnModel().getColumn(2).setCellRenderer(modelocentrarPrexpansion);
	
	}
}
