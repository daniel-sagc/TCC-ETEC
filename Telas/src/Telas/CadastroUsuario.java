package Telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastroUsuario extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnADM,rdbtnUsu;
	private JTable table;
	private PreparedStatement statement;
	private ResultSet rs;
	private DefaultTableModel dtm;
	public UsuarioDAO usuario;
	private JScrollPane scroll;
	private BD bd;
	String tipo = "";
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					CadastroUsuario frame = new CadastroUsuario();
					frame.setVisible(true);
					frame.bd.getConnection();
					frame.atualiza();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroUsuario() {
		setTitle("Usu\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		bd = new BD();
		usuario = new UsuarioDAO();
		ButtonGroup group = new ButtonGroup();
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));
	
		
		
		
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaUsuario.dispositivo();
				dispose();
			}
		});
		
		btnCadastrar.setBounds(25, 103, 89, 25);
		contentPane.add(btnCadastrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.abrir();
				dispose();
				
				
			}
		});
		btnCancelar.setBounds(316, 477, 89, 25);
		contentPane.add(btnCancelar);
		
		JPanel pnTable = new JPanel();
		pnTable.setBounds(10, 139, 414, 327);
		contentPane.add(pnTable);
		pnTable.setLayout(null);
											
		table = new JTable(new DefaultTableModel(		
				new Object[][] {},
					new String[] {
						"Nome", "Data de Nascimento", "Email","Tipo",
											}
				));							
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
													                     TitledBorder.TOP, null, null));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setToolTipText("");
			

			
							table.addMouseListener(new MouseAdapter() {
								@Override
					public void mouseClicked(MouseEvent e) {
							
									
								
					VerUsuario ver = new VerUsuario();
					ver.setVisible(true);
					dispose();
					int linha_seleciona = table.getSelectedRow();
									
					Object dado = (table.getValueAt(linha_seleciona, 0));
					Object dado2 = (table.getValueAt(linha_seleciona, 1));
					Object dado3 = (table.getValueAt(linha_seleciona, 2));
					Object dado4= (table.getValueAt(linha_seleciona, 3));
					Object dado5= (table.getValueAt(linha_seleciona, 4));

					
					
					String dadoP =  String.valueOf(dado.toString());
					String dadoN =  String.valueOf(dado2.toString());
					String dadoM =  String.valueOf(dado3.toString());
					String dadoE =  String.valueOf(dado4.toString());
					String dadoL =  String.valueOf(dado5.toString());

				
									ver.recebendo(dadoP,dadoN,dadoM,dadoE,dadoL);
								
									
								}
							});

			
					// rolagem
												
				scroll = new JScrollPane();
				scroll.setBounds(0, 0, 414, 316);
				pnTable.add(scroll);
				scroll.setViewportView(table);
				
				textField = new JTextField();
				textField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						filtrarNomeNaTabela();
					}
				});
				textField.setBounds(44, 44, 293, 25);
				contentPane.add(textField);
				textField.setColumns(10);
				
				JLabel lblNewLabel = new JLabel("Pesquisar:");
				lblNewLabel.setBounds(44, 22, 55, 14);
				contentPane.add(lblNewLabel);
					
				
				
		
		
	}
	public static void usucadastro(){
		CadastroUsuario frame = new CadastroUsuario();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.bd.getConnection();
		frame.atualiza();
	}
	
	
	public void atualiza(){
		try {
			limpaTable();
            statement = bd.con.prepareStatement("select * from Usuario");
            rs = statement.executeQuery();
            int qtdeColunas = rs.getMetaData().getColumnCount();
            
            
            DefaultTableModel dtm = new DefaultTableModel(new Object[] {
            		"Nome", "Data de Nascimento", "Email","Tipo","Login" }, 0) {
            		 public boolean isCellEditable(int rowIndex, int mColIndex){  
            		 return false;  
            		 }
            		 };
     
          try{
          	  while(rs.next()){
          		  Object[] objects = new Object[qtdeColunas];
          		  for(int i = 0; i < qtdeColunas; i++) {
          		    objects[i] = rs.getObject(i+1);
          		    }
          		  dtm.addRow(objects);
          		  }
          		  table.setModel(dtm);
                } catch (SQLException erro) {
                }
                scroll.setViewportView(table);
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Truta hine " + erro.toString());
        }
		
	}
	
	public void limpaTable(){
		dtm = (DefaultTableModel)table.getModel();
		dtm.setNumRows(0);
	}
	
	
	public void filtrarNomeNaTabela() {
        TableRowSorter sorter = new TableRowSorter(dtm);
        table.setRowSorter(sorter);
        String texto = textField.getText();
        if (texto.length() == 0) {
            sorter.setRowFilter(null);
            
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Valor Não Encontrado!!!", "AVISO - Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
	
}
