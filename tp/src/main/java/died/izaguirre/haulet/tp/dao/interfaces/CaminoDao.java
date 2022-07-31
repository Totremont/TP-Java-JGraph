package died.izaguirre.haulet.tp.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;

public interface CaminoDao {

	public void add(Camino t) throws SQLException;

	public void remove(Integer id_origen, Integer id_destino) throws SQLException;
	
	public void remove(Parada origen, Parada destino) throws SQLException;

	public Camino find(Integer id_origen, Integer id_destino) throws SQLException;
	
	public Camino find(Parada origen, Parada destino) throws SQLException;

	public List<Camino> getAll();
}
