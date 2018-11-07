package ui.medico.gestionCitas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.CitasController;
import business.dto.CitaDto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaGestionSintomas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblSntomasDelPacient;
	private JTextArea txSintomas;
	private JPanel pnBotones;
	private JButton btnAtras;
	private JButton btnConfirmar;
	
	private CitasController cc;
	private CitaDto cita;

	/**
	 * Create the frame.
	 */
	public VentanaGestionSintomas(CitaDto cita) {
		cc = new CitasController();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblSntomasDelPacient(), BorderLayout.NORTH);
		contentPane.add(getTxSintomas(), BorderLayout.CENTER);
		contentPane.add(getPnBotones(), BorderLayout.SOUTH);
		txSintomas.setText(cc.getCitaById(cita.id).sintomas);
		this.cita=cita;
	}

	private JLabel getLblSntomasDelPacient() {
		if (lblSntomasDelPacient == null) {
			lblSntomasDelPacient = new JLabel("S\u00EDntomas del paciente");
			lblSntomasDelPacient.setHorizontalAlignment(SwingConstants.CENTER);
			lblSntomasDelPacient.setFont(new Font("Verdana", Font.PLAIN, 17));
		}
		return lblSntomasDelPacient;
	}
	private JTextArea getTxSintomas() {
		if (txSintomas == null) {
			txSintomas = new JTextArea();
			txSintomas.setFont(new Font("Monospaced", Font.PLAIN, 14));
		}
		return txSintomas;
	}
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			FlowLayout fl_pnlBotonConsulta = (FlowLayout) pnBotones.getLayout();
			fl_pnlBotonConsulta.setAlignment(FlowLayout.RIGHT);
			pnBotones.add(getBtnAtras());
			pnBotones.add(getBtnConfirmar());
		}
		return pnBotones;
	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atr\u00E1s");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnAtras;
	}
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cc.modificarSintomas(cita, txSintomas.getText());
					dispose();
				}
			});
		}
		return btnConfirmar;
	}
}
