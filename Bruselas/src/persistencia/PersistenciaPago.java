package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import beans.ItemRemitoBean;
import beans.ReciboBean;
import utils.Estado;

public class PersistenciaPago {

	public static boolean ingresarPago(ReciboBean r) {
		boolean resultado = false;
		Connection con=PoolConnection.getPoolConnection().getConnection();
		try{
			Statement s=con.createStatement();
			int idPago=obtenerMaxIdPago();
			String[] fe=r.getFecha().split("/");
			String fech=fe[2]+fe[1]+fe[0];
			int idResponsable=obtenerIdUsuario(r.getEmpleado().getNombre());
			int idCliente=obtenerIdCliente(r.getCliente());
			s.executeUpdate("insert into pagos values("+idPago+","+r.getRecibo()+","+fech+","+idCliente+","+idResponsable+","+r.getImporte()+")");
			resultado = true;
		}catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return resultado;
	
	}

	private static int obtenerMaxIdPago() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(Id) from pagos");
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

	public static boolean existeRecibo(int recibo) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean bandera=false;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select recibo from pagos where recibo="+recibo);
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
