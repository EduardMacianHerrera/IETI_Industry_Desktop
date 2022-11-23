package pruebas;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainPrueba {
	private static JPanel panelFlow;

	public static void main(String[] args) {
		framePrueba marco=new framePrueba();
		panelFlow=new JPanel();
		
		marco.add(panelFlow);
		panelFlow.setLayout(new FlowLayout());
		for(int i=0;i<3;i++) {
			bloqueGridBagLayout bloquePrueba= new bloqueGridBagLayout(600,500);
//			bloquePrueba.setPreferredSize(new Dimension(400,300));
//			for(int j=0; j<15;j++) {
//				JPanel simulacionSwitch=new JPanel();
//				simulacionSwitch.add(new JLabel("Esto deberia ser un switch"));
//				bloquePrueba.addSwitch(simulacionSwitch);
//				
//				JPanel simulacionSlider=new JPanel();
//				simulacionSlider.add(new JLabel("Esto deberia ser un slider"));
//				bloquePrueba.addSlider(simulacionSlider);
//				bloquePrueba.addSlider(simulacionSlider);
//				
//				JPanel simulacionDropdown=new JPanel();
//				simulacionDropdown.add(new JLabel("Esto deberia ser un Dropdown"));
//				bloquePrueba.addDropdown(simulacionDropdown);
//				bloquePrueba.addDropdown(simulacionDropdown);
//				
//				JPanel simulacionSensor=new JPanel();
//				simulacionSensor.add(new JLabel("Esto deberia ser un Sensor"));
//				bloquePrueba.addSensor(simulacionSensor);
//				bloquePrueba.addSensor(simulacionSensor);
//			}
			panelFlow.add(bloquePrueba);
		}
		panelFlow.add(new JButton("Boton Texto"));
	}
	
}

class framePrueba extends JFrame {
	public framePrueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Prueba");
		setBounds(600,350,600,300);
	}
}


