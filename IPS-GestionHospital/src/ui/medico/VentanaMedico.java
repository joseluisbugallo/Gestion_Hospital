package ui.medico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import business.CitasController;
import business.dto.CitaDto;
import business.dto.EmpleadoDto;
import business.dto.PacienteDto;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JTextField;
public class VentanaMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private CardLayout c;
	private JPanel contentPane;
	private JButton btnCitas;
	private EmpleadoDto medico;
	private JLabel lblUstedHaEntrado;
	private JPanel pnCitas;
	private JLabel lblUstedHaEntrado_1;
	private JLabel lblMedico;
	private JPanel pnOpciones;
	private JButton btnSalir;
	
	private DefaultListModel<CitaDto> listModelCitas;
	private CitasController cc = new CitasController();
	
	private CitaDto citaSeleccionada;


	/**
	 * Constructor de la ventana.
	 */
	public VentanaMedico(EmpleadoDto medico) {
		c = new CardLayout();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100,100,800,600);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(c);
		//contentPane.add(getPnInicial(),"pnInicial");
		contentPane.add(getPnCitas(),"pnCitas");
		this.medico= medico;
		inicializarListas();
		lblMedico.setText(medico.nombre);
		
	
		
	}
	
	private void inicializarListas() {
		listModelCitas = new DefaultListModel<CitaDto>();
		for (CitaDto cita : cc.getListadoCitas(medico)) {
			listModelCitas.addElement(cita);
		}
	}
	
	public EmpleadoDto getMedico() {
		return medico;
	}

	private JButton getBtnCitas() {
		if (btnCitas == null) {
			btnCitas = new JButton("Comprobar mis citas");
			btnCitas.setBounds(25, 44, 254, 47);
			btnCitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaCitasMedico citas = new VentanaCitasMedico(getMedico());
					citas.setLocationRelativeTo(null);
					citas.setTitle("Citas del medico: " + getMedico().nombre);
					citas.setVisible(true);
				}
			});
		}
		return btnCitas;
	}
	
	private void mostrarMensaje(String mess, String title, int icon) {
		JOptionPane.showMessageDialog(this, mess, title, icon);
	}
	private JLabel getLblUstedHaEntrado() {
		if (lblUstedHaEntrado == null) {
			lblUstedHaEntrado = new JLabel("Usted ha entrado como: ");
			lblUstedHaEntrado.setBounds(51, 23, 199, 26);
			lblUstedHaEntrado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblUstedHaEntrado;
	}
	
	private JPanel getPnCitas() {
		if (pnCitas == null) {
			pnCitas = new JPanel();
			pnCitas.setBorder(null);
			pnCitas.setBounds(51, 96, 674, 360);
			pnCitas.setLayout(null);
			pnCitas.add(getLblUstedHaEntrado_1());
			pnCitas.add(getLblMedico());
			pnCitas.add(getPnOpciones());
			pnCitas.add(getBtnSalir_1());
		}
		return pnCitas;
	}
	private JLabel getLblUstedHaEntrado_1() {
		if (lblUstedHaEntrado_1 == null) {
			lblUstedHaEntrado_1 = new JLabel("Usted ha entrado como: ");
			lblUstedHaEntrado_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblUstedHaEntrado_1.setBounds(33, 33, 196, 31);
		}
		return lblUstedHaEntrado_1;
	}
	private JLabel getLblMedico() {
		if (lblMedico == null) {
			lblMedico = new JLabel("");
			lblMedico.setBounds(239, 33, 151, 31);
		}
		return lblMedico;
	}
	private JPanel getPnOpciones() {
		if (pnOpciones == null) {
			pnOpciones = new JPanel();
			pnOpciones.setBorder(new TitledBorder(null, "Citas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnOpciones.setBounds(69, 100, 693, 352);
			pnOpciones.setLayout(null);
			pnOpciones.add(getBtnCitas());
		}
		return pnOpciones;
	}
	private JButton getBtnSalir_1() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnSalir.setBounds(673, 503, 89, 23);
		}
		return btnSalir;
	}
}
