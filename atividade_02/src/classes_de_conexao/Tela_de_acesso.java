package classes_de_conexao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_de_acesso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField jp1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_de_acesso frame = new Tela_de_acesso();
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
	public Tela_de_acesso() {
		setResizable(false);
		setTitle("Sistema de gerenciamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(54, 36, 170, 59);
		contentPane.add(lblNewLabel);
		
		JLabel pfSenha = new JLabel("Senha");
		pfSenha.setForeground(new Color(0, 0, 0));
		pfSenha.setFont(new Font("Arial", Font.BOLD, 16));
		pfSenha.setBounds(54, 93, 170, 59);
		contentPane.add(pfSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfUsuario.setBounds(132, 50, 123, 27);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		jp1 = new JPasswordField();
		jp1.setBounds(132, 102, 123, 27);
		contentPane.add(jp1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setForeground(new Color(0, 255, 0));
		btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
			try {
				Connection con = conexao.faz_conexao();
				
				String sql = "select *from dados_senhas where usuario=? and senha=?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1,tfUsuario.getText());
				stmt.setString(2, new String(jp1.getPassword()));
				
				
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next()) {
					
				
				//JOptionPane.showMessageDialog(null,"Esse usuário existe" );	
				
				Tela_cadastro exibir = new Tela_cadastro ();
				exibir.setVisible(true);
				
				setVisible (false);
				
				} else {
				
				JOptionPane.showMessageDialog(null, "Esse usuário não existe");
			}
			
				stmt.close();
			con.close();
			
					
			 } catch (SQLException e1) {
                 // TODO Auto-generated catch block
                 e1.printStackTrace();
             }   
         }
     });
     btnNewButton.setBounds(132, 152, 123, 27);
     contentPane.add(btnNewButton);
 }
}