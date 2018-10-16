package ui.admin;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import business.CitasController;
import business.CorreoElectronico;
import business.JornadaController;
import business.PacientesController;
import business.dto.CitaDto;
import business.dto.EmpleadoDto;
import business.dto.PacienteDto;

public class VentanaFijarCita extends JFrame {

	private JPanel pnBase;

	DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");

	private CardLayout c;
	private JPanel pnFijarCita;
	private JLabel label;
	private JLabel label_1;
	private JDateChooser dcInicio;
	private JLabel label_2;
	private JDateChooser dcFin;
	private JLabel label_3;
	private JButton btnSeleccionarPaciente;
	private JTextField txPaciente;
	private JLabel label_4;
	private JButton btnSeleccionarMedico;
	private JCheckBox chckbxUrgente;
	private JLabel label_5;
	private JTextField txUbicacion;
	private JButton btCancelar;
	private JButton btCrear;
	private JTextArea txAreaMedicos;
	private JPanel pnSeleccionarPaciente;
	private JLabel lblSeleccionarPacientePara;
	private JList<PacienteDto> listPacientes;
	private JTextField txDniPaciente;
	private JButton btnBuscarPorDniPacientes;
	private JTextField txNombrePaciente;
	private JButton btnBuscarPorNombrePacientes;
	private JButton btnAceptarPacientes;
	private JButton btnCancelarPacientes;

	private PacientesController pc = new PacientesController();
	private JornadaController jc = new JornadaController();
	private CitasController cc = new CitasController();

	DefaultListModel<PacienteDto> listModelPacientes;
	DefaultListModel<EmpleadoDto> listModelMedicos;
	private JPanel pnSeleccionarMedicos;
	private JLabel lblSeleccionarMdicos;
	private JList<EmpleadoDto> listMedicos;
	private JTextField txDniMedico;
	private JButton btnBuscarPorDniMedicos;
	private JTextField txNombreMedico;
	private JButton btnBuscarPorNombreMedicos;
	private JButton btnAceptarMedicos;
	private JButton btnCancelar;

	private List<EmpleadoDto> medicos = new ArrayList<EmpleadoDto>();
	private PacienteDto seleccion;
	private JButton btnModificarInformacinDe;
	private JPanel pnInfoContacto;
	private JLabel lblInformacinDeContacto;
	private JLabel lblNombre;
	private JTextField txNombreContacto;
	private JLabel lblDni;
	private JTextField txDniContacto;
	private JLabel lblContacto;
	private JTextArea txtAreaInfoContacto;
	private JButton btnModificarContacto;
	private JButton btnCancelarContacto;

	/**
	 * Create the frame.
	 */
	public VentanaFijarCita() {
		c = new CardLayout();
		setResizable(false);
		setTitle("Fijar cita entre paciente y m\u00E9dico(s)");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 705, 410);

		inicializarListas();

