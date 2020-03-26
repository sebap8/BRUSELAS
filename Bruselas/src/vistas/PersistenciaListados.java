package vistas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import beans.ClienteSaldoBean;
import beans.FacturacionBean;
import beans.LitroBarril;
import beans.LitrosAnualesBean;
import beans.ResumenCuentaBean;
import beans.VentaCobroBean;
import persistencia.PersistenciaUsuario;
import persistencia.PersistenciaProducto;
import persistencia.PoolConnection;
import utils.Estado;

public class PersistenciaListados {

	public static Vector<ResumenCuentaBean> obtenerResumenCuenta(String cliente) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<ResumenCuentaBean> resumenCuenta=null;
		try
		{
			Statement s=con.createStatement();
			int idCliente=obtenerIdCliente(cliente);
			ResultSet r=s.executeQuery("select * from pagos where idCliente="+idCliente); 
			resumenCuenta= new Vector<ResumenCuentaBean>();
			while (r.next()){
					ResumenCuentaBean recibo=new ResumenCuentaBean();
					recibo.setCliente(cliente);
					recibo.setDebe(0);
					recibo.setFecha(formatearFecha(r.getString(3)));
					recibo.setHaber(r.getFloat(6));
					recibo.setLitros(0);
					recibo.setRemito(r.getInt(2));
					recibo.setTipo(Estado.RECIBO);
					resumenCuenta.add(recibo);
			}
			r=s.executeQuery("select r.fecha,it.id ,r.numeroRemito ,p.nombre,it.cantidad as litros,sum(it.cantidad*it.precio) as importe from remitos r, itemremito it, productos p where r.idCliente="+idCliente+" and r.id=it.idremito and p.id=it.idProducto group by it.id");
			while (r.next()){
				ResumenCuentaBean remito=new ResumenCuentaBean();
				remito.setCliente(cliente);
				remito.setDebe(r.getFloat(6));
				remito.setFecha(formatearFecha(r.getString(1)));
				remito.setHaber(0);
				remito.setLitros(r.getInt(5));
				remito.setRemito(r.getInt(3));
				remito.setTipo(Estado.REMITO);
				remito.setEstilo(r.getString(4));
				resumenCuenta.add(remito);
			}
			r=s.executeQuery("select numero,fecha,(iva+impuestos) from factura where idCliente="+idCliente);
			while(r.next()) {
				ResumenCuentaBean factura=new ResumenCuentaBean();
				factura.setCliente(cliente);
				factura.setImpuestos(r.getFloat(3));
				factura.setFecha(formatearFecha(r.getString(2)));
				factura.setRemito(r.getInt(1));
				factura.setTipo(Estado.FACTURA);
				resumenCuenta.add(factura);
			}
		
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return resumenCuenta;
		
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

	public static Vector<LitroBarril> obtenerConsumoLitros(String des, String has) {
		String[] fe=des.split("/");
		String desde=fe[2]+fe[1]+fe[0];
		fe=has.split("/");
		String hasta=fe[2]+fe[1]+fe[0];
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<LitroBarril> resultado=null;
		try{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select p.nombre,SUM(it.cantidad) as cantidad, (SUM(it.cantidad))/50 as barriles from remitos r, itemremito it, productos p\n" + 
					"where r.fecha>="+desde+" and fecha<="+hasta+" and r.id=it.idRemito and it.idproducto=p.id group by p.nombre");
			resultado= new Vector<LitroBarril>();
			while (r.next()){
				LitroBarril item=new LitroBarril();
				item.setNombre(r.getString(1));
				item.setLitros(r.getInt(2));
				item.setBarriles(r.getFloat(3));
				resultado.add(item);
			}
		}catch(Exception e) {
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return resultado;
		
	}

	public static Vector<ClienteSaldoBean> obtenerSaldos() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<ClienteSaldoBean> saldos=new Vector<ClienteSaldoBean>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select * from clientes"); 
			ResultSet r1;
			while(r.next()){
				ClienteSaldoBean c=new ClienteSaldoBean();
				c.setCliente(r.getString(2));
				int idCliente=r.getInt(1);
				c.setPagos(obtenerPagos(idCliente));
				c.setRemitos(obtenerImporteRemitos(idCliente));
				c.setImpuestos(obtenerImporteImpuestos(idCliente));
				saldos.add(c);
			}
		
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return saldos;
		
	
	}

	private static float obtenerImporteImpuestos(int idCliente) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		float st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select sum(iva+impuestos) from factura where idCliente="+idCliente+" group by idCliente");
			while(r.next()){
				st=r.getFloat(1);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return st;

	}

	private static float obtenerImporteRemitos(int idCliente) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		float st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select sum(it.cantidad*it.precio) as ventas from remitos r, itemRemito it where r.id=it.idRemito and r.idCliente="+idCliente+" group by idCliente");
			while(r.next()){
				st=r.getFloat(1);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return st;

	}

	private static float obtenerPagos(int idCliente) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		float st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select sum(importe) as pagos from pagos where idCliente="+idCliente+" group by idCliente");
			while(r.next()){
				st=r.getInt(1);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return st;

	}

