package ui.admin;

import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public class VentanaFijarCita extends JFrame {

	private JPanel contentPane;
	
	DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
	private JDateChooser dcInicio;
	private JLabel lblCrearNuevaCita;
	private JLabel lblFechaYHora;
	private JLabel lblFechaYHora_1;
	private JDateChooser dcFin;
	private JLabel lblPaciente;
	private JButton btnPaciente;
	private JTextField txPaciente;
	private JLabel lblMdicos;
	private JButton btnMedicos;
	private JScrollPane scPaneMedicos;
	private JCheckBox chckbxUrgente;
	private JLabel lblUbicacin;
	private JTextField txUbicacion;
	private JButton btnCrear;
	private JButton btnCancelar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaFijarCita frame = new VentanaFijarCita();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaFijarCita() {
		setResizable(false);
		setTitle("Fijar cita entre paciente y m\u00E9dico(s)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getDcInicio());
		contentPane.add(getLblCrearNuevaCita());
		contentPane.add(getLblFechaYHora());
		contentPane.add(getLblFechaYHora_1());
		contentPane.add(getDcFin());
		contentPane.add(getLblPaciente());
		contentPane.add(getBtnPaciente());
		contentPane.add(getTxPaciente());
		contentPane.add(getLblMdicos());
		contentPane.add(getBtnMedicos());
		contentPane.add(getScPaneMedicos());
		contentPane.add(getChckbxUrgente());
		contentPane.add(getLblUbicacin());
		contentPane.add(getTxUbicacion());
		contentPane.add(getBtnCrear());
		contentPane.add(getBtnCancelar());
	}
	private JDateChooser getDcInicio() {
		if (dcInicio == null) {
			dcInicio = new JDateChooser();
			dcInicio.setBounds(166, 72, 160, 20);
			dcInicio.setDateFormatString("dd/MM/yy HH:mm");
		}
		return dcInicio;
	}
	private JLabel getLblCrearNuevaCita() {
		if (lblCrearNuevaCita == null) {
			lblCrearNuevaCita = new JLabel("Crear nueva cita");
			lblCrearNuevaCita.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblCrearNuevaCita.setBounds(35, 23, 151, 20);
		}
		return lblCrearNuevaCita;
	}
	private JLabel getLblFechaYHora() {
		if (lblFechaYHora == null) {
			lblFechaYHora = new JLabel("Fecha y hora inicio:");
			lblFechaYHora.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblFechaYHora.setBounds(35, 72, 121, 20);
		}
		return lblFechaYHora;
	}
	private JLabel getLblFechaYHora_1() {
		if (lblFechaYHora_1 == null) {
			lblFechaYHora_1 = new JLabel("Fecha y hora fin:");
			lblFechaYHora_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblFechaYHora_1.setBounds(353, 72, 121, 20);
		}
		return lblFechaYHora_1;
	}
	private JDateChooser getDcFin() {
		if (dcFin == null) {
			dcFin = new JDateChooser();
			dcFin.setDateFormatString("dd/MM/yy HH:mm");
			dcFin.setBounds(469, 72, 160, 20);
		}
		return dcFin;
	}
	private JLabel getLblPaciente() {
		if (lblPaciente == null) {
			lblPaciente = new JLabel("Paciente:");
			lblPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblPaciente.setBounds(35, 123, 68, 20);
		}
		return lblPaciente;
	}
	private JButton getBtnPaciente() {
		if (btnPaciente == null) {
			btnPaciente = new JButton("Seleccionar paciente");
			btnPaciente.setBounds(117, 123, 138, 23);
		}
		return btnPaciente;
	}
	private JTextField getTxPaciente() {
		if (txPaciente == null) {
			txPaciente = new JTextField();
			txPaciente.setEditable(false);
			txPaciente.setBounds(265, 124, 209, 20);
			txPaciente.setColumns(10);
		}
		return txPaciente;
	}
	private JLabel getLblMdicos() {
		if (lblMdicos == null) {
			lblMdicos = new JLabel("M\u00E9dico(s):");
			lblMdicos.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblMdicos.setBounds(35, 179, 68, 20);
		}
		return lblMdicos;
	}
	private JButton getBtnMedicos() {
		if (btnMedicos == null) {
			btnMedicos = new JButton("Seleccionar m\u00E9dico(s)");
			btnMedicos.setBounds(117, 179, 138, 23);
		}
		return btnMedicos;
	}
	private JScrollPane getScPaneMedicos() {
		if (scPaneMedicos == null) {
			scPaneMedicos = new JScrollPane();
			scPaneMedicos.setBounds(265, 179, 228, 72);
		}
		return scPaneMedicos;
	}
	private JCheckBox getChckbxUrgente() {
		if (chckbxUrgente == null) {
			chckbxUrgente = new JCheckBox("Urgente");
			chckbxUrgente.setFont(new Font("Tahoma", Font.PLAIN, 13));
			chckbxUrgente.setBounds(35, 295, 97, 23);
		}
		return chckbxUrgente;
	}
	private JLabel getLblUbicacin() {
		if (lblUbicacin == null) {
			lblUbicacin = new JLabel("Ubicaci\u00F3n:");
			lblUbicacin.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblUbicacin.setBounds(265, 295, 79, 19);
		}
		return lblUbicacin;
	}
	private JTextField getTxUbicacion() {
		if (txUbicacion == null) {
			txUbicacion = new JTextField();
			txUbicacion.setBounds(353, 295, 177, 20);
			txUbicacion.setColumns(10);
		}
		return txUbicacion;
	}
	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCrear.setBounds(582, 330, 89, 23);
		}
		return btnCrear;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCancelar.setBounds(483, 331, 89, 23);
		}
		return btnCancelar;
	}
}
