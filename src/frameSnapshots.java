import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class frameSnapshots extends JFrame {
	private JPanel contentPane;
	private JScrollPane scrollSnapshots;
	private JPanel panelSnapshots;
	private JLabel seleccionaSnapshot;
	private JList listSnapshots;
	private JButton botonSeleccionar;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				interfazIndustry_2 interfaz = new interfazIndustry_2(Main.modelo);
				interfaz.setVisible(true);
				interfaz.colocarElements(Main.modelo);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		setBounds(100, 100, 200, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		scrollSnapshots = new JScrollPane();
		panelSnapshots = new JPanel();
		panelSnapshots.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		scrollSnapshots.setViewportView(panelSnapshots);
		panelSnapshots.setLayout(new BoxLayout(panelSnapshots, BoxLayout.Y_AXIS));
		
		ArrayList<String> valuesArrayList = new ArrayList<String>();
		ArrayList<Integer> idValues = new ArrayList<Integer>();
		HashMap<Integer, String> snapshotsMap = Database.listSnapshots();
		for (Map.Entry<Integer, String> snapshots : snapshotsMap.entrySet()) {
			valuesArrayList.add(snapshots.getValue());
			idValues.add(snapshots.getKey());
		}
		listSnapshots = new JList();
		
		listSnapshots.setListData(valuesArrayList.toArray());
		
		panelSnapshots.add(listSnapshots);
		panelSnapshots.add(Box.createRigidArea(new Dimension(20, 20)));
		
		botonSeleccionar = new JButton("Seleccionar");
		botonSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=idValues.get(valuesArrayList.indexOf(listSnapshots.getSelectedValue()));
				System.out.println("Has seleccionado "+id);
				Database.loadSnapshot(String.valueOf(id));
				interfazIndustry_2 interfaz = new interfazIndustry_2(Main.modelo);
				interfaz.setVisible(true);
				interfaz.colocarElements(Main.modelo);
				dispose();
			}
		});
		
		botonSeleccionar.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelSnapshots.add(botonSeleccionar);
		contentPane.add(scrollSnapshots, BorderLayout.CENTER);

		
		seleccionaSnapshot = new JLabel("Selecciona un snapshot");
		seleccionaSnapshot.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(seleccionaSnapshot, BorderLayout.NORTH);
//		setBotonesSnapshots();
	}
	
//	public void setBotonesSnapshots() {
//		for(int i=0; i<30;i++) {
//			JButton botonSnapshot=new JButton("Snapshot "+i);
//			botonSnapshot.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					String snapshotSelected=botonSnapshot.getText();
//					System.out.println("Seleccionado el "+snapshotSelected);
//					dispose();
//				}
//			});
//			panelSnapshots.add(botonSnapshot);
//			panelSnapshots.add(Box.createRigidArea(new Dimension(5, 5)));
//		}
//		
//	}
	
}
