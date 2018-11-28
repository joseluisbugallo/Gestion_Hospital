package ui.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import business.JornadaController;
import business.dto.EmpleadoDto;

public class VentanaModificarEmpleado extends JDialog {

	private JPanel contentPane;
	private JLabel label;
	private JCheckBox chckbxMedico;
	private JCheckBox chckbxEnfermero;
	private JTextField txtDni;
	private JButton btnDni;
	private JTextField txtNombre;
	private JButton btnNombre;
	private JList<EmpleadoDto> lsEmpleados;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JornadaController jc = new JornadaController();
	private List<EmpleadoDto> result = new ArrayList<>();

	/**
	 * Create the dialog.
	 */
	public VentanaModificarEmpleado() {
		setBounds(100, 100, 750, 454);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLabel());
		contentPane.add(getChckbxMedico());
		contentPane.add(getChckbxEnfermero());
		contentPane.add(getTxtDni());
		contentPane.add(getBtnDni());
		contentPane.add(getTxtNombre());
		contentPane.add(getBtnNombre());
		contentPane.add(getLsEmpleados());
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnModificar());
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Seleccionar empleado");
			label.setFont(new Font("Tahoma", Font.PLAIN, 17));
			label.setBounds(12, 13, 722, 21);
		}
		return label;
	}
	private JCheckBox getChckbxMedico() {
		if (chckbxMedico == null) {
			chckbxMedico = new JCheckBox("M\u00E9dico");
			chckbxMedico.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					buscarPorFiltros();
				}
			});
			chckbxMedico.setBounds(30, 74, 87, 25);
		}
		return chckbxMedico;
	}
	private JCheckBox getChckbxEnfermero() {
		if (chckbxEnfermero == null) {
			chckbxEnfermero = new JCheckBox("Enfermero");
			chckbxEnfermero.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					buscarPorFiltros();
				}
			});
			chckbxEnfermero.setBounds(147, 75, 113, 25);
		}
		return chckbxEnfermero;
	}
	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setColumns(10);
			txtDni.setBounds(30, 136, 113, 20);
		}
		return txtDni;
	}
	private JButton getBtnDni() {
		if (btnDni == null) {
			btnDni = new JButton("Buscar por DNI");
			btnDni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarPorFiltros();
				}
			});
			btnDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnDni.setBounds(170, 135, 124, 23);
		}
		return btnDni;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(325, 136, 152, 20);
		}
		return txtNombre;
	}
	private JButton getBtnNombre() {
		if (btnNombre == null) {
			btnNombre = new JButton("Buscar por nombre");
			btnNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarPorFiltros();
				}
			});
			btnNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNombre.setBounds(505, 135, 147, 23);
		}
		return btnNombre;
	}
	private JList<EmpleadoDto> getLsEmpleados() {
		if (lsEmpleados == null) {
			lsEmpleados = new JList<EmpleadoDto>();
			lsEmpleados.setBounds(30, 187, 624, 161);
		}
		return lsEmpleados;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Atras");
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCancelar.setBounds(520, 370, 89, 23);
		}
		return btnCancelar;
	}
	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnModificar.setBounds(619, 371, 89, 23);
		}
		return btnModificar;
	}
	
	private void buscarPorFiltros() {

		if(getChckbxEnfermero().isSelected()) {
			result.addAll(jc.getEnfermeros()
					.isEmpty()?new ArrayList<>():jc.getEnfermeros()
							);
		} if(getChckbxMedico().isSelected()) {
			result.addAll(jc.getMedicos()
					.isEmpty()?new ArrayList<>():jc.getMedicos()
							);
		}			
		List<Predicate<EmpleadoDto>> listaPredicados = new ArrayList<>();
		if(!getTxtNombre().getText().isEmpty()) {
			listaPredicados.add(en->en.nombre.contains(getTxtNombre().getText()));
		}
		if(!getTxtDni().getText().isEmpty()) {
			listaPredicados.add(ed->ed.dni.contains(getTxtDni().getText()));
		}
		for(Predicate<EmpleadoDto> p: listaPredicados) {
			result.stream().filter(p).collect(Collectors.toList());
		}
		actualizarLista();
	}
	
	private void actualizarLista() {
		
	}
}
