package died.izaguirre.haulet.tp.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import died.izaguirre.haulet.tp.tablas.Parada;
import died.izaguirre.haulet.tp.tablas.Posee;
import died.izaguirre.haulet.tp.tablas.linea.Linea;

public interface PoseeDao {

	public void add(Posee t) throws SQLException;

	public void remove(Integer id_linea, Integer id_parada);

	public void remove(Posee t);
	
	public void removeByLinea(Linea l);
	
	public void removeByLinea(Integer idLinea);

	public List<Posee> getAll();
	
	public List<Posee> paradasDeLinea(Integer idLinea);
	
	public List<Posee> paradasDeLinea(Linea l);
}
