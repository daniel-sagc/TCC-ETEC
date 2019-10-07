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

public class VerEstoque extends JFrame {

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
	private JTextField txtTipo;
	private JTextField txtCod;
	private JTextField txtEspeci;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerEstoque frame = new VerEstoque();
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
	public VerEstoque() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		gEstoque = new gEstoqueDAO();
		bd = new BD();
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 381, 491);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
	
		
		JLabel lblNewLabel_1 = new JLabel("Categoria:");
		lblNewLabel_1.setBounds(41, 97, 86, 14);
		panel.add(lblNewLabel_1);
		
		txtCat = new JTextField();
		txtCat.setBounds(131, 92, 96, 25);
		panel.add(txtCat);
		txtCat.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Quantidade:");
		lblNewLabel_2.setBounds(41, 133, 75, 14);
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
		txtQTDE.setBounds(131, 128, 96, 25);
		panel.add(txtQTDE);
		txtQTDE.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Especifica\u00E7\u00E3o:");
		lblNewLabel_3.setBounds(41, 218, 86, 14);
		panel.add(lblNewLabel_3);
		
		JButton btnVolta = new JButton("Cancelar");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerenciarEstoque.estoque();
				dispose();
			}
		});
		btnVolta.setBounds(41, 437, 89, 23);
		panel.add(btnVolta);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( txtCat.getText().equals("") || txtQTDE.getText().equals("") ||
			 txtTipo.getText().equals("")){
					JOptionPane.showMessageDialog(panel, "Preencha os dados");
					return;
				}
				if (!bd.getConnection()) {
	                  JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado!");
	                  System.exit(0);
				 }
				gEstoque.gEstoque.setCodigo(txtCod.getText());
				 gEstoque.gEstoque.setCategoria(txtCat.getText());
				 gEstoque.gEstoque.setTipo(txtTipo.getText());
		         gEstoque.gEstoque.setQuantidade(txtQTDE.getText());
		         gEstoque.gEstoque.setEspecificacao(txtEspeci.getText());
		        JOptionPane.showMessageDialog(null, gEstoque.atualizar(gEstoqueDAO.ALTERACAO));
		        
					
			}
		});
		btnAlterar.setBounds(258, 437, 89, 23);
		panel.add(btnAlterar);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo:");
		lblNewLabel_4.setBounds(79, 169, 46, 14);
		panel.add(lblNewLabel_4);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(131, 164, 96, 25);
		panel.add(txtTipo);
		txtTipo.setColumns(10);
		
		JButton btnExcloe = new JButton("Excluir");
		btnExcloe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					
	                gEstoque.gEstoque.setCodigo(txtCod.getText());
					
					
	                gEstoque.localizar();
	                int n = JOptionPane.showConfirmDialog(null,gEstoque.gEstoque.getCodigo(),
	                        " Excluir o Item? ",  JOptionPane.YES_NO_OPTION);
	                if (n == JOptionPane.YES_OPTION) {
	                    JOptionPane.showMessageDialog(null, gEstoque.atualizar(gEstoqueDAO.EXCLUSAO));
	                }  
	                GerenciarEstoque.estoque();
					dispose();
			}
		});
		btnExcloe.setBounds(153, 437, 89, 23);
		panel.add(btnExcloe);
		
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(43, 61, 46, 14);
		panel.add(lblNewLabel);
		
		txtCod = new JTextField();
		txtCod.setEnabled(false);
		txtCod.setBounds(131, 56, 96, 25);
		panel.add(txtCod);
		txtCod.setColumns(10);
		
		txtEspeci = new JTextField();
		txtEspeci.setBounds(130, 215, 97, 87);
		panel.add(txtEspeci);
		txtEspeci.setColumns(10);
		
		
		
	}
	
	public static void dispositivo() {
		CadastroDispo frame = new CadastroDispo();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	public void limparcampos(){
		txtCat.setText("");
		txtQTDE.setText("");
		
	}
	public void recebendo(String cod,String categoria,String quantidade,String tipo,String especi){
		txtCod.setText(cod);
		txtCat.setText(categoria);
		txtQTDE.setText(quantidade);
		txtTipo.setText(tipo);
		txtEspeci.setText(especi);
		
		
		
	}
	public void keyTyped(KeyEvent ev) {

		String caracteres="0987654321";

		       if(caracteres.contains(ev.getKeyChar()+"")){

		              ev.consume();

		       }
	};
}
