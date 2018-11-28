package ui.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import business.CitasController;
import business.JornadaController;
import business.PacientesController;
import business.dto.CitaDto;
import business.dto.EmpleadoDto;
import business.dto.PacienteDto;

public class VentanaActividadEmpleados extends JFrame {

	private JPanel contentPane;

	private CardLayout c;
	private JPanel pnActividad;
	private JPanel pnSeleccionarEmpleado;
	private JLabel lblActividadDeLos;
	private JPanel pnCenterActividad;
	private JPanel pnEmpleado;
	private JButton btnSeleccionarEmpleado;
	private JPanel pnInfoEmpleado;
	private JLabel lblNombre;
	private JTextField txNombre;
	private JLabel lblCargo;
	private JTextField txCargo;
	private JLabel lblDNI;
	private JTextField txDNI;
	private JLabel lblEstado;
	private JTextField txEstado;
	private JLabel lblCorreo;
	private JTextField txCorreo;
	private JPanel pnActividadTabla;
	private JButton btnSalir;
	private JLabel lblSeleccionarEmpleado;
	private JPanel panel;
	private JCheckBox chckbxMedico;
	private JCheckBox chckbxEnfermero;
	private JTextField txBuscarPorDNI;
	private JButton btnBuscarPorDNI;
	private JTextField txBuscarPorNombre;
	private JButton btnBuscarPorNombre;
	private JList<EmpleadoDto> listEmpleados;
	private JButton btnCancelar;
	private JButton btnAceptar;

	private ButtonGroup buttonGroup = new ButtonGroup();

	DefaultListModel<EmpleadoDto> listModelEnfermeros;
	DefaultListModel<EmpleadoDto> listModelMedicos;

	private JornadaController jc = new JornadaController();
	private CitasController cc = new CitasController();
	private PacientesController pc = new PacientesController();

