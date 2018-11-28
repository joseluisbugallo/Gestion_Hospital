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

import business.dto.EmpleadoDto;
import persistence.DataEmpleado;

public class VentanaAddEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSalir;
	private JPanel panel;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblApellido;
	private JTextField txtApellido;
	private JLabel lblSegundoApellido;
	private JTextField txtSegundoapellido;
	private JPanel pnlcargo;
	private JRadioButton rdbtnMedico;
	private JRadioButton rdbtnEnfermero;
	private JLabel lblDni;
	private JLabel lblCorreo;
	private JButton btnCrear;
	private JButton btnLimpiarDatos;
	private JTextField txtDni;
	private JTextField txtCorreo;
	
	private DataEmpleado dataEmpleado = new DataEmpleado();
	/**
	 * Create the dialog.
	 */
	public VentanaAddEmpleado() {
		setTitle("A\u00F1adir nuevo Empleado");
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
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Datos del nuevo Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(12, 13, 357, 349);
			panel.setLayout(null);
			panel.add(getLblNombre());
			panel.add(getTxtNombre());
			panel.add(getLblApellido());
			panel.add(getTxtApellido());
			panel.add(getLblSegundoApellido());
			panel.add(getTxtSegundoapellido());
			panel.add(getPnlcargo());
			panel.add(getLblDni());
			panel.add(getLblCorreo());
			panel.add(getTxtDni());
			panel.add(getTxtCorreo());
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
	private JPanel getPnlcargo() {
		if (pnlcargo == null) {
			pnlcargo = new JPanel();
			pnlcargo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Cargo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlcargo.setBounds(58, 268, 234, 68);
			pnlcargo.add(getRdbtnMedico());
			pnlcargo.add(getRdbtnEnfermero());
		}
		return pnlcargo;
	}
	private JRadioButton getRdbtnMedico() {
		if (rdbtnMedico == null) {
			rdbtnMedico = new JRadioButton("Medico");
			rdbtnMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbtnMedico.isSelected()) {
						getRdbtnEnfermero().setSelected(false);
					} else {
						getRdbtnEnfermero().setSelected(true);
					}
				}
			});
		}
		return rdbtnMedico;
	}
	private JRadioButton getRdbtnEnfermero() {
		if (rdbtnEnfermero == null) {
			rdbtnEnfermero = new JRadioButton("Enfermero");
			rdbtnEnfermero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbtnEnfermero.isSelected()) {
						getRdbtnMedico().setSelected(false);
					} else {
						getRdbtnMedico().setSelected(true);
					}
				}
			});
		}
		return rdbtnEnfermero;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setBounds(43, 176, 108, 23);
		}
		return lblDni;
	}
	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo:");
			lblCorreo.setBounds(43, 220, 108, 23);
		}
		return lblCorreo;
	}
	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(checkDataValues() == JOptionPane.YES_OPTION) {					
						addMedico();
						mostrarMensaje("Se ha añadido un nuevo empleado.", "Se ha creado el medico", JOptionPane.INFORMATION_MESSAGE);
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
	private JTextField getTxtCorreo() {
		if (txtCorreo == null) {
			txtCorreo = new JTextField();
			txtCorreo.setBounds(154, 220, 160, 22);
			txtCorreo.setColumns(10);
		}
		return txtCorreo;
	}
		
	private void limpiarDatos() {
		getTxtNombre().setText("");
		getTxtApellido().setText("");
		getTxtSegundoapellido().setText("");
		getTxtDni().setText("");
		getTxtCorreo().setText("");
		getRdbtnMedico().setSelected(true);
		getRdbtnEnfermero().setSelected(false);
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
		if(getTxtCorreo().getText().isEmpty()) {
			errores+= "El correo esta vacio.\n";
		}
		if(!errores.isEmpty()) {
			mostrarMensaje("No se ha podido crear el empleado por los siguientes motivos:\n"
					+ errores, "Error al crear", JOptionPane.ERROR_MESSAGE); 
			}	else {
				return JOptionPane.showConfirmDialog(this,"¿Esta seguro de que estos son los datos correctos?","Confirmacion", JOptionPane.YES_NO_OPTION);
				
			}
		return -2;
	}

	private void addMedico() {
		EmpleadoDto empleado = new EmpleadoDto();
		empleado.nombre= getTxtNombre().getText() +  " "
				+ getTxtApellido().getText() + " " +  getTxtSegundoapellido().getText();
		empleado.cargo= getRdbtnEnfermero().isSelected()? "Enfermero": "Medico";
		empleado.dni = getTxtDni().getText();
		empleado.correo = getTxtCorreo().getText();
		empleado.estado= "Activado";
		dataEmpleado.add(empleado);
	}

	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}
	
	private void salir() {
		if(JOptionPane.showConfirmDialog(this,  "Va a salir de la ventana", "Salir", JOptionPane.OK_CANCEL_OPTION)== JOptionPane.OK_OPTION) {
			dispose();
			}
	}
}
