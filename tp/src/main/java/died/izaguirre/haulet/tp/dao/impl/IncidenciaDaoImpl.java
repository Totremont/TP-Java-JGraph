package died.izaguirre.haulet.tp.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import died.izaguirre.haulet.tp.dao.DBConnection;
import died.izaguirre.haulet.tp.dao.interfaces.IncidenciaDao;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.tablas.Incidencia;

public class IncidenciaDaoImpl implements IncidenciaDao {

	private Connection con = null;

	public IncidenciaDaoImpl() {
		con = DBConnection.get();
	}

	@Override
	public void add(Incidencia t) throws SQLException {
		// TODO Auto-generated method stub
		try (PreparedStatement pstm = con.prepareStatement(
				"INSERT INTO tp.incidencia (id_parada,fecha_inicio,fecha_fin,esta_resuelto,descripcion,motivo VALUES (?,?,?,?,?,?))",PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstm.setInt(1, t.getParada().getId());
			pstm.setDate(2, Date.valueOf(t.getFechaInicio()));
			pstm.setDate(3, Date.valueOf(t.getFechaFin()));
			pstm.setBoolean(4, t.getEstaResuelto());
			pstm.setString(5,t.getDescripción());
			pstm.setString(6,t.getMotivo());
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			if(rs.next()) t.setId(rs.getInt(1));
			
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		try (PreparedStatement pstm = con.prepareStatement("DELETE FROM tp.incidencia WHERE id_incidencia=?")) {
			pstm.setInt(1, id);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(Incidencia t) {
		// TODO Auto-generated method stub
		this.remove(t.getId());
	}

	@Override
	public Incidencia find(Integer id) {

		Incidencia auxIncidencia = null;

		try (PreparedStatement pstm = con.prepareStatement(
				"SELECT id_incidencia,id_parada,fecha_inicio,fecha_fin,esta_resuelto,descripcion,motivo FROM tp.incidencia WHERE id_incidencia=?")) {
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) 
			{
				auxIncidencia = new Incidencia();
				auxIncidencia.setId(rs.getInt(1));
				ParadaDao aux = new ParadaDaoImpl();
				auxIncidencia.setParada(aux.findByNroParada(rs.getInt(2)));
				auxIncidencia.setFechaInicio(LocalDate.ofInstant(rs.getDate(3).toInstant(), ZoneId.systemDefault()));
				auxIncidencia.setFechaFin(LocalDate.ofInstant(rs.getDate(4).toInstant(), ZoneId.systemDefault()));
				auxIncidencia.setEstaResuelto(rs.getBoolean(5));
				auxIncidencia.setDescripción(rs.getString(6));
				auxIncidencia.setMotivo(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return auxIncidencia;
	}

	@Override
	public List<Incidencia> getAll() {
		// TODO Auto-generated method stub
		List<Incidencia> incidencias = new ArrayList<Incidencia>();

		try (PreparedStatement pstm = con
				.prepareStatement("SELECT id_incidencia,id_parada,fecha_inicio,fecha_fin,esta_resuelto,descripcion,motivo FROM tp.incidencia")) {
			ResultSet rs = pstm.executeQuery();
			ParadaDao aux = new ParadaDaoImpl();
			while(rs.next()) {
				Incidencia auxIncidencia = new Incidencia();
				auxIncidencia.setId(rs.getInt(1));
				auxIncidencia.setParada(aux.findByNroParada(rs.getInt(2)));
				auxIncidencia.setFechaInicio(LocalDate.ofInstant(rs.getDate(3).toInstant(), ZoneId.systemDefault()));
				auxIncidencia.setFechaFin(LocalDate.ofInstant(rs.getDate(4).toInstant(), ZoneId.systemDefault()));
				auxIncidencia.setEstaResuelto(rs.getBoolean(5));
				auxIncidencia.setDescripción(rs.getString(6));
				auxIncidencia.setMotivo(rs.getString(7));
				incidencias.add(auxIncidencia);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return incidencias;
	}

}
