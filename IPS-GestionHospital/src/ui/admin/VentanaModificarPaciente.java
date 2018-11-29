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
import business.PacientesController;
import business.dto.EmpleadoDto;
import business.dto.PacienteDto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class VentanaModificarPaciente extends JDialog {

	private CardLayout cL;
	private JPanel contentPane;
	private JPanel pnlBuscador;
	private JLabel lblSeleccionPaciente;
	private JTextField txtDni;
	private JButton btnDni;
	private JTextField txtNombre;
	private JButton btnNombre;
	private JButton btnAtras;
	private JButton btnModificar;
	private JScrollPane scrListado;
	private JPanel pnlModificarPaciente;
	private JList<PacienteDto> lsPacientes;
	private JLabel lblModificarPaciente;
	private JPanel pnlDatos;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblSegundoApellido;
	private JTextField txtNombreNuevo;
	private JTextField txtApellido;
	private JTextField txtApellidoDos;
	private JLabel lblDni;
	private JLabel lblContact;
	private JTextField txtDniNuevo;
	private JButton btnLimpiarDatos;
	private JButton btnConfirmar;
	private JButton btnVolver;
	private PacientesController pc = new PacientesController();
	DefaultListModel<PacienteDto> modelo = new DefaultListModel<>();
	private List<PacienteDto> result = new ArrayList<>();
	private PacienteDto actual;
	private JTextArea txtContacto;

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
		contentPane.add(getPnlModificarPaciente(), "VentanaModificar");
	}

	private void actualizarLista() {
		modelo = new DefaultListModel<>();
		result.forEach(r -> modelo.addElement(r));
		getLsPacientes().setModel(modelo);
	}

	private JPanel getPnlBuscador() {
		if (pnlBuscador == null) {
			pnlBuscador = new JPanel();
			pnlBuscador.setLayout(null);
			pnlBuscador.add(getLblSeleccionPaciente());
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

	private JLabel getLblSeleccionPaciente() {
		if (lblSeleccionPaciente == null) {
			lblSeleccionPaciente = new JLabel("Seleccionar paciente");
			lblSeleccionPaciente.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblSeleccionPaciente.setBounds(12, 13, 722, 21);
		}
		return lblSeleccionPaciente;
	}

	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setColumns(10);
			txtDni.setBounds(28, 47, 113, 20);
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
			btnDni.setBounds(153, 47, 124, 23);
		}
		return btnDni;
	}

	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(335, 47, 152, 20);
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
			btnNombre.setBounds(499, 47, 147, 23);
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
			scrListado.setBounds(28, 111, 678, 237);
			scrListado.setViewportView(getLsPacientes());
		}
		return scrListado;
	}

	private JPanel getPnlModificarPaciente() {
		if (pnlModificarPaciente == null) {
			pnlModificarPaciente = new JPanel();
			pnlModificarPaciente.setLayout(null);
			pnlModificarPaciente.add(getLblModificarPaciente());
			pnlModificarPaciente.add(getPnlDatos());
			pnlModificarPaciente.add(getBtnVolver());
		}
		return pnlModificarPaciente;
	}

	private JList<PacienteDto> getLsPacientes() {
		if (lsPacientes == null) {
			lsPacientes = new JList<>();
			lsPacientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					actual = lsPacientes.getSelectedValue();
					if(actual!=null) {
						btnModificar.setEnabled(true);
					}else {
						btnModificar.setEnabled(false);					
					}
				}
			});
		}
		return lsPacientes;
	}

	private JLabel getLblModificarPaciente() {
		if (lblModificarPaciente == null) {
			lblModificarPaciente = new JLabel("Modificar paciente");
			lblModificarPaciente.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblModificarPaciente.setBounds(12, 13, 708, 30);
		}
		return lblModificarPaciente;
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
			pnlDatos.add(getLblContact());
			pnlDatos.add(getTxtDniNuevo());
			pnlDatos.add(getBtnLimpiarDatos());
			pnlDatos.add(getBtnConfirmar());
			pnlDatos.add(getTxtContacto());
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

	private JLabel getLblContact() {
		if (lblContact == null) {
			lblContact = new JLabel("Contacto:");
			lblContact.setBounds(305, 117, 56, 16);
		}
		return lblContact;
	}

	private JTextField getTxtDniNuevo() {
		if (txtDniNuevo == null) {
			txtDniNuevo = new JTextField();
			txtDniNuevo.setColumns(10);
			txtDniNuevo.setBounds(315, 71, 179, 22);
		}
		return txtDniNuevo;
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
		getTxtContacto().setText(actual.contacto);
		getTxtDniNuevo().setText(actual.dni);

	}
	
	private void buscarPorFiltro() {
		result = new ArrayList<>();
		result.addAll(pc.listadoPacientes());	
		if(!txtNombre.getText().isEmpty()) {
			List<PacienteDto> aux = result;
			result = aux.stream().filter(e->e.nombre.contains(txtNombre.getText())).collect(Collectors.toList());
		}
		if(!txtDni.getText().isEmpty()) {
			List<PacienteDto> aux = result;
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
		getTxtContacto().setText("");
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
		if(getTxtContacto().getText().isEmpty()) {
			errores+= "No se ha introducido ninguna informacion de contacto.\n";
		}
		if(!errores.isEmpty()) {
			mostrarMensaje("No se ha podido modificar el paceinte por los siguientes motivos:\n"
					+ errores, "Error al modificar", JOptionPane.ERROR_MESSAGE); 
			}	else {
				return JOptionPane.showConfirmDialog(this,"¿Esta seguro de que estos son los datos correctos?","Confirmacion", JOptionPane.YES_NO_OPTION);
				
			}
		return -2;
	}
	
	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}
	
	private void actualizarEmpleado() {
		PacienteDto paciente = new PacienteDto();
		paciente.id=actual.id;
		paciente.estado=actual.estado;
		paciente.nombre= getTxtNombreNuevo().getText() +  " "
				+ getTxtApellido().getText() + " " +  getTxtApellidoDos().getText();
		System.out.println(paciente.nombre);
		paciente.contacto = getTxtContacto().getText();
		paciente.dni = getTxtDniNuevo().getText();
		actual=paciente;
		pc.actualizarPaciente(actual);
	}
	
	private void volver() {
		result= new ArrayList<>();
		actualizarLista();
		getTxtNombre().setText("");
		getTxtDni().setText("");
		getTxtContacto().setText("");
		cL.show(contentPane, "Buscador");
	}
	private JTextArea getTxtContacto() {
		if (txtContacto == null) {
			txtContacto = new JTextArea();
			txtContacto.setBounds(315, 141, 179, 80);
		}
		return txtContacto;
	}
}
