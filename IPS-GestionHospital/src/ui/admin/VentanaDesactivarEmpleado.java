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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import business.JornadaController;
import business.dto.EmpleadoDto;

public class VentanaDesactivarEmpleado extends JFrame {

	private JPanel contentPane;
	private JPanel pnTop;
	private JLabel lblDesactivarUnEmpleado;
	private JPanel pnCenter;
	private JPanel pnEmpleado;
	private JButton btnSeleccionarEmpleado;
	private JTextField txNombreEmpleado;
	private JPanel pnInfoEmpleado;
	private JLabel lblCargo;
	private JTextField txCargo;
	private JLabel lblDni;
	private JTextField txDNI;
	private JLabel lblEmail;
	private JTextField txEmail;
	private JLabel lblEstado;
	private JTextField txEstadoEmpleado;
	private JPanel pnDesactivarEmpleado;

	private CardLayout c;
	private JPanel pnSeleccionarEmpleado;
	private JPanel pnTopS;
	private JLabel lblSeleccionarUnEmpleado;
	private JPanel pnCenterS;
	private JCheckBox chckbxMedico;
	private JCheckBox chckbxEnfermero;
	private JTextField txBuscarPorDNI;
	private JButton btnBuscarPorDni;
	private JTextField txBuscarPorNombre;
	private JButton btnBuscarPorNombre;
	private JList<EmpleadoDto> listEmpleados;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JButton btnDesactivarEmpleado;
	private ButtonGroup buttonGroup = new ButtonGroup();

	DefaultListModel<EmpleadoDto> listModelEnfermeros;
	DefaultListModel<EmpleadoDto> listModelMedicos;

	private JornadaController jc = new JornadaController();

	private EmpleadoDto empleado;

	/**
	 * Create the frame.
	 */
	public VentanaDesactivarEmpleado() {
		c = new CardLayout();
		setTitle("Desactivar empleado");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inicializarListas();
		setBounds(100, 100, 706, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(c);
		setContentPane(contentPane);
		contentPane.add(getPnDesactivarEmpleado(), "pnDesactivar");
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

	private JPanel getPnTop() {
		if (pnTop == null) {
			pnTop = new JPanel();
			pnTop.setLayout(new BorderLayout(0, 0));
			pnTop.add(getLblDesactivarUnEmpleado(), BorderLayout.NORTH);
		}
		return pnTop;
	}

	private JLabel getLblDesactivarUnEmpleado() {
		if (lblDesactivarUnEmpleado == null) {
			lblDesactivarUnEmpleado = new JLabel("Desactivar un empleado");
			lblDesactivarUnEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblDesactivarUnEmpleado;
	}

	private JPanel getPnCenter() {
		if (pnCenter == null) {
			pnCenter = new JPanel();
			pnCenter.setLayout(null);
			pnCenter.add(getPnEmpleado());
			pnCenter.add(getPnInfoEmpleado());
			pnCenter.add(getBtnDesactivarEmpleado());
		}
		return pnCenter;
	}

	private JPanel getPnEmpleado() {
		if (pnEmpleado == null) {
			pnEmpleado = new JPanel();
			pnEmpleado.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Empleado",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnEmpleado.setBounds(34, 35, 607, 73);
			pnEmpleado.setLayout(null);
			pnEmpleado.add(getBtnSeleccionarEmpleado());
			pnEmpleado.add(getTxNombreEmpleado());
		}
		return pnEmpleado;
	}

	private JButton getBtnSeleccionarEmpleado() {
		if (btnSeleccionarEmpleado == null) {
			btnSeleccionarEmpleado = new JButton("Seleccionar empleado");
			btnSeleccionarEmpleado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					c.show(contentPane, "pnSeleccionar");
				}
			});
			btnSeleccionarEmpleado.setBounds(10, 21, 229, 23);
		}
		return btnSeleccionarEmpleado;
	}

