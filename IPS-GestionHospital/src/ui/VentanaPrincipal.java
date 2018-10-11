package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton btnMenDeCitas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal vP = new VentanaPrincipal();
					vP.setLocationRelativeTo(null);
					vP.setTitle("Menu Principal...");
					vP.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor de la ventana.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));
		contentPane.add(getBtnMenDeCitas());
	}

	private JButton getBtnMenDeCitas() {
		if (btnMenDeCitas == null) {
			btnMenDeCitas = new JButton("Men\u00FA de citas");
			btnMenDeCitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Aquí se llamaría a la pantalla de citas
					mostrarMensaje("La ventana aun no ha sido implementada", "SECCIÓN NO IMPLEMENTADA", JOptionPane.WARNING_MESSAGE);
				}
			});
		}
		return btnMenDeCitas;
	}
	
	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}
}
