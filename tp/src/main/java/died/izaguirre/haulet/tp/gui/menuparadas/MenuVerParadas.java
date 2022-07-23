package died.izaguirre.haulet.tp.gui.menuparadas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import died.izaguirre.haulet.tp.tablas.Parada;

public class MenuVerParadas extends JPanel {
	private JPanel header;
	private JLabel emptyLabel;
	private JLabel titulo;
	private JLabel emptyLabel_2;
	private JTable table;
	private JLabel lblNewLabel_2;
	private JPanel body;
	private JSeparator separator;
	private JPanel panelDeCreacion;
	private JLabel emptyLabel_3;
	private JLabel nroParadaLabel;
	private JLabel deleteButton;
	private JLabel emptyLabel_4;
	private JLabel calleLabel;
	private JTextField textField;
	private JTextField textField_1;
	private JButton crearButton;
	private JPanel panelDeTabla;
	private JPanel panelBuscadorTable;
	private JLabel emptyLabel_5;
	private JTextField buscadorTableText;
	private JLabel buscarButtonTable;
	private JLabel emptyLabel_6;
	private JScrollPane scrollPane;
	private JTable paradasTable;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public MenuVerParadas() {
		setLayout(new BorderLayout(0, 0));
		
		header = new JPanel();
		add(header, BorderLayout.NORTH);
		GridBagLayout gbl_header = new GridBagLayout();
		gbl_header.columnWeights = new double[]{0.0};
		gbl_header.rowWeights = new double[]{0.0, 0.0, 0.0};
		header.setLayout(gbl_header);
		
		emptyLabel = new JLabel("");
		GridBagConstraints gbc_emptyLabel = new GridBagConstraints();
		gbc_emptyLabel.weightx = 0.5;
		gbc_emptyLabel.insets = new Insets(0, 0, 5, 0);
		gbc_emptyLabel.gridx = 0;
		gbc_emptyLabel.gridy = 0;
		header.add(emptyLabel, gbc_emptyLabel);
		
		titulo = new JLabel("GESTOR DE PARADAS");
		GridBagConstraints gbc_titulo = new GridBagConstraints();
		gbc_titulo.insets = new Insets(0, 0, 5, 0);
		gbc_titulo.gridx = 0;
		gbc_titulo.gridy = 1;
		header.add(titulo, gbc_titulo);
		
		emptyLabel_2 = new JLabel("");
		GridBagConstraints gbc_emptyLabel_2 = new GridBagConstraints();
		gbc_emptyLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_emptyLabel_2.weightx = 0.5;
		gbc_emptyLabel_2.gridx = 0;
		gbc_emptyLabel_2.gridy = 2;
		header.add(emptyLabel_2, gbc_emptyLabel_2);
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		header.add(separator, gbc_separator);
		
		body = new JPanel();
		add(body, BorderLayout.CENTER);
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
		panelDeCreacion = new JPanel();
		body.add(panelDeCreacion);
		GridBagLayout gbl_panelDeCreacion = new GridBagLayout();
		gbl_panelDeCreacion.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelDeCreacion.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelDeCreacion.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDeCreacion.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDeCreacion.setLayout(gbl_panelDeCreacion);
		
		emptyLabel_3 = new JLabel("");
		GridBagConstraints gbc_emptyLabel_3 = new GridBagConstraints();
		gbc_emptyLabel_3.weightx = 0.5;
		gbc_emptyLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_emptyLabel_3.gridx = 0;
		gbc_emptyLabel_3.gridy = 0;
		panelDeCreacion.add(emptyLabel_3, gbc_emptyLabel_3);
		
		nroParadaLabel = new JLabel("Nro. Parada");
		GridBagConstraints gbc_nroParadaLabel = new GridBagConstraints();
		gbc_nroParadaLabel.anchor = GridBagConstraints.EAST;
		gbc_nroParadaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nroParadaLabel.gridx = 1;
		gbc_nroParadaLabel.gridy = 0;
		panelDeCreacion.add(nroParadaLabel, gbc_nroParadaLabel);
		
		deleteButton = new JLabel("");
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.insets = new Insets(0, 0, 5, 5);
		gbc_deleteButton.gridx = 3;
		gbc_deleteButton.gridy = 0;
		ImageIcon imgDeleteButton = new ImageIcon(getClass().getResource("/delete.png"));
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		panelDeCreacion.add(textField, gbc_textField);
		textField.setColumns(10);
		deleteButton.setIcon(imgDeleteButton);
		panelDeCreacion.add(deleteButton, gbc_deleteButton);
		
		emptyLabel_4 = new JLabel("");
		GridBagConstraints gbc_emptyLabel_4 = new GridBagConstraints();
		gbc_emptyLabel_4.weightx = 0.8;
		gbc_emptyLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_emptyLabel_4.gridx = 4;
		gbc_emptyLabel_4.gridy = 0;
		panelDeCreacion.add(emptyLabel_4, gbc_emptyLabel_4);
		
		calleLabel = new JLabel("Calle");
		GridBagConstraints gbc_calleLabel = new GridBagConstraints();
		gbc_calleLabel.anchor = GridBagConstraints.EAST;
		gbc_calleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_calleLabel.gridx = 1;
		gbc_calleLabel.gridy = 1;
		panelDeCreacion.add(calleLabel, gbc_calleLabel);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 1;
		panelDeCreacion.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		crearButton = new JButton("Crear Parada");
		GridBagConstraints gbc_crearButton = new GridBagConstraints();
		gbc_crearButton.insets = new Insets(0, 0, 0, 5);
		gbc_crearButton.gridx = 2;
		gbc_crearButton.gridy = 2;
		panelDeCreacion.add(crearButton, gbc_crearButton);
		
		panelDeTabla = new JPanel();
		body.add(panelDeTabla);
		panelDeTabla.setLayout(new BorderLayout(0, 0));
		
		panelBuscadorTable = new JPanel();
		panelDeTabla.add(panelBuscadorTable, BorderLayout.NORTH);
		GridBagLayout gbl_panelBuscadorTable = new GridBagLayout();
		gbl_panelBuscadorTable.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelBuscadorTable.rowHeights = new int[] {0};
		gbl_panelBuscadorTable.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelBuscadorTable.rowWeights = new double[]{0.0};
		panelBuscadorTable.setLayout(gbl_panelBuscadorTable);
		
		emptyLabel_5 = new JLabel("");
		GridBagConstraints gbc_emptyLabel_5 = new GridBagConstraints();
		gbc_emptyLabel_5.weightx = 0.5;
		gbc_emptyLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_emptyLabel_5.gridx = 0;
		gbc_emptyLabel_5.gridy = 0;
		panelBuscadorTable.add(emptyLabel_5, gbc_emptyLabel_5);
		
		buscadorTableText = new JTextField();
		GridBagConstraints gbc_buscadorTableText = new GridBagConstraints();
		gbc_buscadorTableText.insets = new Insets(0, 0, 5, 5);
		gbc_buscadorTableText.fill = GridBagConstraints.HORIZONTAL;
		gbc_buscadorTableText.gridx = 1;
		gbc_buscadorTableText.gridy = 0;
		panelBuscadorTable.add(buscadorTableText, gbc_buscadorTableText);
		buscadorTableText.setColumns(10);
		
		buscarButtonTable = new JLabel("");
		GridBagConstraints gbc_buscarButtonTable = new GridBagConstraints();
		gbc_buscarButtonTable.anchor = GridBagConstraints.WEST;
		gbc_buscarButtonTable.insets = new Insets(0, 0, 5, 0);
		gbc_buscarButtonTable.gridx = 2;
		gbc_buscarButtonTable.gridy = 0;
		ImageIcon imgBuscar = new ImageIcon(getClass().getResource("/magnify.png"));
		buscarButtonTable.setIcon(imgBuscar);
		panelBuscadorTable.add(buscarButtonTable, gbc_buscarButtonTable);
		
		emptyLabel_6 = new JLabel("");
		GridBagConstraints gbc_emptyLabel_6 = new GridBagConstraints();
		gbc_emptyLabel_6.weightx = 0.8;
		gbc_emptyLabel_6.insets = new Insets(0, 0, 0, 5);
		gbc_emptyLabel_6.gridx = 3;
		gbc_emptyLabel_6.gridy = 0;
		panelBuscadorTable.add(emptyLabel_6, gbc_emptyLabel_6);
		
		scrollPane = new JScrollPane();
		panelDeTabla.add(scrollPane, BorderLayout.CENTER);
		
		paradasTable = new JTable();
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nro. Parada", "Calle", "Eliminar"
				}
			);
		paradasTable.setModel(model);
		scrollPane.setViewportView(paradasTable);

	}
	
	public void agregarParada(Parada p) {
		
		ImageIcon eliminarAux = new ImageIcon(getClass().getResource("/delete.png"));
		
		Object[] nuevaParada = new Object[3];
		
		nuevaParada[0] = p.getNroParada();
		nuevaParada[1] = p.getCalle();
		nuevaParada[2] = eliminarAux;
		
		model.addRow(nuevaParada);
		
	}

}
