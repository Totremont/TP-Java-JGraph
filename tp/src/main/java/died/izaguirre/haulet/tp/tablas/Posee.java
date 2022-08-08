package died.izaguirre.haulet.tp.tablas;

import died.izaguirre.haulet.tp.tablas.linea.Linea;

public class Posee {

	private Parada parada;
	private Linea linea;
	private Integer ordenParada;

	public Posee() {
	}

	public Posee(Parada parada, Linea linea, Integer ordenParada) {
		this.parada = parada;
		this.linea = linea;
		this.ordenParada = ordenParada;
	}

	public Parada getParada() {
		return parada;
	}

	public void setParada(Parada parada) {
		this.parada = parada;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	
	
	public Integer getOrdenParada() {
		return ordenParada;
	}

	public void setOrdenParada(Integer ordenParada) {
		this.ordenParada = ordenParada;
	}

	@Override
	public boolean equals(Object obj) {
		Posee otro = (Posee) obj;
		return ((otro == this) || (this.getParada().equals(otro.getParada()) && this.getLinea().equals(otro.getLinea())));
	}

}
