package died.izaguirre.haulet.tp.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/* 
 * Las clases de este paquete las sacamos de
 * los archivos del taller 2
 * 
 * */

public class Graph<T> {

	protected List<Edge<T>> edges;
	protected List<Vertex<T>> vertexs;

	public Graph() {
		this.edges = new ArrayList<Edge<T>>();
		this.vertexs = new ArrayList<Vertex<T>>();
	}

	public Graph<T> addVertex(T nodo) {
		this.addVertex(new Vertex<T>(nodo));
		return this;
	}

	protected void addVertex(Vertex<T> nodo) {
		this.vertexs.add(nodo);
	}

	public Graph<T> connect(T n1, T n2) {
		this.connect(getVertex(n1), getVertex(n2), 1.0);
		return this;
	}

	public Graph<T> connect(T n1, T n2, Number valor) {
		this.connect(getVertex(n1), getVertex(n2), valor);
		return this;
	}

	protected void connect(Vertex<T> nodo1, Vertex<T> nodo2, Number valor) {
		this.edges.add(new Edge<T>(nodo1, nodo2, valor));
	}

	public Vertex<T> getVertex(T valor) {
		return this.vertexs.get(this.vertexs.indexOf(new Vertex<T>(valor)));
	}

	public List<T> getNeighbourhood(T valor) {
		Vertex<T> aNode = this.getVertex(valor);
		List<T> output = new ArrayList<T>();
		for (Edge<T> theEdges : this.edges) {
			if (theEdges.getOrigin().equals(aNode)) {
				output.add(theEdges.getEnd().getValue());
			}
		}
		return output;
	}

	protected List<Vertex<T>> getNeighbors(Vertex<T> unNodo) {
		List<Vertex<T>> salida = new ArrayList<Vertex<T>>();
		for (Edge<T> theEdges : this.edges) {
			if (theEdges.getOrigin().equals(unNodo)) {
				salida.add(theEdges.getEnd());
			}
		}
		return salida;
	}

	protected List<T> getNeighbors(T unNodo) {
		return this.getNeighbors(new Vertex<T>(unNodo)).stream().map(v -> v.getValue()).collect(Collectors.toList());
	}

	public void printEdges() {
		System.out.println(this.edges.toString());
	}

	protected Edge<T> findEdge(T v1, T v2) {
		return this.findEdge(new Vertex<T>(v1), new Vertex<T>(v2));
	}

	protected Edge<T> findEdge(Vertex<T> v1, Vertex<T> v2) {
		for (Edge<T> unaArista : this.edges) {

			if (unaArista.getOrigin().equals(v1) && unaArista.getEnd().equals(v2))
				return unaArista;
		}
		return null;
	}

	public Integer inputDegree(T vertice) {
		return this.inputDegree(new Vertex<T>(vertice));
	}

	public Integer outputDegree(T vertice) {
		return this.outputDegree(new Vertex<T>(vertice));
	}

	public boolean isAdjacent(T v1, T v2) {
		return isAdjacent(new Vertex<T>(v1), new Vertex<T>(v2));
	}

	public boolean isAdjacent(Vertex<T> v1, Vertex<T> v2) {
		return !this.edges.stream().filter(e -> e.getOrigin().equals(v1) & e.getEnd().equals(v2))
				.collect(Collectors.toList()).isEmpty();
	}

	protected Integer inputDegree(Vertex<T> vertice) {
		Integer count = 0;
		for (Edge<T> e : this.edges)
			if (e.getEnd().equals(vertice))
				count++;
		return count;
	}

	public Integer outputDegree(Vertex<T> vertice) {
		Integer count = 0;
		for (Edge<T> e : this.edges)
			if (e.getOrigin().equals(vertice))
				count++;
		return count;
	}

	public List<T> bfs(Vertex<T> inicio) {
		List<T> resultado = new ArrayList<T>();
		// estructuras auxiliares
		Queue<Vertex<T>> pendientes = new LinkedList<Vertex<T>>();
		HashSet<Vertex<T>> marcados = new HashSet<Vertex<T>>();
		marcados.add(inicio);
		pendientes.add(inicio);

		while (!pendientes.isEmpty()) {
			Vertex<T> actual = pendientes.poll();
			List<Vertex<T>> adyacentes = this.getNeighbors(actual);
			resultado.add(actual.getValue());
			for (Vertex<T> v : adyacentes) {
				if (!marcados.contains(v)) {
					pendientes.add(v);
					marcados.add(v);
				}
			}
		}
		return resultado;
	}

	public List<T> dfs(Vertex<T> inicio) {
		List<T> resultado = new ArrayList<T>();
		Stack<Vertex<T>> pendientes = new Stack<Vertex<T>>();
		HashSet<Vertex<T>> marcados = new HashSet<Vertex<T>>();
		marcados.add(inicio);
		pendientes.push(inicio);

		while (!pendientes.isEmpty()) {
			Vertex<T> actual = pendientes.pop();
			List<Vertex<T>> adyacentes = this.getNeighbors(actual);
			resultado.add(actual.getValue());
			for (Vertex<T> v : adyacentes) {
				if (!marcados.contains(v)) {
					pendientes.push(v);
					marcados.add(v);
				}
			}
		}
		// System.out.println(resultado);
		return resultado;
	}

	public List<T> topological() {
		List<T> resultado = new ArrayList<T>();
		Map<Vertex<T>, Integer> gradosVertex = new HashMap<Vertex<T>, Integer>();
		for (Vertex<T> vert : this.vertexs) {
			gradosVertex.put(vert, this.inputDegree(vert));
		}
		while (!gradosVertex.isEmpty()) {

			List<Vertex<T>> nodosSinEntradas = gradosVertex.entrySet().stream().filter(x -> x.getValue() == 0)
					.map(p -> p.getKey()).collect(Collectors.toList());

			if (nodosSinEntradas.isEmpty())
				System.out.println("TIENE CICLOS");

			for (Vertex<T> v : nodosSinEntradas) {
				resultado.add(v.getValue());
				gradosVertex.remove(v);
				for (Vertex<T> ady : this.getNeighbors(v)) {
					gradosVertex.put(ady, gradosVertex.get(ady) - 1);
				}
			}
		}

		System.out.println(resultado);
		return resultado;
	}

}
