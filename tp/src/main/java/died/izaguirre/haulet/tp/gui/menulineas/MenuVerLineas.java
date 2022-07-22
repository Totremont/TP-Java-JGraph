package died.izaguirre.haulet.tp.gui.menulineas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;

import died.izaguirre.haulet.tp.gui.menulineas.body.MenuVerLineasBody;
import died.izaguirre.haulet.tp.gui.menulineas.head.MenuVerLineasHead;

import java.awt.Color;
import javax.swing.JSeparator;

public class MenuVerLineas extends JPanel {
	
	private JPanel header;
	private JFrame ventanaPadre;
	private JPanel body;
	
	/**
	 * Create the panel.
	 */
	
	public MenuVerLineas(JFrame ventanaPadre) {
		this();
		this.ventanaPadre = ventanaPadre;
	}
	
	public MenuVerLineas() {
		setLayout(new BorderLayout(0, 0));
		agregarHead();
		agregarBody();
	}
	
	private void agregarHead() {
		header = new MenuVerLineasHead(this);
		header.setBorder(null);
		add(header,BorderLayout.NORTH);
	}
	
	private void agregarBody() {
		this.body = new MenuVerLineasBody(this);
		add(body,BorderLayout.CENTER);
	}

}
