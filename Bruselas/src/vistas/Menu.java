package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;
	Vector<String> permisos=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);
		
		permisos=Controlador.getInstancia().obtenerPermisos();
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnInicio.add(mntmSalir);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmAgregarCliente = new JMenuItem("Agregar cliente");
		mntmAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarCliente ventana=new AgregarCliente();
				ventana.setLocationRelativeTo(null);
				ventana.setVisible(true);
			}
		});
		mnClientes.add(mntmAgregarCliente);
		
		JMenu mnRemitos = new JMenu("Remitos");
		menuBar.add(mnRemitos);
		
		JMenuItem mntIngresarRemito = new JMenuItem("Ingresar remito");
		mntIngresarRemito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresarRemito ventana=new IngresarRemito();
				ventana.setLocationRelativeTo(null);
				ventana.setVisible(true);
			}
		});
		mnRemitos.add(mntIngresarRemito);
		
		JMenuItem mntModificarRemito = new JMenuItem("Modificar remito");
		mntModificarRemito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnRemitos.add(mntModificarRemito);
		mntModificarRemito.setEnabled(false);
		
		JMenuItem mntmEliminarRemito = new JMenuItem("Eliminar remito");
		mntmEliminarRemito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnRemitos.add(mntmEliminarRemito);
		mntmEliminarRemito.setEnabled(false);
		
		JMenu mnBloquera = new JMenu("Facturas");
		menuBar.add(mnBloquera);
	
		JMenuItem mntmListadoPorFecha = new JMenuItem("Ingresar factura");
		mntmListadoPorFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IngresarFactura ventana = new IngresarFactura();
				ventana.setLocationRelativeTo(null);
				ventana.setVisible(true);
			}
		});
		mnBloquera.add(mntmListadoPorFecha);
		
		JMenuItem mntmListadoPorOperario = new JMenuItem("Modificar factura");
		mntmListadoPorOperario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnBloquera.add(mntmListadoPorOperario);
		mntmListadoPorOperario.setEnabled(false);
		
		JMenuItem mntmEliminarFactura = new JMenuItem("Eliminar factura");
		mnBloquera.add(mntmEliminarFactura);
		mntmEliminarFactura.setEnabled(false);
		
		
		JMenu mCobranza = new JMenu("Cobranza");
		menuBar.add(mCobranza);
		
		JMenuItem mntmIngresarPago = new JMenuItem("Ingresar Pago");
		mntmIngresarPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresarPago v= new IngresarPago();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mCobranza.add(mntmIngresarPago);
		
		JMenu mGastos = new JMenu("Gastos");
		menuBar.add(mGastos);
		
		JMenuItem mntmIngresarGasto = new JMenuItem("Ingresar gasto");
		mntmIngresarGasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresarGasto gasto=new IngresarGasto();
				gasto.setLocationRelativeTo(null);
				gasto.setVisible(true);
			}
		});
		mGastos.add(mntmIngresarGasto);
		
		JMenuItem mntmListadoGastos = new JMenuItem("Listado gastos");
		mntmListadoGastos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoGastos listado=new ListadoGastos();
				listado.setLocationRelativeTo(null);
				listado.setVisible(true);
			}
		});
		mGastos.add(mntmListadoGastos);
		
		JMenu mListados = new JMenu("Listados");
		menuBar.add(mListados);
		
		JMenuItem mntmResumenCuentaCliente= new JMenuItem("Resumen de cuenta cliente");
		mntmResumenCuentaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoRemusenCuentaCliente v= new ListadoRemusenCuentaCliente();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mListados.add(mntmResumenCuentaCliente);
		
		JMenuItem mntmFacturasEmitidas = new JMenuItem("Facturas emitidas");
		mListados.add(mntmFacturasEmitidas);
		mntmFacturasEmitidas.setEnabled(false);
		
		JMenuItem mntmPendienteCobro = new JMenuItem("Pendiente de cobro");
		mntmPendienteCobro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoPendienteDeCobro p=new ListadoPendienteDeCobro();
				p.setLocationRelativeTo(null);
				p.setVisible(true);
			}
		});
		mListados.add(mntmPendienteCobro);
		
		JMenu mEstadisticas = new JMenu("Estadisticas");
		menuBar.add(mEstadisticas);
		
		JMenuItem mntmCantidadDeLitros = new JMenuItem("Cantidad de litros");
		mntmCantidadDeLitros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoLitros l=new ListadoLitros();
				l.setLocationRelativeTo(null);
				l.setVisible(true);
			}
		});
		mEstadisticas.add(mntmCantidadDeLitros);
		
		JMenuItem mntmMejorCliente = new JMenuItem("Mejor cliente");
		mEstadisticas.add(mntmMejorCliente);
		mntmMejorCliente.setEnabled(false);
		
		JMenuItem mntmTableroDeControl = new JMenuItem("Tablero de control");
		mntmTableroDeControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableroDeControl t=new TableroDeControl();
				t.setLocationRelativeTo(null);
				t.setVisible(true);
			}
		});
		mEstadisticas.add(mntmTableroDeControl);
		
		JMenu mUsuarios = new JMenu("Usuarios");
		menuBar.add(mUsuarios);
		
		JMenuItem mntmAgregarUsuario = new JMenuItem("Agregar usuario");
		mUsuarios.add(mntmAgregarUsuario);
		
		JMenuItem mntmModificarUsuario = new JMenuItem("Modificar usuario");
		mUsuarios.add(mntmModificarUsuario);
		
		JMenuItem mntmEliminarUsuario = new JMenuItem("Eliminar usuario");
		mUsuarios.add(mntmEliminarUsuario);
		
		JMenuItem mntmParametrizar = new JMenuItem("Parametrizar");
		mntmParametrizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParametrizarUsuario v=new ParametrizarUsuario();
				v.setLocationRelativeTo(null);
				v.setVisible(true);
			}
		});
		mUsuarios.add(mntmParametrizar);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
