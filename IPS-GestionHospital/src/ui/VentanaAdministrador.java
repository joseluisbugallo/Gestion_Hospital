package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import ui.admin.VentanaConsultarCitas;
import ui.medico.VentanaCitasMedico;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class VentanaAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton btnMenDeCitas;
	private JButton btnConsultarCitas;


	/**
	 * Constructor de la ventana.
	 */
	public VentanaAdministrador() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100,100,800,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));
		contentPane.add(getBtnMenDeCitas());
		contentPane.add(getBtnConsultarCitas());
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
	private JButton getBtnConsultarCitas() {
		if (btnConsultarCitas == null) {
			btnConsultarCitas = new JButton("Consultar Citas");
			btnConsultarCitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaConsultarCitas citas = new VentanaConsultarCitas();
					citas.setLocationRelativeTo(null);
					citas.setTitle("Listado completo de Citas");
					citas.setVisible(true);
				}
			});
		}
		return btnConsultarCitas;
	}
}
