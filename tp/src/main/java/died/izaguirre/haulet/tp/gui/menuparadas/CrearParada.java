package died.izaguirre.haulet.tp.gui.menuparadas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import died.izaguirre.haulet.tp.controladores.ControladorCrearParada;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;

public class CrearParada extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ControladorCrearParada controlador;
	private JTextField nroParadaTxt;
	private JComboBox calleCBx;
	private JButton okButton;
	private ParadasPanel panelPadre;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel( new FlatDarkLaf() );
//			CrearParada dialog = new CrearParada();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.pack();
//			dialog.revalidate();
//			dialog.repaint();
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */

	public CrearParada(ParadasPanel panelPadre) {
		cargarVista();
		this.panelPadre = panelPadre;
		controlador = new ControladorCrearParada(this);
	}

	private void cargarVista() {
		setAlwaysOnTop(true);
		setTitle("Sistema de Transporte");
		setResizable(false);
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Crear Parada");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setBackground(SystemColor.textHighlight);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblNewLabel.gridwidth = 2;
			gbc_lblNewLabel.insets = new Insets(20, 5, 20, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 0;
			lblNewLabel.setOpaque(true);
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JSeparator separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.gridheight = 2;
			gbc_separator.fill = GridBagConstraints.VERTICAL;
			gbc_separator.insets = new Insets(0, 10, 5, 5);
			gbc_separator.gridx = 0;
			gbc_separator.gridy = 1;
			contentPanel.add(separator, gbc_separator);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Número*");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 5);
			gbc_lblNewLabel_2.gridx = 1;
			gbc_lblNewLabel_2.gridy = 1;
			lblNewLabel_2.setIcon(new ImageIcon(getClass().getResource("/paperclip.png")));
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			nroParadaTxt = new JTextField();
			GridBagConstraints gbc_nroParadaTxt = new GridBagConstraints();
			gbc_nroParadaTxt.insets = new Insets(0, 0, 5, 5);
			gbc_nroParadaTxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_nroParadaTxt.gridx = 2;
			gbc_nroParadaTxt.gridy = 1;
			contentPanel.add(nroParadaTxt, gbc_nroParadaTxt);
			nroParadaTxt.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Calle*");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 2;
			lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/map-marker-plus.png")));
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			calleCBx = new JComboBox();
			calleCBx.setModel(new DefaultComboBoxModel(new String[] { "SUNCHALES", "LAVAISSE", "OTRO" }));
			GridBagConstraints gbc_calleCBx = new GridBagConstraints();
			gbc_calleCBx.insets = new Insets(0, 0, 5, 5);
			gbc_calleCBx.fill = GridBagConstraints.BOTH;
			gbc_calleCBx.gridx = 2;
			gbc_calleCBx.gridy = 2;
			contentPanel.add(calleCBx, gbc_calleCBx);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Los elementos marcados (*) son obligatorios");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 11));
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
			gbc_lblNewLabel_5.insets = new Insets(20, 0, 5, 0);
			gbc_lblNewLabel_5.ipadx = 5;
			gbc_lblNewLabel_5.gridx = 3;
			gbc_lblNewLabel_5.gridy = 5;
			contentPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		}
		{
			JSeparator separator = new JSeparator();
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.fill = GridBagConstraints.HORIZONTAL;
			gbc_separator.gridwidth = 4;
			gbc_separator.insets = new Insets(15, 0, 5, 0);
			gbc_separator.gridx = 0;
			gbc_separator.gridy = 6;
			contentPanel.add(separator, gbc_separator);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("CREAR");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("CANCELAR");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		pack();
	}

	public ControladorCrearParada getControlador() {
		return controlador;
	}

	public void setControlador(ControladorCrearParada controlador) {
		this.controlador = controlador;
	}

	public JTextField getNroParadaTxt() {
		return nroParadaTxt;
	}

	public void setNroParadaTxt(JTextField nroParadaTxt) {
		this.nroParadaTxt = nroParadaTxt;
	}

	public JComboBox getCalleCBx() {
		return calleCBx;
	}

	public void setCalleCBx(JComboBox calleCBx) {
		this.calleCBx = calleCBx;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public ParadasPanel getPanelPadre() {
		return panelPadre;
	}

	public void setPanelPadre(ParadasPanel panelPadre) {
		this.panelPadre = panelPadre;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

}
