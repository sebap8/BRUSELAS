package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.GastoBean;

public class PersistenciaGasto {

	public static Vector<String> obtenerTiposGasto() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<String> tiposGastos=new Vector<>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select descripcion from tipoGasto");
			while (r.next()){
				tiposGastos.add(r.getString(1));
			}
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return tiposGastos;
	
		
	}

	public static boolean ingresarGasto(GastoBean g) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		boolean flag=false;
		try
		{
			Statement s=con.createStatement();
			int id=obtenerIdGasto();
			int idTipoGasto=obtenerIdTipoGasto(g.getTipoGasto());
			s.executeUpdate("insert into gasto values("+id+",'"+g.getFecha()+"','"+g.getDescripcion()+"',"+idTipoGasto+","+g.getImporte()+","+g.getIva()+")");
			flag=true;
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return flag;
	
		
	}

	private static int obtenerIdTipoGasto(String tipoGasto) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int idTipoGasto=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select id from tipoGasto where descripcion='"+tipoGasto+"'");
			while (r.next()){
				idTipoGasto=r.getInt(1);
			}
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return idTipoGasto;
	}

	private static int obtenerIdGasto() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int id=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(id) from gasto");
			while (r.next()){
				id=r.getInt(1);
			}
			id=id+1;
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return id;
	}

	public static Vector<GastoBean> obtenerListadoGastos(int desde, int hasta) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<GastoBean> gastos=new Vector<GastoBean>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from gasto where fecha>="+desde+" and fecha<="+hasta);
			while (r.next()){
				GastoBean g=new GastoBean();
				g.setFecha(formatearFecha(r.getString(2)));
				g.setDescripcion(r.getString(3));
				g.setTipoGasto(obtenerTipoGasto(r.getInt(4)));
				g.setImporte(r.getFloat(5));
				g.setIva(r.getFloat(6));
				gastos.add(g);
			}
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return gastos;
	}

	private static String obtenerTipoGasto(int idGasto) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String resultado="";
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select descripcion from tipoGasto where id="+idGasto);
			while (r.next()){
				resultado=r.getString(1);
			}
		}
		catch(Exception se){
			System.out.println(se.getMessage());
			System.out.println(se.getStackTrace());
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return resultado;
	}
	private static String formatearFecha(String fecha) {
		char[] result = {'a','a','/','a','a','/','a','a','a','a'};
		fecha.getChars(6, 7, result, 0);
		fecha.getChars(7, 8, result, 1);
		fecha.getChars(5, 6, result, 4);
		fecha.getChars(4, 5, result, 3);
		fecha.getChars(0, 1, result, 6);
		fecha.getChars(1, 2, result, 7);
		fecha.getChars(2, 3, result, 8);
		fecha.getChars(3, 4, result, 9);
		return String.valueOf(result);
		
	}

}
