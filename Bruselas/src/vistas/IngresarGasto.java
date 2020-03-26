package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.GastoBean;
import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class IngresarGasto extends JFrame {

	private JPanel contentPane;
	private JTextField textDescripcion;
	private JLabel lblTipo;
	private JLabel lblImporte;
	private JTextField textImporte;
	private JTextField textFecha;
	private JLabel lblIva;
	private JTextField textIva;
	private JComboBox comboBoxTipoGasto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresarGasto frame = new IngresarGasto();
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
	public IngresarGasto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 437, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 425, 245);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDescripcion = new JLabel("DESCRIPÓN");
		lblDescripcion.setBounds(10, 40, 96, 16);
		panel.add(lblDescripcion);
		
		textDescripcion = new JTextField();
		textDescripcion.setBounds(118, 35, 279, 26);
		panel.add(textDescripcion);
		textDescripcion.setColumns(10);
		
		lblTipo = new JLabel("TIPO");
		lblTipo.setBounds(10, 70, 61, 16);
		panel.add(lblTipo);
		
		comboBoxTipoGasto = new JComboBox();
		comboBoxTipoGasto.setBounds(118, 66, 153, 27);
		panel.add(comboBoxTipoGasto);
		Vector<String> tiposGastos=Controlador.getInstancia().obtenerTiposGastos();
		Collections.sort(tiposGastos);
		comboBoxTipoGasto.addItem("NINGUNO");
		for(int i=0;i<tiposGastos.size();i++){
			comboBoxTipoGasto.addItem(tiposGastos.get(i));
		}
		
		lblImporte = new JLabel("IMPORTE");
		lblImporte.setBounds(10, 100, 90, 16);
		panel.add(lblImporte);
		
		textImporte = new JTextField();
		textImporte.setBounds(118, 95, 130, 26);
		panel.add(textImporte);
		textImporte.setColumns(10);
		
		lblIva = new JLabel("IVA");
		lblIva.setBounds(10, 128, 61, 16);
		panel.add(lblIva);
		
		textIva = new JTextField();
		textIva.setBounds(118, 123, 130, 26);
		panel.add(textIva);
		textIva.setColumns(10);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFecha.getText().isEmpty()) {
					if(!textDescripcion.getText().isEmpty()){
						if(!comboBoxTipoGasto.getSelectedItem().toString().equals("NINGUNO")) {
							if(!textImporte.getText().isEmpty()) {
								if(!textIva.getText().isEmpty()) {
									try {
										float importe=Float.valueOf(textImporte.getText().replace(",", "."));
										float iva=Float.valueOf(textIva.getText().replace(",", "."));
										GastoBean g=new GastoBean();
										g.setFecha(textFecha.getText());
										g.setDescripcion(textDescripcion.getText());
										g.setTipoGasto(comboBoxTipoGasto.getSelectedItem().toString());
										g.setImporte(importe);
										g.setIva(iva);
										if(Controlador.getInstancia().ingresarGasto(g)) {
											JOptionPane.showMessageDialog(null, "Se ha ingresado la información.","INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
											textFecha.setText("");
											textDescripcion.setText("");
											comboBoxTipoGasto.setSelectedItem("NINGUNO");
											textImporte.setText("");
											textIva.setText("");
										}else {
											JOptionPane.showMessageDialog(null, "Ha ocurrido un error.Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
										}
									}catch(Exception ex) {
										JOptionPane.showMessageDialog(null, "Debe ingresar solo numeros en el campo importe e IVA.","ERROR", JOptionPane.ERROR_MESSAGE);
										//debe ingresar solo numeros en el campo importe e iva.
									}
								}else {
									JOptionPane.showMessageDialog(null, "Debe ingresar el IVA.","ERROR", JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Debe ingresar el importe.","ERROR", JOptionPane.ERROR_MESSAGE);
							}
							
						}else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de gasto.","ERROR", JOptionPane.ERROR_MESSAGE);
							
						}
					}else {
						JOptionPane.showMessageDialog(null, "Debe ingresar una descripción.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe ingresar una fecha.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGuardar.setBounds(56, 178, 137, 41);
		panel.add(btnGuardar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(232, 178, 137, 41);
		panel.add(btnCancelar);
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setBounds(10, 10, 61, 16);
		panel.add(lblFecha);
		
		textFecha= new JTextField();
		textFecha.setBounds(118, 5, 130, 26);
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
		btnAsignar.setBounds(280, 5, 117, 29);
		panel.add(btnAsignar);
	}
}
