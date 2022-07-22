package died.izaguirre.haulet.tp.gui.principal.menulineas.head;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JSeparator;

public class MenuVerLineasHead extends JPanel {
	private JLabel tituloDelMenu;
	
	private JPanel menuPadre;
	private JSeparator separator;

	/**
	 * Create the panel.
	 */
	
	public MenuVerLineasHead(JPanel menuPadre) {
		this();
		this.menuPadre = menuPadre;
	}
	
	public MenuVerLineasHead() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 12, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		tituloDelMenu = new JLabel("GESTOR DE LÍNEAS");
		tituloDelMenu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_tituloDelMenu = new GridBagConstraints();
		gbc_tituloDelMenu.insets = new Insets(0, 0, 5, 5);
		gbc_tituloDelMenu.gridwidth = 5;
		gbc_tituloDelMenu.weightx = 1.0;
		gbc_tituloDelMenu.gridx = 0;
		gbc_tituloDelMenu.gridy = 4;
		add(tituloDelMenu, gbc_tituloDelMenu);
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 0, 5);
		gbc_separator.gridwidth = 4;
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 5;
		add(separator, gbc_separator);

	}

}
