package classes_de_conexao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;

public class Tela_cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfUsuario;
	private JTextField tfSenha;
	private JTextField tfBusca;
	private JTable tbDados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastro frame = new Tela_cadastro();
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
	public Tela_cadastro() {
		setResizable(false);
		setTitle("Faz Tudo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsurio.setBounds(10, 89, 73, 38);
		contentPane.add(lblUsurio);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(10, 22, 73, 38);
		contentPane.add(lblId);
		
		JLabel lblUsurio_1 = new JLabel("Senha");
		lblUsurio_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsurio_1.setBounds(10, 149, 73, 38);
		contentPane.add(lblUsurio_1);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setBounds(81, 34, 65, 19);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		tfUsuario = new JTextField();
		tfUsuario.setColumns(10);
		tfUsuario.setBounds(81, 101, 146, 19);
		contentPane.add(tfUsuario);
		
		tfSenha = new JTextField();
		tfSenha.setColumns(10);
		tfSenha.setBounds(81, 161, 146, 19);
		contentPane.add(tfSenha);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(UIManager.getColor("Button.highlight"));
		panel.setBounds(10, 451, 730, 66);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(10, 23, 99, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (tfUsuario.getText().equals("")|| tfSenha.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "Usuario ou senha em branco");
				} else {
				
				
				try {
					Connection con = conexao.faz_conexao();
					String sql = "insert into dados_senhas (usuario, senha) value (?,?) ";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, tfSenha.getText ());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
					
					
					tfUsuario.setText ("");
					tfSenha.setText("");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			}
		});
		panel.setLayout(null);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Abrir Dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(0, 204, 0));
		panel_1.setBounds(10, 519, 730, 56);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		tfBusca = new JTextField();
		tfBusca.setBounds(159, 17, 219, 29);
		panel_1.add(tfBusca);
		tfBusca.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Abrir");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if (tfBusca.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Informe o ID ");
					
				}
				
				else {
				try {
					Connection con = conexao.faz_conexao();
					
					String sql ="select *from dados_senhas where id = ?";
					
					PreparedStatement stmt= con.prepareStatement(sql);
					
					stmt.setString(1, tfBusca.getText());
					
					ResultSet rs= stmt.executeQuery();
					
					while (rs.next()) {
						
						tfID.setText(rs.getString("id"));
						tfUsuario.setText(rs.getString("usuario"));
						tfSenha.setText(rs.getString("senha"));
						
						
						
					}
					
					rs.close();
					con.close();
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
			}
		});
		btnNewButton_1.setBounds(10, 17, 132, 29);
		panel_1.add(btnNewButton_1);
		
		JButton btmlistar = new JButton("Listar Dados");
		btmlistar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = conexao.faz_conexao();
					
					Stirng sql= "Select *from dados_senhas";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs= stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaulTablemodel) tbDados.getModel();
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object [](rs.getString(ABORT));
						
					}
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btmlistar.setBounds(510, 25, 194, 21);
		panel_1.add(btmlistar);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(10, 404, 730, 48);
		contentPane.add(scrollBar);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollBar_1.setBounds(10, 356, 730, 93);
		contentPane.add(scrollBar_1);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"ID", "Usu\u00E1rio", "Senha"
			}
		));
		tbDados.setBounds(10, 281, 730, 167);
		contentPane.add(tbDados);
	}
}
