import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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
import org.xml.sax.SAXParseException;

public class LectorXML {

	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		File file = new File("conf/config.xml");
		try {
			loadConfig(file, modelo);
			System.out.println(modelo.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
	}

	public static void loadConfig(File file, Modelo modelo) throws SyntaxException, Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			// Read XML
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			// Get all control blocks
			NodeList listaBloques = doc.getElementsByTagName("controls");

			for (int i = 0; i < listaBloques.getLength(); i++) {
				Node nodeBlock = listaBloques.item(i);
				if (nodeBlock.getNodeType() == Node.ELEMENT_NODE) {
					Element elmBlock = (Element) nodeBlock;
					Block block = new Block(elmBlock.getAttribute("name"));
					if (block.getName().equals("")) {
						throw new SyntaxException("Syntax error");
					}
					NodeList controlList = elmBlock.getChildNodes();
					for (int j = 0; j < controlList.getLength(); j++) {
						Node control = controlList.item(j);
						if (control.getNodeType() == Node.ELEMENT_NODE) {
							Element elmControl = (Element) control;
							switch (elmControl.getTagName()) {
								case "switch":
									loadSwitch(elmControl, block);
									break;
								case "slider":
									loadSlider(elmControl, block);
									break;
								case "dropdown":
									loadDropdown(elmControl, block);
									break;
								case "sensor":
									loadSensor(elmControl, block);
									break;
							}
						}
					}
					modelo.addBlock(block);
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SyntaxException e) {
			throw new Exception(e.getMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			throw new Exception("El archivo xml no tiene el formato correcto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void loadSwitch(Element elmSwitch, Block block) throws SyntaxException {
		String state = elmSwitch.getAttribute("default");
		String label = elmSwitch.getTextContent();
		int id;
		if (state.equals("") || label.equals("")) {
			throw new SyntaxException("Switch");
		}
		try {
			id = Integer.parseInt(elmSwitch.getAttribute("id"));
		} catch (Exception e) {
			throw new SyntaxException("Switch");
		}
		Switch s = new Switch(state, id, label);
		if (state.equals("on")) {
			s.setSelected(true);
		}
		block.addSwitch(s);
	}

	public static void loadSlider(Element elmSlider, Block block) throws SyntaxException {
		int id;
		int state;
		int min;
		int max;
		int step;
		String label = elmSlider.getTextContent();

		if (label.equals("")) {
			throw new SyntaxException("Slider");
		}

		try {
			id = Integer.parseInt(elmSlider.getAttribute("id"));
			state = Integer.parseInt(elmSlider.getAttribute("default"));
			min = Integer.parseInt(elmSlider.getAttribute("min"));
			max = Integer.parseInt(elmSlider.getAttribute("max"));
			step = Integer.parseInt(elmSlider.getAttribute("step"));
		} catch (Exception e) {
			throw new SyntaxException("Slider");
		}

		Slider s = new Slider(id, label, state, min, max, step);
		s.setValue(state);
		s.setMinimum(min);
		s.setMaximum(max);
		s.setSnapToTicks(true);
		s.setMinorTickSpacing(step);

		block.addSlider(s);
	}

	public static void loadDropdown(Element elmDropdown, Block block) throws SyntaxException {
		int id;
		String state;
		String label = elmDropdown.getAttribute("label");

		if (label.equals("")) {
			throw new SyntaxException("dropdown");
		}
		try {
			id = Integer.parseInt(elmDropdown.getAttribute("id"));
			state = elmDropdown.getAttribute("default");
		} catch (Exception e) {
			throw new SyntaxException("dropdown");
		}
		Dropdown d = new Dropdown(label, id, state);
		NodeList optionList = elmDropdown.getChildNodes();
		for (int i = 0; i < optionList.getLength(); i++) {
			Node optionNode = optionList.item(i);
			if (optionNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elmOption = (Element) optionNode;
				String value;
				try {
					value = elmOption.getAttribute("value");
				} catch (Exception e) {
					throw new SyntaxException("option");
				}
				String optionLabel = elmOption.getTextContent();
				if (optionLabel.equals("")) {
					throw new SyntaxException("option");
				}

				d.addOption(new Option(optionLabel, value));
			}
		}
		int index = 0;
		for (int i = 0; i < d.getoptions().size(); i++) {
			String value = d.getoptions().get(i).getValue();
			if (value.equals(state)) {
				index = i;
			}
		}
		
		d.setSelectedIndex(index);
		block.addDropdown(d);
	}

	public static void loadSensor(Element elmSensor, Block block) throws SyntaxException {
		int id;
		int thresholdHigh;
		int thresholdLow;
		String units = elmSensor.getAttribute("units");
		String label = elmSensor.getTextContent();

		try {
			id = Integer.parseInt(elmSensor.getAttribute("id"));
			thresholdLow = Integer.parseInt(elmSensor.getAttribute("thresholdlow"));
			thresholdHigh = Integer.parseInt(elmSensor.getAttribute("thresholdhigh"));
		} catch (Exception e) {
			throw new SyntaxException("sensor");
		}

		Sensor s = new Sensor(id, units, thresholdHigh, thresholdLow, label);
		block.addSensor(s);
	}

}
