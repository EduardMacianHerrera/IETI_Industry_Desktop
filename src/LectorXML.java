import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LectorXML {
	
	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		File file = new File("conf/config.xml");
		cargarConfig(file, modelo);
		
		ArrayList<Control> controles = modelo.getControles();
		
		for (Control control : controles) {
			System.out.println(control.getTipoControl());
			System.out.println(control.getLabel());
		}
	
		
	}

	public static void cargarConfig(File file, Modelo modelo) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
	        doc.getDocumentElement().normalize();
	        
	        NodeList listaBloques = doc.getElementsByTagName("controls");
	        
	        for (int i = 0; i < listaBloques.getLength(); i++) {
				Node nodeBloque = listaBloques.item(i);
				NodeList listaControles = nodeBloque.getChildNodes();
				for (int j = 0; j < listaControles.getLength(); j++) {
					Node control = listaControles.item(j);
					try {
						if (control.getNodeType() == Node.ELEMENT_NODE) {
							if (control.getNodeName().equals("dropdown")) {
								crearDropDown(control, modelo);
							} else {
								addControlToModel(control, modelo);
							}
						}
					} catch (Exception e) {
						System.out.println("El elemento "+ control.getNodeName()+" no tiene el formato correcto");
					}
					
				}
			}
	        
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static void addControlToModel(Node control, Modelo modelo) {
		String tipoControl = control.getNodeName();
		String labelControl = control.getTextContent();
		Element elmControl = (Element) control;
		String id = elmControl.getAttribute("id");
		Control controlTemp;
		try {
			controlTemp = crearControl(id, labelControl, tipoControl, elmControl);
			modelo.getControles().add(controlTemp);
		} catch (Exception e) {
			System.out.println("Error de formato en un control "+control.getNodeName());
		}
		
		
		
	}
	
	public static Control crearControl(String id, String labelControl, String tipoControl, Element elmControl) throws Exception {
		if (id.equals("") || labelControl.equals("") || tipoControl.equals("") || elmControl.equals("")) {
			throw new Exception();
		}
		Control controlTemp = new Control();
		controlTemp.setId(id);
		controlTemp.setLabel(labelControl);
		controlTemp.setTipoControl(tipoControl);
		switch (tipoControl) {
		case "switch":
			JToggleButton interruptor = new JToggleButton();
			if (elmControl.getAttribute("default").equals("on")) {
				interruptor.setSelected(true);
			} else {
				interruptor.setSelected(false);
			}
			
			controlTemp.setControl(interruptor);
			//modelo.getControles().add(controlTemp);
			break;

		case "slider":
			JSlider slider = new JSlider();
			slider.setMinimum(Integer.valueOf(elmControl.getAttribute("min"))* 10);
			slider.setMaximum(Integer.valueOf(elmControl.getAttribute("max"))* 10);
			slider.setValue((int)(Double.valueOf(elmControl.getAttribute("default"))* 10));
			// Mirar lo de los tick y multiplicar por 10
			
			controlTemp.setControl(slider);
			
			break;
			
			
		case "sensor":
			JTextField textField = new JTextField();
			
			controlTemp.setThresholdLow(Integer.valueOf(elmControl.getAttribute("thresholdlow")));
			controlTemp.setThresholdHigh(Integer.valueOf(elmControl.getAttribute("thresholdhigh")));
			controlTemp.setControl(textField);
			break;
			
		default:
			break;
		}
		
		return controlTemp;
	}
	
	public static void crearDropDown(Node control, Modelo modelo) {
		NodeList listaOpciones = control.getChildNodes();
		Element elmDropdown = (Element) control;
		JComboBox comboBox = new JComboBox();
		ArrayList<String> labelsComboBox = new ArrayList<String>();
		ArrayList<String> valoresComboBox = new ArrayList<String>();
		
		for (int k = 0; k < listaOpciones.getLength(); k++) {
			Node opcion = listaOpciones.item(k);
			if (opcion.getNodeType() == Node.ELEMENT_NODE) {
				Element elmOpcion = (Element) opcion;
				labelsComboBox.add(opcion.getTextContent());
				valoresComboBox.add(elmOpcion.getAttribute("value"));
			}
		}
		
		DefaultComboBoxModel modeloComboBox = new DefaultComboBoxModel(labelsComboBox.toArray());
		comboBox.setModel(modeloComboBox);
		comboBox.setSelectedItem(labelsComboBox.get(valoresComboBox.indexOf(elmDropdown.getAttribute("default")))); // Coge el atributo default, lo busca en la lista de valores,
																													//  saca el indice y selecciona la label por defecto
		Control controlTemp = new Control();
		controlTemp.setId(elmDropdown.getAttribute("id"));
		controlTemp.setControl(comboBox);
		controlTemp.setLabelsComboBox(labelsComboBox);
		controlTemp.setValoresComboBox(valoresComboBox);
		controlTemp.setTipoControl("dropdown");
		
		modelo.getControles().add(controlTemp);
	}
	
}
