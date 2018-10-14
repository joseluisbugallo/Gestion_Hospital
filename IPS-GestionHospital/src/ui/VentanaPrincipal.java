package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import alb.util.jdbc.Jdbc;
import business.dto.EmpleadoDto;
import persistence.DataEmpleado;
import ui.medico.VentanaMedico;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnAdministrador;
	private JButton btnMedico;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jdbc.createThreadConnection();
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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,800,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));
		contentPane.add(getBtnAdministrador());
		contentPane.add(getBtnMedico());
	}

	private JButton getBtnAdministrador() {
		if (btnAdministrador == null) {
			btnAdministrador = new JButton("Entrar como administrador");
			btnAdministrador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaAdministrador vadmin = new VentanaAdministrador();
					vadmin.setLocationRelativeTo(null);
					vadmin.setTitle("Ventana de administrador");
					vadmin.setVisible(true);

				}
			});
		}
		return btnAdministrador;
	}

	private JButton getBtnMedico() {
		if (btnMedico == null) {
			btnMedico = new JButton("Entrar como m\u00E9dico");
			btnMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String dni = JOptionPane.showInputDialog("Introduce el dni del médico:");
					DataEmpleado dtempleado = new DataEmpleado();
					EmpleadoDto medico = dtempleado.getEmpleadoPorDni(dni);
					if (medico != null)// hay que comprobar que el dni pertenece a un medico
					{
						VentanaMedico ventanaMedico = new VentanaMedico(medico);
						ventanaMedico.setLocationRelativeTo(null);
						ventanaMedico.setTitle("Medico: " + medico.nombre);
						ventanaMedico.setVisible(true);
					}
					else {
						mostrarMensaje("El dni introducido no pertenece a ningún medico registrado", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnMedico;
	}

	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}
}
