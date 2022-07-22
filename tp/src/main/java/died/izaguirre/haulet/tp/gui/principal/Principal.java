package died.izaguirre.haulet.tp.gui.principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import died.izaguirre.haulet.tp.gui.principal.menulineas.MenuVerLineas;
import died.izaguirre.haulet.tp.gui.incidencias.IncidenciasPanel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Icon;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField txtOpciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0};
		gbl_contentPane.columnWeights = new double[]{0.4, 0.6d};
		gbl_contentPane.rowWeights = new double[]{1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_izquierdo = new JPanel();
		//JPanel panel_izquierdo = new IncidenciasPanel();
		panel_izquierdo.setBorder(new LineBorder(Color.GRAY));
		GridBagConstraints gbc_panel_izquierdo = new GridBagConstraints();
		gbc_panel_izquierdo.fill = GridBagConstraints.BOTH;
		gbc_panel_izquierdo.gridx = 0;
		gbc_panel_izquierdo.gridy = 0;
		contentPane.add(panel_izquierdo, gbc_panel_izquierdo);
		
		JPanel panel_grafo = new JPanel();
		GridBagConstraints gbc_panel_grafo = new GridBagConstraints();
		gbc_panel_grafo.weightx = 10.0;
		gbc_panel_grafo.fill = GridBagConstraints.BOTH;
		gbc_panel_grafo.gridx = 1;
		gbc_panel_grafo.gridy = 0;
		contentPane.add(panel_grafo, gbc_panel_grafo);
		panel_grafo.setLayout(new BorderLayout(0, 0));

		GridBagLayout gbl_panel_izquierdo = new GridBagLayout();
		gbl_panel_izquierdo.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_izquierdo.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_izquierdo.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_izquierdo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_izquierdo.setLayout(gbl_panel_izquierdo);
		
		JLabel txtTitulo = new JLabel("SISTEMA DE TRANSPORTE");
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitulo.gridx = 2;
		gbc_txtTitulo.gridy = 1;
		panel_izquierdo.add(txtTitulo, gbc_txtTitulo);
		
		JLabel txtsubTitulo = new JLabel("Observe las rutas disponibles");
		GridBagConstraints gbc_txtsubTitulo = new GridBagConstraints();
		gbc_txtsubTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtsubTitulo.gridx = 2;
		gbc_txtsubTitulo.gridy = 2;
		panel_izquierdo.add(txtsubTitulo, gbc_txtsubTitulo);
		
		JSeparator separator1 = new JSeparator();
		GridBagConstraints gbc_separator1 = new GridBagConstraints();
		gbc_separator1.insets = new Insets(0, 0, 5, 5);
		gbc_separator1.fill = GridBagConstraints.BOTH;
		gbc_separator1.gridx = 2;
		gbc_separator1.gridy = 3;
		panel_izquierdo.add(separator1, gbc_separator1);
		
		JLabel txtOrigen = new JLabel("Origen");
		GridBagConstraints gbc_txtOrigen = new GridBagConstraints();
		gbc_txtOrigen.anchor = GridBagConstraints.EAST;
		gbc_txtOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_txtOrigen.gridx = 1;
		gbc_txtOrigen.gridy = 4;
		panel_izquierdo.add(txtOrigen, gbc_txtOrigen);
		
		JLabel txtDestino = new JLabel("Destino");
		GridBagConstraints gbc_txtDestino = new GridBagConstraints();
		gbc_txtDestino.anchor = GridBagConstraints.EAST;
		gbc_txtDestino.insets = new Insets(0, 0, 5, 5);
		gbc_txtDestino.gridx = 1;
		gbc_txtDestino.gridy = 5;
		panel_izquierdo.add(txtDestino, gbc_txtDestino);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 4;
		panel_izquierdo.add(comboBox, gbc_comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 5;
		panel_izquierdo.add(comboBox_1, gbc_comboBox_1);
		
		JLabel txtInvertir = new JLabel("Invertir");
		GridBagConstraints gbc_txtInvertir = new GridBagConstraints();
		gbc_txtInvertir.insets = new Insets(0, 0, 5, 5);
		gbc_txtInvertir.gridx = 3;
		gbc_txtInvertir.gridy = 5;
		txtInvertir.setIcon(new ImageIcon(getClass().getResource("/swap-vertical.png")));
		panel_izquierdo.add(txtInvertir, gbc_txtInvertir);
		
		JButton buttonBuscar = new JButton("Buscar");
		GridBagConstraints gbc_buttonBuscar = new GridBagConstraints();
		gbc_buttonBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_buttonBuscar.gridx = 2;
		gbc_buttonBuscar.gridy = 6;
		panel_izquierdo.add(buttonBuscar, gbc_buttonBuscar);
		
		JRadioButton buttonRutaCorta = new JRadioButton("Ruta más corta");
		GridBagConstraints gbc_buttonRutaCorta = new GridBagConstraints();
		gbc_buttonRutaCorta.insets = new Insets(0, 0, 5, 5);
		gbc_buttonRutaCorta.gridx = 1;
		gbc_buttonRutaCorta.gridy = 7;
		panel_izquierdo.add(buttonRutaCorta, gbc_buttonRutaCorta);
		
		JRadioButton buttonRutaRapida = new JRadioButton("Ruta más rápida");
		GridBagConstraints gbc_buttonRutaRapida = new GridBagConstraints();
		gbc_buttonRutaRapida.insets = new Insets(0, 0, 5, 5);
		gbc_buttonRutaRapida.gridx = 2;
		gbc_buttonRutaRapida.gridy = 7;
		panel_izquierdo.add(buttonRutaRapida, gbc_buttonRutaRapida);
		
		JRadioButton buttonRutaBarata = new JRadioButton("Ruta más barata");
		GridBagConstraints gbc_buttonRutaBarata = new GridBagConstraints();
		gbc_buttonRutaBarata.insets = new Insets(0, 0, 5, 5);
		gbc_buttonRutaBarata.gridx = 3;
		gbc_buttonRutaBarata.gridy = 7;
		panel_izquierdo.add(buttonRutaBarata, gbc_buttonRutaBarata);
		
		JSeparator separator2 = new JSeparator();
		GridBagConstraints gbc_separator2 = new GridBagConstraints();
		gbc_separator2.insets = new Insets(0, 0, 5, 5);
		gbc_separator2.gridwidth = 3;
		gbc_separator2.fill = GridBagConstraints.BOTH;
		gbc_separator2.gridx = 1;
		gbc_separator2.gridy = 8;
		panel_izquierdo.add(separator2, gbc_separator2);
		
		JLabel txtUltimaRuta = new JLabel("ÚLTIMA RUTA");
		GridBagConstraints gbc_txtUltimaRuta = new GridBagConstraints();
		gbc_txtUltimaRuta.insets = new Insets(0, 0, 5, 5);
		gbc_txtUltimaRuta.gridx = 1;
		gbc_txtUltimaRuta.gridy = 9;
		panel_izquierdo.add(txtUltimaRuta, gbc_txtUltimaRuta);
		
		JLabel txtLinea = new JLabel("Línea 9");
		GridBagConstraints gbc_txtLinea = new GridBagConstraints();
		gbc_txtLinea.insets = new Insets(0, 0, 5, 5);
		gbc_txtLinea.gridx = 2;
		gbc_txtLinea.gridy = 9;
		txtLinea.setIcon(new ImageIcon(getClass().getResource("/bus.png")));
		panel_izquierdo.add(txtLinea, gbc_txtLinea);
		
		JLabel txtComodidades = new JLabel("Comodidades");
		GridBagConstraints gbc_txtComodidades = new GridBagConstraints();
		gbc_txtComodidades.anchor = GridBagConstraints.WEST;
		gbc_txtComodidades.insets = new Insets(0, 0, 5, 5);
		gbc_txtComodidades.gridx = 3;
		gbc_txtComodidades.gridy = 9;
		panel_izquierdo.add(txtComodidades, gbc_txtComodidades);
		
		JLabel txtOrigen2 = new JLabel("Origen");
		GridBagConstraints gbc_txtOrigen2 = new GridBagConstraints();
		gbc_txtOrigen2.insets = new Insets(0, 0, 5, 5);
		gbc_txtOrigen2.gridx = 1;
		gbc_txtOrigen2.gridy = 10;
		panel_izquierdo.add(txtOrigen2, gbc_txtOrigen2);
		
		JLabel txtOrigen3 = new JLabel("Alberdi");
		GridBagConstraints gbc_txtOrigen3 = new GridBagConstraints();
		gbc_txtOrigen3.insets = new Insets(0, 0, 5, 5);
		gbc_txtOrigen3.gridx = 2;
		gbc_txtOrigen3.gridy = 10;
		panel_izquierdo.add(txtOrigen3, gbc_txtOrigen3);
		
		JSeparator separator3 = new JSeparator();
		separator3.setForeground(Color.CYAN);
		separator3.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator3 = new GridBagConstraints();
		gbc_separator3.anchor = GridBagConstraints.EAST;
		gbc_separator3.gridheight = 4;
		gbc_separator3.fill = GridBagConstraints.BOTH;
		gbc_separator3.insets = new Insets(0, 0, 5, 5);
		gbc_separator3.gridx = 0;
		gbc_separator3.gridy = 10;
		panel_izquierdo.add(separator3, gbc_separator3);
		
		JCheckBox checkAC = new JCheckBox("AC");
		GridBagConstraints gbc_checkAC = new GridBagConstraints();
		gbc_checkAC.anchor = GridBagConstraints.WEST;
		gbc_checkAC.insets = new Insets(0, 0, 5, 5);
		gbc_checkAC.gridx = 3;
		gbc_checkAC.gridy = 10;
		panel_izquierdo.add(checkAC, gbc_checkAC);
		
		JLabel txtDestino2 = new JLabel("Destino");
		GridBagConstraints gbc_txtDestino2 = new GridBagConstraints();
		gbc_txtDestino2.insets = new Insets(0, 0, 5, 5);
		gbc_txtDestino2.gridx = 1;
		gbc_txtDestino2.gridy = 11;
		panel_izquierdo.add(txtDestino2, gbc_txtDestino2);
		
		JLabel txtDestino3 = new JLabel("Casino");
		GridBagConstraints gbc_txtDestino3 = new GridBagConstraints();
		gbc_txtDestino3.insets = new Insets(0, 0, 5, 5);
		gbc_txtDestino3.gridx = 2;
		gbc_txtDestino3.gridy = 11;
		panel_izquierdo.add(txtDestino3, gbc_txtDestino3);
		
		JCheckBox checkWifi = new JCheckBox("Wi-Fi");
		GridBagConstraints gbc_checkWifi = new GridBagConstraints();
		gbc_checkWifi.anchor = GridBagConstraints.WEST;
		gbc_checkWifi.insets = new Insets(0, 0, 5, 5);
		gbc_checkWifi.gridx = 3;
		gbc_checkWifi.gridy = 11;
		panel_izquierdo.add(checkWifi, gbc_checkWifi);
		
		JLabel txtDistancia = new JLabel("Distancia");
		GridBagConstraints gbc_txtDistancia = new GridBagConstraints();
		gbc_txtDistancia.insets = new Insets(0, 0, 5, 5);
		gbc_txtDistancia.gridx = 1;
		gbc_txtDistancia.gridy = 12;
		panel_izquierdo.add(txtDistancia, gbc_txtDistancia);
		
		JLabel txtDistancia2 = new JLabel("3.5 Km");
		GridBagConstraints gbc_txtDistancia2 = new GridBagConstraints();
		gbc_txtDistancia2.insets = new Insets(0, 0, 5, 5);
		gbc_txtDistancia2.gridx = 2;
		gbc_txtDistancia2.gridy = 12;
		panel_izquierdo.add(txtDistancia2, gbc_txtDistancia2);
		
		JLabel txtPrecio = new JLabel("Precio");
		GridBagConstraints gbc_txtPrecio = new GridBagConstraints();
		gbc_txtPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrecio.gridx = 1;
		gbc_txtPrecio.gridy = 13;
		panel_izquierdo.add(txtPrecio, gbc_txtPrecio);
		
		JLabel txtPrecio2 = new JLabel("25$");
		GridBagConstraints gbc_txtPrecio2 = new GridBagConstraints();
		gbc_txtPrecio2.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrecio2.gridx = 2;
		gbc_txtPrecio2.gridy = 13;
		panel_izquierdo.add(txtPrecio2, gbc_txtPrecio2);
		
		JButton buttonBoleto = new JButton("Comprar Boleto");
		GridBagConstraints gbc_buttonBoleto = new GridBagConstraints();
		gbc_buttonBoleto.insets = new Insets(5, 0, 5, 5);
		gbc_buttonBoleto.gridx = 2;
		gbc_buttonBoleto.gridy = 14;
		buttonBoleto.setIcon(new ImageIcon(getClass().getResource("/ticket-confirmation.png")));
		panel_izquierdo.add(buttonBoleto, gbc_buttonBoleto);
		
		txtOpciones = new JTextField();
		txtOpciones.setEditable(false);
		txtOpciones.setHorizontalAlignment(SwingConstants.CENTER);
		txtOpciones.setText("OPCIONES");
		GridBagConstraints gbc_txtOpciones = new GridBagConstraints();
		gbc_txtOpciones.insets = new Insets(10, 0, 5, 5);
		gbc_txtOpciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOpciones.gridx = 2;
		gbc_txtOpciones.gridy = 15;
		panel_izquierdo.add(txtOpciones, gbc_txtOpciones);
		txtOpciones.setColumns(10);
		
		JLabel imgLinea = new JLabel("");
		GridBagConstraints gbc_imgLinea = new GridBagConstraints();
		gbc_imgLinea.insets = new Insets(5, 0, 5, 5);
		gbc_imgLinea.gridx = 1;
		gbc_imgLinea.gridy = 16;
		imgLinea.setIcon(new ImageIcon(getClass().getResource("/bus-marker.png")));
		panel_izquierdo.add(imgLinea, gbc_imgLinea);
		
		JLabel imgParada = new JLabel("");
		GridBagConstraints gbc_imgParada = new GridBagConstraints();
		gbc_imgParada.insets = new Insets(5, 0, 5, 5);
		gbc_imgParada.gridx = 2;
		gbc_imgParada.gridy = 16;
		imgParada.setIcon(new ImageIcon(getClass().getResource("/map-marker-plus.png")));
		panel_izquierdo.add(imgParada, gbc_imgParada);
		
		JLabel imgIncidencia = new JLabel("");
		GridBagConstraints gbc_imgIncidencia = new GridBagConstraints();
		gbc_imgIncidencia.insets = new Insets(5, 0, 5, 5);
		gbc_imgIncidencia.gridx = 3;
		gbc_imgIncidencia.gridy = 16;
		imgIncidencia.setIcon(new ImageIcon(getClass().getResource("/alert-octagon.png")));
		panel_izquierdo.add(imgIncidencia, gbc_imgIncidencia);
		
		JLabel txtVerLinea = new JLabel("Ver líneas");
		GridBagConstraints gbc_txtVerLinea = new GridBagConstraints();
		gbc_txtVerLinea.insets = new Insets(0, 0, 5, 5);
		gbc_txtVerLinea.gridx = 1;
		gbc_txtVerLinea.gridy = 17;
		panel_izquierdo.add(txtVerLinea, gbc_txtVerLinea);
		
		JLabel txtVerParadas = new JLabel("Ver paradas");
		GridBagConstraints gbc_txtVerParadas = new GridBagConstraints();
		gbc_txtVerParadas.insets = new Insets(0, 0, 5, 5);
		gbc_txtVerParadas.gridx = 2;
		gbc_txtVerParadas.gridy = 17;
		panel_izquierdo.add(txtVerParadas, gbc_txtVerParadas);
		
		JLabel txtVerIncidencias = new JLabel("Ver incidencias");
		GridBagConstraints gbc_txtVerIncidencias = new GridBagConstraints();
		gbc_txtVerIncidencias.insets = new Insets(0, 0, 5, 5);
		gbc_txtVerIncidencias.gridx = 3;
		gbc_txtVerIncidencias.gridy = 17;
		panel_izquierdo.add(txtVerIncidencias, gbc_txtVerIncidencias);
		
		JSeparator separator4 = new JSeparator();
		separator4.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator4 = new GridBagConstraints();
		gbc_separator4.anchor = GridBagConstraints.EAST;
		gbc_separator4.fill = GridBagConstraints.BOTH;
		gbc_separator4.gridx = 4;
		gbc_separator4.gridy = 18;
		panel_izquierdo.add(separator4, gbc_separator4);
		
		
		JLabel labelSinGrafo = new JLabel("");
		labelSinGrafo.setIcon(new ImageIcon(getClass().getResource("/thumbnail.jpg")));
		labelSinGrafo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_grafo.add(labelSinGrafo, BorderLayout.CENTER);

		
	}

}
