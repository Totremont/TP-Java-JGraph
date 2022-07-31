package died.izaguirre.haulet.tp.estructuras.grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import died.izaguirre.haulet.tp.estructuras.matriz.Matriz;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;

public class GrafoDirigido {

	protected ArrayList<Parada> nodos;
	protected ArrayList<Camino> aristas;
	protected Matriz adyacencia;			//El orden de las filas y columnas es el orden de los nodos
	protected ArrayList<ArrayList<Parada>> caminos;
	
	public GrafoDirigido(ArrayList<Parada> nodos, ArrayList<Camino> aristas) {
		super();
		this.nodos = nodos;
		this.aristas = aristas;
		this.adyacencia = new Matriz(nodos.size());
	}
	
	//Este metodo guarda en un arreglo de arreglos la lista de paradas con las que es adyacente cada nodo
	private void listaCaminos()
	{
		ArrayList<ArrayList<Parada>> resultado = new ArrayList<>();
		nodos.forEach(it -> 
		{
			resultado.add(adyacentesDe(it));
		});
		caminos = resultado;
	}
	
	public ArrayList<Parada> adyacentesDe(Parada origen)
	{
		ArrayList<Parada> resultado = new ArrayList<>();
		aristas.forEach(it -> 
		{
			if(it.getOrigen().equals(origen)) resultado.add(it.getDestino());
		});
		
		return resultado;
	}
	
	public Matriz potencia(int n)
	{
		Matriz aux = adyacencia.clonar();
			if (n <= 1)
				return aux;
			else
				return aux.multiplicar(aux, potencia(n - 1));	
	}
	
	public Matriz warshall()
	{		
		//boolean[][] resultante = mat;	Eze - Resultante tiene una referencia a mat;
		getMatrizAdyacencia();
		Matriz aux = adyacencia.clonar();
		boolean valor = false;
		for(int k = 0 ; k < adyacencia.getFilas() ; k++) 			// k para recorrer por la diagonal principal
			for(int i = 0; i < adyacencia.getFilas() ; i++) 		// i recorre horizontalmente a la altura de k
				for(int j = 0 ; j < adyacencia.getColumnas() ; j++) 
				{
					valor = aux.getValorBooleano(i, j) || (aux.getValorBooleano(i, k) && aux.getValorBooleano(k, j));
					aux.insertarValor(valor, i, j);
				}	
		return aux;		
	}
	
	public Matriz getMatrizAdyacencia() 
	{
		listaCaminos();
		this.adyacencia.modificarMatriz((i,j) -> {
			if(caminos.get(i).contains(nodos.get(j))) return 1; else return 0; });
		return adyacencia;
	}
	
	//No funciona si el grafo tiene ciclos
	public ArrayList<ArrayList<Parada>> todosLosCaminos(Parada origen, Parada destino)
	{
		//Caso base
		ArrayList<ArrayList<Parada>> caminos = new ArrayList<>();
		if(origen.equals(destino)) 
		{
			ArrayList<Parada> tramo = new ArrayList<>();
			tramo.add(origen);
			caminos.add(tramo);
		}
		else 	//Caso recursivo
		{
			ArrayList<Parada> adyacentes = adyacentesDe(origen);
			adyacentes.forEach(it -> 
			{
				ArrayList<ArrayList<Parada>> susCaminos = todosLosCaminos(it, destino);
				susCaminos.forEach(j -> 
				{
					j.add(origen);
					caminos.add(j);
				});
			});
		}
		return caminos;
	}
	
}
