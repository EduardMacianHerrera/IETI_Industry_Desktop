import javax.swing.JToggleButton;

public class Switch extends JToggleButton{
    String state;
    int id;
    String label;
    
    public Switch(String state, int id, String label) {
    	super();
        this.state = state;
        this.id = id;
        this.label = label;
    }

    @Override
    public String toString() {
        return "Switch:[state=" + state + ",id=" + id + "];";
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    
}
