package Telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastroEstoque extends JFrame {

	private JPanel contentPane;
	private JTextField txtCat;
	private gEstoqueDAO  gEstoque;
	private BD bd;
	private PreparedStatement statement;
	private ResultSet rs;
	private JTextField txtQTDE;
	private String tipo = "";
	private JRadioButton rd1,rd2,rd3,rd4,rd5;
	private JTextArea txtArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroEstoque frame = new CadastroEstoque();
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
	public CadastroEstoque() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		gEstoque = new gEstoqueDAO();
		bd = new BD();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 381, 491);
		contentPane.add(panel);
		panel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		
		
		
	
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(52, 75, 86, 14);
		panel.add(lblNewLabel_1);
		
		txtCat = new JTextField();
		txtCat.setBounds(131, 70, 96, 25);
		panel.add(txtCat);
		txtCat.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Quantidade:");
		lblNewLabel_2.setBounds(52, 111, 75, 14);
		panel.add(lblNewLabel_2);
		
		txtQTDE = new JTextField();
		txtQTDE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {

				String caracteres="0987654321";

			       if(!caracteres.contains(ev.getKeyChar()+"")){

			              ev.consume();

			       }
			}
		});
		txtQTDE.setText("");
		txtQTDE.setBounds(131, 106, 96, 25);
		panel.add(txtQTDE);
		txtQTDE.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Especifica\u00E7\u00E3o:");
		lblNewLabel_3.setBounds(41, 203, 86, 14);
		panel.add(lblNewLabel_3);
		
		JTextArea txtArea = new JTextArea();
		txtArea.setTabSize(0);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setBounds(131, 198, 127, 105);
		panel.add(txtArea);
		
		JButton btnVolta = new JButton("Cancelar");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciarEstoque.estoque();
				dispose();
			}
		});
		btnVolta.setBounds(61, 437, 89, 23);
		panel.add(btnVolta);
		
		JButton btnADD = new JButton("Cadastrar");
		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( txtCat.getText().equals("") || txtQTDE.getText().equals("")){
					JOptionPane.showMessageDialog(panel, "Preencha os dados");
					return;
				}
				if (!bd.getConnection()) {
	                  JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado!");
	                  System.exit(0);
				 }
				
				 gEstoque.gEstoque.setCategoria(txtCat.getText());
				 gEstoque.gEstoque.setTipo(tipo);
		         gEstoque.gEstoque.setQuantidade(txtQTDE.getText());
		         gEstoque.gEstoque.setEspecificacao(txtArea.getText());
		        JOptionPane.showMessageDialog(null, gEstoque.atualizar(gEstoqueDAO.INCLUSAO));
		        
					
			}
		});
		btnADD.setBounds(240, 437, 89, 23);
		panel.add(btnADD);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo:");
		lblNewLabel_4.setBounds(73, 147, 46, 14);
		panel.add(lblNewLabel_4);
		
		JRadioButton rdbtnPea = new JRadioButton("Pe\u00E7a");
		rdbtnPea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Peça";
			}
		});
		rdbtnPea.setBounds(118, 143, 55, 23);
		panel.add(rdbtnPea);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Tinta");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Tinta";
			}
		});
		rdbtnNewRadioButton.setBounds(189, 143, 62, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Acess\u00F3rio");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Acessório";
			}
		});
		rdbtnNewRadioButton_1.setBounds(253, 143, 86, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		
		
	}
	
	public static void dispositivo() {
		CadastroEstoque frame = new CadastroEstoque();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	public void limparcampos(){
		txtCat.setText("");
		txtQTDE.setText("");
		
	}
	public void keyTyped(KeyEvent ev) {

		String caracteres="0987654321";

		       if(caracteres.contains(ev.getKeyChar()+"")){

		              ev.consume();

		       }
	};
		 
}
