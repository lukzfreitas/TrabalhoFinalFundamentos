/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.Bem;
import negocio.CadastroBemDAO;

/**
 *
 * @author Lucas
 */
public class BemDAOJavaDb implements CadastroBemDAO{

    private static BemDAOJavaDb ref;

    public static BemDAOJavaDb getInstance() throws DAOException{
        if (ref == null) {
            ref = new BemDAOJavaDb();
        }
        return ref;
    }

    private BemDAOJavaDb() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            createDB();
        } catch (Exception ex) {
            System.out.println("Problemas para criar o banco: " + ex.getMessage());
            System.exit(0);
        }
    }

    private static void createDB() throws DAOException {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:DB_LEILOES;create=true");
            Statement sta = con.createStatement();
            String sql = "CREATE TABLE BENS ("
                    + "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"                    
                    + "DESCRICAO VARCHAR(50) NOT NULL,"
                    + "DETALHES CHAR(100) NOT NULL,"
                    + "CATEGORIA VARCHAR(50) NOT NULL"
                    + ")";
            sta.executeUpdate(sql);
            sta.close();
            con.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:DB_Leiloes");
    }

    public boolean adicionar(Bem bem) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO BENS ("                    
                    + "DESCRICAO, "
                    + "DETALHES,"
                    + "CATEGORIA)"
                    + " VALUES (?,?,?)"
            );            
            stmt.setString(1, bem.getDescricao());
            stmt.setString(2, bem.getDetalhes());
            stmt.setString(3, bem.getCategoria());
            int ret = stmt.executeUpdate();
            con.close();
            return (ret > 0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
    }

    @Override
    public List<Bem> getTodos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM BENS");
            List<Bem> listaBens = new ArrayList<Bem>();
            while(result.next()) {
                String descricao = result.getString("DESCRICAO");
                String detalhes = result.getString("DETALHES");
                String categoria = result.getString("CATEGORIA");
                Bem bem = new Bem(descricao, detalhes, categoria);
                listaBens.add(bem);
            }
            return listaBens;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public Bem getBem(int bemId) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
