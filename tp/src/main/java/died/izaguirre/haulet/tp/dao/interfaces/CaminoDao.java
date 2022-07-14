package died.izaguirre.haulet.tp.dao.interfaces;

import java.util.List;

import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;

public interface CaminoDao {

	public void add(Camino t);

	public void remove(Integer id_origen, Integer id_destino);
	
	public void remove(Parada origen, Parada destino);

	public Camino find(Integer id_origen, Integer id_destino);

	public List<Camino> getAll();
}
