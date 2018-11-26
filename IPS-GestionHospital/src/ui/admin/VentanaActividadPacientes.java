package ui.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import business.CitasController;
import business.JornadaController;
import business.PacientesController;
import business.VacunaController;
import business.dto.CitaDto;
import business.dto.EmpleadoDto;
import business.dto.PacienteDto;
import business.dto.VacunaDto;

public class VentanaActividadPacientes extends JFrame {

	private JPanel contentPane;

	private CardLayout c;
	private JPanel pnActividad;
	private JLabel lblActividad;
	private JPanel pnCenterActividad;
	private JPanel pnPaciente;
	private JButton btnSeleccionarPaciente;
	private JPanel pnInfoPaciente;
	private JLabel label;
	private JTextField txNombre;
	private JLabel lblDNI;
	private JTextField txDni;
	private JLabel lblEstado;
	private JTextField txEstado;
	private JLabel lblContacto;
	private JTextField txContacto;
	private JScrollPane scActividad;
	private JTable table;
	private JButton btnSalir;
	private JPanel pnSeleccion;
	private JLabel lblSeleccionarPaciente;
	private JPanel pnCenterSeleccion;
	private JTextField txBuscarPorDni;
	private JButton btnBuscarPorDni;
	private JTextField txBuscarPorNombre;
	private JButton btnBuscarPorNombre;
	private JList<PacienteDto> listPacientes;
	private JButton btnCancelar;
	private JButton btnAceptar;

	DefaultListModel<PacienteDto> listModelPacientes;
	private PacientesController pc = new PacientesController();
	private CitasController cc = new CitasController();
	private VacunaController vc = new VacunaController();
	private JornadaController jc = new JornadaController();
	private PacienteDto paciente;
	DefaultTableModel model = new DefaultTableModel();

	/**
	 * Create the frame.
	 */
	public VentanaActividadPacientes() {
		c = new CardLayout();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 729, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		inicializarListas();
		contentPane.setLayout(c);
		setContentPane(contentPane);
		contentPane.add(getPnActividad(), "pnActividad");
		contentPane.add(getPnSeleccion(), "pnSeleccion");
	}

	private void inicializarListas() {
		listModelPacientes = new DefaultListModel<PacienteDto>();
		for (PacienteDto paciente : pc.listadoPacientes()) {
			listModelPacientes.addElement(paciente);
		}
	}

	private JPanel getPnActividad() {
		if (pnActividad == null) {
			pnActividad = new JPanel();
			pnActividad.setLayout(new BorderLayout(0, 0));
			pnActividad.add(getLblActividad(), BorderLayout.NORTH);
			pnActividad.add(getPnCenterActividad(), BorderLayout.CENTER);
		}
		return pnActividad;
	}

