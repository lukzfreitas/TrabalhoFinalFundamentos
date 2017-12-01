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
import negocio.Usuario;
import negocio.UsuarioPF;
import negocio.UsuarioPJ;

/**
 *
 * @author Camila e Lucas
 */
public class UsuarioPFDAOJavaDb implements CadastroUsuarioPFDAO{

    private static UsuarioPFDAOJavaDb ref;

    public static UsuarioPFDAOJavaDb getInstance() throws DAOException{
        if (ref == null) {
            ref = new UsuarioPFDAOJavaDb();
        }
        return ref;
    }

    private UsuarioPFDAOJavaDb() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:DB_Leiloes");
    }

    public boolean adicionarPF(UsuarioPF usuarioPF) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO USUARIOS_PF (CPF, NOME, EMAIL) VALUES (?,?,?)"
            );
            stmt.setString(1, usuarioPF.getCpf());
            stmt.setString(2, usuarioPF.getNome());
            stmt.setString(3, usuarioPF.getEmail());
            int retorno = stmt.executeUpdate();
            con.close();
            return (retorno > 0);
        } catch (SQLException ex) {
            throw new DAOException("Falua ao adicionar Pessoa Fisica.", ex);
        }
    }    

    @Override
    public List<Usuario> getTodos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM USUARIOS_PF");
            List<Usuario> listaPF = new ArrayList<Usuario>();
            while(result.next()) {
                String cpf = result.getString("cpf");
                String nome = result.getString("nome");
                String email = result.getString("email");
                UsuarioPF pf = new UsuarioPF(cpf, nome, email);
                listaPF.add(pf);
            }
            return listaPF;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }
}
