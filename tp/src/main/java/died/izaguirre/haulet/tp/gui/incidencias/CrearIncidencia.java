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
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import died.izaguirre.haulet.tp.controladores.ControladorIncidencia;
import died.izaguirre.haulet.tp.controladores.ControladorParadas;
import died.izaguirre.haulet.tp.tablas.Incidencia;
import died.izaguirre.haulet.tp.tablas.Parada;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.SystemColor;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearIncidencia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Incidencia modificar;
	private JFrame padre;
	private ArrayList<Parada> paradas;
	private ArrayList<Incidencia> incidencias;
	private ControladorIncidencia controlador = new ControladorIncidencia();
	
	private JComboBox<Parada> comboBox = new JComboBox();
	private JDateChooser dateChooser = new JDateChooser();
	private JDateChooser dateChooser2 = new JDateChooser();
	private JComboBox comboBox2 = new JComboBox();
	private JTextField textField;
	private JCheckBox chckbxNewCheckBox = new JCheckBox("Resuelto");
	
	public CrearIncidencia(JFrame padre, Incidencia modificar) 
	{
		super(padre,"Crear incidencia",Dialog.ModalityType.DOCUMENT_MODAL);
		this.padre = padre;
		this.modificar = modificar;
		CrearInterfaz();
		cargarParadas();
		if(!paradas.isEmpty()) {
			cargarIncidencias();
			if(modificar != null) completarCampos();
			setVisible(true);
		}
	}
	
	private void cargarParadas() 
	{
		paradas = ControladorParadas.buscarParadas();
		if(paradas.isEmpty()) {
			JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true);
			dialog.setLocationRelativeTo(padre);
			JOptionPane.showMessageDialog(dialog, "No hay paradas disponibles",
					"Error", JOptionPane.ERROR_MESSAGE);
			dispose();
			}
		else {
			paradas.forEach(it -> 
			{
				comboBox.addItem(it);
			});
			comboBox.setSelectedIndex(0);
		}
	}
	
	private void cargarIncidencias() 
	{
		incidencias = controlador.buscarIncidencias();
	}
	
	private void restringirFecha() 
	{
		if(dateChooser.getDate() != null) 
		{
			dateChooser2.setMinSelectableDate(dateChooser.getDate());
		} if(dateChooser2.getDate() != null) 
		{
			dateChooser.setMaxSelectableDate(dateChooser2.getDate());
		}
	}
	
	private void completarCampos() 
	{
		comboBox.setSelectedItem(modificar.getParada());
		dateChooser.setDate(Date.from(modificar.getFechaInicio().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		if(modificar.getFechaFin() != null)
		dateChooser2.setDate(Date.from(modificar.getFechaFin().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		comboBox2.setSelectedItem(modificar.getMotivo());
		textField.setText(modificar.getDescripción());
		chckbxNewCheckBox.setSelected(modificar.getEstaResuelto());
		chckbxNewCheckBox.setEnabled(true);
	}
	
	private void validarDatos() 
	{
		boolean errorFecha = false;
		boolean actualizar = modificar == null ? false : true;
		Parada parada = (Parada) comboBox.getSelectedItem();
		LocalDate inicio = dateChooser.getDate() != null ? dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
		LocalDate fin = dateChooser2.getDate() != null ? dateChooser2.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
		String motivo = (String) comboBox2.getSelectedItem();
		String descripcion = textField.getText().length() >= 100 ? textField.getText().substring(0,99) : textField.getText();
		
		if(inicio == null) { 
			JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true);
			dialog.setLocationRelativeTo(padre);
			JOptionPane.showMessageDialog(dialog, "Por favor inserte una fecha de inicio",
					"Error", JOptionPane.ERROR_MESSAGE);
			dateChooser.setBorder(new LineBorder(Color.RED));
			return;
		} else 
		{
			dateChooser.setBorder(new JDateChooser().getBorder());
			if(actualizar) incidencias.remove(modificar);
			if(!incidencias.isEmpty() && fin != null) 
			{
				errorFecha = incidencias.stream().filter(it -> it.getParada().equals(parada)).anyMatch(it -> 
				{
					return estaDentroRango(inicio, fin, it.getFechaInicio(), it.getFechaFin());
				});
			}
		}
		
		if(errorFecha) { 
			JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true);    
			dialog.setLocationRelativeTo(padre);
			JOptionPane.showMessageDialog(dialog, "Ya existe otra incidencia activa en esa parada y rango de fechas",
					"Colisión de incidencias", JOptionPane.ERROR_MESSAGE);
			dateChooser.setBorder(new LineBorder(Color.RED));
			dateChooser2.setBorder(new LineBorder(Color.RED));
		} else 
		{
			Incidencia incidencia = modificar == null ? new Incidencia() : modificar;
			dateChooser.setBorder(new JDateChooser().getBorder());
			dateChooser2.setBorder(new JDateChooser().getBorder());
			incidencia.setParada(parada);
			incidencia.setFechaInicio(inicio);
			incidencia.setFechaFin(fin);
			incidencia.setEstaResuelto(chckbxNewCheckBox.isSelected());
			incidencia.setDescripción(descripcion);
			incidencia.setMotivo(motivo);
			boolean exito = controlador.guardarIncidencia(incidencia,actualizar);
			if(exito) 
			{
				JDialog dialog = new JDialog();
				dialog.setAlwaysOnTop(true);
				dialog.setLocationRelativeTo(padre);
				String mensaje = actualizar ? "Incidencia actualizada con éxito" : "Incidencia agregada con éxito";
				JOptionPane.showMessageDialog(dialog, mensaje,
						"Éxito", JOptionPane.INFORMATION_MESSAGE);
				if(actualizar) dispose();
				incidencias.add(incidencia);
				dateChooser.setDate(null);
				dateChooser.setMinSelectableDate(new JDateChooser().getMinSelectableDate());
				dateChooser.setMaxSelectableDate(new JDateChooser().getMaxSelectableDate());
				dateChooser2.setDate(null);
				dateChooser2.setMinSelectableDate(new JDateChooser().getMinSelectableDate());
				dateChooser2.setMaxSelectableDate(new JDateChooser().getMaxSelectableDate());
				textField.setText("");
			}
			else 
			{
				JDialog dialog = new JDialog();
				dialog.setAlwaysOnTop(true);    
				dialog.setLocationRelativeTo(padre);
				JOptionPane.showMessageDialog(dialog, "Ha ocurrido un error al guardar",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	//Comprueba que no exista otra incidencia activa en ese rango
	private boolean estaDentroRango(LocalDate inicio1, LocalDate fin1, LocalDate inicio2, LocalDate fin2) 
	{
		boolean valor = false;
		if(fin1 == null && fin2 != null) 
		{
			valor = inicio1.isAfter(inicio2) && inicio1.isBefore(fin2);			
		}
		else 
			if(fin2 == null && fin1 != null) 
			{
				valor = inicio2.isAfter(inicio1) && inicio2.isBefore(fin1);		
			}
			else if(fin1 != null && fin2 != null) 
			{
				valor = (inicio1.isBefore(inicio2) && fin1.isBefore(inicio2)) || (inicio1.isAfter(fin2) && fin1.isAfter(fin2));
				valor = !valor;
			}
		return valor;
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
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
			GridBagConstraints gbc_dateChooser = new GridBagConstraints();
			gbc_dateChooser.fill = GridBagConstraints.BOTH;
			gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
			gbc_dateChooser.gridx = 2;
			gbc_dateChooser.gridy = 2;
			dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					restringirFecha();
				}
			});
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
			GridBagConstraints gbc_dateChooser = new GridBagConstraints();
			gbc_dateChooser.fill = GridBagConstraints.BOTH;
			gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
			gbc_dateChooser.gridx = 2;
			gbc_dateChooser.gridy = 3;
			dateChooser2.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					restringirFecha();
				}
			});
			contentPanel.add(dateChooser2, gbc_dateChooser);
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
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.BOTH;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 4;
			comboBox2.setModel(new DefaultComboBoxModel(new String[] {"ACCIDENTE", "MANTENIMIENTO", "PROTESTA", "OTRO"}));
			contentPanel.add(comboBox2, gbc_comboBox);
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
			chckbxNewCheckBox.setEnabled(false);
			GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
			gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
			gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxNewCheckBox.gridx = 2;
			gbc_chckbxNewCheckBox.gridy = 6;
			contentPanel.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Los elementos marcados (*) son obligatorios");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 11));
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
			gbc_lblNewLabel_5.insets = new Insets(20, 0, 5, 0);
			gbc_lblNewLabel_5.ipadx = 5;
			gbc_lblNewLabel_5.gridx = 3;
			gbc_lblNewLabel_5.gridy = 7;
			contentPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		}
		{
			JSeparator separator = new JSeparator();
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.fill = GridBagConstraints.HORIZONTAL;
			gbc_separator.gridwidth = 4;
			gbc_separator.insets = new Insets(15, 0, 5, 0);
			gbc_separator.gridx = 0;
			gbc_separator.gridy = 8;
			contentPanel.add(separator, gbc_separator);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CREAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						validarDatos();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();
		//revalidate();
		//repaint();
		setLocationRelativeTo(padre);
	}

}
