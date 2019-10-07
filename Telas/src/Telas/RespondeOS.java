package Telas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RespondeOS extends JFrame {
	private JTextField txtData;
	private JTextField txtDataFinal;
	private JTextField txtDispositivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RespondeOS frame = new RespondeOS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RespondeOS() {
		setTitle("Responde OS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		getContentPane().setLayout(null);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(44, 445, 89, 23);
		getContentPane().add(btnEnviar);
		
		JButton btnRelat = new JButton("Relat\u00F3rio");
		btnRelat.setBounds(174, 445, 89, 23);
		getContentPane().add(btnRelat);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.abrir();
				dispose();
				
			}
		});
		btnCancelar.setBounds(304, 445, 89, 23);
		getContentPane().add(btnCancelar);
		
		txtData = new JTextField();
		txtData.setBounds(87, 105, 260, 20);
		getContentPane().add(txtData);
		txtData.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Remetente");
		lblNewLabel.setBounds(87, 55, 86, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Data:");
		lblNewLabel_1.setBounds(87, 80, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Problema Relatado");
		lblNewLabel_2.setBounds(87, 153, 97, 14);
		getContentPane().add(lblNewLabel_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(200, 148, 147, 91);
		getContentPane().add(textArea);
		
		txtDataFinal = new JTextField();
		txtDataFinal.setText("Data Final");
		txtDataFinal.setBounds(94, 403, 190, 20);
		getContentPane().add(txtDataFinal);
		txtDataFinal.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Solu\u00E7\u00E3o");
		lblNewLabel_3.setBounds(87, 306, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JTextArea txtSoluça = new JTextArea();
		txtSoluça.setBounds(200, 301, 147, 91);
		getContentPane().add(txtSoluça);
		
		txtDispositivo = new JTextField();
		txtDispositivo.setText("Dispositivo");
		txtDispositivo.setBounds(87, 262, 260, 20);
		getContentPane().add(txtDispositivo);
		txtDispositivo.setColumns(10);
	}
	public static void RespondeOs(){
		RespondeOS frame = new RespondeOS();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
