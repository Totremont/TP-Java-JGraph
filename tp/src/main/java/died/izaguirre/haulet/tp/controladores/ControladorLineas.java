package died.izaguirre.haulet.tp.controladores;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;
import org.postgresql.util.PSQLException;

import died.izaguirre.haulet.tp.dao.impl.CaminoDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.LineaDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.PoseeDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.CaminoDao;
import died.izaguirre.haulet.tp.dao.interfaces.LineaDao;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.dao.interfaces.PoseeDao;
import died.izaguirre.haulet.tp.estructuras.grafo.GrafoConPeso;
import died.izaguirre.haulet.tp.gui.menulineas.MenuVerLineas;
import died.izaguirre.haulet.tp.gui.menulineas.VerInfoLinea;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;
import died.izaguirre.haulet.tp.tablas.Posee;
import died.izaguirre.haulet.tp.tablas.linea.Linea;
import died.izaguirre.haulet.tp.tablas.linea.LineaTipoEnum;

public class ControladorLineas {

	private LineaDao lineaDao = new LineaDaoImpl();
	private PoseeDao poseeDao = new PoseeDaoImpl();
	private ParadaDao paradaDao = new ParadaDaoImpl();
	private CaminoDao caminoDao = new CaminoDaoImpl();
	private List<Linea> lineas = new ArrayList<>();
	private ArrayList<Parada> paradas = new ArrayList<>();
	private ArrayList<Camino> caminos = new ArrayList<>();
	private MenuVerLineas vista;
	private Map<String, List<Camino>> caminosNombre;

	private ControladorGrafo controladorGrafo = ControladorGrafo.getInstance();
	private Graph grafoVisual = controladorGrafo.getGraph();
	private GrafoConPeso grafoPeso = controladorGrafo.getGrafoPeso();

	public ControladorLineas(MenuVerLineas vista) {
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

		vista.getTrayectoCBx().addActionListener(e -> {
			if (vista.getTrayectoCBx().isEnabled() && vista.getTrayectoCBx().getSelectedIndex() >= 0) {
				pintarTrayecto(vista.getTrayectoCBx().getSelectedItem().toString());
			}
		});
	}

//	private void despintarTrayectos() {
//		CaminoDao auxc = new CaminoDaoImpl();
//		ArrayList<Camino> mnos = new ArrayList<Camino>(auxc.getAll());
//		Graph grafoVisual = ControladorGrafo.getGraph();
//		for (Camino c : mnos)
//			grafoVisual.getEdge(c.getId().toString()).removeAttribute("ui.class");
//	}

	private void pintarTrayecto(String clave) {
		pintarTrayecto(caminosNombre.get(clave));
	}

	private void pintarTrayecto(List<Camino> trayecto) {
		List<String> ids = trayecto.stream().map(it -> it.getId().toString()).collect(Collectors.toList());
		grafoVisual.edges().forEach(it -> {
			if (ids.contains(it.getId())) {
				it.setAttribute("ui.class", "marked");
			} else if (it.hasAttribute("ui.class"))
				it.removeAttribute("ui.class");
		});

	}

	private void despintar() {
		grafoVisual.edges().forEach(it -> {
			if (it.hasAttribute("ui.class"))
				it.removeAttribute("ui.class");
		});
	}

	private void orDestListener() {
		vista.getOrigenCBx().addActionListener(e -> cargarTrayectos());
		vista.getDestinoCBx().addActionListener(e -> cargarTrayectos());
	}

	private void cargarTrayectos() {

		JComboBox<Parada> origenCombo = vista.getOrigenCBx();
		JComboBox<Parada> destinoCombo = vista.getDestinoCBx();

		if (origenCombo.getSelectedIndex() >= 0 && destinoCombo.getSelectedIndex() >= 0) {
			vista.getTrayectoCBx().removeAllItems();
			caminosNombre = new HashMap<String, List<Camino>>();
			GrafoConPeso aux = new GrafoConPeso(paradas, caminos);
			Parada or = (Parada) origenCombo.getSelectedItem();
			Parada dest = (Parada) destinoCombo.getSelectedItem();
			List<List<Parada>> todosLosCaminos = aux.caminos(or, dest);
			if (todosLosCaminos.isEmpty()) {
				vista.getTrayectoCBx().setEnabled(false);
				despintar();
			} else
				vista.getTrayectoCBx().setEnabled(true);

			for (int i = 0; i < todosLosCaminos.size(); i++) {
				String clave = "Trayecto: " + i;
				caminosNombre.put(clave, grafoPeso.toListCaminos(todosLosCaminos.get(i)));
				vista.getTrayectoCBx().addItem(clave);
			}
		}

	}

//	private List<Camino> convertirCamino(List<Parada> caminoParadas)	//Convierte camino de lista de paradas a lista de caminos
//	{
//		//Asumiendo que el camino está ordenado
//		ArrayList<Camino> caminoReal = new ArrayList<>();
//		Camino aux = null;
//		
//		for(int i = 0; i < caminoParadas.size(); i++) 
//		{
//			if((i + 1) >= caminoParadas.size()) break;
//			for(Camino c : caminos) 
//			{
//				if(aux != null) break;				
//				else 
//					if(c.getOrigen().equals(caminoParadas.get(i)) && c.getDestino().equals(caminoParadas.get(i + 1))) 
//				{
//					aux = c;
//					caminoReal.add(aux);
//				}
//			}
//			aux = null;
//		}
//		return caminoReal;
//		
//	}

