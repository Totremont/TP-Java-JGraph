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
import died.izaguirre.haulet.tp.tablas.linea.Linea;

public class BoletoDaoImpl implements BoletoDao {

	private Connection con = null;

	public BoletoDaoImpl() {
		con = DBConnection.get();
	}

	@Override
	public void add(Boleto t) {
		// TODO Auto-generated method stub
		try (PreparedStatement pstm = con.prepareStatement("INSERT INTO tp.boleto (id_linea,monto) VALUES (?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstm.setInt(1, t.getLinea().getId());
			pstm.setDouble(2, t.getMonto());
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			if(rs.next()) t.setId(rs.getInt(1));
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

	// Ver como implementar este metodo para un objeto boleto que no se conoce el ID
	@Override
	public void remove(Boleto t) {
		// TODO Auto-generated method stub
		this.remove(t.getId());
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

		Boleto auxBoleto = null;

		try (PreparedStatement pstm = con
				.prepareStatement("SELECT id_boleto,id_linea,monto FROM tp.boleto WHERE id_boleto=?")) {
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();
			if(rs.next()) 
			{
				auxBoleto = new Boleto();
				auxBoleto.setId(rs.getInt(1));
				auxBoleto.setLinea(this.getLinea(rs.getInt(2)));
				auxBoleto.setMonto(rs.getDouble(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return auxBoleto;

	}

	private Linea getLinea(Integer idLinea) {

		LineaDao ld = new LineaDaoImpl();

		return ld.find(idLinea);

	}

}
