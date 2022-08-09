package died.izaguirre.haulet.tp.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

import died.izaguirre.haulet.tp.dao.impl.CaminoDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.LineaDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.PoseeDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.CRUD;
import died.izaguirre.haulet.tp.dao.interfaces.CaminoDao;
import died.izaguirre.haulet.tp.dao.interfaces.LineaDao;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.dao.interfaces.PoseeDao;
import died.izaguirre.haulet.tp.estructuras.grafo.GrafoConPeso;
import died.izaguirre.haulet.tp.gui.menulineas.VerInfoLinea;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;
import died.izaguirre.haulet.tp.tablas.Posee;
import died.izaguirre.haulet.tp.tablas.linea.Linea;
import died.izaguirre.haulet.tp.tablas.linea.LineaColores;
import died.izaguirre.haulet.tp.tablas.linea.LineaTipoEnum;

public class ControladorInfoLineas {

	private VerInfoLinea vista;
	private Linea linea;
	private List<Parada> paradas;
	private List<Camino> caminos;
	private GrafoConPeso grafo;
	Map<String, List<Parada>> trayectosByParada;

	private ControladorInfoLineas() {
		trayectosByParada = new HashMap<String, List<Parada>>();
		ParadaDao pdao = new ParadaDaoImpl();
		CaminoDao cdao = new CaminoDaoImpl();
		paradas = pdao.getAll();
		caminos = cdao.getAll();
		grafo = new GrafoConPeso(new ArrayList<Parada>(paradas), new ArrayList<Camino>(caminos));
	}

	public ControladorInfoLineas(VerInfoLinea vista, Integer idLinea) {
		this();
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

		for (Parada p : paradas) {
			vista.getOrigenCBx().addItem(p);
			vista.getDestinoCBx().addItem(p);
		}

	}

	private void cargarDatos() {
		cargarNombreLinea();
		cargarTipoLinea();
		cargarColorLinea();
		cargarCapSentado();
		cargarOrigenDestino();
		tipoLineaListener();
		capParado();
		cargarTrayecto();
		botoneraListener(); // Agrega funciones a modificar,guardar y salir
		orDestListener(); // Para que cambie el trayecto cuando se cambia el origen y destino
	}

	private void orDestListener() {
		vista.getOrigenCBx().addActionListener(e -> {
			if (origenDestinoIguales()) {
				vista.getTrayectoCBx().setEnabled(false);
				vista.getTrayectoCBx().removeAllItems();
				trayectosByParada.clear();
			} else {
				vista.getTrayectoCBx().setEnabled(true);
				cargarTrayecto();
			}
		});
		vista.getDestinoCBx().addActionListener(e -> {
			if (origenDestinoIguales()) {
				vista.getTrayectoCBx().setEnabled(false);
				vista.getTrayectoCBx().removeAllItems();
				trayectosByParada.clear();
			} else {
				vista.getTrayectoCBx().setEnabled(true);
				cargarTrayecto();
			}
		});
	}

	private void cargarTrayecto() {

		trayectosByParada.clear();
		vista.getTrayectoCBx().removeAllItems();

		Parada or = (Parada) vista.getOrigenCBx().getSelectedItem();
		Parada dest = (Parada) vista.getDestinoCBx().getSelectedItem();

		List<List<Parada>> allCaminosByParada = grafo.caminos(or, dest);

		for (int i = 0; i < allCaminosByParada.size(); i++) {
			String clave = "Trayecto : " + i;
			trayectosByParada.put(clave, allCaminosByParada.get(i));
			vista.getTrayectoCBx().addItem(clave);
		}
		
		seleccionarTrayecto(); // Para que este seleccionado por defecto el que usa.
	}
	
