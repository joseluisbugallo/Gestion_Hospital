package ui.admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import business.JornadaController;
import business.dto.EmpleadoDto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaModificarPaciente extends JDialog {

	private CardLayout cL;
	private JPanel contentPane;
	private JPanel pnlBuscador;
	private JLabel lblSeleccionEmpleado;
	private JCheckBox chckBoxMedico;
	private JCheckBox chckBoxEnfermero;
	private JTextField txtDni;
	private JButton btnDni;
	private JTextField txtNombre;
	private JButton btnNombre;
	private JButton btnAtras;
	private JButton btnModificar;
	private JScrollPane scrListado;
	private JPanel pnlModificarMedico;
	private JList<EmpleadoDto> lsEmpleados;
	private JLabel lblModificarMdico;
	private JPanel pnlDatos;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblSegundoApellido;
	private JTextField txtNombreNuevo;
	private JTextField txtApellido;
	private JTextField txtApellidoDos;
	private JLabel lblDni;
	private JLabel lblCorreo;
	private JPanel pnlCargo;
	private JRadioButton rdbtnMdico;
	private JRadioButton rdbtnEnfermero;
	private JTextField txtDniNuevo;
	private JTextField txtCorreo;
	private JButton btnLimpiarDatos;
	private JButton btnConfirmar;
	private JButton btnVolver;
	private JornadaController jc = new JornadaController();
	DefaultListModel<EmpleadoDto> modelo = new DefaultListModel<>();
	private List<EmpleadoDto> result = new ArrayList<>();
	private EmpleadoDto actual;

	/**
	 * Create the dialog.
	 */
	public VentanaModificarPaciente() {
		setBounds(100, 100, 750, 454);
		cL = new CardLayout(0, 0);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(cL);
		contentPane.add(getPnlBuscador(), "Buscador");
		contentPane.add(getPnlModificarMedico(), "VentanaModificar");
	}

	private void actualizarLista() {
		modelo = new DefaultListModel<>();
		result.forEach(r -> modelo.addElement(r));
		getLsEmpleados().setModel(modelo);
	}

	private JPanel getPnlBuscador() {
		if (pnlBuscador == null) {
			pnlBuscador = new JPanel();
			pnlBuscador.setLayout(null);
			pnlBuscador.add(getLblSeleccionEmpleado());
			pnlBuscador.add(getChckBoxMedico());
			pnlBuscador.add(getChckBoxEnfermero());
			pnlBuscador.add(getTxtDni());
			pnlBuscador.add(getBtnDni());
			pnlBuscador.add(getTxtNombre());
			pnlBuscador.add(getBtnNombre());
			pnlBuscador.add(getBtnAtras());
			pnlBuscador.add(getBtnModificar());
			pnlBuscador.add(getScrollPane_1());
		}
		return pnlBuscador;
	}

	private JLabel getLblSeleccionEmpleado() {
		if (lblSeleccionEmpleado == null) {
			lblSeleccionEmpleado = new JLabel("Seleccionar empleado");
			lblSeleccionEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblSeleccionEmpleado.setBounds(12, 13, 722, 21);
		}
		return lblSeleccionEmpleado;
	}

	private JCheckBox getChckBoxMedico() {
		if (chckBoxMedico == null) {
			chckBoxMedico = new JCheckBox("M\u00E9dico");
			chckBoxMedico.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					buscarPorFiltro();
				}
			});
			chckBoxMedico.setBounds(28, 74, 87, 25);
		}
		return chckBoxMedico;
	}

	private JCheckBox getChckBoxEnfermero() {
		if (chckBoxEnfermero == null) {
			chckBoxEnfermero = new JCheckBox("Enfermero");
			chckBoxEnfermero.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					buscarPorFiltro();
				}
			});
			chckBoxEnfermero.setBounds(145, 75, 113, 25);
		}
		return chckBoxEnfermero;
	}

	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setColumns(10);
			txtDni.setBounds(28, 136, 113, 20);
		}
		return txtDni;
	}

	private JButton getBtnDni() {
		if (btnDni == null) {
			btnDni = new JButton("Buscar por DNI");
			btnDni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarPorFiltro();
				}
			});
			btnDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnDni.setBounds(168, 135, 124, 23);
		}
		return btnDni;
	}

	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(323, 136, 152, 20);
		}
		return txtNombre;
	}

	private JButton getBtnNombre() {
		if (btnNombre == null) {
			btnNombre = new JButton("Buscar por nombre");
			btnNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarPorFiltro();
				}
			});
			btnNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNombre.setBounds(503, 135, 147, 23);
		}
		return btnNombre;
	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					salirVentana();
				}
			});
			btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAtras.setBounds(518, 370, 89, 23);
		}
		return btnAtras;
	}

	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modificarEmpleado();
				}
			});
			btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnModificar.setEnabled(false);
			btnModificar.setBounds(617, 371, 89, 23);
		}
		return btnModificar;
	}

	private JScrollPane getScrollPane_1() {
		if (scrListado == null) {
			scrListado = new JScrollPane();
			scrListado.setBounds(28, 187, 678, 161);
			scrListado.setViewportView(getLsEmpleados());
		}
		return scrListado;
	}

	private JPanel getPnlModificarMedico() {
		if (pnlModificarMedico == null) {
			pnlModificarMedico = new JPanel();
			pnlModificarMedico.setLayout(null);
			pnlModificarMedico.add(getLblModificarMdico());
			pnlModificarMedico.add(getPnlDatos());
			pnlModificarMedico.add(getBtnVolver());
		}
		return pnlModificarMedico;
	}

	private JList<EmpleadoDto> getLsEmpleados() {
		if (lsEmpleados == null) {
			lsEmpleados = new JList<>();
			lsEmpleados.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					actual = lsEmpleados.getSelectedValue();
					if(actual!=null) {
						btnModificar.setEnabled(true);
					}else {
						btnModificar.setEnabled(false);					
					}
				}
			});
		}
		return lsEmpleados;
	}

	private JLabel getLblModificarMdico() {
		if (lblModificarMdico == null) {
			lblModificarMdico = new JLabel("Modificar Empleado");
			lblModificarMdico.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblModificarMdico.setBounds(12, 13, 708, 30);
		}
		return lblModificarMdico;
	}

	private JPanel getPnlDatos() {
		if (pnlDatos == null) {
			pnlDatos = new JPanel();
			pnlDatos.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Datos:",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlDatos.setBounds(12, 84, 708, 265);
			pnlDatos.setLayout(null);
			pnlDatos.add(getLblNombre());
			pnlDatos.add(getLblApellido());
			pnlDatos.add(getLblSegundoApellido());
			pnlDatos.add(getTxtNombreNuevo());
			pnlDatos.add(getTxtApellido());
			pnlDatos.add(getTxtApellidoDos());
			pnlDatos.add(getLblDni());
			pnlDatos.add(getLblCorreo());
			pnlDatos.add(getPnlCargo());
			pnlDatos.add(getTxtDniNuevo());
			pnlDatos.add(getTxtCorreo());
			pnlDatos.add(getBtnLimpiarDatos());
			pnlDatos.add(getBtnConfirmar());
		}
		return pnlDatos;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(12, 48, 189, 26);
		}
		return lblNombre;
	}

	private JLabel getLblApellido() {
		if (lblApellido == null) {
			lblApellido = new JLabel("Apellido:");
			lblApellido.setBounds(12, 112, 189, 26);
		}
		return lblApellido;
	}

	private JLabel getLblSegundoApellido() {
		if (lblSegundoApellido == null) {
			lblSegundoApellido = new JLabel("Segundo Apellido:");
			lblSegundoApellido.setBounds(12, 176, 189, 26);
		}
		return lblSegundoApellido;
	}

	private JTextField getTxtNombreNuevo() {
		if (txtNombreNuevo == null) {
			txtNombreNuevo = new JTextField();
			txtNombreNuevo.setBounds(22, 71, 179, 22);
			txtNombreNuevo.setColumns(10);
		}
		return txtNombreNuevo;
	}

	private JTextField getTxtApellido() {
		if (txtApellido == null) {
			txtApellido = new JTextField();
			txtApellido.setColumns(10);
			txtApellido.setBounds(22, 141, 179, 22);
		}
		return txtApellido;
	}

	private JTextField getTxtApellidoDos() {
		if (txtApellidoDos == null) {
			txtApellidoDos = new JTextField();
			txtApellidoDos.setColumns(10);
			txtApellidoDos.setBounds(22, 206, 179, 22);
		}
		return txtApellidoDos;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setBounds(305, 53, 56, 16);
		}
		return lblDni;
	}

	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo:");
			lblCorreo.setBounds(305, 117, 56, 16);
		}
		return lblCorreo;
	}

	private JPanel getPnlCargo() {
		if (pnlCargo == null) {
			pnlCargo = new JPanel();
			pnlCargo.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Cargo:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlCargo.setBounds(305, 176, 233, 58);
			pnlCargo.add(getRdbtnMdico());
			pnlCargo.add(getRdbtnEnfermero());
		}
		return pnlCargo;
	}

	private JRadioButton getRdbtnMdico() {
		if (rdbtnMdico == null) {
			rdbtnMdico = new JRadioButton("M\u00E9dico");
			rdbtnMdico.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if (rdbtnMdico.isSelected()) {
						getRdbtnEnfermero().setSelected(false);
					} else {
						getRdbtnEnfermero().setSelected(true);
					}
				}
			});
		}
		return rdbtnMdico;
	}

	private JRadioButton getRdbtnEnfermero() {
		if (rdbtnEnfermero == null) {
			rdbtnEnfermero = new JRadioButton("Enfermero");
			rdbtnMdico.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if (rdbtnEnfermero.isSelected()) {
						getRdbtnMdico().setSelected(false);
					} else {
						getRdbtnMdico().setSelected(true);
					}
				}
			});
		}
		return rdbtnEnfermero;
	}

	private JTextField getTxtDniNuevo() {
		if (txtDniNuevo == null) {
			txtDniNuevo = new JTextField();
			txtDniNuevo.setColumns(10);
			txtDniNuevo.setBounds(315, 71, 179, 22);
		}
		return txtDniNuevo;
	}

	private JTextField getTxtCorreo() {
		if (txtCorreo == null) {
			txtCorreo = new JTextField();
			txtCorreo.setColumns(10);
			txtCorreo.setBounds(315, 141, 179, 22);
		}
		return txtCorreo;
	}

	private JButton getBtnLimpiarDatos() {
		if (btnLimpiarDatos == null) {
			btnLimpiarDatos = new JButton("Limpiar Datos");
			btnLimpiarDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiarDatos();
				}
			});
			btnLimpiarDatos.setBounds(549, 106, 147, 39);
		}
		return btnLimpiarDatos;
	}

	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(checkDataValues()==JOptionPane.YES_OPTION) {
						actualizarEmpleado();
						mostrarMensaje("Se ha modificado el empleado.", "Empleado modificado", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnConfirmar.setBounds(549, 54, 147, 39);
		}
		return btnConfirmar;
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					volver();
				}
			});
			btnVolver.setBounds(623, 369, 97, 25);
		}
		return btnVolver;
	}

	private void modificarEmpleado() {
		cL.show(this.contentPane, "VentanaModificar");
		cargarDatos();
	}

	private void cargarDatos() {
		String[] nCompleto = actual.nombre.split(" ");
		if (nCompleto.length == 4) {
			getTxtNombreNuevo().setText(nCompleto[0] + " " + nCompleto[1]);
			getTxtApellido().setText(nCompleto[2]);
			getTxtApellidoDos().setText(nCompleto[3]);
		} else if(nCompleto.length==3) {
			getTxtNombreNuevo().setText(nCompleto[0]);
			getTxtApellido().setText(nCompleto[1]);
			getTxtApellidoDos().setText(nCompleto[2]);
		}
		else if(nCompleto.length==2){
			getTxtNombreNuevo().setText(nCompleto[0]);
			getTxtApellido().setText(nCompleto[1]);
		}
		else {
			getTxtNombreNuevo().setText(nCompleto[0]);
		}
		getTxtCorreo().setText(actual.correo);
		getTxtDniNuevo().setText(actual.dni);
		if(actual.cargo.equals("Enfermero")) {
			getRdbtnEnfermero().setSelected(true);
		} else {
			getRdbtnMdico().setSelected(true);
		}
	}
	
	private void buscarPorFiltro() {
		result = new ArrayList<>();
		if(chckBoxMedico.isSelected()) {
			result.addAll(jc.getMedicos());
		}
		
		if(chckBoxEnfermero.isSelected()) {
			result.addAll(jc.getEnfermeros());
		}
		
		if(!txtNombre.getText().isEmpty()) {
			List<EmpleadoDto> aux = result;
			result = aux.stream().filter(e->e.nombre.contains(txtNombre.getText())).collect(Collectors.toList());
		}
		if(!txtDni.getText().isEmpty()) {
			List<EmpleadoDto> aux = result;
			result = aux.stream().filter(e->e.dni.contains(txtDni.getText())).collect(Collectors.toList());
		}
		actualizarLista();
	}
	
	private void salirVentana() {
		if(JOptionPane.showConfirmDialog(this,"¿Está seguro de querer salir?",
				"Salir",JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE)==JOptionPane.YES_OPTION){
			dispose();
		}
	}
	
	private void limpiarDatos() {
		getTxtNombreNuevo().setText("");
		getTxtApellido().setText("");
		getTxtApellidoDos().setText("");
		getTxtDniNuevo().setText("");
		getTxtCorreo().setText("");
		getRdbtnMdico().setSelected(true);
		getRdbtnEnfermero().setSelected(false);
	}
	
	private int checkDataValues() {
		String errores="";
		if(getTxtNombreNuevo().getText().isEmpty()) {
			errores+="El nombre esta vacio.\n";
		}
		if(getTxtApellido().getText().isEmpty()) {
			errores+= "El apellido esta vacio.\n";
		}
		if(getTxtDniNuevo().getText().isEmpty()) {
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
	
	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}
	
	private void actualizarEmpleado() {
		EmpleadoDto empleado = new EmpleadoDto();
		empleado.id=actual.id;
		empleado.nombre= getTxtNombreNuevo().getText() +  " "
				+ getTxtApellido().getText() + " " +  getTxtApellidoDos().getText();
		System.out.println(empleado.nombre);
		empleado.cargo= getRdbtnEnfermero().isSelected()? "Enfermero": "Medico";
		empleado.dni = getTxtDni().getText();
		empleado.correo = getTxtCorreo().getText();
		actual=empleado;
		jc.actualizarEmpleado(actual);
	}
	
	private void volver() {
		modelo = new DefaultListModel<>();
		getTxtNombre().setText("");
		getTxtDni().setText("");
		getChckBoxEnfermero().setSelected(false);
		getChckBoxMedico().setSelected(false);
		cL.show(contentPane, "Buscador");
	}

}
