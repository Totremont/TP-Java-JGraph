package died.izaguirre.haulet.tp.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.postgresql.util.PSQLException;

import died.izaguirre.haulet.tp.dao.impl.CaminoDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.LineaDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.CaminoDao;
import died.izaguirre.haulet.tp.dao.interfaces.LineaDao;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.estructuras.grafo.GrafoConPeso;
import died.izaguirre.haulet.tp.gui.menulineas.MenuVerLineas;
import died.izaguirre.haulet.tp.gui.menulineas.VerInfoLinea;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;
import died.izaguirre.haulet.tp.tablas.linea.Linea;
import died.izaguirre.haulet.tp.tablas.linea.LineaTipoEnum;

public class ControladorLineas {

	private LineaDao lineaDao;
	private ParadaDao paradaDao;
	private List<Linea> lineas;
	private List<Parada> paradas;
	private MenuVerLineas vista;
	private Map<String, List<Parada>> caminosNombre;

	private ControladorLineas() {
		lineaDao = new LineaDaoImpl();
		paradaDao = new ParadaDaoImpl();
		lineas = new ArrayList<Linea>();
		caminosNombre = new HashMap<String, List<Parada>>();
	}

	public ControladorLineas(MenuVerLineas vista) {
		this();
		this.vista = vista;
		inicializarList();
	}

	private void inicializarList() {
		tipoLineaListener(); // Para habilitar/deshabilitar los botones wifi y aire
		crearLineaListener(); // Para generar la linea y mostrarla cuando se crea
		crearTablaListener(); // Para detectar si elijo Info. - Ver camino - Eliminar en la tabla
		capParadosListener(); // Para que solo se puedan ingresar numeros en ese campo
		buscadorListener(); // Para poder filtrar tuplas en la tabla
		cargarTabla(); // Para cargar las lineas de la bdd en la tabla
		cargarParadas(); // Agrega Las paradas a los ComboBox Origen y Destino
		cargarTrayectos(); // Cargar combo box de trayectos
		orDestListener(); // Para que cuando se seleccione un origen o destino diferente se carguen de
							// nuevo los trayectos
		trayectosListener(); // Para pintar el grafo con el trayecto seleccionado
	}

	private void trayectosListener() {
		//TODO ESTO DA NULL POINTER EXCEPTION, NO SE POR QUE, CUANDO CAMBIO EL ORIGEN O DESTINO
		vista.getTrayectoCBx().addActionListener(e -> {
			despintarTrayectos();
			pintarTrayecto(vista.getTrayectoCBx().getSelectedItem().toString());
			});
	}

	private void despintarTrayectos() {
		CaminoDao auxc = new CaminoDaoImpl();
		ArrayList<Camino> mnos = new ArrayList<Camino>(auxc.getAll());
		Graph grafoVisual = ControladorGrafo.getGraph();
		for (Camino c : mnos)
			grafoVisual.getEdge(c.getId().toString()).removeAttribute("ui.class");
	}

	private void pintarTrayecto(String clave) {
		pintarTrayecto(caminosNombre.get(clave));
	}

	private void pintarTrayecto(List<Parada> trayecto) {
		ParadaDao auxp = new ParadaDaoImpl();
		CaminoDao auxc = new CaminoDaoImpl();
		ArrayList<Parada> pdas = new ArrayList<Parada>(auxp.getAll());
		ArrayList<Camino> mnos = new ArrayList<Camino>(auxc.getAll());
		GrafoConPeso grafo = new GrafoConPeso(pdas, mnos);

		List<Camino> trayectoCamino = grafo.toListCaminos(trayecto);
		Graph grafoVisual = ControladorGrafo.getGraph();

		for (Camino c : trayectoCamino) {
			grafoVisual.getEdge(c.getId().toString()).setAttribute("ui.class", "marked");
		}

	}

	private void orDestListener() {
		vista.getOrigenCBx().addActionListener(e -> cargarTrayectos());
		vista.getDestinoCBx().addActionListener(e -> cargarTrayectos());
	}

	private void cargarTrayectos() {

		if (origenDestinoIguales()) {
			vista.getTrayectoCBx().removeAllItems();
			return;
		}

		caminosNombre = new HashMap<String, List<Parada>>();

		vista.getTrayectoCBx().removeAllItems();

		CaminoDao cd = new CaminoDaoImpl();
		ParadaDao pd = new ParadaDaoImpl();
		List<Camino> aristas = cd.getAll();
		List<Parada> nodos = pd.getAll();
		GrafoConPeso aux = new GrafoConPeso(new ArrayList<Parada>(nodos), new ArrayList<Camino>(aristas));
		Parada or = (Parada) vista.getOrigenCBx().getSelectedItem();
		Parada dest = (Parada) vista.getDestinoCBx().getSelectedItem();
		List<List<Parada>> todosLosCaminos = aux.caminos(or, dest);
		for (int i = 0; i < todosLosCaminos.size(); i++) {
			String clave = "Trayecto" + i;
			caminosNombre.put(clave, todosLosCaminos.get(i));
			vista.getTrayectoCBx().addItem(clave);
		}
		
	}

