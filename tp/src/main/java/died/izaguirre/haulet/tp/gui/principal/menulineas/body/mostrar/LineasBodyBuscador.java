package died.izaguirre.haulet.tp.gui.principal.menulineas.body.mostrar;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import died.izaguirre.haulet.tp.tablas.linea.Linea;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LineasBodyBuscador extends JPanel {
	private JTextField textField;
	private JLabel buscarButton;

	private LineasBody menuPadre;
	private JLabel emptyLabel;
	private JLabel emptyLabel2;

	/**
	 * Create the panel.
	 */

	public LineasBodyBuscador(LineasBody menuPadre) {
		this();
		this.menuPadre = menuPadre;
	}

	public LineasBodyBuscador() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
		emptyLabel = new JLabel("");
		GridBagConstraints gbc_emptyLabel = new GridBagConstraints();
		gbc_emptyLabel.weightx = 0.25;
		gbc_emptyLabel.insets = new Insets(0, 0, 0, 5);
		gbc_emptyLabel.anchor = GridBagConstraints.EAST;
		gbc_emptyLabel.gridx = 3;
		gbc_emptyLabel.gridy = 0;
		add(emptyLabel, gbc_emptyLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.weightx = 0.25;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);

		buscarButton = new JLabel("");
		buscarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuPadre.agregarLinea(new Linea("Economica",5,"Linea 9","Rojo",3,false,false));
			}
		});
		buscarButton.setIcon(
				new ImageIcon("C:\\Users\\tomsh\\eclipse-workspace\\tpdied\\tp\\src\\main\\resources\\magnify.png"));
		GridBagConstraints gbc_buscarButton = new GridBagConstraints();
		gbc_buscarButton.insets = new Insets(0, 0, 0, 5);
		gbc_buscarButton.anchor = GridBagConstraints.WEST;
		gbc_buscarButton.weightx = 0.25;
		gbc_buscarButton.gridx = 5;
		gbc_buscarButton.gridy = 0;
		add(buscarButton, gbc_buscarButton);
		
		emptyLabel2 = new JLabel("");
		GridBagConstraints gbc_emptyLabel2 = new GridBagConstraints();
		gbc_emptyLabel2.weightx = 0.25;
		gbc_emptyLabel2.gridx = 6;
		gbc_emptyLabel2.gridy = 0;
		add(emptyLabel2, gbc_emptyLabel2);

	}

}
