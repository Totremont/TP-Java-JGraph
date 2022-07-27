package died.izaguirre.haulet.tp.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

public interface CRUD<T> {
	
	public void add(T t) throws SQLException;
	
	public void remove(Integer id);
	
	public void remove(T t);
	
	public T find(Integer id);
	
	public List<T> getAll();
	
}
