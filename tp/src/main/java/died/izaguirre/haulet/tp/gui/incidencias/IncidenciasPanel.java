package died.izaguirre.haulet.tp.gui.incidencias;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;


import died.izaguirre.haulet.tp.controladores.ControladorIncidencia;

import died.izaguirre.haulet.tp.gui.utilities.TableUtility;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.JScrollBar;
import java.awt.Font;
import java.awt.SystemColor;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.UIManager;
import javax.swing.JTextField;

import died.izaguirre.haulet.tp.tablas.Incidencia;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IncidenciasPanel extends JPanel {
	
	private JTable table;
	private JTextField textField;
	private JFrame padre;


	private int ultimaFila = -1;
	private ArrayList<Incidencia> incidencias = new ArrayList<>();
	private ControladorIncidencia gestor = new ControladorIncidencia();
	private ArrayList<Incidencia> aux = new ArrayList<>(); //Lista auxiliar que se utiliza para filtrado
	
	private ImageIcon delete = new ImageIcon(getClass().getResource("/delete.png"));
	private JLabel lblNewLabel_7 = new JLabel("");
	private JLabel lblNewLabel_8 = new JLabel("");
	private JLabel lblNewLabel_10 = new JLabel("");
	private JLabel lblNewLabel_11 = new JLabel("");
	private JLabel lblNewLabel_14 = new JLabel("");
	private JLabel lblNewLabel_13 = new JLabel("");
	private JLabel lblNewLabel_18 = new JLabel(" Ver con cursor ");
	private JLabel lblNewLabel_5 = new JLabel("Sin selección");
	private JButton btnNewButton = new JButton("Modificar");
	
	public IncidenciasPanel(JFrame padre) {
		
		this.padre = padre;
		crearInterfaz();
		buscarIncidencias();
		actualizarTabla();
		
	}
	
	private void crearIncidencia(boolean actualizar) 
	{
		if(actualizar) 
		{
			JDialog dialog = new CrearIncidencia(padre,aux.get(table.getSelectedRow()));
		} else 
		{
			JDialog dialog = new CrearIncidencia(padre,null);
			
		}
		buscarIncidencias();
		actualizarTabla();
	}
	
	@SuppressWarnings("unchecked")
	private void buscarIncidencias() 
	{
		gestor.buscarIncidencias();
		incidencias = gestor.ordenarIncidencias();
		aux = (ArrayList<Incidencia>) incidencias.clone();
//		incidencias = gestor.buscarIncidencias();
		//aux = incidencias;
	}
	
	private void actualizarTabla() 
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		ultimaFila = -1;
		
		aux.forEach(it -> 
		{
			String fin = it.getFechaFin() == null ? "No establecido" : it.getFechaFin().toString();
			Object[] fila = { it.getId(), it.getParada().getCalle(), fin, delete};
			model.addRow(fila);
		});
		if(model.getRowCount() > 0) table.changeSelection(0, 0, false, false);
		else limpiarResumen();
		revalidate();
		repaint();
		
	}
	
	private void actualizarResumen(int fila) 
	{
		Incidencia seleccionada = aux.get(fila);
		if(seleccionada == null) return;
		lblNewLabel_5.setText("Incidencia: " + seleccionada.getId());
		String fin = seleccionada.getFechaFin() == null ? "No establecido" : seleccionada.getFechaFin().toString();
		lblNewLabel_7.setText(seleccionada.getParada().getCalle());
		if(seleccionada.getEstaResuelto()) lblNewLabel_8.setText("RESUELTA");
		 else lblNewLabel_8.setText("EN CURSO");
		lblNewLabel_10.setText(seleccionada.getFechaInicio().toString());
		lblNewLabel_11.setText(fin);
		lblNewLabel_14.setText("");
		if(seleccionada.getFechaFin() != null) 
		{
			long falta = ChronoUnit.DAYS.between(LocalDate.now(), seleccionada.getFechaFin());
			if(falta >= 0 && !seleccionada.getEstaResuelto()) 
			{
				lblNewLabel_14.setText("Faltan " + falta + " días");
			} 
		}
		lblNewLabel_13.setText(seleccionada.getMotivo());
		lblNewLabel_18.setToolTipText(seleccionada.getDescripción());
		btnNewButton.setEnabled(true);
		
	}
	
	private void limpiarResumen() 
	{
		lblNewLabel_5.setText("Sin selección");
		lblNewLabel_7.setText("");
		lblNewLabel_8.setText("");
		lblNewLabel_10.setText("");
		lblNewLabel_11.setText("");
		lblNewLabel_14.setText("");
		lblNewLabel_13.setText("");
		lblNewLabel_18.setToolTipText("");
		btnNewButton.setEnabled(false);
	}
	
	@SuppressWarnings("unchecked")
	private void filtrarBusqueda() 
	{
		String busqueda = textField.getText().toLowerCase();
		ArrayList<Incidencia> aux = (ArrayList<Incidencia>) incidencias.clone();
		this.aux = aux;
		if(!busqueda.isBlank() && !aux.isEmpty()) 
		{
			int filas = aux.size();
			this.aux = (ArrayList<Incidencia>) aux.stream().filter(it -> 
			{
				String id = Integer.toString(it.getId()).toLowerCase();
				String calle = it.getParada().getCalle().toLowerCase();
				String fin = it.getFechaFin() != null ? it.getFechaFin().toString().toLowerCase() : "no establecido";
				return (id.startsWith(busqueda) || calle.startsWith(busqueda) || fin.startsWith(busqueda));
			}).collect(Collectors.toList());
		}
		actualizarTabla();
	}
	
	private void eliminarIncidencia(int pos) 
	{
		Incidencia eliminar = aux.get(pos);
		incidencias.remove(aux.get(pos));
		aux.remove(pos);
		gestor.eliminarIncidencia(eliminar);
		((DefaultTableModel)table.getModel()).removeRow(pos);
		table.clearSelection();
		table.getColumnModel().getSelectionModel().setAnchorSelectionIndex(-1);
		table.getColumnModel().getSelectionModel().setLeadSelectionIndex(-1);
		limpiarResumen();
	}
	
	private void crearInterfaz() 
	{
		setBorder(null);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0};
		JScrollPane scrollPane2 = new JScrollPane();
		panel.setBorder(null);
		panel.setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Incidencias");
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
		
		lblNewLabel_5.setIcon(null);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 3;
		gbc_lblNewLabel_5.gridy = 1;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.activeCaption);
		separator.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridheight = 6;
		gbc_separator.fill = GridBagConstraints.VERTICAL;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		panel.add(separator, gbc_separator);
		
		JLabel paradaTxt = new JLabel("Parada afectada");
		paradaTxt.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_paradaTxt = new GridBagConstraints();
		gbc_paradaTxt.anchor = GridBagConstraints.WEST;
		gbc_paradaTxt.insets = new Insets(0, 0, 5, 5);
		gbc_paradaTxt.gridx = 1;
		gbc_paradaTxt.gridy = 2;
		panel.add(paradaTxt, gbc_paradaTxt);
		
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 2;
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setPreferredSize(lblNewLabel_18.getPreferredSize());
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JLabel lblNewLabel_3 = new JLabel("Situación");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 3;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 2;
		gbc_lblNewLabel_8.gridy = 3;
		panel.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		JLabel lblNewLabel_4 = new JLabel("Inicio");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 4;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 2;
		gbc_lblNewLabel_10.gridy = 4;
		panel.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		JLabel lblNewLabel_6 = new JLabel("Fin");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 5;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 2;
		gbc_lblNewLabel_11.gridy = 5;
		panel.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_14.gridx = 3;
		gbc_lblNewLabel_14.gridy = 5;
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_14, gbc_lblNewLabel_14);
		
		JLabel lblNewLabel_12 = new JLabel("Motivo");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 1;
		gbc_lblNewLabel_12.gridy = 6;
		panel.add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 2;
		gbc_lblNewLabel_13.gridy = 6;
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setPreferredSize(lblNewLabel_18.getPreferredSize());
		panel.add(lblNewLabel_13, gbc_lblNewLabel_13);
		
		JLabel lblNewLabel_17 = new JLabel("Descripción");
		GridBagConstraints gbc_lblNewLabel_17 = new GridBagConstraints();
		gbc_lblNewLabel_17.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_17.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_17.gridx = 1;
		gbc_lblNewLabel_17.gridy = 7;
		panel.add(lblNewLabel_17, gbc_lblNewLabel_17);
		
		GridBagConstraints gbc_lblNewLabel_18 = new GridBagConstraints();
		gbc_lblNewLabel_18.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_18.gridx = 2;
		gbc_lblNewLabel_18.gridy = 7;
		lblNewLabel_18.setForeground(Color.BLUE);
		lblNewLabel_18.setToolTipText("asdasjkdhasdasdasdasd");
		panel.add(lblNewLabel_18, gbc_lblNewLabel_18);
		
		JLabel lblNewLabel_15 = new JLabel("Lista");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
		gbc_lblNewLabel_15.insets = new Insets(10, 0, 15, 5);
		gbc_lblNewLabel_15.gridx = 0;
		gbc_lblNewLabel_15.gridy = 8;
		panel.add(lblNewLabel_15, gbc_lblNewLabel_15);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 2;
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 8;
		panel.add(separator_1, gbc_separator_1);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()>= 0) crearIncidencia(true);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 8;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel_19 = new JLabel("Agregar");
		lblNewLabel_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				crearIncidencia(false);
			}
		});
		GridBagConstraints gbc_lblNewLabel_19 = new GridBagConstraints();
		gbc_lblNewLabel_19.insets = new Insets(0, 0, 10, 5);
		gbc_lblNewLabel_19.gridx = 0;
		gbc_lblNewLabel_19.gridy = 9;
		lblNewLabel_19.setIcon(new ImageIcon(getClass().getResource("/plus-circle.png")));
		panel.add(lblNewLabel_19, gbc_lblNewLabel_19);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 10, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 9;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_16 = new JLabel();
		panel_1.add(lblNewLabel_16, BorderLayout.WEST);
		lblNewLabel_16.setIcon(new ImageIcon(getClass().getResource("/magnify.png")));
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarBusqueda();
			}
		});
		panel_1.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Avellaneda", "08/09/2022", null, null},
				{"Vera", "08/09/2022", null, null},
				{"Sunchales", "08/09/2022", null, null},
			},
			new String[] {
				"Incidencia", "Parada", "Fin", "Borrar"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		TableUtility.setCellsAlignment(table, SwingConstants.CENTER);
		table.getColumnModel().getColumn(3).setCellRenderer(table.getDefaultRenderer(ImageIcon.class));
		table.setRowHeight(35);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
	        public void valueChanged(ListSelectionEvent event) {
	            if(table.getSelectedRow()>= 0 && !event.getValueIsAdjusting())  actualizarResumen(table.getSelectedRow());
	        }
	    });
		table.getColumnModel().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if((table.getSelectedColumn() == 3) && !e.getValueIsAdjusting() ) 
				{
					eliminarIncidencia(table.getSelectedRow());
				}
				
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	

}
