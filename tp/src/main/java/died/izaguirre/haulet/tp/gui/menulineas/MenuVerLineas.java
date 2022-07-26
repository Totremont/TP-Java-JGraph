package died.izaguirre.haulet.tp.gui.menulineas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JSeparator;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import died.izaguirre.haulet.tp.tablas.linea.LineaTipoEnum;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuVerLineas extends JPanel {
	private JPanel head;
	private JLabel titulo;
	private JSeparator separator;
	private JPanel body;
	private JPanel panelCrearLinea;
	private JPanel panelCrearLineaHead;
	private JLabel crearLineaTitulo;
	private JSeparator separator_1;
	private JPanel panelCrearLineaBody;
	private JLabel lblNewLabel;
	private JLabel tipoLineaLabel;
	private JLabel lblNewLabel_2;
	private JComboBox lineaTipoCBx;
	private JLabel colorLineaLabel;
	private JComboBox comboBox;
	private JLabel nombreLineaLabel;
	private JTextField nombreLineaText;
	private JLabel capSentadoLabel;
	private JTextField textField;
	private JCheckBox aireCk;
	private JCheckBox wifiCk;
	private JLabel origenLabel;
	private JLabel destinoLabel;
	private JComboBox origenCBx;
	private JComboBox destinoCBx;
	private JLabel trayectoLabel;
	private JComboBox trayectoCBx;
	private JPanel panelVerLineas;
	private JPanel panelVerLineasHead;
	private JLabel verLineasTitulo;
	private JSeparator separator_2;
	private JPanel panelVerLineasBody;
	private JPanel buscador;
	private JLabel lblNewLabel_1;
	private JTextField buscarText;
	private JLabel buscarButton;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panelCrearLineaFoot;
	private JButton crearLineaButton;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public MenuVerLineas() {
		setLayout(new BorderLayout(0, 0));
		
		head = new JPanel();
		add(head, BorderLayout.NORTH);
		GridBagLayout gbl_head = new GridBagLayout();
		gbl_head.columnWidths = new int[]{0, 0};
		gbl_head.rowHeights = new int[]{0, 0, 0};
		gbl_head.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_head.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		head.setLayout(gbl_head);
		
		titulo = new JLabel("GESTOR DE LÍNEAS");
		GridBagConstraints gbc_titulo = new GridBagConstraints();
		gbc_titulo.insets = new Insets(0, 0, 5, 0);
		gbc_titulo.weightx = 1.0;
		gbc_titulo.gridx = 0;
		gbc_titulo.gridy = 0;
		head.add(titulo, gbc_titulo);
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.weightx = 1.0;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		head.add(separator, gbc_separator);
		
		body = new JPanel();
		add(body, BorderLayout.CENTER);
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
		panelCrearLinea = new JPanel();
		body.add(panelCrearLinea);
		panelCrearLinea.setLayout(new BorderLayout(0, 0));
		
		panelCrearLineaHead = new JPanel();
		panelCrearLinea.add(panelCrearLineaHead, BorderLayout.NORTH);
		GridBagLayout gbl_panelCrearLineaHead = new GridBagLayout();
		gbl_panelCrearLineaHead.columnWidths = new int[]{0, 0};
		gbl_panelCrearLineaHead.rowHeights = new int[]{0, 0, 0};
		gbl_panelCrearLineaHead.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelCrearLineaHead.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelCrearLineaHead.setLayout(gbl_panelCrearLineaHead);
		
		crearLineaTitulo = new JLabel("Crear Línea");
		GridBagConstraints gbc_crearLineaTitulo = new GridBagConstraints();
		gbc_crearLineaTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_crearLineaTitulo.weightx = 1.0;
		gbc_crearLineaTitulo.gridx = 0;
		gbc_crearLineaTitulo.gridy = 0;
		panelCrearLineaHead.add(crearLineaTitulo, gbc_crearLineaTitulo);
		
		separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.weightx = 1.0;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 1;
		panelCrearLineaHead.add(separator_1, gbc_separator_1);
		
		panelCrearLineaBody = new JPanel();
		panelCrearLinea.add(panelCrearLineaBody, BorderLayout.CENTER);
		GridBagLayout gbl_panelCrearLineaBody = new GridBagLayout();
		gbl_panelCrearLineaBody.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelCrearLineaBody.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCrearLineaBody.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCrearLineaBody.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCrearLineaBody.setLayout(gbl_panelCrearLineaBody);
		
		lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.weightx = 0.5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelCrearLineaBody.add(lblNewLabel, gbc_lblNewLabel);
		
		tipoLineaLabel = new JLabel("Tipo de línea");
		GridBagConstraints gbc_tipoLineaLabel = new GridBagConstraints();
		gbc_tipoLineaLabel.anchor = GridBagConstraints.EAST;
		gbc_tipoLineaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tipoLineaLabel.gridx = 1;
		gbc_tipoLineaLabel.gridy = 0;
		panelCrearLineaBody.add(tipoLineaLabel, gbc_tipoLineaLabel);
		
		lineaTipoCBx = new JComboBox();
		lineaTipoCBx.setModel(new DefaultComboBoxModel(LineaTipoEnum.values()));
		GridBagConstraints gbc_lineaTipoCBx = new GridBagConstraints();
		gbc_lineaTipoCBx.insets = new Insets(0, 0, 5, 5);
		gbc_lineaTipoCBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_lineaTipoCBx.gridx = 2;
		gbc_lineaTipoCBx.gridy = 0;
		panelCrearLineaBody.add(lineaTipoCBx, gbc_lineaTipoCBx);
		
		lblNewLabel_2 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.weightx = 0.8;
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 0;
		panelCrearLineaBody.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		colorLineaLabel = new JLabel("Color");
		GridBagConstraints gbc_colorLineaLabel = new GridBagConstraints();
		gbc_colorLineaLabel.anchor = GridBagConstraints.EAST;
		gbc_colorLineaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_colorLineaLabel.gridx = 1;
		gbc_colorLineaLabel.gridy = 1;
		panelCrearLineaBody.add(colorLineaLabel, gbc_colorLineaLabel);
		
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		panelCrearLineaBody.add(comboBox, gbc_comboBox);
		
		nombreLineaLabel = new JLabel("Nombre");
		GridBagConstraints gbc_nombreLineaLabel = new GridBagConstraints();
		gbc_nombreLineaLabel.anchor = GridBagConstraints.EAST;
		gbc_nombreLineaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLineaLabel.gridx = 1;
		gbc_nombreLineaLabel.gridy = 2;
		panelCrearLineaBody.add(nombreLineaLabel, gbc_nombreLineaLabel);
		
		nombreLineaText = new JTextField();
		GridBagConstraints gbc_nombreLineaText = new GridBagConstraints();
		gbc_nombreLineaText.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLineaText.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreLineaText.gridx = 2;
		gbc_nombreLineaText.gridy = 2;
		panelCrearLineaBody.add(nombreLineaText, gbc_nombreLineaText);
		nombreLineaText.setColumns(10);
		
		capSentadoLabel = new JLabel("Cap. Sentado");
		GridBagConstraints gbc_capSentadoLabel = new GridBagConstraints();
		gbc_capSentadoLabel.anchor = GridBagConstraints.EAST;
		gbc_capSentadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_capSentadoLabel.gridx = 1;
		gbc_capSentadoLabel.gridy = 3;
		panelCrearLineaBody.add(capSentadoLabel, gbc_capSentadoLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		panelCrearLineaBody.add(textField, gbc_textField);
		textField.setColumns(10);
		
		aireCk = new JCheckBox("Aire");
		aireCk.setEnabled(false);
		GridBagConstraints gbc_aireCk = new GridBagConstraints();
		gbc_aireCk.anchor = GridBagConstraints.WEST;
		gbc_aireCk.insets = new Insets(0, 0, 5, 5);
		gbc_aireCk.gridx = 2;
		gbc_aireCk.gridy = 4;
		panelCrearLineaBody.add(aireCk, gbc_aireCk);
		
		wifiCk = new JCheckBox("Wifi");
		wifiCk.setEnabled(false);
		GridBagConstraints gbc_wifiCk = new GridBagConstraints();
		gbc_wifiCk.anchor = GridBagConstraints.WEST;
		gbc_wifiCk.insets = new Insets(0, 0, 5, 5);
		gbc_wifiCk.gridx = 2;
		gbc_wifiCk.gridy = 5;
		panelCrearLineaBody.add(wifiCk, gbc_wifiCk);
		
		origenLabel = new JLabel("Origen");
		GridBagConstraints gbc_origenLabel = new GridBagConstraints();
		gbc_origenLabel.anchor = GridBagConstraints.EAST;
		gbc_origenLabel.insets = new Insets(0, 0, 5, 5);
		gbc_origenLabel.gridx = 1;
		gbc_origenLabel.gridy = 6;
		panelCrearLineaBody.add(origenLabel, gbc_origenLabel);
		
		origenCBx = new JComboBox();
		GridBagConstraints gbc_origenCBx = new GridBagConstraints();
		gbc_origenCBx.insets = new Insets(0, 0, 5, 5);
		gbc_origenCBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_origenCBx.gridx = 2;
		gbc_origenCBx.gridy = 6;
		panelCrearLineaBody.add(origenCBx, gbc_origenCBx);
		
		destinoLabel = new JLabel("Destino");
		GridBagConstraints gbc_destinoLabel = new GridBagConstraints();
		gbc_destinoLabel.anchor = GridBagConstraints.EAST;
		gbc_destinoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_destinoLabel.gridx = 1;
		gbc_destinoLabel.gridy = 7;
		panelCrearLineaBody.add(destinoLabel, gbc_destinoLabel);
		
		destinoCBx = new JComboBox();
		GridBagConstraints gbc_destinoCBx = new GridBagConstraints();
		gbc_destinoCBx.insets = new Insets(0, 0, 5, 5);
		gbc_destinoCBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_destinoCBx.gridx = 2;
		gbc_destinoCBx.gridy = 7;
		panelCrearLineaBody.add(destinoCBx, gbc_destinoCBx);
		
		trayectoLabel = new JLabel("Trayecto");
		GridBagConstraints gbc_trayectoLabel = new GridBagConstraints();
		gbc_trayectoLabel.anchor = GridBagConstraints.EAST;
		gbc_trayectoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_trayectoLabel.gridx = 1;
		gbc_trayectoLabel.gridy = 8;
		panelCrearLineaBody.add(trayectoLabel, gbc_trayectoLabel);
		
		trayectoCBx = new JComboBox();
		GridBagConstraints gbc_trayectoCBx = new GridBagConstraints();
		gbc_trayectoCBx.insets = new Insets(0, 0, 5, 5);
		gbc_trayectoCBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_trayectoCBx.gridx = 2;
		gbc_trayectoCBx.gridy = 8;
		panelCrearLineaBody.add(trayectoCBx, gbc_trayectoCBx);
		
		panelCrearLineaFoot = new JPanel();
		panelCrearLinea.add(panelCrearLineaFoot, BorderLayout.SOUTH);
		GridBagLayout gbl_panelCrearLineaFoot = new GridBagLayout();
		gbl_panelCrearLineaFoot.columnWidths = new int[]{0, 0};
		gbl_panelCrearLineaFoot.rowHeights = new int[]{0, 0};
		gbl_panelCrearLineaFoot.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelCrearLineaFoot.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelCrearLineaFoot.setLayout(gbl_panelCrearLineaFoot);
		
		crearLineaButton = new JButton("Crear Línea");
		GridBagConstraints gbc_crearLineaButton = new GridBagConstraints();
		gbc_crearLineaButton.weightx = 1.0;
		gbc_crearLineaButton.gridx = 0;
		gbc_crearLineaButton.gridy = 0;
		panelCrearLineaFoot.add(crearLineaButton, gbc_crearLineaButton);
		
		panelVerLineas = new JPanel();
		body.add(panelVerLineas);
		panelVerLineas.setLayout(new BorderLayout(0, 0));
		
		panelVerLineasHead = new JPanel();
		panelVerLineas.add(panelVerLineasHead, BorderLayout.NORTH);
		GridBagLayout gbl_panelVerLineasHead = new GridBagLayout();
		gbl_panelVerLineasHead.columnWidths = new int[]{0, 0};
		gbl_panelVerLineasHead.rowHeights = new int[]{0, 0, 0};
		gbl_panelVerLineasHead.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelVerLineasHead.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelVerLineasHead.setLayout(gbl_panelVerLineasHead);
		
		verLineasTitulo = new JLabel("Ver líneas");
		GridBagConstraints gbc_verLineasTitulo = new GridBagConstraints();
		gbc_verLineasTitulo.insets = new Insets(10, 0, 10, 0);
		gbc_verLineasTitulo.weightx = 1.0;
		gbc_verLineasTitulo.gridx = 0;
		gbc_verLineasTitulo.gridy = 0;
		panelVerLineasHead.add(verLineasTitulo, gbc_verLineasTitulo);
		
		separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.BOTH;
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 1;
		panelVerLineasHead.add(separator_2, gbc_separator_2);
		
		panelVerLineasBody = new JPanel();
		panelVerLineas.add(panelVerLineasBody, BorderLayout.CENTER);
		panelVerLineasBody.setLayout(new BorderLayout(0, 0));
		
		buscador = new JPanel();
		panelVerLineasBody.add(buscador, BorderLayout.NORTH);
		GridBagLayout gbl_buscador = new GridBagLayout();
		gbl_buscador.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_buscador.rowHeights = new int[] {0};
		gbl_buscador.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_buscador.rowWeights = new double[]{0.0};
		buscador.setLayout(gbl_buscador);
		
		lblNewLabel_1 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.weightx = 0.5;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		buscador.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		buscarText = new JTextField();
		GridBagConstraints gbc_buscarText = new GridBagConstraints();
		gbc_buscarText.insets = new Insets(5, 0, 5, 5);
		gbc_buscarText.fill = GridBagConstraints.HORIZONTAL;
		gbc_buscarText.gridx = 1;
		gbc_buscarText.gridy = 0;
		buscador.add(buscarText, gbc_buscarText);
		buscarText.setColumns(10);
		
		buscarButton = new JLabel("");
		GridBagConstraints gbc_buscarButton = new GridBagConstraints();
		gbc_buscarButton.insets = new Insets(5, 0, 5, 0);
		gbc_buscarButton.gridx = 2;
		gbc_buscarButton.gridy = 0;
		ImageIcon imgBuscador = new ImageIcon(getClass().getResource("/magnify.png"));
		buscarButton.setIcon(imgBuscador);
		buscador.add(buscarButton, gbc_buscarButton);
		
		lblNewLabel_3 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.weightx = 0.8;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 0;
		buscador.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		scrollPane = new JScrollPane();
		panelVerLineasBody.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nombre", "Color", "Tipo", "Info.", "Ver camino", "Eliminar"
				});
		table.setModel(model);
		scrollPane.setViewportView(table);

	}

}
