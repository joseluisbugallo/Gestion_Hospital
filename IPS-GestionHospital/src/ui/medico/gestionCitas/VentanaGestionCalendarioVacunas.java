package ui.medico.gestionCitas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;

import business.PacientesController;
import business.VacunaController;
import business.dto.CitaDto;
import business.dto.DiagnosticoDto;
import business.dto.VacunaDto;
import ui.medico.VentanaGestionCita;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class VentanaGestionCalendarioVacunas extends JDialog {

	CitaDto cita;

	private final JPanel contentPanel = new JPanel();

	ArrayList<DiagnosticoDto> diagnosticos = new ArrayList<DiagnosticoDto>();
	private JLabel lblAadirRangoVacunacion;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JButton btnAadir;
	private JDateChooser dcInicio;
	private JDateChooser dcFin;

	private JCalendar calendar;
	private JLabel lblCalendarioDeVacunas;
	private JLabel lblVacuna;
	private JTextField txtVacuna;
	private JButton btnActualizar;
	private JLabel lblFechaDeVacunaciones;
	private JLabel lblLeyenda;
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public VentanaGestionCalendarioVacunas(CitaDto cita) {
		this.cita = cita;

		PacientesController pc = new PacientesController();

		setResizable(false);
		setTitle("Calendario de vacunas para el paciente: " + pc.findPacientesById(cita.idPaciente).nombre);
		setDefaultCloseOperation(0);

		setBounds(100, 100, 789, 574);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblAadirRangoVacunacion());
		contentPanel.add(getLblFechaInicio());
		contentPanel.add(getLblFechaFin());
		contentPanel.add(getBtnAadir());
		contentPanel.add(getDcInicio());
		contentPanel.add(getDcFin());
		contentPanel.add(getCalendar());
		contentPanel.add(getLblCalendarioDeVacunas());
		contentPanel.add(getLblVacuna());
		contentPanel.add(getTxtVacuna());
		contentPanel.add(getBtnActualizar());
		contentPanel.add(getLblFechaDeVacunaciones());
		contentPanel.add(getLblLeyenda());
		contentPanel.add(getTextField());

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Atras");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						salirSinGuardar();
					}
				});
				buttonPane.add(cancelButton);
			}
		}

		colorearCalendario();
	}

	private void colorearCalendario() {
		// TODO Auto-generated method stub
		VacunaController vc = new VacunaController();
		ArrayList<VacunaDto> vacunas = vc.getVacunasPaciente(this.cita.idPaciente);
		System.out.println("Coloreando" + cita.idPaciente);
		for (VacunaDto v : vacunas) {
			System.out.println("Coloreando");
			if (v.fechainicio.getYear()+1900 == getCalendar().getYearChooser().getYear()) {

				if (v.fechainicio.getMonth() == getCalendar().getMonthChooser().getMonth()) {
					System.out.println("Primer if");
					if (v.fechafin.getMonth() != getCalendar().getMonthChooser().getMonth()) {
						// colorear hasta final de mes
						for (int i = v.fechainicio.getDate(); i < 31; i++) {
							System.out.println("Coloreando");
							// getCalendar().getDayChooser().getComponent(i).setBackground(new
							// Color(87,166,57));
							getCalendar().getDayChooser().getDayPanel().getComponent(i - 1 + 10)
									.setBackground(new Color(87, 166, 57));
						}
					} else {
						// colorear de fecha inicio a fecha fin
						for (int i = v.fechainicio.getDate(); i <= v.fechafin.getDate(); i++) {
							// getCalendar().getDayChooser().getComponent(i).setBackground(new
							// Color(87,166,57));
							getCalendar().getDayChooser().getDayPanel().getComponent(i - 1 + 10)
									.setBackground(new Color(87, 166, 57));
							System.out.println("Coloreando: "
									+ getCalendar().getDayChooser().getDayPanel().getComponent(i - 1).getName());
						}
					}
				}
			}
		}
	}

	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}

	private void salirSinGuardar() {
		int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere salir?", "Salir sin guardar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (respuesta == JOptionPane.YES_OPTION) {
			VentanaGestionCita v = new VentanaGestionCita(cita);
			v.setVisible(true);
			this.dispose();
		}
	}

	private JLabel getLblAadirRangoVacunacion() {
		if (lblAadirRangoVacunacion == null) {
			lblAadirRangoVacunacion = new JLabel("A\u00F1adir rango vacunacion:");
			lblAadirRangoVacunacion.setBounds(12, 13, 201, 16);
		}
		return lblAadirRangoVacunacion;
	}

	private JLabel getLblFechaInicio() {
		if (lblFechaInicio == null) {
			lblFechaInicio = new JLabel("Fecha Inicio:");
			lblFechaInicio.setBounds(12, 42, 91, 16);
		}
		return lblFechaInicio;
	}

	private JLabel getLblFechaFin() {
		if (lblFechaFin == null) {
			lblFechaFin = new JLabel("Fecha Fin:");
			lblFechaFin.setBounds(12, 71, 91, 16);
		}
		return lblFechaFin;
	}

	private JButton getBtnAadir() {
		if (btnAadir == null) {
			btnAadir = new JButton("A\u00F1adir");
			btnAadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VacunaDto vacuna = new VacunaDto();
					VacunaController vc = new VacunaController();
					if (!(getTxtVacuna().getText().length() == 0) && getDcInicio().getDate() != null
							&& getDcFin().getDate() != null) {
						vacuna.idPaciente = cita.idPaciente;
						vacuna.fechafin = getDcFin().getDate();
						vacuna.fechainicio = getDcInicio().getDate();
						vacuna.vacuna = getTxtVacuna().getText();
						vc.addVacuna(vacuna);
						mostrarMensaje("Rango de fechas para vacuna añadido", "Vacuna añadida",
								JOptionPane.INFORMATION_MESSAGE);
						colorearCalendario();
					}

				}
			});
			btnAadir.setBounds(631, 36, 97, 59);
		}
		return btnAadir;
	}

	private JDateChooser getDcInicio() {
		if (dcInicio == null) {
			dcInicio = new JDateChooser();

			dcInicio.setBounds(100, 36, 201, 27);
			dcInicio.setDateFormatString("dd/MM/yy");
		}
		return dcInicio;
	}

	private JDateChooser getDcFin() {
		if (dcFin == null) {
			dcFin = new JDateChooser();

			dcFin.setBounds(100, 69, 201, 27);
			dcFin.setDateFormatString("dd/MM/yy");
		}
		return dcFin;
	}

	private JCalendar getCalendar() {
		if (calendar == null) {
			calendar = new JCalendar();
			calendar.setBounds(43, 138, 496, 353);

		}
		return calendar;
	}

	private JLabel getLblCalendarioDeVacunas() {
		if (lblCalendarioDeVacunas == null) {
			lblCalendarioDeVacunas = new JLabel("Calendario de vacunas del paciente");
			lblCalendarioDeVacunas.setHorizontalAlignment(SwingConstants.CENTER);
			lblCalendarioDeVacunas.setBounds(217, 109, 335, 16);
		}
		return lblCalendarioDeVacunas;
	}

	private JLabel getLblVacuna() {
		if (lblVacuna == null) {
			lblVacuna = new JLabel("Vacuna: ");
			lblVacuna.setBounds(328, 42, 56, 16);
		}
		return lblVacuna;
	}

	private JTextField getTxtVacuna() {
		if (txtVacuna == null) {
			txtVacuna = new JTextField();
			txtVacuna.setBounds(386, 39, 217, 22);
			txtVacuna.setColumns(10);
		}
		return txtVacuna;
	}

	private JButton getBtnActualizar() {
		if (btnActualizar == null) {
			btnActualizar = new JButton("actualizar");
			btnActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					colorearCalendario();
				}
			});
			btnActualizar.setBounds(548, 282, 97, 59);
		}
		return btnActualizar;
	}
	private JLabel getLblFechaDeVacunaciones() {
		if (lblFechaDeVacunaciones == null) {
			lblFechaDeVacunaciones = new JLabel("Fecha de vacunaciones");
			lblFechaDeVacunaciones.setBounds(632, 152, 139, 16);
		}
		return lblFechaDeVacunaciones;
	}
	private JLabel getLblLeyenda() {
		if (lblLeyenda == null) {
			lblLeyenda = new JLabel("Leyenda:");
			lblLeyenda.setBounds(589, 123, 56, 16);
		}
		return lblLeyenda;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setEnabled(false);
			textField.setEditable(false);
			textField.setBackground(Color.GREEN);
			textField.setBounds(599, 149, 20, 22);
			textField.setColumns(10);
		}
		return textField;
	}
}
