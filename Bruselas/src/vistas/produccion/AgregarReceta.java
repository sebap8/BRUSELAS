package vistas.produccion;

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

import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class AgregarReceta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textCantidadGranos;
	private JTextField textFieldCantidadLupulo;
	private JTextField textFieldTiempo;
	private JScrollPane jScrollPane1;
	private TableModel jTable1Model;
	private JTable jTableGranos;
	private JScrollPane jScrollPaneLupulos;
	private TableModel jTable1Model2;
	private JTable jTableLupulos;
	private JComboBox comboBoxGranos;
	private JLabel lblGranos;
	private JPanel panelGrano;
	private JPanel panel;
	private JLabel lblCantidad;
	private JButton btnAgregarGranos;
	private JButton buttonEliminarGranos;
	private JPanel panelLupulo;
	private JLabel lblLupulo;
	private JComboBox comboBoxLupulos;
	private JLabel lblCantidad_1;
	private JLabel lblTiempo;
	private JLabel lblMinutos;
	private JButton buttonAgregarLupulos;
	private JButton buttonEliminarLupulos;
	private JButton btnAgregar;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarReceta frame = new AgregarReceta();
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
	public AgregarReceta() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("AGREGAR RECETA");
		setBounds(100, 100, 952, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 940, 466);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(10, 10, 61, 16);
		panel.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(103, 5, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		panelGrano = new JPanel();
		panelGrano.setBounds(10, 39, 450, 335);
		panel.add(panelGrano);
		panelGrano.setLayout(null);
		panelGrano.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		lblGranos = new JLabel("GRANO");
		lblGranos.setBounds(10, 10, 71, 16);
		panelGrano.add(lblGranos);
		
		comboBoxGranos = new JComboBox();
		comboBoxGranos.setBounds(89, 6, 210, 27);
		panelGrano.add(comboBoxGranos);
		Vector<String> granos =Controlador.getInstancia().obtenerGranos();
		Collections.sort(granos);
		comboBoxGranos.addItem("NINGUNO");
		for(int i=0;i<granos.size();i++) {
			comboBoxGranos.addItem(granos.get(i));
		}
		
		lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setBounds(10, 39, 95, 16);
		panelGrano.add(lblCantidad);
		
		textCantidadGranos = new JTextField();
		textCantidadGranos.setBounds(89, 34, 130, 26);
		panelGrano.add(textCantidadGranos);
		textCantidadGranos.setColumns(10);
		
		btnAgregarGranos = new JButton("+");
		btnAgregarGranos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float cantidad=Float.valueOf(textCantidadGranos.getText().replace(",", "."));
					if(!comboBoxGranos.getSelectedItem().toString().equals("NINGUNO")) {
						if(!textCantidadGranos.getText().isEmpty()) {
							Object nuevo[]= {comboBoxGranos.getSelectedItem().toString(),cantidad};
							DefaultTableModel temp = (DefaultTableModel) jTableGranos.getModel();
							temp.addRow(nuevo);
							comboBoxGranos.setSelectedItem("NINGUNO");
							textCantidadGranos.setText("");
						}else {
							JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad. ","ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de grano. ","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Debe ingresar solo numeros en el campo cantidad. ","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAgregarGranos.setBounds(231, 34, 60, 29);
		panelGrano.add(btnAgregarGranos);
		
		buttonEliminarGranos = new JButton("-");
		buttonEliminarGranos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jTableGranos.getRowCount()>0 && jTableGranos.getSelectedRow() >=0){
					DefaultTableModel temp = (DefaultTableModel) jTableGranos.getModel();
					temp.removeRow(jTableGranos.getSelectedRow());
				}
			}
		});
		buttonEliminarGranos.setBounds(293, 34, 60, 29);
		panelGrano.add(buttonEliminarGranos);
		
		panelLupulo = new JPanel();
		panelLupulo.setBounds(472, 39, 450, 335);
		panel.add(panelLupulo);
		panelLupulo.setLayout(null);
		panelLupulo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		lblLupulo = new JLabel("LUPULO");
		lblLupulo.setBounds(10, 10, 61, 16);
		panelLupulo.add(lblLupulo);
		
		comboBoxLupulos = new JComboBox();
		comboBoxLupulos.setBounds(89, 6, 200, 27);
		panelLupulo.add(comboBoxLupulos);
		Vector<String> lupulos=Controlador.getInstancia().obtenerLupulos();
		Collections.sort(lupulos);
		comboBoxLupulos.addItem("NINGUNO");
		for(int i=0;i<lupulos.size();i++) {
			comboBoxLupulos.addItem(lupulos.get(i));
		}
		lblCantidad_1 = new JLabel("CANTIDAD");
		lblCantidad_1.setBounds(10, 39, 80, 16);
		panelLupulo.add(lblCantidad_1);
		
		textFieldCantidadLupulo = new JTextField();
		textFieldCantidadLupulo.setBounds(89, 34, 130, 26);
		panelLupulo.add(textFieldCantidadLupulo);
		textFieldCantidadLupulo.setColumns(10);
		
		lblTiempo = new JLabel("TIEMPO");
		lblTiempo.setBounds(10, 68, 61, 16);
		panelLupulo.add(lblTiempo);
		
		textFieldTiempo = new JTextField();
		textFieldTiempo.setBounds(89, 63, 130, 26);
		panelLupulo.add(textFieldTiempo);
		textFieldTiempo.setColumns(10);
		
		lblMinutos = new JLabel("( MINUTOS )");
		lblMinutos.setBounds(362, 68, 135, 16);
		panelLupulo.add(lblMinutos);
		
		jScrollPane1 = new JScrollPane();
		panelGrano.add(jScrollPane1);
		jScrollPane1.setBounds(6, 97, 438, 220);
		jTable1Model = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Grano","Cantidad"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
		jTableGranos = new JTable();
		jScrollPane1.setViewportView(jTableGranos);
		jTableGranos.setModel(jTable1Model);
		jTableGranos.setBounds(12, 207, 555, 194);
		
		jScrollPaneLupulos = new JScrollPane();
		panelLupulo.add(jScrollPaneLupulos);
		jScrollPaneLupulos.setBounds(6, 97, 438, 220);
		jTable1Model2 = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Lupulo","Cantidad","Tiempo"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
		jTableLupulos = new JTable();
		jScrollPaneLupulos.setViewportView(jTableLupulos);
		jTableLupulos.setModel(jTable1Model2);
		jTableLupulos.setBounds(12, 207, 555, 194);
		
		buttonAgregarLupulos = new JButton("+");
		buttonAgregarLupulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonAgregarLupulos.setBounds(229, 63, 60, 29);
		panelLupulo.add(buttonAgregarLupulos);
		
		buttonEliminarLupulos = new JButton("-");
		buttonEliminarLupulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonEliminarLupulos.setBounds(291, 63, 60, 29);
		panelLupulo.add(buttonEliminarLupulos);
		
		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setBounds(297, 405, 137, 41);
		panel.add(btnAgregar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(506, 405, 137, 41);
		panel.add(btnCancelar);
		
		DefaultTableCellRenderer  modelocentrarGranos = new DefaultTableCellRenderer ();
		modelocentrarGranos.setHorizontalAlignment(SwingConstants.CENTER);
		jTableGranos.getColumnModel().getColumn(0).setCellRenderer(modelocentrarGranos);
        jTableGranos.getColumnModel().getColumn(1).setCellRenderer(modelocentrarGranos);
        
        DefaultTableCellRenderer render = (DefaultTableCellRenderer) jTableGranos.getTableHeader().getDefaultRenderer();
        render.setHorizontalAlignment(JLabel.CENTER);
        jTableGranos.getTableHeader().setDefaultRenderer(render);
        
        DefaultTableCellRenderer  modelocentrarLupulos = new DefaultTableCellRenderer ();
		modelocentrarLupulos.setHorizontalAlignment(SwingConstants.CENTER);
		jTableLupulos.getColumnModel().getColumn(0).setCellRenderer(modelocentrarLupulos);
		jTableLupulos.getColumnModel().getColumn(1).setCellRenderer(modelocentrarLupulos);
		jTableLupulos.getColumnModel().getColumn(2).setCellRenderer(modelocentrarLupulos);
		
		DefaultTableCellRenderer render1 = (DefaultTableCellRenderer) jTableLupulos.getTableHeader().getDefaultRenderer();
        render.setHorizontalAlignment(JLabel.CENTER);
        jTableLupulos.getTableHeader().setDefaultRenderer(render1);

	}
}
