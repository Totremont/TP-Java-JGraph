package died.izaguirre.haulet.tp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import died.izaguirre.haulet.tp.dao.DBConnection;
import died.izaguirre.haulet.tp.dao.interfaces.LineaDao;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.tablas.linea.Linea;
import died.izaguirre.haulet.tp.tablas.linea.LineaTipoEnum;

public class LineaDaoImpl implements LineaDao {

	private Connection con = null;

	public LineaDaoImpl() {
		con = DBConnection.get();
	}

	@Override
	public void add(Linea t) throws SQLException {
		// TODO Auto-generated method stub
		if (t.getTipo().equals(LineaTipoEnum.Economica.toString()))
			try {
				this.addEconomica(t);
			} catch (SQLException e) {
				throw e;
			}
		else
			try {
				this.addSuperior(t);
			} catch (SQLException e) {
				throw e;
			}
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
		this.remove(t.getId());
	}

	@Override
	public Linea find(Integer id) {
		// TODO Auto-generated method stub
		Linea auxLinea = null;
		try (PreparedStatement pstm = con.prepareStatement(
				"SELECT id_linea,tipo,cap_sentado,cap_parado,nombre,color,tiene_aire,tiene_wifi,origen,destino FROM tp.linea WHERE id_linea=?")) {
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				ParadaDao auxDaoP = new ParadaDaoImpl();
				auxLinea = new Linea();
				auxLinea.setId(rs.getInt(1));
				auxLinea.setTipo(rs.getString(2));
				auxLinea.setCapSentado(rs.getInt(3));
				auxLinea.setCapParado(rs.getInt(4));
				auxLinea.setNombre(rs.getString(5));
				auxLinea.setColor(rs.getString(6));
				auxLinea.setTieneAire(rs.getBoolean(7));
				auxLinea.setTieneWifi(rs.getBoolean(8));
				auxLinea.setOrigen(auxDaoP.findByNroParada(rs.getInt(9)));
				auxLinea.setDestino(auxDaoP.findByNroParada(rs.getInt(10)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return auxLinea;

	}

	@Override
	public List<Linea> getAll() {

		List<Linea> auxLinea = new ArrayList<Linea>();
		ParadaDao auxDaoParada = new ParadaDaoImpl();

		try (PreparedStatement pstm = con.prepareStatement(
				"SELECT id_linea,tipo,cap_sentado,cap_parado,nombre,color,tiene_aire,tiene_wifi,origen,destino FROM tp.linea")) {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Linea aux = new Linea();
				aux.setTipo(rs.getString(2));
				aux.setId(rs.getInt(1)); // ECO Y SUP
				aux.setCapSentado(rs.getInt(3)); // ECO Y SUP
				aux.setNombre(rs.getString(5)); // ECO Y SUP
				aux.setColor(rs.getString(6)); // ECO Y SUP
				if (aux.getTipo().equals(LineaTipoEnum.Superior.toString())) {
					aux.setTieneAire(rs.getBoolean(7)); // SUP
					aux.setTieneWifi(rs.getBoolean(8)); // SUP
				} else
					aux.setCapParado(rs.getInt(4));
				aux.setOrigen(auxDaoParada.findByNroParada(rs.getInt(9)));
				aux.setDestino(auxDaoParada.findByNroParada(rs.getInt(10)));
				auxLinea.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return auxLinea;

	}

	private void addEconomica(Linea t) throws SQLException {

		try (PreparedStatement pstm = con.prepareStatement(
				"INSERT INTO tp.linea (tipo,nombre,color,cap_sentado,cap_parado,origen,destino) VALUES (?,?,?,?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, t.getTipo());
			pstm.setString(2, t.getNombre());
			pstm.setString(3, t.getColor());
			pstm.setInt(4, t.getCapSentado());
			pstm.setInt(5, t.getCapParado());
			pstm.setInt(6, t.getOrigen().getNroParada());
			pstm.setInt(7, t.getDestino().getNroParada());
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next())
				t.setId(rs.getInt(1));
		} catch (SQLException e) {
			throw e;
		}

	}

	private void addSuperior(Linea t) throws SQLException {

		try (PreparedStatement pstm = con.prepareStatement(
				"INSERT INTO tp.linea (tipo,nombre,color,cap_sentado,tiene_aire,tiene_wifi,origen,destino) VALUES (?,?,?,?,?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, t.getTipo());
			pstm.setString(2, t.getNombre());
			pstm.setString(3, t.getColor());
			pstm.setInt(4, t.getCapSentado());
			pstm.setBoolean(5, t.getTieneAire());
			pstm.setBoolean(6, t.getTieneWifi());
			pstm.setInt(7, t.getOrigen().getNroParada());
			pstm.setInt(8, t.getDestino().getNroParada());
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next())
				t.setId(rs.getInt(1));
		} catch (SQLException e) {
			throw e;
		}

	}

}
