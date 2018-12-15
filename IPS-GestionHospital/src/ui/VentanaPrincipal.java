package ui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import alb.util.jdbc.Jdbc;
import business.LogController;
import business.PrincipalController;
import business.dto.CambioDto;
import business.dto.EmpleadoDto;
import ui.medico.VentanaMedico;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnAdministrador;
	private JButton btnMedico;
	private PrincipalController principal;

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
					CambioDto cambio = new CambioDto();
					cambio.cambio = "Se ha arrancado la aplicacion" ;
					cambio.fecha = new Date();					
					LogController lc = new LogController();
					lc.añadirCambio(cambio);
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
		principal = new PrincipalController();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));
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
					
					CambioDto cambio = new CambioDto();
					cambio.cambio = "Se ha entrado a la aplicacion como administrador";
					cambio.fecha = new Date();					
					LogController lc = new LogController();
					lc.añadirCambio(cambio);
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
					if (dni == null) {
						mostrarMensaje("Debe introducir un dni válido!", "DNI inválido", JOptionPane.ERROR_MESSAGE);
					} else {
						EmpleadoDto medico = principal.findEmpleado(dni);
						
						if (medico != null && medico.cargo.equals("Medico"))// hay que comprobar que el dni pertenece a un medico
						{
							VentanaMedico ventanaMedico = new VentanaMedico(medico);
							ventanaMedico.setLocationRelativeTo(null);
							ventanaMedico.setTitle("Medico: " + medico.nombre);
							ventanaMedico.setVisible(true);
							
							CambioDto cambio = new CambioDto();
							cambio.cambio = "Se ha entrado a la aplicacion como medico, con id: "+medico.id;
							cambio.fecha = new Date();					
							LogController lc = new LogController();
							lc.añadirCambio(cambio);
						} else {
							mostrarMensaje("El dni introducido no pertenece a ningún medico registrado", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
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
