package died.izaguirre.haulet.tp.controladores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.postgresql.util.PSQLException;

import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.gui.menuparadas.CrearParada;
import died.izaguirre.haulet.tp.tablas.Parada;

public class ControladorCrearParada {

	private CrearParada vista;
	private ControladorGrafo grafo = ControladorGrafo.getInstance();

	private ControladorCrearParada() {

	}

	public ControladorCrearParada(CrearParada vista) {
		this.vista = vista;
		inicializar();
	}

	private void inicializar() {
		restringirNroParada(); // Para que no se ingresen letras
		botonAgregarListener(); // Agrega la funcion al boton agregar
		botonCancelarListener(); // Para que cuando seleccione cancelar se cierre el jdialog
	}

	private void restringirNroParada() {
		vista.getNroParadaTxt().addKeyListener(new KeyAdapter() {
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

	private void botonAgregarListener() {

		vista.getOkButton().addActionListener(e -> {
			if (camposRellenos()) {
				Parada p = new Parada(Integer.parseInt(vista.getNroParadaTxt().getText()),
						vista.getCalleTxt().getText());
				ParadaDao aux = new ParadaDaoImpl();
				try {
					aux.add(p);
					Parada nueva = aux.findByNroParada(p.getNroParada());
					grafo.agregarParada(nueva);
				} catch (SQLException excp) {
					JOptionPane.showMessageDialog(vista, "Ya existe una parada con ese número.", "Error de validación.",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				vista.getPanelPadre().getControlador().agregarParadaTabla(p);
				vista.getPanelPadre().getControlador().agregarParadaAlMap(p, new ArrayList<Parada>());
				vista.dispose();
				JFrame creada = new JFrame();
				JOptionPane.showMessageDialog(creada, "Parada creada exitosamente.", "Información.",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(vista,
						"No se pudo crear la parada, asegúrese de haber completado todos los campos.",
						"Error de validación.", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private Boolean camposRellenos() {
		if (vista.getNroParadaTxt().getText().isEmpty() || vista.getCalleTxt().getText().isEmpty())
			return false;
		else
			return true;
	}

	private void botonCancelarListener() {
		vista.getCancelButton().addActionListener(e -> vista.dispose());
	}
}
