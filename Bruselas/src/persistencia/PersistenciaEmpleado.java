package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import beans.EmpleadoBean;
import utils.Estado;

public class PersistenciaEmpleado {

	public static Vector<EmpleadoBean> obtenerUsuarios() {

		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<EmpleadoBean> empleados=new Vector<EmpleadoBean>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from usuarios where estado='"+Estado.ACTIVO+"'");
			while (r.next()){
				String nombre=r.getString(2);
				String contrasenia=r.getString(3);
				EmpleadoBean e=new EmpleadoBean();
				e.setNombre(nombre);
				e.setContrasenia(contrasenia);
				e.setEstado(r.getString(4));
				empleados.add(e);
			}

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return empleados;
	
	}

	public static EmpleadoBean obtenerUsuario(String nom, String contra) {

		Connection con=PoolConnection.getPoolConnection().getConnection();
		EmpleadoBean empleado= null;	
		try
		{
			Statement s=con.createStatement();
			int pass=contra.hashCode();
			ResultSet r=s.executeQuery("select * from usuarios where nombre ='"+nom+"' and contrasenia ='"+pass+"'");
			while (r.next()){
				String nombre=r.getString(2);
				String contrasenia=r.getString(3);
				empleado=new EmpleadoBean();
				empleado.setNombre(nombre);
				empleado.setContrasenia(contrasenia);
				empleado.setEstado(r.getString(4));
			}

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return empleado;
	
	}

	public static String obtenerIdUsuario(String nombre) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String op=null;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select Id from usuarios where nombre like '"+nombre+"'");
			while(r.next()){
				op=r.getString(1);
			}
		}
		catch(Exception e){
			System.out.println();
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return op;
		
	}

}
