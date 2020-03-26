package beans;

import java.util.Vector;

public class FacturaBean {
	private int numero;
	private String fecha;
	private String fechaVencimiento;
	private String cliente;
	private EmpleadoBean empleado;
	private Vector<ItemFacturaBean> items=new Vector<ItemFacturaBean>();
	private float IVA;
	private float impuestos;
	private String observaciones;
	private String estado;
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
	public Vector<ItemFacturaBean> getItems() {
		return items;
	}
	public void setItems(Vector<ItemFacturaBean> items) {
		this.items = items;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public float getIVA() {
		return IVA;
	}
	public void setIVA(float iVA) {
		IVA = iVA;
	}
	public float getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(float impuestos) {
		this.impuestos = impuestos;
	}

	
}
