package ui.jornada;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import business.JornadaController;
import business.dto.EmpleadoDto;
import business.dto.PacienteDto;

import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class VentanaJornadaLaboral extends JFrame {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
	private JDateChooser dcInicio;
	private JLabel lblFechaYHoraInicio;
	private JLabel lblFechaYHoraFin;
	private JDateChooser dcFin;
	private JButton btnSiguiente;
	private JButton btnLimpiar;
	private JPanel panelEmpleado;
	private JPanel panelJornada;
	
	DefaultListModel<EmpleadoDto> listModelEnfermeros;
	DefaultListModel<EmpleadoDto> listModelMedicos;
	
	private JornadaController jc = new JornadaController();

	private JList<String> listDias;
	private JLabel lblDas;
	private JTextArea textAreaDias;
	private JButton btnSalir;
	private JButton btnSeleccionarEmpleado;
	private JPanel panelPrincipal;
	private JTextField textField;
	private JPanel panelBuscador;
	private JLabel lblSeleccionarEmpleado;
	private JList<EmpleadoDto> listEmpleados;
	private JTextField textFieldBuscarDni;
	private JButton buttonBuscarDni;
	private JTextField textFieldBuscarNombre;
	private JButton buttonBuscarNombre;
	private JButton buttonAceptar;
	private JButton buttonCancelar;
	private JCheckBox chckbxMedico;
	private JCheckBox chckbxEnfermero;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JScrollPane scrollPane;
	private JList<EmpleadoDto> list;

	/**
	 * Create the frame.
	 */
	public VentanaJornadaLaboral() {
		setResizable(false);
		setTitle("Asignar jornada laboral a m\u00E9dicos o enfermeros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inicializarListas();
		setBounds(100, 100, 612, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPanelPrincipal(), "panelPrincipal");
		contentPane.add(getPanel_3(), "panelBuscador");
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

	private JDateChooser getDcInicio() {
		if (dcInicio == null) {
			dcInicio = new JDateChooser();

			dcInicio.setBounds(139, 35, 139, 22);
			dcInicio.setDateFormatString("dd/MM/yy HH:mm");
		}
		return dcInicio;
	}

	private JLabel getLblFechaYHoraInicio() {
		if (lblFechaYHoraInicio == null) {
			lblFechaYHoraInicio = new JLabel("Fecha y hora inicio:");
			lblFechaYHoraInicio.setBounds(12, 35, 123, 16);
			lblFechaYHoraInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblFechaYHoraInicio;
	}

	private JLabel getLblFechaYHoraFin() {
		if (lblFechaYHoraFin == null) {
			lblFechaYHoraFin = new JLabel("Fecha y hora fin:");
			lblFechaYHoraFin.setBounds(300, 35, 109, 16);
			lblFechaYHoraFin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblFechaYHoraFin;
	}

	private JDateChooser getDcFin() {
		if (dcFin == null) {
			dcFin = new JDateChooser();
			dcFin.setBounds(405, 35, 139, 22);
			dcFin.setDateFormatString("dd/MM/yy HH:mm");
		}
		return dcFin;
	}

	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setBounds(487, 373, 97, 25);
//			btnSiguiente.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					comprobarCampos();
//				}
//			});
			btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnSiguiente;
	}

	private JButton getBtnLimpiar() {
		if (btnLimpiar == null) {
			btnLimpiar = new JButton("Limpiar");
			btnLimpiar.setBounds(367, 373, 107, 25);
			btnLimpiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnLimpiar;
	}

	protected void inicializar() {
	
		textAreaDias.setText("");
		getDcFin().setCalendar(null);
		getDcInicio().setCalendar(null);

		listDias.clearSelection();

	}

	private JPanel getPanelEmpleado() {
		if (panelEmpleado == null) {
			panelEmpleado = new JPanel();
			panelEmpleado.setBounds(0, 13, 584, 102);
			panelEmpleado
					.setBorder(new TitledBorder(null, "Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelEmpleado.setLayout(null);
			panelEmpleado.add(getBtnSeleccionarEmpleado());
			panelEmpleado.add(getTextField());
		}
		return panelEmpleado;
	}

	private JPanel getPanel_1() {
		if (panelJornada == null) {
			panelJornada = new JPanel();
			panelJornada.setBounds(0, 122, 584, 248);
			panelJornada
					.setBorder(new TitledBorder(null, "Jornada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelJornada.setLayout(null);
			panelJornada.add(getDcFin());
			panelJornada.add(getLblFechaYHoraFin());
			panelJornada.add(getDcInicio());
			panelJornada.add(getLblFechaYHoraInicio());
			panelJornada.add(getListDias());
			panelJornada.add(getLblDas());
			panelJornada.add(getTextAreaDias());
		}
		return panelJornada;
	}

	private JList<String> getListDias() {
		if (listDias == null) {
			listDias = new JList<String>();
			listDias.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					refrescarSeleccion();
				}
			});
			listDias.setModel(new AbstractListModel<String>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				String[] values = new String[] { "Lunes", "Martes", "Mi\u00E9rcoles", "Jueves", "Viernes",
						"S\u00E1bado", "Domingo" };

				public int getSize() {
					return values.length;
				}

				public String getElementAt(int index) {
					return values[index];
				}
			});
			listDias.setBounds(22, 103, 89, 133);
		}
		return listDias;
	}

	@SuppressWarnings("deprecation")
	private void refrescarSeleccion() {
		Object[] seleccion = listDias.getSelectedValues();
		String nuevoTexto = "";
		for (Object o : seleccion)
			nuevoTexto += (String) o + " ";
		textAreaDias.setText(nuevoTexto);
	}

	private JLabel getLblDas() {
		if (lblDas == null) {
			lblDas = new JLabel("D\u00EDas:");
			lblDas.setBounds(139, 104, 56, 16);
		}
		return lblDas;
	}

	private JTextArea getTextAreaDias() {
		if (textAreaDias == null) {
			textAreaDias = new JTextArea();
			textAreaDias.setLineWrap(true);
			textAreaDias.setWrapStyleWord(true);
			textAreaDias.setEditable(false);
			textAreaDias.setBounds(177, 103, 292, 43);
		}
		return textAreaDias;
	}

//	private void cargarModelo(List<EmpleadoDto> empleados) {
//		cmbxEmpleados.removeAllItems();
//		for (EmpleadoDto e : empleados) {
//			cmbxEmpleados.addItem(e);
//		}
//	}

	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.setBounds(254, 373, 97, 25);
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnSalir;
	}

	public String getFin() {
		return dcFin.getDate().toString();
	}

	public String getInicio() {
		return dcInicio.getDate().toString();
	}

	public java.util.Date getFinDate() {
		return dcFin.getDate();
	}

	public java.util.Date getInicioDate() {
		return dcInicio.getDate();
	}

	public String getDias() {
		return getTextAreaDias().getText();
	}

//	public void muestraDialogo() {
//		VentanaConfirmacionJornada dialogo = new VentanaConfirmacionJornada(
//				(EmpleadoDto) cmbxEmpleados.getSelectedItem(), this);
//		dialogo.setLocationRelativeTo(this);
//		dialogo.setModal(true);
//		dialogo.setVisible(true);
//	}

//	public void comprobarCampos() {
//		if ((!chckbxMedico.isSelected() && !chckbxEnfermero.isSelected()) || cmbxEmpleados.getSelectedItem() == null
//				|| dcFin.getDate() == null || dcInicio.getDate() == null || textAreaDias.getText().isEmpty()) {
//			JOptionPane.showMessageDialog(this, "Rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
//		} else {
//			muestraDialogo();
//		}
//	}
	private JButton getBtnSeleccionarEmpleado() {
		if (btnSeleccionarEmpleado == null) {
			btnSeleccionarEmpleado = new JButton("Seleccionar empleado");
			btnSeleccionarEmpleado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout)contentPane.getLayout()).show(contentPane,"panelBuscador" );
				}
			});
			btnSeleccionarEmpleado.setBounds(12, 25, 159, 25);
		}
		return btnSeleccionarEmpleado;
	}
	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
			panelPrincipal.setLayout(null);
			panelPrincipal.add(getPanelEmpleado());
			panelPrincipal.add(getPanel_1());
			panelPrincipal.add(getBtnSalir());
			panelPrincipal.add(getBtnLimpiar());
			panelPrincipal.add(getBtnSiguiente());
		}
		return panelPrincipal;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(12, 67, 560, 22);
			textField.setColumns(10);
		}
		return textField;
	}
	private JPanel getPanel_3() {
		if (panelBuscador == null) {
			panelBuscador = new JPanel();
			panelBuscador.setLayout(null);
			panelBuscador.add(getLblSeleccionarEmpleado());
			//panelBuscador.add(getListEmpleados());
			panelBuscador.add(getTextFieldBuscarDni());
			panelBuscador.add(getButtonBuscarDni());
			panelBuscador.add(getTextFieldBuscarNombre());
			panelBuscador.add(getButtonBuscarNombre());
			panelBuscador.add(getButtonAceptar());
			panelBuscador.add(getButtonCancelar());
			panelBuscador.add(getChckbxMedico());
			panelBuscador.add(getChckbxEnfermero());
			panelBuscador.add(getScrollPane());
		}
		return panelBuscador;
	}
	private JLabel getLblSeleccionarEmpleado() {
		if (lblSeleccionarEmpleado == null) {
			lblSeleccionarEmpleado = new JLabel("Seleccionar empleado");
			lblSeleccionarEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblSeleccionarEmpleado.setBounds(12, 24, 230, 34);
		}
		return lblSeleccionarEmpleado;
	}
