import java.util.ArrayList;

import javax.swing.JComboBox;

public class Dropdown extends JComboBox {
    String label;
    int id;
    String state;
    ArrayList<Option> options = new ArrayList<Option>();

    public Dropdown(String label, int id, String state) {
    	super();
        this.label = label;
        this.id = id;
        this.state = state;
    }
    
    public Dropdown(String label, int id, String state, ArrayList<Option> options) {
    	super();
        this.label = label;
        this.id = id;
        this.state = state;
        for (Option option : options) {
			addOption(option);
		}
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        String output = "Dropdown:[label=" + label + ",id=" + id + ",state=" + state + ",options=[";
        for (Option o : options) {
            output+= "label="+ o.getLabel() + "&value=" + o.getValue() + "$";
        }
        output += "],];";
        return output;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(String state) {
		this.state = state;
	}

    public int getId() {
        return id;
    }

    public String getState() {
		return state;
	}
    public ArrayList<Option> getoptions() {
        return options;
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public void removeOption(Option option) {
        options.remove(option);
    }

    
    
}
