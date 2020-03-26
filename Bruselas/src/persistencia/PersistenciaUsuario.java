package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import beans.EmpleadoBean;

public class PersistenciaUsuario {

	public static Vector<EmpleadoBean> obtenerUsuarios() {

		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<EmpleadoBean> empleados=new Vector<EmpleadoBean>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from usuarios where estado='Activo'");
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

	public static Vector<String> obtenerPermisosOtrogados(String operario) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> empleados=new Vector<String>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select p.nombre from usuarios e, perfil p where e.id=p.Idusuario and e.nombre='"+operario+"'");
			while (r.next()){
				empleados.add(r.getString(1));
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return empleados;
	}

	public static Vector<String> obtenerPermisosDisponibles(String operario) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> empleados=new Vector<String>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select p.nombre from usuarios e, perfil p where e.id=p.idUsuario "
					+ "and e.nombre!='"+operario+"' and p.nombre not in"
					+ "(select p1.nombre from usuarios e1, perfil p1 where e1.id=p1.idusuario and e1.nombre='"+operario+"' )"
					+ "group by p.nombre;");
			while (r.next()){
				empleados.add(r.getString(1));
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return empleados;
	}

	public static boolean cambiarPermisos(Vector<String> permisos, String nombre) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			String idOperario=PersistenciaEmpleado.obtenerIdUsuario(nombre);
			s.executeUpdate("delete from perfil where idUsuario ="+idOperario);
			
			for(int i=0;i<permisos.size();i++){
				int idPerfil=obtenermaxIdPerfil();
				idPerfil++;
				s.executeUpdate("insert into perfil values("+idPerfil+",'"+permisos.get(i)+"',"+idOperario+")");
			}
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	}
	private static int obtenermaxIdPerfil() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;	
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(id) from perfil");
			while (r.next()){
				id=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return id;
	
	}

	public static Vector<String> obtenerPermisos(String nombre) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> empleados=new Vector<String>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select p.nombre from perfil p, usuarios u where u.id=p.idusuario and u.nombre='"+nombre+"'");
			while (r.next()){
				empleados.add(r.getString(1));
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return empleados;
	}

}
