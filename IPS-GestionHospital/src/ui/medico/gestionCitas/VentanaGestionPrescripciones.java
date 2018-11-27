package ui.medico.gestionCitas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import business.CitasController;
import business.LogController;
import business.dto.CambioDto;
import business.dto.CitaDto;
import ui.medico.VentanaGestionCita;

public class VentanaGestionPrescripciones extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CitaDto cita;
	private JPanel pnlPrincipal;
	private JPanel panel;
	private JLabel lblPrescripcion;
	private JTextField txtPrescripcion;
	private JButton btnGuardar;
	private JButton btnVolver;
	private JPanel pnlPrescripciones;
	private JLabel lblObsrvacion;
	private JTextField txtObservaciones;
	private JButton btnAadirPrescripcin;
	private JList<String> listPrescripcion;
	private List<String> prescripciones;
	private JButton btnEliminarPrescripcion;
	private CitasController cC = new CitasController();

	/**
	 * Create the dialog.
	 */
	public VentanaGestionPrescripciones(CitaDto cita) { // VentanaGestionCita vGC) {
		this.cita = cita;
		System.out.println(cita.prescripcion);
		setTitle("Prescripcion M\u00E9dica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 507);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPnlPrincipal());
		precargarLista();
	}

	private void precargarLista() {
		if (!this.cita.prescripcion.isEmpty()) {
			String[] lista = this.cita.prescripcion.split("\n");
			this.cita.listadoPrescripciones = new ArrayList<>();
			for (int i = 0; i < lista.length; i++) {
				this.cita.listadoPrescripciones.add(lista[i]);
			}
		}
		this.prescripciones = new ArrayList<>();
		this.prescripciones.addAll(this.cita.listadoPrescripciones);
		actualizarLista();
	}

	private void actualizarLista() {
		String[] list = new String[this.prescripciones.size()];
		for (int i = 0; i < list.length; i++) {
			list[i] = this.prescripciones.get(i);
		}
		if (list.length != 0) {
			this.listPrescripcion.setListData(list);
		} else {
			String[] datos = { "No hay datos aun." };
			this.listPrescripcion.setListData(datos);
			;
		}
	}

	private JPanel getPnlPrincipal() {
		if (pnlPrincipal == null) {
			pnlPrincipal = new JPanel();
			pnlPrincipal.setLayout(null);
			pnlPrincipal.add(getPanel());
			pnlPrincipal.add(getBtnGuardar());
			pnlPrincipal.add(getBtnVolver());
			pnlPrincipal.add(getPnlPrescripciones());
		}
		return pnlPrincipal;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "A\u00F1adir prescripcion",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(12, 13, 677, 152);
			panel.setLayout(null);
			panel.add(getLblPrescripcion());
			panel.add(getTxtPrescripcion());
			panel.add(getLblObsrvacion());
			panel.add(getTxtObservaciones());
			panel.add(getBtnAadirPrescripcin());
			panel.add(getBtnEliminarPrescripcion());
		}
		return panel;
	}

	private JLabel getLblPrescripcion() {
		if (lblPrescripcion == null) {
			lblPrescripcion = new JLabel("Prescripcion:");
			lblPrescripcion.setBounds(26, 26, 178, 28);
		}
		return lblPrescripcion;
	}

	private JTextField getTxtPrescripcion() {
		if (txtPrescripcion == null) {
			txtPrescripcion = new JTextField();
			txtPrescripcion.setBounds(26, 54, 315, 28);
			txtPrescripcion.setColumns(10);
		}
		return txtPrescripcion;
	}

	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton("Guardar");
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					guardarYSalir();
				}
			});
			btnGuardar.setBounds(592, 379, 97, 25);
		}
		return btnGuardar;
	}

	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					salirSinGuardar();
				}
			});
			btnVolver.setBounds(483, 379, 97, 25);
		}
		return btnVolver;
	}

	private JPanel getPnlPrescripciones() {
		if (pnlPrescripciones == null) {
			pnlPrescripciones = new JPanel();
			pnlPrescripciones.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Prescripciones",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlPrescripciones.setBounds(12, 169, 677, 196);
			pnlPrescripciones.setLayout(new BorderLayout(0, 0));
			pnlPrescripciones.add(getListPrescripcion(), BorderLayout.CENTER);
		}
		return pnlPrescripciones;
	}

	private JLabel getLblObsrvacion() {
		if (lblObsrvacion == null) {
			lblObsrvacion = new JLabel("Observaciones:");
			lblObsrvacion.setBounds(26, 95, 115, 16);
		}
		return lblObsrvacion;
	}

	private void salirSinGuardar() {
		int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere salir sin guardar los datos?",
				"Salir sin guardar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (respuesta == JOptionPane.YES_OPTION) {
			VentanaGestionCita v = new VentanaGestionCita(cita);
			v.setLocationRelativeTo(this);
			v.setVisible(true);
			this.listPrescripcion = null;
			this.prescripciones = new ArrayList<>();
			this.dispose();
		}
	}

	private void guardarYSalir() {
		int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere guardar estos datos?", "Guardar datos",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		if (respuesta == JOptionPane.YES_OPTION) {
			this.cita.listadoPrescripciones = this.prescripciones;


			cita.prescripcion="";
			for(String s: this.prescripciones) {
				cita.prescripcion+=s + "\n";
				CambioDto cambio = new CambioDto();
				cambio.cambio = "El medico con id: "+ cita.idEmpleado +
						" ha añadido prescripciones al paciente: "+ cita.idPaciente +
						" en la cita "+cita.id + "la siguiente prescripcion "+ s;							
				cambio.fecha = new Date();					
				LogController lc = new LogController();
				lc.añadirCambio(cambio);

			}
			cC.actualizarCita(cita);
			VentanaGestionCita v = new VentanaGestionCita(this.cita);
			v.setLocationRelativeTo(this);
			v.setVisible(true);
			this.listPrescripcion = null;
			this.prescripciones = new ArrayList<>();
			this.dispose();
		}
			
	}

	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}

	private JTextField getTxtObservaciones() {
		if (txtObservaciones == null) {
			txtObservaciones = new JTextField();
			txtObservaciones.setColumns(10);
			txtObservaciones.setBounds(26, 111, 315, 28);
		}
		return txtObservaciones;
	}

	private JButton getBtnAadirPrescripcin() {
		if (btnAadirPrescripcin == null) {
			btnAadirPrescripcin = new JButton("A\u00F1adir Prescripci\u00F3n");
			btnAadirPrescripcin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getTxtPrescripcion().getText().isEmpty()) {
						mostrarMensaje("No ha apuntado ninguna prescripcion", "Error al añadir prescripcion",
								JOptionPane.ERROR_MESSAGE);
					} else {
						addPrescripcion();
					}
				}

			});
			btnAadirPrescripcin.setBounds(428, 55, 205, 26);
		}
		return btnAadirPrescripcin;
	}

	private JList<String> getListPrescripcion() {
		if (listPrescripcion == null) {
			listPrescripcion = new JList<>();
		}
		return listPrescripcion;
	}

	private void addPrescripcion() {
		String prescripcion = getTxtPrescripcion().getText();
		prescripcion = prescripcion + (getTxtObservaciones().getText().isEmpty() ? ". "
				: ". Observaciones: " + getTxtObservaciones().getText() + ".");

		this.prescripciones.add(prescripcion);
		getTxtPrescripcion().setText("");
		getTxtObservaciones().setText("");
		actualizarLista();
	}

	private JButton getBtnEliminarPrescripcion() {
		if (btnEliminarPrescripcion == null) {
			btnEliminarPrescripcion = new JButton("Eliminar Prescripci\u00F3n");
			btnEliminarPrescripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getListPrescripcion().isSelectionEmpty()) {
						mostrarMensaje("No hay ninguna prescripción seleccionada", "Error al eliminar",
								JOptionPane.ERROR_MESSAGE);
					} else {
						int seleccion = getListPrescripcion().getSelectedIndex();
						prescripciones.remove(seleccion);
						actualizarLista();
					}
				}
			});
			btnEliminarPrescripcion.setBounds(428, 113, 205, 26);
		}
		return btnEliminarPrescripcion;
	}
}
