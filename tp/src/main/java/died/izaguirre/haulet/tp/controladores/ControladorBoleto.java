package died.izaguirre.haulet.tp.controladores;

import java.sql.SQLException;

import died.izaguirre.haulet.tp.dao.impl.BoletoDaoImpl;
import died.izaguirre.haulet.tp.tablas.Boleto;

public class ControladorBoleto {
	
	private BoletoDaoImpl boletoDao = new BoletoDaoImpl();
	
	public boolean insertarBoleto(Boleto boleto) 
	{
		try {
			boletoDao.add(boleto);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
