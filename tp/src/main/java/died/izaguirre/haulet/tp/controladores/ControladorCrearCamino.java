package died.izaguirre.haulet.tp.controladores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import died.izaguirre.haulet.tp.dao.impl.CaminoDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.LineaDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.PoseeDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.CaminoDao;
import died.izaguirre.haulet.tp.dao.interfaces.LineaDao;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.dao.interfaces.PoseeDao;
import died.izaguirre.haulet.tp.estructuras.grafo.GrafoConPeso;
import died.izaguirre.haulet.tp.gui.menuparadas.CrearCamino;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;
import died.izaguirre.haulet.tp.tablas.linea.Linea;

public class ControladorCrearCamino {

	private CrearCamino vista;
	private List<Parada> paradas;
	private ControladorGrafo cg = ControladorGrafo.getInstance();

	public ControladorCrearCamino(CrearCamino vista) {
		this.vista = vista;
		inicializar();
	}

	private void inicializar() {
		ParadaDao paradaAux = new ParadaDaoImpl();
		CaminoDao caminoAux = new CaminoDaoImpl();
		paradas = paradaAux.getAll();
		botonSalirListener(); // Para que cuando se presione salir se cierre la ventana
		caminoDistanciaSoloNumeros(); // Para que no se puedan ingresar letras
		cargarComboBoxes(); // Para seleccionar la parada origen y la destino
		cargarTabla(); // Carga los caminos en la tabla
		crearCaminoListener(); // Agrega funcionaldiad al boton crear
		botonEliminarListener(); // Para poder eliminar caminos
	}

	private void botonEliminarListener() {
		vista.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = vista.getTable().rowAtPoint(e.getPoint());
				int columna = vista.getTable().columnAtPoint(e.getPoint());
				if (fila < 0 || columna < 0)
					return;
				if (columna == 4) {
					Parada or = (Parada) vista.getTableModel().getValueAt(fila, 0);
					Parada dest = (Parada) vista.getTableModel().getValueAt(fila, 1);
					try {
						if (!existeLinaQuePasaPor(or, dest)) {
							eliminarDeBdd(or, dest);
							eliminarDeTabla(fila);
						} else {
							JOptionPane.showMessageDialog(vista,
									"No se puede eliminar este camino, verifique que no esté siendo utilizador por alguna línea.",
									"Error.", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException excp) {
						System.out.println("No se pudo eliminar el camino");
					}
				}
			}
		});
	}

	private Boolean existeLinaQuePasaPor(Parada or, Parada dest) {

		ControladorGrafo cg = ControladorGrafo.getInstance();
		GrafoConPeso gcp = cg.getGrafoPeso();

		// Camino que se desea eliminar
		Camino caminoEliminar = gcp.encontrarCamino(or, dest);

		LineaDao ldao = new LineaDaoImpl();
		List<Linea> todasLasLineas = ldao.getAll();

		Map<Linea, List<Camino>> caminosDeCadaLinea = new HashMap<Linea, List<Camino>>();

		PoseeDao pdao = new PoseeDaoImpl();

		// Cargo el trayecto de cada linea en el map
		for (Linea l : todasLasLineas) {
			// Recupero el trayecto de la linea
			List<Parada> trayectoByParada = pdao.paradasDeLinea(l).stream().map(p -> p.getParada())
					.collect(Collectors.toList());
			List<Camino> trayectoByCamino = gcp.toListCaminos(trayectoByParada);
			caminosDeCadaLinea.put(l, trayectoByCamino);
		}

		for (Linea l : todasLasLineas) {
			if (caminosDeCadaLinea.get(l).contains(caminoEliminar))
				return true;
		}

		return false;
	}

	private void eliminarDeTabla(Integer fila) {
		if (fila < 0)
			return;

		vista.getTableModel().removeRow(fila);
	}

	private void eliminarDeBdd(Parada orig, Parada destin) throws SQLException {
		CaminoDao aux = new CaminoDaoImpl();
		ParadaDao auxP = new ParadaDaoImpl();
		try {
			Camino eliminar = aux.find(orig.getId(), destin.getId());
			aux.remove(orig.getId(), destin.getId());
			cg.eliminarCamino(eliminar);
		} catch (SQLException e) {
			throw e;
		}
	}

	private void caminoDistanciaSoloNumeros() {
		vista.getCapacidadTxt().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		vista.getDistanciaTxt().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
	}

	private void cargarComboBoxes() {
		for (Parada p : paradas) {
			vista.getOrigenCBx().addItem(p);
			vista.getDestinoCBx().addItem(p);
		}
		if (!paradas.isEmpty()) {
			vista.getOrigenCBx().setSelectedIndex(0);
			vista.getDestinoCBx().setSelectedIndex(0);
		}
	}

	private void cargarTabla() {
		CaminoDao caux = new CaminoDaoImpl();
		List<Camino> caminos = caux.getAll();
		for (Camino c : caminos) {
			agregarCaminoTabla(c);
		}
	}

	private void agregarCaminoTabla(Camino c) {
		ImageIcon borrar = new ImageIcon(getClass().getResource("/delete.png"));
		Object[] nuevoCamino = new Object[5];
		nuevoCamino[0] = c.getOrigen();
		nuevoCamino[1] = c.getDestino();
		nuevoCamino[2] = c.getCapacidad();
		nuevoCamino[3] = c.getDistancia();
		nuevoCamino[4] = borrar;
		vista.getTableModel().addRow(nuevoCamino);
	}

	private void crearCaminoListener() {
		vista.getCrearButton().addActionListener(e -> {
			if (camposRellenados() && !origenDestinoIguales()) {
				ParadaDao paux = new ParadaDaoImpl();
				Camino c = new Camino();
				try {
					Parada origen = ((Parada) vista.getOrigenCBx().getSelectedItem());
					Parada destino = ((Parada) vista.getDestinoCBx().getSelectedItem());
					c.setOrigen(origen);
					c.setDestino(destino);
					c.setCapacidad(Integer.parseInt(vista.getCapacidadTxt().getText().toString()));
					c.setDistancia(Integer.parseInt(vista.getDistanciaTxt().getText().toString()));
					CaminoDao caux = new CaminoDaoImpl();
					caux.add(c);
					Camino nuevo = caux.find(c.getOrigen(), c.getDestino());
					cg.agregarCamino(nuevo);
					//TODO
					vista.getPanelPadre().getControlador().getAdyacentesDeUnaParada().get(origen).add(destino);
					agregarCaminoTabla(c);
				} catch (SQLException excp) {
					JOptionPane.showMessageDialog(vista,
							"No se pudo crear el camino, verifique que no exista ya uno entre las paradas elegidas.",
							"Error de validación.", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(vista,
						"No se pudo crear el camino, complete todos los campos y verifique que el origen y destino sean distintos.",
						"Error de validación.", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private Boolean camposRellenados() {
		return (!vista.getCapacidadTxt().getText().isEmpty() && !vista.getDistanciaTxt().getText().isEmpty());
	}

	private Boolean origenDestinoIguales() {
		return vista.getOrigenCBx().getSelectedItem().equals(vista.getDestinoCBx().getSelectedItem());
	}

	private void botonSalirListener() {
		vista.getSalirButton().addActionListener(e -> vista.dispose());
	}
}
