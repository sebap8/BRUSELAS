package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.ReciboBean;
import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class IngresarPago extends JFrame {

	private JPanel contentPane;
	private JTextField textFecha;
	private JTextField textRecibo;
	private JTextField textImporte;
	private JComboBox comboBoxClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresarPago frame = new IngresarPago();
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
	public IngresarPago() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 232);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setBounds(10, 10, 61, 16);
		panel.add(lblFecha);
		
		JLabel lblRecibo = new JLabel("RECIBO");
		lblRecibo.setBounds(10, 45, 61, 16);
		panel.add(lblRecibo);
		
		JLabel lblCliente = new JLabel("CLIENTE");
		lblCliente.setBounds(10, 80, 61, 16);
		panel.add(lblCliente);
		
		JLabel lblImporte = new JLabel("IMPORTE");
		lblImporte.setBounds(10, 115, 61, 16);
		panel.add(lblImporte);
		
		textFecha = new JTextField();
		textFecha.setBounds(83, 5, 130, 26);
		panel.add(textFecha);
		textFecha.setColumns(10);
		textFecha.setEnabled(false);
		
		JButton btnAsignar = new JButton("ASIGNAR");
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fe=new DatePicker().setPickedDate();
				textFecha.setText(fe);
			}
		});
		btnAsignar.setBounds(225, 5, 117, 29);
		panel.add(btnAsignar);
		
		textRecibo = new JTextField();
		textRecibo.setBounds(83, 40, 130, 26);
		panel.add(textRecibo);
		textRecibo.setColumns(10);
		
		comboBoxClientes = new JComboBox();
		comboBoxClientes.setBounds(83, 76, 259, 27);
		panel.add(comboBoxClientes);
		comboBoxClientes.addItem("NINGUNO");
		Vector<String> clientes = Controlador.getInstancia().obtenerClientes();
		for(int i=0;i<clientes.size();i++) {
			comboBoxClientes.addItem(clientes.get(i));
		}
		
		textImporte = new JTextField();
		textImporte.setBounds(83, 110, 130, 26);
		panel.add(textImporte);
		textImporte.setColumns(10);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFecha.getText().isEmpty()){
					try{
						if(!textRecibo.getText().isEmpty()) {
							int recibo=Integer.valueOf(textRecibo.getText());
							if(!Controlador.getInstancia().existeRecibo(recibo)) {
								if(!comboBoxClientes.getSelectedItem().toString().equals("NINGUNO")) {
									if(!textImporte.getText().isEmpty()) {
										float importe =Float.valueOf(textImporte.getText().replace(",", "."));
										ReciboBean r=new ReciboBean();
										r.setFecha(textFecha.getText());
										r.setRecibo(recibo);
										r.setCliente(comboBoxClientes.getSelectedItem().toString());
										r.setImporte(importe);
										if(Controlador.getInstancia().ingresarPago(r)){
											JOptionPane.showMessageDialog(null, "Se ha ingresado la informacion correctamente.","EXITO", JOptionPane.INFORMATION_MESSAGE);
											textFecha.setText("");
											textImporte.setText("");
											textRecibo.setText("");
											comboBoxClientes.setSelectedItem("NINGUNO");
										}else {
											JOptionPane.showMessageDialog(null, "Se ha producido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
										}
									}
								}else{
									JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente.","ERROR", JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "El número de recibo ya existe.","ERROR", JOptionPane.ERROR_MESSAGE);
							}
							
						}else{
							JOptionPane.showMessageDialog(null, "Debe ingresar el número del recibo.","ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Debe ingresar solo números en importe y recibo.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Debe ingresar la fecha del pago.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGuardar.setBounds(53, 165, 137, 41);
		panel.add(btnGuardar);
		
		JButton btnCalcelar = new JButton("CALCELAR");
		btnCalcelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCalcelar.setBounds(213, 165, 137, 41);
		panel.add(btnCalcelar);
	}
}
