package ui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import business.PacientesController;
import business.dto.EmpleadoDto;
import business.dto.PacienteDto;
import persistence.DataEmpleado;
import javax.swing.JTextArea;

public class VentanaAddPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSalir;
	private JPanel panel;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblApellido;
	private JTextField txtApellido;
	private JLabel lblSegundoApellido;
	private JTextField txtSegundoapellido;
	private JLabel lblDni;
	private JButton btnCrear;
	private JButton btnLimpiarDatos;
	private JTextField txtDni;
	
	private PacientesController pc= new PacientesController();
	private JPanel pnlDatosContacto;
	private JTextArea textArea;
	/**
	 * Create the dialog.
	 */
	public VentanaAddPaciente() {
		setTitle("Registrar nuevo paciente");
		setResizable(false);
		setBounds(100, 100, 620, 454);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getBtnSalir());
		contentPanel.add(getPanel());
		contentPanel.add(getBtnCrear());
		contentPanel.add(getBtnLimpiarDatos());
	}
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					salir();
				}
			});
			btnSalir.setBounds(453, 381, 149, 25);
		}
		return btnSalir;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Datos del nuevo Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(12, 13, 357, 349);
			panel.setLayout(null);
			panel.add(getLblNombre());
			panel.add(getTxtNombre());
			panel.add(getLblApellido());
			panel.add(getTxtApellido());
			panel.add(getLblSegundoApellido());
			panel.add(getTxtSegundoapellido());
			panel.add(getLblDni());
			panel.add(getTxtDni());
			panel.add(getPnlDatosContacto());
		}
		return panel;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(43, 35, 78, 23);
		}
		return lblNombre;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(154, 35, 160, 22);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JLabel getLblApellido() {
		if (lblApellido == null) {
			lblApellido = new JLabel("Apellido:");
			lblApellido.setBounds(43, 85, 78, 16);
		}
		return lblApellido;
	}
	private JTextField getTxtApellido() {
		if (txtApellido == null) {
			txtApellido = new JTextField();
			txtApellido.setBounds(154, 82, 160, 22);
			txtApellido.setColumns(10);
		}
		return txtApellido;
	}
	private JLabel getLblSegundoApellido() {
		if (lblSegundoApellido == null) {
			lblSegundoApellido = new JLabel("Segundo Apellido:");
			lblSegundoApellido.setBounds(43, 133, 108, 16);
		}
		return lblSegundoApellido;
	}
	private JTextField getTxtSegundoapellido() {
		if (txtSegundoapellido == null) {
			txtSegundoapellido = new JTextField();
			txtSegundoapellido.setBounds(154, 130, 160, 22);
			txtSegundoapellido.setColumns(10);
		}
		return txtSegundoapellido;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setBounds(43, 176, 108, 23);
		}
		return lblDni;
	}
	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(checkDataValues() == JOptionPane.YES_OPTION) {					
						addPaciente();
						mostrarMensaje("Se ha registrado un nuevo pacientee.", "Se ha creado el paciente", JOptionPane.INFORMATION_MESSAGE);
						limpiarDatos();
						}
				}
			});
			btnCrear.setBounds(453, 39, 149, 25);
		}
		return btnCrear;
	}
	private JButton getBtnLimpiarDatos() {
		if (btnLimpiarDatos == null) {
			btnLimpiarDatos = new JButton("Limpiar Datos");
			btnLimpiarDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiarDatos();
				}
			});
			btnLimpiarDatos.setBounds(453, 77, 149, 25);
		}
		return btnLimpiarDatos;
	}
	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setBounds(154, 176, 160, 22);
			txtDni.setColumns(10);
		}
		return txtDni;
	}
		
	private void limpiarDatos() {
		getTxtNombre().setText("");
		getTxtApellido().setText("");
		getTxtSegundoapellido().setText("");
		getTxtDni().setText("");
		getTextArea().setText("");

	}
	
	private int checkDataValues() {
		String errores="";
		if(getTxtNombre().getText().isEmpty()) {
			errores+="El nombre esta vacio.\n";
		}
		if(getTxtApellido().getText().isEmpty()) {
			errores+= "El apellido esta vacio.\n";
		}
		if(getTxtDni().getText().isEmpty()) {
			errores+= "El DNI esta vacio.\n";
		}
		if(getTextArea().getText().isEmpty()) {
			errores+= "No ha introducido ningún dato de contacto.\n";
		}
		if(!errores.isEmpty()) {
			mostrarMensaje("No se ha podido crear el empleado por los siguientes motivos:\n"
					+ errores, "Error al crear", JOptionPane.ERROR_MESSAGE); 
			}	else {
				return JOptionPane.showConfirmDialog(this,"¿Esta seguro de que estos son los datos correctos?","Confirmacion", JOptionPane.YES_NO_OPTION);
				
			}
		return -2;
	}

	private void addPaciente() {
		PacienteDto paciente = new PacienteDto();
		paciente.nombre= getTxtNombre().getText() +  " "
				+ getTxtApellido().getText() + " " +  getTxtSegundoapellido().getText();		
		paciente.dni = getTxtDni().getText();
		paciente.contacto = getTextArea().getText();
		paciente.estado= "Activado";
		pc.addPaciente(paciente);
	}

	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}
	
	private void salir() {
		if(JOptionPane.showConfirmDialog(this,  "Va a salir de la ventana", "Salir", JOptionPane.OK_CANCEL_OPTION)== JOptionPane.OK_OPTION) {
			dispose();
			}
	}
	private JPanel getPnlDatosContacto() {
		if (pnlDatosContacto == null) {
			pnlDatosContacto = new JPanel();
			pnlDatosContacto.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Datos de Contacto:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlDatosContacto.setBounds(36, 226, 278, 110);
			pnlDatosContacto.setLayout(new BorderLayout(0, 0));
			pnlDatosContacto.add(getTextArea(), BorderLayout.CENTER);
		}
		return pnlDatosContacto;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setLineWrap(true);
		}
		return textArea;
	}
}
