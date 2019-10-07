package Telas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JPasswordField txtSenha;
	private static Login frame;
	private static BD bd;
	private JButton btnLogar;
	private PreparedStatement statement;
	private ResultSet rs;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.bd.getConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 369);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));
	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		bd = new BD();
		
			Nimbus.pegaNimbus();
		
		
			
		txtNome = new JTextField();
		txtNome.setBounds(125, 148, 86, 30);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(125, 125, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setBounds(125, 184, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(125, 209, 86, 30);
		contentPane.add(txtSenha);
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
				statement = bd.con.prepareStatement("select useru, senha from Usuario where useru = '"+txtNome.getText()+"' and senha = '"+txtSenha.getText()+"'");
	            rs = statement.executeQuery();
	            
	            if (rs.next()){
	                Principal.abrir();
	                dispose();
	                
	            }else{
	                JOptionPane.showMessageDialog(null,"Usuario ou senha incorreto","Erro",JOptionPane.ERROR_MESSAGE);
	            }           
	            
			}catch (Exception e1) {
	            JOptionPane.showMessageDialog(null, "Truta  hine " + e1.toString());
	        }

			}
		});
		

		
			
		btnLogar.setBounds(45, 280, 89, 30);
		contentPane.add(btnLogar);
		getRootPane().setDefaultButton(btnLogar);
		
		
		
		
		
	
		

		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(215, 280, 89, 30);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("imgs/ICONE.png"));
		lblNewLabel_2.setBounds(110, 14, 120, 100);
		contentPane.add(lblNewLabel_2);
	}
	
	public static void login(){
		Login frame = new Login();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		bd.getConnection();
		
	}
}
