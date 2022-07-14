package died.izaguirre.haulet.tp.tablas;

public class Posee {

	private Parada parada;
	private Linea linea;

	public Posee() {
	}

	public Posee(Parada parada, Linea linea) {
		this.parada = parada;
		this.linea = linea;
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

}
