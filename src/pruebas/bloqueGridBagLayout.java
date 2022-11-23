package pruebas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class bloqueGridBagLayout extends JPanel {
	private JScrollPane scrollSwitches;
	private JPanel panelSwitches;
	private JScrollPane scrollSliders;
	private JPanel panelSliders;
	private JScrollPane scrollDropdowns;
	private JPanel panelDropdowns;
	private JScrollPane scrollSensors;
	private JPanel panelSensors;
	
	private boolean scrollHabilitado=true;

	/**
	 * Create the panel.
	 */
	public bloqueGridBagLayout() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNombreBloque = new JPanel();
		add(panelNombreBloque, BorderLayout.NORTH);
		panelNombreBloque.setLayout(new BoxLayout(panelNombreBloque, BoxLayout.Y_AXIS));
		
		JLabel nombreBloque = new JLabel("NOMBRE BLOQUE");
		nombreBloque.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nombreBloque.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelNombreBloque.add(nombreBloque);
		
		JButton habilitarBarrasScroll = new JButton("Habilitar Scroll");
		habilitarBarrasScroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(scrollHabilitado) {
					scrollSwitches.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
					scrollSwitches.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					
					scrollSliders.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
					scrollSliders.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					
					scrollDropdowns.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
					scrollDropdowns.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					
					scrollSensors.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
					scrollSensors.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					
					scrollHabilitado=false;
				}else {
					scrollSwitches.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					scrollSwitches.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					
					scrollSliders.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					scrollSliders.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					
					scrollDropdowns.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					scrollDropdowns.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					
					scrollSensors.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					scrollSensors.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					scrollHabilitado=true;
				}
			}
		});
		habilitarBarrasScroll.setFont(new Font("Tahoma", Font.PLAIN, 8));
		habilitarBarrasScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelNombreBloque.add(habilitarBarrasScroll);
		
		JPanel panelControles = new JPanel();
		add(panelControles, BorderLayout.CENTER);
		GridBagLayout gbl_panelControles = new GridBagLayout();
		gbl_panelControles.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelControles.rowHeights = new int[]{0, 0, 0};
		gbl_panelControles.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelControles.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panelControles.setLayout(gbl_panelControles);
		
		scrollSwitches = new JScrollPane();
		GridBagConstraints gbc_scrollSwitches = new GridBagConstraints();
		gbc_scrollSwitches.gridwidth = 2;
		gbc_scrollSwitches.insets = new Insets(0, 0, 5, 5);
		gbc_scrollSwitches.fill = GridBagConstraints.BOTH;
		gbc_scrollSwitches.gridx = 0;
		gbc_scrollSwitches.gridy = 0;
		panelControles.add(scrollSwitches, gbc_scrollSwitches);
		
		panelSwitches = new JPanel();
		scrollSwitches.setViewportView(panelSwitches);
		
		scrollSliders = new JScrollPane();
		GridBagConstraints gbc_scrollSliders = new GridBagConstraints();
		gbc_scrollSliders.gridwidth = 4;
		gbc_scrollSliders.insets = new Insets(0, 0, 5, 0);
		gbc_scrollSliders.fill = GridBagConstraints.BOTH;
		gbc_scrollSliders.gridx = 2;
		gbc_scrollSliders.gridy = 0;
		panelControles.add(scrollSliders, gbc_scrollSliders);
		
		panelSliders = new JPanel();
		scrollSliders.setViewportView(panelSliders);
		
		scrollDropdowns = new JScrollPane();
		GridBagConstraints gbc_scrollDropdowns = new GridBagConstraints();
		gbc_scrollDropdowns.gridwidth = 3;
		gbc_scrollDropdowns.insets = new Insets(0, 0, 0, 5);
		gbc_scrollDropdowns.fill = GridBagConstraints.BOTH;
		gbc_scrollDropdowns.gridx = 0;
		gbc_scrollDropdowns.gridy = 1;
		panelControles.add(scrollDropdowns, gbc_scrollDropdowns);
		
		panelDropdowns = new JPanel();
		scrollDropdowns.setViewportView(panelDropdowns);
		
		scrollSensors = new JScrollPane();
		GridBagConstraints gbc_scrollSensors = new GridBagConstraints();
		gbc_scrollSensors.gridwidth = 3;
		gbc_scrollSensors.fill = GridBagConstraints.BOTH;
		gbc_scrollSensors.gridx = 3;
		gbc_scrollSensors.gridy = 1;
		panelControles.add(scrollSensors, gbc_scrollSensors);
		
		panelSensors = new JPanel();
		scrollSensors.setViewportView(panelSensors);

	}
	
	public void addSwitch(JPanel control) {
		panelSwitches.add(control);
	}
	
	public void addSlider(JPanel control) {
		panelSliders.add(control);
	}
	
	public void addDropdown(JPanel control) {
		panelDropdowns.add(control);
	}
	
	public void addSensor(JPanel control) {
		panelSensors.add(control);
	}

}
