package Telas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PesquisarEstoque extends JFrame {

	private JPanel contentPane,panel;
	private JTextField txtPesquisar;
	private JTable table;
	private JScrollPane scroll;
	private DefaultTableModel dtm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisarEstoque frame = new PesquisarEstoque();
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
	public PesquisarEstoque() {
		setTitle("Pesquisa de Estoque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		txtPesquisar.setBounds(42, 51, 319, 23);
		contentPane.add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pesquisar");
		lblNewLabel_1.setBounds(42, 38, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				
			}
		});
		btnVoltar.setBounds(42, 463, 89, 23);
		contentPane.add(btnVoltar);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Estoque", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 138, 414, 328);
		contentPane.add(panel);
		
		DefaultTableModel dtm = new DefaultTableModel(
				new String[]{"Código","Nome","QTDE","Tipo","Especifi"},0);
		panel.setLayout(null);
		
		table = new JTable(dtm);
		
		//rolagem
		
				scroll = new JScrollPane();
				scroll.setBounds(10, 21, 394, 296);
				scroll.setViewportView(table);	
				panel.add(scroll);
				contentPane.add(panel);
	}
	public static void pesquisa(){
		
		PesquisarEstoque frame = new PesquisarEstoque();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void filtrarNomeNaTabela() {
        TableRowSorter sorter = new TableRowSorter(dtm);
        table.setRowSorter(sorter);
        String texto = txtPesquisar.getText();
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
