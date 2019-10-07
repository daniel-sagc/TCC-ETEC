package Telas;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JRadioButton;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class CadastrarDispositivo extends JFrame {

	private JPanel contentPane;
	DecimalFormat df = new DecimalFormat("#,###.00");
	private JTable table;
	private Nimbus nb;
	private JScrollPane scroll;
	public DispositivoDAO dispo;
	private BD bd;
	private DefaultTableModel dtm;
	private JRadioButton rdPC,rdbtnImpressora,rdbtnMONI,rdbtnACES,rdbtnNOTE;
	private PreparedStatement statement;
	private ResultSet rs;
	private AbstractTableModel ModelTabel;
	private String tipo = "";
	private VerDispo ver;
	private JTextField txtFiltro;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				CadastrarDispositivo frame1 = new CadastrarDispositivo();
				frame1.setVisible(true);
				frame1.bd.getConnection();
				frame1.atualiza();
			}	
		});
	}

	/**
	 * Create the frame.
	 */

	public CadastrarDispositivo() {
		
		setTitle("Dispositivos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		dispo = new DispositivoDAO();
		bd = new BD();
		ButtonGroup group = new ButtonGroup();
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));
	
		// ComboBox

	

		String[] cLocalItems = { "LAB I", "LAB II", "LAB III", "GESTÃO",
				"LÓGICA", "DIRETORIA" };
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal.abrir();
				dispose();
				
			}
		});
		btnVoltar.setBounds(347, 482, 82, 23);
		contentPane.add(btnVoltar);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 439, 458);
		contentPane.add(tabbedPane);
		VerDispo ver;
		
				JPanel aba1 = new JPanel();
				tabbedPane.addTab("Cadastro", null, aba1, null);
				aba1.setLayout(null);
				
						JButton btnADD = new JButton("Cadastrar");
						btnADD.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								CadastroDispo.dispositivo();
								dispose();
							}
						});
						
						
						

						btnADD.setBounds(10, 68, 108, 25);
						aba1.add(btnADD);
								
								
										table = new JTable(new DefaultTableModel(
											new Object[][] {
											},
											new String[] {
												"NºPatrimonio", "Categoria", "Modelo", "Local","Especi"
											}
										));
										table.setShowGrid(false);
										table.setShowHorizontalLines(false);
										table.setShowVerticalLines(false);
										
			
										table.getColumnModel().getColumn(0).setResizable(false);
										table.getColumnModel().getColumn(1).setResizable(false);
										table.getColumnModel().getColumn(2).setResizable(false);
										table.getColumnModel().getColumn(3).setResizable(false);
										table.getTableHeader().setReorderingAllowed(false);
										table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
												TitledBorder.TOP, null, null));
										table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
										table.setToolTipText("");
										
												// rolagem
										
								scroll = new JScrollPane();
								scroll.setBounds(10, 104, 436, 334);
								aba1.add(scroll);
								scroll.setViewportView(table);
								
								txtFiltro = new JTextField();
								txtFiltro.addKeyListener(new KeyAdapter() {
												@Override
													public void keyReleased(KeyEvent arg0) {
													filtrarNomeNaTabela();
													}
												});
								txtFiltro.setBounds(42, 37, 341, 25);
		     					aba1.add(txtFiltro);
		 						txtFiltro.setColumns(10);
												
												JLabel lblNewLabel = new JLabel("Pesquisar:");
												lblNewLabel.setBounds(42, 12, 65, 14);
												aba1.add(lblNewLabel);
									table.addMouseListener(new MouseAdapter() {
													@Override
									public void mouseClicked(MouseEvent e) {
												
														
													
									VerDispo ver = new VerDispo();
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
														String dadoT =  String.valueOf(dado4.toString());
														String dadoE =  String.valueOf(dado5.toString());


														
														ver.recebendo(dadoM,dadoN,dadoE,dadoP,dadoT);
													
														
													}
												});
	
		JPanel aba2 = new JPanel();
		tabbedPane.addTab("Impressora", null, aba2, null);
		aba2.setLayout(null);

	}

	public static void dispositivo() {
		CadastrarDispositivo frame = new CadastrarDispositivo();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.bd.getConnection();
		frame.atualiza();
		
	}
	public void atualiza(){
		try {
			limpaTable();
            statement = bd.con.prepareStatement("select * from Dispositivos");
            rs = statement.executeQuery();
            int qtdeColunas = rs.getMetaData().getColumnCount();
     
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
	
	
	public class AplicaNimbusLookAndFeel {
		 
		 private AplicaNimbusLookAndFeel() {
			 
			 
		 
		 }
		 public  void pegaNimbus() {
		 try {
		 for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		 if ("Nimbus".equals(info.getName())) {
		 UIManager.setLookAndFeel(info.getClassName());
		 break;
		 }
		 }
		 } catch (UnsupportedLookAndFeelException e) {
		 
		 System.out.println("Erro: " + e.getMessage());
		 e.printStackTrace();
		 
		 } catch (ClassNotFoundException e) {
		 
		 System.out.println("Erro: " + e.getMessage());
		 e.printStackTrace();
		 
		 } catch (InstantiationException e) {
		 
		 System.out.println("Erro: " + e.getMessage());
		 e.printStackTrace();
		 
		 } catch (IllegalAccessException e) {
		 
		 System.out.println("Erro: " + e.getMessage());
		 e.printStackTrace();
		 }
		 
		 }
	}
	public void filtrarNomeNaTabela() {
        TableRowSorter sorter = new TableRowSorter(dtm);
        table.setRowSorter(sorter);
        String texto = txtFiltro.getText();
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
             

