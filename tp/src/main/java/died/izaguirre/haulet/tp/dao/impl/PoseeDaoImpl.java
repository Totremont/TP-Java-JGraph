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
import died.izaguirre.haulet.tp.dao.interfaces.PoseeDao;
import died.izaguirre.haulet.tp.tablas.Posee;
import died.izaguirre.haulet.tp.tablas.linea.Linea;

public class PoseeDaoImpl implements PoseeDao {

	private Connection con = null;

	public PoseeDaoImpl() {
		con = DBConnection.get();
	}

	@Override
	public void add(Posee t) throws SQLException {
		try (PreparedStatement pstm = con.prepareStatement("INSERT INTO tp.posee (id_parada,id_linea,orden_parada) VALUES (?,?,?)")) {
			pstm.setInt(1, t.getParada().getId());
			pstm.setInt(2, t.getLinea().getId());
			pstm.setInt(3, t.getOrdenParada());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void remove(Integer id_linea, Integer id_parada) {
		try (PreparedStatement pstm = con.prepareStatement("DELETE FROM tp.posee WHERE id_linea=? AND id_parada=?")) {
			pstm.setInt(1, id_linea);
			pstm.setInt(2, id_parada);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(Posee t) {
		this.remove(t.getLinea().getId(), t.getParada().getId());
	}

	@Override
	public List<Posee> getAll() {
		List<Posee> poseeList = new ArrayList<Posee>();

		try (PreparedStatement pstm = con.prepareStatement("SELECT id_parada,id_linea,orden_parada FROM tp.posee ORDER BY orden_parada")) {
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Posee aux = new Posee();
				LineaDao auxL = new LineaDaoImpl();
				ParadaDao auxP = new ParadaDaoImpl();
				aux.setParada(auxP.find(rs.getInt(1)));
				aux.setLinea(auxL.find(rs.getInt(2)));
				aux.setOrdenParada(rs.getInt(3));
				poseeList.add(aux);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return poseeList;
	}

	@Override
	public List<Posee> paradasDeLinea(Integer idLinea) {
		List<Posee> poseeList = new ArrayList<Posee>();

		try (PreparedStatement pstm = con.prepareStatement("SELECT id_parada,id_linea,orden_parada FROM tp.posee WHERE id_linea=? ORDER BY orden_parada")) {
			pstm.setInt(1, idLinea);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Posee aux = new Posee();
				LineaDao auxL = new LineaDaoImpl();
				ParadaDao auxP = new ParadaDaoImpl();
				aux.setParada(auxP.find(rs.getInt(1)));
				aux.setLinea(auxL.find(rs.getInt(2)));
				aux.setOrdenParada(rs.getInt(3));
				poseeList.add(aux);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return poseeList;
	}

	@Override
	public List<Posee> paradasDeLinea(Linea l) {
		return paradasDeLinea(l.getId());
	}

	@Override
	public void removeByLinea(Linea l) {
		removeByLinea(l.getId());
	}

	@Override
	public void removeByLinea(Integer idLinea) {
		try (PreparedStatement pstm = con.prepareStatement("DELETE FROM tp.posee WHERE id_linea=?")) {
			pstm.setInt(1, idLinea);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
