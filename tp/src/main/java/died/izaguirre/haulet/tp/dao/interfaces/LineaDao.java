package died.izaguirre.haulet.tp.dao.interfaces;

import java.sql.SQLException;

import died.izaguirre.haulet.tp.tablas.linea.Linea;

public interface LineaDao extends CRUD<Linea> {
	
	public void updateTipo(Integer id, String tipo) throws SQLException;
	public void updateCapSentado(Integer id, Integer cap) throws SQLException;
	public void updateCapParado(Integer id, Integer cap) throws SQLException;
	public void updateNombre(Integer id, String nombre) throws SQLException;
	public void updateColor(Integer id, String color) throws SQLException;
	public void updateAire(Integer id, Boolean tiene) throws SQLException;
	public void updateWifi(Integer id, Boolean tiene) throws SQLException;
	public void updateOrigen(Integer id, Integer id_origen) throws SQLException;
	public void updateDestino(Integer id, Integer id_destino) throws SQLException;
	public void setAireNull(Integer id) throws SQLException;
	public void setWifiNull(Integer id) throws SQLException;
	
}