	private JTextField getTxNombreEmpleado() {
		if (txNombreEmpleado == null) {
			txNombreEmpleado = new JTextField();
			txNombreEmpleado.setEditable(false);
			txNombreEmpleado.setBounds(286, 22, 271, 20);
			txNombreEmpleado.setColumns(10);
		}
		return txNombreEmpleado;
	}

	private JPanel getPnInfoEmpleado() {
		if (pnInfoEmpleado == null) {
			pnInfoEmpleado = new JPanel();
			pnInfoEmpleado.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
					"Informaci\u00F3n del empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnInfoEmpleado.setBounds(34, 133, 607, 134);
			pnInfoEmpleado.setLayout(null);
			pnInfoEmpleado.add(getLblCargo());
			pnInfoEmpleado.add(getTxCargo());
			pnInfoEmpleado.add(getLblDni());
			pnInfoEmpleado.add(getTxDNI());
			pnInfoEmpleado.add(getLblEmail());
			pnInfoEmpleado.add(getTxEmail());
			pnInfoEmpleado.add(getLblEstado());
			pnInfoEmpleado.add(getTxEstadoEmpleado());
		}
		return pnInfoEmpleado;
	}

	private JLabel getLblCargo() {
		if (lblCargo == null) {
			lblCargo = new JLabel("Cargo:");
			lblCargo.setBounds(10, 27, 66, 14);
		}
		return lblCargo;
	}

