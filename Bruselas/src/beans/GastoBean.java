package beans;

import java.util.Comparator;

public class GastoBean implements Comparable<GastoBean>{
	private String fecha;
	private String descripcion;
	private String tipoGasto;
	private float importe;
	private float iva;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipoGasto() {
		return tipoGasto;
	}
	public void setTipoGasto(String tipoGasto) {
		this.tipoGasto = tipoGasto;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public float getIva() {
		return iva;
	}
	public void setIva(float iva) {
		this.iva = iva;
	}
	@Override
	public int compareTo(GastoBean o) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static Comparator<GastoBean> ComparoFechaComparator= new Comparator<GastoBean>() {

		@Override
		public int compare(GastoBean o1, GastoBean o2) {			
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
	

}
