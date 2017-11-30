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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import negocio.CadastroLanceDAO;
import negocio.Lance;

/**
 *
 * @author Lucas
 */
public class LanceDAOJavaDb implements CadastroLanceDAO {

    private static LanceDAOJavaDb ref;

    public static LanceDAOJavaDb getInstance() throws DAOException {
        if (ref == null) {
            ref = new LanceDAOJavaDb();
        }
        return ref;
    }

    private LanceDAOJavaDb() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:DB_LEILOES");
    }

    @Override
    public boolean adicionar(Lance lance) throws DAOException {
        try {
            Date agora = new Date(System.currentTimeMillis());
            SimpleDateFormat formatarData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dataFormatada = formatarData.format(agora);
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO LANCES (LEILAO_ID_foreign_key, USUARIO_ID, VALOR, DATA) VALUES (?,?,?,?)"
            );
            stmt.setString(1, Integer.toString(lance.getLeilaoId()));
            stmt.setString(2, lance.getUsuarioId());
            stmt.setString(3, Double.toString(lance.getValor()));
            stmt.setString(4, dataFormatada);
            int ret = stmt.executeUpdate();
            con.close();
            return (ret > 0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
    }

    @Override
    public List<Lance> getLancesPorLeilaoID(int leilaoId) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM LANCES WHERE LEILAO_ID_foreign_key=?");
            stmt.setString(1, Integer.toString(leilaoId));
            ResultSet resultado = stmt.executeQuery();
            List<Lance> listaLancesDoLeilao = new ArrayList<Lance>();
            while (resultado.next()) {
                int lance_id = Integer.parseInt(resultado.getString("LANCE_ID"));
                String usuario_id = resultado.getString("USUARIO_ID");
                double lance_valor = Double.parseDouble(resultado.getString("VALOR"));
                String lance_data = resultado.getString("DATA");
                Lance lance = new Lance(lance_id, leilaoId, usuario_id, lance_data, lance_valor);
                listaLancesDoLeilao.add(lance);
            }
            return listaLancesDoLeilao;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public List<Lance> getLancesPorUsuarioID(String usuarioId) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM LANCES WHERE USUARIO_ID=?");
            stmt.setString(1, usuarioId);
            ResultSet resultado = stmt.executeQuery();
            List<Lance> listaLancesDoLeilao = new ArrayList<Lance>();
            while (resultado.next()) {
                int lance_id = Integer.parseInt(resultado.getString("LANCE_ID"));
                int leilao_id = Integer.parseInt(resultado.getString("LEILAO_ID_foreign_key"));
                double lance_valor = Double.parseDouble(resultado.getString("VALOR"));
                String lance_data = resultado.getString("DATA");
                Lance lance = new Lance(lance_id, leilao_id, usuarioId, lance_data, lance_valor);
                listaLancesDoLeilao.add(lance);
            }
            return listaLancesDoLeilao;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public List<Lance> getTodos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LANCES");
            List<Lance> listaLances = new ArrayList<Lance>();
            while (resultado.next()) {
                int lance_id = Integer.parseInt(resultado.getString("lance_id"));
                int leilao_id = Integer.parseInt(resultado.getString("LEILAO_ID_foreign_key"));
                String usuario_id = resultado.getString("USUARIO_ID");
                double lance_valor = Double.parseDouble(resultado.getString("VALOR"));
                String lance_data = resultado.getString("DATA");
                Lance lance = new Lance(lance_id, leilao_id, usuario_id, lance_data, lance_valor);
                listaLances.add(lance);
            }
            return listaLances;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }
}
