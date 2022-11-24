import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class frameSnapshots extends JFrame {
	private JPanel contentPane;
	private JScrollPane scrollSnapshots;
	private JPanel panelSnapshots;
	private JLabel seleccionaSnapshot;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameSnapshots frame = new frameSnapshots();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frameSnapshots() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		scrollSnapshots = new JScrollPane();
		panelSnapshots = new JPanel();
		panelSnapshots.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		scrollSnapshots.setViewportView(panelSnapshots);
		panelSnapshots.setLayout(new BoxLayout(panelSnapshots, BoxLayout.Y_AXIS));
		contentPane.add(scrollSnapshots, BorderLayout.CENTER);
		
		seleccionaSnapshot = new JLabel("Selecciona un snapshot");
		contentPane.add(seleccionaSnapshot, BorderLayout.NORTH);
		setBotonesSnapshots();
	}
	
	public void setBotonesSnapshots() {
		for(int i=0; i<30;i++) {
			JButton botonSnapshot=new JButton("Snapshot "+i);
			botonSnapshot.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Seleccionado el boton "+botonSnapshot.getText());
				}
			});
			panelSnapshots.add(botonSnapshot);
			panelSnapshots.add(Box.createRigidArea(new Dimension(5, 5)));
		}
	}
	
}
