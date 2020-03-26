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

import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ListadoConsumoClientes extends JFrame {

	private JPanel contentPane;
	private JLabel lblDesde;
	private JLabel lblHasta;
	private JTextField textDesde;
	private JTextField textHasta;
	private JLabel lblCliente;
	private JScrollPane jScrollPane1;
	private TableModel jTable1Model;
	private JTable jTable1;
	private JButton btnDetalle;
	private JComboBox comboBoxClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoConsumoClientes frame = new ListadoConsumoClientes();
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
	public ListadoConsumoClientes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 703, 418);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblDesde = new JLabel("DESDE");
		lblDesde.setBounds(10, 10, 61, 16);
		panel.add(lblDesde);
		
		lblHasta = new JLabel("HASTA");
		lblHasta.setBounds(10, 45, 61, 16);
		panel.add(lblHasta);
		
		textDesde = new JTextField();
		textDesde.setBounds(72, 5, 130, 26);
		panel.add(textDesde);
		textDesde.setColumns(10);
		textDesde.setEnabled(false);
		
		textHasta = new JTextField();
		textHasta.setBounds(72, 40, 130, 26);
		panel.add(textHasta);
		textHasta.setColumns(10);
		textHasta.setEnabled(false);
		
		lblCliente = new JLabel("CLIENTE");
		lblCliente.setBounds(10, 85, 75, 16);
		panel.add(lblCliente);
		
		comboBoxClientes = new JComboBox();
		comboBoxClientes.setBounds(72, 81, 295, 27);
		comboBoxClientes.addItem("NINGUNO");
		Vector<String> clientes = Controlador.getInstancia().obtenerClientes();
		for(int i=0;i<clientes.size();i++) {
			comboBoxClientes.addItem(clientes.get(i));
		}
		panel.add(comboBoxClientes);
		
		JButton btnDesde = new JButton("ASIGNAR");
		btnDesde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fe=new DatePicker().setPickedDate();
				textDesde.setText(fe);
			}
		});
		btnDesde.setBounds(214, 5, 117, 29);
		panel.add(btnDesde);
		
		JButton btnHasta = new JButton("ASIGNAR");
		btnHasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fe=new DatePicker().setPickedDate();
				textHasta.setText(fe);
			}
		});
		btnHasta.setBounds(214, 40, 117, 29);
		panel.add(btnHasta);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textDesde.getText().isEmpty()){
					if(!textHasta.getText().isEmpty()) {
						if(!comboBoxClientes.getSelectedItem().toString().equals("NINGUNO")) {
//							if(Controlador.getInstancia().obtenerListadoResumenCuentaCliente(textDesde.getText(),textHasta.getText(),comboBoxClientes.getSelectedItem().toString())) {
//								
//							}
						}else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente.","ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Debe ingresar la fecha hasta.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe ingresar la fecha desde.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.setBounds(379, 80, 117, 29);
		panel.add(btnBuscar);
		
		jScrollPane1 = new JScrollPane();
		panel.add(jScrollPane1);
		jScrollPane1.setBounds(10, 135, 639, 277);
		jTable1Model = 
				new DefaultTableModel(
						new String[][] {  },
						new String[] {"Tipo","Numero","Fecha","Cliente","Litros","Debe","Haber"}){public boolean isCellEditable(int row, int column)
					    {
						      return false;//This causes all cells to be not editable
						    }};
		jTable1 = new JTable();
		jScrollPane1.setViewportView(jTable1);
		jTable1.setModel(jTable1Model);
		jTable1.setBounds(12, 207, 555, 194);
		
		btnDetalle = new JButton("DETALLE");
		btnDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDetalle.setBounds(526, 80, 117, 29);
		panel.add(btnDetalle);
		
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
