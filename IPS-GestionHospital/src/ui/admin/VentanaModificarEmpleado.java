package ui.admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;

public class VentanaModificarEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JScrollPane scrollPane;
	private JButton button_1;
	private JPanel pnlMedicos;

	/**
	 * Create the dialog.
	 */
	public VentanaModificarEmpleado() {
		setBounds(100, 100, 750, 454);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Actividad de los empleados");
			label.setFont(new Font("Tahoma", Font.PLAIN, 17));
			label.setBounds(0, 0, 722, 21);
			contentPanel.add(label);
		}
		{
			JPanel pnlEmplado = new JPanel();
			pnlEmplado.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Empleado",
								TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlEmplado.setBounds(36, 35, 262, 140);
			contentPanel.add(pnlEmplado);
			{
				JButton btnSeleccionarEmpleado = new JButton("Seleccionar empleado");
				pnlEmplado.add(btnSeleccionarEmpleado);
			}
		}
		{
			JPanel pnlInfoEmpleado = new JPanel();
			pnlInfoEmpleado.setLayout(null);
			pnlInfoEmpleado.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
								"Informaci\u00F3n del empleado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlInfoEmpleado.setBounds(325, 35, 383, 140);
			contentPanel.add(pnlInfoEmpleado);
			{
				JLabel label = new JLabel("Nombre:");
				label.setBounds(10, 30, 56, 14);
				pnlInfoEmpleado.add(label);
			}
			{
				textField = new JTextField();
				textField.setEditable(false);
				textField.setColumns(10);
				textField.setBounds(69, 27, 122, 20);
				pnlInfoEmpleado.add(textField);
			}
			{
				JLabel label = new JLabel("Cargo:");
				label.setBounds(10, 64, 56, 14);
				pnlInfoEmpleado.add(label);
			}
			{
				textField_1 = new JTextField();
				textField_1.setEditable(false);
				textField_1.setColumns(10);
				textField_1.setBounds(69, 61, 122, 20);
				pnlInfoEmpleado.add(textField_1);
			}
			{
				JLabel label = new JLabel("DNI:");
				label.setBounds(201, 30, 37, 14);
				pnlInfoEmpleado.add(label);
			}
			{
				textField_2 = new JTextField();
				textField_2.setEditable(false);
				textField_2.setColumns(10);
				textField_2.setBounds(248, 27, 122, 20);
				pnlInfoEmpleado.add(textField_2);
			}
			{
				JLabel label = new JLabel("Estado:");
				label.setBounds(201, 64, 47, 14);
				pnlInfoEmpleado.add(label);
			}
			{
				textField_3 = new JTextField();
				textField_3.setEditable(false);
				textField_3.setColumns(10);
				textField_3.setBounds(248, 61, 122, 20);
				pnlInfoEmpleado.add(textField_3);
			}
			{
				JLabel label = new JLabel("Correo:");
				label.setBounds(10, 102, 56, 14);
				pnlInfoEmpleado.add(label);
			}
			{
				textField_4 = new JTextField();
				textField_4.setEditable(false);
				textField_4.setColumns(10);
				textField_4.setBounds(69, 99, 202, 20);
				pnlInfoEmpleado.add(textField_4);
			}
		}
		contentPanel.add(getButton_1());
		contentPanel.add(getPnlMedicos());
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
		}
		return scrollPane;
	}
	private JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("Salir");
			button_1.setBounds(633, 371, 89, 23);
		}
		return button_1;
	}
	private JPanel getPnlMedicos() {
		if (pnlMedicos == null) {
			pnlMedicos = new JPanel();
			pnlMedicos.setBounds(36, 188, 672, 159);
			pnlMedicos.setLayout(new BorderLayout(0, 0));
			pnlMedicos.add(getScrollPane(), BorderLayout.CENTER);
			{
				JList list = new JList();
				pnlMedicos.add(list, BorderLayout.NORTH);
			}
		}
		return pnlMedicos;
	}
}
