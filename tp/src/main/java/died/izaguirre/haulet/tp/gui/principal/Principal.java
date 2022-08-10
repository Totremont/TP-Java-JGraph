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

import died.izaguirre.haulet.tp.controladores.ControladorGrafo;
import died.izaguirre.haulet.tp.gui.incidencias.IncidenciasPanel;
import died.izaguirre.haulet.tp.gui.layoutespecial.RelativeLayout;
import died.izaguirre.haulet.tp.gui.menulineas.MenuVerLineas;
import died.izaguirre.haulet.tp.gui.menulineas.VerInfoLinea;
import died.izaguirre.haulet.tp.gui.menuparadas.CrearParada;
import died.izaguirre.haulet.tp.gui.menuparadas.ParadasPanel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.BoxLayout;
import javax.swing.Icon;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import org.graphstream.graph.Graph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.View;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JPanel ultimoPanel;
	private JPanel panel_grafo;
	private JTextField txtOpciones;
	private ControladorGrafo controladorGrafo = ControladorGrafo.getInstance();

	/**
	 * Lanza la aplicación manejando el GUI desde un hilo secundario
	 */	
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel( new FlatDarkLaf() );
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal("Sistema de Transporte");
					Image icon = Toolkit.getDefaultToolkit().
							getImage(getClass().getResource("/navigation-variant.png").getFile());  
					frame.setIconImage(icon);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Principal(String titulo) {
		
		super(titulo);
		
		SwingViewer grafo = controladorGrafo.getViewer();
		grafo.enableAutoLayout();
		View view = grafo.addDefaultView(false);
		crearInterfaz(new PrincipalPanel(this), view);
		
	}
	
	public void cambiarInterfaz(JPanel nuevaInterfaz) 
	{
		contentPane.remove(ultimoPanel);
		contentPane.remove(panel_grafo);
		contentPane.add(nuevaInterfaz, 1f);
		contentPane.add(panel_grafo, 1.5f);
		ultimoPanel = nuevaInterfaz;
		revalidate();
	}
	
	private void volverAtras() 
	{
		cambiarInterfaz(new PrincipalPanel(this));
	}
	
	private void parametrosMenu() 
	{
		new EstablecerKm(this);
	}
	
	private void acercaDe() 
	{
		JDialog dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		dialog.setLocationRelativeTo(this);
		JOptionPane.showMessageDialog(dialog, "Aplicación desarrollada por Izaguirre Ezequiel y Tomás Haulet para la cátedra de Disenio e Implementación de Estructuras de Datos de la facultad UTN FRSF. 2022",
				"Acerca de", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void crearInterfaz(JPanel panel_izquierdo, View vista_derecha)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1010, 560);
		contentPane = new JPanel();
		ultimoPanel = panel_izquierdo;
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Boton volver
		JMenu volver = new JMenu("Volver");
		volver.setIcon(new ImageIcon(getClass().getResource("/chevron-white.png")));
		volver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!(ultimoPanel.getClass() == PrincipalPanel.class)) volverAtras();
				
			}
		});
		menuBar.add(volver);
		
		//Boton opciones externas
		JMenu externo = new JMenu("Opciones");
		externo.setMnemonic(KeyEvent.VK_A);
		menuBar.add(externo);
		JMenuItem precioKm = new JMenuItem("Establecer precios");
		precioKm.setMnemonic(KeyEvent.VK_B);
		precioKm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parametrosMenu();
			}
		});
		externo.add(precioKm);
		menuBar.add(externo);
		
		//Boton saber mas
		JMenu saberMas = new JMenu("Acerca de");
		saberMas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				acercaDe();
				
			}
		});
		menuBar.add(saberMas);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		RelativeLayout rl = new RelativeLayout(RelativeLayout.X_AXIS);
		rl.setFill(true);
		contentPane.setLayout(rl);


		panel_izquierdo.setBorder(UIManager.getBorder("ComboBox.border"));
		contentPane.add(panel_izquierdo, 1f);
		
		panel_grafo = new JPanel();		
		panel_grafo.setBackground(SystemColor.textInactiveText);
		panel_grafo.setBorder(null);
		contentPane.add(panel_grafo, 1.5f);
		panel_grafo.setLayout(new BorderLayout(0, 0));
			
		panel_grafo.add((Component) vista_derecha, BorderLayout.CENTER);
	} 
				

}
