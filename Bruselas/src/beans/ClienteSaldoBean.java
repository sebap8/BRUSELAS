package beans;

import java.util.Comparator;

public class ClienteSaldoBean implements Comparable<ClienteSaldoBean>{
	private String cliente;
	private float pagos;
	private float impuestos;
	private float remitos;
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public float getPagos() {
		return pagos;
	}
	public void setPagos(float pagos) {
		this.pagos = pagos;
	}
	public float getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(float impuestos) {
		this.impuestos = impuestos;
	}
	public float getRemitos() {
		return remitos;
	}
	public void setRemitos(float remitos) {
		this.remitos = remitos;
	}
	public float getTotal(){
		return (impuestos+remitos-pagos);
	}
	public static Comparator<ClienteSaldoBean> ComparoFechaComparator= new Comparator<ClienteSaldoBean>() {

		@Override
		public int compare(ClienteSaldoBean o1, ClienteSaldoBean o2) {			
			
			if(o1.getTotal()>o2.getTotal()) {
				return 1;
			}else {
				if(o1.getTotal()==o2.getTotal()) {
					return 0;
				}else {
					return -1;
				}
			}
		}
	};
	@Override
	public int compareTo(ClienteSaldoBean o) {
		if(o.getTotal()>this.getTotal()) {
			return 1;
		}else {
			if(o.getTotal()==this.getTotal()) {
				return 0;
			}else {
				return -1;
			}
		}
	}
	
	
	

}
