package ui.medico.gestionCitas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.management.ListenerNotFoundException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.CorreoElectronico;
import business.DiagnosticoController;
import business.PacientesController;
import business.dto.CitaDto;
import business.dto.DiagnosticoDto;
import business.dto.PacienteDto;
import ui.medico.VentanaGestionCita;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class VentanaGestionDiagnosticos extends JDialog {

	CitaDto cita;
	private DefaultListModel<DiagnosticoDto> modeloDiagosticosPosibles = new DefaultListModel<DiagnosticoDto>();
	private DefaultListModel<DiagnosticoDto> modeloDiagosticosFinales = new DefaultListModel<DiagnosticoDto>();

	private final JPanel contentPanel = new JPanel();
	private JLabel lblSelecionarLosDiagnsticos;
	private JTextField txtFiltroCie;
	private JLabel lblNewLabel;
	private JButton btnBuscar;
	private JList listPosibles;
	private JList listDefinitivos;
	private JButton añadirDiagnostico;
	private JButton eliminarDiagnostico;
	private JLabel lblAlDarleA;
	private JLabel lblPosiblesDiagnosticosSegn;
	private JLabel lblDiagnosticosQueSe;
	private JLabel lblAadir;
	private JLabel lblQuitarSeleccionado;
	private JCheckBox chckEdo;

	ArrayList<DiagnosticoDto> diagnosticos = new ArrayList<DiagnosticoDto>();
	private JScrollPane scrollPane;
	private JScrollPane scrollListaPosibles;

	/**
	 * Create the dialog.
	 */
	public VentanaGestionDiagnosticos(CitaDto cita) {
		this.cita = cita;
		DiagnosticoController dc = new DiagnosticoController();
		diagnosticos = dc.obtenerDiagnosticos();

		ArrayList<DiagnosticoDto> diagcita = dc.obtenerDiagnosticosDeCita(cita.id);
		for (DiagnosticoDto d : diagcita) {
			modeloDiagosticosFinales.addElement(d);
		}

		setResizable(false);
		setTitle("Seleccion de diagnosticos CIE 10");
		setDefaultCloseOperation(0);

		setBounds(100, 100, 1314, 592);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblSelecionarLosDiagnsticos());
		contentPanel.add(getTxtFiltroCie());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getBtnBuscar());
		contentPanel.add(getListDefinitivos());
		contentPanel.add(getAñadirDiagnostico());
		contentPanel.add(getEliminarDiagnostico());
		contentPanel.add(getLblPosiblesDiagnosticosSegn());
		contentPanel.add(getLblDiagnosticosQueSe());
		contentPanel.add(getLblAadir());
		contentPanel.add(getLblQuitarSeleccionado());
		contentPanel.add(getChckEdo());
		contentPanel.add(getListPosibles());
		contentPanel.add(getScrollListaPosibles());
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirmar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// Cambiar el objeto con los diagnosticos creados
						guardarYSalir();
						
					}

				});
				buttonPane.add(getLblAlDarleA());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Atras");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						salirSinGuardar();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

	private JLabel getLblSelecionarLosDiagnsticos() {
		if (lblSelecionarLosDiagnsticos == null) {
			lblSelecionarLosDiagnsticos = new JLabel("Selecionar los diagn\u00F3sticos:");
			lblSelecionarLosDiagnsticos.setFont(new Font("Dialog", Font.PLAIN, 18));
			lblSelecionarLosDiagnsticos.setBounds(222, 13, 245, 37);
		}
		return lblSelecionarLosDiagnsticos;
	}

	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}

	private JTextField getTxtFiltroCie() {
		if (txtFiltroCie == null) {
			txtFiltroCie = new JTextField();
			txtFiltroCie.setBounds(479, 22, 365, 22);
			txtFiltroCie.setColumns(10);
		}
		return txtFiltroCie;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Introduce el codigo CIE10 o busca por alguna palabra del diagnostico");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 12));
			lblNewLabel.setBounds(479, 45, 365, 27);
		}
		return lblNewLabel;
	}

	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String criterio = getTxtFiltroCie().getText().toLowerCase();
					modeloDiagosticosPosibles.clear();
					if (criterio != null && criterio != "") {
						for (DiagnosticoDto d : diagnosticos) {
							if (d.diagnostico.toLowerCase().contains(criterio)
									|| d.id.toLowerCase().contains(criterio)) {
								modeloDiagosticosPosibles.addElement(d);
								listPosibles.setModel(modeloDiagosticosPosibles);
								
							}
						}
					}

				}
			});
			btnBuscar.setBounds(856, 21, 97, 25);
		}
		return btnBuscar;
	}

	private JList getListPosibles() {
		if (listPosibles == null) {
			listPosibles = new JList(modeloDiagosticosPosibles);
			listPosibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		}
		return listPosibles;
	}

	private JList getListDefinitivos() {
		if (listDefinitivos == null) {
			listDefinitivos = new JList(modeloDiagosticosFinales);
			listDefinitivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listDefinitivos.setBounds(727, 115, 569, 394);
		}
		return listDefinitivos;
	}

	private JButton getAñadirDiagnostico() {
		if (añadirDiagnostico == null) {
			añadirDiagnostico = new JButton(">>>>>");
			añadirDiagnostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modeloDiagosticosFinales.addElement((DiagnosticoDto) listPosibles.getSelectedValue());
					modeloDiagosticosPosibles.removeElement(listPosibles.getSelectedValue());
					listPosibles.setModel(modeloDiagosticosPosibles);
					listDefinitivos.setModel(modeloDiagosticosFinales);
				}
			});
			añadirDiagnostico.setBounds(598, 211, 97, 25);
		}
		return añadirDiagnostico;
	}

	private JButton getEliminarDiagnostico() {
		if (eliminarDiagnostico == null) {
			eliminarDiagnostico = new JButton("<<<<<");
			eliminarDiagnostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					modeloDiagosticosPosibles.addElement((DiagnosticoDto) listDefinitivos.getSelectedValue());
					modeloDiagosticosFinales.removeElement(listDefinitivos.getSelectedValue());
					listPosibles.setModel(modeloDiagosticosPosibles);
					listDefinitivos.setModel(modeloDiagosticosFinales);
				}
			});
			eliminarDiagnostico.setBounds(598, 303, 97, 25);
		}
		return eliminarDiagnostico;
	}

	private JLabel getLblAlDarleA() {
		if (lblAlDarleA == null) {
			lblAlDarleA = new JLabel(
					"Al darle a confirmar se a\u00F1adiran los diagnosticos a la cita, sino no se guardara ningun cambio realizado");
		}
		return lblAlDarleA;
	}

	private JLabel getLblPosiblesDiagnosticosSegn() {
		if (lblPosiblesDiagnosticosSegn == null) {
			lblPosiblesDiagnosticosSegn = new JLabel("Posibles diagnosticos seg\u00FAn el filtro:");
			lblPosiblesDiagnosticosSegn.setBounds(114, 86, 264, 16);
		}
		return lblPosiblesDiagnosticosSegn;
	}

	private JLabel getLblDiagnosticosQueSe() {
		if (lblDiagnosticosQueSe == null) {
			lblDiagnosticosQueSe = new JLabel("Diagnosticos que se a\u00F1adir\u00E1n:");
			lblDiagnosticosQueSe.setBounds(888, 86, 294, 16);
		}
		return lblDiagnosticosQueSe;
	}

	private JLabel getLblAadir() {
		if (lblAadir == null) {
			lblAadir = new JLabel("A\u00F1adir seleccionado");
			lblAadir.setHorizontalAlignment(SwingConstants.CENTER);
			lblAadir.setBounds(588, 182, 127, 16);
		}
		return lblAadir;
	}

	private JLabel getLblQuitarSeleccionado() {
		if (lblQuitarSeleccionado == null) {
			lblQuitarSeleccionado = new JLabel("Quitar seleccionado");
			lblQuitarSeleccionado.setHorizontalAlignment(SwingConstants.CENTER);
			lblQuitarSeleccionado.setBounds(588, 271, 127, 16);
		}
		return lblQuitarSeleccionado;
	}

	private JCheckBox getChckEdo() {
		if (chckEdo == null) {
			chckEdo = new JCheckBox("<html><body>Seleccionar<br> si hay alguna<br>enfermedad EDO</body></html>");
			chckEdo.setBounds(598, 358, 113, 73);
		}
		return chckEdo;
	}
	
	private void salirSinGuardar() {
		int respuesta =JOptionPane.showConfirmDialog(null,"Seguro que quiere salir sin guardar los datos?",
				"Salir sin guardar",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		
		if(respuesta==JOptionPane.YES_OPTION) {
			VentanaGestionCita v = new VentanaGestionCita(cita);
			v.setVisible(true);
			this.dispose();
		}
	}
	
	private void guardarYSalir() {
		int respuesta =JOptionPane.showConfirmDialog(null,"Seguro que quiere guardar estos datos?",
				"Guardar datos",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
		
		if(respuesta==JOptionPane.YES_OPTION) {
			PacientesController pc = new PacientesController();
			DiagnosticoController dc = new DiagnosticoController();
			PacienteDto paciente = pc.findPacientesById(cita.idPaciente);
			cita.diagnostico.clear();
			dc.borrarDiagnosticosCita(cita);
			for (int i = 0; i < modeloDiagosticosFinales.size(); i++) {
				DiagnosticoDto d = modeloDiagosticosFinales.getElementAt(i);
				cita.diagnostico.add(d);
				dc.addDiagnosticoACita(cita, d);

			}

			if (getChckEdo().isSelected()) {

				String asunto = "EDOS detectada";
				String mensaje = "Se ha detectado una enfermedad de Deteccion Obligatoria \n El paciente: "
						+ paciente.nombre + " ha sido diagnosticado con: \n ";
				for (DiagnosticoDto d : cita.diagnostico) {
					mensaje += d.id + ": " + d.diagnostico;
				}
				mensaje += "entre las cuales se encuentra una enfermedad EDO";

				CorreoElectronico correo = new CorreoElectronico("gestionhospitalips@gmail.com", asunto,
						mensaje);
				if (correo.enviarCorreo()) {

					mostrarMensaje("Se ha enviado un correo a gerencia", "Informacion",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			VentanaGestionCita v = new VentanaGestionCita(this.cita);
			v.setVisible(true);
			this.dispose();
		}
	}

	private JScrollPane getScrollListaPosibles() {
		if (scrollListaPosibles == null) {
			scrollListaPosibles = new JScrollPane(listPosibles);
			scrollListaPosibles.setBounds(12, 115, 570, 394);
		}
		return scrollListaPosibles;
	}
}
