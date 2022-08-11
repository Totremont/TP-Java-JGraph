package died.izaguirre.haulet.tp.gui.principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import died.izaguirre.haulet.tp.controladores.ControladorBoleto;
import died.izaguirre.haulet.tp.controladores.ControladorGrafo;
import died.izaguirre.haulet.tp.controladores.ControladorLineas;
import died.izaguirre.haulet.tp.controladores.ControladorParadas;
import died.izaguirre.haulet.tp.dao.impl.ParadaDaoImpl;
import died.izaguirre.haulet.tp.dao.interfaces.ParadaDao;
import died.izaguirre.haulet.tp.estructuras.grafo.GrafoConPeso;
import died.izaguirre.haulet.tp.gui.incidencias.IncidenciasPanel;
import died.izaguirre.haulet.tp.gui.menulineas.MenuVerLineas;
import died.izaguirre.haulet.tp.gui.menuparadas.ParadasPanel;
import died.izaguirre.haulet.tp.gui.utilities.Precios;
import died.izaguirre.haulet.tp.tablas.Boleto;
import died.izaguirre.haulet.tp.tablas.Camino;
import died.izaguirre.haulet.tp.tablas.Parada;
import died.izaguirre.haulet.tp.tablas.Posee;
import died.izaguirre.haulet.tp.tablas.linea.Linea;
import died.izaguirre.haulet.tp.tablas.linea.LineaTipoEnum;
import java.util.Map;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrincipalPanel extends JPanel {

	private ArrayList<Parada> paradas = new ArrayList<>();
	private List<Linea> lineas = new ArrayList<>();
	//private List<List<Parada>> trayectoLineasParadas = new ArrayList<>();
	
	private HashMap<Integer, List<Camino>> lineasCaminos = new HashMap<>();	//Relacion linea y su trayecto
	private List<List<Camino>> caminos = new ArrayList<>();
	private Linea ultimaLinea;
	private int ultimoPrecio;
	private int indexBarato = -1;
	
	private ControladorGrafo controladorGrafo = ControladorGrafo.getInstance();
	private ControladorBoleto controladorBoleto = new ControladorBoleto();
	private GrafoConPeso grafoPeso = controladorGrafo.getGrafoPeso();
	private Principal padre;
	
	
	private JComboBox<Parada> comboOrigen;
	private JComboBox<Parada> comboDestino;
	private JComboBox<String> comboTrayecto;
	private JComboBox<Linea> comboLinea;
	private JRadioButton buttonRutaCorta;
	private JRadioButton buttonRutaRapida;
	private JRadioButton buttonRutaBarata;
	private boolean rutaCortaChecked = false;
	private boolean rutaRapidaChecked = false;
	private boolean rutaBarataChecked = false;
	private ButtonGroup buttonGroup;
	private JButton buttonBoleto;
	private JLabel txtOrigen3;
	private JLabel txtDestino3;
	private JLabel txtDistancia2;
	private JLabel txtPrecio2;
	private JCheckBox checkAC;
	private JCheckBox checkWifi;
	private JLabel tiempoEstimado;
	
	private JLabel txtLinea;
	private Parada paradaPrototype = new Parada() 
	{
		@Override
		public String toString() {
			return "XXXXXXXXXXX";
		}
	};
	private Linea lineaPrototype = new Linea() 
	{
		@Override
		public String toString() {
			return "XXXXXXXXXXX";
		}
	};
	
	public PrincipalPanel(Principal padre) 
	{
		this.padre = padre;
		crearInterfaz();
		despintar();
		cargarParadas();
		cargarLineas();
		pintarIncidencias();
	}
	
	private void comprarBoleto() 
	{
		if(ultimaLinea != null) 
		{
			Boleto boleto = new Boleto();
			boleto.setLinea(ultimaLinea);
			boleto.setMonto(ultimoPrecio);
			boolean accion = controladorBoleto.insertarBoleto(boleto);
			JDialog dialog = new JDialog();
			String mensaje = accion ? "Boleto comprado exitosamente" : "No se pudo comprar";
			dialog.setAlwaysOnTop(true);
			dialog.setLocationRelativeTo(padre);
			JOptionPane.showMessageDialog(dialog, mensaje,
					"Operación", JOptionPane.INFORMATION_MESSAGE);
			
		}
		else 
		{
			JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true);
			dialog.setLocationRelativeTo(padre);
			JOptionPane.showMessageDialog(dialog, "No se encontró última ruta",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void ultimaRuta(Linea linea, List<Camino> camino) 
	{
		ultimaLinea = linea;
		buttonBoleto.setEnabled(true);
		pintarTrayecto(camino);
		txtLinea.setText("Línea: " + linea.getNombre());
		txtOrigen3.setText(camino.get(0).getOrigen().getCalle());
		txtDestino3.setText(camino.get(camino.size()-1).getDestino().getCalle());
		txtDistancia2.setText(grafoPeso.distanciaTotal(camino) + " Km");
		int precio = ControladorLineas.precioLinea(linea, camino);
		txtPrecio2.setText(Math.round(precio)+"$");
		ultimoPrecio = Math.round(precio);
		checkAC.setSelected(linea.getTieneAire() != null ? linea.getTieneAire() : false );
		checkWifi.setSelected(linea.getTieneWifi() != null ? linea.getTieneWifi() : false );
		float tiempoTotal = grafoPeso.tiempoTotal(camino);
		String formattedString = String.format("%.2f", tiempoTotal);
		tiempoEstimado.setText(formattedString + " Hs");
	}
	
	private void pintarIncidencias() 
	{
		List<Parada> deshabilitadas = controladorGrafo.comprobarIncidencias();
		controladorGrafo.pintarIncidencias(deshabilitadas);
	}

	
	private void cargarParadas() 
	{
		paradas = ControladorParadas.buscarParadas();
		comboOrigen.removeAllItems();
		comboDestino.removeAllItems();
		
		if(paradas.isEmpty()) 
		{
			Parada paradaVacia = new Parada() 
			{
				@Override
				public String toString() {
					return "No hay paradas disponibles";
				}
			}; 
			
			comboOrigen.setEnabled(false);
			comboDestino.setEnabled(false);
			comboOrigen.addItem(paradaVacia);
			comboDestino.addItem(paradaVacia);
			
		}
		else 
		{
			paradas.forEach(it -> 
			{
				comboOrigen.addItem(it);
				comboDestino.addItem(it);
			});
		}
		
	}
	
	private void cargarLineas() 
	{
		lineas = ControladorLineas.getLineas();
	}
		
	private void mostrarTrayecto(Parada origen, Parada destino) 
	{
		comboTrayecto.setEnabled(false);
		comboLinea.setEnabled(false);
		comboTrayecto.removeAllItems();
		comboLinea.setModel(new DefaultComboBoxModel<Linea>());
		caminos.clear();
		lineasCaminos.clear();
		buscarCaminos(origen, destino);
		if(lineasCaminos.isEmpty()) 
		{
			despintar();
			Linea lineaVacia = new Linea() 
			{
				@Override
				public String toString() {
					return "No hay lineas";
				}
			};
			comboTrayecto.addItem("No hay recorridos");
			comboLinea.addItem(lineaVacia);
		} else 
		{
			for(int i = 0; i < caminos.size(); i++) 
			{
				comboTrayecto.addItem("Trayecto: " + i + 
						" (" + grafoPeso.distanciaTotal(caminos.get(i)) + " Km)");
				
			}
			comboTrayecto.setEnabled(true);
			if(buttonRutaCorta.isSelected()) comboTrayecto.setSelectedIndex(caminoCorto());
			else if(buttonRutaRapida.isSelected()) comboTrayecto.setSelectedIndex(caminoRapido());
			else if(buttonRutaBarata.isSelected()) 
			{
				indexBarato = caminoBarato();
				comboTrayecto.setSelectedIndex(caminos.indexOf(lineasCaminos.get(indexBarato)));
				
			} else comboTrayecto.setSelectedIndex(0);
		}
		
	}
	
	private void mostrarLineas(List<Camino> trayecto) 
	{
		if(!trayecto.isEmpty()) 
		{
			List<Linea> aux = new ArrayList<>();
			comboLinea.setModel(new DefaultComboBoxModel<Linea>());
			lineasCaminos.forEach((key, value) -> 
			{
				if(value.equals(trayecto)) { 
					Linea linea = lineas.stream().filter(it -> it.getId() == key).
							findFirst().get();
					aux.add(linea);
					comboLinea.addItem(linea);
				}
			});
			comboLinea.setEnabled(true);
			if(buttonRutaBarata.isSelected()) 
			{
				Linea linea = lineas.stream().filter(it -> it.getId() == indexBarato).
						findFirst().get();
				comboLinea.setSelectedItem(linea);
			}
		}
	}
	
	private void buscarCaminos(Parada origen, Parada destino) 
	{
		//Esto obtiene todos los caminos de las lineas
		List<List<Parada>> caminos = ControladorLineas.getTrayectoLineas(lineas);
		
		lineasCaminos.clear();	//Dictionary entre id de linea y su camino
		
		for(int i = 0; i < caminos.size(); i++) 
		{
			List<Parada> camino = caminos.get(i);
			if(camino.isEmpty()) continue;
			if(camino.get(0).equals(origen) && camino.get(camino.size()-1).equals(destino)) 
			{
				List<Camino> aux = grafoPeso.toListCaminos(camino);
				lineasCaminos.put(lineas.get(i).getId(), aux);
				if(!this.caminos.contains(aux)) this.caminos.add(aux);
			}
		}
	}
	
	private int caminoCorto() //Posicion dentro de ComboBox
	{
		int pos = -1;
		if(!caminos.isEmpty()) 
		{
			pos = 0;
			int distanciaMinima = grafoPeso.distanciaTotal(caminos.get(0));
			if(caminos.size() > 1) for(int i = 1; i < caminos.size(); i++) 
			{
				int distancia = grafoPeso.distanciaTotal(caminos.get(i));
				if(distanciaMinima > distancia) 
				{
					distanciaMinima = distancia;
					pos = i;
				}
			}
		}
		return pos;
	}
	
	private int caminoRapido() //Posicion dentro de ComboBox
	{
		int pos = -1;
		if(!caminos.isEmpty()) 
		{
			pos = 0;
			float tiempoMinimo = grafoPeso.tiempoTotal(caminos.get(0));
			if(caminos.size() > 1) for(int i = 1; i < caminos.size(); i++) 
			{
				float tiempo = grafoPeso.tiempoTotal(caminos.get(i));
				if(tiempoMinimo > tiempo) 
				{
					tiempoMinimo = tiempo;
					pos = i;
				}
			}
		}
		return pos;
	}
	
	private int caminoBarato() 	//Key en HashMap
	{
		int pos = -1;
		if(!lineasCaminos.isEmpty()) 
		{
			boolean primero = true;
			int precioMinimo = 0;
			for (Map.Entry<Integer, List<Camino>> set : lineasCaminos.entrySet()) 
			{
				Linea linea = lineas.stream().filter(it -> it.getId() == set.getKey()).
						findFirst().get();
				if(primero) 
				{
					precioMinimo = ControladorLineas.precioLinea(linea,set.getValue());
					pos = set.getKey();
				} else 
				{
					int precio = ControladorLineas.precioLinea(linea,set.getValue());
					if(precioMinimo > precio) 
					{
						precioMinimo = precio;
						pos = set.getKey();
					}
				}
			};
		}
		return pos;
	}
	
	private void pintarTrayecto(List<Camino> trayecto) 
	{
		controladorGrafo.pintarTrayecto(trayecto);
	}
	
	private void despintar() 
	{
		controladorGrafo.despintar();;
	}
	
	private void verLineasInterfaz() 
	{
		padre.cambiarInterfaz(new MenuVerLineas(padre));
	}
	
	private void verParadasInterfaz() 
	{
		padre.cambiarInterfaz(new ParadasPanel(padre));
	}
	
	private void verIncidenciasInterfaz() 
	{
		padre.cambiarInterfaz(new IncidenciasPanel(padre));
	}
	
	
	private void crearInterfaz() {
		
		//setVisible(false);
		GridBagLayout gbl_panel_izquierdo = new GridBagLayout();
		gbl_panel_izquierdo.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_izquierdo.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_izquierdo.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_izquierdo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_panel_izquierdo);
		//JScrollPane scrollPane = new JScrollPane();
		setBorder(null);
		
		JLabel txtTitulo = new JLabel("SISTEMA DE TRANSPORTE");
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitulo.gridx = 2;
		gbc_txtTitulo.gridy = 1;
		add(txtTitulo, gbc_txtTitulo);
		
		JLabel txtsubTitulo = new JLabel("Observe las rutas disponibles");
		txtsubTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtsubTitulo = new GridBagConstraints();
		gbc_txtsubTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtsubTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtsubTitulo.gridx = 2;
		gbc_txtsubTitulo.gridy = 2;
		add(txtsubTitulo, gbc_txtsubTitulo);
		
		JSeparator separator1 = new JSeparator();
		GridBagConstraints gbc_separator1 = new GridBagConstraints();
		gbc_separator1.insets = new Insets(0, 0, 5, 5);
		gbc_separator1.fill = GridBagConstraints.BOTH;
		gbc_separator1.gridx = 2;
		gbc_separator1.gridy = 3;
		add(separator1, gbc_separator1);
		
		JLabel txtOrigen = new JLabel("Origen");
		GridBagConstraints gbc_txtOrigen = new GridBagConstraints();
		gbc_txtOrigen.anchor = GridBagConstraints.EAST;
		gbc_txtOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_txtOrigen.gridx = 1;
		gbc_txtOrigen.gridy = 4;
		add(txtOrigen, gbc_txtOrigen);
		
		JLabel txtDestino = new JLabel("Destino");
		GridBagConstraints gbc_txtDestino = new GridBagConstraints();
		gbc_txtDestino.anchor = GridBagConstraints.EAST;
		gbc_txtDestino.insets = new Insets(0, 0, 5, 5);
		gbc_txtDestino.gridx = 1;
		gbc_txtDestino.gridy = 5;
		add(txtDestino, gbc_txtDestino);
		
		comboOrigen = new JComboBox<Parada>();
		comboOrigen.setPrototypeDisplayValue(paradaPrototype);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 4;
		add(comboOrigen, gbc_comboBox);
		
		comboDestino = new JComboBox<Parada>();
		comboDestino.setPrototypeDisplayValue(paradaPrototype);
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.BOTH;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 5;
		add(comboDestino, gbc_comboBox_1);
		
		JLabel txtInvertir = new JLabel("Invertir");
		txtInvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Parada origen = comboOrigen.getSelectedIndex() >= 0 ? 
						(Parada) comboOrigen.getSelectedItem() : null;
				Parada destino = comboDestino.getSelectedIndex() >= 0 ? 
						(Parada) comboDestino.getSelectedItem() : null;
				if(origen != null) comboDestino.setSelectedItem(origen);
				if(destino != null) comboOrigen.setSelectedItem(destino);
			}
		});
		GridBagConstraints gbc_txtInvertir = new GridBagConstraints();
		gbc_txtInvertir.insets = new Insets(0, 0, 5, 5);
		gbc_txtInvertir.gridx = 3;
		gbc_txtInvertir.gridy = 5;
		txtInvertir.setIcon(new ImageIcon(getClass().getResource("/swap-vertical.png")));
		add(txtInvertir, gbc_txtInvertir);
		
		JButton buttonBuscar = new JButton("Buscar");
		buttonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboOrigen.isEnabled() && comboDestino.isEnabled())
					if(comboOrigen.getSelectedIndex() >= 0 && comboDestino.getSelectedIndex() >= 0)
						mostrarTrayecto((Parada) comboOrigen.getSelectedItem(), 
								(Parada) comboDestino.getSelectedItem());
			}
		});
		GridBagConstraints gbc_buttonBuscar = new GridBagConstraints();
		gbc_buttonBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_buttonBuscar.gridx = 2;
		gbc_buttonBuscar.gridy = 6;
		add(buttonBuscar, gbc_buttonBuscar);
		
		buttonRutaCorta = new JRadioButton("Ruta más corta");
		buttonRutaCorta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rutaCortaChecked){ 
					buttonGroup.clearSelection();
					rutaCortaChecked = false;
				}
				else { 
					rutaCortaChecked = true;
					rutaBarataChecked = false;
					rutaRapidaChecked = false;
				}
			}
		});
		GridBagConstraints gbc_buttonRutaCorta = new GridBagConstraints();
		gbc_buttonRutaCorta.insets = new Insets(0, 0, 5, 5);
		gbc_buttonRutaCorta.gridx = 1;
		gbc_buttonRutaCorta.gridy = 7;
		add(buttonRutaCorta, gbc_buttonRutaCorta);
		
		buttonRutaRapida = new JRadioButton("Ruta más rápida");
		buttonRutaRapida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rutaRapidaChecked){ 
					buttonGroup.clearSelection();
					rutaRapidaChecked = false;
				}
				else { 
					rutaRapidaChecked = true;
					rutaBarataChecked = false;
					rutaCortaChecked = false;
				}
			}
		});
		GridBagConstraints gbc_buttonRutaRapida = new GridBagConstraints();
		gbc_buttonRutaRapida.insets = new Insets(0, 0, 5, 5);
		gbc_buttonRutaRapida.gridx = 2;
		gbc_buttonRutaRapida.gridy = 7;
		add(buttonRutaRapida, gbc_buttonRutaRapida);
		
		buttonRutaBarata = new JRadioButton("Ruta más barata");
		buttonRutaBarata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rutaBarataChecked) { 
					buttonGroup.clearSelection();
					rutaBarataChecked = false;
				}
				else { 
					rutaBarataChecked = true;
					rutaRapidaChecked = false;
					rutaCortaChecked = false;
				}
			}
		});
		GridBagConstraints gbc_buttonRutaBarata = new GridBagConstraints();
		gbc_buttonRutaBarata.insets = new Insets(0, 0, 5, 5);
		gbc_buttonRutaBarata.gridx = 3;
		gbc_buttonRutaBarata.gridy = 7;
		add(buttonRutaBarata, gbc_buttonRutaBarata);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(buttonRutaBarata);
		buttonGroup.add(buttonRutaCorta);
		buttonGroup.add(buttonRutaRapida);
		
		JSeparator separator2 = new JSeparator();
		GridBagConstraints gbc_separator2 = new GridBagConstraints();
		gbc_separator2.insets = new Insets(0, 0, 5, 5);
		gbc_separator2.gridwidth = 3;
		gbc_separator2.fill = GridBagConstraints.BOTH;
		gbc_separator2.gridx = 1;
		gbc_separator2.gridy = 8;
		add(separator2, gbc_separator2);
		
		JLabel lblNewLabel = new JLabel("Trayecto");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 9;
		add(lblNewLabel, gbc_lblNewLabel);
		
		comboTrayecto = new JComboBox<String>();
		comboTrayecto.setPrototypeDisplayValue("XXXXXXXXXXX");
		comboTrayecto.addItem("Busque un recorrido");
		comboTrayecto.setEnabled(false);
		comboTrayecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboTrayecto.isEnabled() && comboTrayecto.getSelectedIndex() >= 0)
				mostrarLineas(caminos.get(comboTrayecto.getSelectedIndex()));
			}
		});
		GridBagConstraints gbc_comboBoxTrayecto = new GridBagConstraints();
		gbc_comboBoxTrayecto.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTrayecto.fill = GridBagConstraints.BOTH;
		gbc_comboBoxTrayecto.gridx = 2;
		gbc_comboBoxTrayecto.gridy = 9;
		add(comboTrayecto, gbc_comboBoxTrayecto);
		
		JLabel lblNewLabel_1 = new JLabel("Línea");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 10;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		comboLinea = new JComboBox<Linea>();
		comboLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboTrayecto.isEnabled() && comboLinea.getSelectedIndex() >= 0) {
					Linea linea = (Linea) comboLinea.getSelectedItem();
					ultimaRuta(linea, lineasCaminos.get(linea.getId()));
				}
			}
		});
		comboLinea.setPrototypeDisplayValue(lineaPrototype);
		comboLinea.setEnabled(false);
		GridBagConstraints gbc_comboBoxLinea = new GridBagConstraints();
		gbc_comboBoxLinea.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxLinea.fill = GridBagConstraints.BOTH;
		gbc_comboBoxLinea.gridx = 2;
		gbc_comboBoxLinea.gridy = 10;
		add(comboLinea, gbc_comboBoxLinea);
		
		JLabel txtUltimaRuta = new JLabel("ÚLTIMA RUTA");
		GridBagConstraints gbc_txtUltimaRuta = new GridBagConstraints();
		gbc_txtUltimaRuta.insets = new Insets(3, 0, 5, 5);
		gbc_txtUltimaRuta.gridx = 1;
		gbc_txtUltimaRuta.gridy = 11;
		add(txtUltimaRuta, gbc_txtUltimaRuta);
		
		txtLinea = new JLabel("");
		GridBagConstraints gbc_txtLinea = new GridBagConstraints();
		gbc_txtLinea.insets = new Insets(3, 0, 5, 5);
		gbc_txtLinea.gridx = 2;
		gbc_txtLinea.gridy = 11;
		txtLinea.setIcon(new ImageIcon(getClass().getResource("/bus.png")));
		add(txtLinea, gbc_txtLinea);
		
		JLabel txtComodidades = new JLabel("Comodidades");
		GridBagConstraints gbc_txtComodidades = new GridBagConstraints();
		gbc_txtComodidades.insets = new Insets(3, 0, 5, 5);
		gbc_txtComodidades.gridx = 3;
		gbc_txtComodidades.gridy = 11;
		add(txtComodidades, gbc_txtComodidades);
		
		JLabel txtOrigen2 = new JLabel("Origen");
		txtOrigen2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtOrigen2 = new GridBagConstraints();
		gbc_txtOrigen2.insets = new Insets(0, 0, 5, 5);
		gbc_txtOrigen2.gridx = 1;
		gbc_txtOrigen2.gridy = 12;
		add(txtOrigen2, gbc_txtOrigen2);
		
		txtOrigen3 = new JLabel("-");
		GridBagConstraints gbc_txtOrigen3 = new GridBagConstraints();
		gbc_txtOrigen3.insets = new Insets(0, 0, 5, 5);
		gbc_txtOrigen3.gridx = 2;
		gbc_txtOrigen3.gridy = 12;
		add(txtOrigen3, gbc_txtOrigen3);
		
		JSeparator separator3 = new JSeparator();
		separator3.setForeground(SystemColor.activeCaption);
		separator3.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator3 = new GridBagConstraints();
		gbc_separator3.anchor = GridBagConstraints.EAST;
		gbc_separator3.gridheight = 4;
		gbc_separator3.fill = GridBagConstraints.VERTICAL;
		gbc_separator3.insets = new Insets(0, 5, 5, 5);
		gbc_separator3.gridx = 0;
		gbc_separator3.gridy = 12;
		add(separator3, gbc_separator3);
		
		checkAC = new JCheckBox("AC    ");
		checkAC.setEnabled(false);
		GridBagConstraints gbc_checkAC = new GridBagConstraints();
		gbc_checkAC.insets = new Insets(0, 0, 5, 5);
		gbc_checkAC.gridx = 3;
		gbc_checkAC.gridy = 12;
		add(checkAC, gbc_checkAC);
		
		JLabel txtDestino2 = new JLabel("Destino");
		GridBagConstraints gbc_txtDestino2 = new GridBagConstraints();
		gbc_txtDestino2.insets = new Insets(0, 0, 5, 5);
		gbc_txtDestino2.gridx = 1;
		gbc_txtDestino2.gridy = 13;
		add(txtDestino2, gbc_txtDestino2);
		
		txtDestino3 = new JLabel("-");
		GridBagConstraints gbc_txtDestino3 = new GridBagConstraints();
		gbc_txtDestino3.insets = new Insets(0, 0, 5, 5);
		gbc_txtDestino3.gridx = 2;
		gbc_txtDestino3.gridy = 13;
		add(txtDestino3, gbc_txtDestino3);
		
		checkWifi = new JCheckBox("Wi-Fi");
		checkWifi.setEnabled(false);
		GridBagConstraints gbc_checkWifi = new GridBagConstraints();
		gbc_checkWifi.insets = new Insets(0, 0, 5, 5);
		gbc_checkWifi.gridx = 3;
		gbc_checkWifi.gridy = 13;
		add(checkWifi, gbc_checkWifi);
		
		JLabel txtDistancia = new JLabel("Distancia");
		GridBagConstraints gbc_txtDistancia = new GridBagConstraints();
		gbc_txtDistancia.insets = new Insets(0, 0, 5, 5);
		gbc_txtDistancia.gridx = 1;
		gbc_txtDistancia.gridy = 14;
		add(txtDistancia, gbc_txtDistancia);
		
		txtDistancia2 = new JLabel("-");
		GridBagConstraints gbc_txtDistancia2 = new GridBagConstraints();
		gbc_txtDistancia2.insets = new Insets(0, 0, 5, 5);
		gbc_txtDistancia2.gridx = 2;
		gbc_txtDistancia2.gridy = 14;
		add(txtDistancia2, gbc_txtDistancia2);
		
		JLabel lblNewLabel_2 = new JLabel("Tiempo estimado");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 14;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel txtPrecio = new JLabel("Precio");
		GridBagConstraints gbc_txtPrecio = new GridBagConstraints();
		gbc_txtPrecio.insets = new Insets(5, 0, 5, 5);
		gbc_txtPrecio.gridx = 1;
		gbc_txtPrecio.gridy = 15;
		add(txtPrecio, gbc_txtPrecio);
		
		txtPrecio2 = new JLabel("-");
		GridBagConstraints gbc_txtPrecio2 = new GridBagConstraints();
		gbc_txtPrecio2.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrecio2.gridx = 2;
		gbc_txtPrecio2.gridy = 15;
		add(txtPrecio2, gbc_txtPrecio2);
		
		buttonBoleto = new JButton("Comprar Boleto");
		buttonBoleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comprarBoleto();
			}
		});
		
		tiempoEstimado = new JLabel("-");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 15;
		add(tiempoEstimado, gbc_lblNewLabel_3);
		buttonBoleto.setEnabled(false);
		GridBagConstraints gbc_buttonBoleto = new GridBagConstraints();
		gbc_buttonBoleto.insets = new Insets(5, 0, 5, 5);
		gbc_buttonBoleto.gridx = 2;
		gbc_buttonBoleto.gridy = 16;
		buttonBoleto.setIcon(new ImageIcon(getClass().getResource("/ticket-confirmation.png")));
		add(buttonBoleto, gbc_buttonBoleto);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 17;
		add(separator, gbc_separator);
		
		JTextField txtOpciones = new JTextField();
		txtOpciones.setEditable(false);
		txtOpciones.setHorizontalAlignment(SwingConstants.CENTER);
		txtOpciones.setText("OPCIONES");
		GridBagConstraints gbc_txtOpciones = new GridBagConstraints();
		gbc_txtOpciones.insets = new Insets(10, 0, 5, 5);
		gbc_txtOpciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOpciones.gridx = 2;
		gbc_txtOpciones.gridy = 17;
		add(txtOpciones, gbc_txtOpciones);
		txtOpciones.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 3;
		gbc_separator_1.gridy = 17;
		add(separator_1, gbc_separator_1);
		
		JLabel imgLinea = new JLabel("");
		imgLinea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verLineasInterfaz();
			}
		});
		GridBagConstraints gbc_imgLinea = new GridBagConstraints();
		gbc_imgLinea.insets = new Insets(10, 0, 5, 5);
		gbc_imgLinea.gridx = 1;
		gbc_imgLinea.gridy = 18;
		imgLinea.setIcon(new ImageIcon(getClass().getResource("/bus-marker.png")));
		add(imgLinea, gbc_imgLinea);
		
		JLabel imgParada = new JLabel("");
		imgParada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verParadasInterfaz();
			}
		});
		GridBagConstraints gbc_imgParada = new GridBagConstraints();
		gbc_imgParada.insets = new Insets(10, 0, 5, 5);
		gbc_imgParada.gridx = 2;
		gbc_imgParada.gridy = 18;
		imgParada.setIcon(new ImageIcon(getClass().getResource("/map-marker-plus.png")));
		add(imgParada, gbc_imgParada);
		
		JLabel imgIncidencia = new JLabel("");
		imgIncidencia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verIncidenciasInterfaz();
			}
		});
		GridBagConstraints gbc_imgIncidencia = new GridBagConstraints();
		gbc_imgIncidencia.insets = new Insets(10, 0, 5, 5);
		gbc_imgIncidencia.gridx = 3;
		gbc_imgIncidencia.gridy = 18;
		imgIncidencia.setIcon(new ImageIcon(getClass().getResource("/alert-octagon.png")));
		add(imgIncidencia, gbc_imgIncidencia);
		
		JLabel txtVerLinea = new JLabel("Ver líneas");
		txtVerLinea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verLineasInterfaz();
			}
		});
		GridBagConstraints gbc_txtVerLinea = new GridBagConstraints();
		gbc_txtVerLinea.anchor = GridBagConstraints.NORTH;
		gbc_txtVerLinea.insets = new Insets(0, 0, 5, 5);
		gbc_txtVerLinea.gridx = 1;
		gbc_txtVerLinea.gridy = 19;
		add(txtVerLinea, gbc_txtVerLinea);
		
		JLabel txtVerParadas = new JLabel("Ver paradas");
		txtVerParadas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verParadasInterfaz();
			}
		});
		GridBagConstraints gbc_txtVerParadas = new GridBagConstraints();
		gbc_txtVerParadas.anchor = GridBagConstraints.NORTH;
		gbc_txtVerParadas.insets = new Insets(0, 0, 5, 5);
		gbc_txtVerParadas.gridx = 2;
		gbc_txtVerParadas.gridy = 19;
		add(txtVerParadas, gbc_txtVerParadas);
		
		JLabel txtVerIncidencias = new JLabel("Ver incidencias");
		txtVerIncidencias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verIncidenciasInterfaz();
			}
		});
		GridBagConstraints gbc_txtVerIncidencias = new GridBagConstraints();
		gbc_txtVerIncidencias.anchor = GridBagConstraints.NORTH;
		gbc_txtVerIncidencias.insets = new Insets(0, 0, 5, 5);
		gbc_txtVerIncidencias.gridx = 3;
		gbc_txtVerIncidencias.gridy = 19;
		add(txtVerIncidencias, gbc_txtVerIncidencias);
		
		JSeparator separator4 = new JSeparator();
		separator4.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator4 = new GridBagConstraints();
		gbc_separator4.anchor = GridBagConstraints.EAST;
		gbc_separator4.fill = GridBagConstraints.BOTH;
		gbc_separator4.gridx = 4;
		gbc_separator4.gridy = 20;
		add(separator4, gbc_separator4);

	}

}
