package ui.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import business.CitasController;
import business.LogController;
import business.dto.CambioDto;
import business.dto.CitaDto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;

public class VentanaConsultarCitas extends JFrame {

	private JPanel contentPane;
	private JPanel pnlBuscadorCitas;
	private JPanel pnlBotonesCitas;
	private JPanel pnlCentro;
	private JLabel lblListadoDeCitas;
	private JList<CitaDto> lsCitas;
	private JButton btnConsultar;
	
	private DefaultListModel<CitaDto> modeloCitas;
	private CitasController citasController;
	private JPanel pnlConsultaCita;
	private JLabel lblInformacion;
	private JPanel pnlinfoConsulta;
	private JPanel pnlBotonAtras;
	private JButton btnAtras;
	private JPanel pnlInfoHistorial;
	private JTextArea txtInfoHistorial;

	/**
	 * Create the frame.
	 */
	public VentanaConsultarCitas() {
		modeloCitas = new DefaultListModel<>();
		citasController = new CitasController();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnlBuscadorCitas(), "pnlBuscadorDeCitas");
		contentPane.add(getPnlConsultaCita(), "pnlConsultaCita");
		cargarModelo();
		
		CambioDto cambio = new CambioDto();
		cambio.cambio = "El administrador ha listado las citas";
		cambio.fecha = new Date();					
		LogController lc = new LogController();
		lc.añadirCambio(cambio);
	}

	private JPanel getPnlBuscadorCitas() {
		if (pnlBuscadorCitas == null) {
			pnlBuscadorCitas = new JPanel();
			pnlBuscadorCitas.setLayout(new BorderLayout(0, 0));
			pnlBuscadorCitas.add(getPnlBotonesCitas(), BorderLayout.SOUTH);
			pnlBuscadorCitas.add(getPnlCentro(), BorderLayout.CENTER);
		}
		return pnlBuscadorCitas;
	}
	private JPanel getPnlBotonesCitas() {
		if (pnlBotonesCitas == null) {
			pnlBotonesCitas = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlBotonesCitas.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlBotonesCitas.add(getBtnConsultar());
		}
		return pnlBotonesCitas;
	}
	private JPanel getPnlCentro() {
		if (pnlCentro == null) {
			pnlCentro = new JPanel();
			pnlCentro.setLayout(new BorderLayout(0, 0));
			pnlCentro.add(getLblListadoDeCitas(), BorderLayout.NORTH);
			pnlCentro.add(getLsCitas(), BorderLayout.CENTER);
		}
		return pnlCentro;
	}
	private JLabel getLblListadoDeCitas() {
		if (lblListadoDeCitas == null) {
			lblListadoDeCitas = new JLabel("Listado de citas");
			lblListadoDeCitas.setHorizontalAlignment(SwingConstants.CENTER);
			lblListadoDeCitas.setFont(new Font("Tahoma", Font.PLAIN, 38));
		}
		return lblListadoDeCitas;
	}
	private JList<CitaDto> getLsCitas() {
		if (lsCitas == null) {
			lsCitas = new JList<>(modeloCitas);
			lsCitas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					CitaDto seleccion = lsCitas.getSelectedValue();
					if(seleccion!=null) {
						getBtnConsultar().setEnabled(true);
					}
				}
			});
			lsCitas.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		}
		return lsCitas;
	}
	private JButton getBtnConsultar() {
		if (btnConsultar == null) {
			btnConsultar = new JButton("Consultar");
			btnConsultar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getLsCitas().isSelectionEmpty()) {
						mostrarMensaje("Debe seleccionar una cita para consultar!", "Error: No hay cita seleccionada",
								JOptionPane.ERROR_MESSAGE);
					} else {
						((CardLayout) getContentPane().getLayout()).show(getContentPane(), "pnlConsultaCita");
						getTxtInfoHistorial().setText(
								citasController.cargarDatosHistorial(getLsCitas().getSelectedValue().idPaciente));
						// TODO hay que cargar toda la información una vez seleccionemos todo
					}
				}
			});
			btnConsultar.setEnabled(false);
			btnConsultar.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return btnConsultar;
	}

	private JPanel getPnlConsultaCita() {
		if (pnlConsultaCita == null) {
			pnlConsultaCita = new JPanel();
			pnlConsultaCita.setLayout(new BorderLayout(0, 0));
			pnlConsultaCita.add(getLblInformacion(), BorderLayout.NORTH);
			pnlConsultaCita.add(getPnlinfoConsulta());
			pnlConsultaCita.add(getPnlBotonAtras(), BorderLayout.SOUTH);
		}
		return pnlConsultaCita;
	}
	private JLabel getLblInformacion() {
		if (lblInformacion == null) {
			lblInformacion = new JLabel("Informaci\u00F3n sobre la cita");
			lblInformacion.setHorizontalAlignment(SwingConstants.CENTER);
			lblInformacion.setFont(new Font("Tahoma", Font.PLAIN, 38));
		}
		return lblInformacion;
	}
	private JPanel getPnlinfoConsulta() {
		if (pnlinfoConsulta == null) {
			pnlinfoConsulta = new JPanel();
			pnlinfoConsulta.setLayout(new BorderLayout(0, 0));
			pnlinfoConsulta.add(getPnlInfoHistorial(), BorderLayout.SOUTH);
		}
		return pnlinfoConsulta;
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
					((CardLayout)getContentPane().getLayout()).show(getContentPane(), "pnlBuscadorDeCitas");
					getTxtInfoHistorial().setText("");
				}
			});
		}
		return btnAtras;
	}
	private JPanel getPnlInfoHistorial() {
		if (pnlInfoHistorial == null) {
			pnlInfoHistorial = new JPanel();
			pnlInfoHistorial.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Historial:",
								TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlInfoHistorial.setLayout(new BorderLayout(0, 0));
			pnlInfoHistorial.add(getTxtInfoHistorial(), BorderLayout.NORTH);
		}
		return pnlInfoHistorial;
	}
	private JTextArea getTxtInfoHistorial() {
		if (txtInfoHistorial == null) {
			txtInfoHistorial = new JTextArea();
			txtInfoHistorial.setRows(10);
			txtInfoHistorial.setEditable(false);
		}
		return txtInfoHistorial;
	}
	
	private void cargarModelo() {
		List<CitaDto> citas = citasController.getListadoCompletoDecitas();
		for(CitaDto cita: citas) {
			modeloCitas.addElement(cita);
		}
		
	}
	
	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}	
}

