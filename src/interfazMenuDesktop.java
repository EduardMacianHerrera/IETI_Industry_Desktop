import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

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
	}
	
	class laminaContenedor extends JPanel{
		
	}

}
