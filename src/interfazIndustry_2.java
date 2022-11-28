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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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
		private static JFileChooser navegacioFitxers;
		private static File arxiuDades;
		private static String nomArxiu;
		private static String directoriTreball=System.getProperty("user.dir");
		private JPanel contentPane;
		private JScrollPane panelScroll;
		private static JPanel panelControles;
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
			
			JMenuItem snapshot=new JMenuItem("SNAPSHOT");
			snapshot.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nombreSnapshot= JOptionPane.showInputDialog("Introduzca un nombre para el Snapshot", 0);
					System.out.println("El snapshot se guardara como "+nombreSnapshot);
					Database.saveSnapshot(nombreSnapshot, modelo.toString());
				}
			});
			
			
			JMenuItem carregarSnapshot=new JMenuItem("carregar SNAPSHOT");
			carregarSnapshot.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Selecciona un snapshot: ");
					HashMap<Integer, String> snapshotsMap = Database.listSnapshots();
                    for (Map.Entry<Integer, String> snapshot : snapshotsMap.entrySet()) {
                        System.out.println("Id: "+snapshot.getKey()+", Name: "+snapshot.getValue());
                    }
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Escoge:");
					frameSnapshots marcoSnapshots=new frameSnapshots();
					marcoSnapshots.setVisible(true);
					if(marcoSnapshots.getNumSnapshots()==0) {
						marcoSnapshots.mostrarMensajeError();
					}
					dispose();
				}
			});
			
			menuOpciones.add(opcioArxiu);
			menuOpciones.add(opcioVisualitzacions);
			menuOpciones.add(new JSeparator());
			menuOpciones.add(snapshot);
			menuOpciones.add(carregarSnapshot);
			menuOpciones.add(new JSeparator());
			menuOpciones.add(opcioBloquearScroll);
			//Fin de JMenuBar
			
			//Creamos el panel de control que tendra una barra de scroll 
			panelScroll=new JScrollPane();
			panelScroll.setBorder(bordeGris1);
			
			panelControles = new JPanel();
			panelScroll.setViewportView(panelControles);
			contentPane.add(panelScroll, BorderLayout.CENTER);
		}
		public void mostrarMissatgeError(JPanel lamina, String mensajeError) {
			JOptionPane.showMessageDialog(lamina, mensajeError, "Lectura XML incorrecta", 0);
		}
		
		//Metodo para colocar los elementos
		public static void colocarElements(Modelo modelo) {
			int anchoEspecificado=400;
			int altoEspecificado=400;
			ArrayList<Block> listaBloques = modelo.getBlocks();
			for(Block bloqueInsertar:listaBloques) {
				bloqueGridBagLayout panelBloque=new bloqueGridBagLayout();
				
				ArrayList<Switch> switches = bloqueInsertar.getSwitches();
			    ArrayList<Dropdown> dropdowns = bloqueInsertar.getDropdowns();
			    ArrayList<Slider> sliders = bloqueInsertar.getSliders();
			    ArrayList<Sensor> sensors = bloqueInsertar.getSensors();

			    panelBloque.enterBlockName(bloqueInsertar.getName());
			    
			    //Primero coloca los switches
			    for(Switch sw:switches) {
			    	JPanel panelControl=new JPanel();
			    	panelControl.setOpaque(false);
			    	String etiqueta=sw.getLabel();
			    	
			    	ChangeListener cListener=new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
							if(sw.isSelected()) {
								sw.setState("on");
							}else {
								sw.setState("off");
							}
							String[] values = {"block1", String.valueOf(sw.getId()), "switch", sw.getState()};
							WsServidor.change(values);
						}
					};
					sw.addChangeListener(cListener);
					if (sw.getState().equals("on")) {
						sw.setSelected(true);
					}
			    	panelControl.add(new JLabel(etiqueta));
			    	panelControl.add(sw);
			    	panelBloque.addSwitch(panelControl);
			    }
			    
			    //Segundo coloca los sliders
			    for(Slider sl:sliders) {
			    	JPanel panelControl=new JPanel();
			    	panelControl.setOpaque(false);
			    	String etiqueta=sl.getLabel();
			    	
				    ChangeListener cListener=new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
							sl.setState(sl.getValue());
						}
					};
			    	sl.addChangeListener(cListener);
			    	sl.setValue(sl.getState());
					sl.setMinimum(sl.getMin());
					sl.setMaximum(sl.getMax());
					sl.setSnapToTicks(true);
					sl.setMinorTickSpacing(sl.getStep());
			    	
			    	
			    	int anchoB=panelBloque.getAncho();
			    	sl.setPreferredSize(new Dimension(anchoB/3, 20));
			    	panelControl.add(new JLabel(etiqueta));
			    	panelControl.add(sl);
			    	sl.setOpaque(false);
			    	panelBloque.addSlider(panelControl);
			    }
			    
			    //Tercero coloca los desplegables
			    for(Dropdown dpd: dropdowns) {
			    	JPanel panelControl=new JPanel();
			    	panelControl.setOpaque(false);
			    	String etiqueta=dpd.getLabel();
			    	
			    	ItemListener itListener=new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							//Con el indice sabemos que opcion es la seleccionada
							int indexSelected=dpd.getSelectedIndex();
							Option optionSelected=dpd.getoptions().get(indexSelected);
							String newState= optionSelected.getValue();
							dpd.setState(newState);
						}
					};
					dpd.addItemListener(itListener);
					ArrayList<String> labels = new ArrayList<String>();
					for (Option option : dpd.getoptions()) {
						labels.add(option.getLabel());
					}
					
					DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(labels.toArray());
					dpd.setModel(modeloComboBox);
					
					int index = 0;
					for (int i = 0; i < dpd.getoptions().size(); i++) {
						String value = dpd.getoptions().get(i).getValue();
						if (value.equals(dpd.getState())) {
							index = i;
						}
					}
					dpd.setSelectedIndex(index);
					
					panelControl.add(new JLabel(etiqueta));
			    	panelControl.add(dpd);
			    	panelBloque.addDropdown(panelControl);
			    }
			    
			    //Cuarto y ultimo coloca los sensores
			    for(Sensor sn:sensors) {
			    	JPanel panelControl=new JPanel();
			    	panelControl.setOpaque(false);
			    	String etiqueta=sn.getLabel();
			    	
			    	panelControl.add(new JLabel(etiqueta));
			    	panelControl.add(sn);
			    	panelBloque.addSensor(panelControl);
					
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
