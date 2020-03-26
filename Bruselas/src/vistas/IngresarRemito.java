package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import beans.ClienteBean;
import beans.ItemRemitoBean;
import beans.ProductoBean;
import beans.RemitoBean;
import controlador.Controlador;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class IngresarRemito extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField textFecha;
	private JLabel lblFecha;
	private JButton btnAsignar;
	private JLabel lblCliente;
	private JComboBox comboBoxCliente;
	private JTextField textRemito;
	private JLabel lblProducto;
	private JLabel lblRemito;
	private JComboBox comboBoxProducto;
	private JScrollPane jScrollPane1;
	private TableModel jTable1Model;
	private JTable jTable1;
	private JLabel lblCantidad;
	private JTextField textCantidad;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JTextField textPrecio;
	private JLabel lblBarriles;
	private JTextArea textBarrilesEntregados;
	private JTextArea textBarrilesRetirados;
	private JLabel lblBarrilesRetirados;
	private JComboBox comboBoxEntregados;
	private JComboBox comboBoxRetirados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresarRemito frame = new IngresarRemito();
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
	public IngresarRemito() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 659, 674);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 647, 640);
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
		
		lblRemito = new JLabel("REMITO Nº");
		lblRemito.setBounds(10, 18, 105, 16);
		panel.add(lblRemito);
		
		textRemito = new JTextField();
		textRemito.setBounds(90, 13, 130, 26);
		panel.add(textRemito);
		textRemito.setColumns(10);
		
		lblProducto = new JLabel("PRODUCTO");
		lblProducto.setBounds(10, 141, 85, 16);
		panel.add(lblProducto);
		
		comboBoxProducto = new JComboBox();
		comboBoxProducto.setBounds(90, 137, 210, 27);
		panel.add(comboBoxProducto);
		
		JPanel panelProductos= new JPanel();
		panelProductos.setBounds(10, 273, 576, 183);
		panel.add(panelProductos);
		panelProductos.setLayout(null);
		panelProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		jScrollPane1 = new JScrollPane();
		panelProductos.add(jScrollPane1);
		jScrollPane1.setBounds(6, 6, 564, 171);
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
		
		
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(293, 593, 137, 41);
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
					int precio =Integer.valueOf(textPrecio.getText());
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
		
		lblBarriles = new JLabel("BARRILES ENTREGADOS");
		lblBarriles.setBounds(10, 483, 148, 16);
		panel.add(lblBarriles);
		
		textBarrilesEntregados = new JTextArea();
	    JScrollPane scroll = new JScrollPane ( textBarrilesEntregados );
		scroll.setBounds(new Rectangle(170, 468, 193, 50));                     
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    panel.add(scroll);
	    textBarrilesEntregados.setLineWrap(true);
	    textBarrilesEntregados.setWrapStyleWord(true);
	    textBarrilesEntregados.setEditable(false);
		
		lblBarrilesRetirados = new JLabel("BARRILES RETIRADOS");
		lblBarrilesRetirados.setBounds(10, 545, 148, 16);
		panel.add(lblBarrilesRetirados);
		
		textBarrilesRetirados = new JTextArea();
	    JScrollPane scroll1 = new JScrollPane ( textBarrilesRetirados );
		scroll1.setBounds(new Rectangle(170, 530, 193, 50));                     
	    scroll1.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    panel.add(scroll1);
	    textBarrilesRetirados.setLineWrap(true);
	    textBarrilesRetirados.setWrapStyleWord(true);
	    textBarrilesRetirados.setEditable(false);
	    
	    comboBoxEntregados = new JComboBox();
	    comboBoxEntregados.setBounds(387, 479, 124, 27);
	    panel.add(comboBoxEntregados);
	    
	    comboBoxRetirados = new JComboBox();
	    comboBoxRetirados.setBounds(387, 541, 124, 27);
	    panel.add(comboBoxRetirados);
	    comboBoxEntregados.addItem("NINGUNO");
    	comboBoxRetirados.addItem("NINGUNO");
	    for(int i=1;i<=200;i++) {
	    	comboBoxEntregados.addItem(i);
	    	comboBoxRetirados.addItem(i);
	    }
	    
	    JButton btnAgregarEntregados = new JButton("AGREGAR");
	    btnAgregarEntregados.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(!comboBoxEntregados.getSelectedItem().toString().equals("NINGUNO")) {
	    			textBarrilesEntregados.setText(textBarrilesEntregados.getText()+comboBoxEntregados.getSelectedItem()+",");
	    		}else {
	    			//DEBE SELECCIONAR UN NUMERO DE BARRIL.
	    		}
	    		
	    	}
	    });
	    btnAgregarEntregados.setBounds(523, 478, 105, 29);
	    panel.add(btnAgregarEntregados);
	    
	    JButton btnAgregarRetirados = new JButton("AGREGAR");
	    btnAgregarRetirados.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(!comboBoxRetirados.getSelectedItem().toString().equals("NINGUNO")) {
	    			textBarrilesRetirados.setText(textBarrilesRetirados.getText()+comboBoxRetirados.getSelectedItem()+",");
	    		}else {
	    			//DEBE SELECCIONAR UN NUMERO DE BARRIL.
	    		}
	    	}
	    });
	    btnAgregarRetirados.setBounds(523, 540, 105, 29);
	    panel.add(btnAgregarRetirados);
		
		Vector<String> clientes = Controlador.getInstancia().obtenerClientes();
		for(int i=0;i<clientes.size();i++) {
			comboBoxCliente.addItem(clientes.get(i));
		}
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textRemito.getText().isEmpty() && !Controlador.getInstancia().existeRemito(textRemito.getText())){
					try {
						int remito=Integer.valueOf(textRemito.getText());
						if(!textFecha.getText().isEmpty()) {
							if(!comboBoxCliente.getSelectedItem().toString().equals("NINGUNO")) {
								//ACA HAY QUE PARTIR LOS BARRILES ENTREGADOS Y RETIRADOS. LOS ENTREGADOS
								String[] barrilesEntregados=textBarrilesEntregados.getText().split(",");
								String[] barrilesRecibidos=textBarrilesEntregados.getText().split(",");
								
								if(!textBarrilesEntregados.getText().isEmpty() || !textBarrilesRetirados.getText().isEmpty()){
									if(jTable1.getRowCount()>0){
										RemitoBean rem =new RemitoBean();
										rem.setNumero(textRemito.getText());
										rem.setCliente(comboBoxCliente.getSelectedItem().toString());
										rem.setFecha(textFecha.getText());
										rem.setEstado("ACTIVO");
										rem.setBarrilesRecibidos(barrilesEntregados);
										rem.setBarrilesRecibidos(barrilesRecibidos);
										for(int i=0;i<jTable1.getRowCount();i++){
											ItemRemitoBean it = new ItemRemitoBean();
											it.setProducto(jTable1.getValueAt(i, 0).toString());
											it.setCantidad(Integer.valueOf(jTable1.getValueAt(i, 1).toString()));
											it.setPrecio(Integer.valueOf(jTable1.getValueAt(i, 2).toString()));
											rem.getItems().add(it);
										}
										if(Controlador.getInstancia().ingresarRemito(rem)) {
											JOptionPane.showMessageDialog(null, "Se ha ingresado la informacion correctamente.","EXITO", JOptionPane.INFORMATION_MESSAGE);
											comboBoxCliente.setSelectedItem("NINGUNO");
											comboBoxProducto.setSelectedItem("NINGUNO");
											textRemito.setText("");
											textFecha.setText("");
											textCantidad.setText("");
											textPrecio.setText("");
											textBarrilesRetirados.setText("");
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
									JOptionPane.showMessageDialog(null, "Debe ingresar los barriles entregados y recibidos.","ERROR", JOptionPane.ERROR_MESSAGE);
								}
								
							}else {
								JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente.","ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Debe ingresar la fecha del remito.","ERROR", JOptionPane.ERROR_MESSAGE);
						}
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Debe ingresar solo números en el remito.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
				}else{
					if(textRemito.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Debe ingresar un numero de remito.","ERROR", JOptionPane.ERROR_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "El número de remito ingresado ya se encuentra registrado.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		btnGuardar.setBounds(90, 593, 137, 41);
		panel.add(btnGuardar);
		DefaultTableCellRenderer  modelocentrarPrexpansion = new DefaultTableCellRenderer ();
		modelocentrarPrexpansion.setHorizontalAlignment(SwingConstants.CENTER);
		jTable1.getColumnModel().getColumn(0).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrarPrexpansion);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrarPrexpansion);
		
		
		
	}
}
