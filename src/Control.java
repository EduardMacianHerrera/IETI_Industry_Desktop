
public class Control {

	private String id;
	private String label;
	private String tipoControl;
	private Object control;

	public Control() {
		super();
	}

	public Control(String id, String label, String tipoControl, Object control) {
		super();
		this.id = id;
		this.label = label;
		this.tipoControl = tipoControl;
		this.control = control;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getControl() {
		return control;
	}

	public void setControl(Object control) {
		this.control = control;
	}

	public String getTipoControl() {
		return tipoControl;
	}

	public void setTipoControl(String tipoControl) {
		this.tipoControl = tipoControl;
	}
	
}
