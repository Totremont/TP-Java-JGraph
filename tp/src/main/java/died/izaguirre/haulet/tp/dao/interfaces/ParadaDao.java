package died.izaguirre.haulet.tp.dao.interfaces;

import java.sql.SQLException;

import died.izaguirre.haulet.tp.tablas.Parada;

public interface ParadaDao extends CRUD<Parada> {
	
	public Parada findByNroParada(Integer nro_parada) throws SQLException;
	
	public void removeByNroParada(Integer nro_parada) throws SQLException;
	
}
