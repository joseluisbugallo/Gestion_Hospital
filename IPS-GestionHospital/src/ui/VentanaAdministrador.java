package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import ui.admin.VentanaConsultarCitas;
import ui.admin.VentanaFijarCita;
import ui.jornada.VentanaJornadaLaboral;
import ui.medico.VentanaCitasMedico;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class VentanaAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton btnFijarCita;
	private JButton btnConsultarCitas;
	private JButton btnJornadaLaboral;


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
		contentPane.add(getBtnFijarCita());
		contentPane.add(getBtnConsultarCitas());
		contentPane.add(getBtnJornadaLaboral());
	}

	private JButton getBtnFijarCita() {
		if (btnFijarCita == null) {
			btnFijarCita = new JButton("Fijar cita");
			btnFijarCita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaFijarCita vFC = new VentanaFijarCita();
					vFC.setLocationRelativeTo(null);
					vFC.setTitle("Listado completo de Citas");
					vFC.setVisible(true);
				}
			});
		}
		return btnFijarCita;
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
	private JButton getBtnJornadaLaboral() {
		if (btnJornadaLaboral == null) {
			btnJornadaLaboral = new JButton("Jornada Laboral");
			btnJornadaLaboral.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaJornadaLaboral jornada = new VentanaJornadaLaboral();
					jornada.setLocationRelativeTo(null);
					jornada.setVisible(true);
				}
			});
		}
		return btnJornadaLaboral;
	}
	
	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}
}
