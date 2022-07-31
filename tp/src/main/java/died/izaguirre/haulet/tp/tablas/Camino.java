package died.izaguirre.haulet.tp.tablas;

import java.util.ArrayList;

public class Camino {

	// Paradas adyacentes
	private Integer id;
	private Parada origen;
	private Parada destino;
	private Integer distancia;
	private Integer capacidad;

	public Parada getOrigen() {
		return origen;
	}
	
	public Camino() {
	}
	
	public Camino(Parada origen, Parada destino, Integer distancia) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.distancia = distancia;
		this.capacidad = 0;
	}

	public Camino(Parada origen, Parada destino, Integer distancia, Integer capacidad) {
		this(origen, destino, distancia);
		this.capacidad = capacidad;
	}

	public Parada getDestino() {
		return destino;
	}

	public void setDestino(Parada destinos) {
		this.destino = destinos;
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
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		Camino otro = (Camino) obj;
		return ((otro == this) || (this.getOrigen() == otro.getOrigen() && this.getDestino() == otro.getDestino())
				|| this.getId() == otro.getId());
	}

}
