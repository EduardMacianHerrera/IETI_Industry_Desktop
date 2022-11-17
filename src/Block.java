import java.util.ArrayList;

public class Block {
    String name;
    ArrayList<Switch> switches = new ArrayList<Switch>();
    ArrayList<Dropdown> dropdowns = new ArrayList<Dropdown>();
    ArrayList<Slider> sliders = new ArrayList<Slider>();
    ArrayList<Sensor> sensors = new ArrayList<Sensor>();

    public Block(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Switch> getSwitches() {
        return switches;
    }

    
    public ArrayList<Dropdown> getDropdowns() {
        return dropdowns;
    }

    public ArrayList<Slider> getSliders() {
        return sliders;
    }

    public ArrayList<Sensor> getSensors() {
        return sensors;
    }

    public void addSwitch(Switch switchButton) {
        switches.add(switchButton);
    }

    public void addDropdown(Dropdown dropdown) {
        dropdowns.add(dropdown);
    }

    public void addSlider(Slider slider) {
        sliders.add(slider);
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public void removeSwitch(Switch switchButton) {
        switches.remove(switchButton);
    }

    public void removeDropdown(Dropdown dropdown) {
        dropdowns.remove(dropdown);
    }

    public void removeSlider(Slider slider) {
        sliders.remove(slider);
    }

    public void removeSensor(Sensor sensor) {
        sensors.remove(sensor);
    }

    public String toString() {
        String output = "";
        for (Switch s : switches) {
            output+= s.toString();
        }
        for (Dropdown d : dropdowns) {
            output+= d.toString();
        }
        for (Slider s : sliders) {
            output += s.toString();
        }
        for (Sensor s: sensors) {
            output += s.toString();
        }
        return output;
    }
    
}
