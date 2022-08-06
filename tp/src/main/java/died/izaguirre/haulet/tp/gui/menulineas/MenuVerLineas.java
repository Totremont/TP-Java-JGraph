package died.izaguirre.haulet.tp.gui.menulineas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JSeparator;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

import died.izaguirre.haulet.tp.tablas.linea.LineaTipoEnum;
import died.izaguirre.haulet.tp.controladores.ControladorLineas;
import died.izaguirre.haulet.tp.tablas.Parada;
import died.izaguirre.haulet.tp.tablas.linea.Linea;
import died.izaguirre.haulet.tp.tablas.linea.LineaColores;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import java.awt.Color;

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
	private JComboBox<LineaTipoEnum> lineaTipoCBx;
	private JLabel colorLineaLabel;
	private JComboBox<LineaColores> colorCBx;
	private JLabel nombreLineaLabel;
	private JTextField nombreLineaText;
	private JLabel capSentadoLabel;
	private JCheckBox aireCk;
	private JCheckBox wifiCk;
	private JLabel origenLabel;
	private JLabel destinoLabel;
	private JComboBox<Parada> origenCBx;
	private JComboBox<Parada> destinoCBx;
	private JLabel trayectoLabel;
	private JComboBox<String> trayectoCBx;
	private JPanel panelVerLineas;
	private JPanel panelVerLineasBody;
	private JLabel buscarButton;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton crearLineaButton;
	private DefaultTableModel model;
	private TableRowSorter tableSorter;

	private ControladorLineas controlador;
	private JFrame menuPadre;
	private JPanel panel;
	private JSpinner capSentadoText;
	private JPanel panel_1;
	private JLabel lblNewLabel_4;
	private JPanel panel_2;
	private JLabel lblNewLabel_5;
	private JTextField buscarText;
	private JPanel panel_3;
	private JLabel lblNewLabel_1;
	private JSpinner porcentajeSpinner;
	private JSeparator separator_2;
	private JPanel panel_4;

	/**
	 * Create the panel.
	 */
	public MenuVerLineas(JFrame menuPadre) {
		this.menuPadre = menuPadre;
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
		gbl_panelCrearLineaBody.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panelCrearLineaBody.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCrearLineaBody.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0 };
		gbl_panelCrearLineaBody.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panelCrearLineaBody.setLayout(gbl_panelCrearLineaBody);

		lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.weightx = 0.5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panelCrearLineaBody.add(lblNewLabel, gbc_lblNewLabel);

		tipoLineaLabel = new JLabel("Tipo de línea");
		GridBagConstraints gbc_tipoLineaLabel = new GridBagConstraints();
		gbc_tipoLineaLabel.anchor = GridBagConstraints.WEST;
		gbc_tipoLineaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tipoLineaLabel.gridx = 1;
		gbc_tipoLineaLabel.gridy = 1;
		panelCrearLineaBody.add(tipoLineaLabel, gbc_tipoLineaLabel);

		lineaTipoCBx = new JComboBox<LineaTipoEnum>();
		lineaTipoCBx.setModel(new DefaultComboBoxModel(LineaTipoEnum.values()));
		GridBagConstraints gbc_lineaTipoCBx = new GridBagConstraints();
		gbc_lineaTipoCBx.insets = new Insets(0, 0, 5, 5);
		gbc_lineaTipoCBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_lineaTipoCBx.gridx = 2;
		gbc_lineaTipoCBx.gridy = 1;
		panelCrearLineaBody.add(lineaTipoCBx, gbc_lineaTipoCBx);

		lblNewLabel_2 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.weightx = 0.8;
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 1;
		panelCrearLineaBody.add(lblNewLabel_2, gbc_lblNewLabel_2);

		colorLineaLabel = new JLabel("Color");
		GridBagConstraints gbc_colorLineaLabel = new GridBagConstraints();
		gbc_colorLineaLabel.anchor = GridBagConstraints.WEST;
		gbc_colorLineaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_colorLineaLabel.gridx = 1;
		gbc_colorLineaLabel.gridy = 2;
		panelCrearLineaBody.add(colorLineaLabel, gbc_colorLineaLabel);
		

		colorCBx = new JComboBox<LineaColores>();
		colorCBx.setModel(new DefaultComboBoxModel(LineaColores.values()));
		GridBagConstraints gbc_colorCBx = new GridBagConstraints();
		gbc_colorCBx.insets = new Insets(0, 0, 5, 5);
		gbc_colorCBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_colorCBx.gridx = 2;
		gbc_colorCBx.gridy = 2;
		panelCrearLineaBody.add(colorCBx, gbc_colorCBx);
		
		panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.anchor = GridBagConstraints.WEST;
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.gridx = 3;
		gbc_panel_4.gridy = 2;
		panelCrearLineaBody.add(panel_4, gbc_panel_4);
				
				capSentadoText = new JSpinner();
				panel_4.add(capSentadoText);
				capSentadoText.setPreferredSize(new JSpinner().getPreferredSize());
				capSentadoText.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				
						capSentadoLabel = new JLabel("Cap. Sentado");
						panel_4.add(capSentadoLabel);
		
		panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.WEST;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 3;
		panelCrearLineaBody.add(panel_3, gbc_panel_3);
		
		porcentajeSpinner = new JSpinner();
		panel_3.add(porcentajeSpinner);
		
		lblNewLabel_1 = new JLabel("% Parado       ");
		panel_3.add(lblNewLabel_1);

		nombreLineaLabel = new JLabel("Nombre");
		GridBagConstraints gbc_nombreLineaLabel = new GridBagConstraints();
		gbc_nombreLineaLabel.anchor = GridBagConstraints.WEST;
		gbc_nombreLineaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLineaLabel.gridx = 1;
		gbc_nombreLineaLabel.gridy = 3;
		panelCrearLineaBody.add(nombreLineaLabel, gbc_nombreLineaLabel);

		nombreLineaText = new JTextField();
		GridBagConstraints gbc_nombreLineaText = new GridBagConstraints();
		gbc_nombreLineaText.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLineaText.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreLineaText.gridx = 2;
		gbc_nombreLineaText.gridy = 3;
		panelCrearLineaBody.add(nombreLineaText, gbc_nombreLineaText);
		nombreLineaText.setColumns(10);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 1;
		panelCrearLineaBody.add(panel, gbc_panel);
		
				wifiCk = new JCheckBox("Wi-Fi");
				panel.add(wifiCk);
				wifiCk.setEnabled(false);
				
						aireCk = new JCheckBox("AC");
						panel.add(aireCk);
						aireCk.setEnabled(false);
				
						origenLabel = new JLabel("Origen");
						GridBagConstraints gbc_origenLabel = new GridBagConstraints();
						gbc_origenLabel.anchor = GridBagConstraints.WEST;
						gbc_origenLabel.insets = new Insets(0, 0, 5, 5);
						gbc_origenLabel.gridx = 1;
						gbc_origenLabel.gridy = 4;
						panelCrearLineaBody.add(origenLabel, gbc_origenLabel);
				
						origenCBx = new JComboBox<Parada>();
						GridBagConstraints gbc_origenCBx = new GridBagConstraints();
						gbc_origenCBx.insets = new Insets(2, 0, 5, 5);
						gbc_origenCBx.fill = GridBagConstraints.HORIZONTAL;
						gbc_origenCBx.gridx = 2;
						gbc_origenCBx.gridy = 4;
						panelCrearLineaBody.add(origenCBx, gbc_origenCBx);
				
						destinoLabel = new JLabel("Destino");
						GridBagConstraints gbc_destinoLabel = new GridBagConstraints();
						gbc_destinoLabel.anchor = GridBagConstraints.WEST;
						gbc_destinoLabel.insets = new Insets(0, 0, 5, 5);
						gbc_destinoLabel.gridx = 1;
						gbc_destinoLabel.gridy = 5;
						panelCrearLineaBody.add(destinoLabel, gbc_destinoLabel);
				
						destinoCBx = new JComboBox<Parada>();
						GridBagConstraints gbc_destinoCBx = new GridBagConstraints();
						gbc_destinoCBx.insets = new Insets(2, 0, 5, 5);
						gbc_destinoCBx.fill = GridBagConstraints.HORIZONTAL;
						gbc_destinoCBx.gridx = 2;
						gbc_destinoCBx.gridy = 5;
						panelCrearLineaBody.add(destinoCBx, gbc_destinoCBx);
				