	public static VentaCobroBean obtenerVentaCorbo(String des, String has) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String[] fe=des.split("/");
		String desde=fe[2]+fe[1]+fe[0];
		fe=has.split("/");
		String hasta=fe[2]+fe[1]+fe[0];
		VentaCobroBean vc=new VentaCobroBean();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select sum(precio*cantidad)as importe from remitos r, itemRemito it\n" + 
					"where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito");
			while(r.next()){
				vc.setVenta(r.getFloat(1));
			}
			ResultSet r1=s.executeQuery("select sum(importe) from pagos where fecha>="+desde+" and fecha<="+hasta);
			while(r1.next()){
				vc.setCobro(r1.getFloat(1));
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return vc;
	}

	public static LitrosAnualesBean obtenerLitrosAnuales(int anio) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String desde=anio+String.format("%02d", 1)+"00";
		String hasta=anio+String.format("%02d", 1)+"31";
		LitrosAnualesBean la=new LitrosAnualesBean();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select sum(it.cantidad) from remitos r, itemRemito it where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito;");
			while(r.next()){
				la.setEnero(r.getFloat(1));
			}
			desde=anio+String.format("%02d", 2)+"00";
			hasta=anio+String.format("%02d", 2)+"31";
			r=s.executeQuery("select sum(it.cantidad) from remitos r, itemRemito it where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito;");
			while(r.next()){
				la.setFebrero(r.getFloat(1));
			}
			desde=anio+String.format("%02d", 3)+"00";
			hasta=anio+String.format("%02d", 3)+"31";
			r=s.executeQuery("select sum(it.cantidad) from remitos r, itemRemito it where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito;");
			while(r.next()){
				la.setMarzo(r.getFloat(1));
			}
			desde=anio+String.format("%02d", 4)+"00";
			hasta=anio+String.format("%02d", 4)+"31";
			r=s.executeQuery("select sum(it.cantidad) from remitos r, itemRemito it where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito;");
			while(r.next()){
				la.setAbril(r.getFloat(1));
			}
			desde=anio+String.format("%02d", 5)+"00";
			hasta=anio+String.format("%02d", 5)+"31";
			r=s.executeQuery("select sum(it.cantidad) from remitos r, itemRemito it where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito;");
			while(r.next()){
				la.setMayo(r.getFloat(1));
			}
			desde=anio+String.format("%02d", 6)+"00";
			hasta=anio+String.format("%02d", 6)+"31";
			r=s.executeQuery("select sum(it.cantidad) from remitos r, itemRemito it where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito;");
			while(r.next()){
				la.setJunio(r.getFloat(1));
			}
			desde=anio+String.format("%02d", 7)+"00";
			hasta=anio+String.format("%02d", 7)+"31";
			r=s.executeQuery("select sum(it.cantidad) from remitos r, itemRemito it where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito;");
			while(r.next()){
				la.setJulio(r.getFloat(1));
			}
			desde=anio+String.format("%02d", 8)+"00";
			hasta=anio+String.format("%02d", 8)+"31";
			r=s.executeQuery("select sum(it.cantidad) from remitos r, itemRemito it where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito;");
			while(r.next()){
				la.setAgosto(r.getFloat(1));
			}
			desde=anio+String.format("%02d", 9)+"00";
			hasta=anio+String.format("%02d", 9)+"31";
			r=s.executeQuery("select sum(it.cantidad) from remitos r, itemRemito it where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito;");
			while(r.next()){
				la.setSeptiembre(r.getFloat(1));
			}
			desde=anio+String.format("%02d", 10)+"00";
			hasta=anio+String.format("%02d", 10)+"31";
			r=s.executeQuery("select sum(it.cantidad) from remitos r, itemRemito it where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito;");
			while(r.next()){
				la.setOctubre(r.getFloat(1));
			}
			desde=anio+String.format("%02d", 11)+"00";
			hasta=anio+String.format("%02d", 11)+"31";
			r=s.executeQuery("select sum(it.cantidad) from remitos r, itemRemito it where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito;");
			while(r.next()){
				la.setNoviembre(r.getFloat(1));
			}
			desde=anio+String.format("%02d", 12)+"00";
			hasta=anio+String.format("%02d", 12)+"31";
			r=s.executeQuery("select sum(it.cantidad) from remitos r, itemRemito it where r.fecha>="+desde+" and r.fecha<="+hasta+" and r.id=it.idRemito;");
			while(r.next()){
				la.setDiciembre(r.getFloat(1));
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return la;
	}

	public static Vector<LitroBarril> obtenerConsumoLitrosSinBonificacion(String des, String has) {
		String[] fe=des.split("/");
		String desde=fe[2]+fe[1]+fe[0];
		fe=has.split("/");
		String hasta=fe[2]+fe[1]+fe[0];
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<LitroBarril> resultado=null;
		try{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select p.nombre,SUM(it.cantidad) as cantidad, (SUM(it.cantidad))/50 as barriles from remitos r, itemremito it, productos p\n" + 
					"where r.fecha>="+desde+" and fecha<="+hasta+" and r.id=it.idRemito and it.idproducto=p.id and it.precio!=0 group by p.nombre");
			resultado= new Vector<LitroBarril>();
			while (r.next()){
				LitroBarril item=new LitroBarril();
				item.setNombre(r.getString(1));
				item.setLitros(r.getInt(2));
				item.setBarriles(r.getFloat(3));
				resultado.add(item);
			}
		}catch(Exception e) {
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return resultado;
		
	}

}
