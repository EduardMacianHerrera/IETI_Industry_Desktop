public class Slider {
    int id;
    double state;
    double min;
    double max;
    double step;
    String label;

    public Slider(int id, String label, double state, double min, double max, double step) {
        this.id = id;
        this.label = label;
        this.state = state;
        this.min = min;
        this.max = max;
        this.step = step;
    }

    @Override
    public String toString() {
        return "Slider:[label="+ label + ",id=" + id + ",state=" + state + ",min=" + min + ",step=" + step + "];";
    }

    public double getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getState() {
        return state;
    }

    public void setState(double state) {
        this.state = state;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    
}
