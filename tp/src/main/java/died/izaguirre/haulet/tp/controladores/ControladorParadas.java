package died.izaguirre.haulet.tp.controladores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;

import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.gui.menuparadas.CrearCamino;
import died.izaguirre.haulet.tp.gui.menuparadas.CrearParada;
import died.izaguirre.haulet.tp.gui.menuparadas.ParadasPanel;
import died.izaguirre.haulet.tp.tablas.Parada;

public class ControladorParadas {

	private static ParadaDao paradasDao = new ParadaDaoImpl();
	private ParadasPanel vista;
	private static List<Parada> paradas = new ArrayList<Parada>();

//	private ControladorParadas() {
//		paradasDao = new ParadaDaoImpl();
//		paradas = new ArrayList<Parada>();
//	}

	public ControladorParadas(ParadasPanel vista) {
		//this();
		this.vista = vista;
		inicializar();
	}

	private void inicializar() {
		filtrarTablaListener(); // Para que el buscado de la tabla funciona (filtra en la columna calle)
		agregarButtonListener(); // Para que el boton Agregar cree paradas (por ahora es solo demostrativo)
		agregarTablaListener(); // Para que al dar click en el boton elimianr de la tabla se elimine la parada
		caminosListener(); // Para mostrar el jdialog de crear caminos
		cargarTabla();
	}
	
	private void caminosListener() {
		vista.getCaminosButton().addActionListener(e -> {
		JDialog camino = new CrearCamino();
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
	}

	private void filtrarTablaListener() {

		vista.getBuscadorTxt().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				vista.getTableSorter().setRowFilter(RowFilter.regexFilter(vista.getBuscadorTxt().getText(), 1));
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

				if (columna == 2) { // Columna que contiene el boton eliminar
					Integer nroParada = (Integer) ((DefaultTableModel) vista.getTable().getModel()).getValueAt(fila, 0);
					ParadaDao aux = new ParadaDaoImpl();
					try {
					aux.removeByNroParada(nroParada);
					eliminarParada(fila); // Metodo que elimina una fila de la tabla (debe encargarse de eliminar
											// la parada de la BDD tambien
					} catch (SQLException excp) {
						System.out.println("No se pudo eliminar la parada, probablemente porque una linea la esta usando");
					}
				}
			}
		});
	}

	private void eliminarParada(int fila) {
		
		if(fila < 0)
			return;
//		int nroParada = vista.getTableModel().getValueAt(fila, 0); // Esto obtiene el nroParada, para buscar la parada en la BBD
		vista.getTableModel().removeRow(fila);
	}
	
	private void cargarTabla() {
		ParadaDao aux = new ParadaDaoImpl();
		paradas = aux.getAll();
		
		for(Parada p : paradas)
			agregarParadaTabla(p);
	}
	
	public static ArrayList<Parada> buscarParadas() 
	{
		paradas = paradasDao.getAll();
		return (ArrayList<Parada>) paradas;
	}
}
