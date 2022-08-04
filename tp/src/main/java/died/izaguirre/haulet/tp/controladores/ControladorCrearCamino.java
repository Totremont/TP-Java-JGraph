package died.izaguirre.haulet.tp.controladores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import died.izaguirre.haulet.tp.dao.impl.CaminoDaoImpl;
import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.CaminoDao;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.gui.menuparadas.CrearCamino;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;

public class ControladorCrearCamino {

	private CrearCamino vista;
	private List<Parada> paradas;

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
				if(columna == 4) {
					Parada or = (Parada) vista.getTableModel().getValueAt(fila, 0);
					Parada dest = (Parada) vista.getTableModel().getValueAt(fila, 1);
					try {
						eliminarDeBdd(or,dest);
						eliminarDeTabla(fila);
					}catch(SQLException excp) {
						System.out.println("No se pudo eliminar el camino");
					}
				}
			}
		});
	}
	
	private void eliminarDeTabla(Integer fila) {
		if(fila < 0)
			return;
		
		vista.getTableModel().removeRow(fila);
	}
	
	private void eliminarDeBdd(Parada orig, Parada destin) throws SQLException {
		CaminoDao aux = new CaminoDaoImpl();
		ParadaDao auxP = new ParadaDaoImpl();
		try {
			aux.remove(orig.getId(), destin.getId());
		}catch(SQLException e) {
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
		if(!paradas.isEmpty()) 
		{
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
			if(camposRellenados() && !origenDestinoIguales()) {
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
					agregarCaminoTabla(c);					
				}catch(SQLException excp) {
					System.out.println("No se pudo crear el camino, probablemente porque ya exista uno entre esas dos paradas");
				}
			}else {
				System.out.println("No se puede crear el camino.");
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