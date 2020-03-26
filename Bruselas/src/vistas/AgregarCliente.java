package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JPanel panel;
	private JLabel lblNombre;
	private JButton btnCancelar;
	private JButton btnAceptar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarCliente frame = new AgregarCliente();
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
	public AgregarCliente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 438, 124);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(10, 10, 61, 16);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(83, 5, 130, 26);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		btnAceptar = new JButton("GUARDAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textNombre.getText().isEmpty()) {
					if(Controlador.getInstancia().ingresarCliente(textNombre.getText())) {
						JOptionPane.showMessageDialog(null, "Se ha ingresado la informacion correctamente.","EXITO", JOptionPane.INFORMATION_MESSAGE);
						textNombre.setText("");
					}else {
						JOptionPane.showMessageDialog(null, "Se ha producido un error. Comuníquese con el administrador.","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de cliente.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptar.setBounds(58, 57, 137, 41);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(211, 57, 137, 41);
		panel.add(btnCancelar);
	}
}
