package SQLquery;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

public class sql {

    private Connection con = null; // conector

    // strings com as informa��es para conectar ao banco de dados
    private String enderecoServidor = "localhost:3306"; // endere�o e porta do servidor
    private String dbNome = "urna_eletronica"; // nome da database
    private String nomeUser = "root"; // usu�rio
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
                //System.out.println("Conex�o OK"); // debug de conex�o
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
//            JOptionPane.showMessageDialog(null, "Erro ao executar inser��o no banco de dados.");
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
            //System.out.println("Conex�o encerrada"); // debug de encerramento de conex�o
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}