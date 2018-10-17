package ui.medico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.CitasController;
import business.dto.CitaDto;
import business.dto.EmpleadoDto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.border.LineBorder;
import java.awt.Color;

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

	/**
	 * Constructor de la ventana.
	 */
	public VentanaCitasMedico(EmpleadoDto medico) {
		this.medico = medico;
		this.citasController = new CitasController();
		this.modeloCitas = new DefaultListModel<CitaDto>();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
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
			lsCitas.setFont(new Font("Tahoma", Font.PLAIN, 23));
			lsCitas.setSelectedIndex(0);
			lsCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return lsCitas;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new CardLayout(0, 0));
			panel.add(getPnlListadoCitas(), "PanelListadoCitas");
			panel.add(getPnlConsultaCita(), "PanelConsultaCita");
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
						((CardLayout) getPanel().getLayout()).show(getPanel(), "PanelConsultaCita");
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
					((CardLayout) getPanel().getLayout()).show(getPanel(), "PanelListadoCitas");
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
			if(cita.fechainicio.getYear() == year && cita.fechainicio.getDate() == diaMes && cita.fechainicio.getMonth() == month)
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
		if(calendario == null)
		{
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
}
