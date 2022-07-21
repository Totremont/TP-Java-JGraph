package died.izaguirre.haulet.tp.gui.principal.menulineas.body.mostrar;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class LineasBody extends JPanel {
	
	private JPanel menuPadre;
	private JPanel buscador;
	private LineasBodyLista listaLineas;
	private JScrollPane scrollPane;
	
	/**
	 * Create the panel.
	 */
	
	public LineasBody(JPanel menuPadre) {
		this();
		this.menuPadre = menuPadre;
	}
	
	public LineasBody() {
		setLayout(new BorderLayout(0, 0));
		agregarBuscador();
	}
	
	private void agregarBuscador() {
		this.buscador = new LineasBodyBuscador(this);
		add(buscador,BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		
		listaLineas = new LineasBodyLista(this);
		listaLineas.setBorder(null);
		scrollPane.setViewportView(listaLineas);
	}
	
	public void agregarLinea() {
		listaLineas.agregarLinea();
	}
	
}
