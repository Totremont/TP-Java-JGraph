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
import javax.swing.table.DefaultTableCellRenderer;

import died.izaguirre.haulet.tp.tablas.linea.LineaTipoEnum;
import died.izaguirre.haulet.tp.controladores.ControladorLineas;
import died.izaguirre.haulet.tp.tablas.linea.LineaColores;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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
	private JComboBox colorCBx;
	private JLabel nombreLineaLabel;
	private JTextField nombreLineaText;
	private JLabel capSentadoLabel;
	private JTextField capSentadoText;
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
	private TableRowSorter tableSorter;

	private ControladorLineas controlador;

	/**
	 * Create the panel.
	 */
	public MenuVerLineas() {
		setLayout(new BorderLayout(0, 0));

		head = new JPanel();
		add(head, BorderLayout.NORTH);
		GridBagLayout gbl_head = new GridBagLayout();
		gbl_head.columnWidths = new int[] { 0, 0 };
		gbl_head.rowHeights = new int[] { 0, 0, 0 };
		gbl_head.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_head.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
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
		gbl_panelCrearLineaHead.columnWidths = new int[] { 0, 0 };
		gbl_panelCrearLineaHead.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelCrearLineaHead.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelCrearLineaHead.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
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
		gbl_panelCrearLineaBody.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelCrearLineaBody.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCrearLineaBody.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelCrearLineaBody.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
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
		

		colorCBx = new JComboBox();
		colorCBx.setModel(new DefaultComboBoxModel(LineaColores.values()));
		GridBagConstraints gbc_colorCBx = new GridBagConstraints();
		gbc_colorCBx.insets = new Insets(0, 0, 5, 5);
		gbc_colorCBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_colorCBx.gridx = 2;
		gbc_colorCBx.gridy = 1;
		panelCrearLineaBody.add(colorCBx, gbc_colorCBx);

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

		capSentadoText = new JTextField();
		GridBagConstraints gbc_capSentadoText = new GridBagConstraints();
		gbc_capSentadoText.insets = new Insets(0, 0, 5, 5);
		gbc_capSentadoText.fill = GridBagConstraints.HORIZONTAL;
		gbc_capSentadoText.gridx = 2;
		gbc_capSentadoText.gridy = 3;
		panelCrearLineaBody.add(capSentadoText, gbc_capSentadoText);
		capSentadoText.setColumns(10);

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
		gbl_panelCrearLineaFoot.columnWidths = new int[] { 0, 0 };
		gbl_panelCrearLineaFoot.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelCrearLineaFoot.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelCrearLineaFoot.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelCrearLineaFoot.setLayout(gbl_panelCrearLineaFoot);

		crearLineaButton = new JButton("Crear Línea");
		GridBagConstraints gbc_crearLineaButton = new GridBagConstraints();
		gbc_crearLineaButton.insets = new Insets(0, 0, 5, 0);
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
		gbl_panelVerLineasHead.columnWidths = new int[] { 0, 0 };
		gbl_panelVerLineasHead.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelVerLineasHead.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelVerLineasHead.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
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
		gbl_buscador.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_buscador.rowHeights = new int[] { 0 };
		gbl_buscador.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_buscador.rowWeights = new double[] { 0.0 };
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
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Id.", "Nombre", "Color", "Info.", "Ver camino", "Eliminar" }) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 3:
					return ImageIcon.class;
				case 4:
					return ImageIcon.class;
				case 5:
					return ImageIcon.class;
				default:
					return Object.class;
				}
			}
		};

		table.setModel(model);
		DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
		alinear.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(alinear);
		table.getColumnModel().getColumn(1).setCellRenderer(alinear);
		table.getColumnModel().getColumn(2).setCellRenderer(alinear);
		tableSorter = new TableRowSorter(table.getModel());
		table.setRowSorter(tableSorter);
		scrollPane.setViewportView(table);

		controlador = new ControladorLineas(this);
	}

	public JPanel getHead() {
		return head;
	}

	public void setHead(JPanel head) {
		this.head = head;
	}

	public JLabel getTitulo() {
		return titulo;
	}

	public void setTitulo(JLabel titulo) {
		this.titulo = titulo;
	}

	public JSeparator getSeparator() {
		return separator;
	}

	public void setSeparator(JSeparator separator) {
		this.separator = separator;
	}

	public JPanel getBody() {
		return body;
	}

	public void setBody(JPanel body) {
		this.body = body;
	}

	public JPanel getPanelCrearLinea() {
		return panelCrearLinea;
	}

	public void setPanelCrearLinea(JPanel panelCrearLinea) {
		this.panelCrearLinea = panelCrearLinea;
	}

	public JPanel getPanelCrearLineaHead() {
		return panelCrearLineaHead;
	}

	public void setPanelCrearLineaHead(JPanel panelCrearLineaHead) {
		this.panelCrearLineaHead = panelCrearLineaHead;
	}

	public JLabel getCrearLineaTitulo() {
		return crearLineaTitulo;
	}

	public void setCrearLineaTitulo(JLabel crearLineaTitulo) {
		this.crearLineaTitulo = crearLineaTitulo;
	}

	public JSeparator getSeparator_1() {
		return separator_1;
	}

	public void setSeparator_1(JSeparator separator_1) {
		this.separator_1 = separator_1;
	}

	public JPanel getPanelCrearLineaBody() {
		return panelCrearLineaBody;
	}

	public void setPanelCrearLineaBody(JPanel panelCrearLineaBody) {
		this.panelCrearLineaBody = panelCrearLineaBody;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JLabel getTipoLineaLabel() {
		return tipoLineaLabel;
	}

	public void setTipoLineaLabel(JLabel tipoLineaLabel) {
		this.tipoLineaLabel = tipoLineaLabel;
	}

	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}

	public void setLblNewLabel_2(JLabel lblNewLabel_2) {
		this.lblNewLabel_2 = lblNewLabel_2;
	}

	public JComboBox getLineaTipoCBx() {
		return lineaTipoCBx;
	}

	public void setLineaTipoCBx(JComboBox lineaTipoCBx) {
		this.lineaTipoCBx = lineaTipoCBx;
	}

	public JLabel getColorLineaLabel() {
		return colorLineaLabel;
	}

	public void setColorLineaLabel(JLabel colorLineaLabel) {
		this.colorLineaLabel = colorLineaLabel;
	}

	public JComboBox getColorCBx() {
		return colorCBx;
	}

	public void setColorCBx(JComboBox comboBox) {
		this.colorCBx = comboBox;
	}

	public JLabel getNombreLineaLabel() {
		return nombreLineaLabel;
	}

	public void setNombreLineaLabel(JLabel nombreLineaLabel) {
		this.nombreLineaLabel = nombreLineaLabel;
	}

	public JTextField getNombreLineaText() {
		return nombreLineaText;
	}

	public void setNombreLineaText(JTextField nombreLineaText) {
		this.nombreLineaText = nombreLineaText;
	}

	public JLabel getCapSentadoLabel() {
		return capSentadoLabel;
	}

	public void setCapSentadoLabel(JLabel capSentadoLabel) {
		this.capSentadoLabel = capSentadoLabel;
	}

	public JTextField getCapSentadoText() {
		return capSentadoText;
	}

	public void setCapSentadoText(JTextField textField) {
		this.capSentadoText = textField;
	}

	public JCheckBox getAireCk() {
		return aireCk;
	}

	public void setAireCk(JCheckBox aireCk) {
		this.aireCk = aireCk;
	}

	public JCheckBox getWifiCk() {
		return wifiCk;
	}

	public void setWifiCk(JCheckBox wifiCk) {
		this.wifiCk = wifiCk;
	}

	public JLabel getOrigenLabel() {
		return origenLabel;
	}

	public void setOrigenLabel(JLabel origenLabel) {
		this.origenLabel = origenLabel;
	}

	public JLabel getDestinoLabel() {
		return destinoLabel;
	}

	public void setDestinoLabel(JLabel destinoLabel) {
		this.destinoLabel = destinoLabel;
	}

	public JComboBox getOrigenCBx() {
		return origenCBx;
	}

	public void setOrigenCBx(JComboBox origenCBx) {
		this.origenCBx = origenCBx;
	}

	public JComboBox getDestinoCBx() {
		return destinoCBx;
	}

	public void setDestinoCBx(JComboBox destinoCBx) {
		this.destinoCBx = destinoCBx;
	}

	public JLabel getTrayectoLabel() {
		return trayectoLabel;
	}

	public void setTrayectoLabel(JLabel trayectoLabel) {
		this.trayectoLabel = trayectoLabel;
	}

	public JComboBox getTrayectoCBx() {
		return trayectoCBx;
	}

	public void setTrayectoCBx(JComboBox trayectoCBx) {
		this.trayectoCBx = trayectoCBx;
	}

	public JPanel getPanelVerLineas() {
		return panelVerLineas;
	}

	public void setPanelVerLineas(JPanel panelVerLineas) {
		this.panelVerLineas = panelVerLineas;
	}

	public JPanel getPanelVerLineasHead() {
		return panelVerLineasHead;
	}

	public void setPanelVerLineasHead(JPanel panelVerLineasHead) {
		this.panelVerLineasHead = panelVerLineasHead;
	}

	public JLabel getVerLineasTitulo() {
		return verLineasTitulo;
	}

	public void setVerLineasTitulo(JLabel verLineasTitulo) {
		this.verLineasTitulo = verLineasTitulo;
	}

	public JSeparator getSeparator_2() {
		return separator_2;
	}

	public void setSeparator_2(JSeparator separator_2) {
		this.separator_2 = separator_2;
	}

	public JPanel getPanelVerLineasBody() {
		return panelVerLineasBody;
	}

	public void setPanelVerLineasBody(JPanel panelVerLineasBody) {
		this.panelVerLineasBody = panelVerLineasBody;
	}

	public JPanel getBuscador() {
		return buscador;
	}

	public void setBuscador(JPanel buscador) {
		this.buscador = buscador;
	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public void setLblNewLabel_1(JLabel lblNewLabel_1) {
		this.lblNewLabel_1 = lblNewLabel_1;
	}

	public JTextField getBuscarText() {
		return buscarText;
	}

	public void setBuscarText(JTextField buscarText) {
		this.buscarText = buscarText;
	}

	public JLabel getBuscarButton() {
		return buscarButton;
	}

	public void setBuscarButton(JLabel buscarButton) {
		this.buscarButton = buscarButton;
	}

	public JLabel getLblNewLabel_3() {
		return lblNewLabel_3;
	}

	public void setLblNewLabel_3(JLabel lblNewLabel_3) {
		this.lblNewLabel_3 = lblNewLabel_3;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JPanel getPanelCrearLineaFoot() {
		return panelCrearLineaFoot;
	}

	public void setPanelCrearLineaFoot(JPanel panelCrearLineaFoot) {
		this.panelCrearLineaFoot = panelCrearLineaFoot;
	}

	public JButton getCrearLineaButton() {
		return crearLineaButton;
	}

	public void setCrearLineaButton(JButton crearLineaButton) {
		this.crearLineaButton = crearLineaButton;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public TableRowSorter getTableSorter() {
		return tableSorter;
	}

	public void setTableSorter(TableRowSorter sort) {
		this.tableSorter = sort;
	}

	public ControladorLineas getControlador() {
		return controlador;
	}

	public void setControlador(ControladorLineas controlador) {
		this.controlador = controlador;
	}

}
