package aula.jdbc;

import javax.swing.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.sql.*;

public class ApagarDados extends JFrame{
	
	private Connection con;
	private Statement st;

	public ApagarDados() throws SQLException{
		String driver = "org.postgresql.Driver";
		String sUsuario = "postgres";
		String sSenha = "flor@2024";
		String sFonte = "jdbc:postgresql://localhost:5432/postgres";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(sFonte, sUsuario, sSenha);
			JOptionPane.showMessageDialog(this,"Banco conectado com sucesso!","Mensagem", JOptionPane.WARNING_MESSAGE);
		}catch (SQLException eSQL) {
			// exceções de SQL
			eSQL.printStackTrace();
			JOptionPane.showMessageDialog(this,"Falha na conexão com o banco!\n" + "Mensagem: " + eSQL.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}catch (Exception e) {
			// demais exceções
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Falha na conexão com o banco!\n" + "Mensagem: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		try{
			st = con.createStatement();
			String ExecutaDelete= "DELETE FROM tabela1 WHERE codigo=1";
			st.execute (ExecutaDelete);
		}catch(SQLException eSQL){
			JOptionPane.showMessageDialog(this,"Erro na expressão do DELETE!\n" + "Mensagem: " + eSQL.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(2);
		}
		
		try {
			st.close();
			con.close();
		}catch(Exception exception){
			exception.printStackTrace();
			System.exit(3);
		}
		Container P = getContentPane();
		P.setLayout(new FlowLayout());
		JLabel mensagem = new JLabel("Você acabou de testar um exemplo usando DELETE!");
		P.add(mensagem);
	}

	public static void main(String args[]) throws SQLException {
		ApagarDados ex = new ApagarDados ();
		ex.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ex.setVisible(true);
		ex.setTitle("USANDO DELETE");
		ex.setSize(400,200);
	}
}
