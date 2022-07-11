package died.izaguirre.haulet.tp.tablas;

import java.util.ArrayList;

public class Camino {
	
	//Paradas adyacentes
	private Parada origen;
	private Parada destinos;
	private Integer distancia;
	
	public Parada getOrigen() {
		return origen;
	}
		
	public Camino(Parada origen, Parada destinos, Integer distancia) {
		super();
		this.origen = origen;
		this.destinos = destinos;
		this.distancia = distancia;
	}

	public Parada getDestinos() {
		return destinos;
	}

	public void setDestinos(Parada destinos) {
		this.destinos = destinos;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public void setOrigen(Parada origen) {
		this.origen = origen;
	}
	
	
	
	
	
	
	
}
