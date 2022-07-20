package died.izaguirre.haulet.tp.gui.principal.menulineas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import died.izaguirre.haulet.tp.tablas.linea.LineaTipoEnum;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class MenuLineas extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public MenuLineas() {
		setPreferredSize(new Dimension(250, 400));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 96, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel volverButton = new JLabel("");
		volverButton.setIcon(new ImageIcon(
				"C:\\Users\\tomsh\\eclipse-workspace\\tpdied\\tp\\src\\main\\resources\\chevron-left.png"));
		GridBagConstraints gbc_volverButton = new GridBagConstraints();
		gbc_volverButton.insets = new Insets(0, 0, 5, 5);
		gbc_volverButton.gridx = 0;
		gbc_volverButton.gridy = 0;
		add(volverButton, gbc_volverButton);

		JLabel lblNewLabel = new JLabel("GESTOR DE LÍNEAS");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 2;
		add(separator, gbc_separator);

		JLabel tipoLineaLabel = new JLabel("Tipo de Línea");
		GridBagConstraints gbc_tipoLineaLabel = new GridBagConstraints();
		gbc_tipoLineaLabel.anchor = GridBagConstraints.EAST;
		gbc_tipoLineaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tipoLineaLabel.gridx = 1;
		gbc_tipoLineaLabel.gridy = 3;
		add(tipoLineaLabel, gbc_tipoLineaLabel);

		JComboBox tipoLineaComboBox = new JComboBox();
		tipoLineaComboBox.setToolTipText("");
		tipoLineaComboBox.setModel(new DefaultComboBoxModel(LineaTipoEnum.values()));
		GridBagConstraints gbc_tipoLineaComboBox = new GridBagConstraints();
		gbc_tipoLineaComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_tipoLineaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_tipoLineaComboBox.gridx = 2;
		gbc_tipoLineaComboBox.gridy = 3;
		tipoLineaComboBox.addActionListener(e -> this.comprobarTipoLinea());
		add(tipoLineaComboBox, gbc_tipoLineaComboBox);

		JLabel colorLabel = new JLabel("Color");
		GridBagConstraints gbc_colorLabel = new GridBagConstraints();
		gbc_colorLabel.anchor = GridBagConstraints.EAST;
		gbc_colorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_colorLabel.gridx = 1;
		gbc_colorLabel.gridy = 4;
		add(colorLabel, gbc_colorLabel);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(ColorLinea.values()));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 4;
		add(comboBox_1, gbc_comboBox_1);

		JLabel nombreLineaLabel = new JLabel("Nombre");
		GridBagConstraints gbc_nombreLineaLabel = new GridBagConstraints();
		gbc_nombreLineaLabel.anchor = GridBagConstraints.EAST;
		gbc_nombreLineaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLineaLabel.gridx = 1;
		gbc_nombreLineaLabel.gridy = 5;
		add(nombreLineaLabel, gbc_nombreLineaLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 5;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel capParadoLabel = new JLabel("Cap. Parado");
		GridBagConstraints gbc_capParadoLabel = new GridBagConstraints();
		gbc_capParadoLabel.anchor = GridBagConstraints.EAST;
		gbc_capParadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_capParadoLabel.gridx = 1;
		gbc_capParadoLabel.gridy = 6;
		add(capParadoLabel, gbc_capParadoLabel);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 6;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel capSentadoLabel = new JLabel("Cap. Sentado");
		GridBagConstraints gbc_capSentadoLabel = new GridBagConstraints();
		gbc_capSentadoLabel.anchor = GridBagConstraints.EAST;
		gbc_capSentadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_capSentadoLabel.gridx = 1;
		gbc_capSentadoLabel.gridy = 7;
		add(capSentadoLabel, gbc_capSentadoLabel);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 7;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JCheckBox wifiCheck = new JCheckBox("Wifi");
		GridBagConstraints gbc_wifiCheck = new GridBagConstraints();
		gbc_wifiCheck.anchor = GridBagConstraints.WEST;
		gbc_wifiCheck.insets = new Insets(0, 0, 5, 5);
		gbc_wifiCheck.gridx = 2;
		gbc_wifiCheck.gridy = 8;
		add(wifiCheck, gbc_wifiCheck);

		JCheckBox aireCheck = new JCheckBox("Aire");
		GridBagConstraints gbc_aireCheck = new GridBagConstraints();
		gbc_aireCheck.anchor = GridBagConstraints.WEST;
		gbc_aireCheck.insets = new Insets(0, 0, 5, 5);
		gbc_aireCheck.gridx = 2;
		gbc_aireCheck.gridy = 9;
		add(aireCheck, gbc_aireCheck);

		JButton crearButton = new JButton("Crear");
		GridBagConstraints gbc_crearButton = new GridBagConstraints();
		gbc_crearButton.gridwidth = 4;
		gbc_crearButton.insets = new Insets(0, 0, 5, 0);
		gbc_crearButton.gridx = 0;
		gbc_crearButton.gridy = 10;
		add(crearButton, gbc_crearButton);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 2;
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 11;
		add(separator_1, gbc_separator_1);

	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {
		// TODO Auto-generated method stub
		super.setPreferredSize(preferredSize);
	}

	public void comprobarTipoLinea() {

	}

}
