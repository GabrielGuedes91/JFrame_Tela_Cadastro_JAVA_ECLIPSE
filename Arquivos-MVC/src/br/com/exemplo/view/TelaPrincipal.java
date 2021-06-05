package br.com.exemplo.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import br.com.exemplo.dao.LeitorDAO;
import br.com.exemplo.model.Leitor;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblmensagem;
	private JTextField txtcodeleitor;
	private JTextField txtnomeleitor;
	private JComboBox cmbtipoleitor;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private TextArea textArea;
	private Leitor leitor;
	private LeitorDAO dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Codigo Leitor");
		lblNewLabel.setBounds(10, 11, 77, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nome Leitor");
		lblNewLabel_1.setBounds(10, 36, 62, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Tipo Leitor");
		lblNewLabel_2.setBounds(10, 61, 62, 14);
		contentPane.add(lblNewLabel_2);
		
		lblmensagem = new JLabel("");
		lblmensagem.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, new Color(0, 0, 0), new Color(255, 200, 0), new Color(255, 200, 0)));
		lblmensagem.setBounds(10, 236, 414, 25);
		contentPane.add(lblmensagem);
		
		txtcodeleitor = new JTextField();
		txtcodeleitor.setBounds(91, 8, 333, 20);
		contentPane.add(txtcodeleitor);
		txtcodeleitor.setColumns(10);
		
		txtnomeleitor = new JTextField();
		txtnomeleitor.setBounds(91, 36, 333, 20);
		contentPane.add(txtnomeleitor);
		txtnomeleitor.setColumns(10);
		
		cmbtipoleitor = new JComboBox();
		cmbtipoleitor.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma Op\u00E7\u00E3o", "Aluno", "Professor", "Administrativo"}));
		cmbtipoleitor.setBounds(91, 58, 333, 20);
		contentPane.add(cmbtipoleitor);
		
		btnNewButton = new JButton("Novo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//===================
				txtcodeleitor.setText(null);
				txtnomeleitor.setText(null);
				textArea.setText(" ");
				cmbtipoleitor.setSelectedIndex(0);
				lblmensagem.setText(null);
				//===================
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton.setBounds(0, 86, 62, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//===================
				try {
					// criei o objeto leitor para pegar os dados da tela
					leitor = new Leitor();
					leitor.setCodLeitor (Integer.parseInt(txtcodeleitor.getText()));
					leitor.setNomeLeitor(txtnomeleitor.getText()); 
					leitor.setTipoLeitor((String) cmbtipoleitor.getSelectedItem());
					//abrir a conexão
					dao = new LeitorDAO();
					//salvar
					dao.Salvar(leitor);
					lblmensagem.setText("Salvo com Sucesso !!");
				}catch(Exception e) {
					lblmensagem.setText("Erro ao Salvar");
				}
				
				//===================
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_1.setBounds(67, 86, 68, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Consultar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//===================
				try {
					dao = new LeitorDAO();
					int codigo = Integer.parseInt(txtcodeleitor.getText());
					leitor = dao.consultar(codigo);
					txtnomeleitor.setText(leitor.getNomeLeitor());
					
					String tipo =leitor.getTipoLeitor();
					
					if(tipo.equals("Aluno")) {
						cmbtipoleitor.setSelectedIndex(1);
				    }
					else if(tipo.equals("Professor")) {
						cmbtipoleitor.setSelectedIndex(2);
				    }
					else {
						cmbtipoleitor.setSelectedIndex(3);
				    }
				}catch(Exception e1) {
					lblmensagem.setText("Erro ao Consultar");
				}
				//===================
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_2.setBounds(142, 86, 77, 23);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Alterar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					//===================
					try {
						// criei o objeto leitor para pegar os dados da tela
						leitor = new Leitor();
						leitor.setCodLeitor (Integer.parseInt(txtcodeleitor.getText()));
						leitor.setNomeLeitor(txtnomeleitor.getText()); 
						leitor.setTipoLeitor((String) cmbtipoleitor.getSelectedItem());
						//abrir a conexão
						dao = new LeitorDAO();
						// Alterar
						dao.alterar(leitor);
						lblmensagem.setText("Alterado com Sucesso !!");
					}catch(Exception e) {
						lblmensagem.setText("Erro ao Alterar");
					}
					
					//===================
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_3.setBounds(229, 86, 62, 23);
		contentPane.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Excluir");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//===================
				try {
					//abrir a conexão
					dao = new LeitorDAO();
					//Excluir
					int codigo = Integer.parseInt(txtcodeleitor.getText());
					dao.Excluir(codigo);
					lblmensagem.setText("Excluido com Sucesso !!");
				}catch(Exception e1) {
					lblmensagem.setText("Erro ao Excluir");
				}
				
				//===================
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_4.setBounds(301, 86, 63, 23);
		contentPane.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("Listar");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//===================
				try {
					List<Leitor> lista = new ArrayList<Leitor>();
					dao = new LeitorDAO();
					lista = dao.listarTodos();
					for(Leitor leitor : lista) {
						textArea.append("Código do Leitor.... "+leitor.getCodLeitor()+"\n");
						textArea.append("Nome do Leitor.... "+leitor.getNomeLeitor()+"\n");
						textArea.append("Tipo do Leitor.... "+leitor.getTipoLeitor()+"\n\n");
				}
				}catch(Exception e1) {
					lblmensagem.setText("Erro ao listar");
				}
				//===================
						
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_5.setBounds(367, 86, 57, 23);
		contentPane.add(btnNewButton_5);
		
		textArea = new TextArea();
		textArea.setBounds(10, 114, 414, 122);
		contentPane.add(textArea);
	}
}