package died.izaguirre.haulet.tp.gui.principal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import died.izaguirre.haulet.tp.gui.utilities.Precios;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.Dialog;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class EstablecerKm extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JSpinner spinnerKm;
	private JSpinner spinnerEconomica;
	private JSpinner spinnerSuperior;
	private JSpinner spinnerWifi;
	private JSpinner spinnerAC;

	public EstablecerKm(JFrame padre) {
		
		super(padre,"Parámetros",Dialog.ModalityType.DOCUMENT_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel_3 = new JLabel("Valores municipales");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setBackground(Color.BLUE);
			lblNewLabel_3.setOpaque(true);
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
			gbc_lblNewLabel_3.gridwidth = 2;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 1;
			gbc_lblNewLabel_3.gridy = 1;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			JLabel lblNewLabel = new JLabel("Precio por kilómetro");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 2;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			spinnerKm = new JSpinner();
			spinnerKm.setModel(new SpinnerNumberModel(Precios.precioKm, 0, null, 1));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 2;
			contentPanel.add(spinnerKm, gbc_spinner);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("% Adicional línea superior");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 3;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			spinnerSuperior = new JSpinner();
			int inicial = Math.round(((Precios.porcentajeSuperior - 1f)*100));
			spinnerSuperior .setModel(new SpinnerNumberModel(inicial, 0, null, 1));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 3;
			contentPanel.add(spinnerSuperior , gbc_spinner);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("% Adicional línea económica");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 15);
			gbc_lblNewLabel_2.gridx = 1;
			gbc_lblNewLabel_2.gridy = 4;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			spinnerEconomica = new JSpinner();
			int inicial = Math.round(((Precios.porcentajeEconomica - 1f)*100));
			spinnerEconomica.setModel(new SpinnerNumberModel(inicial, 0, null, 1));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 4;
			contentPanel.add(spinnerEconomica, gbc_spinner);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("% Adicional por AC");
			GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
			gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_4.gridx = 1;
			gbc_lblNewLabel_4.gridy = 5;
			contentPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		}
		{
			spinnerAC = new JSpinner();
			int inicial = Math.round(((Precios.porcentajeAC - 1f)*100));
			spinnerAC.setModel(new SpinnerNumberModel(inicial, 0, null, 1));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 5;
			contentPanel.add(spinnerAC, gbc_spinner);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("% Adicional por Wi-Fi");
			GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
			gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_5.gridx = 1;
			gbc_lblNewLabel_5.gridy = 6;
			contentPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		}
		{
			spinnerWifi = new JSpinner();
			int inicial = Math.round(((Precios.porcentajeWifi - 1f)*100));
			spinnerWifi.setModel(new SpinnerNumberModel(inicial, 0, null, 1));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 6;
			contentPanel.add(spinnerWifi, gbc_spinner);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						int km = (int) spinnerKm.getValue();
						int superior = (int) spinnerSuperior.getValue();
						int economica = (int) spinnerEconomica.getValue();
						int ac = (int) spinnerAC.getValue();
						int wifi = (int) spinnerWifi.getValue();
						Precios.precioKm = km;
						Precios.porcentajeEconomica = (economica/100f) + 1;
						Precios.porcentajeSuperior = (superior/100f) + 1;
						Precios.porcentajeAC = (ac/100f) + 1;
						Precios.porcentajeWifi = (wifi/100f) + 1;
						dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setAlwaysOnTop(true);
		pack();
		setVisible(true);
	}

}
