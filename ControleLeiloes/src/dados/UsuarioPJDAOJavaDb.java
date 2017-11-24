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
import negocio.CadastroUsuarioPFDAO;
import negocio.CadastroUsuarioPJDAO;
import negocio.Usuario;
import negocio.UsuarioPF;
import negocio.UsuarioPJ;

/**
 *
 * @author Lucas
 */
public class UsuarioPJDAOJavaDb implements CadastroUsuarioPJDAO {

    private static UsuarioPJDAOJavaDb ref;

    public static UsuarioPJDAOJavaDb getInstance() throws DAOException {
        if (ref == null) {
            ref = new UsuarioPJDAOJavaDb();
        }
        return ref;
    }

    private UsuarioPJDAOJavaDb() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        try {
//            createDB();
//        } catch (Exception ex) {
//            System.out.println("Problemas para criar o banco: "+ex.getMessage());
//            System.exit(0);
//        }
    }

    private static void createDB() throws DAOException {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:DB_LEILOES;create=true");
            Statement sta = con.createStatement();
            String sql = "CREATE TABLE USUARIO_PJ ("
                    + "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "CNPJ CHAR(16) NOT NULL," + "NOME VARCHAR(100) NOT NULL," + "EMAIL VARCHAR(100) NOT NULL" + ")";
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

    @Override
    public boolean adicionarPJ(UsuarioPJ usuarioPJ) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO USUARIO_PJ (cnpj, nome, email) VALUES (?,?,?)"
            );
            stmt.setString(1, usuarioPJ.getCnpj());
            stmt.setString(2, usuarioPJ.getNome());
            stmt.setString(3, usuarioPJ.getEmail());
            int retorno = stmt.executeUpdate();
            con.close();
            return (retorno > 0);
        } catch (SQLException ex) {
            throw new DAOException("Falua ao adicionar Pessoa Jurídica.", ex);
        }
    }

    @Override
    public List<Usuario> getTodos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM USUARIO_PJ");
            List<Usuario> listaPJ = new ArrayList<Usuario>();
            while (result.next()) {
                String cnpj = result.getString("cnpj");
                String nome = result.getString("nome");
                String email = result.getString("email");
                UsuarioPJ pj = new UsuarioPJ(cnpj, nome, email);
                listaPJ.add(pj);
            }
            return listaPJ;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }
}
