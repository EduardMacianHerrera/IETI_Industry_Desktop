import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Modelo {

	private ArrayList<JToggleButton> switches = new ArrayList<JToggleButton>();
	private ArrayList<JSlider> sliders = new ArrayList<JSlider>();
	private ArrayList<JComboBox> dropdowns = new ArrayList<JComboBox>();
	private ArrayList<JTextField> sensors = new ArrayList<JTextField>(); // Mirar que hacen los sensores
	
	private ArrayList<Control> controles = new ArrayList<Control>();
	
	
	public Modelo() {
		super();
	}
	public Modelo(ArrayList<JToggleButton> switches, ArrayList<JSlider> sliders, ArrayList<JComboBox> dropdowns,
			ArrayList<JTextField> sensors) {
		super();
		this.switches = switches;
		this.sliders = sliders;
		this.dropdowns = dropdowns;
		this.sensors = sensors;
	}
	public ArrayList<JToggleButton> getSwitches() {
		return switches;
	}
	public void setSwitches(ArrayList<JToggleButton> switches) {
		this.switches = switches;
	}
	public ArrayList<JSlider> getSliders() {
		return sliders;
	}
	public void setSliders(ArrayList<JSlider> sliders) {
		this.sliders = sliders;
	}
	public ArrayList<JComboBox> getDropdowns() {
		return dropdowns;
	}
	public void setDropdowns(ArrayList<JComboBox> dropdowns) {
		this.dropdowns = dropdowns;
	}
	public ArrayList<JTextField> getSensors() {
		return sensors;
	}
	public void setSensors(ArrayList<JTextField> sensors) {
		this.sensors = sensors;
	}
	public ArrayList<Control> getControles() {
		return controles;
	}
	public void setControles(ArrayList<Control> controles) {
		this.controles = controles;
	}
	
	
	
}
