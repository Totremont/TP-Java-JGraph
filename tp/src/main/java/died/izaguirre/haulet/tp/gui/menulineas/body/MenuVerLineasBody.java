package died.izaguirre.haulet.tp.gui.menulineas.body;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

import died.izaguirre.haulet.tp.gui.menulineas.body.crear.CrearLineaBody;
import died.izaguirre.haulet.tp.gui.menulineas.body.mostrar.LineasBody;

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
	
	private MenuVerLineasBody() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		crearLinea = new CrearLineaBody(this);
		crearLinea.setBorder(null);
		lineas = new LineasBody(this);
		add(crearLinea);
		add(lineas);
	}

}