	private JTextField getTxCargo() {
		if (txCargo == null) {
			txCargo = new JTextField();
			txCargo.setEditable(false);
			txCargo.setBounds(74, 24, 110, 20);
			txCargo.setColumns(10);
		}
		return txCargo;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setBounds(10, 75, 54, 14);
		}
		return lblDni;
	}

	private JTextField getTxDNI() {
		if (txDNI == null) {
			txDNI = new JTextField();
			txDNI.setEditable(false);
			txDNI.setColumns(10);
			txDNI.setBounds(74, 72, 110, 20);
		}
		return txDNI;
	}

	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email:");
			lblEmail.setBounds(276, 27, 54, 14);
		}
		return lblEmail;
	}

	private JTextField getTxEmail() {
		if (txEmail == null) {
			txEmail = new JTextField();
			txEmail.setEditable(false);
			txEmail.setColumns(10);
			txEmail.setBounds(329, 24, 236, 20);
		}
		return txEmail;
	}

	private JLabel getLblEstado() {
		if (lblEstado == null) {
			lblEstado = new JLabel("Estado:");
			lblEstado.setBounds(276, 75, 54, 14);
		}
		return lblEstado;
	}

	private JTextField getTxEstadoEmpleado() {
		if (txEstadoEmpleado == null) {
			txEstadoEmpleado = new JTextField();
			txEstadoEmpleado.setEditable(false);
			txEstadoEmpleado.setColumns(10);
			txEstadoEmpleado.setBounds(329, 72, 236, 20);
		}
		return txEstadoEmpleado;
	}

	private JPanel getPnDesactivarEmpleado() {
		if (pnDesactivarEmpleado == null) {
			pnDesactivarEmpleado = new JPanel();
			pnDesactivarEmpleado.setLayout(new BorderLayout(0, 0));
			pnDesactivarEmpleado.add(getPnTop(), BorderLayout.NORTH);
			pnDesactivarEmpleado.add(getPnCenter());
		}
		return pnDesactivarEmpleado;
	}

	private JPanel getPnSeleccionarEmpleado() {
		if (pnSeleccionarEmpleado == null) {
			pnSeleccionarEmpleado = new JPanel();
			pnSeleccionarEmpleado.setLayout(new BorderLayout(0, 0));
			pnSeleccionarEmpleado.add(getPnTopS(), BorderLayout.NORTH);
			pnSeleccionarEmpleado.add(getPnCenterS(), BorderLayout.CENTER);
		}
		return pnSeleccionarEmpleado;
	}

	private JPanel getPnTopS() {
		if (pnTopS == null) {
			pnTopS = new JPanel();
			pnTopS.setLayout(new BorderLayout(0, 0));
			pnTopS.add(getLblSeleccionarUnEmpleado());
		}
		return pnTopS;
	}

	private JLabel getLblSeleccionarUnEmpleado() {
		if (lblSeleccionarUnEmpleado == null) {
			lblSeleccionarUnEmpleado = new JLabel("Seleccionar un empleado");
			lblSeleccionarUnEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblSeleccionarUnEmpleado;
	}

	private JPanel getPnCenterS() {
		if (pnCenterS == null) {
			pnCenterS = new JPanel();
			pnCenterS.setLayout(null);
			pnCenterS.add(getChckbxMedico());
			pnCenterS.add(getChckbxEnfermero());
			pnCenterS.add(getTxBuscarPorDNI());
			pnCenterS.add(getBtnBuscarPorDni());
			pnCenterS.add(getTxBuscarPorNombre());
			pnCenterS.add(getBtnBuscarPorNombre());
			pnCenterS.add(getListEmpleados());
			pnCenterS.add(getBtnCancelar());
			pnCenterS.add(getBtnAceptar());
		}
		return pnCenterS;
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
			chckbxMedico.setBounds(23, 33, 87, 25);
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
			chckbxEnfermero.setBounds(140, 33, 113, 25);
		}
		return chckbxEnfermero;
	}

	private JTextField getTxBuscarPorDNI() {
		if (txBuscarPorDNI == null) {
			txBuscarPorDNI = new JTextField();
			txBuscarPorDNI.setColumns(10);
			txBuscarPorDNI.setBounds(23, 97, 113, 20);
		}
		return txBuscarPorDNI;
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
			btnBuscarPorDni.setBounds(162, 96, 124, 23);
		}
		return btnBuscarPorDni;
	}

	private void buscarDNIFiltro() {
		if (chckbxMedico.isSelected()) {
			for (EmpleadoDto em : jc.getMedicos()) {
				if (Integer.parseInt(txBuscarPorDNI.getText()) == em.id) {
					DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
					filtro.addElement(em);
					listEmpleados.setModel(filtro);
				}
			}
		} else if (chckbxEnfermero.isSelected()) {
			for (EmpleadoDto em : jc.getEnfermeros()) {
				if (Integer.parseInt(txBuscarPorDNI.getText()) == em.id) {
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
			txBuscarPorNombre.setBounds(323, 97, 152, 20);
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
			btnBuscarPorNombre.setBounds(500, 96, 147, 23);
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
			listEmpleados.setBounds(23, 128, 624, 140);
		}
		return listEmpleados;
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
			btnCancelar.setBounds(478, 298, 89, 23);
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
						txNombreEmpleado.setText(empleado.nombre);
						txCargo.setText(empleado.cargo);
						txEmail.setText(empleado.correo);
						txDNI.setText(empleado.dni);
						txEstadoEmpleado.setText(empleado.estado);
						c.show(contentPane, "pnDesactivar");
					} else
						JOptionPane.showMessageDialog(contentPane, "Debe seleccionar un empleado al que desactivar",
								"No hay empleado seleccionado", JOptionPane.WARNING_MESSAGE);

				}
			});
			btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAceptar.setBounds(575, 298, 89, 23);
		}
		return btnAceptar;
	}

	private JButton getBtnDesactivarEmpleado() {
		if (btnDesactivarEmpleado == null) {
			btnDesactivarEmpleado = new JButton("Desactivar empleado");
			btnDesactivarEmpleado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (empleado != null) {
						if (!empleado.estado.equals("Desactivado")) {

							empleado.estado = "Desactivado";
							jc.desactivarEmpleado(empleado);
							JOptionPane.showMessageDialog(contentPane, "El empleado ha sido desactivado con éxito",
									"Empleado desactivado", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {

							JOptionPane.showMessageDialog(contentPane, "El empleado ya había sido desactivado previamente.",
									"El empleado está desactivado", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(contentPane,
								"Debe seleccionar un empleado para poder desactivarlo", "No hay empleado seleccionado",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			btnDesactivarEmpleado.setBounds(476, 296, 180, 23);
		}
		return btnDesactivarEmpleado;
	}
}
