package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.FermentadorBean;
import beans.GranoBean;

public class PersistenciaFermentador {

	public static Vector<FermentadorBean> obtenerFermentadores() {
		Connection con=PoolConnection.getPoolConnection().getConnection();
		Vector<FermentadorBean> granos =new Vector<FermentadorBean>();
		try
		{
			Statement s=con.createStatement();
			ResultSet r=s.executeQuery("select nombre,litros from fermentador");
			while(r.next()){
				FermentadorBean f= new FermentadorBean();
				f.setNombre(r.getString(1));
				f.setCapacidad(r.getInt(2));
				granos.add(f);
			}
		}
		catch(Exception e){
			System.out.print(e.getStackTrace());
			System.out.print(e);
		}
		PoolConnection.getPoolConnection().realeaseConnection(con);	
		return granos;
	}

}
