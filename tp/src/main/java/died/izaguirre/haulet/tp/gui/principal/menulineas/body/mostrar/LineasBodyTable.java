package died.izaguirre.haulet.tp.gui.principal.menulineas.body.mostrar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import died.izaguirre.haulet.tp.tablas.linea.Linea;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class LineasBodyTable extends JPanel {
	
	private LineasBody menuPadre;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;

	
	/**
	 * Create the panel.
	 */
	
	public LineasBodyTable(LineasBody menuPadre) {
		this();
		this.menuPadre = menuPadre;
	}
	
	public LineasBodyTable() {
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nombre", "Color", "Tipo", "Info.", "Ver camino", "Eliminar"
				}
			);
		
		table.setModel(model);
		scrollPane.setViewportView(table);

	}
	
	public void agregarLinea(Linea l) {
		
		JLabel info = new JLabel("");
		info.setIcon(new ImageIcon("C:\\Users\\tomsh\\eclipse-workspace\\tpdied\\tp\\src\\main\\resources\\help-circle.png"));
		JLabel camino = new JLabel("");
		camino.setIcon(new ImageIcon("C:\\Users\\tomsh\\eclipse-workspace\\tpdied\\tp\\src\\main\\resources\\eye-outline.png"));
		JLabel eliminar = new JLabel("");
		eliminar.setIcon(new ImageIcon("C:\\Users\\tomsh\\eclipse-workspace\\tpdied\\tp\\src\\main\\resources\\delete.png"));
		
		Object[] nuevaLinea = new Object[6];
		nuevaLinea[0] = l.getNombre();
		nuevaLinea[1] = l.getColor();
		nuevaLinea[2] = l.getTipo();
		nuevaLinea[3] = info;
		nuevaLinea[4] = camino;
		nuevaLinea[5] = eliminar;
		
		model.addRow(nuevaLinea);
		validate();
		
	}
	
//	private void cargarColumnas() {
//		model.addColumn("Nombre");
//		model.addColumn("Color");
//		model.addColumn("Tipo");
//		model.addColumn("Info");
//		model.addColumn("Ver camino");
//		model.addColumn("Eliminar");
//	}
	
}
