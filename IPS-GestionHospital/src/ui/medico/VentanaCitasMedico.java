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
	private JButton btnConsulta;
	private JPanel panel;
	private JPanel pnlConsultaCita;
	private JPanel pnlBotonAtras;
	private JButton btnAtras;
	private JPanel pnlInfoCosulta;
	private JList<CitaDto> lsCitas;
	private DefaultListModel<CitaDto> modeloCitas;
	private EmpleadoDto medico;
	private CitasController citasController;
	private JLabel lblInformacinSobreLa;
	private JPanel pnlInfoHistorial;
	private JTextArea txtInfoHistorial;
	private JPanel pnlCentral;
	private JDateChooser calendario;
	private JPanel panel_1;
	private JButton btnConsultarCitas;
	private JLabel lblNewLabel;
	private JButton btnGestionar;
	private JPanel pnGestionCita;
	private JLabel label;
	private JLabel label_1;
	private JTextField txPaciente;
	private JLabel label_2;
	private JTextField txSala;
	private JLabel lblFechaInicio;
	private JTextField txFechaInicio;
	private JLabel lblFechaFin;
	private JTextField txFechaFin;
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
			pnlBotonConsulta.add(getBtnConsulta());
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
						btnConsulta.setEnabled(true);

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
			panel.add(getPnlConsultaCita(), "PanelConsultaCita");
			panel.add(getPnGestionCita(), "pnGestion");
		}
		return panel;
	}

	private JPanel getPnlConsultaCita() {
		if (pnlConsultaCita == null) {
			pnlConsultaCita = new JPanel();
			pnlConsultaCita.setLayout(new BorderLayout(0, 0));
			pnlConsultaCita.add(getLblInformacinSobreLa(), BorderLayout.NORTH);
			pnlConsultaCita.add(getPnlInfoConsulta(), BorderLayout.CENTER);
			pnlConsultaCita.add(getPnlBotonAtras(), BorderLayout.SOUTH);
		}
		return pnlConsultaCita;
	}

	private JButton getBtnConsulta() {
		if (btnConsulta == null) {
			btnConsulta = new JButton("Consultar");
			btnConsulta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getLsCitas().isSelectionEmpty()) {
						mostrarMensaje("Debe seleccionar una cita para consultar!", "Error: No hay cita seleccionada",
								JOptionPane.ERROR_MESSAGE);
					} else {
						c.show(getPanel(), "PanelConsultaCita");
						getTxtInfoHistorial().setText(
								citasController.cargarDatosHistorial(getLsCitas().getSelectedValue().idPaciente));
						// TODO hay que cargar toda la información una vez seleccionemos todo
					}
				}
			});
			btnConsulta.setEnabled(false);
		}
		return btnConsulta;
	}

	private JPanel getPnlBotonAtras() {
		if (pnlBotonAtras == null) {
			pnlBotonAtras = new JPanel();
			pnlBotonAtras.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnlBotonAtras.add(getBtnAtras());
		}
		return pnlBotonAtras;
	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atr\u00E1s");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.show(getPanel(), "PanelListadoCitas");
					getTxtInfoHistorial().setText("");
				}
			});
		}
		return btnAtras;
	}

	private JPanel getPnlInfoConsulta() {
		if (pnlInfoCosulta == null) {
			pnlInfoCosulta = new JPanel();
			pnlInfoCosulta.setLayout(new BorderLayout(0, 0));
			pnlInfoCosulta.add(getPnlInfoHistorial(), BorderLayout.CENTER);
		}
		return pnlInfoCosulta;
	}

	private JLabel getLblInformacinSobreLa() {
		if (lblInformacinSobreLa == null) {
			lblInformacinSobreLa = new JLabel("Informaci\u00F3n sobre la cita");
			lblInformacinSobreLa.setHorizontalAlignment(SwingConstants.CENTER);
			lblInformacinSobreLa.setFont(new Font("Tahoma", Font.PLAIN, 38));
		}
		return lblInformacinSobreLa;
	}

	private JPanel getPnlInfoHistorial() {
		if (pnlInfoHistorial == null) {
			pnlInfoHistorial = new JPanel();
			pnlInfoHistorial.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Historial:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlInfoHistorial.setLayout(new BorderLayout(0, 0));
			pnlInfoHistorial.add(getTxtInfoHistorial(), BorderLayout.CENTER);
		}
		return pnlInfoHistorial;
	}

	private JTextArea getTxtInfoHistorial() {
		if (txtInfoHistorial == null) {
			txtInfoHistorial = new JTextArea();
			txtInfoHistorial.setLineWrap(true);
			txtInfoHistorial.setFont(new Font("Monospaced", Font.PLAIN, 16));
			txtInfoHistorial.setEditable(false);
			txtInfoHistorial.setRows(10);
		}
		return txtInfoHistorial;
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
			btnGestionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (lsCitas.getSelectedValue() != null) {
						c.show(panel, "pnGestion");
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
			pnGestionCita.add(getLabel());
			pnGestionCita.add(getLabel_1());
			pnGestionCita.add(getTxPaciente());
			pnGestionCita.add(getLabel_2());
			pnGestionCita.add(getTxSala());
			pnGestionCita.add(getLblFechaInicio());
			pnGestionCita.add(getTxFechaInicio());
			pnGestionCita.add(getLblFechaFin());
			pnGestionCita.add(getTxFechaFin());
			pnGestionCita.add(getPanel_2());
			pnGestionCita.add(getBtnAtrasGestion());
			pnGestionCita.add(getBtnConfirmar());
		}
		return pnGestionCita;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Gesti\u00F3n de cita");
			label.setFont(new Font("Tahoma", Font.PLAIN, 17));
			label.setBounds(45, 30, 172, 35);
		}
		return label;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Paciente:");
			label_1.setBounds(45, 90, 57, 20);
		}
		return label_1;
	}

	private JTextField getTxPaciente() {
		if (txPaciente == null) {
			txPaciente = new JTextField();
			txPaciente.setEditable(false);
			txPaciente.setColumns(10);
			txPaciente.setBounds(107, 90, 201, 20);
		}
		return txPaciente;
	}

	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Sala:");
			label_2.setBounds(45, 134, 46, 14);
		}
		return label_2;
	}

	private JTextField getTxSala() {
		if (txSala == null) {
			txSala = new JTextField();
			txSala.setEditable(false);
			txSala.setBounds(107, 131, 201, 20);
			txSala.setColumns(10);
		}
		return txSala;
	}

	private JLabel getLblFechaInicio() {
		if (lblFechaInicio == null) {
			lblFechaInicio = new JLabel("Fecha inicio:");
			lblFechaInicio.setBounds(333, 93, 74, 14);
		}
		return lblFechaInicio;
	}

	private JTextField getTxFechaInicio() {
		if (txFechaInicio == null) {
			txFechaInicio = new JTextField();
			txFechaInicio.setEditable(false);
			txFechaInicio.setColumns(10);
			txFechaInicio.setBounds(406, 90, 201, 20);
		}
		return txFechaInicio;
	}

	private JLabel getLblFechaFin() {
		if (lblFechaFin == null) {
			lblFechaFin = new JLabel("Fecha fin:");
			lblFechaFin.setBounds(333, 134, 66, 14);
		}
		return lblFechaFin;
	}

	private JTextField getTxFechaFin() {
		if (txFechaFin == null) {
			txFechaFin = new JTextField();
			txFechaFin.setEditable(false);
			txFechaFin.setBounds(406, 131, 201, 20);
			txFechaFin.setColumns(10);
		}
		return txFechaFin;
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
}
