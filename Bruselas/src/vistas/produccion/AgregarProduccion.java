package vistas.produccion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import beans.FermentadorBean;
import controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class AgregarProduccion extends JFrame {

	private JPanel contentPane;
	private JTextField textListrosAntesHervor;
	private JTextField textLitrosFinales;
	private JTextField textDensidadAntesHervor;
	private JTextField textDensidadFinal;
	private JComboBox comboBoxRecirculado;
	private JLabel lblTiempoRecirculado;
	private JLabel lblLitrosFinales;
	private JLabel lblDensidadAh;
	private JComboBox comboBoxReceta;
	private JComboBox comboBoxTemperaturaMacerado;
	private JComboBox comboBoxTemperaturaLavado;
	private JComboBox comboBoxFermentador;
	private JComboBox comboBoxTiempoMacerado;
	private JTextField textFecha;
	private JTextField textLote;
	private JButton btnGuardar;
	private JButton btnCerrar;
	private JComboBox comboBoxTiempoHervor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarProduccion frame = new AgregarProduccion();
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
	public AgregarProduccion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 788, 566);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setBounds(10, 13, 61, 16);
		panel.add(lblFecha);
		
		JLabel lblLote = new JLabel("LOTE");
		lblLote.setBounds(10, 41, 61, 16);
		panel.add(lblLote);
		
		JLabel lblResponsable = new JLabel("RESPONSABLE");
		lblResponsable.setBounds(360, 13, 136, 16);
		panel.add(lblResponsable);
		
		JLabel lblReceta = new JLabel("RECETA");
		lblReceta.setBounds(10, 69, 61, 16);
		panel.add(lblReceta);
		
		comboBoxReceta = new JComboBox();
		comboBoxReceta.setBounds(157, 65, 189, 27);
		panel.add(comboBoxReceta);
		comboBoxReceta.addItem("NINGUNO");
		comboBoxReceta.addItem("APA");
		
		JButton btnVerReceta = new JButton("VER RECETA");
		btnVerReceta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboBoxReceta.getSelectedItem().toString().equals("NINGUNO")) {
					Receta r=new Receta(comboBoxReceta.getSelectedItem().toString());
					r.setLocationRelativeTo(null);
					r.setVisible(true);
				}else {
					
				}
				
				
			}
		});
		btnVerReceta.setBounds(350, 64, 125, 29);
		panel.add(btnVerReceta);
		
		JLabel lblTMacerado = new JLabel("Tº MACERADO");
		lblTMacerado.setBounds(10, 97, 111, 16);
		panel.add(lblTMacerado);
		
		JLabel lblTLavado = new JLabel("Tº LAVADO");
		lblTLavado.setBounds(10, 125, 84, 16);
		panel.add(lblTLavado);
		
		comboBoxTemperaturaMacerado = new JComboBox();
		comboBoxTemperaturaMacerado.setBounds(157, 93, 84, 27);
		panel.add(comboBoxTemperaturaMacerado);
		
		comboBoxTemperaturaLavado = new JComboBox();
		comboBoxTemperaturaLavado.setBounds(157, 121, 84, 27);
		panel.add(comboBoxTemperaturaLavado);
		
		for(int i=0;i<100;i++) {
			comboBoxTemperaturaMacerado.addItem(i);
			comboBoxTemperaturaLavado.addItem(i);
			comboBoxTiempoMacerado.addItem(i);
			comboBoxTiempoHervor.addItem(i);
			comboBoxRecirculado.addItem(i);
		}
		
		JLabel lblTiempoDeMacerado = new JLabel("TIEMPO DE MACERADO");
		lblTiempoDeMacerado.setBounds(360, 97, 180, 16);
		panel.add(lblTiempoDeMacerado);
		
		JLabel lblTiempoDeHervor = new JLabel("TIEMPO DE HERVOR");
		lblTiempoDeHervor.setBounds(360, 125, 144, 16);
		panel.add(lblTiempoDeHervor);
		
		comboBoxTiempoMacerado = new JComboBox();
		comboBoxTiempoMacerado.setBounds(527, 93, 84, 27);
		panel.add(comboBoxTiempoMacerado);
		
		comboBoxTiempoHervor = new JComboBox();
		comboBoxTiempoHervor.setBounds(527, 121, 84, 27);
		panel.add(comboBoxTiempoHervor);
		
		JLabel lblDescripcinDelProceso = new JLabel("OBSERVACIONES");
		lblDescripcinDelProceso.setBounds(6, 254, 213, 16);
		panel.add(lblDescripcinDelProceso);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 6, 747, 155);
		JScrollPane scroll = new JScrollPane ( textArea );
		scroll.setBounds(6, 282, 766, 225);
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    panel.add(scroll);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    
	    JLabel lblFermentador = new JLabel("FERMENTADOR");
	    lblFermentador.setBounds(10, 153, 111, 16);
	    panel.add(lblFermentador);
	    
	    comboBoxFermentador = new JComboBox();
	    comboBoxFermentador.setBounds(157, 149, 84, 27);
	    panel.add(comboBoxFermentador);
	    Vector<FermentadorBean> fermentadores=Controlador.getInstancia().obtenerFermentadores();
	    for(int i=0;i<fermentadores.size();i++) {
	    	comboBoxFermentador.addItem(fermentadores.get(i).getNombre());
	    }
	    
	    btnGuardar = new JButton("GUARDAR");
	    btnGuardar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(!textFecha.getText().isEmpty()) {
	    			if(!textLote.getText().isEmpty()) {
	    				if(!comboBoxTemperaturaMacerado.getSelectedItem().equals("0")) {
	    					
	    				}else {
	    					//
	    				}
	    				
	    			}else {
	    				//LOTE VACIO
	    			}
	    		}else {
	    			//FECHA VACIA
	    		}
	    	}
	    });
	    btnGuardar.setBounds(200, 519, 137, 41);
	    panel.add(btnGuardar);
	    
	    btnCerrar = new JButton("CERRAR");
	    btnCerrar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	    btnCerrar.setBounds(388, 519, 137, 41);
	    panel.add(btnCerrar);
	    
	    JLabel lblLitrosDeMosto = new JLabel("LITROS MOSTO A/H");
	    lblLitrosDeMosto.setBounds(10, 181, 172, 16);
	    panel.add(lblLitrosDeMosto);
	    
	    textListrosAntesHervor = new JTextField();
	    textListrosAntesHervor.setBounds(157, 176, 100, 26);
	    panel.add(textListrosAntesHervor);
	    textListrosAntesHervor.setColumns(10);
	    
	    lblLitrosFinales = new JLabel("LITROS MOSTO FINAL");
	    lblLitrosFinales.setBounds(360, 181, 144, 16);
	    panel.add(lblLitrosFinales);
	    
	    textLitrosFinales = new JTextField();
	    textLitrosFinales.setBounds(527, 176, 130, 26);
	    panel.add(textLitrosFinales);
	    textLitrosFinales.setColumns(10);
	    
	    lblDensidadAh = new JLabel("DENSIDAD A/H");
	    lblDensidadAh.setBounds(10, 209, 136, 16);
	    panel.add(lblDensidadAh);
	    
	    textDensidadAntesHervor = new JTextField();
	    textDensidadAntesHervor.setBounds(157, 204, 130, 26);
	    panel.add(textDensidadAntesHervor);
	    textDensidadAntesHervor.setColumns(10);
	    
	    JLabel lblDensidadFinal = new JLabel("DENSIDAD FINAL");
	    lblDensidadFinal.setBounds(360, 209, 126, 16);
	    panel.add(lblDensidadFinal);
	    
	    textDensidadFinal = new JTextField();
	    textDensidadFinal.setBounds(527, 204, 130, 26);
	    panel.add(textDensidadFinal);
	    textDensidadFinal.setColumns(10);
	    
	    lblTiempoRecirculado = new JLabel("TIEMPO RECIRCULADO");
	    lblTiempoRecirculado.setBounds(360, 153, 144, 16);
	    panel.add(lblTiempoRecirculado);
	    
	    comboBoxRecirculado = new JComboBox();
	    comboBoxRecirculado.setBounds(527, 149, 84, 27);
	    panel.add(comboBoxRecirculado);
	    
	    textFecha = new JTextField();
	    textFecha.setBounds(157, 8, 100, 26);
	    panel.add(textFecha);
	    textFecha.setColumns(10);
	    textFecha.setEditable(false);
	    
	    textLote = new JTextField();
	    textLote.setBounds(157, 36, 75, 26);
	    panel.add(textLote);
	    textLote.setColumns(10);
	    
	    JButton btnAsignar = new JButton("ASIGNAR");
	    btnAsignar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String fe=new DatePicker().setPickedDate();
				textFecha.setText(fe);
	    	}
	    });
	    btnAsignar.setBounds(258, 8, 91, 29);
	    panel.add(btnAsignar);
	    
	    JComboBox comboBoxResponsable = new JComboBox();
	    comboBoxResponsable.setBounds(527, 9, 213, 27);
	    panel.add(comboBoxResponsable);
	}
}
