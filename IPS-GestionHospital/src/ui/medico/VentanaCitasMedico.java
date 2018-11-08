package ui.medico;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import business.CitasController;
import business.dto.CitaDto;
import business.dto.EmpleadoDto;

public class VentanaCitasMedico extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblListadoDeCitas;
	private JPanel pnlListadoCitas;
	private JPanel pnlBotonConsulta;
	private JPanel panel;
	private JList<CitaDto> lsCitas;
	private DefaultListModel<CitaDto> modeloCitas;
	private EmpleadoDto medico;
	private CitasController citasController;
	private JPanel pnlCentral;
	private JDateChooser calendario;
	private JPanel panel_1;
	private JButton btnConsultarCitas;
	private JLabel lblNewLabel;
	private JButton btnGestionar;
	private JPanel pnGestionCita;
	private JPanel panel_2;
	private JTextArea txSintomas;
	private JButton btnAtrasGestion;
	private JButton btnConfirmar;
	
	private CardLayout c;

	/**
	 * Constructor de la ventana.
	 */
	public VentanaCitasMedico(EmpleadoDto medico) {
		c = new CardLayout();
		this.medico = medico;
		this.citasController = new CitasController();
		this.modeloCitas = new DefaultListModel<CitaDto>();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.CENTER);
		cargarModelo();
	}

	private JLabel getLblListadoDeCitas() {
		if (lblListadoDeCitas == null) {
			lblListadoDeCitas = new JLabel("Listado de citas");
			lblListadoDeCitas.setHorizontalAlignment(SwingConstants.CENTER);
			lblListadoDeCitas.setFont(new Font("Tahoma", Font.PLAIN, 38));
		}
		return lblListadoDeCitas;
	}

	private JPanel getPnlListadoCitas() {
		if (pnlListadoCitas == null) {
			pnlListadoCitas = new JPanel();
			pnlListadoCitas.setLayout(new BorderLayout(0, 0));
			pnlListadoCitas.add(getLblListadoDeCitas(), BorderLayout.NORTH);
			pnlListadoCitas.add(getPnlBotonConsulta(), BorderLayout.SOUTH);
			pnlListadoCitas.add(getPnlCentral(), BorderLayout.CENTER);
		}
		return pnlListadoCitas;
	}

	private JPanel getPnlBotonConsulta() {
		if (pnlBotonConsulta == null) {
			pnlBotonConsulta = new JPanel();
			FlowLayout fl_pnlBotonConsulta = (FlowLayout) pnlBotonConsulta.getLayout();
			fl_pnlBotonConsulta.setAlignment(FlowLayout.RIGHT);
			pnlBotonConsulta.add(getBtnGestionar());
		}
		return pnlBotonConsulta;
	}

	private JList<CitaDto> getLsCitas() {
		if (lsCitas == null) {
			lsCitas = new JList<CitaDto>(modeloCitas);
			lsCitas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					CitaDto seleccion = lsCitas.getSelectedValue();
					if (seleccion != null) {
						btnGestionar.setEnabled(true);

					}
				}
			});
			lsCitas.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lsCitas.setSelectedIndex(0);
			lsCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return lsCitas;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(c);
			panel.add(getPnlListadoCitas(), "PanelListadoCitas");
			panel.add(getPnGestionCita(), "pnGestion");
		}
		return panel;
	}

	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}

	private void cargarModelo() {
		modeloCitas.removeAllElements();
		List<CitaDto> citas = citasController.getListadoCitas(medico);

		java.util.Date fecha = getCalendario().getDate();
		int diaMes = fecha.getDate();
		int year = fecha.getYear();
		int month = fecha.getMonth();

		for (CitaDto cita : citas) {
			if (cita.fechainicio.getYear() == year && cita.fechainicio.getDate() == diaMes
					&& cita.fechainicio.getMonth() == month)
				modeloCitas.addElement(cita);
		}
	}

	private JPanel getPnlCentral() {
		if (pnlCentral == null) {
			pnlCentral = new JPanel();
			pnlCentral.setLayout(new BorderLayout(0, 0));
			pnlCentral.add(getLsCitas(), BorderLayout.CENTER);
			pnlCentral.add(getPanel_1(), BorderLayout.NORTH);
		}
		return pnlCentral;
	}

	private JDateChooser getCalendario() {
		if (calendario == null) {
			calendario = new JDateChooser();
			calendario.setDate(new java.util.Date());
		}
		return calendario;

	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getLblNewLabel());
			panel_1.add(getCalendario());
			panel_1.add(getBtnConsultarCitas());
		}
		return panel_1;
	}

	private JButton getBtnConsultarCitas() {
		if (btnConsultarCitas == null) {
			btnConsultarCitas = new JButton("Consultar Citas");
			btnConsultarCitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cargarModelo();
				}
			});
		}
		return btnConsultarCitas;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Consultar citas para este dia:");
		}
		return lblNewLabel;
	}

	private JButton getBtnGestionar() {
		if (btnGestionar == null) {
			btnGestionar = new JButton("Gestionar");
			btnGestionar.setEnabled(false);
			btnGestionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (lsCitas.getSelectedValue() != null) {
						abrirVentanaGestionCita(lsCitas.getSelectedValue());
					} else
						JOptionPane.showMessageDialog(contentPane, "Debe seleccionar una cita para gestionar",
								"No hay cita seleccionada", JOptionPane.WARNING_MESSAGE);
				}
			});
		}
		return btnGestionar;
	}

	private JPanel getPnGestionCita() {
		if (pnGestionCita == null) {
			pnGestionCita = new JPanel();
			pnGestionCita.setLayout(null);
			pnGestionCita.add(getPanel_2());
			pnGestionCita.add(getBtnAtrasGestion());
			pnGestionCita.add(getBtnConfirmar());
		}
		return pnGestionCita;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "S\u00EDntomas del paciente", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panel_2.setBounds(45, 196, 567, 225);
			panel_2.setLayout(new GridLayout(1, 0, 0, 0));
			panel_2.add(getTxSintomas());
		}
		return panel_2;
	}

	private JTextArea getTxSintomas() {
		if (txSintomas == null) {
			txSintomas = new JTextArea();
		}
		return txSintomas;
	}

	private JButton getBtnAtrasGestion() {
		if (btnAtrasGestion == null) {
			btnAtrasGestion = new JButton("Atr\u00E1s");
			btnAtrasGestion.setBounds(470, 434, 89, 23);
		}
		return btnAtrasGestion;
	}

	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.setBounds(576, 434, 105, 23);
		}
		return btnConfirmar;
	}

	private void abrirVentanaGestionCita(CitaDto cita) {
		CitaDto c = citasController.getCitaById(cita.id);
		CitaDto aux = citasController.precargarDatos(c);
		VentanaGestionCita vGC = new VentanaGestionCita(aux);
		vGC.setVisible(true);
		vGC.setLocationRelativeTo(this);
	}
}
