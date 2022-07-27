package died.izaguirre.haulet.tp.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import died.izaguirre.haulet.tp.tablas.Posee;

public interface PoseeDao {

	public void add(Posee t) throws SQLException;

	public void remove(Integer id_linea, Integer id_parada);

	public void remove(Posee t);

	public List<Posee> getAll();
}
