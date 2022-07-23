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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearLineaBody extends JPanel {
	private JLabel tipoLineaLabel;
	private JComboBox tipoLineaComboBx;
	private JLabel colorLabel;
	private JComboBox colorLineaComboBx;
	private JLabel nombreLabel;
	private JTextField nombreLineaText;
	private JLabel capSentadoLabel;
	private JTextField capSentadoText;
	private JCheckBox wifiCk;
	private JCheckBox aireCk;
	private JButton crearButton;
	
	private JPanel menuPadre;
	private JLabel paradaOrigenLabel;
	private JLabel paradaDestinoLabel;
	private JComboBox origenComboBx;
	private JComboBox destinoComboBx;
	private JLabel trayectoLabel;
	private JComboBox trayectoComboBx;

	/**
	 * Create the panel.
	 */
	
	public CrearLineaBody(JPanel menuPadre) {
		this();
		this.menuPadre = menuPadre;
	}
	
	private CrearLineaBody() {
		setBorder(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 146, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		tipoLineaLabel = new JLabel("Tipo de línea");
		GridBagConstraints gbc_tipoLineaLabel = new GridBagConstraints();
		gbc_tipoLineaLabel.anchor = GridBagConstraints.EAST;
		gbc_tipoLineaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tipoLineaLabel.gridx = 1;
		gbc_tipoLineaLabel.gridy = 1;
		add(tipoLineaLabel, gbc_tipoLineaLabel);
		
		tipoLineaComboBx = new JComboBox();
		tipoLineaComboBx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tipoLineaComboBx.getSelectedItem() == LineaTipoEnum.ECONOMICA) {
					wifiCk.setEnabled(false);
					aireCk.setEnabled(false);
					capParadoText.setEnabled(true);
					validate();
				}else {
					wifiCk.setEnabled(true);
					aireCk.setEnabled(true);
					capParadoText.setEnabled(false);
					validate();
				}
			}
		});
		tipoLineaComboBx.setModel(new DefaultComboBoxModel(LineaTipoEnum.values()));
		GridBagConstraints gbc_tipoLineaComboBx = new GridBagConstraints();
		gbc_tipoLineaComboBx.insets = new Insets(0, 0, 5, 5);
		gbc_tipoLineaComboBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_tipoLineaComboBx.gridx = 2;
		gbc_tipoLineaComboBx.gridy = 1;
		add(tipoLineaComboBx, gbc_tipoLineaComboBx);
		
		colorLabel = new JLabel("Color");
		GridBagConstraints gbc_colorLabel = new GridBagConstraints();
		gbc_colorLabel.anchor = GridBagConstraints.EAST;
		gbc_colorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_colorLabel.gridx = 1;
		gbc_colorLabel.gridy = 2;
		add(colorLabel, gbc_colorLabel);
		
		colorLineaComboBx = new JComboBox();
		colorLineaComboBx.setModel(new DefaultComboBoxModel(ColorLinea.values()));
		GridBagConstraints gbc_colorLineaComboBx = new GridBagConstraints();
		gbc_colorLineaComboBx.insets = new Insets(0, 0, 5, 5);
		gbc_colorLineaComboBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_colorLineaComboBx.gridx = 2;
		gbc_colorLineaComboBx.gridy = 2;
		add(colorLineaComboBx, gbc_colorLineaComboBx);
		
		nombreLabel = new JLabel("Nombre de la línea");
		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
		gbc_nombreLabel.anchor = GridBagConstraints.EAST;
		gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLabel.gridx = 1;
		gbc_nombreLabel.gridy = 3;
		add(nombreLabel, gbc_nombreLabel);
		
		nombreLineaText = new JTextField();
		GridBagConstraints gbc_nombreLineaText = new GridBagConstraints();
		gbc_nombreLineaText.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLineaText.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreLineaText.gridx = 2;
		gbc_nombreLineaText.gridy = 3;
		add(nombreLineaText, gbc_nombreLineaText);
		nombreLineaText.setColumns(10);
		
		capSentadoLabel = new JLabel("Cap. Sentado");
		GridBagConstraints gbc_capSentadoLabel = new GridBagConstraints();
		gbc_capSentadoLabel.anchor = GridBagConstraints.EAST;
		gbc_capSentadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_capSentadoLabel.gridx = 1;
		gbc_capSentadoLabel.gridy = 4;
		add(capSentadoLabel, gbc_capSentadoLabel);
		
		capSentadoText = new JTextField();
		GridBagConstraints gbc_capSentadoText = new GridBagConstraints();
		gbc_capSentadoText.insets = new Insets(0, 0, 5, 5);
		gbc_capSentadoText.fill = GridBagConstraints.HORIZONTAL;
		gbc_capSentadoText.gridx = 2;
		gbc_capSentadoText.gridy = 4;
		add(capSentadoText, gbc_capSentadoText);
		capSentadoText.setColumns(10);
		
		wifiCk = new JCheckBox("Wifi");
		GridBagConstraints gbc_wifiCk = new GridBagConstraints();
		gbc_wifiCk.anchor = GridBagConstraints.WEST;
		gbc_wifiCk.insets = new Insets(0, 0, 5, 5);
		gbc_wifiCk.gridx = 2;
		gbc_wifiCk.gridy = 5;
		wifiCk.setEnabled(false);
		add(wifiCk, gbc_wifiCk);
		
		aireCk = new JCheckBox("Aire");
		GridBagConstraints gbc_aireCk = new GridBagConstraints();
		gbc_aireCk.anchor = GridBagConstraints.WEST;
		gbc_aireCk.insets = new Insets(0, 0, 5, 5);
		gbc_aireCk.gridx = 2;
		gbc_aireCk.gridy = 6;
		aireCk.setEnabled(false);
		add(aireCk, gbc_aireCk);
		
		paradaOrigenLabel = new JLabel("Parada origen");
		GridBagConstraints gbc_paradaOrigenLabel = new GridBagConstraints();
		gbc_paradaOrigenLabel.anchor = GridBagConstraints.EAST;
		gbc_paradaOrigenLabel.insets = new Insets(0, 0, 5, 5);
		gbc_paradaOrigenLabel.gridx = 1;
		gbc_paradaOrigenLabel.gridy = 7;
		add(paradaOrigenLabel, gbc_paradaOrigenLabel);
		
		origenComboBx = new JComboBox();
		GridBagConstraints gbc_origenComboBx = new GridBagConstraints();
		gbc_origenComboBx.insets = new Insets(0, 0, 5, 5);
		gbc_origenComboBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_origenComboBx.gridx = 2;
		gbc_origenComboBx.gridy = 7;
		add(origenComboBx, gbc_origenComboBx);
		
		paradaDestinoLabel = new JLabel("Parada destino");
		GridBagConstraints gbc_paradaDestinoLabel = new GridBagConstraints();
		gbc_paradaDestinoLabel.anchor = GridBagConstraints.EAST;
		gbc_paradaDestinoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_paradaDestinoLabel.gridx = 1;
		gbc_paradaDestinoLabel.gridy = 8;
		add(paradaDestinoLabel, gbc_paradaDestinoLabel);
		
		destinoComboBx = new JComboBox();
		GridBagConstraints gbc_destinoComboBx = new GridBagConstraints();
		gbc_destinoComboBx.insets = new Insets(0, 0, 5, 5);
		gbc_destinoComboBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_destinoComboBx.gridx = 2;
		gbc_destinoComboBx.gridy = 8;
		add(destinoComboBx, gbc_destinoComboBx);
		
		trayectoLabel = new JLabel("Trayecto");
		GridBagConstraints gbc_trayectoLabel = new GridBagConstraints();
		gbc_trayectoLabel.anchor = GridBagConstraints.EAST;
		gbc_trayectoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_trayectoLabel.gridx = 1;
		gbc_trayectoLabel.gridy = 9;
		add(trayectoLabel, gbc_trayectoLabel);
		
		trayectoComboBx = new JComboBox();
		GridBagConstraints gbc_trayectoComboBx = new GridBagConstraints();
		gbc_trayectoComboBx.insets = new Insets(0, 0, 5, 5);
		gbc_trayectoComboBx.fill = GridBagConstraints.HORIZONTAL;
		gbc_trayectoComboBx.gridx = 2;
		gbc_trayectoComboBx.gridy = 9;
		add(trayectoComboBx, gbc_trayectoComboBx);
		
		crearButton = new JButton("Crear");
		crearButton.addActionListener( e -> crearLinea(e));
		GridBagConstraints gbc_crearButton = new GridBagConstraints();
		gbc_crearButton.insets = new Insets(0, 0, 5, 0);
		gbc_crearButton.gridwidth = 4;
		gbc_crearButton.gridx = 2;
		gbc_crearButton.gridy = 10;
		add(crearButton, gbc_crearButton);

	}

	private void crearLinea(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tipoLineaComboBx.getSelectedItem() == LineaTipoEnum.ECONOMICA) {
			crearLineaEconomica();
		}else if(tipoLineaComboBx.getSelectedItem() == LineaTipoEnum.SUPERIOR){
			crearLineaSuperior();
		}
		
	}

	private void crearLineaSuperior() {
		// TODO Auto-generated method stub
		String 
		
	}

	private void crearLineaEconomica() {
		// TODO Auto-generated method stub
		
	}

}
