package died.izaguirre.haulet.tp.estructuras.grafo;

import java.util.ArrayList;

import died.izaguirre.haulet.tp.estructuras.matriz.Matriz;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;

public class GrafoDirigido {

	protected ArrayList<Parada> nodos;
	protected ArrayList<Camino> aristas;
	protected Matriz adyacencia;			//El orden de las filas y columnas es el orden de los nodos
	public GrafoDirigido(ArrayList<Parada> nodos, ArrayList<Camino> aristas) {
		super();
		this.nodos = nodos;
		this.aristas = aristas;
		this.adyacencia = new Matriz(nodos.size());
		this.adyacencia.modificarMatriz((i,j) -> {
			if(aristas.get(i).getDestinos().contains(nodos.get(j))) return 1; else return 0; });
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
		return adyacencia;
	}
	
}
