package Telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VerUsuario extends JFrame {

	private JPanel contentPane;
	private BD bd;
	private PreparedStatement statement;
	private ResultSet rs;
	private String tipo = "";
	private JRadioButton rd1,rd2,rd3,rd4,rd5;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtLogin;
	private UsuarioDAO usuario;
	private JPasswordField txtSenha;
	private JTextField txtTipo;
	private JTextField txtData;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerUsuario frame = new VerUsuario();
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
	public VerUsuario() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		
		JLabel lblNewLabel = new JLabel("Nome Completo:");
		lblNewLabel.setBounds(43, 51, 126, 14);
		contentPane.add(lblNewLabel);
		usuario = new UsuarioDAO();
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setBounds(43, 66, 154, 25);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Data de Nascimento:");
		lblNewLabel_1.setBounds(43, 91, 126, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setBounds(43, 133, 71, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Senha:");
		lblNewLabel_3.setBounds(43, 175, 71, 14);
		contentPane.add(lblNewLabel_3);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(43, 148, 154, 25);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Login:");
		lblNewLabel_4.setBounds(234, 51, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(234, 66, 86, 25);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JButton btnVolta = new JButton("Cancelar");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroUsuario.usucadastro();
				dispose();
			}
		});
		btnVolta.setBounds(43, 424, 89, 25);
		contentPane.add(btnVolta);
		
		JButton btnADD = new JButton("Alterar");
		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtNome.getText().equals("") || 
						txtData.getText().equals("") || 
						txtEmail.getText().equals("") || 
						txtSenha.getText().equals("")){
							Component pnTable = null;
							JOptionPane.showMessageDialog(pnTable, "Preencha os dados");
							return;
						}
							if (!bd.getConnection()) {
			                  JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado!");
			                  System.exit(0);
						 }
					 usuario.usuario.setNome(txtNome.getText());  
					 usuario.usuario.setDataNasci(txtData.getText()); 
			         usuario.usuario.setEmail(txtEmail.getText());
			         usuario.usuario.setTipo(txtTipo.getText());
			         usuario.usuario.setSenha(txtSenha.getText().toString());
			         usuario.usuario.setLogin(txtLogin.getText());
			          JOptionPane.showMessageDialog(null, usuario.atualizar(UsuarioDAO.ALTERACAO));			      
			          
			          
			}
			
		});
		btnADD.setBounds(267, 424, 89, 25);
		contentPane.add(btnADD);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(43, 191, 126, 25);
		contentPane.add(txtSenha);
		
		JLabel lblNewLabel_5 = new JLabel("Tipo:");
		lblNewLabel_5.setBounds(43, 221, 71, 14);
		contentPane.add(lblNewLabel_5);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(43, 237, 118, 25);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		
		JButton btExcloe = new JButton("Excluir");
		btExcloe.addActionListener(new ActionListener() {
			
			
			
			public void actionPerformed(ActionEvent arg0) {
				
				
                usuario.usuario.setNome(txtNome.getText());
				
				
                usuario.localizar();
                int n = JOptionPane.showConfirmDialog(null,usuario.usuario.getNome(),
                        " Excluir o Item? ",  JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, usuario.atualizar(UsuarioDAO.EXCLUSAO));
                }  
                CadastroUsuario.usucadastro();
                dispose();
		}
		});
		btExcloe.setBounds(154, 425, 89, 25);
		contentPane.add(btExcloe);
		
		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
				String caracteres="0987654321";

			       if(!caracteres.contains(ev.getKeyChar()+"")){

			              ev.consume();

			       }
			}
		});
		txtData.setBounds(43, 106, 154, 25);
		contentPane.add(txtData);
		txtData.setColumns(10);
		bd = new BD();
		
		
		
		
		
	}
	
	public static void dispositivo() {
		TelaUsuario frame = new TelaUsuario();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	public void limparcampos(){
		txtNome.setText("");
		txtEmail.setText("");
		
	}
	public void recebendo(String nome, String data,String email,String senha,String login){
		txtNome.setText(nome);
		txtData.setText(data.toString());
		txtEmail.setText(email);
		txtTipo.setText(senha);
		txtLogin.setText(login);
		
	}
	
	private MaskFormatter setMascara(String mascara){  
	    MaskFormatter mask = null;  
	    try{  
	        mask = new MaskFormatter(mascara);                        
	        }catch(java.text.ParseException ex){}  
	    return mask;  
	}  
	public void keyTyped(KeyEvent ev) {

		String caracteres="0987654321";

		       if(caracteres.contains(ev.getKeyChar()+"")){

		              ev.consume();

		       }
	};
}
