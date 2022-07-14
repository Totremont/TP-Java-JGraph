package died.izaguirre.haulet.tp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import died.izaguirre.haulet.tp.dao.DBConnection;
import died.izaguirre.haulet.tp.dao.interfaces.LineaDao;
import died.izaguirre.haulet.tp.tablas.Linea;

public class LineaDaoImpl implements LineaDao {

	private Connection con = null;

	public LineaDaoImpl() {
		con = DBConnection.get();
	}

	@Override
	public void add(Linea t) {
		// TODO Auto-generated method stub
		if (t.getTipo() == "Linea Economica")
			this.addEconomica(t);
		else
			this.addSuperior(t);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		try (PreparedStatement pstm = con.prepareStatement("DELETE FROM tp.linea WHERE id_linea=?")) {
			pstm.setInt(1, id);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void remove(Linea t) {
		// TODO Auto-generated method stub
		this.remove(this.find(t.getNombre(), t.getColor()));
	}

	@Override
	public Linea find(Integer id) {
		// TODO Auto-generated method stub
		Linea auxLinea = new Linea();
		try (PreparedStatement pstm = con.prepareStatement(
				"SELECT id_linea,tipo,cap_sentado,cap_parado,nombre,color,tiene_aire,tiene_wifi FROM tp.linea WHERE id_linea=?")) {
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			rs.next();
			auxLinea.setId(rs.getInt(1));
			auxLinea.setTipo(rs.getString(2));
			auxLinea.setCapSentado(rs.getInt(3));
			auxLinea.setCapParado(rs.getInt(4));
			auxLinea.setNombre(rs.getString(5));
			auxLinea.setColor(rs.getString(6));
			auxLinea.setTieneAire(rs.getBoolean(7));
			auxLinea.setTieneWifi(rs.getBoolean(8));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return auxLinea;

	}

	@Override
	public List<Linea> getAll() {

		List<Linea> auxLinea = new ArrayList<Linea>();

		try (PreparedStatement pstm = con.prepareStatement(
				"SELECT id_linea,tipo,cap_sentado,cap_parado,nombre,color,tiene_aire,tiene_wifi FROM tp.linea")) {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Linea aux = new Linea();
				aux.setTipo(rs.getString(2));
				// PUEDE DAR ERROR POR SETEAR VALORES NULL, DICHO CASO DIFERENCIAR LOS SETTER
				// POR TIPO LINEA
				aux.setId(rs.getInt(1)); // ECO Y SUP
				aux.setCapSentado(rs.getInt(3)); // ECO Y SUP
				aux.setCapParado(rs.getInt(4)); // ECO
				aux.setNombre(rs.getString(5)); // ECO Y SUP
				aux.setColor(rs.getString(6)); // ECO Y SUP
				aux.setTieneAire(rs.getBoolean(7)); // SUP
				aux.setTieneWifi(rs.getBoolean(8)); // SUP
				auxLinea.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return auxLinea;

	}

	private void addEconomica(Linea t) {

		try (PreparedStatement pstm = con.prepareStatement(
				"INSERT INTO tp.linea (tipo,nombre,color,cap_sentado,cap_parado) VALUES (?,?,?,?,?)")) {
			pstm.setString(1, t.getTipo());
			pstm.setString(2, t.getNombre());
			pstm.setString(3, t.getColor());
			pstm.setInt(4, t.getCapSentado());
			pstm.setInt(5, t.getCapParado());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void addSuperior(Linea t) {

		try (PreparedStatement pstm = con.prepareStatement(
				"INSERT INTO tp.linea (tipo,nombre,color,cap_sentado,tiene_aire,tiene_wifi) VALUES (?,?,?,?,?,?)")) {
			pstm.setString(2, t.getNombre());
			pstm.setString(3, t.getColor());
			pstm.setInt(4, t.getCapSentado());
			pstm.setBoolean(5, t.getTieneAire());
			pstm.setBoolean(6, t.getTieneWifi());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Linea find(String nombre, String color) {
		// TODO Auto-generated method stub
		Linea auxLinea = new Linea();

		try (PreparedStatement pstm = con.prepareStatement(
				"SELECT id_linea,tipo,cap_sentado,cap_parado,nombre,color,tiene_aire,tiene_wifi FROM tp.linea")) {
			ResultSet rs = pstm.executeQuery();
			rs.next();
			auxLinea.setId(rs.getInt(1));
			auxLinea.setTipo(rs.getString(2));
			auxLinea.setCapSentado(rs.getInt(3));
			auxLinea.setCapParado(rs.getInt(4));
			auxLinea.setNombre(rs.getString(5));
			auxLinea.setColor(rs.getString(6));
			auxLinea.setTieneAire(rs.getBoolean(7));
			auxLinea.setTieneWifi(rs.getBoolean(8));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return auxLinea;

	}

}
