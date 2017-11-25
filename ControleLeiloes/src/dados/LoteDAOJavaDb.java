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
import negocio.CadastroLoteDAO;
import negocio.Lote;

/**
 *
 * @author Lucas
 */
public class LoteDAOJavaDb implements CadastroLoteDAO {
    
    private static LoteDAOJavaDb ref;

    public static LoteDAOJavaDb getInstance() throws DAOException {
        if (ref == null) {
            ref = new LoteDAOJavaDb();
        }
        return ref;
    }

    private LoteDAOJavaDb() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        try {
//            createDB();
//        } catch (Exception ex) {
//            System.out.println("Problemas para criar o banco: " + ex.getMessage());
//            System.exit(0);
//        }
    }

    private static void createDB() throws DAOException {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:DB_LEILOES;create=true");
            Statement sta = con.createStatement();
            String sql = "CREATE TABLE LOTES ("
                    + "LOTE_ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "BEM_ID_foreign_key INT NOT NULL,"
                    + "VALOR CHAR(100) NOT NULL"                    
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

    public boolean adicionar(Lote lote) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO LOTES ("
                    + "BEM_ID, "
                    + "VALOR)"                    
                    + " VALUES (?,?)"
            );
            stmt.setInt(1, lote.getBemId());
            stmt.setDouble(2, lote.getValor());            
            int ret = stmt.executeUpdate();
            con.close();
            return (ret > 0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
    }

    @Override
    public List<Lote> getTodos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM LOTES");
            List<Lote> listaLotes = new ArrayList<Lote>();
            while (result.next()) {
                int loteId = result.getInt("LOTE_ID");
                int bemId = result.getInt("BEM_ID");                
                double valor = result.getDouble("VALOR");
                Lote lote = new Lote(loteId, bemId, valor);
                listaLotes.add(lote);
            }
            return listaLotes;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }
    
    @Override
    public Lote getLote(int loteId) throws DAOException {
            try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM LOTES WHERE LOTE_ID=?");
            stmt.setString(1, Integer.toString(loteId));
            ResultSet resultado = stmt.executeQuery();
            Lote lote = null;
            if (resultado.next()) {
                int lote_Id = Integer.parseInt(resultado.getString("LOTE_ID"));
                int bem_Id = Integer.parseInt(resultado.getString("BEM_ID"));
                double valor = Double.parseDouble(resultado.getString("VALOR"));
                lote = new Lote(lote_Id, bem_Id, valor);                
            }
            return lote;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }
}
