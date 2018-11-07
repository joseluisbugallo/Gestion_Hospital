package ui.medico.gestionCitas;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import business.CitasController;
import business.dto.CitaDto;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VentanaGestionProcedimientos extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel panelAddProc;
	private JPanel panelProc;
	private JTextField textFieldProc;
	private JButton btnAadir;
	private JButton btnAceptar;
	private JButton btnBorrar;
	private JButton btnCancelar;
	private JLabel lblProcedimiento;
	private JDateChooser calendario;
	private JLabel lblFecha;
	
	private CitaDto cita;
	private CitasController cc;
	private JTextArea textAreaProc;
	
	public VentanaGestionProcedimientos(CitaDto cita) {
		this.cc = new CitasController();
		this.cita=cita;
		setBounds(100, 100, 707, 507);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanelPrincipal(), BorderLayout.CENTER);
		
	}
	
	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
			panelPrincipal.setLayout(null);
			panelPrincipal.add(getPanelAddProc());
			panelPrincipal.add(getPanelProc());
			panelPrincipal.add(getBtnAceptar());
			panelPrincipal.add(getBtnBorrar());
			panelPrincipal.add(getBtnCancelar());
		}
		return panelPrincipal;
	}
	private JPanel getPanelAddProc() {
		if (panelAddProc == null) {
			panelAddProc = new JPanel();
			panelAddProc.setBorder(new TitledBorder(null, "A\u00F1adir Procedimientos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelAddProc.setBounds(12, 13, 665, 160);
			panelAddProc.setLayout(null);
			panelAddProc.add(getTextFieldProc());
			panelAddProc.add(getBtnAadir());
			panelAddProc.add(getLblProcedimiento());
			panelAddProc.add(getLblFecha());
			panelAddProc.add(getCalendario());
		}
		return panelAddProc;
	}
	private JPanel getPanelProc() {
		if (panelProc == null) {
			panelProc = new JPanel();
			panelProc.setBorder(new TitledBorder(null, "Procedimientos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelProc.setBounds(12, 180, 665, 208);
			panelProc.setLayout(new BorderLayout(0, 0));
			panelProc.add(getTextAreaProc(), BorderLayout.CENTER);
		}
		return panelProc;
	}
	private JTextField getTextFieldProc() {
		if (textFieldProc == null) {
			textFieldProc = new JTextField();
			textFieldProc.setBounds(12, 57, 364, 22);
			textFieldProc.setColumns(10);
		}
		return textFieldProc;
	}
	private JButton getBtnAadir() {
		if (btnAadir == null) {
			btnAadir = new JButton("A\u00F1adir");
			btnAadir.setBounds(536, 122, 97, 25);
		}
		return btnAadir;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.setBounds(580, 401, 97, 25);
		}
		return btnAceptar;
	}
	private JButton getBtnBorrar() {
		if (btnBorrar == null) {
			btnBorrar = new JButton("Borrar Proc");
			btnBorrar.setBounds(453, 401, 115, 25);
		}
		return btnBorrar;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(344, 401, 97, 25);
		}
		return btnCancelar;
	}
	private JLabel getLblProcedimiento() {
		if (lblProcedimiento == null) {
			lblProcedimiento = new JLabel("Procedimiento:");
			lblProcedimiento.setBounds(12, 28, 112, 16);
		}
		return lblProcedimiento;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(12, 95, 56, 16);
		}
		return lblFecha;
	}
	
	private JDateChooser getCalendario() {
		if (calendario == null) {
			calendario = new JDateChooser();
			calendario.setBounds(12, 132, 145, -21);
			calendario.setDate(new java.util.Date());
		}
		return calendario;
	}
	private JTextArea getTextAreaProc() {
		if (textAreaProc == null) {
			textAreaProc = new JTextArea();
			textAreaProc.setLineWrap(true);
			textAreaProc.setWrapStyleWord(true);
			textAreaProc.setEditable(false);
			
			String p = cc.obtenerProcCita(cita);
			if(p==null) {
				textAreaProc.setText(" ");
			}else {
				textAreaProc.setText(p);
			}
		}
		return textAreaProc;
	}
}
