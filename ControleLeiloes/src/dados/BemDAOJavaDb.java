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
 * @author Camila e Lucas
 */
public class BemDAOJavaDb implements CadastroBemDAO {

    private static BemDAOJavaDb ref;

    public static BemDAOJavaDb getInstance() throws DAOException {
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
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:DB_Leiloes");
    }

    @Override
    public boolean adicionar(Bem bem) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO BENS ("
                    + "LOTE_ID_foreign_key,"
                    + "DESCRICAO, "
                    + "DETALHES,"
                    + "CATEGORIA)"
                    + " VALUES (?,?,?,?)"
            );
            stmt.setInt(1, bem.getLoteId());
            stmt.setString(2, bem.getDescricao());
            stmt.setString(3, bem.getDetalhes());
            stmt.setString(4, bem.getCategoria());
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
            while (result.next()) {
                int bemId = Integer.parseInt(result.getString("BEM_ID"));
                int loteId = Integer.parseInt(result.getString("LOTE_ID_foreign_key"));
                String descricao = result.getString("DESCRICAO");
                String detalhes = result.getString("DETALHES");
                String categoria = result.getString("CATEGORIA");
                Bem bem = new Bem(bemId, loteId, descricao, detalhes, categoria);
                listaBens.add(bem);
            }
            return listaBens;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public Bem getBem(int bemId) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM BENS WHERE BEM_ID=?");
            stmt.setString(1, Integer.toString(bemId));
            ResultSet resultado = stmt.executeQuery();
            Bem bem = null;
            if (resultado.next()) {
                int bem_Id = Integer.parseInt(resultado.getString("BEM_ID"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String descricao = resultado.getString("DESCRICAO");
                String detalhes = resultado.getString("DETALHES");
                String categoria = resultado.getString("CATEGORIA");
                bem = new Bem(bem_Id, loteId, descricao, detalhes, categoria);
            }
            return bem;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public List<Bem> getBensPorLoteId(int loteId) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM BENS WHERE LOTE_ID_foreign_key=?");
            stmt.setInt(1, loteId);
            ResultSet resultado = stmt.executeQuery();
            List<Bem> listaBens = new ArrayList<Bem>();
            while (resultado.next()) {
                int bem_Id = Integer.parseInt(resultado.getString("BEM_ID"));                
                String descricao = resultado.getString("DESCRICAO");
                String detalhes = resultado.getString("DETALHES");
                String categoria = resultado.getString("CATEGORIA");
                Bem bem = new Bem(bem_Id, loteId, descricao, detalhes, categoria);
                listaBens.add(bem);
            }
            return listaBens;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }
}
