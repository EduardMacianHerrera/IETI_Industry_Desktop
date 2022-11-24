package pruebas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;

public class prueba1 extends JFrame {

	private JPanel panelBloque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prueba1 frame = new prueba1();
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
	public prueba1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelBloque = new JPanel();
		panelBloque.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelBloque);
		GridBagLayout gbl_panelBloque = new GridBagLayout();
		gbl_panelBloque.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelBloque.rowHeights = new int[]{0, 0, 0};
		gbl_panelBloque.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelBloque.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panelBloque.setLayout(gbl_panelBloque);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelBloque.add(scrollPane, gbc_scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel.add(btnNewButton_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 0;
		panelBloque.add(scrollPane_1, gbc_scrollPane_1);
		
		JPanel panel_1 = new JPanel();
		scrollPane_1.setViewportView(panel_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 3;
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 1;
		panelBloque.add(scrollPane_2, gbc_scrollPane_2);
		
		JPanel panel_2 = new JPanel();
		scrollPane_2.setViewportView(panel_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 3;
		gbc_scrollPane_3.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 3;
		gbc_scrollPane_3.gridy = 1;
		panelBloque.add(scrollPane_3, gbc_scrollPane_3);
		
		JPanel panel_3 = new JPanel();
		scrollPane_3.setViewportView(panel_3);
	}

}
