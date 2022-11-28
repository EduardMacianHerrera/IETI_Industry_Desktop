package pruebas;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pedirDimensiones extends JFrame {

	private JPanel contentPane;
	
	private static int anchoEntrado;
	private static int altoEntrado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pedirDimensiones frame = new pedirDimensiones();
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
	public pedirDimensiones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea entraAlto = new JTextArea();
		entraAlto.setBounds(40, 20, 180, 30);
		contentPane.add(entraAlto);
		
		JTextArea entraAncho = new JTextArea();
		entraAncho.setBounds(40, 80, 180, 30);
		contentPane.add(entraAncho);
		
		JLabel alto = new JLabel("Altura:");
		alto.setBounds(40, 0, 180, 30);
		contentPane.add(alto);
		
		JLabel ancho = new JLabel("Ancho:");
		ancho.setBounds(40, 60, 180, 30);
		contentPane.add(ancho);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contenidoAncho=ancho.getText();
				String contenidoAlto=alto.getText();
				try {
					anchoEntrado=Integer.parseInt(contenidoAncho);
					altoEntrado=Integer.parseInt(contenidoAlto);
				}catch(NumberFormatException e1) {
					mostrarErrorInt();
				}
			}
		});
		botonAceptar.setBounds(90, 130, 100, 30);
		contentPane.add(botonAceptar);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botonCancelar.setBounds(90, 170, 100, 30);
		contentPane.add(botonCancelar);
	}

	public static int getAnchoEntrado() {
		return anchoEntrado;
	}

	public static void setAnchoEntrado(int anchoEntrado) {
		pedirDimensiones.anchoEntrado = anchoEntrado;
	}

	public static int getAltoEntrado() {
		return altoEntrado;
	}

	public static void setAltoEntrado(int altoEntrado) {
		pedirDimensiones.altoEntrado = altoEntrado;
	}
	
	public void mostrarErrorInt() {
		JOptionPane.showMessageDialog(this, "debes entrar dos numeros enteros", "Entra Integers", 0);
	}
}
