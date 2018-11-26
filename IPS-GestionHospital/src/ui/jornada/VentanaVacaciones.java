package ui.jornada;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import business.JornadaController;
import business.LogController;
import business.VacacionesController;
import business.dto.CambioDto;
import business.dto.EmpleadoDto;
import business.dto.JornadaLaboralDto;
import business.dto.VacacionesDto;

public class VentanaVacaciones extends JFrame {

	private JPanel contentPane;
	private JLabel lblAsignarVacacionesA;
	private JPanel pnFechas;
	private JPanel pnEmpleado;
	private JButton btnSeleccionarEmpleado;
	private JLabel label;
	private JTextField txNombreEmpleado;
	private JLabel label_1;
	private JButton btnVerJornadas;
	private JLabel label_2;
	private JTextField txVacacionesAsignadas;
	private JPanel pnVacaciones;
	private JLabel label_3;
	private JDateChooser dcInicioVacaciones;
	private JLabel label_4;
	private JDateChooser dcFinVacaciones;
	private JButton btnComprobarVacaciones;
	private JPanel pnBotones;
	private JButton btnCancelar;
	private JButton btnAsignarVacaciones;
	private JPanel pnAsignarVacaciones;
	private JPanel pnSeleccionarEmpleado;

	private CardLayout c;
	private JLabel label_5;
	private JCheckBox chckbxMedico;
	private JCheckBox chckbxEnfermero;
	private JTextField txDniEmpleadoBuscar;
	private JButton btBuscarPorDni;
	private JTextField txNombreBuscar;
	private JButton btBuscarPorNombre;
	private JList<EmpleadoDto> listEmpleados;
	private JButton btCancelarBusqueda;
	private JButton btAceptarBusqueda;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	DefaultListModel<EmpleadoDto> listModelEnfermeros;
	DefaultListModel<EmpleadoDto> listModelMedicos;
	DefaultListModel<JornadaLaboralDto> listModelJornadas;
	private JornadaController jc = new JornadaController();
	private VacacionesController vc = new VacacionesController();
	private JScrollPane scrollPane;

	private EmpleadoDto empleado;
	private JPanel pnVerJornadas;
	private JPanel pnTitulo;
	private JLabel lblJornadasLaboralesDe;
	private JLabel lblNombreEmpleadoJornadas;
	private JPanel pnJornadasEmpleado;
	private JList<JornadaLaboralDto> listJornadas;
	private JPanel pnBotonesVerJornadas;
	private JButton btnAtrasJornadas;
	private JScrollPane scJornadas;

	private boolean existeJornada;
	private boolean existenVacaciones;
	private List<JornadaLaboralDto> jornadasABorrar = new ArrayList<JornadaLaboralDto>();

