package died.izaguirre.haulet.tp.controladores;

import java.util.ArrayList;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;

import died.izaguirre.haulet.tp.dao.impl.CaminoDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.estructuras.grafo.GrafoConPeso;
import died.izaguirre.haulet.tp.gui.menuparadas.CrearParada;
import died.izaguirre.haulet.tp.gui.utilities.CSSParser;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;

public class ControladorGrafo {
	
	public static Graph graph = null;
	public static SwingViewer view = null;
	
	private ControladorGrafo() {

	}
	
	public static Graph getGraph() {
		if(graph == null) {
			ControladorGrafo ctrl = new ControladorGrafo();
			view = ctrl.crearGrafo();
		}
		
		return graph;
	}
	
	public static SwingViewer getViewer() {
		if(view == null) {
			ControladorGrafo ctrl = new ControladorGrafo();
			view = ctrl.crearGrafo();
		}
		
		return view;
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
		ArrayList<Camino> caminos = (ArrayList<Camino>) caminoDao.getAll();
		
		caminos.forEach(it -> 
		{
			Edge edge = graph.addEdge(it.getId().toString(), it.getOrigen().getCalle(), it.getDestino().getCalle(), true);
			edge.setAttribute("ui.label", it.getDistancia() + " Km");
		});
		
		
		for(Node node : graph)
		{
			node.setAttribute("ui.label", node.getId());
			
		}
		
		graph.setAttribute("ui.quality");
		graph.setAttribute("ui.antialias");
		
		return viewer;	//Objecto que muestra el grafo. Se acopla al resto del GUI
		
	}
	

}
