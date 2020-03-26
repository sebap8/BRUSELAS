package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.ClienteBean;
import beans.ProductoBean;
import utils.Estado;

public class PersistenciaCliente {

	public static Vector<ClienteBean> obtenerClientes() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<ClienteBean> clientes=new Vector<ClienteBean>();		
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from clientes where estado='ACTIVO' ");
			while (r.next()){
				ClienteBean c=new ClienteBean();
				c.setContacto(r.getString(6));
				c.setCorreo(r.getString(5));
				c.setCuit(r.getString(3));
				c.setNombre(r.getString(2));
				c.setTel(r.getString(4));
				c.setEstado(r.getString(7));
				clientes.add(c);
			}

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return clientes;
	
	}

	public static boolean ingresarCliente(String nombre) {
		Connection con=PoolConnection.getPoolConnection().getConnection();	
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			int idCliente=obtenerIdMaxCliente();
			int idMaxBarrilCliente=obtenerIdMaxBarrilCliente();
			s.executeUpdate("insert into clientes values("+idCliente+",'"+nombre+"','0','0','0','0','"+Estado.ACTIVO+"')");
			s.execute("insert into barrilcliente values("+idMaxBarrilCliente+","+idCliente+",'0')");
			flag=true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	}
	private static int obtenerIdMaxBarrilCliente() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(id) from barrilcliente");
			while(r.next()){
				st=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return (st+1);
	
		
	}

	private static int obtenerIdMaxCliente() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(id) from clientes");
			while(r.next()){
				st=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return (st+1);
	
		
	}

}
