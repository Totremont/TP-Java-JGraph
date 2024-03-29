package died.izaguirre.haulet.tp.gui.menuparadas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import died.izaguirre.haulet.tp.controladores.ControladorParadas;
import died.izaguirre.haulet.tp.gui.utilities.TableUtility;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.JScrollBar;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

public class ParadasPanel extends JPanel {
	private JTable table;
	private JTextField buscadorTxt;
	private DefaultTableModel tableModel;
	private TableRowSorter<TableModel> tableSorter;
	private ControladorParadas controlador;
	private JLabel calleResumenTxt;
	private JLabel paradasAdyResumenTxt;
	private JLabel numeroParadaResumenTxt;
	private JLabel pintarAdyacentesResumenLbl;
	private JLabel lblNewLabel_3;

	private JFrame ventanaPadre;
	private JButton caminosButton;
	private JLabel agregarButton = new JLabel("Agregar");

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public ParadasPanel(JFrame ventanaPadre) {
		this.ventanaPadre = ventanaPadre;
		setBorder(null);
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0 };
		JScrollPane scrollPane2 = new JScrollPane();
		panel.setBorder(null);
		panel.setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Paradas");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(UIManager.getColor("TextField.selectionBackground"));
		lblNewLabel.setOpaque(true);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 5, 10, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Resumen");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.gridwidth = 2;
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 1;
		gbc_separator_2.gridy = 1;
		panel.add(separator_2, gbc_separator_2);

		numeroParadaResumenTxt = new JLabel("");
		GridBagConstraints gbc_numeroParadaResumenTxt = new GridBagConstraints();
		gbc_numeroParadaResumenTxt.insets = new Insets(0, 0, 5, 0);
		gbc_numeroParadaResumenTxt.gridx = 3;
		gbc_numeroParadaResumenTxt.gridy = 1;
		panel.add(numeroParadaResumenTxt, gbc_numeroParadaResumenTxt);

		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.activeCaption);
		separator.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridheight = 2;
		gbc_separator.fill = GridBagConstraints.VERTICAL;
		gbc_separator.insets = new Insets(5, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		panel.add(separator, gbc_separator);

		JLabel lblNewLabel_2 = new JLabel("Calle");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(5, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		calleResumenTxt = new JLabel("-");
		calleResumenTxt.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_calleResumenTxt = new GridBagConstraints();
		gbc_calleResumenTxt.fill = GridBagConstraints.BOTH;
		gbc_calleResumenTxt.insets = new Insets(5, 0, 5, 5);
		gbc_calleResumenTxt.gridx = 2;
		gbc_calleResumenTxt.gridy = 2;
		panel.add(calleResumenTxt, gbc_calleResumenTxt);

		lblNewLabel_3 = new JLabel("Paradas adyacentes");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 3;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		paradasAdyResumenTxt = new JLabel("");
		GridBagConstraints gbc_paradasAdyResumenTxt = new GridBagConstraints();
		gbc_paradasAdyResumenTxt.insets = new Insets(0, 0, 5, 5);
		gbc_paradasAdyResumenTxt.gridx = 2;
		gbc_paradasAdyResumenTxt.gridy = 3;
		panel.add(paradasAdyResumenTxt, gbc_paradasAdyResumenTxt);

		pintarAdyacentesResumenLbl = new JLabel("");
		pintarAdyacentesResumenLbl.setIcon(new ImageIcon(getClass().getResource("/help-circle.png")));
		GridBagConstraints gbc_pintarAdyacentesResumenLbl = new GridBagConstraints();
		gbc_pintarAdyacentesResumenLbl.insets = new Insets(0, 0, 5, 0);
		gbc_pintarAdyacentesResumenLbl.gridx = 3;
		gbc_pintarAdyacentesResumenLbl.gridy = 3;
		panel.add(pintarAdyacentesResumenLbl, gbc_pintarAdyacentesResumenLbl);

		JLabel lblNewLabel_15 = new JLabel("Lista");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
		gbc_lblNewLabel_15.insets = new Insets(10, 0, 15, 5);
		gbc_lblNewLabel_15.gridx = 0;
		gbc_lblNewLabel_15.gridy = 4;
		panel.add(lblNewLabel_15, gbc_lblNewLabel_15);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 2;
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 4;
		panel.add(separator_1, gbc_separator_1);

		caminosButton = new JButton("Caminos");
		GridBagConstraints gbc_caminosButton = new GridBagConstraints();
		gbc_caminosButton.insets = new Insets(0, 0, 5, 0);
		gbc_caminosButton.gridx = 3;
		gbc_caminosButton.gridy = 4;
		panel.add(caminosButton, gbc_caminosButton);
		JLabel textPaneIcon = new JLabel();
		textPaneIcon.setIcon(new ImageIcon(getClass().getResource("/magnify.png")));
		JTextField fieldText = new JTextField();

		GridBagConstraints gbc_agregarButton = new GridBagConstraints();
		gbc_agregarButton.insets = new Insets(0, 0, 5, 5);
		gbc_agregarButton.gridx = 0;
		gbc_agregarButton.gridy = 5;
		agregarButton.setIcon(new ImageIcon(getClass().getResource("/plus-circle.png")));
		panel.add(agregarButton, gbc_agregarButton);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 10, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 5;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_4 = new JLabel("");
		panel_1.add(lblNewLabel_4, BorderLayout.WEST);
		lblNewLabel_4.setIcon(new ImageIcon(getClass().getResource("/magnify.png")));

		buscadorTxt = new JTextField();
		panel_1.add(buscadorTxt, BorderLayout.CENTER);
		buscadorTxt.setColumns(10);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Nro. Parada", "Calle", "Eliminar" }) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 2:
					return ImageIcon.class;
				default:
					return Object.class;
				}
			}
		};
		table.setModel(tableModel);
		tableSorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(tableSorter);
