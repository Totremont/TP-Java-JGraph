package died.izaguirre.haulet.tp.dao.interfaces;

import died.izaguirre.haulet.tp.tablas.Linea;

public interface LineaDao extends CRUD<Linea> {
	
	public Linea find(String nombre, String color);
	
}