	private void seleccionarTrayecto() {
		PoseeDao dao = new PoseeDaoImpl();
		List<Parada> paradas =dao.paradasDeLinea(linea).stream().map(p -> p.getParada()).collect(Collectors.toList());
//		List<List<Parada>> paradasDelMap = (List<List<Parada>>) trayectosByParada.values();
//		
//		for(List<Parada> i : paradasDelMap) {
//			if(i.containsAll(paradas)) {
//				vista.getTrayectoCBx().setSelectedItem(trayectosByParada.);
//			}
//		}
		
		trayectosByParada.keySet().forEach(k -> {
			if(trayectosByParada.get(k).containsAll(paradas)) {
				vista.getTrayectoCBx().setSelectedItem(k);
			}
		});
	}

	private void capParado() {
		if(linea.getCapSentado() != 0)
			vista.getCapParado().setValue((Integer) (linea.getCapParado()/linea.getCapSentado()));
		else
			vista.getCapParado().setValue(caminos);
	}

	private void cargarNombreLinea() {
		vista.getNombreLinea().setText(linea.getNombre());
	}

	private void cargarTipoLinea() {
		if (linea.getTipo().equals(LineaTipoEnum.Economica.toString()))
			vista.getTipoCBx().setSelectedItem(LineaTipoEnum.Economica);
		else {
			vista.getTipoCBx().setSelectedItem(LineaTipoEnum.Superior);
			cargarAireWifi();
		}
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
		vista.getWifiCk().setSelected(linea.getTieneWifi());
		vista.getAireCk().setSelected(linea.getTieneAire());
	}

	private void cargarOrigenDestino() {
		vista.getOrigenCBx().setSelectedItem(linea.getOrigen());
		vista.getDestinoCBx().setSelectedItem(linea.getDestino());
	}

	private void botoneraListener() {
		vista.getSalirButton().addActionListener(e -> {
			vista.dispose();
		});

		vista.getModificarButton().addActionListener(e -> modoModificacion());
		vista.getGuardarButton().addActionListener(e -> guardarCambios());
	}

	private void modoModificacion() {
		onoffTodo(true);
		if (vista.getTipoCBx().getSelectedItem().equals(LineaTipoEnum.Economica)) {
			vista.getAireCk().setEnabled(false);
			vista.getWifiCk().setEnabled(false);
		}
		vista.getModificarButton().setEnabled(false);
		vista.getGuardarButton().setEnabled(true);
	}

	private void onoffTodo(Boolean estado) {
		vista.getNombreLinea().setEditable(estado);
		vista.getTipoCBx().setEnabled(estado);
		vista.getColorCBx().setEnabled(estado);
		vista.getCapSentadoTxt().setEditable(estado);
		vista.getWifiCk().setEnabled(estado);
		vista.getAireCk().setEnabled(estado);
		vista.getOrigenCBx().setEnabled(estado);
		vista.getDestinoCBx().setEnabled(estado);
		vista.getTrayectoCBx().setEnabled(estado);
	}