	/**
	 * Create the frame.
	 */
	public VentanaVacaciones() {
		c = new CardLayout();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inicializarListas();
		setBounds(100, 100, 716, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(c);
		contentPane.add(getPnAsignarVacaciones(), "pnAsignar");
		contentPane.add(getPnSeleccionarEmpleado(), "pnSeleccionar");
		contentPane.add(getPnVerJornadas(), "pnJornadas");
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

	private void inicializarListaJornadas() {
		listModelJornadas = new DefaultListModel<JornadaLaboralDto>();
		for (JornadaLaboralDto jornada : jc.getJornadasByEmpleado(empleado)) {
			listModelJornadas.addElement(jornada);
		}
	}

	private JLabel getLblAsignarVacacionesA() {
		if (lblAsignarVacacionesA == null) {
			lblAsignarVacacionesA = new JLabel("Asignar vacaciones a un empleado");
			lblAsignarVacacionesA.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblAsignarVacacionesA;
	}

	private JPanel getPnFechas() {
		if (pnFechas == null) {
			pnFechas = new JPanel();
			pnFechas.setLayout(null);
			pnFechas.add(getPnEmpleado());
			pnFechas.add(getPnVacaciones());
		}
		return pnFechas;
	}

	private JPanel getPnEmpleado() {
		if (pnEmpleado == null) {
			pnEmpleado = new JPanel();
			pnEmpleado.setLayout(null);
			pnEmpleado.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Empleado",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnEmpleado.setBounds(20, 26, 305, 179);
			pnEmpleado.add(getBtnSeleccionarEmpleado());
			pnEmpleado.add(getLabel());
			pnEmpleado.add(getTxNombreEmpleado());
			pnEmpleado.add(getLabel_1());
			pnEmpleado.add(getBtnVerJornadas());
			pnEmpleado.add(getLabel_2());
			pnEmpleado.add(getTxVacacionesAsignadas());
		}
		return pnEmpleado;
	}

	private JButton getBtnSeleccionarEmpleado() {
		if (btnSeleccionarEmpleado == null) {
			btnSeleccionarEmpleado = new JButton("Seleccionar empleado");
			btnSeleccionarEmpleado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.show(contentPane, "pnSeleccionar");
					CambioDto cambio = new CambioDto();
					cambio.cambio = "El administrador ha listado todos los empleados";
					cambio.fecha = new Date();					
					LogController lc = new LogController();
					lc.añadirCambio(cambio);
				}
			});
			btnSeleccionarEmpleado.setBounds(10, 32, 183, 23);
		}
		return btnSeleccionarEmpleado;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Nombre:");
			label.setBounds(10, 66, 57, 14);
		}
		return label;
	}

	private JTextField getTxNombreEmpleado() {
		if (txNombreEmpleado == null) {
			txNombreEmpleado = new JTextField();
			txNombreEmpleado.setEditable(false);
			txNombreEmpleado.setColumns(10);
			txNombreEmpleado.setBounds(83, 63, 202, 20);
		}
		return txNombreEmpleado;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Jornadas laborales:");
			label_1.setBounds(10, 98, 136, 14);
		}
		return label_1;
	}

	private JButton getBtnVerJornadas() {
		if (btnVerJornadas == null) {
			btnVerJornadas = new JButton("Ver");
			btnVerJornadas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (empleado != null) {
						c.show(contentPane, "pnJornadas");
						lblNombreEmpleadoJornadas.setText(empleado.nombre);
						inicializarListaJornadas();
						listJornadas.setModel(listModelJornadas);
						
						CambioDto cambio = new CambioDto();
						cambio.cambio = "El administrador ha listado las jornadas del empleado con id: "+empleado.id;
						cambio.fecha = new Date();					
						LogController lc = new LogController();
						lc.añadirCambio(cambio);
					} else
						JOptionPane.showMessageDialog(contentPane,
								"Para ver las jornadas de un empleado primero debe seleccionar uno",
								"No hay empleado seleccionado", JOptionPane.WARNING_MESSAGE);
				}
			});
			btnVerJornadas.setBounds(196, 94, 89, 23);
		}
		return btnVerJornadas;
	}

	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Vacaciones ya asignadas:");
			label_2.setBounds(10, 135, 176, 14);
		}
		return label_2;
	}

	private JTextField getTxVacacionesAsignadas() {
		if (txVacacionesAsignadas == null) {
			txVacacionesAsignadas = new JTextField();
			txVacacionesAsignadas.setEditable(false);
			txVacacionesAsignadas.setColumns(10);
			txVacacionesAsignadas.setBounds(196, 132, 89, 20);
		}
		return txVacacionesAsignadas;
	}

	private JPanel getPnVacaciones() {
		if (pnVacaciones == null) {
			pnVacaciones = new JPanel();
			pnVacaciones.setLayout(null);
			pnVacaciones.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
					"Asignar vacaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnVacaciones.setBounds(345, 26, 305, 179);
			pnVacaciones.add(getLabel_3());
			pnVacaciones.add(getDcInicioVacaciones());
			pnVacaciones.add(getLabel_4());
			pnVacaciones.add(getDcFinVacaciones());
			pnVacaciones.add(getBtnComprobarVacaciones());
		}
		return pnVacaciones;
	}

	private JLabel getLabel_3() {
		if (label_3 == null) {
			label_3 = new JLabel("Fecha inicio:");
			label_3.setBounds(10, 32, 72, 14);
		}
		return label_3;
	}

	private JDateChooser getDcInicioVacaciones() {
		if (dcInicioVacaciones == null) {
			dcInicioVacaciones = new JDateChooser();
			dcInicioVacaciones.setBounds(92, 26, 154, 20);
		}
		return dcInicioVacaciones;
	}

	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Fecha fin:");
			label_4.setBounds(10, 69, 72, 14);
		}
		return label_4;
	}

	private JDateChooser getDcFinVacaciones() {
		if (dcFinVacaciones == null) {
			dcFinVacaciones = new JDateChooser();
			dcFinVacaciones.setBounds(92, 63, 154, 20);
		}
		return dcFinVacaciones;
	}

	private JButton getBtnComprobarVacaciones() {
		if (btnComprobarVacaciones == null) {
			btnComprobarVacaciones = new JButton("Comprobar disponibilidad");
			btnComprobarVacaciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Comprobar que hay un empleado
					if (empleado != null) {

						// Comprobar que las fechas estan bien puestas
						if (dcInicioVacaciones.getDate() == null || dcFinVacaciones.getDate() == null) {
							JOptionPane.showMessageDialog(contentPane,
									"No hay fechas seleccionadas, por favor indíquelas.", "No hay fechas seleccionadas",
									JOptionPane.WARNING_MESSAGE);
						} else if (dcInicioVacaciones.getDate().after(dcFinVacaciones.getDate())
								|| dcInicioVacaciones.getDate().before(new Date())
								|| dcFinVacaciones.getDate().before(new Date())) {
							JOptionPane.showMessageDialog(contentPane,
									"La fecha de fin de vacaciones no puede ser anterior a la de inicio ni anteriores a la fecha actual",
									"Fechas no compatibles", JOptionPane.WARNING_MESSAGE);
						} else {
							// Comprobar que las vacaciones coinciden con una jornada
							List<JornadaLaboralDto> jornadas = jc.getJornadasByEmpleado(empleado);
							Date fechaVacacionesInicio = new Date(dcInicioVacaciones.getDate().getYear(),
									dcInicioVacaciones.getDate().getMonth(), dcInicioVacaciones.getDate().getDate());
							Date fechaVacacionesFin = new Date(dcFinVacaciones.getDate().getYear(),
									dcFinVacaciones.getDate().getMonth(), dcFinVacaciones.getDate().getDate());
							Date fechaJornadaInicio = null;
							Date fechaJornadaFin = null;
							existeJornada = false;
							for (JornadaLaboralDto jornada : jornadas) {
								fechaJornadaInicio = new Date(jornada.fechainicio.getYear(),
										jornada.fechainicio.getMonth(), jornada.fechainicio.getDate(),
										jornada.fechainicio.getHours(), jornada.fechainicio.getMinutes());

								fechaJornadaFin = new Date(jornada.fechafin.getYear(), jornada.fechafin.getMonth(),
										jornada.fechafin.getDate(), jornada.fechafin.getHours(),
										jornada.fechafin.getMinutes());

								if (fechaVacacionesInicio.before(fechaJornadaInicio)
										&& fechaVacacionesFin.after(fechaJornadaInicio)
										|| fechaVacacionesInicio.after(fechaJornadaInicio)
												&& fechaVacacionesInicio.before(fechaJornadaFin)
										|| fechaJornadaInicio.compareTo(fechaVacacionesInicio) == 0
												&& fechaVacacionesFin.before(fechaJornadaFin)
										|| fechaVacacionesInicio.after(fechaJornadaInicio)
												&& fechaVacacionesInicio.before(fechaJornadaFin)
												&& fechaVacacionesFin.compareTo(fechaJornadaFin) == 0
										|| fechaVacacionesInicio.before(fechaJornadaInicio)
												&& fechaVacacionesFin.after(fechaJornadaFin)) {
									existeJornada = true;
									jornadasABorrar.add(jornada);

								}
								// Comprobar que las vacaciones no coinciden con una jornada --> asignar
								// directamente

								// vc.asignarVacaciones(dcInicioVacaciones.getDate(), dcFinVacaciones.getDate(),
								// empleado.id);
							}
							if (!existeJornada)
								JOptionPane.showMessageDialog(contentPane,
										"Las fechas no coinciden con ninguna jornada laboral, pueden asignarse sin problema");
							else {
								JOptionPane.showMessageDialog(contentPane,
										"El rango de vacaciones seleccionado coincide con una jornada, pero puede asignarlo igualmente.",
										"Coincidencia de rangos de vacaciones y jornadas", JOptionPane.WARNING_MESSAGE);
							}

							// Comprobar si ya tiene vacaciones de antes en esas fechas
							List<VacacionesDto> vacacionesList = vc.getVacacionesPorEmpleado(empleado);
							existenVacaciones = false;
							Date dcInicio = new Date(dcInicioVacaciones.getDate().getYear(),
									dcInicioVacaciones.getDate().getMonth(), dcInicioVacaciones.getDate().getDate());
							Date dcFin = new Date(dcFinVacaciones.getDate().getYear(),
									dcFinVacaciones.getDate().getMonth(), dcFinVacaciones.getDate().getDate());
							for (VacacionesDto vacaciones : vacacionesList) {
//								System.out.println(vacaciones.fechainicio);
//								System.out.println(vacaciones.fechafin);
								Date vacacionesInicio = new Date(vacaciones.fechainicio.getYear(),
										vacaciones.fechainicio.getMonth(), vacaciones.fechainicio.getDate());
								Date vacacionesFin = new Date(vacaciones.fechafin.getYear(),
										vacaciones.fechafin.getMonth(), vacaciones.fechafin.getDate());
								
								if (dcInicio.before(vacacionesInicio) && dcFin.after(vacacionesInicio)
										|| dcInicio.after(vacacionesInicio) && dcInicio.before(vacacionesFin)
										|| dcFin.after(vacacionesInicio) && dcFin.before(vacacionesFin)
										|| dcInicio.compareTo(vacacionesInicio)==0 && dcFin.before(vacacionesFin)
										|| dcInicio.after(vacacionesInicio) && dcFin.compareTo(vacacionesFin)==0
										
										|| dcInicio.before(vacacionesInicio) && dcFin.after(vacacionesFin)
										|| dcInicio.compareTo(vacacionesInicio) == 0
										|| dcFin.compareTo(vacacionesFin) == 0) {
									existenVacaciones = true;

								}
							}
							btnAsignarVacaciones.setEnabled(true);
							if (existenVacaciones) {
								JOptionPane.showMessageDialog(contentPane,
										"Ya existe un rango de vacaciones en esas fechas", "Vacaciones incompatibles",
										JOptionPane.WARNING_MESSAGE);
							}
						}

					} else
						JOptionPane.showMessageDialog(contentPane,
								"Para comprobar la disponibilidad de las vacaciones debe seleccionar un empleado",
								"No hay empleado seleccionado", JOptionPane.WARNING_MESSAGE);

				}
			});
			btnComprobarVacaciones.setBounds(92, 122, 179, 23);
		}
		return btnComprobarVacaciones;
	}

	private void borrarJornadasPorVacaciones(List<JornadaLaboralDto> jornadas) {
		jc.eliminarJornadas(jornadas);
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			FlowLayout fl_pnlBotonConsulta = (FlowLayout) pnBotones.getLayout();
			fl_pnlBotonConsulta.setAlignment(FlowLayout.RIGHT);
			pnBotones.add(getBtnCancelar());
			pnBotones.add(getBtnAsignarVacaciones());
		}
		return pnBotones;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}

	private JButton getBtnAsignarVacaciones() {
		if (btnAsignarVacaciones == null) {
			btnAsignarVacaciones = new JButton("Asignar");
			btnAsignarVacaciones.setEnabled(false);
			btnAsignarVacaciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (empleado != null) {
						if (dcInicioVacaciones.getDate().before(new Date())
								|| dcFinVacaciones.getDate().before(new Date())
								|| dcInicioVacaciones.getDate().after(dcFinVacaciones.getDate())
								|| dcInicioVacaciones.getDate() == null || dcFinVacaciones.getDate() == null) {
							JOptionPane.showMessageDialog(contentPane,
									"Las fechas introducidas no son correctas, por favor, cámbielas.",
									"Fechas incorrectas", JOptionPane.WARNING_MESSAGE);
						} else {
							if (existeJornada && jornadasABorrar.size() > 0 && !existenVacaciones) {
								int respuesta = JOptionPane.showConfirmDialog(contentPane,
										"Existe una jornada laboral para esas fechas, sí acepta estas jornadas se borrarán, ¿desea continuar?",
										"Jornadas existentes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								if (respuesta == 0) {
									
									borrarJornadasPorVacaciones(jornadasABorrar);
									vc.asignarVacaciones(dcInicioVacaciones.getDate(), dcFinVacaciones.getDate(),
											empleado.id);
									JOptionPane.showMessageDialog(contentPane,
											"Las vacaciones se han asignado correctamente", "Vacaciones asignadas",
											JOptionPane.INFORMATION_MESSAGE);
									dispose();
								}

							} else if (existenVacaciones) {
								JOptionPane.showMessageDialog(contentPane, "Ya existen unas vacaciones en esas fechas",
										"Vacaciones ya existentes", JOptionPane.WARNING_MESSAGE);
							} else {
								vc.asignarVacaciones(dcInicioVacaciones.getDate(), dcFinVacaciones.getDate(),
										empleado.id);
								JOptionPane.showMessageDialog(contentPane,
										"Las vacaciones se han asignado correctamente", "Vacaciones asignadas",
										JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}

						}
					} else
						JOptionPane.showMessageDialog(contentPane,
								"Para asignar las vacaciones debe seleccionar un empleado",
								"No hay empleado seleccionado", JOptionPane.WARNING_MESSAGE);
				}

			});
		}
		return btnAsignarVacaciones;

	}

	private JPanel getPnAsignarVacaciones() {
		if (pnAsignarVacaciones == null) {
			pnAsignarVacaciones = new JPanel();
			pnAsignarVacaciones.setLayout(new BorderLayout(0, 0));
			pnAsignarVacaciones.add(getLblAsignarVacacionesA(), BorderLayout.NORTH);
			pnAsignarVacaciones.add(getPnFechas(), BorderLayout.CENTER);
			pnAsignarVacaciones.add(getPnBotones(), BorderLayout.SOUTH);
		}
		return pnAsignarVacaciones;
	}

	private JPanel getPnSeleccionarEmpleado() {
		if (pnSeleccionarEmpleado == null) {
			pnSeleccionarEmpleado = new JPanel();
			pnSeleccionarEmpleado.setLayout(null);
			pnSeleccionarEmpleado.add(getLabel_5());
			pnSeleccionarEmpleado.add(getChckbxMedico());
			pnSeleccionarEmpleado.add(getChckbxEnfermero());
			pnSeleccionarEmpleado.add(getTxDniEmpleadoBuscar());
			pnSeleccionarEmpleado.add(getBtBuscarPorDni());
			pnSeleccionarEmpleado.add(getTxNombreBuscar());
			pnSeleccionarEmpleado.add(getBtBuscarPorNombre());
			pnSeleccionarEmpleado.add(getBtCancelarBusqueda());
			pnSeleccionarEmpleado.add(getBtAceptarBusqueda());
			pnSeleccionarEmpleado.add(getScrollPane());
		}
		return pnSeleccionarEmpleado;
	}

	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel("Seleccionar empleado");
			label_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
			label_5.setBounds(10, 11, 230, 34);
		}
		return label_5;
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
			chckbxMedico.setBounds(27, 66, 113, 25);
			buttonGroup.add(chckbxMedico);
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
			chckbxEnfermero.setBounds(177, 67, 113, 25);
			buttonGroup.add(chckbxEnfermero);
		}
		return chckbxEnfermero;
	}

	private JTextField getTxDniEmpleadoBuscar() {
		if (txDniEmpleadoBuscar == null) {
			txDniEmpleadoBuscar = new JTextField();
			txDniEmpleadoBuscar.setColumns(10);
			txDniEmpleadoBuscar.setBounds(27, 114, 113, 20);
		}
		return txDniEmpleadoBuscar;
	}

	private JButton getBtBuscarPorDni() {
		if (btBuscarPorDni == null) {
			btBuscarPorDni = new JButton("Buscar por DNI");
			btBuscarPorDni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarDNIFiltro();
				}
			});
			btBuscarPorDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btBuscarPorDni.setBounds(150, 113, 124, 23);
		}
		return btBuscarPorDni;
	}

	private JTextField getTxNombreBuscar() {
		if (txNombreBuscar == null) {
			txNombreBuscar = new JTextField();
			txNombreBuscar.setColumns(10);
			txNombreBuscar.setBounds(313, 114, 152, 20);
		}
		return txNombreBuscar;
	}

	private JButton getBtBuscarPorNombre() {
		if (btBuscarPorNombre == null) {
			btBuscarPorNombre = new JButton("Buscar por nombre");
			btBuscarPorNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarNombreFiltro();
				}
			});
			btBuscarPorNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btBuscarPorNombre.setBounds(478, 113, 147, 23);
		}
		return btBuscarPorNombre;
	}

	private JList<EmpleadoDto> getListEmpleados() {
		if (listEmpleados == null) {
			listEmpleados = new JList<EmpleadoDto>();
		}
		return listEmpleados;
	}

	private JButton getBtCancelarBusqueda() {
		if (btCancelarBusqueda == null) {
			btCancelarBusqueda = new JButton("Cancelar");
			btCancelarBusqueda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.show(contentPane, "pnAsignar");
				}
			});
			btCancelarBusqueda.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btCancelarBusqueda.setBounds(487, 362, 89, 23);
		}
		return btCancelarBusqueda;
	}

	private JButton getBtAceptarBusqueda() {
		if (btAceptarBusqueda == null) {
			btAceptarBusqueda = new JButton("Aceptar");
			btAceptarBusqueda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (listEmpleados.getSelectedValue() != null) {
						empleado = listEmpleados.getSelectedValue();
						txNombreEmpleado.setText(empleado.nombre);
						c.show(contentPane, "pnAsignar");
						if (vc.empleadoTieneVacaciones(empleado)) {
							txVacacionesAsignadas.setText("Sí");
						} else
							txVacacionesAsignadas.setText("No");
					} else
						JOptionPane.showMessageDialog(contentPane,
								"Debe seleccionar un empleado al que asignar la jornada laboral",
								"No hay empleado seleccionado", JOptionPane.WARNING_MESSAGE);
				}
			});
			btAceptarBusqueda.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btAceptarBusqueda.setBounds(591, 363, 89, 23);
		}
		return btAceptarBusqueda;
	}

	private void buscarNombreFiltro() {
		if (chckbxMedico.isSelected()) {
			for (EmpleadoDto em : jc.getMedicos()) {
				if (txNombreBuscar.getText().equals(em.nombre)) {
					DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
					filtro.addElement(em);
					listEmpleados.setModel(filtro);
				}
			}
		} else if (chckbxEnfermero.isSelected()) {
			for (EmpleadoDto em : jc.getEnfermeros()) {
				if (txNombreBuscar.getText().equals(em.nombre)) {
					DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
					filtro.addElement(em);
					listEmpleados.setModel(filtro);
				}
			}
		}
	}

	private void buscarDNIFiltro() {
		if (chckbxMedico.isSelected()) {
			for (EmpleadoDto em : jc.getMedicos()) {
				if (txDniEmpleadoBuscar.getText() == em.dni) {
					DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
					filtro.addElement(em);
					listEmpleados.setModel(filtro);
				}
			}
		} else if (chckbxEnfermero.isSelected()) {
			for (EmpleadoDto em : jc.getEnfermeros()) {
				if (txDniEmpleadoBuscar.getText() == em.dni) {
					DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
					filtro.addElement(em);
					listEmpleados.setModel(filtro);
				}
			}
		}
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(27, 160, 598, 165);
			scrollPane.setViewportView(getListEmpleados());
		}
		return scrollPane;
	}

	private JPanel getPnVerJornadas() {
		if (pnVerJornadas == null) {
			pnVerJornadas = new JPanel();
			pnVerJornadas.setLayout(new BorderLayout(0, 0));
			pnVerJornadas.add(getPnTitulo(), BorderLayout.NORTH);
			pnVerJornadas.add(getPnJornadasEmpleado(), BorderLayout.CENTER);
			pnVerJornadas.add(getPnBotonesVerJornadas(), BorderLayout.SOUTH);
		}
		return pnVerJornadas;
	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			FlowLayout fl_pnlBotonConsulta = (FlowLayout) pnTitulo.getLayout();
			fl_pnlBotonConsulta.setAlignment(FlowLayout.LEFT);
			pnTitulo.add(getLblJornadasLaboralesDe());
			pnTitulo.add(getLblNombreEmpleadoJornadas());
		}
		return pnTitulo;
	}

	private JLabel getLblJornadasLaboralesDe() {
		if (lblJornadasLaboralesDe == null) {
			lblJornadasLaboralesDe = new JLabel("Jornadas laborales de: ");
			lblJornadasLaboralesDe.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblJornadasLaboralesDe;
	}

	private JLabel getLblNombreEmpleadoJornadas() {
		if (lblNombreEmpleadoJornadas == null) {
			lblNombreEmpleadoJornadas = new JLabel("");
			lblNombreEmpleadoJornadas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblNombreEmpleadoJornadas;
	}

	private JPanel getPnJornadasEmpleado() {
		if (pnJornadasEmpleado == null) {
			pnJornadasEmpleado = new JPanel();
			pnJornadasEmpleado.setLayout(new BorderLayout(0, 0));
			pnJornadasEmpleado.add(getScJornadas());
		}
		return pnJornadasEmpleado;
	}

	private JList<JornadaLaboralDto> getListJornadas() {
		if (listJornadas == null) {
			listJornadas = new JList<JornadaLaboralDto>();
		}
		return listJornadas;
	}

	private JPanel getPnBotonesVerJornadas() {
		if (pnBotonesVerJornadas == null) {
			pnBotonesVerJornadas = new JPanel();
			FlowLayout fl_pnlBotonConsulta = (FlowLayout) pnBotonesVerJornadas.getLayout();
			fl_pnlBotonConsulta.setAlignment(FlowLayout.RIGHT);
			pnBotonesVerJornadas.add(getBtnAtrasJornadas());
		}
		return pnBotonesVerJornadas;
	}

	private JButton getBtnAtrasJornadas() {
		if (btnAtrasJornadas == null) {
			btnAtrasJornadas = new JButton("Atr\u00E1s");
			btnAtrasJornadas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					c.show(contentPane, "pnAsignar");
				}
			});
		}
		return btnAtrasJornadas;
	}

	private JScrollPane getScJornadas() {
		if (scJornadas == null) {
			scJornadas = new JScrollPane();
			scJornadas.setViewportView(getListJornadas());
		}
		return scJornadas;
	}
}
