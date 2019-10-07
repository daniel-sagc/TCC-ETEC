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
import javax.swing.ImageIcon;
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
import java.text.ParseException;

import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;

public class TelaUsuario extends JFrame {

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
	MaskFormatter maskData = new MaskFormatter();
	private JTextField txtData;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuario frame = new TelaUsuario();
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
	public TelaUsuario() {
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
		txtNome.setBounds(43, 66, 154, 25);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Data de Nascimento:");
		lblNewLabel_1.setBounds(43, 91, 154, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setBounds(43, 133, 92, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Senha:");
		lblNewLabel_3.setBounds(43, 175, 118, 14);
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
		
		JRadioButton RD1 = new JRadioButton("ADM");
		RD1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "ADM";
			}
		});
		RD1.setBounds(26, 256, 109, 44);
		contentPane.add(RD1);
		
		JRadioButton RD2 = new JRadioButton("Usuario");
		RD2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Usuario";
			}
		});
		RD2.setBounds(171, 256, 109, 44);
		contentPane.add(RD2);
		
		JButton btnVolta = new JButton("Cancelar");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroUsuario.usucadastro();
				dispose();
			}
		});
		btnVolta.setBounds(72, 424, 89, 25);
		contentPane.add(btnVolta);
		
		JButton btnADD = new JButton("Cadastro");
		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtNome.getText().equals("") || 
					 
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
					 usuario.usuario.setDataNasci(txtData.getText().toString()); 
			         usuario.usuario.setEmail(txtEmail.getText());
			         usuario.usuario.setTipo(tipo);
			         usuario.usuario.setSenha(txtSenha.getText().toString());
			         usuario.usuario.setLogin(txtLogin.getText());
			          JOptionPane.showMessageDialog(null, usuario.atualizar(UsuarioDAO.INCLUSAO));			      
			          
			          
			}
			
		});
		btnADD.setBounds(234, 424, 89, 25);
		contentPane.add(btnADD);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(43, 192, 126, 20);
		contentPane.add(txtSenha);
		
		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setBounds(43, 106, 154, 25);
		contentPane.add(txtData);
		txtData.setColumns(10);
		
		JButton btnHelp = new JButton (new ImageIcon("imgs/help.png"));
		btnHelp.setBorderPainted(false);
		btnHelp.setContentAreaFilled(false);
		btnHelp.setFocusPainted(false);
		btnHelp.setToolTipText("Todos os campos são obrigatórios!!"
				+"Cadastrar um usuário como ADM da ele acesso a todo o sistema, como usuário"
				+"têm restrições");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHelp.setBounds(350, 11, 25, 25);
		contentPane.add(btnHelp);
		
		
		
		bd = new BD();
		ButtonGroup group = new ButtonGroup();
		group.add(rd2);
		group.add(rd1);
		group.add(rd3);
		group.add(rd4);
		group.add(rd5);
		
		String[] cLocalItems = { "LAB I", "LAB II", "LAB III", "GESTÃO",
				"LÓGICA", "DIRETORIA" };
		
		
		
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
	public void recebendo(String nome, String data,String email,String senha){
		txtNome.setText(nome);
		txtData.setText(data);
		txtEmail.setText(email);
		txtSenha.setText(senha);
		
		
	}
	
	private MaskFormatter setMascara(String mascara){  
	    MaskFormatter mask = null;  
	    try{  
	        mask = new MaskFormatter(mascara);                        
	        }catch(java.text.ParseException ex){}  
	    return mask;  
	}  

	
}
