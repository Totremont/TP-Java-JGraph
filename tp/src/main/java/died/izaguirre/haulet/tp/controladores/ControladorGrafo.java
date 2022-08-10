package died.izaguirre.haulet.tp.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.spriteManager.SpriteManager;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;

import died.izaguirre.haulet.tp.dao.impl.CaminoDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.IncidenciaDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.estructuras.grafo.GrafoConPeso;
import died.izaguirre.haulet.tp.gui.menuparadas.CrearParada;
import died.izaguirre.haulet.tp.gui.utilities.CSSParser;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Incidencia;
import died.izaguirre.haulet.tp.tablas.Parada;

public class ControladorGrafo {
	
	private Graph graph = null;
	private SwingViewer view = null;
	private GrafoConPeso grafoPeso = null;
	private static ControladorGrafo instance = null;
	private static SpriteManager manager;
	private ArrayList<Parada> deshabilitadas = new ArrayList<>();
	private ArrayList<Parada> paradas = new ArrayList<>();
	private ArrayList<Camino> caminos = new ArrayList<>();
	
	public static ControladorGrafo getInstance() 
	{
		if(instance == null) instance = new ControladorGrafo();
		return instance;
	}
	
	
	private ControladorGrafo() {

	}
	
	public void agregarCamino(Camino camino) 
	{
		caminos.add(camino);
		Edge edge = graph.addEdge(camino.getId().toString(), camino.getOrigen().getId().toString(),camino.getDestino().getId().toString(), true);
		edge.setAttribute("ui.label", camino.getDistancia() + " Km");
		grafoPeso = new GrafoConPeso(paradas, caminos);
	}
	
	public void agregarParada(Parada parada) 
	{
		paradas.add(parada);
		Node node = graph.addNode(parada.getId().toString());
		node.setAttribute("ui.label", parada.getCalle());
		grafoPeso = new GrafoConPeso(paradas, caminos);
	}
	
	public void eliminarCamino(Camino camino) 
	{
		caminos.remove(camino);
		graph.removeEdge(camino.getId());
		grafoPeso = new GrafoConPeso(paradas, caminos);
	}
	
	public void eliminarParada(Parada parada) 
	{
		paradas.remove(parada);
		graph.removeNode(parada.getId());
		grafoPeso = new GrafoConPeso(paradas, caminos);
	}
	
	public Graph getGraph() {
		if(graph == null) { 
			view = crearGrafo();
			manager = new SpriteManager(graph);
		}
		return graph;
	}
	
	public SwingViewer getViewer() {
		
		if(view == null) view = crearGrafo();
		return view;
	}
	
	public GrafoConPeso getGrafoPeso() 
	{
		if(grafoPeso == null) crearGrafo();
		return grafoPeso;
	}
		
	private SwingViewer crearGrafo()
	{
		System.setProperty("org.graphstream.ui", "swing");
		String style = CSSParser.parseStyle("/stylesheets/graphStyle.css",this.getClass());
		
		graph = new SingleGraph("Sistema de Transporte");
		graph.setAutoCreate(true);	//Crear nodos bajo demanda
		graph.setStrict(false);
		graph.setAttribute("ui.stylesheet",style);
		SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		CaminoDaoImpl caminoDao = new CaminoDaoImpl();	
		ParadaDaoImpl paradaDao = new ParadaDaoImpl();
		caminos = (ArrayList<Camino>) caminoDao.getAll();
		paradas = (ArrayList<Parada>) paradaDao.getAll();
		
		paradas.forEach(it -> 
		{
			Node node = graph.addNode(it.getId().toString());
			node.setAttribute("ui.label", it.getCalle());
		});
		
		caminos.forEach(it -> 
		{
			Edge edge = graph.addEdge(it.getId().toString(), it.getOrigen().getId().toString(),it.getDestino().getId().toString(), true);
			edge.setAttribute("ui.label", it.getDistancia() + " Km");
		});
		
		if(!paradas.isEmpty()) grafoPeso = new GrafoConPeso(paradas, caminos);
		
		graph.setAttribute("ui.quality");
		graph.setAttribute("ui.antialias");
		
		return viewer;	//Objecto que muestra el grafo. Se acopla al resto del GUI
		
	}
	
	public void pintarTrayecto(List<Camino> trayecto) 
	{
		List<String> ids = trayecto.stream().map(it -> it.getId().toString()).collect(Collectors.toList());
		graph.edges().forEach(it -> 
		{
			if(ids.contains(it.getId())) 
			{
				it.setAttribute("ui.class", "marked");
			} else if(it.hasAttribute("ui.class")) it.removeAttribute("ui.class");
		});
		
	}
	
	public void despintar() 
	{
		graph.edges().forEach(it -> 
		{
			if(it.hasAttribute("ui.class")) it.removeAttribute("ui.class");
		});
		graph.nodes().forEach(it -> 
		{
			if(it.hasAttribute("ui.class")) it.removeAttribute("ui.class");
		});
	}
	
	public void pintarNodo(String id) {
		graph.getNode(id).setAttribute("ui.class", "marked");
	}
	
	public void despintarNodo(String id) {
		if(graph.getNode(id).hasAttribute("ui.class"))
			graph.getNode(id).removeAttribute("ui.class");
	}
	
	public void pintarNodo(Parada p) {
		pintarNodo(p.getId().toString());
	}
	
	public void despintarNodo(Parada p) {
		despintarNodo(p.getId().toString());
	}
	
	public void pintarNodos(List<Parada> paradas) {
		paradas.forEach(p -> pintarNodo(p));
	}
	
	public void despintarNodos(List<Parada> paradas) {
		paradas.forEach(p -> despintarNodo(p));
	}
	
	public void pintarTrayectoByParadas(List<Parada> trayecto) {
		List<String> ids = trayecto.stream().map(t -> t.getId().toString()).collect(Collectors.toList());
		graph.nodes().forEach(n -> {
			if(ids.contains(n.getId().toString())) {
				pintarNodo(n.getId().toString());
			}else {
				despintarNodo(n.getId().toString());
			}
		});
	}
	
	public void despintarTodosLosNodos() {
		graph.nodes().forEach(n -> {
			despintarNodo(n.getId());
		});
	}

	public List<List<Parada>> caminos(Parada origen, Parada destino)
	{
		List<List<Parada>> caminos = grafoPeso.caminos(origen, destino);
		if(!deshabilitadas.isEmpty())
		return caminos.stream().filter(it -> Collections.disjoint(it,deshabilitadas))
		.collect(Collectors.toList());
		else return caminos;
	}
	
	public ArrayList<Parada> comprobarIncidencias() 
	{
		IncidenciaDaoImpl dao = new IncidenciaDaoImpl();
		ArrayList<Incidencia> incidencias = dao.allActivas();
		deshabilitadas = (ArrayList<Parada>) incidencias.stream()
				.filter(it -> it.sucedeAhora()).map(Incidencia::getParada)
				.collect(Collectors.toList());
		return deshabilitadas;
		
	}
	
}
