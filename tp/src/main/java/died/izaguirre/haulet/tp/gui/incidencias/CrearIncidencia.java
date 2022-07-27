package died.izaguirre.haulet.tp.gui.incidencias;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.SystemColor;
import javax.swing.JTextField;

public class CrearIncidencia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JFrame padre;
	
	public CrearIncidencia(JFrame padre) 
	{
		super(padre,"Crear incidencia",Dialog.ModalityType.DOCUMENT_MODAL);
		this.padre = padre;
		CrearInterfaz();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();
		//revalidate();
		//repaint();
		setLocationRelativeTo(padre);
		setVisible(true);
	}

	
	private void CrearInterfaz() {
		setAlwaysOnTop(true);
		setTitle("Sistema de Transporte");
		setResizable(false);
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Crear Incidencia");
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
			gbc_separator.gridheight = 5;
			gbc_separator.fill = GridBagConstraints.VERTICAL;
			gbc_separator.insets = new Insets(0, 10, 5, 5);
			gbc_separator.gridx = 0;
			gbc_separator.gridy = 1;
			contentPanel.add(separator, gbc_separator);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Parada*");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 1;
			lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/map-marker-plus.png")));
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"CANDIOTI", "ALBERDI", "LAVAISSE", "SUNCHALES"}));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.BOTH;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 1;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Inicio*");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 5);
			gbc_lblNewLabel_2.gridx = 1;
			gbc_lblNewLabel_2.gridy = 2;
			lblNewLabel_2.setIcon(new ImageIcon(getClass().getResource("/calendar-range.png")));
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			JDateChooser dateChooser = new JDateChooser();
			GridBagConstraints gbc_dateChooser = new GridBagConstraints();
			gbc_dateChooser.fill = GridBagConstraints.BOTH;
			gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
			gbc_dateChooser.gridx = 2;
			gbc_dateChooser.gridy = 2;
			dateChooser.setPreferredSize(new Dimension(150,dateChooser.getHeight()));
			contentPanel.add(dateChooser, gbc_dateChooser);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Fin");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_3.insets = new Insets(0, 5, 5, 5);
			gbc_lblNewLabel_3.gridx = 1;
			gbc_lblNewLabel_3.gridy = 3;
			lblNewLabel_3.setIcon(new ImageIcon(getClass().getResource("/calendar-range.png")));
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			JDateChooser dateChooser = new JDateChooser();
			GridBagConstraints gbc_dateChooser = new GridBagConstraints();
			gbc_dateChooser.fill = GridBagConstraints.BOTH;
			gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
			gbc_dateChooser.gridx = 2;
			gbc_dateChooser.gridy = 3;
			contentPanel.add(dateChooser, gbc_dateChooser);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Motivo*");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
			gbc_lblNewLabel_4.insets = new Insets(0, 5, 5, 5);
			gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_4.gridx = 1;
			gbc_lblNewLabel_4.gridy = 4;
			lblNewLabel_4.setIcon(new ImageIcon(getClass().getResource("/paperclip.png")));
			contentPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"ACCIDENTE", "MANTENIMIENTO", "PROTESTA", "OTRO"}));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.BOTH;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 4;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Descripción");
			GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
			gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_6.insets = new Insets(0, 5, 5, 5);
			gbc_lblNewLabel_6.gridx = 1;
			gbc_lblNewLabel_6.gridy = 5;
			lblNewLabel_6.setIcon(new ImageIcon(getClass().getResource("/file-outline.png")));
			contentPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 2;
			gbc_textField.gridy = 5;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Los elementos marcados (*) son obligatorios");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 11));
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
			gbc_lblNewLabel_5.insets = new Insets(20, 0, 5, 0);
			gbc_lblNewLabel_5.ipadx = 5;
			gbc_lblNewLabel_5.gridx = 3;
			gbc_lblNewLabel_5.gridy = 6;
			contentPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		}
		{
			JSeparator separator = new JSeparator();
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.fill = GridBagConstraints.HORIZONTAL;
			gbc_separator.gridwidth = 4;
			gbc_separator.insets = new Insets(15, 0, 5, 0);
			gbc_separator.gridx = 0;
			gbc_separator.gridy = 7;
			contentPanel.add(separator, gbc_separator);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CREAR");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