	private void tipoLineaListener() {
		vista.getTipoCBx().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getTipoCBx().getSelectedItem().equals(LineaTipoEnum.Economica)) {
					vista.getAireCk().setEnabled(false);
					vista.getWifiCk().setEnabled(false);
					vista.getAireCk().setSelected(false);
					vista.getWifiCk().setSelected(false);
				} else if (vista.getTipoCBx().getSelectedItem().equals(LineaTipoEnum.Superior)) {
					vista.getAireCk().setEnabled(true);
					vista.getWifiCk().setEnabled(true);
				}
				vista.validate();
			}
		});
	}

	private void guardarCambios() {
		if (!origenDestinoIguales()) {
			try {
				Linea lineaActualizada = actualizarLinea(linea.getId());
				actualizarBdd(lineaActualizada);
				actualizarTabla(lineaActualizada);
				vista.dispose();
			} catch (SQLException excp) {
				System.out.println("No se pudo actualizar la linea");
			}
		} else
			System.out.println("No se puede modificar la linea");
	}

	private Boolean origenDestinoIguales() {
		if (vista.getOrigenCBx().getSelectedItem().toString()
				.equals(vista.getDestinoCBx().getSelectedItem().toString()))
			return true;
		else
			return false;
	}

	private Linea actualizarLinea(Integer id) throws SQLException {
//		final String nombre = vista.getNombreLinea().getText();
//		final String tipoLinea = vista.getTipoCBx().getSelectedItem().toString();
//		final String color = vista.getColorCBx().getSelectedItem().toString();
//		final Integer capSentado = Integer.parseInt(vista.getCapSentadoTxt().getText());
//		final Boolean aire = vista.getAireCk().isSelected();
//		final Boolean wifi = vista.getWifiCk().isSelected();
//		final Integer origen = (Integer) ((Camino) vista.getOrigenCBx().getSelectedItem()).getId();
//		final Integer destino = Integer.parseInt(vista.getDestinoCBx().getSelectedItem().toString());
//
//		ParadaDao daoParada = new ParadaDaoImpl();
//
//		Linea l = new Linea();
//		l.setId(id);
//		l.setNombre(nombre);
//		l.setTipo(tipoLinea);
//		l.setColor(color);
//		l.setTieneAire(aire);
//		l.setTieneWifi(wifi);
//		try {
//		l.setOrigen(daoParada.findByNroParada(origen));
//		l.setDestino(daoParada.findByNroParada(destino));
//		} catch(SQLException excp) {
//			throw excp;
//		}
//		l.setCapSentado(capSentado);
//		if (tipoLinea.equals(LineaTipoEnum.Economica.toString())) {
//			int capParado = ((Double) (capSentado * 0.40)).intValue();
//			l.setCapParado(capParado);
//		} else {
//			l.setCapParado(0);
//		}

		if (vista.getTipoCBx().getSelectedItem().equals(LineaTipoEnum.Economica)) {
			Linea l = new Linea(vista.getTipoCBx().getSelectedItem().toString(), vista.getNombreLinea().getText(),
					vista.getColorCBx().getSelectedItem().toString(),
					Integer.parseInt(vista.getCapSentadoTxt().getText()), 
					((Integer) vista.getCapParado().getValue()), 
					(Parada) vista.getOrigenCBx().getSelectedItem(),
					(Parada) vista.getDestinoCBx().getSelectedItem());
			l.setId(id);
			return l;
		}else {
			Linea l = new Linea(vista.getTipoCBx().getSelectedItem().toString(), vista.getNombreLinea().getText(),
					vista.getColorCBx().getSelectedItem().toString(),
					Integer.parseInt(vista.getCapSentadoTxt().getText()), vista.getAireCk().isSelected(), vista.getWifiCk().isSelected(), (Parada) vista.getOrigenCBx().getSelectedItem(),
					(Parada) vista.getDestinoCBx().getSelectedItem());
			l.setId(id);
			return l;
		}
	}

	private void actualizarBdd(Linea l) {

		final Integer id = l.getId();

		LineaDao dao = new LineaDaoImpl();

		// Economica
		try {
			dao.updateNombre(id, l.getNombre());
			dao.updateTipo(id, l.getTipo());
			dao.updateCapSentado(id, l.getCapSentado());
			dao.updateCapParado(id, l.getCapParado());
			dao.updateColor(id, l.getColor());
			dao.updateAire(id, l.getTieneAire());
			dao.updateWifi(id, l.getTieneWifi());
			dao.updateOrigen(id, l.getOrigen().getNroParada());
			dao.updateDestino(id, l.getDestino().getNroParada());
			actualizarTrayecto(l);
		} catch (SQLException e) {
//				e.printStackTrace();
			System.out.println("No se pudo actualizar la linea " + id);
		}
	}

	private void actualizarTrayecto(Linea l) {
		PoseeDao pdao = new PoseeDaoImpl();
		pdao.removeByLinea(l);
		int i = 0;
		List<Parada> trayecto = trayectosByParada.get(vista.getTrayectoCBx().getSelectedItem());
		for (Parada p : trayecto) {
			Posee aux = new Posee(p, l,i);
			i++;
			try {
				pdao.add(aux);
			} catch (SQLException e) {
//				e.printStackTrace();
				System.out.println("Problema al actualizar el trayecto.");
			}
		}
	}

	private void actualizarTabla(Linea l) {
		vista.getMenu().getControlador().actualizarLineaDeTabla(l);
	}
}
