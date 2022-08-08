package died.izaguirre.haulet.tp.estructuras.grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import died.izaguirre.haulet.tp.estructuras.matriz.Matriz;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;

public class GrafoConPeso extends GrafoDirigido {
	
	private Matriz matrizPesos;
	public static final int INFINITO = Integer.MAX_VALUE;
	
	public GrafoConPeso(ArrayList<Parada> nodos, ArrayList<Camino> aristas) 
	{		
		super(nodos, aristas);
		matrizPesos = new Matriz(nodos.size());
	}
	
	public int getPesoArco(Parada origen, Parada destino) 
	{
		List<Integer> resultado = aristas.stream().filter(it -> it.getOrigen().equals(origen) && it.getDestino().equals(destino)).map(it -> it.getDistancia()).collect(Collectors.toList());
		return resultado.isEmpty() ? INFINITO : resultado.get(0);
	}
	
	public Matriz getMatrizPeso() 
	{
		matrizPesos.modificarMatriz((i,j) -> 
		{
			if(adyacencia.getValor(i,j) > 0) 
			{
				int posDistancia = aristas.stream().filter(it -> 
					(it.getOrigen().equals(nodos.get(i)) && it.getDestino().equals(nodos.get(j)))
				).map(w -> w.getDistancia()).collect(Collectors.toList()).get(0);				
				return posDistancia;
			} else return INFINITO;
		});
		return matrizPesos;
	}
		
	public ArrayList<Parada> dijkstra(Parada origen, Parada destino)
	{
		boolean[] marcados = new boolean[nodos.size()];
		int[] distancias = new int[nodos.size()];
		Parada[] trayecto = new Parada[nodos.size()];
		int posOrigen = nodos.indexOf(origen);
		
		// valores iniciales
		for (int i = 0; i < nodos.size(); i++)
		{
			marcados[i] = false;
			distancias[i] = getPesoArco(origen, nodos.get(i));
			trayecto[i] = origen;
		}
	
		marcados[posOrigen] = true; 
		distancias[posOrigen] = 0;
		
		// Pasos para marcar los n-1 vértices
		for (int i = 1; i < nodos.size(); i++)
		{
			Parada v = minimo(marcados,distancias); /* selecciona vértice no marcado de menor distancia */		
			marcados[nodos.indexOf(v)] = true;
			
		// actualiza distancia de vértices no marcados
			for (int w = 1; w < nodos.size(); w++) 
				if (!marcados[w])
					if ((distancias[nodos.indexOf(v)] + getPesoArco(v, nodos.get(w))) < distancias[w])
					{
						distancias[w] = distancias[w] + getPesoArco(v, nodos.get(w));
						trayecto[w] = v;
					}
		}
		//Camino minimo para pares de nodos
		ArrayList<Parada> minimo = recuperaCamino(trayecto, origen, destino);
		Collections.reverse(minimo);
		return minimo;
	}
	
	//Obtiene el nodo mas cercano al nodo dado
	private Parada minimo(boolean[] marcados, int[] distancias)
	{
		int valor = GrafoConPeso.INFINITO;
		int v = 1;
		for (int j = 1; j < nodos.size(); j++)
			if (!marcados[j] && (distancias[j]<= valor))
			{
					valor = distancias[j];
					v = j;
			}
		return nodos.get(v);
	}
	
	private ArrayList<Parada> recuperaCamino(Parada[] trayectos, Parada origen, Parada destino)
	{
		ArrayList<Parada> trayecto = new ArrayList<>();
		trayecto.add(destino);
		Parada anterior = trayectos[nodos.indexOf(destino)];
		if (anterior != origen)
		{
			trayecto.addAll(recuperaCamino(trayectos, origen, anterior));
		}
		else trayecto.add(origen);
		
		return trayecto;
	}
	
	public Integer distanciaTotal(List<Camino> camino) 
	{
		Integer distancia = 0;
		for(int i = 0; i < camino.size(); i++) 
		{
			distancia += camino.get(i).getDistancia();
		}
		return distancia;
	}

}
