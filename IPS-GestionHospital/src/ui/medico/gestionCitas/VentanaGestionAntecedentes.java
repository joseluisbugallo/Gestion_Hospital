package ui.medico.gestionCitas;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import business.CitasController;
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
import javax.swing.JList;

public class VentanaGestionAntecedentes extends JDialog {

	private CitaDto cita;
	//VentanaGestionCita vGC;
	private JPanel pnlPrincipal;
	private JPanel panel;
	private JLabel lblProdcedimientoRealizado;
	private JTextField txtAntecedente;
	private JButton btnAadirAntecedente;
	private JButton btnGuardar;
	private JButton btnVolver;
	private JPanel panel_1;
	private List<String> antecedentes = new ArrayList<>();
	CitasController cC= new CitasController();
	private JList<String> listAntecedentes;
	private JButton btnBorrarAntecedene;

	/**
	 * Create the dialog.
	 */
	public VentanaGestionAntecedentes(CitaDto cita) { //VentanaGestionCita vGC) {
		this.cita = cita;
		setTitle("Antecedentes del paciente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 507);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPnlPrincipal());
		precargarLista();
	}

	private void precargarLista() {
		if(!this.cita.antecedentes.isEmpty()) {
			String[] lista = this.cita.antecedentes.split("\n");
			this.cita.listadoAntecedentes = new ArrayList<>();
			for(int i=0; i<lista.length; i++) {
				this.cita.listadoAntecedentes.add(lista[i]);
			}
		}
		this.antecedentes= new ArrayList<>();
		this.antecedentes.addAll(this.cita.listadoAntecedentes);
		actualizarLista();
	}

	private void actualizarLista() {
		String[] list = new String[this.antecedentes.size()];
		for(int i=0; i<list.length; i++) {
			list[i]= this.antecedentes.get(i);
		}
		if(list.length!=0) {
			this.listAntecedentes.setListData(list);
		}else {
			String[] datos = {"No hay datos aun."};
			this.listAntecedentes.setListData(datos);
		}
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
			panel.setBounds(12, 13, 677, 126);
			panel.setLayout(null);
			panel.add(getLblProdcedimientoRealizado());
			panel.add(getTxtAntecedente());
			panel.add(getBtnAniadirProcedimiento());
			panel.add(getBtnBorrarAntecedene());
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
			txtAntecedente.setBounds(26, 54, 384, 28);
			txtAntecedente.setColumns(10);
		}
		return txtAntecedente;
	}
	private JButton getBtnAniadirProcedimiento() {
		if (btnAadirAntecedente == null) {
			btnAadirAntecedente = new JButton("A\u00F1adir Antecedente");
			btnAadirAntecedente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getTxtAntecedente().getText().isEmpty()) {
						mostrarMensaje("¡El campo de antecedentes esta en blanco!", "Error:no se ha especificado antecedente", JOptionPane.ERROR_MESSAGE);
					}else {
						addAntecedente();
						}
				}
			});
			btnAadirAntecedente.setBounds(456, 28, 173, 25);
		}
		return btnAadirAntecedente;
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
			panel_1.setBounds(12, 152, 677, 213);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getListAntecedentes(), BorderLayout.CENTER);
		}
		return panel_1;
	}
	
	
	private void salirSinGuardar() {
		int respuesta =JOptionPane.showConfirmDialog(null,"Seguro que quiere salir sin guardar los datos?",
				"Salir sin guardar",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
		
		if(respuesta==JOptionPane.YES_OPTION) {
			VentanaGestionCita v = new VentanaGestionCita(cita);
			v.setLocationRelativeTo(this);
			v.setVisible(true);
			this.listAntecedentes=null;
			this.antecedentes= new ArrayList<>();
			this.dispose();
		}
	}
	
	private void guardarYSalir() {
		int respuesta =JOptionPane.showConfirmDialog(null,"Seguro que quiere guardar estos datos?",
				"Guardar datos",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
		
		if(respuesta==JOptionPane.YES_OPTION) {
			cC.actualizarCita(cita);
			VentanaGestionCita v = new VentanaGestionCita(this.cita);
			v.setLocationRelativeTo(this);
			v.setVisible(true);
			this.listAntecedentes=null;
			this.antecedentes=new ArrayList<>();
			this.dispose();
		}
	}
	
	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}
	
	private JList<String> getListAntecedentes() {
		if (listAntecedentes == null) {
			listAntecedentes = new JList<>();
		}
		return listAntecedentes;
	}
	private JButton getBtnBorrarAntecedene() {
		if (btnBorrarAntecedene == null) {
			btnBorrarAntecedene = new JButton("Borrar Antecedene");
			btnBorrarAntecedene.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getListAntecedentes().isSelectionEmpty()) {
						mostrarMensaje("No hay ningun antecedente seleccionado", "Error al eliminar",
								JOptionPane.ERROR_MESSAGE);
					} else {
						int seleccion = getListAntecedentes().getSelectedIndex();
						antecedentes.remove(seleccion);
						actualizarLista();
					}
				}
			});
			btnBorrarAntecedene.setBounds(456, 66, 173, 28);
		}
		return btnBorrarAntecedene;
	}
	
	private void addAntecedente() {
		String antecedente = getTxtAntecedente().getText();
		this.antecedentes.add(antecedente);
		getTxtAntecedente().setText("");
		actualizarLista();
	}
}
