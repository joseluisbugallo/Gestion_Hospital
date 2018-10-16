package ui.jornada;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import business.JornadaController;
import business.dto.EmpleadoDto;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaJornadaLaboral extends JFrame{
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
	private JButton btnCancelar;
	private JCheckBox chckbxMedico;
	private JCheckBox chckbxEnfermero;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblSeleccionarEmpleado;
	private JComboBox<EmpleadoDto> cmbxEmpleados;
	private JPanel panelEmpleado;
	private JPanel panelJornada;
	
	private JornadaController jc = new JornadaController();
	private JList<String> listDias;
	private JLabel lblDas;
	private JTextArea textAreaDias;
	private JButton btnSalir;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaJornadaLaboral frame = new VentanaJornadaLaboral();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanaJornadaLaboral() {
		setResizable(false);
		setTitle("Asignar jornada laboral a m\u00E9dicos o enfermeros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelEmpleado());
		contentPane.add(getPanel_1());
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnSiguiente());
		contentPane.add(getBtnSalir());
	}
	private JDateChooser getDcInicio() {
		if (dcInicio == null) {
			dcInicio = new JDateChooser();
			dcInicio.setBounds(411, 35, 139, 22);
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
			dcFin.setBounds(139, 35, 139, 22);
			dcFin.setDateFormatString("dd/MM/yy HH:mm");
		}
		return dcFin;
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setBounds(471, 360, 97, 25);
			btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnSiguiente;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			btnCancelar.setBounds(354, 360, 107, 25);
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return btnCancelar;
	}
	protected void inicializar() {
		buttonGroup.clearSelection();
		textAreaDias.setText("");
		getDcFin().setCalendar(null);
		getDcInicio().setCalendar(null);
		cmbxEmpleados.removeAllItems();
		listDias.clearSelection();
		
	}

	private JCheckBox getChckbxMedico() {
		if (chckbxMedico == null) {
			chckbxMedico = new JCheckBox("M\u00E9dico");
			chckbxMedico.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(chckbxMedico.isSelected()) {
						List<EmpleadoDto> empleados= jc.getMedicos();
							cargarModelo(empleados);
						}
				}
			});
			chckbxMedico.setBounds(8, 24, 78, 16);
			buttonGroup.add(chckbxMedico);
		}
		return chckbxMedico;
	}
	private JCheckBox getChckbxEnfermero() {
		if (chckbxEnfermero == null) {
			chckbxEnfermero = new JCheckBox("Enfermero");
			chckbxEnfermero.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(chckbxEnfermero.isSelected()) {
						List<EmpleadoDto> empleados= jc.getEnfermeros();
							cargarModelo(empleados);
						}
				}
			});
			chckbxEnfermero.setBounds(106, 20, 105, 25);
			buttonGroup.add(chckbxEnfermero);
		}
		return chckbxEnfermero;
	}
	private JLabel getLblSeleccionarEmpleado() {
		if (lblSeleccionarEmpleado == null) {
			lblSeleccionarEmpleado = new JLabel("Seleccionar empleado:");
			lblSeleccionarEmpleado.setBounds(12, 53, 139, 16);
		}
		return lblSeleccionarEmpleado;
	}
	private JComboBox<EmpleadoDto> getCmbxEmpleados() {
		if (cmbxEmpleados == null) {
			cmbxEmpleados = new JComboBox<EmpleadoDto>();
			cmbxEmpleados.setBounds(147, 49, 370, 25);
		}
		return cmbxEmpleados;
	}
	private JPanel getPanelEmpleado() {
		if (panelEmpleado == null) {
			panelEmpleado = new JPanel();
			panelEmpleado.setBounds(12, 36, 570, 91);
			panelEmpleado.setBorder(new TitledBorder(null, "Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelEmpleado.setLayout(null);
			panelEmpleado.add(getChckbxMedico());
			panelEmpleado.add(getChckbxEnfermero());
			panelEmpleado.add(getLblSeleccionarEmpleado());
			panelEmpleado.add(getCmbxEmpleados());
		}
		return panelEmpleado;
	}
	private JPanel getPanel_1() {
		if (panelJornada == null) {
			panelJornada = new JPanel();
			panelJornada.setBounds(12, 134, 570, 213);
			panelJornada.setBorder(new TitledBorder(null, "Jornada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
				String[] values = new String[] {"Lunes", "Martes", "Mi\u00E9rcoles", "Jueves", "Viernes", "S\u00E1bado", "Domingo"};
				public int getSize() {
					return values.length;
				}
				public String getElementAt(int index) {
					return values[index];
				}
			});
			listDias.setBounds(12, 67, 89, 133);
		}
		return listDias;
	}
	
	@SuppressWarnings("deprecation")
	private void refrescarSeleccion() {
		Object[] seleccion = listDias.getSelectedValues();
		String nuevoTexto = "";
		for(Object o: seleccion) 
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
			textAreaDias.setBounds(176, 104, 292, 43);
		}
		return textAreaDias;
	}
	
	private void cargarModelo(List<EmpleadoDto> empleados) {
		cmbxEmpleados.removeAllItems();
		for(EmpleadoDto e: empleados) {
			cmbxEmpleados.addItem(e);
		}
	}
	
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnSalir.setBounds(245, 360, 97, 25);
		}
		return btnSalir;
	}
}