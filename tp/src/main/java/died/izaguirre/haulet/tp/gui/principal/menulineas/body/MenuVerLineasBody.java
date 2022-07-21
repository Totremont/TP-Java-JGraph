package died.izaguirre.haulet.tp.gui.principal.menulineas.body;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

import died.izaguirre.haulet.tp.gui.principal.menulineas.body.crear.CrearLineaBody;
import died.izaguirre.haulet.tp.gui.principal.menulineas.body.mostrar.LineasBody;

import java.awt.Color;

public class MenuVerLineasBody extends JPanel {

	private JPanel menuPadre;
	
	private JPanel crearLinea;
	private JPanel lineas;
	
	/**
	 * Create the panel.
	 */
	
	public MenuVerLineasBody(JPanel menuPadre){
		this();
		this.menuPadre = menuPadre;
	}
	
	public MenuVerLineasBody() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		crearLinea = new CrearLineaBody(this);
		crearLinea.setBorder(new LineBorder(new Color(0, 0, 0)));
		lineas = new LineasBody();
		add(crearLinea);
		add(lineas);
	}

}
