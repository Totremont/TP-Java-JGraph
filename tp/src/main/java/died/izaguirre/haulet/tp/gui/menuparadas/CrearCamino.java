package died.izaguirre.haulet.tp.gui.menuparadas;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import died.izaguirre.haulet.tp.controladores.ControladorCrearCamino;
import died.izaguirre.haulet.tp.gui.utilities.TableUtility;
import died.izaguirre.haulet.tp.tablas.Parada;

import javax.swing.UIManager;
import javax.swing.BoxLayout;

public class CrearCamino extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel tableModel;
	private ControladorCrearCamino controlador;
	private JTextField capacidadTxt;
	private JTextField distanciaTxt;
	private JTable table;
	private JButton crearButton;
	private JComboBox<Parada> destinoCBx = new JComboBox<Parada>();
	private JComboBox<Parada> origenCBx = new JComboBox<Parada>();
	private JButton salirButton;
	private ParadasPanel panelPadre;
	private TableRowSorter<TableModel> tableSorter;

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
//			CrearCamino dialog = new CrearCamino();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public CrearCamino(ParadasPanel panelPadre) {
		super(panelPadre.getVentanaPadre(), "Crear paradas menú", Dialog.ModalityType.DOCUMENT_MODAL);
		this.panelPadre = panelPadre;
		setBounds(100, 100, 565, 331);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JPanel crear = new JPanel();
			contentPanel.add(crear);
			GridBagLayout gbl_crear = new GridBagLayout();
			gbl_crear.columnWidths = new int[] { 0, 0, 0, 0, 0 };
			gbl_crear.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
			gbl_crear.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_crear.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			crear.setLayout(gbl_crear);
			{
				JLabel lblNewLabel = new JLabel("");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.weightx = 0.5;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 0;
				crear.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Parada Origen");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 0;
				crear.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				//origenCBx = new JComboBox();
				GridBagConstraints gbc_origenCBx = new GridBagConstraints();
				gbc_origenCBx.insets = new Insets(0, 0, 5, 5);
				gbc_origenCBx.fill = GridBagConstraints.HORIZONTAL;
				gbc_origenCBx.gridx = 2;
				gbc_origenCBx.gridy = 0;
				crear.add(origenCBx, gbc_origenCBx);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("");
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.weightx = 0.8;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel_2.gridx = 3;
				gbc_lblNewLabel_2.gridy = 0;
				crear.add(lblNewLabel_2, gbc_lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Parada Destino");
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 1;
				gbc_lblNewLabel_3.gridy = 1;
				crear.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				//destinoCBx = new JComboBox();
				GridBagConstraints gbc_destinoCBx = new GridBagConstraints();
				gbc_destinoCBx.insets = new Insets(0, 0, 5, 5);
				gbc_destinoCBx.fill = GridBagConstraints.HORIZONTAL;
				gbc_destinoCBx.gridx = 2;
				gbc_destinoCBx.gridy = 1;
				destinoCBx.setPreferredSize(new Dimension(150,(int) new JComboBox<>().getPreferredSize().getHeight()));
				crear.add(destinoCBx, gbc_destinoCBx);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Capacidad");
				GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
				gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_4.gridx = 1;
				gbc_lblNewLabel_4.gridy = 2;
				crear.add(lblNewLabel_4, gbc_lblNewLabel_4);
			}
			{
				capacidadTxt = new JTextField();
				GridBagConstraints gbc_capacidadTxt = new GridBagConstraints();
				gbc_capacidadTxt.insets = new Insets(0, 0, 5, 5);
				gbc_capacidadTxt.fill = GridBagConstraints.HORIZONTAL;
				gbc_capacidadTxt.gridx = 2;
				gbc_capacidadTxt.gridy = 2;
				crear.add(capacidadTxt, gbc_capacidadTxt);
				capacidadTxt.setColumns(10);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Distancia");
				GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
				gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_5.gridx = 1;
				gbc_lblNewLabel_5.gridy = 3;
				crear.add(lblNewLabel_5, gbc_lblNewLabel_5);
			}
			{
				distanciaTxt = new JTextField();
				GridBagConstraints gbc_distanciaTxt = new GridBagConstraints();
				gbc_distanciaTxt.insets = new Insets(0, 0, 5, 5);
				gbc_distanciaTxt.fill = GridBagConstraints.HORIZONTAL;
				gbc_distanciaTxt.gridx = 2;
				gbc_distanciaTxt.gridy = 3;
				crear.add(distanciaTxt, gbc_distanciaTxt);
				distanciaTxt.setColumns(10);
			}
			{
				crearButton = new JButton("Agregar");
				GridBagConstraints gbc_crearButton = new GridBagConstraints();
				gbc_crearButton.insets = new Insets(0, 0, 15, 5);
				gbc_crearButton.gridx = 2;
				gbc_crearButton.gridy = 4;
				crear.add(crearButton, gbc_crearButton);
			}
		}
		{
			JPanel verPanel = new JPanel();
			contentPanel.add(verPanel);
			verPanel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				verPanel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					tableModel = new DefaultTableModel(new Object[][] {},
							new String[] { "Origen", "Destino", "Capacidad", "Distancia [km]", "Eliminar" }) {
						/**
								 * 
								 */
								private static final long serialVersionUID = 1L;

						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}

//						@Override
//						public Class<?> getColumnClass(int columnIndex) {
//							if (columnIndex == 4)
//								return ImageIcon.class;
//							else
//								return Object.class;
//						}
					};
					table.setModel(tableModel);
					scrollPane.setViewportView(table);
				}
			}
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				salirButton = new JButton("Salir");
				buttonPane.add(salirButton);
			}
		}
		TableUtility.setCellsAlignment(table, SwingConstants.CENTER);
		table.getColumnModel().getColumn(4).setCellRenderer(table.getDefaultRenderer(ImageIcon.class));
		this.controlador = new ControladorCrearCamino(this);
		
		tableSorter = new TableRowSorter<>(tableModel);
		table.setRowSorter(tableSorter);
		table.getTableHeader().setReorderingAllowed(false);
		setLocationRelativeTo(panelPadre.getVentanaPadre());
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public ControladorCrearCamino getControlador() {
		return controlador;
	}

	public JTextField getCapacidadTxt() {
		return capacidadTxt;
	}

	public JTextField getDistanciaTxt() {
		return distanciaTxt;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getCrearButton() {
		return crearButton;
	}

	public JComboBox<Parada> getDestinoCBx() {
		return destinoCBx;
	}

	public JComboBox<Parada> getOrigenCBx() {
		return origenCBx;
	}

	public JButton getSalirButton() {
		return salirButton;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public void setControlador(ControladorCrearCamino controlador) {
		this.controlador = controlador;
	}

	public void setCapacidadTxt(JTextField capacidadTxt) {
		this.capacidadTxt = capacidadTxt;
	}

	public void setDistanciaTxt(JTextField distanciaTxt) {
		this.distanciaTxt = distanciaTxt;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setCrearButton(JButton crearButton) {
		this.crearButton = crearButton;
	}

	public void setDestinoCBx(JComboBox destinoCBx) {
		this.destinoCBx = destinoCBx;
	}

	public void setOrigenCBx(JComboBox origenCBx) {
		this.origenCBx = origenCBx;
	}

	public void setSalirButton(JButton salirButton) {
		this.salirButton = salirButton;
	}

}
