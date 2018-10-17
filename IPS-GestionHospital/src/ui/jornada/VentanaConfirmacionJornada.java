package ui.jornada;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import business.JornadaController;
import business.dto.EmpleadoDto;

public class VentanaConfirmacionJornada extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblIdEmpleado;
	private JLabel lblFechaYHoraInicio;
	private JLabel lblFechaYHoraFin;
	private JTextField textFieldId;
	private JTextField textFieldInicio;
	private JTextField textFieldFin;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	
	private JornadaController jc = new JornadaController();
	
	private VentanaJornadaLaboral v;
	private EmpleadoDto empleado;
	private JLabel lblDas;
	private JTextField textFieldDias;
	
	public VentanaConfirmacionJornada(EmpleadoDto emp, VentanaJornadaLaboral ventana) {
		setBounds(new Rectangle(0, 0, 420, 270));
		this.empleado=emp;
		this.v=ventana;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Confirmacion Jornada Laboral");
		setModal(true);
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().add(getLblIdEmpleado());
		getContentPane().add(getLblFechaYHoraInicio());
		getContentPane().add(getLblFechaYHoraFin());
		getContentPane().add(getTextFieldId());
		getContentPane().add(getTextFieldInicio());
		getContentPane().add(getTextFieldFin());
		getContentPane().add(getBtnConfirmar());
		getContentPane().add(getBtnCancelar());
		getContentPane().add(getLblDas());
		getContentPane().add(getTextFieldDias());
		cargarInfo();
	}
	private void cargarInfo() {
		getTextFieldId().setText(String.valueOf(empleado.id));
		getTextFieldInicio().setText(v.getInicio());
		getTextFieldFin().setText(v.getFin());
		getTextFieldDias().setText(v.getDias());
	}
	private JLabel getLblIdEmpleado() {
		if (lblIdEmpleado == null) {
			lblIdEmpleado = new JLabel("ID empleado:");
			lblIdEmpleado.setBounds(12, 32, 86, 16);
		}
		return lblIdEmpleado;
	}
	private JLabel getLblFechaYHoraInicio() {
		if (lblFechaYHoraInicio == null) {
			lblFechaYHoraInicio = new JLabel("Fecha y hora inicio:");
			lblFechaYHoraInicio.setBounds(12, 67, 124, 16);
		}
		return lblFechaYHoraInicio;
	}
	private JLabel getLblFechaYHoraFin() {
		if (lblFechaYHoraFin == null) {
			lblFechaYHoraFin = new JLabel("Fecha y hora fin:");
			lblFechaYHoraFin.setBounds(12, 102, 107, 16);
		}
		return lblFechaYHoraFin;
	}
	private JTextField getTextFieldId() {
		if (textFieldId == null) {
			textFieldId = new JTextField();
			textFieldId.setEditable(false);
			textFieldId.setBounds(148, 29, 197, 22);
			textFieldId.setColumns(10);
		}
		return textFieldId;
	}
	private JTextField getTextFieldInicio() {
		if (textFieldInicio == null) {
			textFieldInicio = new JTextField();
			textFieldInicio.setEditable(false);
			textFieldInicio.setBounds(148, 64, 197, 22);
			textFieldInicio.setColumns(10);
		}
		return textFieldInicio;
	}
	private JTextField getTextFieldFin() {
		if (textFieldFin == null) {
			textFieldFin = new JTextField();
			textFieldFin.setEditable(false);
			textFieldFin.setBounds(148, 99, 197, 22);
			textFieldFin.setColumns(10);
		}
		return textFieldFin;
	}
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					jc.asignarJornada(v.getInicioDate(),v.getFinDate(), getTextFieldDias().getText().toString(),  empleado.id);
					JOptionPane.showMessageDialog(v, "Jornada asignada correctamente", "Confirmado", JOptionPane.PLAIN_MESSAGE);
					v.inicializar();
					dispose();
				}
			});
			btnConfirmar.setBounds(204, 197, 97, 25);
		}
		return btnConfirmar;
	}
	
	
	
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					v.inicializar();
					dispose();
				}
			});
			btnCancelar.setBounds(95, 197, 97, 25);
		}
		return btnCancelar;
	}
	private JLabel getLblDas() {
		if (lblDas == null) {
			lblDas = new JLabel("D\u00EDas:");
			lblDas.setBounds(12, 142, 56, 16);
		}
		return lblDas;
	}
	private JTextField getTextFieldDias() {
		if (textFieldDias == null) {
			textFieldDias = new JTextField();
			textFieldDias.setEditable(false);
			textFieldDias.setBounds(53, 139, 350, 22);
			textFieldDias.setColumns(10);
		}
		return textFieldDias;
	}
}