	private void tipoLineaListener() {
		vista.getLineaTipoCBx().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getLineaTipoCBx().getSelectedItem().equals(LineaTipoEnum.Economica)) {
					vista.getAireCk().setEnabled(false);
					vista.getWifiCk().setEnabled(false);
				} else if (vista.getLineaTipoCBx().getSelectedItem().equals(LineaTipoEnum.Superior)) {
					vista.getAireCk().setEnabled(true);
					vista.getWifiCk().setEnabled(true);
				}
				vista.validate();
			}
		});
	}

	private void crearLineaListener() {
		vista.getCrearLineaButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Linea l;
				if (camposRellenados() && !origenDestinoIguales()) {
					if (vista.getLineaTipoCBx().getSelectedItem().equals(LineaTipoEnum.Economica.toString())) {
						try {
							l = crearLineaEconomica();
							lineaDao.add(l);
							agregarLineaTabla(l);

						} catch (SQLException excp) {
							System.out.println("No se pudo crear la linea");
						}
					} else
						try {
							l = crearLineaSuperior();
							lineaDao.add(l);
							agregarLineaTabla(l);
						} catch (SQLException excp) {
							System.out.println("No se pudo crear la linea");
						}

				} else
					System.out.println("No se puede crear la linea");

			}
		});
	}

	private Boolean origenDestinoIguales() {
		if (vista.getOrigenCBx().getSelectedItem().toString()
				.equals(vista.getDestinoCBx().getSelectedItem().toString()))
			return true;
		else
			return false;
	}

	private Boolean camposRellenados() {
		// Los unicos campos que el usuario puede no ingresar nada son en Nombre y
		// capacidad sentado
		if (vista.getNombreLineaText().getText().isEmpty() || vista.getCapSentadoText().getText().isEmpty()
				|| vista.getOrigenCBx().getSelectedItem() == null || vista.getDestinoCBx().getSelectedItem() == null
				|| vista.getTrayectoCBx().getSelectedItem().toString().isEmpty()) {
			return false;
		} else
			return true;
	}

	private Linea crearLineaEconomica() throws SQLException {
		Parada o = (Parada) vista.getOrigenCBx().getSelectedItem();
		Parada d = (Parada) vista.getDestinoCBx().getSelectedItem();

		Linea l = new Linea(vista.getLineaTipoCBx().getSelectedItem().toString(), vista.getNombreLineaText().getText(),
				vista.getColorCBx().getSelectedItem().toString(), Integer.parseInt(vista.getCapSentadoText().getText()),
				o, d);

		return l;

	}

	private Linea crearLineaSuperior() throws SQLException {

		Parada o = (Parada) vista.getOrigenCBx().getSelectedItem();
		Parada d = (Parada) vista.getDestinoCBx().getSelectedItem();

		Linea l = new Linea(vista.getLineaTipoCBx().getSelectedItem().toString(), vista.getNombreLineaText().getText(),
				vista.getColorCBx().getSelectedItem().toString(), Integer.parseInt(vista.getCapSentadoText().getText()),
				vista.getAireCk().isSelected(), vista.getWifiCk().isSelected(), o, d);

		return l;
	}

	private void agregarLineaTabla(Linea l) {

		Object[] nuevaLinea = new Object[6];
		// Falta agregar listener
		ImageIcon imgInfo = new ImageIcon(getClass().getResource("/help-circle.png"));
		ImageIcon imgVerCamino = new ImageIcon(getClass().getResource("/eye-outline.png"));
		ImageIcon imgDelete = new ImageIcon(getClass().getResource("/delete.png"));

		nuevaLinea[0] = l.getId();
		nuevaLinea[1] = l.getNombre();
		nuevaLinea[2] = l.getColor();
		nuevaLinea[3] = imgInfo;
		nuevaLinea[4] = imgVerCamino;
		nuevaLinea[5] = imgDelete;

		vista.getModel().addRow(nuevaLinea);
		vista.validate();

	}

	private void eliminarFilaTabla(int fila) {
		if (fila < 0)
			return;
		((DefaultTableModel) vista.getModel()).removeRow(fila);
	}

	private void cargarTabla() {
		lineas = lineaDao.getAll();
		for (Linea l : lineas)
			agregarLineaTabla(l);

	}

	private void cargarParadas() {

		paradas = paradaDao.getAll();
		for (Parada p : paradas) {
			vista.getOrigenCBx().addItem(p);
			vista.getDestinoCBx().addItem(p);
		}

	}

	private void crearTablaListener() {
		vista.getTable().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = vista.getTable().rowAtPoint(e.getPoint());
				int columna = vista.getTable().columnAtPoint(e.getPoint());

				if (columna == 3) {
					// Codigo para ver info
					Integer id = (Integer) ((DefaultTableModel) vista.getTable().getModel()).getValueAt(fila, 0);
					VerInfoLinea asd = new VerInfoLinea(vista, id);
					asd.setVisible(true);
				} else if (columna == 4) {
					// Codigo para ver camino
					
				} else if (columna == 5) {
					// Codigo para eliminar linea
					try {
						Integer id = (Integer) ((DefaultTableModel) vista.getTable().getModel()).getValueAt(fila, 0);
						lineaDao.remove(id);
						eliminarFilaTabla(fila);
						vista.validate();
					} catch (Exception exc) {
						System.out.println("Error al eliminar linea");
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
	}

	private void buscadorListener() {
		JTextField buscarButton = vista.getBuscarText();

		buscarButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrarTabla();
			}
		});

	}

	@SuppressWarnings("unchecked")
	private void filtrarTabla() {

		@SuppressWarnings("rawtypes")
		TableRowSorter sorter = vista.getTableSorter();
		sorter.setRowFilter(RowFilter.regexFilter(vista.getBuscarText().getText(), 1));
		vista.getTable().setRowSorter(sorter);
	}

	private void capParadosListener() {
		vista.getCapSentadoText().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
	}

	public void actualizarLineaDeTabla(Linea l) {
		for (int i = 0; i < vista.getModel().getRowCount(); i++) {
			Integer idModelo = (Integer) vista.getModel().getValueAt(i, 0);
			if (idModelo.equals(l.getId())) {
				vista.getModel().setValueAt(l.getNombre(), i, 1);
				vista.getModel().setValueAt(l.getColor(), i, 2);
			}
		}
	}
}
