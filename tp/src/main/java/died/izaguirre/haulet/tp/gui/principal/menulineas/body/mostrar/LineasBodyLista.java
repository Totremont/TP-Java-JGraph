package died.izaguirre.haulet.tp.gui.principal.menulineas.body.mostrar;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class LineasBodyLista extends JPanel {
	
	private JPanel menuPadre;
	
	private List<Generador> tuplasLineas;
	
	/**
	 * Create the panel.
	 */
	
	public LineasBodyLista(JPanel menuPadre) {
		this();
		this.menuPadre = menuPadre;
	}
	
	public LineasBodyLista() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		tuplasLineas = new ArrayList<Generador>();
	}
	
	public void agregarLinea() {
		Generador aux = new Generador(this);
		tuplasLineas.add(aux);
		add(aux);
		validate();
	}
	
}
