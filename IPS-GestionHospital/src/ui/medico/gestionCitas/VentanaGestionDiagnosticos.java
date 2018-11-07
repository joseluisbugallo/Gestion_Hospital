package ui.medico.gestionCitas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.dto.CitaDto;
import business.dto.DiagnosticoDto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class VentanaGestionDiagnosticos extends JDialog {

	
	CitaDto cita;
	private DefaultListModel<DiagnosticoDto> modeloDiagosticosPosibles;
	private DefaultListModel<DiagnosticoDto> modeloDiagosticosFinales;
	
	
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblSelecionarLosDiagnsticos;
	private JTextField textField;
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



	/**
	 * Create the dialog.
	 */
	public VentanaGestionDiagnosticos(CitaDto cita) {
		setTitle("Seleccion de diagnosticos CIE 10");
		setDefaultCloseOperation(0);
		this.cita=cita;
		setBounds(100, 100, 803, 516);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblSelecionarLosDiagnsticos());
		contentPanel.add(getTextField());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getBtnBuscar());
		contentPanel.add(getListPosibles());
		contentPanel.add(getListDefinitivos());
		contentPanel.add(getAñadirDiagnostico());
		contentPanel.add(getEliminarDiagnostico());
		contentPanel.add(getLblPosiblesDiagnosticosSegn());
		contentPanel.add(getLblDiagnosticosQueSe());
		contentPanel.add(getLblAadir());
		contentPanel.add(getLblQuitarSeleccionado());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirmar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//Cambiar el objeto con los diagnosticos creados
						
					}
				});
				buttonPane.add(getLblAlDarleA());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Atras");
				buttonPane.add(cancelButton);
			}
		}
	}
	private JLabel getLblSelecionarLosDiagnsticos() {
		if (lblSelecionarLosDiagnsticos == null) {
			lblSelecionarLosDiagnsticos = new JLabel("Selecionar los diagn\u00F3sticos:");
			lblSelecionarLosDiagnsticos.setFont(new Font("Dialog", Font.PLAIN, 18));
			lblSelecionarLosDiagnsticos.setBounds(36, 13, 245, 37);
		}
		return lblSelecionarLosDiagnsticos;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(273, 22, 365, 22);
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Introduce el codigo CIE10 o busca por alguna palabra del diagnostico");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 12));
			lblNewLabel.setBounds(273, 46, 365, 27);
		}
		return lblNewLabel;
	}
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(664, 21, 97, 25);
		}
		return btnBuscar;
	}
	private JList getListPosibles() {
		if (listPosibles == null) {
			listPosibles = new JList();
			listPosibles.setBounds(36, 151, 280, 267);
		}
		return listPosibles;
	}
	private JList getListDefinitivos() {
		if (listDefinitivos == null) {
			listDefinitivos = new JList();
			listDefinitivos.setBounds(467, 151, 294, 267);
		}
		return listDefinitivos;
	}
	private JButton getAñadirDiagnostico() {
		if (añadirDiagnostico == null) {
			añadirDiagnostico = new JButton(">>>>>");
			añadirDiagnostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			añadirDiagnostico.setBounds(341, 218, 97, 25);
		}
		return añadirDiagnostico;
	}
	private JButton getEliminarDiagnostico() {
		if (eliminarDiagnostico == null) {
			eliminarDiagnostico = new JButton("<<<<<");
			eliminarDiagnostico.setBounds(341, 340, 97, 25);
		}
		return eliminarDiagnostico;
	}
	private JLabel getLblAlDarleA() {
		if (lblAlDarleA == null) {
			lblAlDarleA = new JLabel("Al darle a confirmar se a\u00F1adiran los diagnosticos a la cita, sino no se guardara ningun cambio realizado");
		}
		return lblAlDarleA;
	}
	private JLabel getLblPosiblesDiagnosticosSegn() {
		if (lblPosiblesDiagnosticosSegn == null) {
			lblPosiblesDiagnosticosSegn = new JLabel("Posibles diagnosticos seg\u00FAn el filtro:");
			lblPosiblesDiagnosticosSegn.setBounds(36, 122, 264, 16);
		}
		return lblPosiblesDiagnosticosSegn;
	}
	private JLabel getLblDiagnosticosQueSe() {
		if (lblDiagnosticosQueSe == null) {
			lblDiagnosticosQueSe = new JLabel("Diagnosticos que se a\u00F1adir\u00E1n:");
			lblDiagnosticosQueSe.setBounds(467, 122, 294, 16);
		}
		return lblDiagnosticosQueSe;
	}
	private JLabel getLblAadir() {
		if (lblAadir == null) {
			lblAadir = new JLabel("A\u00F1adir seleccionado");
			lblAadir.setHorizontalAlignment(SwingConstants.CENTER);
			lblAadir.setBounds(328, 199, 127, 16);
		}
		return lblAadir;
	}
	private JLabel getLblQuitarSeleccionado() {
		if (lblQuitarSeleccionado == null) {
			lblQuitarSeleccionado = new JLabel("Quitar seleccionado");
			lblQuitarSeleccionado.setHorizontalAlignment(SwingConstants.CENTER);
			lblQuitarSeleccionado.setBounds(328, 321, 127, 16);
		}
		return lblQuitarSeleccionado;
	}
}
