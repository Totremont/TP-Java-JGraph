package died.izaguirre.haulet.tp.controladores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.CRUD;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.estructuras.grafo.GrafoConPeso;
import died.izaguirre.haulet.tp.gui.menuparadas.CrearCamino;
import died.izaguirre.haulet.tp.gui.menuparadas.CrearParada;
import died.izaguirre.haulet.tp.gui.menuparadas.ParadasPanel;
import died.izaguirre.haulet.tp.tablas.Parada;

public class ControladorParadas {

	private static ParadaDao paradasDao = new ParadaDaoImpl();
	private ParadasPanel vista;
	private static List<Parada> paradas = new ArrayList<Parada>();
	private List<Parada> adyacentesDeLaUltimaSeleccionada;
	private List<Parada> paradasTabla = new ArrayList<Parada>();
	private Map<Parada, List<Parada>> adyacentesDeUnaParada = new HashMap<>();
	private Parada ultimaParadaSeleccionada;
	private ControladorGrafo grafo = ControladorGrafo.getInstance();

//	private ControladorParadas() {
//		paradasDao = new ParadaDaoImpl();
//		paradas = new ArrayList<Parada>();
//	}

	public ControladorParadas(ParadasPanel vista) {
		// this();
		this.vista = vista;
		adyacentesDeLaUltimaSeleccionada = new ArrayList<Parada>();
		inicializar();
	}

	private void inicializar() {
		resumenInicial();
		filtrarTablaListener(); // Para que el buscado de la tabla funciona (filtra en la columna calle)
		agregarButtonListener(); // Para que el boton Agregar cree paradas (por ahora es solo demostrativo)
		agregarTablaListener(); // Para que al dar click en el boton elimianr de la tabla se elimine la parada
		caminosListener(); // Para mostrar el jdialog de crear caminos
		verAdyacentesListener(); // Para ver las paradas adyacentes de la parada seleccionada
		cargarTabla();
	}

	private void resumenInicial() {
		vista.getCalleResumenTxt().setText("-");
		vista.getNumeroParadaResumenTxt().setText("Número#: ?");
		vista.getParadasAdyResumenTxt().setText("-");
		ultimaParadaSeleccionada = null;
	}

