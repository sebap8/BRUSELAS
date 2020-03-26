package beans;

import java.util.Vector;

public class RemitoBean {
	private String numero;
	private String fecha;
	private String cliente;
	private EmpleadoBean empleado;
	private Vector<ItemRemitoBean> items=new Vector<ItemRemitoBean>();
	private String observaciones;
	private String estado;
	private String[] barrilesEntregados;
	private String[] barrilesRecibidos;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public EmpleadoBean getEmpleado() {
		return empleado;
	}
	public void setEmpleado(EmpleadoBean empleado) {
		this.empleado = empleado;
	}
	public Vector<ItemRemitoBean> getItems() {
		return items;
	}
	public void setItems(Vector<ItemRemitoBean> items) {
		this.items = items;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String[] getBarrilesEntregados() {
		return barrilesEntregados;
	}
	public void setBarrilesEntregados(String[] barrilesEntregados) {
		this.barrilesEntregados = barrilesEntregados;
	}
	public String[] getBarrilesRecibidos() {
		return barrilesRecibidos;
	}
	public void setBarrilesRecibidos(String[] barrilesRecibidos) {
		this.barrilesRecibidos = barrilesRecibidos;
	}
	
	
}
