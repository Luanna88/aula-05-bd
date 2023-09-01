package loja.jdbc;

import java.awt.Container;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class CriarTabela extends JFrame {
    private String driver = "org.postgresql.Driver";
    private String sFonte = "jdbc:postgresql://localhost:5432/loja";
    private String sUsuario = "postgres";
    private String sSenha = "flor@2024";

    public CriarTabela() {
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(sFonte, sUsuario, sSenha);
            JOptionPane.showMessageDialog(this, "Banco conectado com sucesso!", "Mensagem", JOptionPane.WARNING_MESSAGE);

            Statement st = con.createStatement();

            String createTableQuery = "CREATE TABLE tabelaProdutos (codProduto int PRIMARY KEY, produto VARCHAR(50));";
            st.executeUpdate(createTableQuery);
            JOptionPane.showMessageDialog(this, "Tabela criada com sucesso!", "Mensagem", JOptionPane.WARNING_MESSAGE);

            st.close();
            con.close();

            Container P = getContentPane();
            P.setLayout(new FlowLayout());
            JLabel mensagem = new JLabel("Você criou uma tabela usando CREATE TABLE!");
            P.add(mensagem);
        } catch (SQLException eSQL) {
            eSQL.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro na conexão com o banco!\nMensagem: " + eSQL.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(2);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Falha na conexão com o banco!\nMensagem: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            CriarTabela ex = new CriarTabela();
            ex.setDefaultCloseOperation(EXIT_ON_CLOSE);
            ex.setTitle("USANDO CREATE TABLE");
            ex.setSize(400, 300);
            ex.setVisible(true);
        });
    }
}

		