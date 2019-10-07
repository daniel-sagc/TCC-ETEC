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
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastroDispo extends JFrame {

	private JPanel contentPane;
	private JTextField txtPatrimonio;
	public DispositivoDAO dispo;
	private BD bd;
	private PreparedStatement statement;
	private ResultSet rs;
	private JComboBox cLocal;
	private JTextField txtModel;
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
					CadastroDispo frame = new CadastroDispo();
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
	public CadastroDispo() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		dispo = new DispositivoDAO();
		bd = new BD();
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 371, 491);
		contentPane.add(panel);
		panel.setLayout(null);
		ButtonGroup group = new ButtonGroup();
		group.add(rd2);
		group.add(rd1);
		group.add(rd3);
		group.add(rd4);
		group.add(rd5);
		
		
		String[] cLocalItems = { "LAB I", "LAB II", "LAB III", "GESTÃO",
				"LÓGICA", "DIRETORIA" };
		
		JLabel lblNewLabel = new JLabel("Categoria:");
		lblNewLabel.setBounds(10, 22, 67, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("N\u00BAPatrimonio:");
		lblNewLabel_1.setBounds(42, 102, 86, 14);
		panel.add(lblNewLabel_1);
		
		txtPatrimonio = new JTextField();
		txtPatrimonio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
				String caracteres="0987654321";

			       if(!caracteres.contains(ev.getKeyChar()+"")){

			              ev.consume();
			       }
					
				}
			});
			
		txtPatrimonio.setBounds(131, 97, 96, 25);
		panel.add(txtPatrimonio);
		txtPatrimonio.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Modelo:");
		lblNewLabel_2.setBounds(42, 130, 46, 14);
		panel.add(lblNewLabel_2);
		
		txtModel = new JTextField();
		txtModel.setText("");
		txtModel.setBounds(131, 127, 96, 25);
		panel.add(txtModel);
		txtModel.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Especifica\u00E7\u00E3o:");
		lblNewLabel_3.setBounds(42, 197, 86, 14);
		panel.add(lblNewLabel_3);
		
		JTextArea txtArea = new JTextArea();
		txtArea.setTabSize(0);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setBounds(138, 197, 127, 105);
		panel.add(txtArea);
		
		JButton btnVolta = new JButton("Cancelar");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarDispositivo.dispositivo();
				dispose();
			}
		});
		btnVolta.setBounds(61, 437, 89, 23);
		panel.add(btnVolta);
		
		JButton btnADD = new JButton("Cadastrar");
		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtModel.getText().equals("")
						|| txtPatrimonio.getText().equals("")) {
					JOptionPane.showMessageDialog(panel, "Preencha os dados");
					return;
				}
				 if (!bd.getConnection()) {
	                  JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado!");
	                  System.exit(0);
				 }
			 dispo.dispo.setPatrimonio(txtPatrimonio.getText());  
			 dispo.dispo.setNome(tipo); 
	         dispo.dispo.setModelo(txtModel.getText());
	         dispo.dispo.setLocal(cLocal.getSelectedItem().toString());
	         dispo.dispo.setEspeci(txtArea.getText());
	          JOptionPane.showMessageDialog(null, dispo.atualizar(DispositivoDAO.INCLUSAO));
	          limparcampos();
	        
	          
			}
		});
		btnADD.setBounds(240, 437, 89, 23);
		panel.add(btnADD);
		
		JLabel lblNewLabel_4 = new JLabel("Local:");
		lblNewLabel_4.setBounds(42, 164, 46, 14);
		panel.add(lblNewLabel_4);
		
		JRadioButton rd1 = new JRadioButton("PC");
		rd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "PC";
			}
		});
		rd1.setBounds(6, 58, 51, 23);
		panel.add(rd1);
		
		JRadioButton rd2 = new JRadioButton("IMPRESSORA");
		rd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "IMPRESSORA";
			}
		});
		rd2.setBounds(54, 58, 96, 23);
		panel.add(rd2);
		
		JRadioButton rd3 = new JRadioButton("NOTEBOOK");
		rd3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "NOTEBOOK";
			}
		});
		rd3.setBounds(152, 58, 86, 23);
		panel.add(rd3);
		
		JRadioButton rd4 = new JRadioButton("MONITOR");
		rd4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "MONITOR";
			}
		});
		rd4.setBounds(240, 58, 73, 23);
		panel.add(rd4);
		
		JRadioButton rd5 = new JRadioButton("ACES.");
		rd5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "ACESSÓRIO";
			}
		});
		rd5.setBounds(315, 58, 60, 23);
		panel.add(rd5);
		
		
		cLocal = new JComboBox(cLocalItems);
		cLocal.setBounds(146, 161, 81, 25);
		panel.add(cLocal);
		
		
		group.add(rd2);
		group.add(rd1);
		group.add(rd3);
		group.add(rd4);
		group.add(rd5);
		
		JButton btnHelp = new JButton(new ImageIcon("imgs/help.png"));
		btnHelp.setBorderPainted(false);
		btnHelp.setContentAreaFilled(false);
		btnHelp.setFocusPainted(false);
		btnHelp.setToolTipText("Todos os campos são obrigatórios!!"
				+ "\nO número de patrimônio se localiza atrás, ou abaixo do gabinete");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHelp.setBounds(325, 17, 25, 25);
		panel.add(btnHelp);
		
		
		
	}
	
	public static void dispositivo() {
		CadastroDispo frame = new CadastroDispo();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	public void limparcampos(){
		txtPatrimonio.setText("");
		txtModel.setText("");
		

	}
	public void keyTyped(KeyEvent ev) {

		String caracteres="0987654321";

		       if(caracteres.contains(ev.getKeyChar()+"")){

		              ev.consume();

		       }
	};
}
