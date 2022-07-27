package died.izaguirre.haulet.tp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import died.izaguirre.haulet.tp.dao.DBConnection;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.tablas.Parada;

public class ParadaDaoImpl implements ParadaDao {

	private Connection con = null;

	public ParadaDaoImpl() {
		con = DBConnection.get();
	}

	@Override
	public void add(Parada t) {
		// TODO Auto-generated method stub
		try (PreparedStatement pstm = con.prepareStatement("INSET INTO tp.parada (nro_parada,calle) VALUES (?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstm.setInt(1, t.getNroParada());
			pstm.setString(2, t.getCalle());
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			rs.next();
			t.setId(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		try (PreparedStatement pstm = con.prepareStatement("DELETE FROM tp.parada WHERE id_parada=?")) {
			pstm.setInt(1, id);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(Parada t) {
		// TODO Auto-generated method stub
		this.remove(t.getId());
	}

	@Override
	public Parada find(Integer id) {
		// TODO Auto-generated method stub
		Parada aux = null;
		try (PreparedStatement pstm = con
				.prepareStatement("SELECT id_parada,nro_parada,calle FROM tp.parada WHERE id_parada=?")) {
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) 
			{
				aux = new Parada();
				aux.setId(rs.getInt(1));
				aux.setNroParada(rs.getInt(2));
				aux.setCalle(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aux;
	}

	@Override
	public List<Parada> getAll() {
		// TODO Auto-generated method stub
		List<Parada> paradas = new ArrayList<Parada>();

		try (PreparedStatement pstm = con.prepareStatement("SELECT id_parada,nro_parada,calle FROM tp.parada")) {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Parada aux = new Parada();
				aux.setId(rs.getInt(1));
				aux.setNroParada(rs.getInt(2));
				aux.setCalle(rs.getString(3));
				paradas.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return paradas;
	}

	@Override
	public Parada findByNroParada(Integer nro_parada) {
		
		Parada aux = null;

		try (PreparedStatement pstm = con
				.prepareStatement("SELECT id_parada,nro_parada,calle FROM tp.parada WHERE nro_parada=?")) {
			pstm.setInt(1, nro_parada);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) 
			{
				aux = new Parada();
				aux.setId(rs.getInt(1));
				aux.setNroParada(rs.getInt(2));
				aux.setCalle(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aux;
	}

	@Override
	public void removeByNroParada(Integer nro_parada) {
		// TODO Auto-generated method stub
		try (PreparedStatement pstm = con.prepareStatement("DELETE FROM tp.parada WHERE nro_parada=?")) {
			pstm.setInt(1, nro_parada);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
