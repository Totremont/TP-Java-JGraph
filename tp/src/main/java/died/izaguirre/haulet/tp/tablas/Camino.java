package died.izaguirre.haulet.tp.tablas;

import java.util.ArrayList;

public class Camino {
	
	private Parada origen;
	//Lista de todas las paradas con las que es adyacente el origen
	//Los nodos aislados deben tener una lista de destinos empty (no nula)
	//Distancia si puede ser nula si no hay destinos
	
	private ArrayList<Parada> destinos;
	private ArrayList<Integer> distancia;
	//DISTANCIA Y DESTINOS DEBEN ESTAR EN EL MISMO ORDEN
	
	
	
	public Parada getOrigen() {
		return origen;
	}
	public Camino(Parada origen, ArrayList<Parada> destinos, ArrayList<Integer> distancia) {
		super();
		this.origen = origen;
		this.destinos = destinos;
		this.distancia = distancia;
	}
	public void setOrigen(Parada origen) {
		this.origen = origen;
	}
	public ArrayList<Parada> getDestinos() {
		return destinos;
	}
	public void setDestinos(ArrayList<Parada> destinos) {
		this.destinos = destinos;
	}
	public ArrayList<Integer> getDistancia() {
		return distancia;
	}
	public void setDistancia(ArrayList<Integer> distancia) {
		this.distancia = distancia;
	}
	
	
	
	
	
}
