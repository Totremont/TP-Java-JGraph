package died.izaguirre.haulet.tp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import died.izaguirre.haulet.tp.dao.DBConnection;
import died.izaguirre.haulet.tp.dao.interfaces.BoletoDao;
import died.izaguirre.haulet.tp.dao.interfaces.LineaDao;
import died.izaguirre.haulet.tp.tablas.*;

public class BoletoDaoImpl implements BoletoDao {

	private Connection con = null;

	public BoletoDaoImpl() {
		con = DBConnection.get();
	}

	@Override
	public void add(Boleto t) {
		// TODO Auto-generated method stub
		try (PreparedStatement pstm = con.prepareStatement("INSERT INTO tp.boleto (id_linea,monto) VALUES (?,?)")) {
			LineaDao aux = new LineaDaoImpl();
			pstm.setInt(1, aux.find(t.getLinea().getNombre(), t.getLinea().getColor()).getId());
			pstm.setDouble(2, t.getMonto());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		try (PreparedStatement pstm = con.prepareStatement("DELETE FROM tp.boleto WHERE id_boleto=?")) {
			pstm.setInt(1, id);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Boleto> getAll() {
		// TODO Auto-generated method stub
		List<Boleto> boletos = new ArrayList<Boleto>();

		try (PreparedStatement pstm = con.prepareStatement("SELECT id_boleto,id_linea,monto FROM tp.boleto")) {
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Boleto auxBoleto = new Boleto();
				auxBoleto.setId(rs.getInt(1));
				auxBoleto.setLinea(this.getLinea(rs.getInt(2)));
				auxBoleto.setMonto(rs.getDouble(3));
				boletos.add(auxBoleto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return boletos;
	}

	@Override
	public Boleto find(Integer id) {

		Boleto auxBoleto = new Boleto();

		try (PreparedStatement pstm = con
				.prepareStatement("SELECT id_boleto,id_linea,monto FROM tp.boleto WHERE id_boleto=?")) {
			pstm.setInt(1, id);
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				auxBoleto.setId(rs.getInt(1));
				auxBoleto.setLinea(this.getLinea(rs.getInt(2)));
				auxBoleto.setMonto(rs.getDouble(3));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return auxBoleto;
		
	}

	private Linea getLinea(Integer idLinea) {

		LineaDao ld = new LineaDaoImpl();

		return ld.find(idLinea);

	}

}
