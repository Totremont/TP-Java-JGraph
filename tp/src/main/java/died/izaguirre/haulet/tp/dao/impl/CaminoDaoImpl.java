package died.izaguirre.haulet.tp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import died.izaguirre.haulet.tp.dao.DBConnection;
import died.izaguirre.haulet.tp.dao.interfaces.CaminoDao;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;

public class CaminoDaoImpl implements CaminoDao {

	private Connection con = null;

	public CaminoDaoImpl() {
		con = DBConnection.get();
	}

	@Override
	public void add(Camino t) throws SQLException {
		// TODO Auto-generated method stub
		try (PreparedStatement pstm = con.prepareStatement(
				"INSERT INTO tp.camino (origen,destino,capacidad,distancia) VALUES (?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstm.setInt(1, t.getOrigen().getId());
			pstm.setInt(2, t.getDestino().getId());
			pstm.setInt(3, t.getCapacidad());
			pstm.setInt(4, t.getDistancia());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void remove(Integer id_origen, Integer id_destino) throws SQLException {
		try (PreparedStatement pstm = con.prepareStatement("DELETE FROM tp.camino WHERE origen=? AND destino=?")) {
			pstm.setInt(1, id_origen);
			pstm.setInt(2, id_destino);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void remove(Parada origen, Parada destino) throws SQLException {
		// TODO Auto-generated method stub
		try {
			this.remove(origen.getId(), destino.getId());
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public Camino find(Integer id_origen, Integer id_destino) throws SQLException {
		// TODO Auto-generated method stub
		Camino auxCamino = null;
		try (PreparedStatement pstm = con.prepareStatement(
				"SELECT id_camino,origen,destino,capacidad,distancia FROM tp.camino WHERE origen=? AND destino=?")) {
			pstm.setInt(1, id_origen);
			pstm.setInt(2, id_destino);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) 
			{
				auxCamino = new Camino();
				ParadaDao aux = new ParadaDaoImpl();
				auxCamino.setId(rs.getInt(1));
				auxCamino.setOrigen(aux.find(rs.getInt(2)));
				auxCamino.setDestino(aux.find(rs.getInt(3)));
				auxCamino.setCapacidad(rs.getInt(4));
				auxCamino.setDistancia(rs.getInt(5));
			}
		} catch (SQLException e) {
			throw e;
		}

		return auxCamino;
	}
	
	@Override
	public Camino find(Parada origen, Parada destino) throws SQLException {
		return this.find(origen.getId(),destino.getId());
	}

	@Override
	public List<Camino> getAll() {
		// TODO Auto-generated method stub
		List<Camino> caminos = new ArrayList<Camino>();

		try (PreparedStatement pstm = con
				.prepareStatement("SELECT id_camino,origen,destino,capacidad,distancia FROM tp.camino")) {
			ResultSet rs = pstm.executeQuery();
			ParadaDao paradaDao = new ParadaDaoImpl();
			while (rs.next()) {
				Camino auxCamino = new Camino();

				auxCamino.setId(rs.getInt(1));
				auxCamino.setOrigen(paradaDao.find(rs.getInt(2)));
				auxCamino.setDestino(paradaDao.find(rs.getInt(3)));
				auxCamino.setCapacidad(rs.getInt(4));
				auxCamino.setDistancia(rs.getInt(5));

				caminos.add(auxCamino);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return caminos;
	}

}
