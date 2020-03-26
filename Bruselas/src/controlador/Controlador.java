package controlador;

import java.util.Collections;
import java.util.Vector;

import beans.ClarificanteBean;
import beans.ClienteBean;
import beans.ClienteSaldoBean;
import beans.EmpleadoBean;
import beans.VentaCobroBean;
import beans.FacturaBean;
import beans.FacturacionBean;
import beans.FermentadorBean;
import beans.GastoBean;
import beans.GranoBean;
import beans.LitroBarril;
import beans.LitrosAnualesBean;
import beans.LupuloBean;
import beans.ProductoBean;
import beans.ReciboBean;
import beans.RemitoBean;
import beans.ResumenCuentaBean;
import beans.SalBean;
import persistencia.PersistenciaCliente;
import persistencia.PersistenciaUsuario;
import persistencia.PersistenciaFactura;
import persistencia.PersistenciaFermentador;
import persistencia.PersistenciaGasto;
import persistencia.PersistenciaPago;
import persistencia.PersistenciaProducto;
import persistencia.PersistenciaReceta;
import persistencia.PersistenciaRemito;
import persistencia.PersistenciaUsuario;
import vistas.PersistenciaListados;

public class Controlador {
	private static Controlador instancia;
	private EmpleadoBean usuarioActual;
	
	public static Controlador getInstancia(){
		if(instancia==null){
			instancia=new Controlador();
		}
		return instancia;
	}

	public Vector<EmpleadoBean> obtenerUsuarios() {
		return PersistenciaUsuario.obtenerUsuarios();
	}

	public boolean verificarContrasenia(String nombre, String contrasenia) {
		EmpleadoBean e = PersistenciaUsuario.obtenerUsuario(nombre,contrasenia);
		if(e!=null) {
			usuarioActual=e;
			return true;
		}else {
			return false;
		}
	}

	public Vector<String> obtenerProductos() {
		Vector<String> result =obtenerNombresProductos(PersistenciaProducto.obtenerProductos());
		Collections.sort(result);
		return result;
	}
	public Vector<String> obtenerNombresProductos(Vector<ProductoBean> productos){
		Vector<String> result = new Vector<String>();
		for(int i=0;i<productos.size();i++) {
			result.add(productos.get(i).getNombre());
		}
		return result;
	}

	public Vector<String> obtenerClientes() {
		Vector<String> result =obtenerNombresClientes(PersistenciaCliente.obtenerClientes());
		Collections.sort(result);
		return result;
	}
	public Vector<String> obtenerNombresClientes(Vector<ClienteBean> clientes){
		Vector<String> result = new Vector<String>();
		for(int i=0;i<clientes.size();i++) {
			result.add(clientes.get(i).getNombre());
		}
		return result;
	}

	public boolean ingresarRemito(RemitoBean rem) {
		rem.setEmpleado(usuarioActual);
		return PersistenciaRemito.ingresarRemito(rem);
	}

	public boolean ingresarFactura(FacturaBean factura) {
		factura.setEmpleado(usuarioActual);
		return PersistenciaFactura.ingresarFactura(factura);
	}

	public boolean existeRemito(String remito) {
		int numeroRemito=Integer.valueOf(remito);
		return PersistenciaRemito.existeRemito(numeroRemito);
	}

	public boolean ingresarCliente(String nombre) {
		return PersistenciaCliente.ingresarCliente(nombre);
	}

	public boolean existeRecibo(int recibo) {
		return PersistenciaPago.existeRecibo(recibo);
	}

	public boolean ingresarPago(ReciboBean r) {
		r.setEmpleado(usuarioActual);
		return PersistenciaPago.ingresarPago(r);
	}

	public Vector<ResumenCuentaBean> obtenerListadoResumenCuentaCliente(String cliente) {
		Vector<ResumenCuentaBean> resumenCuenta=PersistenciaListados.obtenerResumenCuenta(cliente);
		Collections.sort(resumenCuenta, ResumenCuentaBean.ComparoFechaComparator);
		return resumenCuenta;
	}

	public Vector<LitroBarril> obtenerConsumoLitros(String desde, String hasta) {
		return PersistenciaListados.obtenerConsumoLitros(desde,hasta);
				
	}

	public Vector<ClienteSaldoBean> obtenerSaldos() {
		return PersistenciaListados.obtenerSaldos();
	}

	public Vector<String> obtenerTiposGastos() {
		return PersistenciaGasto.obtenerTiposGasto();
	}

	public FacturacionBean obtenerFacturacion(String desde, String hasta) {
		return PersistenciaFactura.obtenerFacturacion(desde,hasta);
	}

	public VentaCobroBean obtenerVentaCobro(String desde, String hasta) {
		return PersistenciaListados.obtenerVentaCorbo(desde,hasta);
	}

	public LitrosAnualesBean obtenerLitrosAnuales(int anio) {
		return PersistenciaListados.obtenerLitrosAnuales(anio);
	}
	public Vector<String> obtenerPermisosOtrogados(String operario) {
		return PersistenciaUsuario.obtenerPermisosOtrogados(operario);
	}

	public Vector<String> obtenerPermisosDisponibles(String operario) {
		return PersistenciaUsuario.obtenerPermisosDisponibles(operario);
	}

	public boolean cambiarPermisos(Vector<String> permisos, String nombre) {
		return PersistenciaUsuario.cambiarPermisos(permisos,nombre);
	}

	public Vector<String> obtenerPermisos() {
		return PersistenciaUsuario.obtenerPermisos(usuarioActual.getNombre());
	}

	public Vector<String> obtenerGranos() {
		return PersistenciaProducto.obtenerGranos();
	}

	public Vector<String> obtenerLupulos() {
		return PersistenciaProducto.obtenerLupulos();
	}

	public Vector<LitroBarril> obtenerConsumoLitrosSinBonificacion(String desde, String hasta) {
		return PersistenciaListados.obtenerConsumoLitrosSinBonificacion(desde,hasta);
	}

	public boolean ingresarGasto(GastoBean g) {
		String[] fe=g.getFecha().split("/");
		g.setFecha(fe[2]+fe[1]+fe[0]);
		return PersistenciaGasto.ingresarGasto(g);
	}

	public Vector<GranoBean> obtenerGranos(String estilo) {
		return PersistenciaReceta.obtenerGranos(estilo);
	}

	public Vector<LupuloBean> obtenerLupulos(String estilo) {
		return PersistenciaReceta.obtenerLupulos(estilo);
	}

	public Vector<SalBean> obtenerSales(String estilo) {
		return PersistenciaReceta.obtenerSales(estilo);
	}

	public Vector<ClarificanteBean> obtenerClarificantes(String estilo) {
		return PersistenciaReceta.obtenerClarificantes(estilo);
	}

	public Vector<FermentadorBean> obtenerFermentadores() {
		return PersistenciaFermentador.obtenerFermentadores();
	}

	public Vector<GastoBean> obtenerListadoGastos(String des, String has) {
		String[] fe=des.split("/");
		String desde=fe[2]+fe[1]+fe[0];
		fe=has.split("/");
		String hasta=fe[2]+fe[1]+fe[0];
		Vector<GastoBean> resultado =PersistenciaGasto.obtenerListadoGastos(Integer.valueOf(desde),Integer.valueOf(hasta));
		Collections.sort(resultado, GastoBean.ComparoFechaComparator);
		return resultado;
	}

}
	
