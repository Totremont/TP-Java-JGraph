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
			UIManager.setLookAndFeel( new FlatDarkLaf() );
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
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		RelativeLayout rl = new RelativeLayout(RelativeLayout.X_AXIS);
		rl.setFill(true);
		contentPane.setLayout(rl);

		JPanel panel_izquierdo =  new MenuVerLineas(this);	//Cambiar para ver otra interfaz

		panel_izquierdo.setBorder(UIManager.getBorder("ComboBox.border"));
		contentPane.add(panel_izquierdo, 1f);

		JPanel panel_grafo = new JPanel();
		panel_grafo.setBorder(null);
		contentPane.add(panel_grafo, 1.5f);
		panel_grafo.setLayout(new BorderLayout(0, 0));
		
		JLabel labelSinGrafo = new JLabel("");
		labelSinGrafo.setIcon(new ImageIcon(getClass().getResource("/thumbnail.jpg")));
		labelSinGrafo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_grafo.add(labelSinGrafo, BorderLayout.CENTER);

		
	}

}
