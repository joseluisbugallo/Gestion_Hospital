package ui.medico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import business.dto.EmpleadoDto;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class VentanaMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton btnCitas;
	private EmpleadoDto medico;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaMedico vP = new VentanaMedico();
//					vP.setLocationRelativeTo(null);
//					vP.setTitle("Menu Principal...");
//					vP.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public EmpleadoDto getMedico() {
		return medico;
	}

	/**
	 * Constructor de la ventana.
	 */
	public VentanaMedico(EmpleadoDto medico) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100,100,800,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));
		contentPane.add(getBtnCitas());
		this.medico= medico;
		
	}

	private JButton getBtnCitas() {
		if (btnCitas == null) {
			btnCitas = new JButton("Comprobar mis citas");
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
}