	private EmpleadoDto empleado;
	DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public VentanaActividadEmpleados() {
		c = new CardLayout();
		setTitle("Actividad de los empleados");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inicializarListas();
		setBounds(100, 100, 750, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(c);
		setContentPane(contentPane);
		contentPane.add(getPnActividad(), "pnActividad");
		contentPane.add(getPnSeleccionarEmpleado(), "pnSeleccionar");
	}

	private void inicializarListas() {
		listModelEnfermeros = new DefaultListModel<EmpleadoDto>();
		for (EmpleadoDto paciente : jc.getEnfermeros()) {
			listModelEnfermeros.addElement(paciente);
		}
		listModelMedicos = new DefaultListModel<EmpleadoDto>();
		for (EmpleadoDto empleado : jc.getMedicos()) {
			listModelMedicos.addElement(empleado);
		}
	}

	private JPanel getPnActividad() {
		if (pnActividad == null) {
			pnActividad = new JPanel();
			pnActividad.setLayout(new BorderLayout(0, 0));
			pnActividad.add(getLblActividadDeLos(), BorderLayout.NORTH);
			pnActividad.add(getPnCenterActividad(), BorderLayout.CENTER);
		}
		return pnActividad;
	}

	private JPanel getPnSeleccionarEmpleado() {
		if (pnSeleccionarEmpleado == null) {
			pnSeleccionarEmpleado = new JPanel();
			pnSeleccionarEmpleado.setLayout(new BorderLayout(0, 0));
			pnSeleccionarEmpleado.add(getLblSeleccionarEmpleado(), BorderLayout.NORTH);
			pnSeleccionarEmpleado.add(getPanel(), BorderLayout.CENTER);
		}
		return pnSeleccionarEmpleado;
	}

	private JLabel getLblActividadDeLos() {
		if (lblActividadDeLos == null) {
			lblActividadDeLos = new JLabel("Actividad de los empleados");
			lblActividadDeLos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblActividadDeLos;
	}

	private JPanel getPnCenterActividad() {
		if (pnCenterActividad == null) {
			pnCenterActividad = new JPanel();
			pnCenterActividad.setLayout(null);
			pnCenterActividad.add(getPnEmpleado());
			pnCenterActividad.add(getPnInfoEmpleado());
			pnCenterActividad.add(getPnActividadTabla());
			pnCenterActividad.add(getBtnSalir());
		}
		return pnCenterActividad;
	}

	private JPanel getPnEmpleado() {
		if (pnEmpleado == null) {
			pnEmpleado = new JPanel();
			pnEmpleado.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Empleado",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnEmpleado.setBounds(28, 29, 262, 140);
			pnEmpleado.add(getBtnSeleccionarEmpleado());
		}
		return pnEmpleado;
	}

	private JButton getBtnSeleccionarEmpleado() {
		if (btnSeleccionarEmpleado == null) {
			btnSeleccionarEmpleado = new JButton("Seleccionar empleado");
			btnSeleccionarEmpleado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.show(contentPane, "pnSeleccionar");
				}
			});
		}
		return btnSeleccionarEmpleado;
	}

	private JPanel getPnInfoEmpleado() {
		if (pnInfoEmpleado == null) {
			pnInfoEmpleado = new JPanel();
			pnInfoEmpleado.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
					"Informaci\u00F3n del empleado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnInfoEmpleado.setBounds(318, 29, 383, 140);
			pnInfoEmpleado.setLayout(null);
			pnInfoEmpleado.add(getLblNombre());
			pnInfoEmpleado.add(getTxNombre());
			pnInfoEmpleado.add(getLblCargo());
			pnInfoEmpleado.add(getTxCargo());
			pnInfoEmpleado.add(getLblDNI());
			pnInfoEmpleado.add(getTxDNI());
			pnInfoEmpleado.add(getLblEstado());
			pnInfoEmpleado.add(getTxEstado());
			pnInfoEmpleado.add(getLblCorreo());
			pnInfoEmpleado.add(getTxCorreo());
		}
		return pnInfoEmpleado;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 30, 56, 14);
		}
		return lblNombre;
	}

	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setEditable(false);
			txNombre.setBounds(69, 27, 122, 20);
			txNombre.setColumns(10);
		}
		return txNombre;
	}

	private JLabel getLblCargo() {
		if (lblCargo == null) {
			lblCargo = new JLabel("Cargo:");
			lblCargo.setBounds(10, 64, 56, 14);
		}
		return lblCargo;
	}

	private JTextField getTxCargo() {
		if (txCargo == null) {
			txCargo = new JTextField();
			txCargo.setEditable(false);
			txCargo.setColumns(10);
			txCargo.setBounds(69, 61, 122, 20);
		}
		return txCargo;
	}

	private JLabel getLblDNI() {
		if (lblDNI == null) {
			lblDNI = new JLabel("DNI:");
			lblDNI.setBounds(201, 30, 37, 14);
		}
		return lblDNI;
	}

	private JTextField getTxDNI() {
		if (txDNI == null) {
			txDNI = new JTextField();
			txDNI.setEditable(false);
			txDNI.setColumns(10);
			txDNI.setBounds(248, 27, 122, 20);
		}
		return txDNI;
	}

	private JLabel getLblEstado() {
		if (lblEstado == null) {
			lblEstado = new JLabel("Estado:");
			lblEstado.setBounds(201, 64, 47, 14);
		}
		return lblEstado;
	}

	private JTextField getTxEstado() {
		if (txEstado == null) {
			txEstado = new JTextField();
			txEstado.setEditable(false);
			txEstado.setColumns(10);
			txEstado.setBounds(248, 61, 122, 20);
		}
		return txEstado;
	}

	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo:");
			lblCorreo.setBounds(10, 102, 56, 14);
		}
		return lblCorreo;
	}

	private JTextField getTxCorreo() {
		if (txCorreo == null) {
			txCorreo = new JTextField();
			txCorreo.setEditable(false);
			txCorreo.setColumns(10);
			txCorreo.setBounds(69, 99, 202, 20);
		}
		return txCorreo;
	}

	private JPanel getPnActividadTabla() {
		if (pnActividadTabla == null) {
			pnActividadTabla = new JPanel();
			pnActividadTabla.setBounds(28, 194, 672, 140);
			pnActividadTabla.setLayout(new BorderLayout(0, 0));
			pnActividadTabla.add(getScrollPane(), BorderLayout.CENTER);
		}
		return pnActividadTabla;
	}

	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnSalir.setBounds(612, 350, 89, 23);
		}
		return btnSalir;
	}

	private JLabel getLblSeleccionarEmpleado() {
		if (lblSeleccionarEmpleado == null) {
			lblSeleccionarEmpleado = new JLabel("Seleccionar empleado");
			lblSeleccionarEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblSeleccionarEmpleado;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getChckbxMedico());
			panel.add(getChckbxEnfermero());
			panel.add(getTxBuscarPorDNI());
			panel.add(getBtnBuscarPorDNI());
			panel.add(getTxBuscarPorNombre());
			panel.add(getBtnBuscarPorNombre());
			panel.add(getListEmpleados());
			panel.add(getBtnCancelar());
			panel.add(getBtnAceptar());
		}
		return panel;
	}

	private JCheckBox getChckbxMedico() {
		if (chckbxMedico == null) {
			chckbxMedico = new JCheckBox("M\u00E9dico");
			chckbxMedico.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if (chckbxMedico.isSelected()) {
						listEmpleados.setModel(listModelMedicos);
					}
				}
			});
			buttonGroup.add(chckbxMedico);
			chckbxMedico.setBounds(29, 42, 87, 25);
		}
		return chckbxMedico;
	}

	private JCheckBox getChckbxEnfermero() {
		if (chckbxEnfermero == null) {
			chckbxEnfermero = new JCheckBox("Enfermero");
			chckbxEnfermero.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if (chckbxEnfermero.isSelected()) {
						listEmpleados.setModel(listModelEnfermeros);
					}
				}
			});
			buttonGroup.add(chckbxEnfermero);
			chckbxEnfermero.setBounds(146, 43, 113, 25);
		}
		return chckbxEnfermero;
	}

	private JTextField getTxBuscarPorDNI() {
		if (txBuscarPorDNI == null) {
			txBuscarPorDNI = new JTextField();
			txBuscarPorDNI.setColumns(10);
			txBuscarPorDNI.setBounds(29, 104, 113, 20);
		}
		return txBuscarPorDNI;
	}

	private JButton getBtnBuscarPorDNI() {
		if (btnBuscarPorDNI == null) {
			btnBuscarPorDNI = new JButton("Buscar por DNI");
			btnBuscarPorDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBuscarPorDNI.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarDNIFiltro();
				}

			});
			btnBuscarPorDNI.setBounds(169, 103, 124, 23);
		}
		return btnBuscarPorDNI;
	}

	private void buscarDNIFiltro() {
		if (chckbxMedico.isSelected()) {
			for (EmpleadoDto em : jc.getMedicos()) {
				if (txBuscarPorDNI.getText().equals(em.dni)) {
					DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
					filtro.addElement(em);
					listEmpleados.setModel(filtro);
				}
			}
		} else if (chckbxEnfermero.isSelected()) {
			for (EmpleadoDto em : jc.getEnfermeros()) {
				if (txBuscarPorDNI.getText().equals(em.dni)) {
					DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
					filtro.addElement(em);
					listEmpleados.setModel(filtro);
				}
			}
		}
	}

	private JTextField getTxBuscarPorNombre() {
		if (txBuscarPorNombre == null) {
			txBuscarPorNombre = new JTextField();
			txBuscarPorNombre.setColumns(10);
			txBuscarPorNombre.setBounds(324, 104, 152, 20);
		}
		return txBuscarPorNombre;
	}

	private JButton getBtnBuscarPorNombre() {
		if (btnBuscarPorNombre == null) {
			btnBuscarPorNombre = new JButton("Buscar por nombre");
			btnBuscarPorNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBuscarPorNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarPorNombreFiltro();
				}
			});
			btnBuscarPorNombre.setBounds(504, 103, 147, 23);
		}
		return btnBuscarPorNombre;
	}

	private void buscarPorNombreFiltro() {
		if (chckbxMedico.isSelected()) {
			for (EmpleadoDto em : jc.getMedicos()) {
				if (txBuscarPorNombre.getText().equals(em.nombre)) {
					DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
					filtro.addElement(em);
					listEmpleados.setModel(filtro);
				}
			}
		} else if (chckbxEnfermero.isSelected()) {
			for (EmpleadoDto em : jc.getEnfermeros()) {
				if (txBuscarPorNombre.getText().equals(em.nombre)) {
					DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
					filtro.addElement(em);
					listEmpleados.setModel(filtro);
				}
			}
		}

	}

	private JList<EmpleadoDto> getListEmpleados() {
		if (listEmpleados == null) {
			listEmpleados = new JList<EmpleadoDto>();
			listEmpleados.setBounds(29, 155, 624, 161);
		}
		return listEmpleados;
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
			btnCancelar.setBounds(519, 338, 89, 23);
		}
		return btnCancelar;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (listEmpleados.getSelectedValue() != null) {
						empleado = listEmpleados.getSelectedValue();
						txNombre.setText(empleado.nombre);
						txCargo.setText(empleado.cargo);
						txCorreo.setText(empleado.correo);
						txDNI.setText(empleado.dni);
						txEstado.setText(empleado.estado);
						cargarTabla();
						c.show(contentPane, "pnActividad");
						mostrarTodosMedicos();
					} else
						JOptionPane.showMessageDialog(contentPane, "Debe seleccionar un empleado para ver su actividad",
								"No hay empleado seleccionado", JOptionPane.WARNING_MESSAGE);
				}
			});
			btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAceptar.setBounds(618, 339, 89, 23);
		}
		return btnAceptar;
	}
	
	private void mostrarTodosMedicos() {
		DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
		for (EmpleadoDto em : jc.getMedicos()) {
			if (em.estado.equals("Activado"))
				filtro.addElement(em);
		}
		listEmpleados.setModel(filtro);

	}

	private void cargarTabla() {
		model = new DefaultTableModel();
		model.addColumn("Actividad");
		model.addColumn("Fecha inicio");
		model.addColumn("Fecha fin");
		model.addColumn("Nombre paciente");		
		for (CitaDto cita : cc.getListadoCitas(empleado)) {
			PacienteDto paciente = pc.findPacientesById(cita.idPaciente);
			model.addRow(new Object[] { "Cita", cita.fechainicio, cita.fechafin, paciente.nombre });
		}
		table.setModel(model);
		

	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
		}
		return table;
	}
}
