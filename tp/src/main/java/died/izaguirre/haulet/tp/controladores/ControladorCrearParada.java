package died.izaguirre.haulet.tp.controladores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.gui.menuparadas.CrearParada;
import died.izaguirre.haulet.tp.tablas.Parada;

public class ControladorCrearParada {

	private CrearParada vista;

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
						vista.getCalleCBx().getSelectedItem().toString());
				ParadaDao aux = new ParadaDaoImpl();
				try {
					aux.add(p);
				}catch(SQLException excp) {
					System.out.println("Ya existe una parada con este numero");
					return;
				}
				vista.getPanelPadre().getControlador().agregarParadaTabla(p);
				vista.dispose();
			}
		});
	}
	
	private Boolean camposRellenos() {
		if(vista.getNroParadaTxt().getText().isEmpty())
			return false;
		else
			return true;
	}
	
	private void botonCancelarListener() {
		vista.getCancelButton().addActionListener(e -> vista.dispose());
	}
}
