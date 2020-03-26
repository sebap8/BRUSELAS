package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.EmpleadoBean;
import beans.ProductoBean;

public class PersistenciaProducto {

	public static Vector<ProductoBean> obtenerProductos() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<ProductoBean> empleados=new Vector<ProductoBean>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from productos where tipo='VENTA'");
			while (r.next()){
				String nombre=r.getString(2);
				int stock=r.getInt(3);
				ProductoBean p=new ProductoBean();
				p.setNombre(nombre);
				p.setStock(stock);
				empleados.add(p);
			}

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return empleados;
	
	}

	public static Vector<String> obtenerGranos() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> empleados=new Vector<String>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from productos where tipo='GRANO'");
			while (r.next()){
				empleados.add(r.getString(2));
			}

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return empleados;
	
	}

	public static Vector<String> obtenerLupulos() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> empleados=new Vector<String>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from productos where tipo='LUPULO'");
			while (r.next()){
				empleados.add(r.getString(2));
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
