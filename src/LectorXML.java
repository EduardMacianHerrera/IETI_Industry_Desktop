import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JSlider;
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
		String config = "conf/config.xml";
		cargarConfig(config, modelo);
		
		ArrayList<Control> controles = modelo.getControles();
		
		for (Control control : controles) {
			System.out.println(control.getTipoControl());
			System.out.println(control.getLabel());
		}
	
		
	}

	public static void cargarConfig(String config, Modelo modelo) {
		File file = new File(config);
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
					if (control.getNodeType() == Node.ELEMENT_NODE) {
						if (control.getNodeName().equals("dropdown")) {
							NodeList listaOpciones = control.getChildNodes();
							for (int k = 0; k < listaOpciones.getLength(); k++) {
								Node opcion = listaOpciones.item(k);
								if (opcion.getNodeType() == Node.ELEMENT_NODE) {
									String labelOpcion = opcion.getTextContent();
								}
							}
						} else {
							addControlToModel(control, modelo);
						}
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
		Control controlTemp = new Control();
		
		switch (tipoControl) {
		case "switch":
			JToggleButton interruptor = new JToggleButton();
			if (elmControl.getAttribute("default").equals("on")) {
				interruptor.setSelected(true);
			} else {
				interruptor.setSelected(false);
			}
			controlTemp.setId(id);
			controlTemp.setLabel(labelControl);
			controlTemp.setTipoControl(tipoControl);
			controlTemp.setControl(interruptor);
			break;

		case "slider":
			JSlider slider = new JSlider();
			slider.setMinimum(Integer.valueOf(elmControl.getAttribute("min"))* 10);
			slider.setMaximum(Integer.valueOf(elmControl.getAttribute("max"))* 10);
			slider.setValue((int)(Double.valueOf(elmControl.getAttribute("default"))* 10));
			// Mirar lo de los tick y multiplicar por 10
			break;
			
		default:
			break;
		}
		
		modelo.getControles().add(controlTemp);
		
	}
	
}