//		TableUtility.setCellsAlignment(table, SwingConstants.CENTER);
		DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
		alinear.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(alinear);
		table.getColumnModel().getColumn(1).setCellRenderer(alinear);
		table.setRowHeight(35);
//		table.getColumnModel().getColumn(2).setCellRenderer(table.getDefaultRenderer(ImageIcon.class));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		add(scrollPane, BorderLayout.CENTER);
		table.getTableHeader().setReorderingAllowed(false);
		tableSorter.setSortable(0, false);
		tableSorter.setSortable(1, false);
		tableSorter.setSortable(2, false);
//		table.setColumnSelectionAllowed(false);
		calleResumenTxt.setPreferredSize(lblNewLabel_3.getPreferredSize());
		controlador = new ControladorParadas(this);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getBuscadorTxt() {
		return buscadorTxt;
	}

	public void setBuscadorTxt(JTextField textField) {
		this.buscadorTxt = textField;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel model) {
		this.tableModel = model;
	}

	public ControladorParadas getControlador() {
		return controlador;
	}

	public void setControlador(ControladorParadas controlador) {
		this.controlador = controlador;
	}

	public JLabel getAgregarButton() {
		return agregarButton;
	}

	public void setAgregarButton(JLabel agregarButton) {
		this.agregarButton = agregarButton;
	}

	public TableRowSorter<TableModel> getTableSorter() {
		return this.tableSorter;
	}

	public JFrame getVentanaPadre() {
		return ventanaPadre;
	}

	public JButton getCaminosButton() {
		return caminosButton;
	}

	public JLabel  getCalleResumenTxt() {
		return calleResumenTxt;
	}

	public JLabel  getParadasAdyResumenTxt() {
		return paradasAdyResumenTxt;
	}

	public JLabel getNumeroParadaResumenTxt() {
		return numeroParadaResumenTxt;
	}

	public JLabel getPintarAdyacentesResumenLbl() {
		return pintarAdyacentesResumenLbl;
	}

}
