package ui.medico.gestionCitas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;


import business.PacientesController;
import business.dto.CitaDto;
import business.dto.DiagnosticoDto;
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


	/**
	 * Create the dialog.
	 */
	public VentanaGestionCalendarioVacunas(CitaDto cita) {
		this.cita = cita;

		PacientesController pc= new PacientesController();
		
		setResizable(false);
		setTitle("Calendario de vacunas para el paciente: "+ pc.findPacientesById(cita.idPaciente).nombre);
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
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirmar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// Cambiar el objeto con los diagnosticos creados
						guardarYSalir();
						
					}

				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
	}

	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}
	
	private void salirSinGuardar() {
		int respuesta =JOptionPane.showConfirmDialog(null,"Seguro que quiere salir sin guardar los datos?",
				"Salir sin guardar",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		
		if(respuesta==JOptionPane.YES_OPTION) {
			VentanaGestionCita v = new VentanaGestionCita(cita);
			v.setVisible(true);
			this.dispose();
		}
	}
	
	private void guardarYSalir() {
		int respuesta =JOptionPane.showConfirmDialog(null,"Seguro que quiere guardar estos datos?",
				"Guardar datos",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
		
		if(respuesta==JOptionPane.YES_OPTION) {
			//cosas
			
			
			
			
			VentanaGestionCita v = new VentanaGestionCita(this.cita);
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
			calendar.setBounds(121, 138, 496, 353);
			
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
}
