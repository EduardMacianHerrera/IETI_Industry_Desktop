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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

public class interfazMenuDesktop extends JFrame {

	//arxiuDades es el archivo que abrimos, se guardara como un File, el nombre de archivo de nomento no se usa, pero esta por
	//si queremos hacer rutas mas especificas
	//El directoriTreball es donde el File Chooser (objeto navegacio fitxers) abrira para que podamos seleccinar archivo
	private static JFileChooser navegacioFitxers;
	private static File arxiuDades;
	private static String nomArxiu;
	private static String directoriTreball=System.getProperty("user.dir");
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfazMenuDesktop frame = new interfazMenuDesktop();
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
	public interfazMenuDesktop() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
				arxiuDades=navegacioFitxers.getSelectedFile() ;
				if (arxiuDades != null) {
					System.out.println("S'ha obert un arxiu amb la ruta "+arxiuDades.getAbsolutePath().toString());
				}
			}
		});
		
		menuOpciones.add(opcioArxiu);
		
		JMenuItem opcioVisualitzacions = new JMenuItem("Visualitzacions");
		menuOpciones.add(opcioVisualitzacions);
		
		JPanel panelControles = new JPanel();
		contentPane.add(panelControles, BorderLayout.CENTER);
		GridBagLayout gbl_panelControles = new GridBagLayout();
		gbl_panelControles.columnWidths = new int[] {2};
		gbl_panelControles.rowHeights = new int[] {2};
		gbl_panelControles.columnWeights = new double[]{1.0, 1.0};
		gbl_panelControles.rowWeights = new double[]{1.0, 1.0};
		panelControles.setLayout(gbl_panelControles);
		
		JPanel panelSliders = new JPanel();
		GridBagConstraints gbc_panelSliders = new GridBagConstraints();
		gbc_panelSliders.fill = GridBagConstraints.BOTH;
		gbc_panelSliders.insets = new Insets(0, 0, 5, 0);
		gbc_panelSliders.gridx = 0;
		gbc_panelSliders.gridy = 0;
		panelControles.add(panelSliders, gbc_panelSliders);
		
		JPanel panelSpinners = new JPanel();
		GridBagConstraints gbc_panelSpinners = new GridBagConstraints();
		gbc_panelSpinners.insets = new Insets(0, 0, 5, 0);
		gbc_panelSpinners.fill = GridBagConstraints.BOTH;
		gbc_panelSpinners.gridx = 0;
		gbc_panelSpinners.gridy = 1;
		panelControles.add(panelSpinners, gbc_panelSpinners);
		
		JPanel panel3 = new JPanel();
		GridBagConstraints gbc_panel3 = new GridBagConstraints();
		gbc_panel3.insets = new Insets(0, 0, 5, 0);
		gbc_panel3.fill = GridBagConstraints.BOTH;
		gbc_panel3.gridx = 1;
		gbc_panel3.gridy = 0;
		panelControles.add(panel3, gbc_panel3);
		
		JPanel panel4 = new JPanel();
		GridBagConstraints gbc_panel4 = new GridBagConstraints();
		gbc_panel4.fill = GridBagConstraints.BOTH;
		gbc_panel4.gridx = 1;
		gbc_panel4.gridy = 1;
		panelControles.add(panel4, gbc_panel4);
		
		/*
		 * Codigo de prueba para hacer una funcion que coloque algunos elementos de ejemplo
		 * Hasta no tener el ArrayList o varios leidos del xml       
		 */
		JSpinner spinner1=new JSpinner();
		JSpinner spinner2=new JSpinner();
		JSpinner spinner3=new JSpinner();
		JSlider slider1=new JSlider();
		JSlider slider2=new JSlider();
		
		ArrayList<JSpinner> llistaSpinners=new ArrayList<>(Arrays.asList(spinner1, spinner2, spinner3));
		ArrayList<JSlider> llistaSliders=new ArrayList<>(Arrays.asList(slider1, slider2));
		colocarElements(llistaSpinners, llistaSliders, panelSpinners, panel3);
	}
	
	public void colocarElements(ArrayList<JSpinner> llistaSpinners, ArrayList<JSlider> llistaSliders, JPanel laminaSpinners, JPanel altrePanell) {
		for(int i=0;i<llistaSpinners.size();i++) {
			JPanel laminaInsertar=new JPanel();
			JSpinner elementSpinner=llistaSpinners.get(i);
			JLabel etiqueta=new JLabel("Etiqueta");
			laminaInsertar.add(etiqueta);
			laminaInsertar.add(elementSpinner);
		}
		
		for(int i=0;i<llistaSliders.size();i++) {
			JPanel laminaInsertar2=new JPanel();
			laminaInsertar2.setLayout(new BoxLayout(laminaInsertar2, 1));
			JSlider elementSlider=llistaSliders.get(i);
			JLabel etiqueta=new JLabel("Slider");
			laminaInsertar2.add(etiqueta);
			laminaInsertar2.add(elementSlider);
			altrePanell.add(laminaInsertar2);
		}
		

	}

}
