package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import beans.FacturaBean;
import beans.ItemFacturaBean;
import beans.ItemRemitoBean;
import beans.RemitoBean;
import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class IngresarFactura extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField textFecha;
	private JLabel lblFecha;
	private JButton btnAsignar;
	private JLabel lblCliente;
	private JComboBox comboBoxCliente;
	private JTextField textFactura;
	private JLabel lblProducto;
	private JLabel lblFactura;
	private JComboBox comboBoxProducto;
	private JScrollPane jScrollPane1;
	private TableModel jTable1Model;
	private JTable jTable1;
	private JLabel lblCantidad;
	private JTextField textCantidad;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JTextField textPrecio;
	private JLabel lblImpuestos;
	private JLabel lblIva;
	private JLabel lblTotal;
	private JTextField textImpuestos;
	private JTextField textIVA;
	private JTextField textTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresarFactura frame = new IngresarFactura();
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
	public IngresarFactura() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 659, 650);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 647, 616);
		contentPane.add(panel);
		
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		panel.setLayout(null);
		
		lblFecha = new JLabel("FECHA");
		lblFecha.setBounds(10, 63, 61, 16);
		panel.add(lblFecha);
		
		textFecha = new JTextField();
		textFecha.setBounds(90, 58, 130, 26);
		panel.add(textFecha);
		textFecha.setColumns(10);
		textFecha.setEnabled(false);
		
		btnAsignar = new JButton("ASIGNAR");
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fe=new DatePicker().setPickedDate();
				textFecha.setText(fe);
			}
		});
		btnAsignar.setBounds(246, 58, 117, 29);
		panel.add(btnAsignar);
		
		lblCliente = new JLabel("CLIENTE");
		lblCliente.setBounds(10, 96, 68, 16);
		panel.add(lblCliente);
		
		comboBoxCliente = new JComboBox();
		comboBoxCliente.setBounds(90, 92, 363, 27);
		panel.add(comboBoxCliente);
		
		lblFactura = new JLabel("FACTURA Nº");
		lblFactura.setBounds(10, 18, 105, 16);
		panel.add(lblFactura);
		
		textFactura = new JTextField();
		textFactura.setBounds(90, 13, 130, 26);
		panel.add(textFactura);
		textFactura.setColumns(10);
		
		lblProducto = new JLabel("PRODUCTO");
		lblProducto.setBounds(10, 141, 85, 16);
		panel.add(lblProducto);
		
		comboBoxProducto = new JComboBox();
		comboBoxProducto.setBounds(90, 137, 210, 27);
		panel.add(comboBoxProducto);
		
		JPanel panelProductos= new JPanel();
		panelProductos.setBounds(6, 273, 635, 151);
		panel.add(panelProductos);
		panelProductos.setLayout(null);
		panelProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		jScrollPane1 = new JScrollPane();
		panelProductos.add(jScrollPane1);
		jScrollPane1.setBounds(6, 6, 623, 139);
		jTable1Model = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Producto","Precio","Cantidad"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
		jTable1 = new JTable();
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
		jTable1.setBounds(12, 207, 555, 194);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFactura.getText().isEmpty()){
					try {
						int nrofactura=Integer.valueOf(textFactura.getText());
						if(!textFecha.getText().isEmpty()) {
							if(!comboBoxCliente.getSelectedItem().toString().equals("NINGUNO")) {
								if(jTable1.getRowCount()>0){
									FacturaBean factura =new FacturaBean();
									factura.setNumero(nrofactura);
									factura.setCliente(comboBoxCliente.getSelectedItem().toString());
									factura.setFecha(textFecha.getText());
									factura.setImpuestos(Float.valueOf(textImpuestos.getText().replace(",", ".")));
									factura.setIVA(Float.valueOf(textIVA.getText().replace(",", ".")));
									factura.setEstado("ACTIVO");
								
									for(int i=0;i<jTable1.getRowCount();i++){
										ItemFacturaBean it = new ItemFacturaBean();
										it.setProducto(jTable1.getValueAt(i, 0).toString());
										it.setPrecio(Float.valueOf(jTable1.getValueAt(i, 1).toString()));
										it.setCantidad(Integer.valueOf(jTable1.getValueAt(i, 2).toString()));
										factura.getItems().add(it);
									}
									if(Controlador.getInstancia().ingresarFactura(factura)) {
										JOptionPane.showMessageDialog(null, "Se ha ingresado la informacion correctamente.","EXITO", JOptionPane.INFORMATION_MESSAGE);
										comboBoxCliente.setSelectedItem("NINGUNO");
										comboBoxProducto.setSelectedItem("NINGUNO");
										textFactura.setText("");
										textFecha.setText("");
										textCantidad.setText("");
										textPrecio.setText("");
										textIVA.setText("");
										textImpuestos.setText("");
										textTotal.setText("");
										DefaultTableModel temp1 = (DefaultTableModel) jTable1.getModel();
										while(jTable1.getRowCount()>0){
											temp1.removeRow(jTable1.getRowCount()-1);
										}
										
									}else {
										JOptionPane.showMessageDialog(null, "Se ha producido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
									}
								}else {
									JOptionPane.showMessageDialog(null, "Debe ingresar al menos un producto.","ERROR", JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente.","ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Debe ingresar la fecha del remito.","ERROR", JOptionPane.ERROR_MESSAGE);
						}
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Debe ingresar número de factura correctamente.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Debe ingresar un numero de remito.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGuardar.setBounds(94, 569, 137, 41);
		panel.add(btnGuardar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(296, 569, 137, 41);
		panel.add(btnCancelar);
		
		lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setBounds(10, 228, 85, 16);
		panel.add(lblCantidad);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(90, 223, 130, 26);
		panel.add(textCantidad);
		textCantidad.setColumns(10);
		
		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cantidad=Integer.valueOf(textCantidad.getText());
					float precio =Float.valueOf(textPrecio.getText().replace(",", "."));
					if(!comboBoxProducto.getSelectedItem().toString().equals("NINGUNO")) {
						Object nuevo[]= {comboBoxProducto.getSelectedItem().toString(),precio,cantidad};
						DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();
						temp.addRow(nuevo);
						textPrecio.setText("");
						textCantidad.setText("");
					}else{
						JOptionPane.showMessageDialog(null, "Debe seleccionar un producto. ","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Debe ingresar solo numeros en el campo cantidad. ","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnAgregar.setBounds(233, 223, 117, 29);
		panel.add(btnAgregar);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jTable1.getRowCount()>0 && jTable1.getSelectedRow() >=0){
					DefaultTableModel temp = (DefaultTableModel) jTable1.getModel();
					temp.removeRow(jTable1.getSelectedRow());
				}
				
			}
		});
		btnEliminar.setBounds(362, 223, 117, 29);
		panel.add(btnEliminar);
		
		comboBoxProducto.addItem("NINGUNO");
		Vector<String> productos = Controlador.getInstancia().obtenerProductos();
		for(int i=0;i<productos.size();i++) {
			comboBoxProducto.addItem(productos.get(i));
		}
		
		comboBoxCliente.addItem("NINGUNO");
		
		JLabel lblPrecio = new JLabel("PRECIO");
		lblPrecio.setBounds(10, 188, 61, 16);
		panel.add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setBounds(90, 183, 130, 26);
		panel.add(textPrecio);
		textPrecio.setColumns(10);
		
		lblImpuestos = new JLabel("IMPUESTOS");
		lblImpuestos.setBounds(10, 436, 85, 16);
		panel.add(lblImpuestos);
		
		lblIva = new JLabel("IVA");
		lblIva.setBounds(10, 469, 61, 16);
		panel.add(lblIva);
		
		lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(10, 500, 61, 16);
		panel.add(lblTotal);
		
		textImpuestos = new JTextField();
		textImpuestos.setBounds(113, 431, 130, 26);
		panel.add(textImpuestos);
		textImpuestos.setColumns(10);
		
		textIVA = new JTextField();
		textIVA.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(jTable1.getRowCount()>0) {
					float iva=Float.valueOf(textIVA.getText().replace(",", "."));
					float impuestos = Float.valueOf(textImpuestos.getText().replace(",", "."));
					float importe=0;
					for(int i=0;i<jTable1.getRowCount();i++){
						importe+=Float.valueOf(jTable1.getValueAt(i, 1).toString())*Integer.valueOf(jTable1.getValueAt(i, 2).toString());
					}
					textTotal.setText(String.valueOf(importe+iva+impuestos));
				}
			}
		});
		textIVA.setBounds(113, 464, 130, 26);
		panel.add(textIVA);
		textIVA.setColumns(10);
		
		textTotal = new JTextField();
		textTotal.setBounds(113, 495, 130, 26);
		panel.add(textTotal);
		textTotal.setColumns(10);
		textTotal.setEditable(false);
		
		Vector<String> clientes = Controlador.getInstancia().obtenerClientes();
		for(int i=0;i<clientes.size();i++) {
			comboBoxCliente.addItem(clientes.get(i));
		}
		DefaultTableCellRenderer  modelocentrarPrexpansion = new DefaultTableCellRenderer ();
		modelocentrarPrexpansion.setHorizontalAlignment(SwingConstants.CENTER);
		jTable1.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrarPrexpansion);
		
		
		
	}
}
