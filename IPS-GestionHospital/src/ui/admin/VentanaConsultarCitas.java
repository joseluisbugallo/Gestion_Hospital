package ui.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import business.CitasController;
import business.LogController;
import business.dto.CambioDto;
import business.dto.CitaDto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;

public class VentanaConsultarCitas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlCentro;
	private JLabel lblListadoDeCitas;
	private JList<CitaDto> lsCitas;
	
	private DefaultListModel<CitaDto> modeloCitas;
	private CitasController citasController;
	private JTextField textFieldIdMed;
	private JButton btnBuscarMed;

	/**
	 * Create the frame.
	 */
	public VentanaConsultarCitas() {
		modeloCitas = new DefaultListModel<>();
		citasController = new CitasController();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnlCentro(), "name_2784946257351");
		cargarModelo();
		
		CambioDto cambio = new CambioDto();
		cambio.cambio = "El administrador ha listado las citas";
		cambio.fecha = new Date();					
		LogController lc = new LogController();
		lc.añadirCambio(cambio);
	}
	private JPanel getPnlCentro() {
		if (pnlCentro == null) {
			pnlCentro = new JPanel();
			pnlCentro.setLayout(null);
			pnlCentro.add(getLsCitas());
			pnlCentro.add(getLblListadoDeCitas());
			pnlCentro.add(getTextFieldIdMed());
			pnlCentro.add(getBtnBuscarMed());
		}
		return pnlCentro;
	}
	private JLabel getLblListadoDeCitas() {
		if (lblListadoDeCitas == null) {
			lblListadoDeCitas = new JLabel("---------------Listado de citas---------------");
			lblListadoDeCitas.setBounds(48, 13, 682, 46);
			lblListadoDeCitas.setHorizontalAlignment(SwingConstants.CENTER);
			lblListadoDeCitas.setFont(new Font("Tahoma", Font.PLAIN, 38));
		}
		return lblListadoDeCitas;
	}
	private JList<CitaDto> getLsCitas() {
		if (lsCitas == null) {
			lsCitas = new JList<>(modeloCitas);
			lsCitas.setBounds(0, 109, 784, 433);
			lsCitas.setValueIsAdjusting(true);
			lsCitas.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		}
		return lsCitas;
	}
	
	
	private void cargarModelo() {
		List<CitaDto> citas = citasController.getListadoCompletoDecitas();
		for(CitaDto cita: citas) {
			modeloCitas.addElement(cita);
		}
		
	}
	private JTextField getTextFieldIdMed() {
		if (textFieldIdMed == null) {
			textFieldIdMed = new JTextField();
			textFieldIdMed.setBounds(12, 74, 116, 22);
			textFieldIdMed.setColumns(10);
		}
		return textFieldIdMed;
	}
	private JButton getBtnBuscarMed() {
		if (btnBuscarMed == null) {
			btnBuscarMed = new JButton("Buscar por m\u00E9dico");
			btnBuscarMed.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					modeloCitas.removeAllElements();
					cargarModeloMed();
				}
			});
			btnBuscarMed.setBounds(140, 71, 154, 25);
		}
		return btnBuscarMed;
	}
	
	private void cargarModeloMed() {
		List<CitaDto> citas = citasController.obtenerCitasEmpleado(Integer.parseInt(textFieldIdMed.getText()));
		for(CitaDto cita: citas) {
			modeloCitas.addElement(cita);
		}
		
	}
}

