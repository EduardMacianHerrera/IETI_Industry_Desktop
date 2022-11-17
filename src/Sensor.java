public class Sensor {
    int id;
    String units;
    int thresholdHigh;
    int thresholdLow;
    String label;

    public Sensor(int id, String units, int thresholdHigh, int thresholdLow, String label) {
        this.id = id;
        this.units = units;
        this.thresholdHigh = thresholdHigh;
        this.thresholdLow = thresholdLow;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public int getThresholdHigh() {
        return thresholdHigh;
    }

    public void setThresholdHigh(int thresholdHigh) {
        this.thresholdHigh = thresholdHigh;
    }

    public int getThresholdLow() {
        return thresholdLow;
    }

    public void setThresholdLow(int thresholdLow) {
        this.thresholdLow = thresholdLow;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Sensor:[id=" + id + ",units=" + units + ",thresholdHigh=" + thresholdHigh + ",thresholdLow="
                + thresholdLow + ",label=" + label + "];";
    }



}
