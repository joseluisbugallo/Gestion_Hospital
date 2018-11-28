package ui.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import business.CitasController;
import business.JornadaController;
import business.LogController;
import business.PacientesController;
import business.dto.CambioDto;
import business.dto.CitaDto;
import business.dto.EmpleadoDto;
import business.dto.PacienteDto;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListModel;
import com.toedter.calendar.JDateChooser;

public class VentanaModificarCitas extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panelPrincipal;
	private JPanel panelMedPac;
	private JButton btnSeleccionarPaciente;
	private JButton btnSeleccionarMdicos;
	private JTextField textField;
	private JTextArea textArea;
	private CardLayout c;
	private JPanel pnSeleccionarMedicos;
	private JLabel lblTitulo;
	private JTextField textFieldDNI;
	private JButton btnBuscarPorDni;
	private JTextField textFieldNombre;
	private JButton btnBuscarPorNombre;
	private JList<EmpleadoDto> listMedico;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnAceptar_1;
	private JButton btnCancelar_1;

	private JornadaController jc = new JornadaController();
	private PacientesController pc = new PacientesController();
	private CitasController citasController = new CitasController();

	DefaultListModel<EmpleadoDto> listModelMedicos;
	DefaultListModel<PacienteDto> listModelPacientes;
	private JButton btnLimpiar;
	private JPanel pnSeleccionarPaciente;
	private JLabel lblSeleccionarPaciente;
	private JTextField textFieldDNIP;
	private JButton button;
	private JTextField textFieldNombreP;
	private JButton btnBuscarNombreP;
	private JList<PacienteDto> listPaciente;
	private JButton btnAceptar_2;
	private JButton btnCancelar_2;
	private JButton btnLimpiar_2;
	private JDateChooser dateChooserInicio;
	private JDateChooser dateChooserFin;
	private JTextField textFieldLocalizacion;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JLabel lblLocalizacin;

	private CitaDto cita;
	private PacienteDto pacienteElegido;
	private EmpleadoDto medicoElegido;

	public VentanaModificarCitas(CitaDto cita) {
		this.cita = cita;
		pacienteElegido = pc.findPacientesById(cita.idPaciente);
		medicoElegido = citasController.getMedicoByID(cita.idEmpleado);
		c = new CardLayout();
		setResizable(false);
		setTitle("Gestionar Citas: Modificar Cita");
		setBounds(100, 100, 750, 497);
		inicializarListas();
		getContentPane().setLayout(new CardLayout(0, 0));
		getContentPane().add(getPanelPrincipal(), "panelPrincipal");
		getContentPane().add(getPnSeleccionarMedicos(), "pnSeleccionarMedicos");
		getContentPane().add(getPnSeleccionarPaciente(), "pnSeleccionarPaciente");
		textFieldLocalizacion.setText(cita.sala);
		dateChooserInicio.setDate(cita.fechainicio);
		dateChooserFin.setDate(cita.fechafin);
		textArea.setText(cita.mostrarMedico());
		textField.setText(cita.mostrarPaciente());
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(29, 31, 687, 114);
			panel.setBorder(new TitledBorder(null, "Fecha y localizaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			panel.setLayout(null);
			panel.add(getDateChooserInicio());
			panel.add(getDateChooserFin());
			panel.add(getTextField_1_3());
			panel.add(getLblFechaInicio());
			panel.add(getLblFechaFin());
			panel.add(getLblLocalizacin());
		}
		return panel;
	}

	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
			panelPrincipal.setLayout(null);
			panelPrincipal.add(getPanel());
			panelPrincipal.add(getPanel_1_1());
			panelPrincipal.add(getBtnAceptar());
			panelPrincipal.add(getBtnCancelar());
		}
		return panelPrincipal;
	}

	private JPanel getPanel_1_1() {
		if (panelMedPac == null) {
			panelMedPac = new JPanel();
			panelMedPac.setBorder(new TitledBorder(null, "Paciente y M\u00E9dicos", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panelMedPac.setBounds(29, 158, 687, 239);
			panelMedPac.setLayout(null);
			panelMedPac.add(getBtnSeleccionarPaciente());
			panelMedPac.add(getBtnSeleccionarMdicos());
			panelMedPac.add(getTextField_1());
			panelMedPac.add(getTextArea_1());
		}
		return panelMedPac;
	}

	private JButton getBtnSeleccionarPaciente() {
		if (btnSeleccionarPaciente == null) {
			btnSeleccionarPaciente = new JButton("Seleccionar paciente");
			btnSeleccionarPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CardLayout) getContentPane().getLayout()).show(getContentPane(), "pnSeleccionarPaciente");
					CambioDto cambio = new CambioDto();
					cambio.cambio = "El administrador ha listado todos los pacientes" ;
					cambio.fecha = new Date();					
					LogController lc = new LogController();
					lc.añadirCambio(cambio);
				}
			});
			btnSeleccionarPaciente.setBounds(12, 45, 163, 25);
		}
		return btnSeleccionarPaciente;
	}

	private JButton getBtnSeleccionarMdicos() {
		if (btnSeleccionarMdicos == null) {
			btnSeleccionarMdicos = new JButton("Seleccionar m\u00E9dico(s)");
			btnSeleccionarMdicos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout) getContentPane().getLayout()).show(getContentPane(), "pnSeleccionarMedicos");
					CambioDto cambio = new CambioDto();
					cambio.cambio = "El administrador ha listado todos los medicos " ;
					cambio.fecha = new Date();					
					LogController lc = new LogController();
					lc.añadirCambio(cambio);
				}
			});
			btnSeleccionarMdicos.setBounds(12, 100, 163, 25);
		}
		return btnSeleccionarMdicos;
	}

	private JTextField getTextField_1() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(197, 46, 460, 22);
			textField.setColumns(10);
		}
		return textField;
	}

	private JTextArea getTextArea_1() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setBounds(197, 101, 460, 107);
		}
		return textArea;
	}

	private JPanel getPnSeleccionarMedicos() {
		if (pnSeleccionarMedicos == null) {
			pnSeleccionarMedicos = new JPanel();
			pnSeleccionarMedicos.setLayout(null);
			pnSeleccionarMedicos.add(getLblTitulo());
			pnSeleccionarMedicos.add(getTextField_1_1());
			pnSeleccionarMedicos.add(getBtnBuscarPorDni());
			pnSeleccionarMedicos.add(getTextFieldNombre());
			pnSeleccionarMedicos.add(getBtnBuscarPorNombre());
			pnSeleccionarMedicos.add(getListMedico());
			pnSeleccionarMedicos.add(getBtnAceptar_1());
			pnSeleccionarMedicos.add(getBtnCancelar_1());
			pnSeleccionarMedicos.add(getBtnLimpiar());
		}
		return pnSeleccionarMedicos;
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Seleccionar m\u00E9dico(s)");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 26));
			lblTitulo.setBounds(32, 28, 293, 37);
		}
		return lblTitulo;
	}

	private JTextField getTextField_1_1() {
		if (textFieldDNI == null) {
			textFieldDNI = new JTextField();
			textFieldDNI.setBounds(32, 95, 143, 22);
			textFieldDNI.setColumns(10);
		}
		return textFieldDNI;
	}

	private JButton getBtnBuscarPorDni() {
		if (btnBuscarPorDni == null) {
			btnBuscarPorDni = new JButton("Buscar por DNI");
			btnBuscarPorDni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarDNIMedicosFiltro();
				}

			});
			btnBuscarPorDni.setBounds(187, 94, 127, 25);
		}
		return btnBuscarPorDni;
	}

	private void buscarDNIMedicosFiltro() {
		for (EmpleadoDto em : jc.getMedicos()) {
			if (textFieldDNI.getText().toLowerCase().equals(em.dni.toLowerCase())) {
				DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
				filtro.addElement(em);
				listMedico.setModel(filtro);
			}
		}
	}

	private void buscarDNIPacientesFiltro() {
		for (PacienteDto em : pc.listadoPacientes()) {
			if (textFieldDNIP.getText().toLowerCase().equals(em.dni.toLowerCase())) {
				DefaultListModel<PacienteDto> filtro = new DefaultListModel<PacienteDto>();
				filtro.addElement(em);
				listPaciente.setModel(filtro);
			}
		}
	}

	private JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(328, 95, 221, 22);
			textFieldNombre.setColumns(10);
		}
		return textFieldNombre;
	}

	private JButton getBtnBuscarPorNombre() {
		if (btnBuscarPorNombre == null) {
			btnBuscarPorNombre = new JButton("Buscar por nombre");
			btnBuscarPorNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarNombreMedicosFiltro();
				}
			});
			btnBuscarPorNombre.setBounds(561, 94, 143, 25);
		}
		return btnBuscarPorNombre;
	}

	private void buscarNombreMedicosFiltro() {
		for (EmpleadoDto em : jc.getMedicos()) {
			if (textFieldNombre.getText().toLowerCase().equals(em.nombre.toLowerCase())) {
				DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
				filtro.addElement(em);
				listMedico.setModel(filtro);
			}
		}

	}

	private JList<EmpleadoDto> getListMedico() {
		if (listMedico == null) {
			listMedico = new JList<>(listModelMedicos);
			listMedico.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			listMedico.setBounds(32, 149, 672, 252);
		}
		return listMedico;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					citasController.modificarCita(cita, pacienteElegido.id, medicoElegido.id,
								textFieldLocalizacion.getText(), dateChooserInicio.getDate(), dateChooserFin.getDate());
					System.out.println(citasController.getListadoCompletoDecitas());
					CambioDto cambio = new CambioDto();
					String c = "El administrador ha modificado la cita con id: "+ cita.id;
					c = c + " El paciente de la cita es id: "+ pacienteElegido.id;
					c = c + " El medico de la cita es id: "+ medicoElegido.id;
					c = c + " La sala de la cita es "+ textFieldLocalizacion.getText();
					c = c + " La fecha de inicio es: "+ dateChooserInicio.getDate();
					c = c + " La fecha de fin es: "+ dateChooserFin.getDate();				
					
					cambio.cambio =  c;
					cambio.fecha = new Date();					
					LogController lc = new LogController();
					lc.añadirCambio(cambio);
					dispose();
				}
			});
			btnAceptar.setBounds(619, 421, 97, 25);
		}
		return btnAceptar;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dateChooserInicio.setDate(cita.fechainicio);
					dateChooserFin.setDate(cita.fechafin);
					textFieldLocalizacion.setText(cita.sala);
					textField.setText(cita.mostrarPaciente());
					textArea.setText(cita.mostrarMedico());
				}
			});
			btnCancelar.setBounds(510, 421, 97, 25);
		}
		return btnCancelar;
	}

	private JButton getBtnAceptar_1() {
		if (btnAceptar_1 == null) {
			btnAceptar_1 = new JButton("Aceptar");
			btnAceptar_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// TODO como meter si seleccionas dos medicos???
					medicoElegido = listMedico.getSelectedValue();
					textArea.setText(medicoElegido.nombre + ";ID: " + medicoElegido.id);
					((CardLayout) getContentPane().getLayout()).show(getContentPane(), "panelPrincipal");
				}
			});
			btnAceptar_1.setBounds(607, 424, 97, 25);
		}
		return btnAceptar_1;
	}

	private JButton getBtnCancelar_1() {
		if (btnCancelar_1 == null) {
			btnCancelar_1 = new JButton("Cancelar");
			btnCancelar_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CardLayout) getContentPane().getLayout()).show(getContentPane(), "panelPrincipal");
					textFieldDNI.setText("");
					textFieldNombre.setText("");
					mostrarTodosMedicos();
				}
			});
			btnCancelar_1.setBounds(389, 424, 97, 25);
		}
		return btnCancelar_1;
	}

	private void inicializarListas() {
		listModelPacientes = new DefaultListModel<PacienteDto>();
		for (PacienteDto paciente : pc.listadoPacientes()) {
			listModelPacientes.addElement(paciente);
		}
		listModelMedicos = new DefaultListModel<EmpleadoDto>();
		for (EmpleadoDto empleado : jc.getMedicos()) {
			listModelMedicos.addElement(empleado);
		}
	}

	private JButton getBtnLimpiar() {
		if (btnLimpiar == null) {
			btnLimpiar = new JButton("Limpiar");
			btnLimpiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarTodosMedicos();
					textFieldDNI.setText("");
					textFieldNombre.setText("");
				}
			});
			btnLimpiar.setBounds(498, 424, 97, 25);
		}
		return btnLimpiar;
	}

	private void mostrarTodosMedicos() {
		DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
		for (EmpleadoDto em : jc.getMedicos()) {
			filtro.addElement(em);
		}
		listMedico.setModel(filtro);
	}

	private JPanel getPnSeleccionarPaciente() {
		if (pnSeleccionarPaciente == null) {
			pnSeleccionarPaciente = new JPanel();
			pnSeleccionarPaciente.setLayout(null);
			pnSeleccionarPaciente.add(getLblSeleccionarPaciente());
			pnSeleccionarPaciente.add(getTextField_1_2());
			pnSeleccionarPaciente.add(getButton());
			pnSeleccionarPaciente.add(getTextFieldNombreP());
			pnSeleccionarPaciente.add(getBtnBuscarNombreP());
			pnSeleccionarPaciente.add(getListPaciente());
			pnSeleccionarPaciente.add(getBtnAceptar_2());
			pnSeleccionarPaciente.add(getBtnCancelar_2());
			pnSeleccionarPaciente.add(getBtnLimpiar_2());
		}
		return pnSeleccionarPaciente;
	}

	private JLabel getLblSeleccionarPaciente() {
		if (lblSeleccionarPaciente == null) {
			lblSeleccionarPaciente = new JLabel("Seleccionar paciente");
			lblSeleccionarPaciente.setFont(new Font("Tahoma", Font.PLAIN, 26));
			lblSeleccionarPaciente.setBounds(32, 28, 293, 37);
		}
		return lblSeleccionarPaciente;
	}

	private JTextField getTextField_1_2() {
		if (textFieldDNIP == null) {
			textFieldDNIP = new JTextField();
			textFieldDNIP.setColumns(10);
			textFieldDNIP.setBounds(32, 95, 143, 22);
		}
		return textFieldDNIP;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("Buscar por DNI");
			btnBuscarPorDni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarDNIPacientesFiltro();
				}

			});
			button.setBounds(187, 94, 127, 25);
		}
		return button;
	}

	private JTextField getTextFieldNombreP() {
		if (textFieldNombreP == null) {
			textFieldNombreP = new JTextField();
			textFieldNombreP.setColumns(10);
			textFieldNombreP.setBounds(328, 95, 221, 22);
		}
		return textFieldNombreP;
	}

	private JButton getBtnBuscarNombreP() {
		if (btnBuscarNombreP == null) {
			btnBuscarNombreP = new JButton("Buscar por nombre");
			btnBuscarNombreP.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarNombrePacientesFiltro();
				}
			});
			btnBuscarNombreP.setBounds(561, 94, 143, 25);
		}
		return btnBuscarNombreP;
	}

	private void buscarNombrePacientesFiltro() {

		for (PacienteDto em : pc.listadoPacientes()) {
			if (textFieldNombreP.getText().toLowerCase().equals(em.nombre.toLowerCase())) {
				DefaultListModel<PacienteDto> filtro = new DefaultListModel<PacienteDto>();
				filtro.addElement(em);
				listPaciente.setModel(filtro);
			}
		}
	}

	private JList<PacienteDto> getListPaciente() {
		if (listPaciente == null) {
			listPaciente = new JList<PacienteDto>(listModelPacientes);
			listPaciente.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			listPaciente.setBounds(32, 149, 672, 252);
		}
		return listPaciente;
	}

	private JButton getBtnAceptar_2() {
		if (btnAceptar_2 == null) {
			btnAceptar_2 = new JButton("Aceptar");
			btnAceptar_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pacienteElegido = listPaciente.getSelectedValue();
					textField.setText(listPaciente.getSelectedValue().nombre);
					((CardLayout) getContentPane().getLayout()).show(getContentPane(), "panelPrincipal");
				}
			});
			btnAceptar_2.setBounds(607, 424, 97, 25);
		}
		return btnAceptar_2;
	}

	private JButton getBtnCancelar_2() {
		if (btnCancelar_2 == null) {
			btnCancelar_2 = new JButton("Cancelar");
			btnCancelar_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout) getContentPane().getLayout()).show(getContentPane(), "panelPrincipal");
					mostrarTodosPacientes();
					textFieldDNIP.setText("");
					textFieldNombreP.setText("");
				}
			});
			btnCancelar_2.setBounds(389, 424, 97, 25);
		}
		return btnCancelar_2;
	}

	private void mostrarTodosPacientes() {
		DefaultListModel<PacienteDto> filtro = new DefaultListModel<PacienteDto>();
		for (PacienteDto em : pc.listadoPacientes()) {
			filtro.addElement(em);
		}
		listPaciente.setModel(filtro);
	}

	private JButton getBtnLimpiar_2() {
		if (btnLimpiar_2 == null) {
			btnLimpiar_2 = new JButton("Limpiar");
			btnLimpiar_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarTodosPacientes();
					textFieldDNI.setText("");
					textFieldNombre.setText("");
				}
			});
			btnLimpiar_2.setBounds(498, 424, 97, 25);
		}
		return btnLimpiar_2;
	}

	private JDateChooser getDateChooserInicio() {
		if (dateChooserInicio == null) {
			dateChooserInicio = new JDateChooser();
			dateChooserInicio.setBounds(135, 37, 147, 22);
			dateChooserInicio.setDateFormatString("dd/MM/yy HH:mm:ss");
		}
		return dateChooserInicio;
	}

	private JDateChooser getDateChooserFin() {
		if (dateChooserFin == null) {
			dateChooserFin = new JDateChooser();
			dateChooserFin.setBounds(470, 37, 147, 22);
			dateChooserFin.setDateFormatString("dd/MM/yy HH:mm:ss");
		}
		return dateChooserFin;
	}

	private JTextField getTextField_1_3() {
		if (textFieldLocalizacion == null) {
			textFieldLocalizacion = new JTextField();
			textFieldLocalizacion.setBounds(135, 79, 147, 22);
			textFieldLocalizacion.setColumns(10);
		}
		return textFieldLocalizacion;
	}

	private JLabel getLblFechaInicio() {
		if (lblFechaInicio == null) {
			lblFechaInicio = new JLabel("Fecha inicio:");
			lblFechaInicio.setBounds(12, 37, 96, 16);
		}
		return lblFechaInicio;
	}

	private JLabel getLblFechaFin() {
		if (lblFechaFin == null) {
			lblFechaFin = new JLabel("Fecha fin:");
			lblFechaFin.setBounds(379, 37, 79, 16);
		}
		return lblFechaFin;
	}

	private JLabel getLblLocalizacin() {
		if (lblLocalizacin == null) {
			lblLocalizacin = new JLabel("Localizaci\u00F3n:");
			lblLocalizacin.setBounds(12, 82, 96, 16);
		}
		return lblLocalizacin;
	}
}