	private void tipoLineaListener() {
		vista.getLineaTipoCBx().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getLineaTipoCBx().getSelectedItem().equals(LineaTipoEnum.Economica)) {
					vista.getAireCk().setEnabled(false);
					vista.getWifiCk().setEnabled(false);
					vista.getPorcentaje().setEnabled(true);
				} else if (vista.getLineaTipoCBx().getSelectedItem().equals(LineaTipoEnum.Superior)) {
					vista.getAireCk().setEnabled(true);
					vista.getWifiCk().setEnabled(true);
					vista.getPorcentaje().setEnabled(false);
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
					
					if(vista.getTrayectoCBx().getSelectedItem() == null) {
						Frame errorFrame = new JFrame("Error de validación");
						JOptionPane.showMessageDialog(errorFrame,
								"No se pudo crear la línea, no hay un trayecto disponible para el origen y destino especificado.",
								"Error de validación", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					List<Camino> trayectoPorAristas = caminosNombre.get(vista.getTrayectoCBx().getSelectedItem());
					
					if(trayectoPorAristas.isEmpty() || trayectoPorAristas == null) {
						Frame errorFrame = new JFrame("Error de validación");
						JOptionPane.showMessageDialog(errorFrame,
								"No se pudo crear la línea, no hay un trayecto disponible para el origen y destino especificado.",
								"Error de validación", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					// Obtengo las paradas
					List<Parada> trayectoPorParadas = new ArrayList<Parada>();
					for (Camino c : trayectoPorAristas) {
						if (!trayectoPorParadas.contains(c.getOrigen()))
							trayectoPorParadas.add(c.getOrigen());
						if (!trayectoPorParadas.contains(c.getDestino()))
							trayectoPorParadas.add(c.getDestino());
					}

					if (vista.getLineaTipoCBx().getSelectedItem().equals(LineaTipoEnum.Economica)) {
						try {
							l = crearLineaEconomica();
							lineaDao.add(l);
							agregarTrayectoLineaBdd(l, trayectoPorParadas);
							agregarLineaTabla(l);
							JFrame lineaCreada = new JFrame();
							JOptionPane.showMessageDialog(lineaCreada, "Línea creada exitosamente", "Información",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException excp) {
							System.out.println("No se pudo crear la linea economica");
							excp.printStackTrace();
						}
					} else
						try {
							l = crearLineaSuperior();
							lineaDao.add(l);
							agregarTrayectoLineaBdd(l, trayectoPorParadas);
							agregarLineaTabla(l);
							JFrame lineaCreada = new JFrame();
							JOptionPane.showMessageDialog(lineaCreada, "Línea creada exitosamente", "Información",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException excp) {
							System.out.println("No se pudo crear la linea superior");
						}

				} else {
//					System.out.println("No se puede crear la linea");
					JFrame errorFrame = new JFrame("Error de validación");
					JOptionPane.showMessageDialog(errorFrame,
							"No se pudo crear la línea, asegurese de completar todos los campos y que el origen y destino sean diferentes.",
							"Error de validación", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void agregarTrayectoLineaBdd(Linea l, List<Parada> trayecto) throws SQLException {

		PoseeDao pdao = new PoseeDaoImpl();
		int i = 0;
		for (Parada p : trayecto) {
			try {
				Posee posee = new Posee(p, l, i);
				i++;
				pdao.add(posee);
			} catch (SQLException excp) {
				throw excp;
			}
		}
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
		if (vista.getNombreLineaText().getText().isEmpty() || vista.getCapSentadoText().getValue() == null
				|| vista.getOrigenCBx().getSelectedItem() == null || vista.getDestinoCBx().getSelectedItem() == null) {
			return false;
		} else
			return true;
	}

	private Linea crearLineaEconomica() throws SQLException {
		Parada o = (Parada) vista.getOrigenCBx().getSelectedItem();
		Parada d = (Parada) vista.getDestinoCBx().getSelectedItem();

		Linea l = new Linea(vista.getLineaTipoCBx().getSelectedItem().toString(), vista.getNombreLineaText().getText(),
				vista.getColorCBx().getSelectedItem().toString(), ((Integer) vista.getCapSentadoText().getValue()),
				((Integer) vista.getPorcentaje().getValue()), o, d);

		return l;

	}

	private Linea crearLineaSuperior() throws SQLException {

		Parada o = (Parada) vista.getOrigenCBx().getSelectedItem();
		Parada d = (Parada) vista.getDestinoCBx().getSelectedItem();

		Linea l = new Linea(vista.getLineaTipoCBx().getSelectedItem().toString(), vista.getNombreLineaText().getText(),
				vista.getColorCBx().getSelectedItem().toString(), ((Integer) vista.getCapSentadoText().getValue()),
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

		vista.getTableModel().addRow(nuevaLinea);
		vista.validate();

	}

	private void eliminarFilaTabla(int fila) {
		if (fila < 0)
			return;
		((DefaultTableModel) vista.getTableModel()).removeRow(fila);
	}

	private void cargarTabla() {
		lineas = lineaDao.getAll();
		for (Linea l : lineas)
			agregarLineaTabla(l);

	}

	private void cargarParadas() {
		paradas = (ArrayList<Parada>) paradaDao.getAll();
		caminos = (ArrayList<Camino>) caminoDao.getAll();
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

				if (fila < 0 || columna < 0)
					return;

				if (columna == 3) {
					// Codigo para ver info
					Integer id = (Integer) ((DefaultTableModel) vista.getTable().getModel()).getValueAt(fila, 0);
					VerInfoLinea asd = new VerInfoLinea(vista, id);
					asd.setVisible(true);
				} else if (columna == 4) {
					// Codigo para ver camino
					Integer id = (Integer) ((DefaultTableModel) vista.getTable().getModel()).getValueAt(fila, 0);
					mostrarTrayecto(id);
				} else if (columna == 5) {
					// Codigo para eliminar linea
					try {
						Integer id = (Integer) ((DefaultTableModel) vista.getTable().getModel()).getValueAt(fila, 0);
						poseeDao.removeByLinea(id);
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
		sorter.setRowFilter(RowFilter.regexFilter("(?i)" + "^" + vista.getBuscarText().getText()));
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
		for (int i = 0; i < vista.getTableModel().getRowCount(); i++) {
			Integer idModelo = (Integer) vista.getTableModel().getValueAt(i, 0);
			if (idModelo.equals(l.getId())) {
				vista.getTableModel().setValueAt(l.getNombre(), i, 1);
				vista.getTableModel().setValueAt(l.getColor(), i, 2);
			}
		}
	}

	private void mostrarTrayecto(Integer idLinea) {
		PoseeDao pdao = new PoseeDaoImpl();
		List<Posee> paradasDeLinea = pdao.paradasDeLinea(idLinea);
		List<Parada> paradas = paradasDeLinea.stream().map(ps -> ps.getParada()).collect(Collectors.toList());
		pintarTrayecto(grafoPeso.toListCaminos(paradas));
	}

	public static List<Linea> getLineas() {
		LineaDaoImpl lineaDao = new LineaDaoImpl();
		return lineaDao.getAll();
	}
									//Este metodo contempla las paradas deshabilitadas y sugiere nuevo trayecto
	public static List<List<Parada>> getTrayectoLineas(List<Linea> lineas2) 
	{
		PoseeDaoImpl poseeDao = new PoseeDaoImpl();
		ControladorGrafo grafo = ControladorGrafo.getInstance();
		GrafoConPeso grafoPeso = grafo.getGrafoPeso();
		List<Parada> deshabilitadas= grafo.comprobarIncidencias();
		
		List<List<Parada>> aux = new ArrayList<>();
		lineas2.forEach(it -> {
			aux.add(poseeDao.paradasDeLinea(it.getId()).stream()
					.map(Posee::getParada).collect(Collectors.toList()));
		});	//Obtiene las paradas por las que pasa cada linea
		int indexIt = 0;
		if(!deshabilitadas.isEmpty())
		for(List<Parada> it : aux)
		{
			if(!Collections.disjoint(it, deshabilitadas)) 	//Si una de esas paradas esta deshabilitada...
			{
				List<List<Parada>> alternativos = grafo.caminos(it.get(0), it.get(it.size()-1));	//Se busca otro camino
				if(alternativos.isEmpty()) 
				{
					aux.set(indexIt, new ArrayList<>());
				}else 
				{
					//Busca camino mas corto
					List<List<Camino>> caminos = new ArrayList<>();
					alternativos.forEach(al -> caminos.add(grafoPeso.toListCaminos(al)));
					int distanciaMinima = grafoPeso.distanciaTotal(caminos.get(0));
					int posMinima = 0;
					if(caminos.size() > 1) for(int i = 1; i<caminos.size(); i++) 
					{
						int distancia = grafoPeso.distanciaTotal(caminos.get(i));
						if(distanciaMinima > distancia) { 
							distanciaMinima = distancia;
							posMinima = i;
						}
					}
					aux.set(indexIt,alternativos.get(posMinima));
				}
			}
			indexIt++;
		};
		return aux;

	}
}
