import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class interfazIndustry_2 extends JFrame {
	
    public static void main(String[] args) {   
        Modelo modelo = new Modelo();
        interfazIndustry_2 interfaz = new interfazIndustry_2(modelo);
        interfaz.setVisible(true);
    }
    	
		private static JFileChooser navegacioFitxers;
		private static File arxiuDades;
		private static String nomArxiu;
		private static String directoriTreball=System.getProperty("user.dir");
		private JPanel contentPane;
		private JScrollPane panelScroll;
		private JPanel panelControles;
		private Border bordeGris1;
		private Border bordeVacio1;
		
		boolean scrollHabilitado=true;
		
		public interfazIndustry_2(final Modelo modelo) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			bordeGris1=BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3);
			bordeVacio1=new EmptyBorder(5, 5, 5, 5);
			
			setBounds(100, 100, 600, 400);
			contentPane = new JPanel();
			contentPane.setBorder(bordeVacio1);
			
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			
			//Codigo de JMenuBar
			JMenuBar barraMenu = new JMenuBar();
			contentPane.add(barraMenu, BorderLayout.NORTH);
			
			JMenu menuOpciones = new JMenu("Opciones");
			barraMenu.add(menuOpciones);
			barraMenu.setBorder(bordeGris1);
			
			JMenuItem opcioArxiu = new JMenuItem("Arxiu");
			opcioArxiu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					navegacioFitxers=new JFileChooser(directoriTreball);
					navegacioFitxers.showOpenDialog(new JFrame());
					arxiuDades=navegacioFitxers.getSelectedFile();
					if (arxiuDades != null) {
						System.out.println("S'ha obert un arxiu amb la ruta "+arxiuDades.getAbsolutePath().toString());
						try {
							LectorXML.loadConfig(arxiuDades, modelo);
						} catch (Exception e1) {
							e1.printStackTrace();
							mostrarMissatgeError(contentPane,e1.getMessage());
							modelo.getBlocks().clear();
						}
						colocarElements(modelo);
						contentPane.revalidate();
						contentPane.repaint();
					}
				}
			});
			
			menuOpciones.add(opcioArxiu);
			
			JMenuItem opcioVisualitzacions = new JMenuItem("Visualitzacions");
			menuOpciones.add(opcioVisualitzacions);
			
			menuOpciones.add(new JSeparator());
			
			JMenuItem opcioBloquearScroll=new JMenuItem("Bloquear barras Scroll");
			opcioBloquearScroll.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(scrollHabilitado) {
						panelScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
						panelScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
						scrollHabilitado=false;
					}else {
						panelScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
						panelScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						scrollHabilitado=true;
					}

				}
			});
			menuOpciones.add(opcioBloquearScroll);
			
			JMenuItem pruebaCargarElementos=new JMenuItem("Prueba de Carga Areas Texto");
			pruebaCargarElementos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					colocarElementsProva1();
				}
			});
			menuOpciones.add(pruebaCargarElementos);
			
			JMenuItem pruebaCargarPaneles=new JMenuItem("Prueba de Carga de Paneles");
			pruebaCargarPaneles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					colocarElementsProva2();
				}
			});
			menuOpciones.add(pruebaCargarPaneles);
			
			JMenuItem pruebaVaciarElementos=new JMenuItem("Vaciar Panel de Controles");
			pruebaVaciarElementos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vaciarPanelControles();
				}
			});
			menuOpciones.add(pruebaVaciarElementos);
			//Fin de JMenuBar
			
			//Creamos el panel de control que tendra una barra de scroll 
			panelScroll=new JScrollPane();
			panelScroll.setBorder(bordeGris1);
			
			panelControles = new JPanel();
