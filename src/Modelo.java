import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Modelo {

	private ArrayList<Control> controles = new ArrayList<Control>();
	
	
	public Modelo() {
		super();
	}
	
	public ArrayList<Control> getControles() {
		return controles;
	}
	public void setControles(ArrayList<Control> controles) {
		this.controles = controles;
	}
	
	
	
}
