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
import javax.swing.JList;
import java.awt.BorderLayout;
public class VentanaCitasMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private EmpleadoDto medico;
	private JList list;
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

	/**
	 * Constructor de la ventana.
	 */
	public VentanaCitasMedico(EmpleadoDto medico) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100,100,800,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getList());
		this.medico= medico;
		
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
		}
		return list;
	}
}
