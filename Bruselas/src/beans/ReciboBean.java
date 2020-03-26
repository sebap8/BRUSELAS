package beans;

public class ReciboBean {
	private String fecha;
	private int recibo;
	private String cliente;
	private float importe;
	private EmpleadoBean empleado;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getRecibo() {
		return recibo;
	}
	public void setRecibo(int recibo) {
		this.recibo = recibo;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public EmpleadoBean getEmpleado() {
		return empleado;
	}
	public void setEmpleado(EmpleadoBean empleado) {
		this.empleado = empleado;
	}
	
	
	

}
