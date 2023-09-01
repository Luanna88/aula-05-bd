package loja.jdbc;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CriarDataBase extends JFrame {
	
	private Connection con;
	private Statement st;

	public CriarDataBase(){
		String driver = "org.postgresql.Driver";
		String sUsuario = "postgres";
		String sSenha = "flor@2024";
		String sFonte = "jdbc:postgresql://localhost:5432/";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(sFonte, sUsuario, sSenha);
			JOptionPane.showMessageDialog(this,"Banco conectado com sucesso!","Mensagem", JOptionPane.WARNING_MESSAGE);

		}catch (SQLException eSQL) {
			// exceções de SQL
			eSQL.printStackTrace();
			JOptionPane.showMessageDialog(this,"Falha na conexão com o banco!\n" +"Mensagem: " + eSQL.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}catch (Exception e) {
			// demais exceções
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Falha na conexão com o banco!\n" +"Mensagem: " + e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		try {
		    // Criando o banco de dados
		    String createDBQuery = "CREATE DATABASE Loja";
		    st = con.createStatement();
		    st.executeUpdate(createDBQuery);
		    
		    JOptionPane.showMessageDialog(this, "DataBase criada com sucesso!", "Mensagem", JOptionPane.WARNING_MESSAGE);
		} catch (SQLException eSQL) {
		    eSQL.printStackTrace();
		    JOptionPane.showMessageDialog(this, "Não foi possível criar a DataBase!\n" + "Mensagem: " + eSQL.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
		JLabel mensagem = new JLabel("Você criou um banco de dados usando CREATE DATABASE!");
		P.add(mensagem);
	}

	
	public static void main(String args[]){
		CriarDataBase ex = new CriarDataBase();
		ex.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ex.setTitle("USANDO CREATE DATABASE");
		ex.setVisible(true);
		ex.setSize(400,300);
	}
}
