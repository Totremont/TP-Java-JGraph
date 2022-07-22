package died.izaguirre.haulet.tp.gui.menulineas.body.crear;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import died.izaguirre.haulet.tp.gui.menulineas.ColorLinea;
import died.izaguirre.haulet.tp.tablas.linea.LineaTipoEnum;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class CrearLineaBody extends JPanel {
	private JLabel tipoLineaLabel;
	private JComboBox comboBox;
	private JLabel colorLabel;
	private JComboBox comboBox_1;
	private JLabel nombreLabel;
	private JTextField textField;
	private JLabel capParadoLabel;
	private JTextField textField_1;
	private JLabel capSentadoLabel;
	private JTextField textField_2;
	private JCheckBox wifiCk;
	private JCheckBox aireCk;
	private JButton crearButton;
	
	private JPanel menuPadre;

	/**
	 * Create the panel.
	 */
	
	public CrearLineaBody(JPanel menuPadre) {
		this();
		this.menuPadre = menuPadre;
	}
	
	public CrearLineaBody() {
		setBorder(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 146, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		tipoLineaLabel = new JLabel("Tipo de línea");
		GridBagConstraints gbc_tipoLineaLabel = new GridBagConstraints();
		gbc_tipoLineaLabel.anchor = GridBagConstraints.EAST;
		gbc_tipoLineaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tipoLineaLabel.gridx = 1;
		gbc_tipoLineaLabel.gridy = 1;
		add(tipoLineaLabel, gbc_tipoLineaLabel);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(LineaTipoEnum.values()));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		add(comboBox, gbc_comboBox);
		
		colorLabel = new JLabel("Color");
		GridBagConstraints gbc_colorLabel = new GridBagConstraints();
		gbc_colorLabel.anchor = GridBagConstraints.EAST;
		gbc_colorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_colorLabel.gridx = 1;
		gbc_colorLabel.gridy = 2;
		add(colorLabel, gbc_colorLabel);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(ColorLinea.values()));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 2;
		add(comboBox_1, gbc_comboBox_1);
		
		nombreLabel = new JLabel("Nombre de la línea");
		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
		gbc_nombreLabel.anchor = GridBagConstraints.EAST;
		gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLabel.gridx = 1;
		gbc_nombreLabel.gridy = 3;
		add(nombreLabel, gbc_nombreLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		capParadoLabel = new JLabel("Cap. Parado");
		GridBagConstraints gbc_capParadoLabel = new GridBagConstraints();
		gbc_capParadoLabel.anchor = GridBagConstraints.EAST;
		gbc_capParadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_capParadoLabel.gridx = 1;
		gbc_capParadoLabel.gridy = 4;
		add(capParadoLabel, gbc_capParadoLabel);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 4;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		capSentadoLabel = new JLabel("Cap. Sentado");
		GridBagConstraints gbc_capSentadoLabel = new GridBagConstraints();
		gbc_capSentadoLabel.anchor = GridBagConstraints.EAST;
		gbc_capSentadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_capSentadoLabel.gridx = 1;
		gbc_capSentadoLabel.gridy = 5;
		add(capSentadoLabel, gbc_capSentadoLabel);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 5;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		wifiCk = new JCheckBox("Wifi");
		GridBagConstraints gbc_wifiCk = new GridBagConstraints();
		gbc_wifiCk.anchor = GridBagConstraints.WEST;
		gbc_wifiCk.insets = new Insets(0, 0, 5, 5);
		gbc_wifiCk.gridx = 2;
		gbc_wifiCk.gridy = 6;
		add(wifiCk, gbc_wifiCk);
		
		aireCk = new JCheckBox("Aire");
		GridBagConstraints gbc_aireCk = new GridBagConstraints();
		gbc_aireCk.anchor = GridBagConstraints.WEST;
		gbc_aireCk.insets = new Insets(0, 0, 5, 5);
		gbc_aireCk.gridx = 2;
		gbc_aireCk.gridy = 7;
		add(aireCk, gbc_aireCk);
		
		crearButton = new JButton("Crear");
		GridBagConstraints gbc_crearButton = new GridBagConstraints();
		gbc_crearButton.gridwidth = 2;
		gbc_crearButton.insets = new Insets(0, 0, 0, 5);
		gbc_crearButton.gridx = 1;
		gbc_crearButton.gridy = 8;
		add(crearButton, gbc_crearButton);

	}

}
