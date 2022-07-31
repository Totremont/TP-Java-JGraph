package died.izaguirre.haulet.tp.gui.grafo;

import java.util.ArrayList;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import died.izaguirre.haulet.tp.dao.impl.CaminoDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.estructuras.grafo.GrafoConPeso;
import died.izaguirre.haulet.tp.gui.menuparadas.CrearParada;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;

public class Grafo {
	
	private GrafoConPeso grafo;

	public static void main(String[] args) {
		System.setProperty("org.graphstream.ui", "swing"); 
		Graph graph = new SingleGraph("Sistema de Transporte");
		ParadaDaoImpl paradaDao = new ParadaDaoImpl();
		CaminoDaoImpl caminoDao = new CaminoDaoImpl();		
		ArrayList<Parada> paradas = (ArrayList<Parada>) paradaDao.getAll();
		ArrayList<Camino> caminos = (ArrayList<Camino>) caminoDao.getAll();
		ArrayList<Node> nodos = new ArrayList<>();
		ArrayList<Edge> aristas = new ArrayList<>();
		
		paradas.forEach(it -> 
		{
			nodos.add(graph.addNode(it.getCalle()));
		});
		caminos.forEach(it -> 
		{
			aristas.add(graph.addEdge(it.getId().toString(), it.getOrigen().getCalle(), it.getDestino().getCalle()));
		});
		
		graph.display();
		
	}
	
	public GrafoConPeso crearGrafo2() 
	{
		ParadaDaoImpl paradaDao = new ParadaDaoImpl();
		CaminoDaoImpl caminoDao = new CaminoDaoImpl();
		ArrayList<Parada> paradas = (ArrayList<Parada>) paradaDao.getAll();
		ArrayList<Camino> caminos = (ArrayList<Camino>) caminoDao.getAll();
		grafo = new GrafoConPeso(paradas, caminos);
		return grafo;
		
	}
	
	public Graph crearGrafo() 
	{
		Graph graph = new SingleGraph("Sistema de Transporte");
		ParadaDaoImpl paradaDao = new ParadaDaoImpl();
		CaminoDaoImpl caminoDao = new CaminoDaoImpl();		
		ArrayList<Parada> paradas = (ArrayList<Parada>) paradaDao.getAll();
		ArrayList<Camino> caminos = (ArrayList<Camino>) caminoDao.getAll();
		ArrayList<Node> nodos = new ArrayList<>();
		ArrayList<Edge> aristas = new ArrayList<>();
		
		paradas.forEach(it -> 
		{
			nodos.add(graph.addNode(it.getCalle()));
		});
		caminos.forEach(it -> 
		{
			aristas.add(graph.addEdge(it.getId().toString(), it.getOrigen().getCalle(), it.getDestino().getCalle()));
		});
		
		return graph;
		
	}

}