//						buscarButton = new JLabel("");
//						GridBagConstraints gbc_buscarButton = new GridBagConstraints();
//						gbc_buscarButton.insets = new Insets(0, 0, 5, 0);
//						gbc_buscarButton.gridx = 3;
//						gbc_buscarButton.gridy = 7;
//						panelCrearLineaBody.add(buscarButton, gbc_buscarButton);
//						buscarButton.setIcon(imgBuscador);
		
				trayectoLabel = new JLabel("Trayecto");
				GridBagConstraints gbc_trayectoLabel = new GridBagConstraints();
				gbc_trayectoLabel.anchor = GridBagConstraints.WEST;
				gbc_trayectoLabel.insets = new Insets(0, 0, 5, 5);
				gbc_trayectoLabel.gridx = 1;
				gbc_trayectoLabel.gridy = 6;
				panelCrearLineaBody.add(trayectoLabel, gbc_trayectoLabel);
												
														trayectoCBx = new JComboBox<String>();
														GridBagConstraints gbc_trayectoCBx = new GridBagConstraints();
														gbc_trayectoCBx.insets = new Insets(2, 0, 5, 5);
														gbc_trayectoCBx.fill = GridBagConstraints.HORIZONTAL;
														gbc_trayectoCBx.gridx = 2;
														gbc_trayectoCBx.gridy = 6;
														panelCrearLineaBody.add(trayectoCBx, gbc_trayectoCBx);
										
												crearLineaButton = new JButton("Crear Línea");
												GridBagConstraints gbc_crearLineaButton = new GridBagConstraints();
												gbc_crearLineaButton.fill = GridBagConstraints.VERTICAL;
												gbc_crearLineaButton.gridwidth = 5;
												gbc_crearLineaButton.insets = new Insets(5, 0, 5, 0);
												gbc_crearLineaButton.gridx = 0;
												gbc_crearLineaButton.gridy = 7;
												panelCrearLineaBody.add(crearLineaButton, gbc_crearLineaButton);
										
										separator_2 = new JSeparator();
										GridBagConstraints gbc_separator_2 = new GridBagConstraints();
										gbc_separator_2.anchor = GridBagConstraints.SOUTH;
										gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
										gbc_separator_2.gridwidth = 5;
										gbc_separator_2.gridx = 0;
										gbc_separator_2.gridy = 8;
										panelCrearLineaBody.add(separator_2, gbc_separator_2);

		panelVerLineas = new JPanel();
		body.add(panelVerLineas);
		panelVerLineas.setLayout(new BorderLayout(0, 0));

		panelVerLineasBody = new JPanel();
		panelVerLineas.add(panelVerLineasBody, BorderLayout.CENTER);
		panelVerLineasBody.setLayout(new BorderLayout(0, 0));
		ImageIcon imgBuscador = new ImageIcon(getClass().getResource("/magnify.png"));

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
		
		panel_1 = new JPanel();
		panelVerLineasBody.add(panel_1, BorderLayout.NORTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0};
		gbl_panel_1.rowHeights = new int[] {0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0};
		panel_1.setLayout(gbl_panel_1);
		
		lblNewLabel_4 = new JLabel("VER LINEAS");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(5, 0, 0, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 20, 0, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel_1.add(panel_2, gbc_panel_2);
		
		buscarText = new JTextField();
		buscarText.setHorizontalAlignment(SwingConstants.LEFT);
		buscarText.setPreferredSize(new Dimension(150,buscarText.getPreferredSize().height));
		panel_2.add(buscarText);
		
		lblNewLabel_5 = new JLabel("");
		panel_2.add(lblNewLabel_5);
		lblNewLabel_5.setIcon(new ImageIcon(getClass().getResource("/magnify.png")));
		//buscarText.setColumns(10);

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

	public JComboBox<LineaTipoEnum> getLineaTipoCBx() {
		return lineaTipoCBx;
	}

	public void setLineaTipoCBx(JComboBox<LineaTipoEnum> lineaTipoCBx) {
		this.lineaTipoCBx = lineaTipoCBx;
	}

	public JLabel getColorLineaLabel() {
		return colorLineaLabel;
	}

	public void setColorLineaLabel(JLabel colorLineaLabel) {
		this.colorLineaLabel = colorLineaLabel;
	}

	public JComboBox<LineaColores> getColorCBx() {
		return colorCBx;
	}

	public void setColorCBx(JComboBox<LineaColores> comboBox) {
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

	public JSpinner getCapSentadoText() {
		return capSentadoText;
	}

//	public void setCapSentadoText(JTextField textField) {
//		this.capSentadoText = textField;
//	}

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

	public JComboBox<Parada> getOrigenCBx() {
		return origenCBx;
	}

	public void setOrigenCBx(JComboBox<Parada> origenCBx) {
		this.origenCBx = origenCBx;
	}

	public JComboBox<Parada> getDestinoCBx() {
		return destinoCBx;
	}

	public void setDestinoCBx(JComboBox<Parada> destinoCBx) {
		this.destinoCBx = destinoCBx;
	}

	public JLabel getTrayectoLabel() {
		return trayectoLabel;
	}

	public void setTrayectoLabel(JLabel trayectoLabel) {
		this.trayectoLabel = trayectoLabel;
	}

	public JComboBox<String> getTrayectoCBx() {
		return trayectoCBx;
	}

	public void setTrayectoCBx(JComboBox<String> trayectoCBx) {
		this.trayectoCBx = trayectoCBx;
	}

	public JPanel getPanelVerLineas() {
		return panelVerLineas;
	}

	public void setPanelVerLineas(JPanel panelVerLineas) {
		this.panelVerLineas = panelVerLineas;
	}

//	public JPanel getPanelVerLineasHead() {
//		return panelVerLineasHead;
//	}
//
//	public void setPanelVerLineasHead(JPanel panelVerLineasHead) {
//		this.panelVerLineasHead = panelVerLineasHead;
//	}
//
//	public JLabel getVerLineasTitulo() {
//		return verLineasTitulo;
//	}
//
//	public void setVerLineasTitulo(JLabel verLineasTitulo) {
//		this.verLineasTitulo = verLineasTitulo;
//	}
//
//	public JSeparator getSeparator_2() {
//		return separator_2;
//	}
//
//	public void setSeparator_2(JSeparator separator_2) {
//		this.separator_2 = separator_2;
//	}

	public JPanel getPanelVerLineasBody() {
		return panelVerLineasBody;
	}

	public void setPanelVerLineasBody(JPanel panelVerLineasBody) {
		this.panelVerLineasBody = panelVerLineasBody;
	}

//	public JPanel getBuscador() {
//		return buscador;
//	}
//
//	public void setBuscador(JPanel buscador) {
//		this.buscador = buscador;
//	}
//
//	public JLabel getLblNewLabel_1() {
//		return lblNewLabel_1;
//	}
//
//	public void setLblNewLabel_1(JLabel lblNewLabel_1) {
//		this.lblNewLabel_1 = lblNewLabel_1;
//	}
//
	public JTextField getBuscarText() {
		return buscarText;
	}
//
//	public void setBuscarText(JTextField buscarText) {
//		this.buscarText = buscarText;
//	}

	public JLabel getBuscarButton() {
		return buscarButton;
	}

	public void setBuscarButton(JLabel buscarButton) {
		this.buscarButton = buscarButton;
	}

//	public JLabel getLblNewLabel_3() {
//		return lblNewLabel_3;
//	}
//
//	public void setLblNewLabel_3(JLabel lblNewLabel_3) {
//		this.lblNewLabel_3 = lblNewLabel_3;
//	}
	
	public JSpinner getPorcentaje() 
	{
		return porcentajeSpinner;
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

//	public JPanel getPanelCrearLineaFoot() {
//		return panelCrearLineaFoot;
//	}
//
//	public void setPanelCrearLineaFoot(JPanel panelCrearLineaFoot) {
//		this.panelCrearLineaFoot = panelCrearLineaFoot;
//	}

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
