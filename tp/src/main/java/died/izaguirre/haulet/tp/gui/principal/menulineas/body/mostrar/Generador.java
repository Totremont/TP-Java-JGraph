package died.izaguirre.haulet.tp.gui.principal.menulineas.body.mostrar;

import javax.swing.JPanel;

import died.izaguirre.haulet.tp.tablas.linea.Linea;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;

public class Generador extends JPanel {
	private JLabel nombreLinea;
	private JLabel verLinea;
	private JLabel eliminarLinea;
	
	private JPanel panelPadre;
	private Linea linea;
	

	/**
	 * Create the panel.
	 */
	
	/*
	 * Se encarga de generar la tupla correspondiente a cada linea
	 * que se va a insertar en la lista
	 */
	
	public Generador(Linea linea, JPanel panelPadre) {
		this(panelPadre);
		this.linea = linea;
	}
	
	public Generador(JPanel panelPadre) {
		this();
		this.panelPadre = panelPadre;
	}
	
	private Generador() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		nombreLinea = new JLabel("ASD");
		GridBagConstraints gbc_nombreLinea = new GridBagConstraints();
		gbc_nombreLinea.anchor = GridBagConstraints.WEST;
		gbc_nombreLinea.weightx = 1.0;
		gbc_nombreLinea.insets = new Insets(0, 20, 0, 20);
		gbc_nombreLinea.gridx = 0;
		gbc_nombreLinea.gridy = 0;
		add(nombreLinea, gbc_nombreLinea);
		
		verLinea = new JLabel("");
		verLinea.setIcon(new ImageIcon("C:\\Users\\tomsh\\eclipse-workspace\\tpdied\\tp\\src\\main\\resources\\eye-outline.png"));
		GridBagConstraints gbc_verLinea = new GridBagConstraints();
		gbc_verLinea.insets = new Insets(0, 0, 0, 5);
		gbc_verLinea.gridx = 1;
		gbc_verLinea.gridy = 0;
		add(verLinea, gbc_verLinea);
		
		eliminarLinea = new JLabel("");
		eliminarLinea.setIcon(new ImageIcon("C:\\Users\\tomsh\\eclipse-workspace\\tpdied\\tp\\src\\main\\resources\\minus.png"));
		GridBagConstraints gbc_eliminarLinea = new GridBagConstraints();
		gbc_eliminarLinea.gridx = 2;
		gbc_eliminarLinea.gridy = 0;
		add(eliminarLinea, gbc_eliminarLinea);

	}

}
