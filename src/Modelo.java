import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Modelo {

	private ArrayList<Block> blocks = new ArrayList<Block>();

	public Modelo() {
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public void addBlock(Block block) {
		blocks.add(block);
	}

	public String toString() {
		String output = "";
		for (Block b : blocks) {
			output += b.getName() + ":[";
			for (Switch s : b.getSwitches()) {
				output += s.toString();
			}
			for (Slider s : b.getSliders()) {
				output += s.toString();
			}
			for (Dropdown d : b.getDropdowns()) {
				output += d.toString();
			}
			for (Sensor s : b.getSensors()) {
				output += s.toString();
			}
			output+="];";
		}
		return output;
	}
}