		pnBase = new JPanel();
		pnBase.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnBase);
		pnBase.setLayout(c);
		pnBase.add(getPnFijarCita(), "pnFijarCita");
		pnBase.add(getPnSeleccionarPaciente(), "pnSeleccionarPaciente");
		pnBase.add(getPnSeleccionarMedicos(), "pnSeleccionarMedicos");
		pnBase.add(getPnInfoContacto(), "pnInfoContacto");

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

	private JPanel getPnFijarCita() {
		if (pnFijarCita == null) {
			pnFijarCita = new JPanel();
			pnFijarCita.setLayout(null);
			pnFijarCita.add(getLabel());
			pnFijarCita.add(getLabel_1());
			pnFijarCita.add(getDcInicio());
			pnFijarCita.add(getLabel_2());
			pnFijarCita.add(getDcFin());
			pnFijarCita.add(getLabel_3());
			pnFijarCita.add(getBtnSeleccionarPaciente());
			pnFijarCita.add(getTxPaciente());
			pnFijarCita.add(getLabel_4());
			pnFijarCita.add(getBtnSeleccionarMedico());
			pnFijarCita.add(getChckbxUrgente());
			pnFijarCita.add(getLabel_5());
			pnFijarCita.add(getTxUbicacion());
			pnFijarCita.add(getBtCancelar());
			pnFijarCita.add(getBtCrear());
			pnFijarCita.add(getTxAreaMedicos());
			pnFijarCita.add(getBtnModificarInformacinDe());
		}
		return pnFijarCita;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Crear nueva cita");
			label.setFont(new Font("Tahoma", Font.PLAIN, 17));
			label.setBounds(43, 30, 151, 20);
		}
		return label;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Fecha y hora inicio:");
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label_1.setBounds(43, 79, 121, 20);
		}
		return label_1;
	}

	private JDateChooser getDcInicio() {
		if (dcInicio == null) {
			dcInicio = new JDateChooser();
			dcInicio.setDateFormatString("dd/MM/yy HH:mm:ss");
			dcInicio.setBounds(174, 79, 160, 20);
		}
		return dcInicio;
	}

	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Fecha y hora fin:");
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label_2.setBounds(361, 79, 121, 20);
		}
		return label_2;
	}

	private JDateChooser getDcFin() {
		if (dcFin == null) {
			dcFin = new JDateChooser();
			dcFin.setDateFormatString("dd/MM/yy HH:mm:ss");
			dcFin.setBounds(477, 79, 160, 20);
		}
		return dcFin;
	}

	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Paciente:");
			label_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label_3.setBounds(43, 130, 68, 20);
		}
		return label_3;
	}

	private JButton getBtnSeleccionarPaciente() {
		if (btnSeleccionarPaciente == null) {
			btnSeleccionarPaciente = new JButton("Seleccionar paciente");
			btnSeleccionarPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					c.show(pnBase, "pnSeleccionarPaciente");
				}
			});
			btnSeleccionarPaciente.setBounds(125, 130, 177, 23);
		}
		return btnSeleccionarPaciente;
	}

	private JTextField getTxPaciente() {
		if (txPaciente == null) {
			txPaciente = new JTextField();
			txPaciente.setEditable(false);
			txPaciente.setColumns(10);
			txPaciente.setBounds(329, 131, 308, 20);
		}
		return txPaciente;
	}

	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("M\u00E9dico(s):");
			label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label_4.setBounds(43, 202, 68, 20);
		}
		return label_4;
	}

	private JButton getBtnSeleccionarMedico() {
		if (btnSeleccionarMedico == null) {
			btnSeleccionarMedico = new JButton("Seleccionar m\u00E9dico(s)");
			btnSeleccionarMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.show(pnBase, "pnSeleccionarMedicos");
				}
			});
			btnSeleccionarMedico.setBounds(125, 202, 177, 23);
		}
		return btnSeleccionarMedico;
	}

	private JCheckBox getChckbxUrgente() {
		if (chckbxUrgente == null) {
			chckbxUrgente = new JCheckBox("Urgente");
			chckbxUrgente.setFont(new Font("Tahoma", Font.PLAIN, 13));
			chckbxUrgente.setBounds(43, 302, 97, 23);
		}
		return chckbxUrgente;
	}

	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Ubicaci\u00F3n:");
			label_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label_5.setBounds(273, 302, 79, 19);
		}
		return label_5;
	}

	private JTextField getTxUbicacion() {
		if (txUbicacion == null) {
			txUbicacion = new JTextField();
			txUbicacion.setColumns(10);
			txUbicacion.setBounds(361, 302, 177, 20);
		}
		return txUbicacion;
	}

	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btCancelar.setBounds(491, 337, 89, 23);
		}
		return btCancelar;
	}

	private JButton getBtCrear() {
		if (btCrear == null) {
			btCrear = new JButton("Crear");
			btCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Acciones crear cita

					// Comprobar que los campos introducidos son correctos (Lorena)
					if (dcInicio.getDate() == null || dcFin.getDate() == null) {
						JOptionPane.showMessageDialog(pnBase, "Debe introducir las fechas de inicio y fin de la cita",
								"No hay fechas seleccionadas", JOptionPane.WARNING_MESSAGE);
					} else if (dcInicio.getDate().before(new Date()) || dcFin.getDate().before(new Date())) {
						JOptionPane.showMessageDialog(pnBase,
								"La fecha para la cita no puede ocurrir antes de la fecha actual",
								"Las fechas son incompatibles", JOptionPane.WARNING_MESSAGE);
					} else if (dcInicio.getDate().after(dcFin.getDate())) {
						JOptionPane.showMessageDialog(pnBase,
								"La fecha de inicio de la cita no puede ser después que la de su fin",
								"Las fechas son incompatibles", JOptionPane.WARNING_MESSAGE);
					} else if (seleccion == null) {
						JOptionPane.showMessageDialog(pnBase, "Debe seleccionar un paciente para la cita",
								"No hay paciente seleccionado", JOptionPane.WARNING_MESSAGE);
					} else if (medicos.size() == 0) {
						JOptionPane.showMessageDialog(pnBase, "Debe seleccionar al menos un médico para la cita",
								"No hay médico seleccionado", JOptionPane.WARNING_MESSAGE);
					} else if (txUbicacion.getText().length() == 0) {
						JOptionPane.showMessageDialog(pnBase, "Debe introducir una ubicación para la cita",
								"No hay ubicación", JOptionPane.WARNING_MESSAGE);
					} else {

						// Actualizar la informacion de contacto en la base de datos(Lorena)
						pc.updateInfoContacto(seleccion);

						// Enviar correo en caso de ser urgente (Jose)
						ArrayList<Boolean> correos = new ArrayList<Boolean>();
						if (getChckbxUrgente().isSelected()) {
							String asunto = "Cita urgente";
							String mensaje = "Se ha generado una cita urgente \n El paciente es:" + seleccion.nombre;

							for (EmpleadoDto e : medicos) {
								CorreoElectronico correo = new CorreoElectronico(e.correo, asunto, mensaje);
								correos.add(correo.enviarCorreo());
							}
						}
						boolean a = true;
						for (Boolean b : correos) {
							if (b == false)
								a = b;
						}
						if (a)
							mostrarMensaje(
									"Se ha enviado un correo a todos los medicos de esta cita, ya que ha sido marcada como urgente",
									"Informacion", JOptionPane.INFORMATION_MESSAGE);
						
						//crear la cita con los datos introducidos
						CitaDto cita = new CitaDto();
						cita.fechainicio=dcInicio.getDate();
						cita.fechafin=dcFin.getDate();
						cita.idPaciente=seleccion.id;
						if(chckbxUrgente.isSelected()) {
							cita.urgente=true;
						}
						else
							cita.urgente=false;
						cita.sala=txUbicacion.getText();
						
						cc.crearCita(cita, medicos);
						dispose();
						
					}
				}
			});
			btCrear.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btCrear.setBounds(590, 337, 89, 23);

		}
		return btCrear;
	}

	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}

	private JTextArea getTxAreaMedicos() {
		if (txAreaMedicos == null) {
			txAreaMedicos = new JTextArea();
			txAreaMedicos.setBounds(329, 201, 308, 74);
		}
		return txAreaMedicos;
	}

	private JPanel getPnSeleccionarPaciente() {
		if (pnSeleccionarPaciente == null) {
			pnSeleccionarPaciente = new JPanel();
			pnSeleccionarPaciente.setLayout(null);
			pnSeleccionarPaciente.add(getLblSeleccionarPacientePara());
			pnSeleccionarPaciente.add(getListPacientes());
			pnSeleccionarPaciente.add(getTxDniPaciente());
			pnSeleccionarPaciente.add(getBtnBuscarPorDniPacientes());
			pnSeleccionarPaciente.add(getTxNombrePaciente());
			pnSeleccionarPaciente.add(getBtnBuscarPorNombrePacientes());
			pnSeleccionarPaciente.add(getBtnAceptarPacientes());
			pnSeleccionarPaciente.add(getBtnCancelarPacientes());
		}
		return pnSeleccionarPaciente;
	}

	private JLabel getLblSeleccionarPacientePara() {
		if (lblSeleccionarPacientePara == null) {
			lblSeleccionarPacientePara = new JLabel("Seleccionar paciente para la cita");
			lblSeleccionarPacientePara.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblSeleccionarPacientePara.setBounds(21, 25, 250, 31);
		}
		return lblSeleccionarPacientePara;
	}

	private JList<PacienteDto> getListPacientes() {
		if (listPacientes == null) {
			listPacientes = new JList<>(listModelPacientes);
			listPacientes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listPacientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					PacienteDto seleccion = listPacientes.getSelectedValue();
					if (seleccion != null) {
						getBtnAceptarPacientes().setEnabled(true);
					}
				}
			});
			listPacientes.setBounds(51, 123, 560, 138);
		}
		return listPacientes;
	}

	private JTextField getTxDniPaciente() {
		if (txDniPaciente == null) {
			txDniPaciente = new JTextField();
			txDniPaciente.setBounds(51, 92, 114, 20);
			txDniPaciente.setColumns(10);
		}
		return txDniPaciente;
	}

	private JButton getBtnBuscarPorDniPacientes() {
		if (btnBuscarPorDniPacientes == null) {
			btnBuscarPorDniPacientes = new JButton("Buscar por DNI");
			btnBuscarPorDniPacientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBuscarPorDniPacientes.setBounds(175, 91, 129, 23);
		}
		return btnBuscarPorDniPacientes;
	}

	private JTextField getTxNombrePaciente() {
		if (txNombrePaciente == null) {
			txNombrePaciente = new JTextField();
			txNombrePaciente.setBounds(324, 92, 136, 20);
			txNombrePaciente.setColumns(10);
		}
		return txNombrePaciente;
	}

	private JButton getBtnBuscarPorNombrePacientes() {
		if (btnBuscarPorNombrePacientes == null) {
			btnBuscarPorNombrePacientes = new JButton("Buscar por nombre");
			btnBuscarPorNombrePacientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBuscarPorNombrePacientes.setBounds(470, 91, 141, 23);
		}
		return btnBuscarPorNombrePacientes;
	}

	private JButton getBtnAceptarPacientes() {
		if (btnAceptarPacientes == null) {
			btnAceptarPacientes = new JButton("Aceptar");
			btnAceptarPacientes.setEnabled(false);
			btnAceptarPacientes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getListPacientes().getSelectedValue() != null) {
						PacienteDto paciente = getListPacientes().getSelectedValue();
						System.out.println(paciente);
						addPaciente(paciente);
						txPaciente.setText(paciente.nombre);
						btnModificarInformacinDe.setEnabled(true);
						c.show(pnBase, "pnFijarCita");
					}
				}
			});
			btnAceptarPacientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAceptarPacientes.setBounds(577, 316, 89, 23);
		}
		return btnAceptarPacientes;
	}

	private JButton getBtnCancelarPacientes() {
		if (btnCancelarPacientes == null) {
			btnCancelarPacientes = new JButton("Cancelar");
			btnCancelarPacientes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.show(pnBase, "pnFijarCita");
				}
			});
			btnCancelarPacientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCancelarPacientes.setBounds(478, 317, 89, 23);
		}
		return btnCancelarPacientes;
	}

	private JPanel getPnSeleccionarMedicos() {
		if (pnSeleccionarMedicos == null) {
			pnSeleccionarMedicos = new JPanel();
			pnSeleccionarMedicos.setLayout(null);
			pnSeleccionarMedicos.add(getLblSeleccionarMdicos());
			pnSeleccionarMedicos.add(getListMedicos());
			pnSeleccionarMedicos.add(getTxDniMedico());
			pnSeleccionarMedicos.add(getBtnBuscarPorDniMedicos());
			pnSeleccionarMedicos.add(getTxNombreMedico());
			pnSeleccionarMedicos.add(getBtnBuscarPorNombreMedicos());
			pnSeleccionarMedicos.add(getBtnAceptarMedicos());
			pnSeleccionarMedicos.add(getBtnCancelar());
		}
		return pnSeleccionarMedicos;
	}

	private JLabel getLblSeleccionarMdicos() {
		if (lblSeleccionarMdicos == null) {
			lblSeleccionarMdicos = new JLabel("Seleccionar m\u00E9dico(s) para la cita");
			lblSeleccionarMdicos.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblSeleccionarMdicos.setBounds(37, 31, 275, 34);
		}
		return lblSeleccionarMdicos;
	}

	private JList<EmpleadoDto> getListMedicos() {
		if (listMedicos == null) {
			listMedicos = new JList<>(listModelMedicos);
			listMedicos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			listMedicos.setBounds(37, 121, 559, 150);
		}
		return listMedicos;
	}

	private JTextField getTxDniMedico() {
		if (txDniMedico == null) {
			txDniMedico = new JTextField();
			txDniMedico.setBounds(37, 90, 112, 20);
			txDniMedico.setColumns(10);
		}
		return txDniMedico;
	}

	private JButton getBtnBuscarPorDniMedicos() {
		if (btnBuscarPorDniMedicos == null) {
			btnBuscarPorDniMedicos = new JButton("Buscar por DNI");
			btnBuscarPorDniMedicos.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBuscarPorDniMedicos.setBounds(161, 89, 124, 23);
		}
		return btnBuscarPorDniMedicos;
	}

	private JTextField getTxNombreMedico() {
		if (txNombreMedico == null) {
			txNombreMedico = new JTextField();
			txNombreMedico.setBounds(295, 90, 152, 20);
			txNombreMedico.setColumns(10);
		}
		return txNombreMedico;
	}

	private JButton getBtnBuscarPorNombreMedicos() {
		if (btnBuscarPorNombreMedicos == null) {
			btnBuscarPorNombreMedicos = new JButton("Buscar por nombre");
			btnBuscarPorNombreMedicos.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBuscarPorNombreMedicos.setBounds(457, 87, 141, 23);
		}
		return btnBuscarPorNombreMedicos;
	}

	private JButton getBtnAceptarMedicos() {
		if (btnAceptarMedicos == null) {
			btnAceptarMedicos = new JButton("Aceptar");
			btnAceptarMedicos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!getListMedicos().getSelectedValuesList().isEmpty()) {
						List<EmpleadoDto> emp = getListMedicos().getSelectedValuesList();
						addEmpleados(emp);
						c.show(pnBase, "pnFijarCita");
					}
				}
			});
			btnAceptarMedicos.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAceptarMedicos.setBounds(574, 319, 89, 23);
		}
		return btnAceptarMedicos;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.show(pnBase, "pnFijarCita");
				}
			});
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCancelar.setBounds(473, 319, 89, 23);
		}
		return btnCancelar;
	}

	private void addPaciente(PacienteDto paciente) {
		this.seleccion = paciente;
		getTxPaciente().setText("");
		getTxPaciente().setText(paciente.toString());
	}

	private void addEmpleados(List<EmpleadoDto> emp) {
		this.medicos = emp;
		getTxAreaMedicos().setText("");
		for (EmpleadoDto e : medicos) {
			getTxAreaMedicos().append(e.toString() + "\n");
		}
	}

	private JButton getBtnModificarInformacinDe() {
		if (btnModificarInformacinDe == null) {
			btnModificarInformacinDe = new JButton("Modificar informaci\u00F3n de contacto");
			btnModificarInformacinDe.setEnabled(false);
			btnModificarInformacinDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					c.show(pnBase, "pnInfoContacto");
					txNombreContacto.setText(seleccion.nombre);
					txDniContacto.setText(seleccion.dni);
					txtAreaInfoContacto.setText(seleccion.contacto);
				}
			});
			btnModificarInformacinDe.setBounds(329, 162, 251, 23);
		}
		return btnModificarInformacinDe;
	}

	private JPanel getPnInfoContacto() {
		if (pnInfoContacto == null) {
			pnInfoContacto = new JPanel();
			pnInfoContacto.setLayout(null);
			pnInfoContacto.add(getLblInformacinDeContacto());
			pnInfoContacto.add(getLblNombre());
			pnInfoContacto.add(getTxNombreContacto());
			pnInfoContacto.add(getLblDni());
			pnInfoContacto.add(getTxDniContacto());
			pnInfoContacto.add(getLblContacto());
			pnInfoContacto.add(getTxtAreaInfoContacto());
			pnInfoContacto.add(getBtnModificarContacto());
			pnInfoContacto.add(getBtnCancelarContacto());
		}
		return pnInfoContacto;
	}

	private JLabel getLblInformacinDeContacto() {
		if (lblInformacinDeContacto == null) {
			lblInformacinDeContacto = new JLabel("Informaci\u00F3n de contacto del paciente");
			lblInformacinDeContacto.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblInformacinDeContacto.setBounds(28, 27, 315, 27);
		}
		return lblInformacinDeContacto;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNombre.setBounds(28, 80, 64, 27);
		}
		return lblNombre;
	}

	private JTextField getTxNombreContacto() {
		if (txNombreContacto == null) {
			txNombreContacto = new JTextField();
			txNombreContacto.setEditable(false);
			txNombreContacto.setBounds(91, 84, 252, 20);
			txNombreContacto.setColumns(10);
		}
		return txNombreContacto;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblDni.setBounds(28, 123, 53, 20);
		}
		return lblDni;
	}

	private JTextField getTxDniContacto() {
		if (txDniContacto == null) {
			txDniContacto = new JTextField();
			txDniContacto.setEditable(false);
			txDniContacto.setBounds(91, 124, 252, 20);
			txDniContacto.setColumns(10);
		}
		return txDniContacto;
	}

	private JLabel getLblContacto() {
		if (lblContacto == null) {
			lblContacto = new JLabel("Contacto:");
			lblContacto.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblContacto.setBounds(28, 168, 64, 20);
		}
		return lblContacto;
	}

	private JTextArea getTxtAreaInfoContacto() {
		if (txtAreaInfoContacto == null) {
			txtAreaInfoContacto = new JTextArea();
			txtAreaInfoContacto.setBounds(91, 167, 252, 140);
		}
		return txtAreaInfoContacto;
	}

	private JButton getBtnModificarContacto() {
		if (btnModificarContacto == null) {
			btnModificarContacto = new JButton("Modificar");
			btnModificarContacto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!txtAreaInfoContacto.getText().equals(seleccion.contacto)) {
						seleccion.contacto = txtAreaInfoContacto.getText();
						c.show(pnBase, "pnFijarCita");
					}
				}
			});
			btnModificarContacto.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnModificarContacto.setBounds(578, 288, 89, 23);
		}
		return btnModificarContacto;
	}

	private JButton getBtnCancelarContacto() {
		if (btnCancelarContacto == null) {
			btnCancelarContacto = new JButton("Cancelar");
			btnCancelarContacto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.show(pnBase, "pnFijarCita");
				}
			});
			btnCancelarContacto.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCancelarContacto.setBounds(479, 289, 89, 23);
		}
		return btnCancelarContacto;
	}
}
