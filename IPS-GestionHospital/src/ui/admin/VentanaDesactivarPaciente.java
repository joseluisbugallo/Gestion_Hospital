package ui.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import business.LogController;
import business.PacientesController;
import business.dto.CambioDto;
import business.dto.PacienteDto;

public class VentanaDesactivarPaciente extends JFrame {

	private JPanel contentPane;
	private JLabel lblDesactivarPacienteDel;
	private JPanel pnDesactivar;

	private CardLayout c;
	private JPanel pnCenterDesactivar;
	private JPanel pnPaciente;
	private JButton btnSeleccionarPaciente;
	private JPanel pnInfo;
	private JLabel lblNombre;
	private JTextField txNombre;
	private JLabel label_1;
	private JTextField txDni;
	private JLabel lblContacto;
	private JTextField txContacto;
	private JButton btnDesactivarPaciente;
	private JPanel pnSeleccionar;
	private JLabel lblSeleccionarPacientePara;
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

	private PacienteDto paciente;

	/**
	 * Create the frame.
	 */
	public VentanaDesactivarPaciente() {
		c = new CardLayout();
		setTitle("Desactivar paciente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 654, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(c);
		inicializarListas();
		setContentPane(contentPane);
		contentPane.add(getPnDesactivar(), "pnDesactivar");
		contentPane.add(getPnSeleccionar(), "pnSeleccion");
	}

	private void inicializarListas() {
		listModelPacientes = new DefaultListModel<PacienteDto>();
		for (PacienteDto paciente : pc.listadoPacientes()) {
			listModelPacientes.addElement(paciente);
		}
	}

	private JLabel getLblDesactivarPacienteDel() {
		if (lblDesactivarPacienteDel == null) {
			lblDesactivarPacienteDel = new JLabel("Desactivar paciente del centro de salud actual");
			lblDesactivarPacienteDel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblDesactivarPacienteDel;
	}

	private JPanel getPnDesactivar() {
		if (pnDesactivar == null) {
			pnDesactivar = new JPanel();
			pnDesactivar.setLayout(new BorderLayout(0, 0));
			pnDesactivar.add(getLblDesactivarPacienteDel(), BorderLayout.NORTH);
			pnDesactivar.add(getPanel_1(), BorderLayout.CENTER);
		}
		return pnDesactivar;
	}

	private JPanel getPanel_1() {
		if (pnCenterDesactivar == null) {
			pnCenterDesactivar = new JPanel();
			pnCenterDesactivar.setLayout(null);
			pnCenterDesactivar.add(getPnPaciente());
			pnCenterDesactivar.add(getPnInfo());
			pnCenterDesactivar.add(getBtnDesactivarPaciente());
		}
		return pnCenterDesactivar;
	}

	private JPanel getPnPaciente() {
		if (pnPaciente == null) {
			pnPaciente = new JPanel();
			pnPaciente.setLayout(null);
			pnPaciente.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Paciente",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnPaciente.setBounds(10, 26, 271, 73);
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
					CambioDto cambio = new CambioDto();
					cambio.cambio = "El administrador ha listado todos los pacientes" ;
					cambio.fecha = new Date();					
					LogController lc = new LogController();
					lc.añadirCambio(cambio);
				}
			});
			btnSeleccionarPaciente.setBounds(10, 21, 229, 23);
		}
		return btnSeleccionarPaciente;
	}

	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setLayout(null);
			pnInfo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
					"Informaci\u00F3n del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnInfo.setBounds(10, 110, 607, 121);
			pnInfo.add(getLblNombre());
			pnInfo.add(getTxNombre());
			pnInfo.add(getLabel_1());
			pnInfo.add(getTxDni());
			pnInfo.add(getLblContacto());
			pnInfo.add(getTxContacto());
		}
		return pnInfo;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 27, 54, 14);
		}
		return lblNombre;
	}

	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setEditable(false);
			txNombre.setColumns(10);
			txNombre.setBounds(74, 24, 192, 20);
		}
		return txNombre;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("DNI:");
			label_1.setBounds(10, 75, 54, 14);
		}
		return label_1;
	}

	private JTextField getTxDni() {
		if (txDni == null) {
			txDni = new JTextField();
			txDni.setEditable(false);
			txDni.setColumns(10);
			txDni.setBounds(74, 72, 110, 20);
		}
		return txDni;
	}

	private JLabel getLblContacto() {
		if (lblContacto == null) {
			lblContacto = new JLabel("Contacto:");
			lblContacto.setBounds(276, 27, 54, 14);
		}
		return lblContacto;
	}

	private JTextField getTxContacto() {
		if (txContacto == null) {
			txContacto = new JTextField();
			txContacto.setEditable(false);
			txContacto.setColumns(10);
			txContacto.setBounds(329, 24, 236, 20);
		}
		return txContacto;
	}

	private JButton getBtnDesactivarPaciente() {
		if (btnDesactivarPaciente == null) {
			btnDesactivarPaciente = new JButton("Desactivar paciente");
			btnDesactivarPaciente.setEnabled(false);
			btnDesactivarPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (paciente != null) {
						if (paciente.estado.equals("Activado")) {
							paciente.estado = "Desactivado";
							pc.desactivarPaciente(paciente);
							JOptionPane.showMessageDialog(contentPane, "El paciente ha sido desactivado con éxito",
									"Paciente desactivado", JOptionPane.INFORMATION_MESSAGE);
							
							CambioDto cambio = new CambioDto();
							cambio.cambio = "El administrador ha desactivado al paciente con id: "+paciente.id ;
							cambio.fecha = new Date();					
							LogController lc = new LogController();
							lc.añadirCambio(cambio);
							dispose();
						} else {
							JOptionPane.showMessageDialog(contentPane,
									"El paciente ya había sido desactivado previamente.",
									"El paciente está desactivado", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(contentPane,
								"Debe seleccionar un paciente para desactivarlo.",
								"No hay paciente seleccionado", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnDesactivarPaciente.setBounds(408, 242, 209, 23);
		}
		return btnDesactivarPaciente;
	}

	private JPanel getPnSeleccionar() {
		if (pnSeleccionar == null) {
			pnSeleccionar = new JPanel();
			pnSeleccionar.setLayout(new BorderLayout(0, 0));
			pnSeleccionar.add(getLblSeleccionarPacientePara(), BorderLayout.NORTH);
			pnSeleccionar.add(getPnCenterSeleccion(), BorderLayout.CENTER);
		}
		return pnSeleccionar;
	}

	private JLabel getLblSeleccionarPacientePara() {
		if (lblSeleccionarPacientePara == null) {
			lblSeleccionarPacientePara = new JLabel("Seleccionar paciente para la desactivaci\u00F3n");
			lblSeleccionarPacientePara.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblSeleccionarPacientePara;
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
			txBuscarPorDni.setBounds(10, 26, 114, 20);
		}
		return txBuscarPorDni;
	}

	private JButton getBtnBuscarPorDni() {
		if (btnBuscarPorDni == null) {
			btnBuscarPorDni = new JButton("Buscar por DNI");
			btnBuscarPorDni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarDNIPacientesFiltro();
				}
			});
			btnBuscarPorDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBuscarPorDni.setBounds(136, 25, 129, 23);
		}
		return btnBuscarPorDni;
	}

	private void buscarDNIPacientesFiltro() {

		for (PacienteDto em : pc.listadoPacientes()) {
			if (txBuscarPorDni.getText().toLowerCase().equals(em.dni.toLowerCase())) {
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
			txBuscarPorNombre.setBounds(280, 26, 136, 20);
		}
		return txBuscarPorNombre;
	}

	private JButton getBtnBuscarPorNombre() {
		if (btnBuscarPorNombre == null) {
			btnBuscarPorNombre = new JButton("Buscar por nombre");
			btnBuscarPorNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarNombrePacientesFiltro();
				}
			});
			btnBuscarPorNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBuscarPorNombre.setBounds(431, 25, 141, 23);
		}
		return btnBuscarPorNombre;
	}

	private void buscarNombrePacientesFiltro() {

		for (PacienteDto em : pc.listadoPacientes()) {
			if (txBuscarPorNombre.getText().toLowerCase().equals(em.nombre.toLowerCase())) {
				DefaultListModel<PacienteDto> filtro = new DefaultListModel<PacienteDto>();
				filtro.addElement(em);
				listPacientes.setModel(filtro);
			}
		}
	}

	private JList<PacienteDto> getListPacientes() {
		if (listPacientes == null) {
			listPacientes = new JList<PacienteDto>(listModelPacientes);
			listPacientes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listPacientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					PacienteDto seleccion = listPacientes.getSelectedValue();
					if (seleccion != null) {
						getBtnAceptar().setEnabled(true);
					}
				}
			});
			listPacientes.setBounds(10, 72, 560, 138);
		}
		return listPacientes;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.show(contentPane, "pnDesactivar");
				}
			});
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCancelar.setBounds(431, 237, 89, 23);
		}
		return btnCancelar;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (listPacientes.getSelectedValue() != null) {
						paciente = listPacientes.getSelectedValue();
						txNombre.setText(paciente.nombre);
						txDni.setText(paciente.dni);
						txContacto.setText(paciente.contacto);
						btnDesactivarPaciente.setEnabled(true);
						c.show(contentPane, "pnDesactivar");
					}

				}
			});
			btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAceptar.setEnabled(false);
			btnAceptar.setBounds(529, 238, 89, 23);
		}
		return btnAceptar;
	}
}
