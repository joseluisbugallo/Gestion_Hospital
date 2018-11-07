package ui.medico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import business.dto.CitaDto;
import ui.medico.gestionCitas.VentanaGestionAntecedentes;
import ui.medico.gestionCitas.VentanaGestionProcedimientos;

public class VentanaGestionCita extends JDialog {

	private final JPanel pnlPrincipal = new JPanel();

	private CitaDto cita;
	private JLabel lblGestinDeLa;
	private JPanel panel;
	private JLabel label_2;
	private JTextField textField_1;
	private JLabel label_3;
	private JTextField textField_2;
	private JLabel label_4;
	private JTextField textField_3;
	private JPanel panel_1;
	private JButton btnAtras;
	private JButton btnFinalizar;
	private JButton btnHistorial;
	private JButton btnAntecedentes;
	private JButton btnProcedimientos;
	private JButton btnDiagnostico;
	private JButton btnSintomas;
	private JButton btnPrescripcion;
	private JPanel panel_2;
	private JLabel lblNombre;
	private JTextField textField;
	private JLabel lblTelefono;
	private JTextField textField_4;
	private JButton btnCalendarioDeVacunas;

	/**
	 * Create the dialog.
	 */
	public VentanaGestionCita(CitaDto cita) {
		setResizable(false);
		this.cita=cita;
		setDefaultCloseOperation(0);
		setBounds(100, 100, 707, 507);
		getContentPane().setLayout(new BorderLayout());
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
		pnlPrincipal.setLayout(null);
		pnlPrincipal.add(getLblGestinDeLa());
		pnlPrincipal.add(getPanel());
		pnlPrincipal.add(getPanel_1());
		pnlPrincipal.add(getBtnAtras());
		pnlPrincipal.add(getBtnFinalizar());
		pnlPrincipal.add(getPanel_2());
	}

	private JLabel getLblGestinDeLa() {
		if (lblGestinDeLa == null) {
			lblGestinDeLa = new JLabel("Gesti\u00F3n de la Cita");
			lblGestinDeLa.setHorizontalAlignment(SwingConstants.CENTER);
			lblGestinDeLa.setBounds(155, 13, 391, 63);
		}
		return lblGestinDeLa;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Datos de la cita:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(357, 77, 332, 176);
			panel.setLayout(null);
			panel.add(getLabel_2());
			panel.add(getTextField_1());
			panel.add(getLabel_3());
			panel.add(getTextField_2());
			panel.add(getLabel_4());
			panel.add(getTextField_3());
		}
		return panel;
	}

	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Sala:");
			label_2.setBounds(68, 35, 30, 16);
		}
		return label_2;
	}

	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setBounds(110, 32, 144, 22);
			textField_1.setEditable(false);
			textField_1.setColumns(10);
		}
		return textField_1;
	}

	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Fecha inicio:");
			label_3.setBounds(26, 79, 72, 16);
		}
		return label_3;
	}

	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setBounds(110, 118, 144, 22);
			textField_2.setEditable(false);
			textField_2.setColumns(10);
		}
		return textField_2;
	}

	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Fecha fin:");
			label_4.setBounds(41, 121, 57, 16);
		}
		return label_4;
	}

	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setBounds(110, 76, 144, 22);
			textField_3.setEditable(false);
			textField_3.setColumns(10);
		}
		return textField_3;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Opciones:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(12, 266, 677, 141);
			panel_1.setLayout(new GridLayout(2, 3, 0, 0));
			panel_1.add(getBtnHistorial());
			panel_1.add(getBtnSintomas());
			panel_1.add(getBtnDiagnostico());
			panel_1.add(getBtnAntecedentes());
			panel_1.add(getBtnProcedimientos());
			panel_1.add(getBtnPrescripcion());
		}
		return panel_1;
	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrarVentana();
				}
			});
			btnAtras.setBounds(485, 420, 97, 25);
		}
		return btnAtras;
	}

	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setBounds(592, 420, 97, 25);
		}
		return btnFinalizar;
	}

	private JButton getBtnHistorial() {
		if (btnHistorial == null) {
			btnHistorial = new JButton("Historial");
		}
		return btnHistorial;
	}

	private JButton getBtnAntecedentes() {
		if (btnAntecedentes == null) {
			btnAntecedentes = new JButton("Antecedentes");
			btnAntecedentes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirVentanaGestionAntecedentes();
				}
			});
		}
		return btnAntecedentes;
	}

	private JButton getBtnProcedimientos() {
		if (btnProcedimientos == null) {
			btnProcedimientos = new JButton("Procedimientos");
			btnProcedimientos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					abrirVentanaProcedimientos();
				}
			});
		}
		return btnProcedimientos;
	}

	private void abrirVentanaProcedimientos() {
		VentanaGestionProcedimientos dialogo = new VentanaGestionProcedimientos(cita);
		dialogo.setLocationRelativeTo(this);
		dialogo.setModal(true);
		dialogo.setVisible(true);

	}

	private JButton getBtnDiagnostico() {
		if (btnDiagnostico == null) {
			btnDiagnostico = new JButton("Diagnostico");
		}
		return btnDiagnostico;
	}

	private JButton getBtnSintomas() {
		if (btnSintomas == null) {
			btnSintomas = new JButton("Sintomas");
		}
		return btnSintomas;
	}

	private JButton getBtnPrescripcion() {
		if (btnPrescripcion == null) {
			btnPrescripcion = new JButton("Prescripcion");
		}
		return btnPrescripcion;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Datos del Paciente:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(12, 77, 333, 176);
			panel_2.add(getLblNombre());
			panel_2.add(getTextField());
			panel_2.add(getLblTelefono());
			panel_2.add(getTextField_4());
			panel_2.add(getBtnCalendarioDeVacunas());
		}
		return panel_2;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(24, 27, 56, 16);
		}
		return lblNombre;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBounds(92, 24, 198, 22);
		}
		return textField;
	}

	private JLabel getLblTelefono() {
		if (lblTelefono == null) {
			lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(24, 78, 56, 16);
		}
		return lblTelefono;
	}

	private JTextField getTextField_4() {
		if (textField_4 == null) {
			textField_4 = new JTextField();
			textField_4.setEditable(false);
			textField_4.setColumns(10);
			textField_4.setBounds(92, 75, 198, 22);
		}
		return textField_4;
	}

	private JButton getBtnCalendarioDeVacunas() {
		if (btnCalendarioDeVacunas == null) {
			btnCalendarioDeVacunas = new JButton("Calendario de Vacunas");
			btnCalendarioDeVacunas.setBounds(56, 126, 225, 25);
		}
		return btnCalendarioDeVacunas;
	}

	private void cerrarVentana() {
		int resp = JOptionPane.showConfirmDialog(this, "¿Estás seguro de querer cerrar sin guardar?",
				"Cerrar sin guardar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		if (resp == JOptionPane.OK_OPTION)
			this.dispose();
	}

	private void abrirVentanaGestionAntecedentes() {
		VentanaGestionAntecedentes vGA = new VentanaGestionAntecedentes(cita);
		vGA.setLocationRelativeTo(this);
		vGA.setModal(true);
		vGA.setVisible(true);
	}
}
