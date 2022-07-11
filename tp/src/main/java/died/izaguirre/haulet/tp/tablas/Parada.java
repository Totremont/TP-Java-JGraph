package died.izaguirre.haulet.tp.tablas;

public class Parada {
	
	private int id;
	private int nroParada;
	private String calle;
	
	public Parada(int id, int nroParada, String calle) {
		super();
		this.id = id;
		this.nroParada = nroParada;
		this.calle = calle;
	}

	public int getNroParada() {
		return nroParada;
	}

	public void setNroParada(int nroParada) {
		this.nroParada = nroParada;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	
	
	

}
