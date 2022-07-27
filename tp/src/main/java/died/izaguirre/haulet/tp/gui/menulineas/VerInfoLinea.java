package died.izaguirre.haulet.tp.gui.menulineas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import died.izaguirre.haulet.tp.tablas.linea.LineaColores;
import died.izaguirre.haulet.tp.tablas.linea.LineaTipoEnum;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

public class VerInfoLinea extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nombreLinea;
	private JTextField capSentadoTxt;
	private MenuVerLineas menu;
	private Integer idLinea;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		try {
//			VerInfoLinea dialog = new VerInfoLinea();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
//	private VerInfoLinea() {
//		cargarVista();
//	}
	
	public VerInfoLinea(MenuVerLineas menu, Integer idLinea) {
		this.menu = menu;
		this.idLinea = idLinea;
		cargarVista();
	}
	
	private void cargarVista() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 82, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.weightx = 0.4;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel nombreLineaLabel = new JLabel("Nombre");
			GridBagConstraints gbc_nombreLineaLabel = new GridBagConstraints();
			gbc_nombreLineaLabel.insets = new Insets(0, 0, 5, 5);
			gbc_nombreLineaLabel.anchor = GridBagConstraints.EAST;
			gbc_nombreLineaLabel.gridx = 1;
			gbc_nombreLineaLabel.gridy = 0;
			contentPanel.add(nombreLineaLabel, gbc_nombreLineaLabel);
		}
		{
			nombreLinea = new JTextField();
			nombreLinea.setEditable(false);
			GridBagConstraints gbc_nombreLinea = new GridBagConstraints();
			gbc_nombreLinea.weightx = 0.1;
			gbc_nombreLinea.insets = new Insets(0, 0, 5, 5);
			gbc_nombreLinea.fill = GridBagConstraints.HORIZONTAL;
			gbc_nombreLinea.gridx = 2;
			gbc_nombreLinea.gridy = 0;
			contentPanel.add(nombreLinea, gbc_nombreLinea);
			nombreLinea.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel(" ");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.weightx = 0.1;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 20, 20);
			gbc_lblNewLabel_2.gridx = 3;
			gbc_lblNewLabel_2.gridy = 0;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			JLabel origenLabel = new JLabel("Origen");
			GridBagConstraints gbc_origenLabel = new GridBagConstraints();
			gbc_origenLabel.anchor = GridBagConstraints.EAST;
			gbc_origenLabel.insets = new Insets(0, 0, 5, 5);
			gbc_origenLabel.gridx = 4;
			gbc_origenLabel.gridy = 0;
			contentPanel.add(origenLabel, gbc_origenLabel);
		}
		{
			JComboBox comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.weightx = 0.1;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 5;
			gbc_comboBox.gridy = 0;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.weightx = 0.4;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_1.gridx = 6;
			gbc_lblNewLabel_1.gridy = 0;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			JLabel tipoLineaLabel = new JLabel("Tipo");
			GridBagConstraints gbc_tipoLineaLabel = new GridBagConstraints();
			gbc_tipoLineaLabel.anchor = GridBagConstraints.EAST;
			gbc_tipoLineaLabel.insets = new Insets(0, 0, 5, 5);
			gbc_tipoLineaLabel.gridx = 1;
			gbc_tipoLineaLabel.gridy = 1;
			contentPanel.add(tipoLineaLabel, gbc_tipoLineaLabel);
		}
		{
			JComboBox tipoCBx = new JComboBox();
			tipoCBx.setEnabled(false);
			tipoCBx.setModel(new DefaultComboBoxModel(LineaTipoEnum.values()));
			GridBagConstraints gbc_tipoCBx = new GridBagConstraints();
			gbc_tipoCBx.insets = new Insets(0, 0, 5, 5);
			gbc_tipoCBx.fill = GridBagConstraints.HORIZONTAL;
			gbc_tipoCBx.gridx = 2;
			gbc_tipoCBx.gridy = 1;
			contentPanel.add(tipoCBx, gbc_tipoCBx);
		}
		{
			JLabel destinoLabel = new JLabel("Destino");
			GridBagConstraints gbc_destinoLabel = new GridBagConstraints();
			gbc_destinoLabel.anchor = GridBagConstraints.EAST;
			gbc_destinoLabel.insets = new Insets(0, 0, 5, 5);
			gbc_destinoLabel.gridx = 4;
			gbc_destinoLabel.gridy = 1;
			contentPanel.add(destinoLabel, gbc_destinoLabel);
		}
		{
			JComboBox comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 5;
			gbc_comboBox.gridy = 1;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JLabel colorLabel = new JLabel("Color");
			GridBagConstraints gbc_colorLabel = new GridBagConstraints();
			gbc_colorLabel.anchor = GridBagConstraints.EAST;
			gbc_colorLabel.insets = new Insets(0, 0, 5, 5);
			gbc_colorLabel.gridx = 1;
			gbc_colorLabel.gridy = 2;
			contentPanel.add(colorLabel, gbc_colorLabel);
		}
		{
			JComboBox colorCBx = new JComboBox();
			colorCBx.setEnabled(false);
			colorCBx.setModel(new DefaultComboBoxModel(LineaColores.values()));
			GridBagConstraints gbc_colorCBx = new GridBagConstraints();
			gbc_colorCBx.insets = new Insets(0, 0, 5, 5);
			gbc_colorCBx.fill = GridBagConstraints.HORIZONTAL;
			gbc_colorCBx.gridx = 2;
			gbc_colorCBx.gridy = 2;
			contentPanel.add(colorCBx, gbc_colorCBx);
		}
		{
			JLabel trayectoLabel = new JLabel("Trayecto");
			GridBagConstraints gbc_trayectoLabel = new GridBagConstraints();
			gbc_trayectoLabel.anchor = GridBagConstraints.EAST;
			gbc_trayectoLabel.insets = new Insets(0, 0, 5, 5);
			gbc_trayectoLabel.gridx = 4;
			gbc_trayectoLabel.gridy = 2;
			contentPanel.add(trayectoLabel, gbc_trayectoLabel);
		}
		{
			JComboBox comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 5;
			gbc_comboBox.gridy = 2;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JLabel capSentadoLabel = new JLabel("Cap. Sentado");
			GridBagConstraints gbc_capSentadoLabel = new GridBagConstraints();
			gbc_capSentadoLabel.anchor = GridBagConstraints.EAST;
			gbc_capSentadoLabel.insets = new Insets(0, 0, 5, 5);
			gbc_capSentadoLabel.gridx = 1;
			gbc_capSentadoLabel.gridy = 3;
			contentPanel.add(capSentadoLabel, gbc_capSentadoLabel);
		}
		{
			capSentadoTxt = new JTextField();
			capSentadoTxt.setEditable(false);
			GridBagConstraints gbc_capSentadoTxt = new GridBagConstraints();
			gbc_capSentadoTxt.insets = new Insets(0, 0, 5, 5);
			gbc_capSentadoTxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_capSentadoTxt.gridx = 2;
			gbc_capSentadoTxt.gridy = 3;
			contentPanel.add(capSentadoTxt, gbc_capSentadoTxt);
			capSentadoTxt.setColumns(10);
		}
		{
			JCheckBox wifiCk = new JCheckBox("Wifi");
			wifiCk.setEnabled(false);
			GridBagConstraints gbc_wifiCk = new GridBagConstraints();
			gbc_wifiCk.insets = new Insets(0, 0, 5, 5);
			gbc_wifiCk.anchor = GridBagConstraints.WEST;
			gbc_wifiCk.gridx = 2;
			gbc_wifiCk.gridy = 4;
			contentPanel.add(wifiCk, gbc_wifiCk);
		}
		{
			JCheckBox aireCk = new JCheckBox("Aire");
			aireCk.setEnabled(false);
			GridBagConstraints gbc_aireCk = new GridBagConstraints();
			gbc_aireCk.insets = new Insets(0, 0, 0, 5);
			gbc_aireCk.anchor = GridBagConstraints.WEST;
			gbc_aireCk.gridx = 2;
			gbc_aireCk.gridy = 5;
			contentPanel.add(aireCk, gbc_aireCk);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton modificarButton = new JButton("Modificar");
				modificarButton.setActionCommand("OK");
				buttonPane.add(modificarButton);
				getRootPane().setDefaultButton(modificarButton);
			}
			{
				JButton guardarButton = new JButton("Guardar");
				buttonPane.add(guardarButton);
			}
			{
				JButton salirButton = new JButton("Salir");
				salirButton.setActionCommand("Cancel");
				buttonPane.add(salirButton);
			}
		}
	}
}
