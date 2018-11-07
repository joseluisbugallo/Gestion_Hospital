package ui.medico.gestionCitas;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import business.dto.CitaDto;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import business.enums.TiposAntecedentes;
import ui.medico.VentanaGestionCita;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class VentanaGestionPrescripciones extends JDialog {

	private CitaDto cita;
	//VentanaGestionCita vGC;
	private JPanel pnlPrincipal;
	private JPanel panel;
	private JLabel lblProdcedimientoRealizado;
	private JTextField txtAntecedente;
	private JButton btnAadirProcedimiento;
	private JButton btnGuardar;
	private JButton btnVolver;
	private JPanel panel_1;
	private JTextArea txtAreaAntecedentes;
	private JLabel lblTipoAntecedente;
	private JComboBox<TiposAntecedentes> comboBox;
	Map<TiposAntecedentes, List<String>> antecedentes = new HashMap<>();
	private JButton btnReiniciar;

	/**
	 * Create the dialog.
	 */
	public VentanaGestionPrescripciones(CitaDto cita) { //VentanaGestionCita vGC) {
		this.cita = cita;
		//this.vGC = vGC;
		inicializarMapa();
		setTitle("Antecedentes del paciente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 507);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPnlPrincipal());
		//txtAreaAntecedentes.setText(cita.antecedentes);
	}

	private JPanel getPnlPrincipal() {
		if (pnlPrincipal == null) {
			pnlPrincipal = new JPanel();
			pnlPrincipal.setLayout(null);
			pnlPrincipal.add(getPanel());
			pnlPrincipal.add(getBtnGuardar());
			pnlPrincipal.add(getBtnVolver());
			pnlPrincipal.add(getPanel_1());
		}
		return pnlPrincipal;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "A\u00F1adir Antecedente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(12, 13, 677, 152);
			panel.setLayout(null);
			panel.add(getLblProdcedimientoRealizado());
			panel.add(getTxtAntecedente());
			panel.add(getBtnAniadirProcedimiento());
			panel.add(getLblTipoAntecedente());
			panel.add(getComboBox());
			panel.add(getBtnReiniciar());
		}
		return panel;
	}
	private JLabel getLblProdcedimientoRealizado() {
		if (lblProdcedimientoRealizado == null) {
			lblProdcedimientoRealizado = new JLabel("Antecedente:");
			lblProdcedimientoRealizado.setBounds(26, 26, 178, 28);
		}
		return lblProdcedimientoRealizado;
	}
	private JTextField getTxtAntecedente() {
		if (txtAntecedente == null) {
			txtAntecedente = new JTextField();
			txtAntecedente.setBounds(26, 54, 315, 28);
			txtAntecedente.setColumns(10);
		}
		return txtAntecedente;
	}
	private JButton getBtnAniadirProcedimiento() {
		if (btnAadirProcedimiento == null) {
			btnAadirProcedimiento = new JButton("A\u00F1adir Antecedente");
			btnAadirProcedimiento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getTxtAntecedente().getText().isEmpty()) {
						mostrarMensaje("¡El campo de antecedentes esta en blanco!", "Error:no se ha especificado antecedente", JOptionPane.ERROR_MESSAGE);
					}else {
						addAntecedente(getTxtAntecedente().getText(),(TiposAntecedentes) getComboBox().getSelectedItem());
					}
				}
			});
			btnAadirProcedimiento.setBounds(372, 114, 173, 25);
		}
		return btnAadirProcedimiento;
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
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Antecedentes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(12, 169, 677, 196);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getTxtAreaAntecedentes());
		}
		return panel_1;
	}
	private JTextArea getTxtAreaAntecedentes() {
		if (txtAreaAntecedentes == null) {
			txtAreaAntecedentes = new JTextArea();
			txtAreaAntecedentes.setLineWrap(true);
			txtAreaAntecedentes.setEditable(false);
//			if(cita.antecedentes==null) {
//				txtAreaAntecedentes.setText(" ");
//			}else {
//				txtAreaAntecedentes.setText(cita.antecedentes);
//			}
		}
		return txtAreaAntecedentes;
	}
	private JLabel getLblTipoAntecedente() {
		if (lblTipoAntecedente == null) {
			lblTipoAntecedente = new JLabel("Tipo Antecedente:");
			lblTipoAntecedente.setBounds(430, 32, 115, 16);
		}
		return lblTipoAntecedente;
	}
	private JComboBox<TiposAntecedentes> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<TiposAntecedentes>();
			comboBox.setModel(new DefaultComboBoxModel<TiposAntecedentes>(TiposAntecedentes.values()));
			comboBox.setBounds(440, 57, 178, 22);
		}
		return comboBox;
	}
	
	private void addAntecedente(String antecedente, TiposAntecedentes tipo) {
		this.antecedentes.get(tipo).add(antecedente);
		actualizarTxtArea();
	}
	
	private void actualizarTxtArea() {
		StringBuilder antecedentes = new StringBuilder();
		for(TiposAntecedentes t: this.antecedentes.keySet()) {
			antecedentes.append(t + "\n");
			for(String a: this.antecedentes.get(t)) {
				antecedentes.append("\t- " + a + ".\n");
			}
			antecedentes.append("\n");
		}
		getTxtAreaAntecedentes().setText(antecedentes.toString());
	}

	private void inicializarMapa() {
		this.antecedentes.put(TiposAntecedentes.FAMILIAR, new ArrayList<String>());
		this.antecedentes.put(TiposAntecedentes.PERSONAL, new ArrayList<String>());
		this.antecedentes.put(TiposAntecedentes.OTRO, new ArrayList<String>());
		
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
			this.cita.antecedentes=getTxtAreaAntecedentes().getText();
			VentanaGestionCita v = new VentanaGestionCita(this.cita);
			v.setVisible(true);
			this.dispose();
		}
	}
	
	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}
	private JButton getBtnReiniciar() {
		if (btnReiniciar == null) {
			btnReiniciar = new JButton("Borrar Antecedentes");
			btnReiniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializarMapa();
					txtAreaAntecedentes.setText("");
				}
			});
			btnReiniciar.setBounds(168, 114, 173, 25);
		}
		return btnReiniciar;
	}
}