//	private JList<EmpleadoDto> getListEmpleados() {
//		if (listEmpleados == null) {
//			listEmpleados = new JList<EmpleadoDto>((ListModel) null);
//			listEmpleados.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//			listEmpleados.setBounds(12, 158, 572, 150);
//		}
//		return listEmpleados;
//	}
	private JTextField getTextFieldBuscarDni() {
		if (textFieldBuscarDni == null) {
			textFieldBuscarDni = new JTextField();
			textFieldBuscarDni.setColumns(10);
			textFieldBuscarDni.setBounds(12, 125, 113, 20);
		}
		return textFieldBuscarDni;
	}
	private JButton getButtonBuscarDni() {
		if (buttonBuscarDni == null) {
			buttonBuscarDni = new JButton("Buscar por ID");
			buttonBuscarDni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxMedico.isSelected()) {
						for(EmpleadoDto em : jc.getMedicos()) {
							if(Integer.parseInt(textFieldBuscarDni.getText())==em.id) {
								DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
								filtro.addElement(em);
								list.setModel(filtro);
							}
						}
					}else if(chckbxEnfermero.isSelected()) {
						for(EmpleadoDto em : jc.getEnfermeros()) {
							if(Integer.parseInt(textFieldBuscarDni.getText())==em.id) {
								DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
								filtro.addElement(em);
								list.setModel(filtro);
							}
						}
					}
					
				}
			});
			buttonBuscarDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
			buttonBuscarDni.setBounds(137, 122, 124, 23);
		}
		return buttonBuscarDni;
	}
	private JTextField getTextFieldBuscarNombre() {
		if (textFieldBuscarNombre == null) {
			textFieldBuscarNombre = new JTextField();
			textFieldBuscarNombre.setColumns(10);
			textFieldBuscarNombre.setBounds(276, 125, 152, 20);
		}
		return textFieldBuscarNombre;
	}
	private JButton getButtonBuscarNombre() {
		if (buttonBuscarNombre == null) {
			buttonBuscarNombre = new JButton("Buscar por nombre");
			buttonBuscarNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chckbxMedico.isSelected()) {
						for(EmpleadoDto em : jc.getMedicos()) {
							if(textFieldBuscarNombre.getText().equals(em.nombre)) {
								DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
								filtro.addElement(em);
								list.setModel(filtro);
							}
						}
					}else if(chckbxEnfermero.isSelected()) {
						for(EmpleadoDto em : jc.getEnfermeros()) {
							if(textFieldBuscarNombre.getText().equals(em.nombre)) {
								DefaultListModel<EmpleadoDto> filtro = new DefaultListModel<EmpleadoDto>();
								filtro.addElement(em);
								list.setModel(filtro);
							}
						}
					}
				}
			});
			buttonBuscarNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			buttonBuscarNombre.setBounds(437, 122, 147, 23);
		}
		return buttonBuscarNombre;
	}
	private JButton getButtonAceptar() {
		if (buttonAceptar == null) {
			buttonAceptar = new JButton("Aceptar");
			buttonAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			buttonAceptar.setBounds(496, 370, 89, 23);
		}
		return buttonAceptar;
	}
	private JButton getButtonCancelar() {
		if (buttonCancelar == null) {
			buttonCancelar = new JButton("Cancelar");
			buttonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			buttonCancelar.setBounds(395, 370, 89, 23);
		}
		return buttonCancelar;
	}
	private JCheckBox getChckbxMedico() {
		if (chckbxMedico == null) {
			chckbxMedico = new JCheckBox("M\u00E9dico");
			chckbxMedico.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if(chckbxMedico.isSelected()) {
						list.setModel(listModelMedicos);
					}
				}
			});
			buttonGroup.add(chckbxMedico);
			chckbxMedico.setBounds(12, 74, 113, 25);
		}
		return chckbxMedico;
	}
	private JCheckBox getChckbxEnfermero() {
		if (chckbxEnfermero == null) {
			chckbxEnfermero = new JCheckBox("Enfermero");
			chckbxEnfermero.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if(chckbxEnfermero.isSelected()) {
						list.setModel(listModelEnfermeros);
					}
				}
			});
			buttonGroup.add(chckbxEnfermero);
			chckbxEnfermero.setBounds(129, 74, 113, 25);
		}
		return chckbxEnfermero;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 173, 572, 184);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JList<EmpleadoDto> getList() {
		if (list == null) {
			list = new JList<EmpleadoDto>();
		}
		return list;
	}
}