package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ListadoFacturasEmitidas extends JFrame {

	private JPanel contentPane;
	private JTextField textDesde;
	private JTextField textHasta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoFacturasEmitidas frame = new ListadoFacturasEmitidas();
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
	public ListadoFacturasEmitidas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 674, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 662, 266);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFechaDesde = new JLabel("FECHA DESDE");
		lblFechaDesde.setBounds(10, 10, 153, 16);
		panel.add(lblFechaDesde);
		
		JLabel lblFechaHasta = new JLabel("FECHA HASTA");
		lblFechaHasta.setBounds(10, 38, 89, 16);
		panel.add(lblFechaHasta);
		
		textDesde = new JTextField();
		textDesde.setBounds(120, 5, 130, 26);
		panel.add(textDesde);
		textDesde.setColumns(10);
		
		textHasta = new JTextField();
		textHasta.setBounds(120, 33, 130, 26);
		panel.add(textHasta);
		textHasta.setColumns(10);
	}
}
