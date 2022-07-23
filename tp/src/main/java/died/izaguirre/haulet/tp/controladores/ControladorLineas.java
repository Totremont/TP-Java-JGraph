package died.izaguirre.haulet.tp.controladores;

import java.util.ArrayList;
import java.util.List;

import died.izaguirre.haulet.tp.dao.impl.LineaDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.LineaDao;
import died.izaguirre.haulet.tp.tablas.linea.Linea;

public class ControladorLineas {

	private static ControladorLineas gestor;
	private LineaDao lineaDao;
	private List<Linea> lineas;

	private ControladorLineas() {
		lineas = new ArrayList<Linea>();
		lineaDao = new LineaDaoImpl();		
	}

	public synchronized static ControladorLineas get() {
		if (gestor == null)
			gestor = new ControladorLineas();

		return gestor;

	}
	
	

}
