package died.izaguirre.haulet.tp.gui.menulineas.body.mostrar;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import died.izaguirre.haulet.tp.tablas.linea.Linea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LineasBody extends JPanel {
	
	private JPanel menuPadre;
	private JPanel buscador;
	private JPanel panel;
	
	/**
	 * Create the panel.
	 */
	
	public LineasBody(JPanel menuPadre) {
		this();
		this.menuPadre = menuPadre;
	}
	
	private LineasBody() {
		setLayout(new BorderLayout(0, 0));
		agregarBuscador();
	}
	
	private void agregarBuscador() {
		this.buscador = new LineasBodyBuscador(this);
		add(buscador,BorderLayout.NORTH);
		
		panel = new LineasBodyTable(this);
		add(panel, BorderLayout.CENTER);
	}
	
	public void agregarLinea(Linea l) {
		((LineasBodyTable) panel).agregarLinea(l);
	}
	
}
