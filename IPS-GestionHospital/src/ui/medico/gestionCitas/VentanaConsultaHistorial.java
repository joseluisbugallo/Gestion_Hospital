package ui.medico.gestionCitas;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import business.CitasController;
import business.DiagnosticoController;
import business.PacientesController;
import business.dto.CitaDto;
import business.dto.DiagnosticoDto;
import business.dto.HistorialDto;
import business.dto.PacienteDto;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import business.enums.TiposAntecedentes;
import ui.medico.VentanaGestionCita;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;

public class VentanaConsultaHistorial extends JDialog {

	private CitaDto cita;
	// VentanaGestionCita vGC;
	private JPanel pnlPrincipal;
	private JButton btnVolver;
	private JLabel lblHistorial;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextArea txtInfoHistorial;
	CitasController cC = new CitasController();
	PacientesController pC = new PacientesController();
	DiagnosticoController dc = new DiagnosticoController();

	HistorialDto historial;

	/**
	 * Create the dialog.
	 */
	public VentanaConsultaHistorial(CitaDto cita) { // VentanaGestionCita vGC) {
		this.cita = cita;
		this.historial = cC.obtenerHistorial(cita.idPaciente);
		if (this.historial == null)
			this.historial = new HistorialDto();
		setTitle("Antecedentes del paciente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 507);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPnlPrincipal());
		cargarDatosHistorial();
	}

	private void cargarDatosHistorial() {
		StringBuilder data = new StringBuilder();
		PacienteDto p = pC.findPacientesById(cita.idPaciente);
		data.append("Datos de: " + p.nombre + ".\n");
		data.append(cC.cargarDatosHistorial(cita.idPaciente) + "\n\n");
		if (cita.antecedentes != null || cita.antecedentes.isEmpty()) {
			data.append("ANTECEDENTES: " + "\n" + cita.antecedentes + "\n");
		}
		
		ArrayList<DiagnosticoDto> diagcita = dc.obtenerDiagnosticosDeCita(cita.id);
		
		if (cita.diagnostico != null || cita.diagnostico.isEmpty()) {
			data.append("DIAGNOSTICOS: " + "\n");
			for (DiagnosticoDto d : diagcita) {
				data.append(d.diagnostico + "\n");
			}
		}
		this.historial.datos = data.toString();
		getTxtInfoHistorial().setText(this.historial.datos);
	}

	private JPanel getPnlPrincipal() {
		if (pnlPrincipal == null) {
			pnlPrincipal = new JPanel();
			pnlPrincipal.setLayout(null);
			pnlPrincipal.add(getBtnVolver());
			pnlPrincipal.add(getLblHistorial());
			pnlPrincipal.add(getPanel_2());
		}
		return pnlPrincipal;
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					salir();
				}
			});
			btnVolver.setBounds(592, 434, 97, 25);
		}
		return btnVolver;
	}

	private void actualizarTxtArea() {
//		StringBuilder antecedentes = new StringBuilder();
//		for(TiposAntecedentes t: this.antecedentes.keySet()) {
//			antecedentes.append(t + "\n");
//			for(String a: this.antecedentes.get(t)) {
//				antecedentes.append("\t- " + a + ".\n");
//			}
//			antecedentes.append("\n");
//		}
//		getTxtAreaAntecedentes().setText(antecedentes.toString());
	}

	private void salir() {
		int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere salir?", "Salir",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (respuesta == JOptionPane.YES_OPTION) {
			VentanaGestionCita v = new VentanaGestionCita(cita);
			v.setLocationRelativeTo(this);
			v.setVisible(true);
			getTxtInfoHistorial().setText("");
			this.dispose();
		}
	}

	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}

	private JLabel getLblHistorial() {
		if (lblHistorial == null) {
			lblHistorial = new JLabel("Historial del paciente");
			lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
			lblHistorial.setFont(new Font("Tahoma", Font.PLAIN, 38));
			lblHistorial.setBounds(49, 13, 575, 64);
		}
		return lblHistorial;
	}

	private JPanel getPanel_2() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			panel.setBounds(12, 77, 677, 344);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTxtInfoHistorial());
		}
		return scrollPane;
	}

	private JTextArea getTxtInfoHistorial() {
		if (txtInfoHistorial == null) {
			txtInfoHistorial = new JTextArea();
			txtInfoHistorial.setWrapStyleWord(true);
			txtInfoHistorial.setLineWrap(true);
			txtInfoHistorial.setEditable(false);
		}
		return txtInfoHistorial;
	}
}