	private JLabel getLblActividad() {
		if (lblActividad == null) {
			lblActividad = new JLabel("Actividad de los pacientes");
			lblActividad.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblActividad;
	}

	private JPanel getPnCenterActividad() {
		if (pnCenterActividad == null) {
			pnCenterActividad = new JPanel();
			pnCenterActividad.setLayout(null);
			pnCenterActividad.add(getPnPaciente());
			pnCenterActividad.add(getPnInfoPaciente());
			pnCenterActividad.add(getScActividad());
			pnCenterActividad.add(getBtnSalir());
		}
		return pnCenterActividad;
	}

	private JPanel getPnPaciente() {
		if (pnPaciente == null) {
			pnPaciente = new JPanel();
			pnPaciente.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Paciente",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnPaciente.setBounds(22, 11, 262, 140);
			pnPaciente.add(getBtnSeleccionarPaciente());
		}
		return pnPaciente;
	}

	private JButton getBtnSeleccionarPaciente() {
		if (btnSeleccionarPaciente == null) {
			btnSeleccionarPaciente = new JButton("Seleccionar paciente");
			btnSeleccionarPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.show(contentPane, "pnSeleccion");
				}
			});
		}
		return btnSeleccionarPaciente;
	}

	private JPanel getPnInfoPaciente() {
		if (pnInfoPaciente == null) {
			pnInfoPaciente = new JPanel();
			pnInfoPaciente.setLayout(null);
			pnInfoPaciente.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
					"Informaci\u00F3n del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnInfoPaciente.setBounds(294, 11, 383, 140);
			pnInfoPaciente.add(getLabel());
			pnInfoPaciente.add(getTxNombre());
			pnInfoPaciente.add(getLblDNI());
			pnInfoPaciente.add(getTxDni());
			pnInfoPaciente.add(getLblEstado());
			pnInfoPaciente.add(getTxEstado());
			pnInfoPaciente.add(getLblContacto());
			pnInfoPaciente.add(getTxContacto());
		}
		return pnInfoPaciente;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Nombre:");
			label.setBounds(10, 30, 56, 14);
		}
		return label;
	}

	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setEditable(false);
			txNombre.setColumns(10);
			txNombre.setBounds(69, 27, 304, 20);
		}
		return txNombre;
	}

	private JLabel getLblDNI() {
		if (lblDNI == null) {
			lblDNI = new JLabel("DNI:");
			lblDNI.setBounds(206, 64, 37, 14);
		}
		return lblDNI;
	}

	private JTextField getTxDni() {
		if (txDni == null) {
			txDni = new JTextField();
			txDni.setEditable(false);
			txDni.setColumns(10);
			txDni.setBounds(251, 61, 122, 20);
		}
		return txDni;
	}

	private JLabel getLblEstado() {
		if (lblEstado == null) {
			lblEstado = new JLabel("Estado:");
			lblEstado.setBounds(10, 64, 47, 14);
		}
		return lblEstado;
	}

	private JTextField getTxEstado() {
		if (txEstado == null) {
			txEstado = new JTextField();
			txEstado.setEditable(false);
			txEstado.setColumns(10);
			txEstado.setBounds(69, 61, 122, 20);
		}
		return txEstado;
	}

	private JLabel getLblContacto() {
		if (lblContacto == null) {
			lblContacto = new JLabel("Contacto:");
			lblContacto.setBounds(10, 102, 56, 14);
		}
		return lblContacto;
	}

	private JTextField getTxContacto() {
		if (txContacto == null) {
			txContacto = new JTextField();
			txContacto.setEditable(false);
			txContacto.setColumns(10);
			txContacto.setBounds(69, 99, 202, 20);
		}
		return txContacto;
	}

	private JScrollPane getScActividad() {
		if (scActividad == null) {
			scActividad = new JScrollPane();
			scActividad.setBounds(10, 176, 672, 140);
			scActividad.setViewportView(getTable());
		}
		return scActividad;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
		}
		return table;
	}

	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnSalir.setBounds(593, 341, 89, 23);
		}
		return btnSalir;
	}

	private JPanel getPnSeleccion() {
		if (pnSeleccion == null) {
			pnSeleccion = new JPanel();
			pnSeleccion.setLayout(new BorderLayout(0, 0));
			pnSeleccion.add(getLblSeleccionarPaciente(), BorderLayout.NORTH);
			pnSeleccion.add(getPnCenterSeleccion(), BorderLayout.CENTER);
		}
		return pnSeleccion;
	}

	private JLabel getLblSeleccionarPaciente() {
		if (lblSeleccionarPaciente == null) {
			lblSeleccionarPaciente = new JLabel("Seleccionar paciente");
			lblSeleccionarPaciente.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblSeleccionarPaciente;
	}

	private JPanel getPnCenterSeleccion() {
		if (pnCenterSeleccion == null) {
			pnCenterSeleccion = new JPanel();
			pnCenterSeleccion.setLayout(null);
			pnCenterSeleccion.add(getTxBuscarPorDni());
			pnCenterSeleccion.add(getBtnBuscarPorDni());
			pnCenterSeleccion.add(getTxBuscarPorNombre());
			pnCenterSeleccion.add(getBtnBuscarPorNombre());
			pnCenterSeleccion.add(getListPacientes());
			pnCenterSeleccion.add(getBtnCancelar());
			pnCenterSeleccion.add(getBtnAceptar());
		}
		return pnCenterSeleccion;
	}

	private JTextField getTxBuscarPorDni() {
		if (txBuscarPorDni == null) {
			txBuscarPorDni = new JTextField();
			txBuscarPorDni.setColumns(10);
			txBuscarPorDni.setBounds(21, 33, 113, 20);
		}
		return txBuscarPorDni;
	}

	private JButton getBtnBuscarPorDni() {
		if (btnBuscarPorDni == null) {
			btnBuscarPorDni = new JButton("Buscar por DNI");
			btnBuscarPorDni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarDNIFiltro();
				}
			});
			btnBuscarPorDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBuscarPorDni.setBounds(144, 32, 124, 23);
		}
		return btnBuscarPorDni;
	}

	private void buscarDNIFiltro() {

		for (PacienteDto em : pc.listadoPacientes()) {
			if (txBuscarPorDni.getText().equals(em.dni) && em.estado.equals("Activado")) {
				DefaultListModel<PacienteDto> filtro = new DefaultListModel<PacienteDto>();
				filtro.addElement(em);
				listPacientes.setModel(filtro);
			}
		}
	}

	private JTextField getTxBuscarPorNombre() {
		if (txBuscarPorNombre == null) {
			txBuscarPorNombre = new JTextField();
			txBuscarPorNombre.setColumns(10);
			txBuscarPorNombre.setBounds(298, 33, 152, 20);
		}
		return txBuscarPorNombre;
	}

	private JButton getBtnBuscarPorNombre() {
		if (btnBuscarPorNombre == null) {
			btnBuscarPorNombre = new JButton("Buscar por nombre");
			btnBuscarPorNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarPorNombreFiltro();
				}
			});
			btnBuscarPorNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBuscarPorNombre.setBounds(467, 32, 147, 23);
		}
		return btnBuscarPorNombre;
	}

	private void buscarPorNombreFiltro() {

		for (PacienteDto em : pc.listadoPacientes()) {
			if (txBuscarPorNombre.getText().equals(em.nombre) && em.estado.equals("Activado")) {
				DefaultListModel<PacienteDto> filtro = new DefaultListModel<PacienteDto>();
				filtro.addElement(em);
				listPacientes.setModel(filtro);
			}
		}
	}

	private JList<PacienteDto> getListPacientes() {
		if (listPacientes == null) {
			listPacientes = new JList<PacienteDto>(listModelPacientes);
			listPacientes.setBounds(21, 77, 624, 202);
		}
		return listPacientes;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.show(contentPane, "pnActividad");
				}
			});
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCancelar.setBounds(496, 319, 89, 23);
		}
		return btnCancelar;
	}

	private void mostrarTodosPacientes() {
		DefaultListModel<PacienteDto> filtro = new DefaultListModel<PacienteDto>();
		for (PacienteDto em : pc.listadoPacientes()) {
			if (em.estado.equals("Activado"))
				filtro.addElement(em);
		}
		listPacientes.setModel(filtro);

	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (listPacientes.getSelectedValue() != null) {
						paciente = listPacientes.getSelectedValue();
						txNombre.setText(paciente.nombre);
						txContacto.setText(paciente.contacto);
						txDni.setText(paciente.dni);
						txEstado.setText(paciente.estado);
						cargarTabla();
						c.show(contentPane, "pnActividad");
						mostrarTodosPacientes();
					} else
						JOptionPane.showMessageDialog(contentPane, "Debe seleccionar un paciente para ver su actividad",
								"No hay paciente seleccionado", JOptionPane.WARNING_MESSAGE);
				}
			});
			btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAceptar.setBounds(595, 320, 89, 23);
		}
		return btnAceptar;
	}

	private String getMedicosPorCita(CitaDto cita) {
		List<EmpleadoDto> medicos = new ArrayList<EmpleadoDto>();
		for (CitaDto citaR : cc.getCitasByIDPaciente(paciente)) {
			if (cita.urgente == citaR.urgente && cita.idPaciente == citaR.idPaciente && cita.sala.equals(citaR.sala)
					&& cita.fechainicio.equals(citaR.fechainicio) && cita.fechafin.equals(citaR.fechafin)) {
				medicos.add(cc.getMedicoByID(cita.idEmpleado));
				if (cita.idEmpleado != citaR.idEmpleado)
					medicos.add(cc.getMedicoByID(citaR.idEmpleado));

			}
		}
		System.out.println(medicos);
		String medicosNombres = "";
		if (medicos.size() > 1) {
			for (EmpleadoDto em : medicos) {
				if (!medicosNombres.contains(em.nombre))
					medicosNombres += em.nombre + ", ";
			}
		} else
			medicosNombres = medicos.get(0).nombre;

		return medicosNombres;

	}

	private void cargarTabla() {
		model = new DefaultTableModel();
		model.addColumn("Actividad");
		model.addColumn("Fecha inicio");
		model.addColumn("Fecha fin");
		model.addColumn("Nombre médico(s)");
		for (CitaDto cita : cc.getCitasByIDPaciente(paciente)) {
			model.addRow(new Object[] { "Cita", cita.fechainicio, cita.fechafin, getMedicosPorCita(cita) });
		}
		for (VacunaDto vacuna : vc.getVacunasPaciente(paciente.id)) {
			model.addRow(
					new Object[] { "Vacuna: " + vacuna.vacuna, vacuna.fechainicio, vacuna.fechafin, paciente.nombre });
		}
		table.setModel(model);

	}
}
