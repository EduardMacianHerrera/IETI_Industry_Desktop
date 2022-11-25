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
import javax.swing.DefaultListCellRenderer;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.event.WindowStateListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class frameSnapshots extends JFrame {
	private JPanel contentPane;
	private JScrollPane scrollSnapshots;
	private JPanel panelSnapshots;
	private JLabel seleccionaSnapshot;
	private JList listSnapshots;
	private JButton botonSeleccionar;
	
	int wFrame;
	int hFrame;

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
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		scrollSnapshots = new JScrollPane();
		panelSnapshots = new JPanel();
		panelSnapshots.setBackground(new Color(255, 255, 255));
		panelSnapshots.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		scrollSnapshots.setViewportView(panelSnapshots);
		panelSnapshots.setLayout(new BoxLayout(panelSnapshots, BoxLayout.Y_AXIS));
		
		wFrame=0;
		
		ArrayList<String> valuesArrayList = new ArrayList<String>();
		ArrayList<Integer> idValues = new ArrayList<Integer>();
		HashMap<Integer, String> snapshotsMap = Database.listSnapshots();
		int numSnaps=0;
		for (Map.Entry<Integer, String> snapshots : snapshotsMap.entrySet()) {
			valuesArrayList.add(snapshots.getValue());
			idValues.add(snapshots.getKey());
			if(snapshots.getValue().length()>wFrame) {
				wFrame=snapshots.getValue().length();
			}
			numSnaps++;
		}
		
		hFrame=(numSnaps *40)+120;
	
		setBounds(100, 100, wFrame*10, hFrame);
	
		listSnapshots = new JList();
		listSnapshots.setListData(valuesArrayList.toArray());
		listSnapshots.setFixedCellHeight(40);
		listSnapshots.setFixedCellWidth(this.getWidth()-40);
	
		panelSnapshots.add(listSnapshots);
		contentPane.add(scrollSnapshots, BorderLayout.CENTER);

		addComponentListener(new ComponentAdapter() {
		public void componentResized(ComponentEvent e) {
			listSnapshots.setFixedCellWidth(getWidth()-40);
		}
		});
		
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) listSnapshots.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		seleccionaSnapshot = new JLabel("Selecciona un snapshot");
		seleccionaSnapshot.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(seleccionaSnapshot, BorderLayout.NORTH);
		
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
		botonSeleccionar.setAlignmentX(0.5f);
		contentPane.add(botonSeleccionar, BorderLayout.SOUTH);
		
		if(valuesArrayList.size()==0) {
			JOptionPane.showMessageDialog(this, "no se ha encontrado ningun snapshot", "No Snapshot", 0);
			this.dispose();
		}
		
//		setBotonesSnapshots();
	}
}
