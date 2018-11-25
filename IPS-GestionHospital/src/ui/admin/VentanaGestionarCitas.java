package ui.admin;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

import business.CitasController;
import business.dto.CitaDto;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class VentanaGestionarCitas extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel panelLista;
	private JPanel panelBotones;
	private JLabel lblLista;
	private DefaultListModel<CitaDto> modeloCitas;
	private CitasController citasController;
	private JList<CitaDto> list;
	private JButton btnModificar;
	private JButton btnEliminar;
	
	public VentanaGestionarCitas() {
		modeloCitas = new DefaultListModel<>();
		citasController = new CitasController();
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanelPrincipal());
		cargarModelo();
	}

	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
			panelPrincipal.setLayout(new BorderLayout(0, 0));
			panelPrincipal.add(getPanelLista(), BorderLayout.CENTER);
			panelPrincipal.add(getPanelBotones(), BorderLayout.SOUTH);
		}
		return panelPrincipal;
	}
	private JPanel getPanelLista() {
		if (panelLista == null) {
			panelLista = new JPanel();
			panelLista.setLayout(new BorderLayout(0, 0));
			panelLista.add(getLblLista(), BorderLayout.NORTH);
			panelLista.add(getList(), BorderLayout.CENTER);
		}
		return panelLista;
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.add(getBtnModificar());
			panelBotones.add(getBtnEliminar());
		}
		return panelBotones;
	}
	private JLabel getLblLista() {
		if (lblLista == null) {
			lblLista = new JLabel("Listado de Citas");
			lblLista.setFont(new Font("Tahoma", Font.PLAIN, 37));
			lblLista.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblLista;
	}
	private JList<CitaDto> getList() {
		if (list == null) {
			list = new JList<>(modeloCitas);
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					CitaDto seleccion = list.getSelectedValue();
					if(seleccion!=null) {
						getBtnEliminar().setEnabled(true);
						getBtnModificar().setEnabled(true);
					}
				}
			});
		}
		return list;
	}
	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar");
			btnModificar.setEnabled(false);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaModificarCitas vv = new VentanaModificarCitas(list.getSelectedValue());
					vv.setVisible(true);
					vv.setLocationRelativeTo(null);
				}
			});
		}
		return btnModificar;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar ");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					citasController.eliminarCita(list.getSelectedValue());
					//TODO: eliminar el elemento de la lista dinamicamente. No se como hacerlo jajaxd
				}
			});
			btnEliminar.setEnabled(false);
		}
		return btnEliminar;
	}
	
	private void cargarModelo() {
		List<CitaDto> citas = citasController.getListadoCompletoDecitas();
		for(CitaDto cita: citas) {
			modeloCitas.addElement(cita);
		}
		
	}

}
