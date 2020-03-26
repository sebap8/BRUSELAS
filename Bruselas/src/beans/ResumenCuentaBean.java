package beans;

import java.util.Comparator;

public class ResumenCuentaBean implements Comparable<ResumenCuentaBean>{
	private int remito;
	private String fecha;
	private String cliente;
	private int litros;
	private float debe;
	private float haber;
	private String tipo; 
	private String estilo;
	private float impuestos;
	
	public int getRemito() {
		return remito;
	}
	public void setRemito(int remito) {
		this.remito = remito;
	}
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
	public int getLitros() {
		return litros;
	}
	public void setLitros(int litros) {
		this.litros = litros;
	}
	public float getDebe() {
		return debe;
	}
	public void setDebe(float debe) {
		this.debe = debe;
	}
	public float getHaber() {
		return haber;
	}
	public void setHaber(float haber) {
		this.haber = haber;
	}
	
	public static Comparator<ResumenCuentaBean> ComparoFechaComparator= new Comparator<ResumenCuentaBean>() {

		@Override
		public int compare(ResumenCuentaBean o1, ResumenCuentaBean o2) {			
			String[] fe=o1.getFecha().split("/");
			String fech1=fe[2]+fe[1]+fe[0];
			
			fe=o2.getFecha().split("/");
			String fech2=fe[2]+fe[1]+fe[0];
	
			//ascending order
			return fech1.compareTo(fech2);
			//descending order
			//return fruitName2.compareTo(fruitName1);
		}
	};
	@Override
	public int compareTo(ResumenCuentaBean o) {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstilo() {
		return estilo;
	}
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	public float getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(float impuestos) {
		this.impuestos = impuestos;
	}
	
	

}
