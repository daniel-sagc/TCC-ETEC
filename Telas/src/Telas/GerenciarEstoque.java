package Telas;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.JRadioButton;


public class GerenciarEstoque extends JFrame {

	private JPanel contentPane,pnTable;
	private BD bd;
	DecimalFormat df =  new DecimalFormat("#,###.00");
	private JPanel panel;
	private JRadioButton rdTinta,rdPeça,rdAces;
	private JTable table;
	private DefaultTableModel dtm;
	private PreparedStatement statement;
	private ResultSet rs;
	private JScrollPane scroll;
	private gEstoqueDAO  gEstoque;
	String tipo = "";
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarEstoque frame = new GerenciarEstoque();
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
	public GerenciarEstoque() {
		setTitle("Estoque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		bd = new BD();
		gEstoque = new gEstoqueDAO();
		ButtonGroup group = new ButtonGroup();
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		
		JButton btnADD = new JButton("Cadastrar");
		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					CadastroEstoque.dispositivo();
					dispose();
				
				
			}
		});
		btnADD.setBounds(22, 72, 89, 25);
		contentPane.add(btnADD);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.abrir();
				dispose();
			}
		});
		btnVoltar.setBounds(308, 477, 89, 25);
		contentPane.add(btnVoltar);
		
		panel = new JPanel();
		panel.setBounds(10, 108, 424, 358);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable(new DefaultTableModel(		
				new Object[][] {},
					new String[] {
						"Codigo", "Categoria", "QTDE","TIPO","ESPECI"
											}
				));		
		
		
					
							
		//rolagem
		
				scroll = new JScrollPane();
				scroll.setBounds(10, 0, 414, 347);
				scroll.setViewportView(table);	
				panel.add(scroll);
				
				contentPane.add(panel);
		
	
		DefaultTableCellRenderer alinhaDireita = new DefaultTableCellRenderer();
		alinhaDireita.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setCellRenderer(alinhaDireita);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setCellRenderer(alinhaDireita);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setCellRenderer(alinhaDireita);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setCellRenderer(alinhaDireita);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		table.setToolTipText("");
		
		textField = new JTextField();
		textField.setBounds(58, 41, 298, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Pesquisar:");
		lblNewLabel.setBounds(44, 22, 67, 14);
		contentPane.add(lblNewLabel);
		
		table = new JTable();
		
		
		
		
								table.addMouseListener(new MouseAdapter() {
									@Override
						public void mouseClicked(MouseEvent e) {
								
										
									
						VerEstoque ver = new VerEstoque();
						ver.setVisible(true);
						dispose();
						int linha_seleciona = table.getSelectedRow();
										
						Object dado = (table.getValueAt(linha_seleciona, 0));
						Object dado2 = (table.getValueAt(linha_seleciona, 1));
						Object dado3 = (table.getValueAt(linha_seleciona, 2));
						Object dado4= (table.getValueAt(linha_seleciona, 3));
						Object dado5 = (table.getValueAt(linha_seleciona, 4));
						
						
						String dadoP =  String.valueOf(dado.toString());
						String dadoN =  String.valueOf(dado2.toString());
						String dadoM =  String.valueOf(dado3.toString());
						String dadoE =  String.valueOf(dado4.toString());
						String dadoL = String.valueOf(dado5.toString());
						
						
										ver.recebendo(dadoP,dadoN,dadoM,dadoE,dadoL);
									
										
									}
								});
		
	}
	
	
	public void atualiza(){
		try {
			
            statement = bd.con.prepareStatement("select * from Material");
            rs = statement.executeQuery();
            int qtdeColunas = rs.getMetaData().getColumnCount();
            
            DefaultTableModel dtm = new DefaultTableModel(new Object[] {
            		"Codigo", "Categoria", "QTDE","TIPO","ESPECI" }, 0) {
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
	
		
		public static void estoque(){
		GerenciarEstoque frame = new GerenciarEstoque();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.bd.getConnection();
		frame.atualiza();
	}    	
}