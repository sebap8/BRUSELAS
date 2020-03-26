package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import beans.EmpleadoBean;
import beans.ItemRemitoBean;
import beans.ProductoBean;
import beans.RemitoBean;
import utils.Estado;

public class PersistenciaRemito {

	public static boolean ingresarRemito(RemitoBean rem) {
		boolean resultado = false;
		Connection con=PoolConnection.getPoolConnection().getConnection();
		
		try{
			Statement s=con.createStatement();
			int idRemito=obtenerMaxIdRemito();
			String[] fe=rem.getFecha().split("/");
			String fech=fe[2]+fe[1]+fe[0];
			int idResponsable=obtenerIdUsuario(rem.getEmpleado().getNombre());
			int idCliente=obtenerIdCliente(rem.getCliente());
			s.executeUpdate("insert into remitos values("+idRemito+","+rem.getNumero()+","+fech+","+idResponsable+","+idCliente+",'"+Estado.PENDIENTE+"')");
			Vector<ItemRemitoBean> items=rem.getItems();
			ItemRemitoBean it=new ItemRemitoBean();
			int idItem=obtenerMaxIdItemRemito();
			int idProducto=0;
			for(int i = 0; i < items.size(); i++){
				it=items.get(i);
				idProducto=obtenerIdProducto(it.getProducto());
				s.executeUpdate("insert into itemRemito values ("+idItem+","+idRemito+","+idProducto+","+it.getCantidad()+","+it.getPrecio()+",'"+Estado.PENDIENTE+"')");
				idItem++;
			}
//			s.execute("update barrilcliente set barriles=barriles+"+rem.getCantidadBarriles()+" where idCliente="+idCliente);
			resultado = true;
		}catch(Exception e){
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return resultado;
	
	}

	private static int obtenerIdProducto(String producto) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select id from productos where nombre like '"+producto+"'");
			while(r.next()){
				st=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return (st);
	
		
	}

	private static int obtenerMaxIdItemRemito() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(Id) from itemRemito");
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

	private static int obtenerIdCliente(String cliente) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select id from clientes where nombre like '"+cliente+"'");
			while(r.next()){
				st=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return (st);
	
		
	}

	private static int obtenerIdUsuario(String nombre) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select id from usuarios where nombre like '"+nombre+"'");
			while(r.next()){
				st=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return (st);
	
		
	}

	private static int obtenerMaxIdRemito() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(Id) from remitos");
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

	public static boolean existeRemito(int remito) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean bandera=false;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select numeroRemito from remitos where numeroRemito="+remito);
			while(r.next()){
				bandera=true;
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return bandera;
	}

}
