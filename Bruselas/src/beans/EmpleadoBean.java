package beans;

import java.util.Comparator;

public class EmpleadoBean implements Comparable<EmpleadoBean>{

	private String nombre;
	private String contrasenia;
	private String estado;
	private String tipo;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
	
	@Override
	public int compareTo(EmpleadoBean o) {
		// TODO Auto-generated method stub
		return 0;
	}	
	public static Comparator<EmpleadoBean> ComparoFechaComparator= new Comparator<EmpleadoBean>() {

		@Override
		public int compare(EmpleadoBean o1, EmpleadoBean o2) {			
			//ascending order
			return o1.getNombre().compareTo(o2.getNombre());
			//descending order
			//return fruitName2.compareTo(fruitName1);
		}
	};
	

}
