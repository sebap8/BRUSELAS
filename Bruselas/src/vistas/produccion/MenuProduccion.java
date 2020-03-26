package vistas.produccion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

public class MenuProduccion extends JFrame {

	private JPanel contentPane;
	Vector<String> permisos=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuProduccion frame = new MenuProduccion();
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
	public MenuProduccion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);
		
//		permisos=Controlador.getInstancia().obtenerPermisos();
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnInicio.add(mntmSalir);
		
		JMenu mnClientes = new JMenu("Recetas");
		menuBar.add(mnClientes);
		
		JMenuItem mntmAgregarCliente = new JMenuItem("Agregar receta");
		mntmAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarReceta r=new AgregarReceta();
				r.setLocationRelativeTo(null);
				r.setVisible(true);
			}
		});
		mnClientes.add(mntmAgregarCliente);
		
		JMenuItem mntmVerRecetas = new JMenuItem("Ver recetas");
		mnClientes.add(mntmVerRecetas);
		
		JMenu mnRemitos = new JMenu("Producción");
		menuBar.add(mnRemitos);
		
		JMenuItem mntIngresarRemito = new JMenuItem("Ingresar producción");
		mntIngresarRemito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnRemitos.add(mntIngresarRemito);
		
		JMenuItem mntModificarRemito = new JMenuItem("Ver producciones");
		mntModificarRemito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnRemitos.add(mntModificarRemito);
		
		
		JMenu mnBloquera = new JMenu("Mercadería");
		menuBar.add(mnBloquera);
	
		JMenuItem mntmListadoPorFecha = new JMenuItem("Ingresar mercadería");
		mntmListadoPorFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnBloquera.add(mntmListadoPorFecha);
		
		
		JMenu mCobranza = new JMenu("Fermentación");
		menuBar.add(mCobranza);
		
		JMenuItem mntmIngresarPago = new JMenuItem("Ingresar control");
		mntmIngresarPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mCobranza.add(mntmIngresarPago);
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
