package died.izaguirre.haulet.tp.controladores;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import died.izaguirre.haulet.tp.dao.impl.IncidenciaDaoImpl;
import died.izaguirre.haulet.tp.estructuras.monticulo.Monticulo;
import died.izaguirre.haulet.tp.tablas.Incidencia;

public class ControladorIncidencia {
	
	private IncidenciaDaoImpl incidenciaDao;
	private ArrayList<Incidencia> incidencias = new ArrayList<>();
	
	public ControladorIncidencia() 
	{
		incidenciaDao = new IncidenciaDaoImpl();
	}
	
	public ArrayList<Incidencia> buscarIncidencias()
	{
		incidencias = (ArrayList<Incidencia>) incidenciaDao.getAll();
		actualizarIncidencias();
		return incidencias;		
	}
	
	public ArrayList<Incidencia> ordenarIncidencias()
	{
		if(incidencias.isEmpty()) buscarIncidencias();
		Monticulo<Incidencia> monticulo = new Monticulo<>(incidencias);
		ArrayList<Incidencia> aux = new ArrayList<>();
		int tamanio = monticulo.getTamanio();
		for(int i = 0; i<tamanio;i++) 
		{
			aux.add(monticulo.quitarRaiz());
		}
		return aux;
		
	}
	
	public boolean guardarIncidencia(Incidencia incidencia, boolean actualizar) 
	{
		try {
			if(actualizar) incidenciaDao.update(incidencia);
			else incidenciaDao.add(incidencia);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public void eliminarIncidencia(Incidencia incidencia) 
	{
		incidenciaDao.remove(incidencia);
	}
	
	private void actualizarIncidencias() 
	{
		incidencias.forEach(it -> 
		{
			if(it.getFechaFin() != null)
			if(!it.getEstaResuelto() && LocalDate.now().isAfter(it.getFechaFin())) { 
				it.setEstaResuelto(true);
				try {
					incidenciaDao.update(it);
				} catch (SQLException e) {
					it.setEstaResuelto(false);
				}
			}
		});
	}
	

}
