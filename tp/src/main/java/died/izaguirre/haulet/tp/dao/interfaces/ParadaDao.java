package died.izaguirre.haulet.tp.dao.interfaces;

import died.izaguirre.haulet.tp.tablas.Parada;

public interface ParadaDao extends CRUD<Parada> {
	
	public Parada findByNroParada(Integer nro_parada);
	
}
