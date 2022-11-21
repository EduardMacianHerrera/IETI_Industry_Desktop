import javax.swing.JSlider;

public class Slider extends JSlider {
    int id;
    int state;
    int min;
    int max;
    int step;
    String label;

    public Slider(int id, String label, int state, int min, int max, int step) {
    	super();
        this.id = id;
        this.label = label;
        this.state = state;
        this.min = min;
        this.max = max;
        this.step = step;
    }

    @Override
    public String toString() {
        return "Slider:[label="+ label + ",id=" + id + ",state=" + state + ",min=" + min + ",max=" + max + ",step=" + step + "];";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

    
    
}
