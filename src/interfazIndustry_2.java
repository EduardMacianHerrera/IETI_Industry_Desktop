import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

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
		private static Modelo modelo;
		
		boolean scrollHabilitado=true;
		
		public interfazIndustry_2(final Modelo modelo) {
			this.modelo = modelo;
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			bordeGris1=BorderFactory.createLineBorder(Color.GRAY, 3);
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
						System.out.println(modelo.toString());
						vaciarPanelControles();
						colocarElements(modelo);
						contentPane.revalidate();
						contentPane.repaint();
					}
				}
			});			
			
			
			
			JMenuItem prueba = new JMenuItem("* TEST");
			prueba.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("TEST");
					String[] arrays = {"block1","1","slider","2"};
					updateInterfaz(arrays);
				}
			});
			
			
			JMenuItem opcioVisualitzacions = new JMenuItem("Visualitzacions");
			
			
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
			
			JMenuItem pruebaVaciarElementos=new JMenuItem("* Vaciar Panel de Controles");
			pruebaVaciarElementos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vaciarPanelControles();
				}
			});
			
			
			JMenuItem cargarModelo=new JMenuItem("* Cargar Modelo sin abrir xml");
			cargarModelo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vaciarPanelControles();
					colocarElements(modelo);
				}
			});
			
			
			JMenuItem verModelo=new JMenuItem("* Ver modelo en la terminal");
			verModelo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("\nNuestro actual modelo es: "+ modelo.toString());
				}
			});
			
			
			JMenuItem snapshot=new JMenuItem("SNAPSHOT");
			snapshot.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Se vienen cositas transformando la fecha a un formato para guardar en la BBDD
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
					LocalDateTime now = LocalDateTime.now();  
					System.out.println(dtf.format(now));  
					System.out.println("Modelo caturado: \n"+modelo.toString());
				}
			});
			
			
			JMenuItem carregarSnapshot=new JMenuItem("carregar SNAPSHOT");
			carregarSnapshot.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Selecciona un snapshot: ");
				}
			});
			
			menuOpciones.add(opcioArxiu);
			menuOpciones.add(opcioVisualitzacions);
			//Opciones que aun no es claro que lo quieran asi
			//Los de snapshot la descripción habla de botones
			menuOpciones.add(new JSeparator());
			menuOpciones.add(snapshot);
			menuOpciones.add(carregarSnapshot);
			menuOpciones.add(opcioBloquearScroll);
			//Opciones de prueba del codigo
			menuOpciones.add(new JSeparator());
			menuOpciones.add(prueba);
			menuOpciones.add(pruebaVaciarElementos);
			menuOpciones.add(cargarModelo);
			menuOpciones.add(verModelo);
			
			
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
			JOptionPane.showMessageDialog(lamina, mensajeError, "Lectura XML incorrecta", 0);
		}
		
		//Metodo para colocar los elementos
		public void colocarElements(Modelo modelo) {
			int anchoBloque=400;
			int altoBloque=400;
			ArrayList<Block> listaBloques = modelo.getBlocks();
			for(Block bloqueInsertar:listaBloques) {
				JPanel panelBloque=new JPanel();
				panelBloque.setPreferredSize(new Dimension(anchoBloque,altoBloque));
				panelBloque.setLayout(new BorderLayout());
				
				JPanel panelNombreBloq=new JPanel();
				JPanel panelContrBloq=new JPanel();
				
				panelNombreBloq.setPreferredSize(new Dimension(anchoBloque,altoBloque/8));
				
				panelNombreBloq.setBorder(bordeGris1);
				panelContrBloq.setBorder(bordeGris1);
				
				panelContrBloq.setLayout(new GridBagLayout());
				GridBagConstraints gbc=new GridBagConstraints();
				
				panelBloque.add(panelNombreBloq, BorderLayout.NORTH);
				panelBloque.add(panelContrBloq, BorderLayout.CENTER);
				
				JPanel panelSwitches=new JPanel();
				JPanel panelDropdowns=new JPanel();
				JPanel panelSliders=new JPanel();
				JPanel panelSensors=new JPanel();
				
				gbc.gridx=0;
				gbc.gridy=0;
				gbc.gridwidth=1;
				gbc.gridheight=1;
				panelContrBloq.add(panelSwitches, gbc);
				
				gbc.gridx=1;
				gbc.gridy=0;
				gbc.gridwidth=2;
				gbc.gridheight=1;
				panelContrBloq.add(panelSliders, gbc);
				
				gbc.gridx=0;
				gbc.gridy=1;
				gbc.gridwidth=1;
				gbc.gridheight=1;
				panelContrBloq.add(panelDropdowns, gbc);
				
				gbc.gridx=1;
				gbc.gridy=1;
				gbc.gridwidth=1;
				gbc.gridheight=1;
				panelContrBloq.add(panelSensors, gbc);
				panelContrBloq.setBackground(Color.LIGHT_GRAY);
				
				panelSwitches.setBackground(Color.CYAN);
				panelDropdowns.setBackground(Color.CYAN);
				panelSliders.setBackground(Color.CYAN);
				panelSensors.setBackground(Color.CYAN);
				
				ArrayList<Switch> switches = bloqueInsertar.getSwitches();
			    ArrayList<Dropdown> dropdowns = bloqueInsertar.getDropdowns();
			    ArrayList<Slider> sliders = bloqueInsertar.getSliders();
			    ArrayList<Sensor> sensors = bloqueInsertar.getSensors();
			    
			    JLabel nombreBloque=new JLabel(bloqueInsertar.getName());
			    nombreBloque.setFont(new Font("Arial", 20, 20));
			    panelNombreBloq.add(nombreBloque);
			    
			    //Primero coloca los switches
			    for(Switch sw:switches) {
			    	JPanel panelControl=new JPanel();
			    	panelControl.setBackground(Color.LIGHT_GRAY);
			    	String etiqueta=sw.getLabel();
			    	
			    	ChangeListener cListener=new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
//							System.out.println(sw.isSelected());
							if(sw.isSelected()) {
								sw.setState("on");
							}else {
								sw.setState("off");
							}
						}
					};
					sw.addChangeListener(cListener);
			    	
			    	panelControl.add(new JLabel(etiqueta));
			    	panelControl.add(sw);
			    	panelSwitches.add(panelControl);
			    }
			    
			    //Segundo coloca los sliders
			    for(Slider sl:sliders) {
			    	JPanel panelControl=new JPanel();
			    	panelControl.setBackground(Color.LIGHT_GRAY);
			    	String etiqueta=sl.getLabel();
			    	
				    ChangeListener cListener=new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
//							System.out.println("El valor de l'Slider es: "+sl.getValue());
							sl.setState(sl.getValue());
						}
					};
			    	sl.addChangeListener(cListener);
			    	
			    	
			    	panelControl.add(new JLabel(etiqueta));
			    	panelControl.add(sl);
			    	sl.setPreferredSize(new Dimension(anchoBloque/3, 20));
			    	panelSliders.add(panelControl);
			    }
			    
			    //Tercero coloca los depslegables
			    for(Dropdown dpd: dropdowns) {
			    	JPanel panelControl=new JPanel();
			    	panelControl.setBackground(Color.LIGHT_GRAY);
			    	String etiqueta=dpd.getLabel();
			    	
			    	ItemListener itListener=new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							//Con el indice sabemos que opcion es la seleccionada, pero no estoy seguro de si estoy estableciendo lo correcto
//							System.out.println(dpd.getSelectedIndex()); 
//							System.out.println(dpd.getSelectedItem());
							int indexSelected=dpd.getSelectedIndex();
							Option optionSelected=dpd.getoptions().get(indexSelected);
//							System.out.println("The selected option is "+optionSelected);
							String newState= optionSelected.getValue();
							dpd.setState(newState);
						}
					};
					dpd.addItemListener(itListener);
			    	
					panelControl.add(new JLabel(etiqueta));
			    	panelControl.add(dpd);
			    	panelDropdowns.add(panelControl);
			    }
			    
			    //Cuarto y ultimo coloca los sensores
			    for(Sensor sn:sensors) {
			    	JPanel panelControl=new JPanel();
			    	panelControl.setBackground(Color.LIGHT_GRAY);
			    	String etiqueta=sn.getLabel();
			    	
			    	panelControl.add(new JLabel(etiqueta));
			    	panelControl.add(sn);
			    	panelSensors.add(panelControl);
					
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
		
		//[[block1,2,slider,2],[block1,3,dropdown,1]]
		
		public static void updateInterfaz(String[] arrays) {
			String nameBlock = arrays[0];
			String id = arrays[1];
			String type = arrays[2];
			String value = arrays[3];
			for (Block b : modelo.getBlocks()) {
				if (b.getName().equals(nameBlock)) {
					switch (type) {
					case "switch":
						for (Switch s : b.getSwitches()) {
							if (id.equals(String.valueOf(s.getId()))) {
								if (value.equals("on")) {
									s.setSelected(true);
								} else {
									s.setSelected(false);
								}
							}
						}
						break;
						
					case "slider":
						for (Slider s : b.getSliders()) {
							if (id.equals(String.valueOf(s.getId()))) {
								s.setValue(Integer.parseInt(value));
							}
						}
						break;
						
					case "dropdown":
						for (Dropdown d : b.getDropdowns()) {
							if (id.equals(String.valueOf(d.getId()))) {
								d.setSelectedIndex(Integer.parseInt(value));
							}
						}
						break;
						
					case "sensor":
						for (Sensor s : b.getSensors()) {
							if (id.equals(String.valueOf(s.getId()))) {
								s.setText(value);
							}
						}
						break;

					default:
						break;
					}
				}
			}
		}
		
}
