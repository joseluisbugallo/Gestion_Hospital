package ui.admin;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JList;

public class VentanaJornadaLaboral extends JFrame{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private JPanel contentPane;
	
	DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
	private JDateChooser dcInicio;
	private JLabel lblFechaYHoraInicio;
	private JLabel lblFechaYHoraFin;
	private JDateChooser dcFin;
	private JButton btnAsignar;
	private JButton btnCancelar;
	private JCheckBox chckbxMedico;
	private JCheckBox chckbxEnfermero;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblSeleccionarEmpleado;
	private JComboBox cmbxEmpleados;
	private JPanel panelEmpleado;
	private JList listDias;
	private JPanel panelJornada;
	private JPanel panelFechas;
	private JPanel panelDias;
	private JPanel panelBotones;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
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
	public VentanaJornadaLaboral() {
		setTitle("Asignar jornada laboral a m\u00E9dicos o enfermeros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getPanelEmpleado());
		contentPane.add(getPanel_1());
		contentPane.add(getPanelBotones());
	}
	private JDateChooser getDcInicio() {
		if (dcInicio == null) {
			dcInicio = new JDateChooser();
			dcInicio.setDateFormatString("dd/MM/yy HH:mm");
		}
		return dcInicio;
	}
	private JLabel getLblFechaYHoraInicio() {
		if (lblFechaYHoraInicio == null) {
			lblFechaYHoraInicio = new JLabel("Fecha y hora inicio:");
			lblFechaYHoraInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblFechaYHoraInicio;
	}
	private JLabel getLblFechaYHoraFin() {
		if (lblFechaYHoraFin == null) {
			lblFechaYHoraFin = new JLabel("Fecha y hora fin:");
			lblFechaYHoraFin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblFechaYHoraFin;
	}
	private JDateChooser getDcFin() {
		if (dcFin == null) {
			dcFin = new JDateChooser();
			dcFin.setDateFormatString("dd/MM/yy HH:mm");
		}
		return dcFin;
	}
	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar");
			btnAsignar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnAsignar;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnCancelar;
	}
	private JCheckBox getChckbxMedico() {
		if (chckbxMedico == null) {
			chckbxMedico = new JCheckBox("M\u00E9dico");
			buttonGroup.add(chckbxMedico);
		}
		return chckbxMedico;
	}
	private JCheckBox getChckbxEnfermero() {
		if (chckbxEnfermero == null) {
			chckbxEnfermero = new JCheckBox("Enfermero");
			buttonGroup.add(chckbxEnfermero);
		}
		return chckbxEnfermero;
	}
	private JLabel getLblSeleccionarEmpleado() {
		if (lblSeleccionarEmpleado == null) {
			lblSeleccionarEmpleado = new JLabel("Seleccionar empleado:");
		}
		return lblSeleccionarEmpleado;
	}
	private JComboBox getCmbxEmpleados() {
		if (cmbxEmpleados == null) {
			cmbxEmpleados = new JComboBox();
		}
		return cmbxEmpleados;
	}
	private JPanel getPanelEmpleado() {
		if (panelEmpleado == null) {
			panelEmpleado = new JPanel();
			panelEmpleado.setBorder(new TitledBorder(null, "Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelEmpleado.setLayout(new GridLayout(0, 1, 0, 0));
			panelEmpleado.add(getChckbxMedico());
			panelEmpleado.add(getChckbxEnfermero());
			panelEmpleado.add(getLblSeleccionarEmpleado());
			panelEmpleado.add(getCmbxEmpleados());
		}
		return panelEmpleado;
	}
	private JList getListDias() {
		if (listDias == null) {
			listDias = new JList();
		}
		return listDias;
	}
	private JPanel getPanel_1() {
		if (panelJornada == null) {
			panelJornada = new JPanel();
			panelJornada.setBorder(new TitledBorder(null, "Jornada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelJornada.setLayout(new GridLayout(0, 2, 0, 0));
			panelJornada.add(getPanelFechas());
			panelJornada.add(getPanelDias());
		}
		return panelJornada;
	}
	private JPanel getPanelFechas() {
		if (panelFechas == null) {
			panelFechas = new JPanel();
			panelFechas.setLayout(new GridLayout(0, 2, 0, 0));
			panelFechas.add(getLblFechaYHoraInicio());
			panelFechas.add(getDcInicio());
			panelFechas.add(getLblFechaYHoraFin());
			panelFechas.add(getDcFin());
		}
		return panelFechas;
	}
	private JPanel getPanelDias() {
		if (panelDias == null) {
			panelDias = new JPanel();
			panelDias.add(getListDias());
		}
		return panelDias;
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new GridLayout(2, 4, 0, 0));
			panelBotones.add(getLblNewLabel());
			panelBotones.add(getLblNewLabel_2());
			panelBotones.add(getLblNewLabel_1());
			panelBotones.add(getLblNewLabel_3());
			panelBotones.add(getLblNewLabel_4());
			panelBotones.add(getLblNewLabel_5());
			panelBotones.add(getBtnCancelar());
			panelBotones.add(getBtnAsignar());
		}
		return panelBotones;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("New label");
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
		}
		return lblNewLabel_4;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("");
		}
		return lblNewLabel_5;
	}
}