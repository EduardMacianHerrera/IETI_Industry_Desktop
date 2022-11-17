import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class interfazMenuDesktop extends JFrame {

	//arxiuDades es el archivo que abrimos, se guardara como un File, el nombre de archivo de nomento no se usa, pero esta por
	//si queremos hacer rutas mas especificas
	//El directoriTreball es donde el File Chooser (objeto navegacio fitxers) abrira para que podamos seleccinar archivo
	private static JFileChooser navegacioFitxers;
	private static File arxiuDades;
	private static String nomArxiu;
	private static String directoriTreball=System.getProperty("user.dir");
	private JPanel contentPane;
	private JPanel panelSliders;
	private JPanel panelControles;
	private JPanel panelToggleButtons;
	private JPanel panelJTextField;
	private JPanel panelJComboBox;
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public interfazMenuDesktop(Modelo modelo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Objeto JMenuBar con un menu dentro, este menu tiene dentro dos items, 
		//arxiu al seleccionarlo desencadena un evento que nos aobre el explorador de archivos y nos permite guardar el archivo
		JMenuBar barraMenu = new JMenuBar();
		contentPane.add(barraMenu, BorderLayout.NORTH);
		
		JMenu menuOpciones = new JMenu("Opciones");
		barraMenu.add(menuOpciones);
		
		JMenuItem opcioArxiu = new JMenuItem("Arxiu");
		opcioArxiu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navegacioFitxers=new JFileChooser(directoriTreball);
				navegacioFitxers.showOpenDialog(new JFrame());
				arxiuDades=navegacioFitxers.getSelectedFile();
				if (arxiuDades != null) {
					System.out.println("S'ha obert un arxiu amb la ruta "+arxiuDades.getAbsolutePath().toString());
					
					
					try {
						
						LectorXML.cargarConfig(arxiuDades, modelo);
						
						

					} catch (Exception e1) {
						mostrarMissatgeError(contentPane,e1.getMessage());
						modelo.getControles().clear();
						//inicializarPaneles();
					}
					inicializarPaneles();
					colocarElements(modelo);
					contentPane.revalidate();
					contentPane.repaint();
				}
			}
		});
		
		menuOpciones.add(opcioArxiu);
		
		JMenuItem opcioVisualitzacions = new JMenuItem("Visualitzacions");
		menuOpciones.add(opcioVisualitzacions);
		
		JMenuItem opcioReiniciar=new JMenuItem("Reiniciar");
		opcioReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSliders.removeAll();
				panelJComboBox.removeAll();
				panelJTextField.removeAll();
				panelToggleButtons.removeAll();
				contentPane.revalidate();
				contentPane.repaint();
				System.out.println("Elements eliminats");
			}
		});
		menuOpciones.add(opcioReiniciar);
		
	}
	
	public void inicializarPaneles() {
		panelControles = new JPanel();
		contentPane.add(panelControles, BorderLayout.CENTER);
		GridBagLayout gbl_panelControles = new GridBagLayout();
		gbl_panelControles.columnWidths = new int[] {2};
		gbl_panelControles.rowHeights = new int[] {2};
		gbl_panelControles.columnWeights = new double[]{1.0, 1.0};
		gbl_panelControles.rowWeights = new double[]{1.0, 1.0};
		panelControles.setLayout(gbl_panelControles);
		
		/*********
		 * ********
		 */
		
		panelSliders = new JPanel();
		GridBagConstraints gbc_panelSliders = new GridBagConstraints();
		gbc_panelSliders.fill = GridBagConstraints.BOTH;
		gbc_panelSliders.insets = new Insets(0, 0, 5, 0);
		gbc_panelSliders.gridx = 0;
		gbc_panelSliders.gridy = 0;
		panelControles.add(panelSliders, gbc_panelSliders);
		
		panelToggleButtons = new JPanel();
		GridBagConstraints gbc_panelToggleButtons = new GridBagConstraints();
		gbc_panelToggleButtons.insets = new Insets(0, 0, 5, 0);
		gbc_panelToggleButtons.fill = GridBagConstraints.BOTH;
		gbc_panelToggleButtons.gridx = 0;
		gbc_panelToggleButtons.gridy = 1;
		panelControles.add(panelToggleButtons, gbc_panelToggleButtons);
		
		panelJComboBox = new JPanel();
		GridBagConstraints gbc_panelJComboBox = new GridBagConstraints();
		gbc_panelJComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_panelJComboBox.fill = GridBagConstraints.BOTH;
		gbc_panelJComboBox.gridx = 1;
		gbc_panelJComboBox.gridy = 0;
		panelControles.add(panelJComboBox, gbc_panelJComboBox);
		
		panelJTextField = new JPanel();
		GridBagConstraints gbc_panelJTextField = new GridBagConstraints();
		gbc_panelJTextField.fill = GridBagConstraints.BOTH;
		gbc_panelJTextField.gridx = 1;
		gbc_panelJTextField.gridy = 1;
		panelControles.add(panelJTextField, gbc_panelJTextField);

	}
	
	public void mostrarMissatgeError(JPanel lamina, String mensajeError) {
		JOptionPane.showMessageDialog(lamina, mensajeError, "lectura XML incorrecta", 0);
	}
	
	public void colocarElements(Modelo modelo) {
		
		ArrayList<Control> listaElementos = modelo.getControles();
		for(int i=0;i<listaElementos.size();i++) {
			JPanel panelInsertar=new JPanel();
			Control elemento=listaElementos.get(i);
			String tipoControl = elemento.getTipoControl();
			String etiqueta=elemento.getLabel();
			JLabel etiquetaBoton=new JLabel(elemento.getLabel());
			panelInsertar.add(etiquetaBoton);
			switch (tipoControl) {
			case "switch":
				JToggleButton boton=(JToggleButton) elemento.getControl();
				panelInsertar.add(boton);
				panelToggleButtons.add(panelInsertar);
				break;
			case "slider":
				JSlider slider =(JSlider) elemento.getControl();
				int rango=slider.getMaximum()-slider.getMinimum();
				slider.setPaintTicks(true);
				slider.setPaintLabels(true);
				slider.setMinorTickSpacing(rango/10);
				slider.setMajorTickSpacing(rango/2);
				
				panelInsertar.add(slider);
				panelSliders.add(panelInsertar);
				break;
			case "sensor":
				JTextField campoTexto=(JTextField) elemento.getControl();
				campoTexto.setText(elemento.getUnits());
				panelInsertar.add(campoTexto);
				panelJTextField.add(panelInsertar);
				break;
			case "dropdown":
				JComboBox desplegable=(JComboBox) elemento.getControl();
				panelInsertar.add(desplegable);
				panelJComboBox.add(panelInsertar);
				break;

			default:
				break;
			}
			
		}
	}
		
}
