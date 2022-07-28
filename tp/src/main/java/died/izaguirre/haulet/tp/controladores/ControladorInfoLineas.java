package died.izaguirre.haulet.tp.controladores;

import java.util.List;

import died.izaguirre.haulet.tp.dao.impl.LineaDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.LineaDao;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.gui.menulineas.VerInfoLinea;
import died.izaguirre.haulet.tp.tablas.Parada;
import died.izaguirre.haulet.tp.tablas.linea.Linea;
import died.izaguirre.haulet.tp.tablas.linea.LineaColores;
import died.izaguirre.haulet.tp.tablas.linea.LineaTipoEnum;

public class ControladorInfoLineas {

	private VerInfoLinea vista;
	private Linea linea;
	private List<Parada> paradas;

	private ControladorInfoLineas() {
	}

	public ControladorInfoLineas(VerInfoLinea vista, Integer idLinea) {
		this.vista = vista;
		LineaDao aux = new LineaDaoImpl();
		this.linea = aux.find(idLinea);
		inicializar();
	}

	private void inicializar() {
		cargarParadas(); // Datos que van en los combo box
		cargarDatos(); // Datos que se muestran
	}

	private void cargarParadas() {
		ParadaDao aux = new ParadaDaoImpl();
		paradas = aux.getAll();

		for (Parada p : paradas) {
			vista.getOrigenCBx().addItem(p.getNroParada());
			vista.getDestinoCBx().addItem(p.getNroParada());
		}

	}

	private void cargarDatos() {
		cargarNombreLinea();
		cargarTipoLinea();
		cargarColorLinea();
		cargarCapSentado();
		cargarOrigenDestino(); // Falta implementar
	}
	
	private void cargarNombreLinea() {
		vista.getNombreLinea().setText(linea.getNombre());
	}
	
	private void cargarTipoLinea() {
		if (linea.getTipo() .equals(LineaTipoEnum.Economica.toString()))
			vista.getTipoCBx().setSelectedItem(LineaTipoEnum.Economica);
		else
			vista.getTipoCBx().setSelectedItem(LineaTipoEnum.Superior);
	}
	
	private void cargarColorLinea() {
		switch (linea.getColor()) {
		case "Rojo":
			vista.getColorCBx().setSelectedItem(LineaColores.Rojo);
			break;
		case "Azul":
			vista.getColorCBx().setSelectedItem(LineaColores.Azul);
			break;
		case "Verde":
			vista.getColorCBx().setSelectedItem(LineaColores.Verde);
			break;
		case "Amarillo":
			vista.getColorCBx().setSelectedItem(LineaColores.Amarillo);
			break;
		}
	}
	
	private void cargarCapSentado() {
		vista.getCapSentadoTxt().setText(linea.getCapSentado().toString());	
	}
	
	private void cargarAireWifi() {
		if (linea.getTipo() == "Superior") {
			vista.getWifiCk().setEnabled(linea.getTieneWifi());
			vista.getAireCk().setEnabled(linea.getTieneAire());
		}
	}
	
	private void cargarOrigenDestino() {
		
	}
}
