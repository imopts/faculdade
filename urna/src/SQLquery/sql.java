package SQLquery;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

public class sql {

    private Connection con = null; // conector

    // strings com as informações para conectar ao banco de dados
    private String enderecoServidor = "localhost:3306"; // endereço e porta do servidor
    private String dbNome = "urna_eletronica"; // nome da database
    private String nomeUser = "root"; // usuário
    private String dbSn = ""; // senha

    public sql() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+enderecoServidor+"/"+dbNome, nomeUser, dbSn);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                //System.out.println("Conexão OK"); // debug de conexão
                this.con = connection;
            }
        }
    }

    public ResultSet ExecutaSelect(String stmt) {
        try {
            Statement st = this.con.createStatement();
            ResultSet res = st.executeQuery(stmt);
            return res;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean ExecutarInsercao(String stmt) {
        try {
            Statement st = this.con.createStatement();
            st.executeUpdate(stmt);
            return true;
//            JOptionPane.showMessageDialog(null, "Salvo!");
        }
        catch (SQLException e) {
            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Erro ao executar inserção no banco de dados.");
            return false;
        }
    }

    public void ExecutarAlteracao(String stmt) {
        try {
            Statement st = this.con.createStatement();
            st.executeUpdate(stmt);
//            JOptionPane.showMessageDialog(null, "Comando executado com sucesso!");

        }
        catch (SQLException e) {
            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Erro ao executar comando no banco de dados.");
        }
    }

    public void encerrarConexao(){
        try {
            this.con.close();
            //System.out.println("Conexão encerrada"); // debug de encerramento de conexão
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}