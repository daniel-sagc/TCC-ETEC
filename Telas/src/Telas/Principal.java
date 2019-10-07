package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.text.ParseException;

import javax.swing.border.LineBorder;

public class Principal extends JFrame {

	private JPanel contentPane;
	private BD bd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setTitle("Tela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		bd = new BD();
		
		
		Nimbus.pegaNimbus();
		
		
		JButton btnUsuário = new JButton("");
		btnUsuário.setForeground(SystemColor.info);
		 btnUsuário.setToolTipText("Cadastrar Usúario");
	
		btnUsuário.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroUsuario.usucadastro();
				dispose();

				
			}
		});
		btnUsuário.setVerticalAlignment(SwingConstants.BOTTOM);
		btnUsuário.setBackground(SystemColor.menuText);
		btnUsuário.setIcon(new ImageIcon("imgs/interface.png"));
		btnUsuário.setBounds(50, 46, 140, 145);
		contentPane.add(btnUsuário);
		
		JButton btnEstoque = new JButton("");
		 btnEstoque.setToolTipText("Cadastrar Estoque");
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				GerenciarEstoque.estoque();
				dispose();

			}
		});
		btnEstoque.setIcon(new ImageIcon("imgs/buildings.png"));
		btnEstoque.setBackground(SystemColor.menuText);
	
		
		btnEstoque.setBounds(261, 46, 140, 145);
		contentPane.add(btnEstoque);
		
		JButton btnOS = new JButton("");
		 btnOS.setToolTipText("Responder OS");
		btnOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RespondeOS.RespondeOs();
				dispose();

			}
		});
		btnOS.setIcon(new ImageIcon("imgs/write.png"));
		btnOS.setBackground(Color.BLACK);
		btnOS.setBounds(455, 46, 140, 145);
		contentPane.add(btnOS);
		
		JButton btnDispo = new JButton("");
		btnDispo.setToolTipText("Cadastrar Dispositivos");
		btnDispo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarDispositivo.dispositivo();
				dispose();

				
			}
		});
		btnDispo.setIcon(new ImageIcon("imgs/printer.png"));
		btnDispo.setVerticalAlignment(SwingConstants.BOTTOM);
		btnDispo.setBackground(Color.BLACK);
		btnDispo.setBounds(50, 229, 140, 145);
		contentPane.add(btnDispo);
		
		JButton btnPEDI = new JButton("");
		btnPEDI.setToolTipText("Solicitar material");
		btnPEDI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoEstoque.pedido();
				dispose();

			}
		});
		btnPEDI.setIcon(new ImageIcon("imgs/add.png"));
		btnPEDI.setVerticalAlignment(SwingConstants.BOTTOM);
		btnPEDI.setBackground(Color.BLACK);
		btnPEDI.setBounds(261, 229, 140, 145);
		contentPane.add(btnPEDI);
		
		JButton btnBackup = new JButton("");
		btnBackup.setToolTipText("Backup");
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBackup.setIcon(new ImageIcon("imgs/arrow.png"));
		btnBackup.setVerticalAlignment(SwingConstants.BOTTOM);
		btnBackup.setBackground(Color.BLACK);
		btnBackup.setBounds(455, 229, 140, 145);
		contentPane.add(btnBackup);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.setToolTipText("Voltar para a tela de login");
		btnVoltar.setBackground(SystemColor.menuText);
		btnVoltar.setIcon(new ImageIcon("imgs/exit.png"));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Deseja realmente voltar a tela de Login?",
						"Sair", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0){
					Login.login();
					dispose();
					
				}
				
				
			}
		});
		btnVoltar.setBounds(564, 397, 65, 74);
		contentPane.add(btnVoltar);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Fechar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(JOptionPane.showConfirmDialog(null, "Deseja realmente sair do sistema?",
						"Sair", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0){
					System.exit(0);	
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon("imgs/icon.png"));
		btnNewButton.setBounds(633, 11, 54, 43);
		contentPane.add(btnNewButton);
	}
	public static void abrir(){
		Principal frame = new Principal();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
}