//			panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.Y_AXIS));
			panelScroll.setViewportView(panelControles);
			contentPane.add(panelScroll, BorderLayout.CENTER);
		}
		public void mostrarMissatgeError(JPanel lamina, String mensajeError) {
			JOptionPane.showMessageDialog(lamina, mensajeError, "lectura XML incorrecta", 0);
		}
		
		//Metodo para colocar los elementos
		public void colocarElements(Modelo modelo) {
			ArrayList<Block> listaBloques = modelo.getBlocks();
			for(Block bloqueInsertar:listaBloques) {
				System.out.println("Inserta un bloque");
				JPanel panelBloque=new JPanel();
				panelBloque.setBackground(Color.CYAN);
				panelBloque.setPreferredSize(new Dimension(400,400));
				
				ArrayList<Switch> switches = bloqueInsertar.getSwitches();
			    ArrayList<Dropdown> dropdowns = bloqueInsertar.getDropdowns();
			    ArrayList<Slider> sliders = bloqueInsertar.getSliders();
			    ArrayList<Sensor> sensors = bloqueInsertar.getSensors();
			    
			    //Primero coloca los switches
			    for(Switch sw:switches) {
					System.out.println("Hemos colocado un switch");
			    	JPanel panelControl=new JPanel();
			    	panelControl.setBackground(Color.LIGHT_GRAY);
			    	String etiqueta=sw.getLabel();
			    	
			    	panelControl.add(new JLabel(etiqueta));
			    	panelControl.add(sw);
			    	panelBloque.add(panelControl);
			    }
			    
			    //Segundo coloca los sliders
			    for(Slider sl:sliders) {
					System.out.println("Hemos colocado un slider");
			    	JPanel panelControl=new JPanel();
			    	panelControl.setBackground(Color.LIGHT_GRAY);
			    	String etiqueta=sl.getLabel();
					System.out.println("Slider añadido :"+sl.getMin()+" "+sl.getMaximum());
			    	
			    	panelControl.add(new JLabel(etiqueta));
			    	panelControl.add(sl);
			    	panelBloque.add(panelControl);
			    }
			    
			    //Tercero coloca los depslegables
			    for(Dropdown dpd: dropdowns) {
					System.out.println("Hemos colocado un desplegable");
			    	JPanel panelControl=new JPanel();
			    	panelControl.setBackground(Color.LIGHT_GRAY);
			    	String etiqueta=dpd.getLabel();
			    	
					panelControl.add(new JLabel(etiqueta));
			    	panelControl.add(dpd);
			    	panelBloque.add(panelControl);
			    }
			    
			    //Cuarto y ultimo coloca los sensores
			    for(Sensor sn:sensors) {
					System.out.println("Hemos colocado un sensor");
			    	JPanel panelControl=new JPanel();
			    	panelControl.setBackground(Color.LIGHT_GRAY);
			    	String etiqueta=sn.getLabel();
			    	
			    	panelControl.add(new JLabel(etiqueta));
			    	panelControl.add(sn);
			    	panelBloque.add(panelControl);
					
			    }
				panelControles.add(panelBloque);
			}
			panelControles.repaint();
			panelControles.revalidate();
			
		}
		//Fin metodo colocar elementos
		
		public void vaciarPanelControles() {
			panelControles.removeAll();
			panelControles.repaint();
			panelControles.revalidate();
		}
		
		//Funcion de prueba, no es definitiva
		public void colocarElementsProva1() {
			for(int i=0; i<5; i++) {
				JTextArea areaAdd=new JTextArea("Area de texto de prueba\n"
						+ "Con un salto de linea");
				areaAdd.setPreferredSize(new Dimension(600,600));
				panelControles.add(areaAdd);
				panelControles.repaint();
				panelControles.revalidate();
			}
		}
		
		public void colocarElementsProva2() {
			for(int i=0; i<4;i++) {
				JPanel panelColocar=new JPanel();
				panelColocar.setPreferredSize(new Dimension(400, 400));
				panelColocar.setBackground(Color.CYAN);
				panelControles.add(panelColocar);
				panelControles.repaint();
				panelControles.revalidate();
			}
		}
		

}
