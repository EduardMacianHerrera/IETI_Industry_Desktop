import java.util.ArrayList;

public class Dropdown {
    String label;
    int id;
    int state;
    ArrayList<Option> options = new ArrayList<Option>();

    public Dropdown(String label, int id, int state) {
        this.label = label;
        this.id = id;
        this.state = state;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        String output = "Dropdown:[label=" + label + ",id=" + id + ",state=" + state + ",options=[";
        for (Option o : options) {
            output+= "label="+ o.getLabel() + ",value=" + o.getValue() + ";";
        }
        output += "];";
        return output;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getState() {
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
