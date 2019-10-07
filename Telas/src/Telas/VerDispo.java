package Telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
import javax.swing.DropMode;
import javax.swing.SwingConstants;

public class VerDispo extends JFrame {

	private JPanel contentPane;
	private JTextField txtPatrimonio;
	public DispositivoDAO dispo;
	private BD bd;
	private PreparedStatement statement;
	private ResultSet rs;
	private JTextField txtModel;
	private JTextArea textArea;
	private String tipo = "";
	private JLabel label;
	private JTextField txtEspeci;
	private JTextField txtCat;
	private JTextField txtLocal;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerDispo frame = new VerDispo();
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
	public VerDispo() {
		setTitle("Registro");
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
		
		
		String[] cLocalItems = { "LAB I", "LAB II", "LAB III", "GESTÃO",
				"LÓGICA", "DIRETORIA" };
		
		JLabel lblNewLabel = new JLabel("Categoria:");
		lblNewLabel.setBounds(42, 71, 67, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("N\u00BAPatrimonio:");
		lblNewLabel_1.setBounds(42, 102, 86, 14);
		panel.add(lblNewLabel_1);
		
		txtPatrimonio = new JTextField();
		txtPatrimonio.setEnabled(false);
		txtPatrimonio.setBounds(142, 97, 96, 25);
		panel.add(txtPatrimonio);
		txtPatrimonio.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Modelo:");
		lblNewLabel_2.setBounds(42, 130, 46, 14);
		panel.add(lblNewLabel_2);
		
		txtModel = new JTextField();
		txtModel.setBounds(142, 125, 96, 25);
		panel.add(txtModel);
		txtModel.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Especifica\u00E7\u00E3o:");
		lblNewLabel_3.setBounds(42, 197, 86, 14);
		panel.add(lblNewLabel_3);
		
		JButton btnVolta = new JButton("Voltar");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarDispositivo.dispositivo();
				dispose();
			}
		});
		btnVolta.setBounds(42, 437, 89, 23);
		panel.add(btnVolta);
		
		JButton btnAltera = new JButton("Alterar");
		btnAltera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtModel.getText().equals("")
						|| txtPatrimonio.getText().equals("")) {
					JOptionPane.showMessageDialog(panel, "Preencha os dados");
					return;
				}
				 dispo.dispo.setPatrimonio(txtPatrimonio.getText());  
				 dispo.dispo.setNome(txtCat.getText()); 
		         dispo.dispo.setModelo(txtModel.getText());
		         dispo.dispo.setLocal(txtLocal.getText());
		                JOptionPane.showMessageDialog(null, dispo.atualizar(DispositivoDAO.ALTERACAO));
		              
		               
		           }
		});
		btnAltera.setBounds(254, 437, 89, 23);
		panel.add(btnAltera);
		
		JLabel lblNewLabel_4 = new JLabel("Local:");
		lblNewLabel_4.setBounds(42, 164, 46, 14);
		panel.add(lblNewLabel_4);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		
	
                dispo.dispo.setPatrimonio(txtPatrimonio.getText());
				
				
                dispo.localizar();
                int n = JOptionPane.showConfirmDialog(null, dispo.dispo.getModelo(),
                        " Excluir o Filme? ", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, dispo.atualizar(DispositivoDAO.EXCLUSAO));
                 
                }
                CadastrarDispositivo.dispositivo();
				dispose();
		}
		});				
		btnExcluir.setBounds(155, 437, 89, 23);
		panel.add(btnExcluir);
		
		txtEspeci = new JTextField();
		txtEspeci.setBounds(127, 194, 125, 75);
		panel.add(txtEspeci);
		txtEspeci.setColumns(10);
		
		txtCat = new JTextField();
		txtCat.setBounds(142, 66, 99, 25);
		panel.add(txtCat);
		txtCat.setColumns(10);
		
		txtLocal = new JTextField();
		txtLocal.setBounds(142, 161, 96, 25);
		panel.add(txtLocal);
		txtLocal.setColumns(10);
		
		
	}
	
	public static void dispositivo() {
		VerDispo frame = new VerDispo();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public void recebendo(String nome, String Num,String especi,String modelo,String tipo){
		txtPatrimonio.setText(modelo);
		txtModel.setText(nome);
		txtEspeci.setText(especi);
		txtCat.setText(Num);
		
	}
}
