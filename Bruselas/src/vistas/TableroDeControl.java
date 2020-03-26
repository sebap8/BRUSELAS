package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import beans.FacturacionBean;
import beans.LitroBarril;
import beans.LitrosAnualesBean;
import beans.VentaCobroBean;
import controlador.Controlador;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TableroDeControl extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxMes;
	private JComboBox comboBoxAnio;
	private JPanel panelLitros;
	private JPanel panelFacturacion;
	private JPanel panelVentasVsCobros;
	private JPanel panelAnualLitros;
	private DefaultPieDataset dataLitros;
	private DefaultPieDataset dataFacturacion;
	private DefaultPieDataset dataVentaCobro;
	private JFreeChart chartLitros;
	private JFreeChart chartFacturacion;
	private JFreeChart chartVentaCobro;
	private JFreeChart chartLitrosAnuales;
	private ChartPanel chartPanelLitros;
	private ChartPanel chartPanelFacturacion;
	private ChartPanel chartPanelVentaCobro;
	private ChartPanel chartPanelLitrosAnuales ;
	private DefaultCategoryDataset dataset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableroDeControl frame = new TableroDeControl();
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
	public TableroDeControl() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1174, 725);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelLitros = new JPanel();
		panelLitros.setBounds(10, 54, 376, 278);
		contentPane.add(panelLitros);
		panelLitros.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		JLabel lblMes = new JLabel("MES");
		lblMes.setBounds(10, 10, 61, 16);
		contentPane.add(lblMes);
		
		JLabel lblAo = new JLabel("AÑO");
		lblAo.setBounds(219, 10, 61, 16);
		contentPane.add(lblAo);
		
		comboBoxMes = new JComboBox();
		comboBoxMes.setBounds(60, 6, 147, 27);
		contentPane.add(comboBoxMes);
		comboBoxMes.addItem("ENERO");
		comboBoxMes.addItem("FEBRERO");
		comboBoxMes.addItem("MARZO");
		comboBoxMes.addItem("ABRIL");
		comboBoxMes.addItem("MAYO");
		comboBoxMes.addItem("JUNIO");
		comboBoxMes.addItem("JULIO");
		comboBoxMes.addItem("AGOSTO");
		comboBoxMes.addItem("SEPTIEMBRE");
		comboBoxMes.addItem("OCTUBRE");
		comboBoxMes.addItem("NOVIEMBRE");
		comboBoxMes.addItem("DICIEMBRE");
		Date fecha =new Date();
		int mes=fecha.getMonth();
		if(mes==0){
			comboBoxMes.setSelectedItem("ENERO");
		}
		if(mes==1){
			comboBoxMes.setSelectedItem("FEBRERO");		
		}
		if(mes==2){
			comboBoxMes.setSelectedItem("MARZO");
		}
		if(mes==3){
			comboBoxMes.setSelectedItem("ABRIL");
		}
		if(mes==4){
			comboBoxMes.setSelectedItem("MAYO");
		}
		if(mes==5){
			comboBoxMes.setSelectedItem("JUNIO");
		}
		if(mes==6){
			comboBoxMes.setSelectedItem("JULIO");
		}
		if(mes==7){
			comboBoxMes.setSelectedItem("AGOSTO");
		}
		if(mes==8){
			comboBoxMes.setSelectedItem("SEPTIEMBRE");
		}
		if(mes==9){
			comboBoxMes.setSelectedItem("OCTUBRE");
		}
		if(mes==10){
			comboBoxMes.setSelectedItem("NOVIEMBRE");
		}
		if(mes==11){
			comboBoxMes.setSelectedItem("DICIEMBRE");
		}
		
		comboBoxAnio = new JComboBox();
		comboBoxAnio.setBounds(297, 6, 133, 27);
		contentPane.add(comboBoxAnio);
		int anio=fecha.getYear();
		anio+=1890;
		for(int i=0;i<20;i++){
			comboBoxAnio.addItem(anio);
			anio++;
		}
		comboBoxAnio.setSelectedItem(fecha.getYear()+1900);
		panelFacturacion = new JPanel();
		panelFacturacion.setBounds(398, 54, 376, 278);
		contentPane.add(panelFacturacion);
		panelFacturacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		panelVentasVsCobros = new JPanel();
		panelVentasVsCobros.setBounds(786, 54, 376, 278);
		contentPane.add(panelVentasVsCobros);
		panelVentasVsCobros.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		panelAnualLitros = new JPanel();
		panelAnualLitros.setBounds(10, 344, 1152, 353);
		contentPane.add(panelAnualLitros);
		panelAnualLitros.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14), null));
		
		JButton btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();
			}
		});
		btnActualizar.setBounds(461, 5, 117, 29);
		contentPane.add(btnActualizar);
		
		actualizar();
		
	
	}

	private void actualizar() {
		//PANEL LITROS
		int anio=Integer.valueOf(comboBoxAnio.getSelectedItem().toString());
		int mes=verificarFecha(comboBoxMes.getSelectedItem().toString());
		String desde="00/"+String.format("%02d", mes)+"/"+anio;
		String hasta="31/"+String.format("%02d", mes)+"/"+anio;
		Vector<LitroBarril> listado=Controlador.getInstancia().obtenerConsumoLitros(desde, hasta);
		cargarPanelLitros(listado);
		FacturacionBean facturacion=Controlador.getInstancia().obtenerFacturacion(desde, hasta);
		cargarPanelFacturacion(facturacion);
		VentaCobroBean ventaCobro=Controlador.getInstancia().obtenerVentaCobro(desde,hasta);
		cargarPanelVentaCobro(ventaCobro);
		LitrosAnualesBean litrosAnuales=Controlador.getInstancia().obtenerLitrosAnuales(anio);
		cargarPanelListrosAnuales(litrosAnuales);
		
	}

	private void cargarPanelListrosAnuales(LitrosAnualesBean litrosAnuales) {
		dataset=null;
		dataset = new DefaultCategoryDataset();
        dataset.setValue(litrosAnuales.getEnero(), "Litros vendidos", "Enero");
        dataset.setValue(litrosAnuales.getFebrero(), "Litros vendidos", "Febrero");
        dataset.setValue(litrosAnuales.getMarzo(), "Litros vendidos", "Marzo");
        dataset.setValue(litrosAnuales.getAbril(), "Litros vendidos", "Abril");
        dataset.setValue(litrosAnuales.getMayo(), "Litros vendidos", "Mayo");
        dataset.setValue(litrosAnuales.getJunio(), "Litros vendidos", "Junio");
        dataset.setValue(litrosAnuales.getJulio(), "Litros vendidos", "Julio");
        dataset.setValue(litrosAnuales.getAgosto(), "Litros vendidos", "Agosto");
        dataset.setValue(litrosAnuales.getSeptiembre(), "Litros vendidos", "Septiembre");
        dataset.setValue(litrosAnuales.getOctubre(), "Litros vendidos", "Octubre");
        dataset.setValue(litrosAnuales.getNoviembre(), "Litros vendidos", "Noviembre");
        dataset.setValue(litrosAnuales.getDiciembre(), "Litros vendidos", "Diciembre");

        // Creando el Grafico
        panelAnualLitros.removeAll();
        chartLitrosAnuales=null;
        chartLitrosAnuales = ChartFactory.createBarChart3D
        ("Ventas anuales","Meses", "Litros", 
        dataset, PlotOrientation.VERTICAL, true,true, false);
        chartLitrosAnuales.setBackgroundPaint(Color.cyan);
        chartLitrosAnuales.getTitle().setPaint(Color.black); 
        CategoryPlot p = chartLitrosAnuales.getCategoryPlot(); 
        p.setRangeGridlinePaint(Color.red); 
        panelAnualLitros.setLayout(null);
        // Mostrar Grafico
        chartPanelLitrosAnuales=null;
        chartPanelLitrosAnuales = new ChartPanel(chartLitrosAnuales);
        chartPanelLitrosAnuales.setBounds(6, 7, 1140, 340);
        panelAnualLitros.add(chartPanelLitrosAnuales);
        chartPanelLitrosAnuales.repaint();
	}

	private void cargarPanelVentaCobro(VentaCobroBean ventaCobro) {

		// Fuente de Datos

		dataVentaCobro=null;
		dataVentaCobro = new DefaultPieDataset();
		dataVentaCobro.clear();
		dataVentaCobro.setValue("PAGOS", ventaCobro.getCobro());
		dataVentaCobro.setValue("VENTAS", ventaCobro.getVenta());

        // Creando el Grafico
        chartVentaCobro=null;
        chartVentaCobro = ChartFactory.createPieChart(
         "FACTURACION", 
         dataVentaCobro, 
         true, 
         true, 
         false);
        panelVentasVsCobros.setLayout(null);
        
               // Mostrar Grafico
        panelVentasVsCobros.removeAll();
        chartPanelVentaCobro = new ChartPanel(chartVentaCobro);
        chartPanelVentaCobro.setBounds(6, 6, 364, 266);
        panelVentasVsCobros.add(chartPanelVentaCobro);
        chartPanelVentaCobro.repaint();

	}

	private void cargarPanelFacturacion(FacturacionBean facturacion) {
		// Fuente de Datos

		dataFacturacion=null;
		dataFacturacion = new DefaultPieDataset();
		dataFacturacion.clear();
    	dataFacturacion.setValue("IMPORTE", facturacion.getImporte());
    	dataFacturacion.setValue("IVA", facturacion.getIva());
    	dataFacturacion.setValue("IMPUESTOS", facturacion.getImpuestos());

        // Creando el Grafico
        chartFacturacion=null;
        chartFacturacion = ChartFactory.createPieChart(
         "FACTURACION", 
         dataFacturacion, 
         true, 
         true, 
         false);
        panelFacturacion.setLayout(null);
        
               // Mostrar Grafico
        panelFacturacion.removeAll();
        chartPanelFacturacion = new ChartPanel(chartFacturacion);
        chartPanelFacturacion.setBounds(6, 6, 364, 266);
        panelFacturacion.add(chartPanelFacturacion);
        chartPanelFacturacion.repaint();
//        PiePlot plot = (PiePlot) chart.getPlot();
//        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
//            "{0} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
//        plot.setLabelGenerator(gen);

		
	}

	private int verificarFecha(String mes) {
		int resultado=0;
		if(mes.equals("ENERO")) {
			resultado=1;
		}
		if(mes.equals("FEBRERO")) {
			resultado=2;
		}
		if(mes.equals("MARZO")) {
			resultado=3;
		}
		if(mes.equals("ABRIL")) {
			resultado=4;
		}
		if(mes.equals("MAYO")) {
			resultado=5;
		}
		if(mes.equals("JUNIO")) {
			resultado=6;
		}
		if(mes.equals("JULIO")) {
			resultado=7;
		}
		if(mes.equals("AGOSTO")) {
			resultado=8;
		}
		if(mes.equals("SEPTIEMBRE")) {
			resultado=9;
		}
		if(mes.equals("OCTUBRE")) {
			resultado=10;
		}
		if(mes.equals("NOVIEMBRE")) {
			resultado=11;
		}
		if(mes.equals("DICIEMBRE")) {
			resultado=12;
		}
		return resultado;
	}

	private void cargarPanelLitros(Vector<LitroBarril> listado) {
		// Fuente de Datos
//		if(dataLitros!=null) {
//			dataLitros.clear();
//		}
		dataLitros=null;
		dataLitros = new DefaultPieDataset();
		dataLitros.clear();
        for(int i=0;i<listado.size();i++) {
        	dataLitros.setValue(listado.get(i).getNombre(), listado.get(i).getLitros());
        }

        // Creando el Grafico
        chartLitros=null;
        chartLitros = ChartFactory.createPieChart(
         "LITROS", 
         dataLitros, 
         true, 
         true, 
         false);
        panelLitros.setLayout(null);
        
               // Mostrar Grafico
        panelLitros.removeAll();
        chartPanelLitros = new ChartPanel(chartLitros);
        chartPanelLitros.setBounds(6, 6, 364, 266);
        panelLitros.add(chartPanelLitros);
        chartPanelLitros.repaint();
//        PiePlot plot = (PiePlot) chart.getPlot();
//        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
//            "{0} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
//        plot.setLabelGenerator(gen);

		
	}
}
