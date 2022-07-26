package died.izaguirre.haulet.tp.gestores.incidencias;

import java.util.ArrayList;
import java.util.List;

import died.izaguirre.haulet.tp.dao.impl.IncidenciaDaoImpl;
import died.izaguirre.haulet.tp.estructuras.monticulo.Monticulo;
import died.izaguirre.haulet.tp.tablas.Incidencia;

public class GestorIncidencias {
	
	private IncidenciaDaoImpl incidenciaDao;
	
	public GestorIncidencias() 
	{
		incidenciaDao = new IncidenciaDaoImpl();
	}
	
	public ArrayList<Incidencia> buscarIncidencias()
	{
		ArrayList<Incidencia> aux = (ArrayList<Incidencia>) incidenciaDao.getAll();
		Monticulo<Incidencia> monticulo = new Monticulo<>(aux);
		ArrayList<Incidencia> incidencias = new ArrayList<>();
		int tamanio = monticulo.getTamanio();
		for(int i = 0; i<tamanio;i++) 
		{
			incidencias.add(monticulo.quitarRaiz());
		}
		return incidencias;
		
	}

}
