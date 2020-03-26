package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import beans.FacturaBean;
import beans.FacturacionBean;
import beans.ItemFacturaBean;
import beans.ItemRemitoBean;
import utils.Estado;

public class PersistenciaFactura {

	public static boolean ingresarFactura(FacturaBean factura) {
		boolean resultado = false;
		Connection con=PoolConnection.getPoolConnection().getConnection();
		
		try{
			Statement s=con.createStatement();
			int idFactura=obtenerMaxIdFactura();
			String[] fe=factura.getFecha().split("/");
			String fech=fe[2]+fe[1]+fe[0];
			int idResponsable=obtenerIdUsuario(factura.getEmpleado().getNombre());
			int idCliente=obtenerIdCliente(factura.getCliente());
			s.executeUpdate("insert into factura values("+idFactura+","+factura.getNumero()+","+fech+","+idCliente+","+idResponsable+","+factura.getIVA()+","+factura.getImpuestos()+",'','"+Estado.PENDIENTE+"')");
			Vector<ItemFacturaBean> items=factura.getItems();
			ItemFacturaBean it=new ItemFacturaBean();
			int idItem=obtenerMaxIdItemFactura();
			int idProducto=0;
			for(int i = 0; i < items.size(); i++){
				it=items.get(i);
				idProducto=obtenerIdProducto(it.getProducto());
				s.executeUpdate("insert into itemfactura values ("+idItem+","+idFactura+","+idProducto+","+it.getPrecio()+","+it.getCantidad()+",'"+Estado.PENDIENTE+"')");
				idItem++;
			}
			resultado = true;
		}catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);
		return resultado;
	
	}

	private static int obtenerMaxIdItemFactura() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(Id) from itemFactura");
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

	private static int obtenerMaxIdFactura() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		int st=0;
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select max(Id) from factura");
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

	public static FacturacionBean obtenerFacturacion(String des, String has) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		String[] fe=des.split("/");
		String desde=fe[2]+fe[1]+fe[0];
		fe=has.split("/");
		String hasta=fe[2]+fe[1]+fe[0];
		FacturacionBean f=new FacturacionBean();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select sum(x.importe) as importe, sum(x.impuestos) as impuestos, sum(x.iva) as iva "
					+ "from (select  sum(it.precio*it.cantidad) as importe, f.impuestos,f.iva from factura f, itemFactura it "
					+ "where f.fecha>='"+desde+"' and f.fecha<='"+hasta+"' and it.idfactura=f.id group by f.id)x");
			while(r.next()){
				f.setImporte(r.getFloat(1));
				f.setImpuestos(r.getFloat(2));
				f.setIva(r.getFloat(3));
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return f;
	}
	

}