	private void verAdyacentesListener() {
		vista.getPintarAdyacentesResumenLbl().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pintarParadaSeleccionada();
			}
		});
	}

	private void pintarParadaSeleccionada() {
		if (ultimaParadaSeleccionada == null) {
			JFrame aviso = new JFrame();
			JOptionPane.showMessageDialog(aviso, "Debe seleccionar una parada de la tabla primero.", "Advertencia.",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (adyacentesDeLaUltimaSeleccionada.isEmpty()) {
			JFrame aviso = new JFrame();
			JOptionPane.showMessageDialog(aviso, "Esta parada no tiene adyacentes.", "Información.",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		ControladorGrafo cg = ControladorGrafo.getInstance();
		cg.pintarTrayectoByParadas(adyacentesDeLaUltimaSeleccionada);
		cg.pintarNodo(ultimaParadaSeleccionada);
	}

	private void caminosListener() {
		vista.getCaminosButton().addActionListener(e -> {
			JDialog camino = new CrearCamino(vista);
			camino.setVisible(true);

		});
	}

	public void agregarParadaTabla(Parada p) {

		Object[] nuevaParada = new Object[3];
		ImageIcon imgDelete = new ImageIcon(getClass().getResource("/delete.png"));

		nuevaParada[0] = p.getNroParada();
		nuevaParada[1] = p.getCalle();
		nuevaParada[2] = imgDelete;

		vista.getTableModel().addRow(nuevaParada);
		paradasTabla.add(p);
	}

	private void filtrarTablaListener() {

		vista.getBuscadorTxt().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				vista.getTableSorter()
						.setRowFilter(RowFilter.regexFilter("(?i)" + "^" + vista.getBuscadorTxt().getText()));
			}
		});
	}

	private void agregarButtonListener() {

		vista.getAgregarButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CrearParada menuCrear = new CrearParada(vista);
				menuCrear.setVisible(true);
			}
		});
	}

	private void agregarTablaListener() {

		vista.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = vista.getTable().rowAtPoint(e.getPoint());
				int columna = vista.getTable().columnAtPoint(e.getPoint());

				if (fila < 0 || columna < 0)
					return;

				if (columna == 2) { // Columna que contiene el boton eliminar
					Integer nroParada = (Integer) ((DefaultTableModel) vista.getTable().getModel()).getValueAt(fila, 0);
					ParadaDao aux = new ParadaDaoImpl();
					try {
						Parada eliminar = paradasTabla.stream().filter(p -> p.getNroParada().equals(nroParada))
								.findFirst().get();
						aux.removeByNroParada(nroParada);
						grafo.eliminarParada(eliminar);
						eliminarParada(fila); // Metodo que elimina una fila de la tabla (debe encargarse de eliminar
												// la parada de la BDD tambien
						paradasTabla.remove(eliminar);
						adyacentesDeUnaParada.remove(eliminar);
					} catch (SQLException excp) {
						JFrame error = new JFrame();
						JOptionPane.showMessageDialog(error,
								"No se pudo eliminar la parada, verifique no esté siendo utilizada por alguna línea o incidencia.",
								"Error.", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					actualizarResumen(fila, columna);
				}
			}
		});
	}

	private void eliminarParada(int fila) {

		if (fila < 0)
			return;
//		int nroParada = vista.getTableModel().getValueAt(fila, 0); // Esto obtiene el nroParada, para buscar la parada en la BBD
		vista.getTableModel().removeRow(fila);
	}

	private void cargarTabla() {
		ParadaDao aux = new ParadaDaoImpl();
		paradas = aux.getAll();
		ControladorGrafo cg = ControladorGrafo.getInstance();
		
		for (Parada p : paradas) {
			agregarParadaTabla(p);
			paradasTabla.add(p);
			GrafoConPeso gcp = cg.getGrafoPeso();
			List<Parada> listAux = gcp.adyacentesDe(p);
			adyacentesDeUnaParada.put(p, listAux);
		}
	}

	public static ArrayList<Parada> buscarParadas() {
		paradas = paradasDao.getAll();
		return (ArrayList<Parada>) paradas;
	}

	private void actualizarResumen(Integer fila, Integer columna) {
		Integer nroParada = (Integer) vista.getTable().getValueAt(fila, 0);
//		ControladorGrafo cg = ControladorGrafo.getInstance();
//		CRUD dao = new ParadaDaoImpl();
//		try {
//			Parada parada = ((ParadaDao) dao).findByNroParada(nroParada);
//			ultimaParadaSeleccionada = parada;
//
//			adyacentesDeLaUltimaSeleccionada = cg.getGrafoPeso().adyacentesDe(parada).stream()
//					.collect(Collectors.toList());
//			Integer adyacentesCount = adyacentesDeLaUltimaSeleccionada.size();
//
//			vista.getCalleResumenTxt().setText(parada.getCalle());
//			vista.getParadasAdyResumenTxt().setText(adyacentesCount.toString());
//			vista.getNumeroParadaResumenTxt().setText("Número# " + parada.getNroParada().toString());
//		} catch (SQLException e) {
//			System.out.println("Problema al encontrar el numero de parada en la bdd.");
//		}		
		Parada p = paradasTabla.stream().filter(x -> x.getNroParada().equals(nroParada)).findFirst().get();
		
		ultimaParadaSeleccionada = p;
		adyacentesDeLaUltimaSeleccionada = adyacentesDeUnaParada.get(p);
		vista.getCalleResumenTxt().setText(p.getCalle());
		vista.getParadasAdyResumenTxt().setText(""+adyacentesDeLaUltimaSeleccionada.size());
		vista.getNumeroParadaResumenTxt().setText("Número# " + p.getNroParada().toString());

	}

}
