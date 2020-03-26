package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.ClarificanteBean;
import beans.GranoBean;
import beans.LupuloBean;
import beans.SalBean;

public class PersistenciaReceta {

	public static Vector<GranoBean> obtenerGranos(String estilo) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<GranoBean> granos =new Vector<GranoBean>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select g.nombre, it.cantidad from receta r, itemgranos it, granos g "
					+ "where r.id=it.idreceta and it.idGrano=g.id and r.nombre like '"+estilo+"'");
			while(r.next()){
				GranoBean g= new GranoBean();
				g.setNombre(r.getString(1));
				g.setCantidad(r.getFloat(2));
				granos.add(g);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return granos;
	}

	public static Vector<LupuloBean> obtenerLupulos(String estilo) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<LupuloBean> lupulos =new Vector<LupuloBean>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select l.nombre, it.cantidad,it.tiempo from receta r, itemlupulos it, lupulos l "
					+ "where r.id=it.idreceta and it.idLupulo=l.id and r.nombre like '"+estilo+"';");
			while(r.next()){
				LupuloBean l= new LupuloBean();
				l.setNombre(r.getString(1));
				l.setCantidad(r.getFloat(2));
				l.setTiempo(r.getString(3));
				lupulos.add(l);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return lupulos;
	}

	public static Vector<SalBean> obtenerSales(String estilo) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<SalBean> sales =new Vector<SalBean>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select s.nombre, it.cantidad from receta r, itemSales it, sales s \n" + 
					"where r.id=it.idreceta and it.idSal=s.id and r.nombre like '"+estilo+"';");
			while(r.next()){
				SalBean sal= new SalBean();
				sal.setNombre(r.getString(1));
				sal.setCantidad(r.getFloat(2));
				sales.add(sal);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return sales;
	}

	public static Vector<ClarificanteBean> obtenerClarificantes(String estilo) {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<ClarificanteBean> clarificantes =new Vector<ClarificanteBean>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select c.nombre, it.cantidad,it.tiempo from receta r, itemClarificanteYOtros it, clarificantesyotros c\n" + 
					"where r.id=it.idreceta and it.idClarificanteYOtros=c.id and r.nombre like '"+estilo+"'");
			while(r.next()){
				ClarificanteBean c= new ClarificanteBean();
				c.setNombre(r.getString(1));
				c.setCantidad(r.getFloat(2));
				c.setTiempo(r.getInt(3));
				clarificantes.add(c);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return clarificantes;
	}
	
		
	

}
