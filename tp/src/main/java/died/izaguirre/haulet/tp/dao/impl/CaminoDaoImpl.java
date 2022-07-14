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
	public void add(Camino t) {
		// TODO Auto-generated method stub
		try (PreparedStatement pstm = con
				.prepareStatement("INSERT INTO tp.camino (origen,destino,capacidad,distancia) VALUES (?,?,?,?)")) {
			ParadaDao aux = new ParadaDaoImpl();
			pstm.setInt(1, aux.findByNroParada(t.getOrigen().getId()).getId());
			pstm.setInt(2, aux.findByNroParada(t.getDestino().getId()).getId());
			pstm.setInt(3, t.getCapacidad());
			pstm.setInt(4, t.getDistancia());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(Integer id_origen, Integer id_destino) {
		// TODO Auto-generated method stub
		try (PreparedStatement pstm = con.prepareStatement("DELETE FROM tp.camino WHERE origen=? AND destino=?")) {
			pstm.setInt(1, id_origen);
			pstm.setInt(2, id_destino);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(Parada origen, Parada destino) {
		ParadaDao aux = new ParadaDaoImpl();
		this.remove(aux.findByNroParada(origen.getNroParada()).getId(),
				aux.findByNroParada(destino.getNroParada()).getId());
	}

	@Override
	public Camino find(Integer id_origen, Integer id_destino) {
		// TODO Auto-generated method stub
		Camino auxCamino = new Camino();
		try (PreparedStatement pstm = con.prepareStatement(
				"SELECT origen,destino,capacidad,distancia FROM tp.camino WHERE origen=? AND destino=?")) {
			pstm.setInt(1, id_origen);
			pstm.setInt(2, id_destino);
			ResultSet rs = pstm.executeQuery();
			rs.next();
			ParadaDao aux = new ParadaDaoImpl();
			auxCamino.setOrigen(aux.findByNroParada(rs.getInt(1)));
			auxCamino.setDestino(aux.findByNroParada(rs.getInt(2)));
			auxCamino.setCapacidad(rs.getInt(3));
			auxCamino.setDistancia(rs.getInt(4));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return auxCamino;
	}

	@Override
	public List<Camino> getAll() {
		// TODO Auto-generated method stub
		List<Camino> caminos = new ArrayList<Camino>();

		try (PreparedStatement pstm = con
				.prepareStatement("SELECT origen,destino,capacidad,distancia FROM tp.camino")) {
			ResultSet rs = pstm.executeQuery();
			ParadaDao aux = new ParadaDaoImpl();
			while(rs.next()) {
				Camino auxCamino = new Camino();
				auxCamino.setOrigen(aux.findByNroParada(rs.getInt(1)));
				auxCamino.setDestino(aux.findByNroParada(rs.getInt(2)));
				auxCamino.setCapacidad(rs.getInt(3));
				auxCamino.setDistancia(rs.getInt(4));
				caminos.add(auxCamino);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return caminos;
	}

}
