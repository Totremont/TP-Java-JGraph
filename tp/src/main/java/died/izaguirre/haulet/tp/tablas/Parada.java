package died.izaguirre.haulet.tp.tablas;

public class Parada {

	private Integer id;
	private Integer nroParada;
	private String calle;

	public Parada() {
	}

	public Parada(Integer nroParada, String calle) {
		super();
		this.nroParada = nroParada;
		this.calle = calle;
	}

	public Integer getNroParada() {
		return nroParada;
	}

	public void setNroParada(Integer nroParada) {
		this.nroParada = nroParada;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return nroParada + ": " + calle;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		Parada otro = (Parada) obj;
		return ((otro == this) || (this.getId() == otro.getId()));
	}
	
	

}
