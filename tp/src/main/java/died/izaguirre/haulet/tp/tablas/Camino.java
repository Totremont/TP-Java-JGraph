package died.izaguirre.haulet.tp.tablas;

import java.util.ArrayList;

public class Camino {

	// Paradas adyacentes
	private Parada origen;
	private Parada destinos;
	private Integer distancia;
	private Integer capacidad;

	public Parada getOrigen() {
		return origen;
	}

	public Camino(Parada origen, Parada destino, Integer distancia) {
		super();
		this.origen = origen;
		this.destinos = destino;
		this.distancia = distancia;
		this.capacidad = 0;
	}

	public Camino(Parada origen, Parada destino, Integer distancia, Integer capacidad) {
		this(origen, destino, distancia);
		this.capacidad = capacidad;
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

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

}
