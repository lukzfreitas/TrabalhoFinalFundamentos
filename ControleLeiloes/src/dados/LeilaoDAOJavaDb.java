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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import negocio.CadastroLeilaoDAO;
import negocio.Lance;
import negocio.Leilao;

/**
 *
 * @author Lucas
 */
public class LeilaoDAOJavaDb implements CadastroLeilaoDAO {

    private static LeilaoDAOJavaDb ref;
    Date agora = new Date(System.currentTimeMillis());
    Date d_temp;
    SimpleDateFormat formatarData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static LeilaoDAOJavaDb getInstance() throws DAOException {
        if (ref == null) {
            ref = new LeilaoDAOJavaDb();
        }
        return ref;
    }

    private LeilaoDAOJavaDb() {
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
    public boolean adicionar(Leilao leilao) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO LEILOES ("
                    + "LOTE_ID_foreign_key, "
                    + "DATA_INICIO, "
                    + "DATA_FIM, "
                    + "ARREMATE, "
                    + "VENCEDOR,"
                    + "TIPO, "
                    + "TIPO_LANCE"
                    + ") VALUES (?,?,?,?,?,?,?)" //                             1        2         3            4          5             6
            );
            stmt.setString(1, Integer.toString(leilao.getLoteId()));
            stmt.setString(2, leilao.getDataIni());
            stmt.setString(3, leilao.getDataFim());
            stmt.setString(4, Double.toString(leilao.getArremate()));
            stmt.setString(5, leilao.getVencedor());
            stmt.setString(6, leilao.getTipo());
            stmt.setString(7, leilao.getTipoLance());

            int ret = stmt.executeUpdate();
            con.close();
            return (ret > 0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
    }

    @Override
    public List<Leilao> getTodos() throws DAOException, ParseException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LEILOES");
            List<Leilao> listaLeiloes = new ArrayList<Leilao>();
            while (resultado.next()) {
                int leilaoId = Integer.parseInt(resultado.getString("LEILAO_ID"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("DATA_INICIO");
                String dataFim = resultado.getString("DATA_FIM");
                double arremate = Double.parseDouble(resultado.getString("ARREMATE"));
                String vencedor = resultado.getString("VENCEDOR");
                String tipo_leilao = resultado.getString("TIPO");
                String tipo_lance = resultado.getString("TIPO_LANCE");

                Leilao leilao = new Leilao(leilaoId, loteId, arremate, vencedor, tipo_lance, tipo_lance, dataIni, dataFim);

                String data_hora_lance = dataFim;
                d_temp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data_hora_lance);
                if (agora.before(d_temp)) {
                    leilao.ativa();
                } else {
                    leilao.desativa();
                }
                listaLeiloes.add(leilao);
            }
            return listaLeiloes;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public boolean darLance(Lance lance) throws DAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE LEILAO SET ARREMATE=? WHERE LEILAO_ID=?"
            );
            stmt.setString(1, Double.toString(lance.getValor()));
            stmt.setString(2, Integer.toString(lance.getLeilaoId()));

            int retorno = stmt.executeUpdate();
            con.close();
            return (retorno > 0);
        } catch (SQLException ex) {
            throw new DAOException("Falha ao dar lance.", ex);
        }
    }

    @Override
    public List<Leilao> getAtivos() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LEILOES WHERE DATA_FIM > CURRENT TIMESTAMP");
            List<Leilao> listaLeiloesAtivos = new ArrayList<Leilao>();
            while (resultado.next()) {
                int leilaoId = Integer.parseInt(resultado.getString("LEILAO_ID"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("DATA_INICIO");
                String dataFim = resultado.getString("DATA_FIM");
                double arremate = Double.parseDouble(resultado.getString("ARREMATE"));
                String vencedor = resultado.getString("VENCEDOR");
                String tipo_leilao = resultado.getString("TIPO");
                String tipo_lance = resultado.getString("TIPO_LANCE");
                Boolean status = true;

                Leilao leilao = new Leilao(leilaoId, loteId, arremate, vencedor, tipo_lance, tipo_lance, dataIni, dataFim);
                leilao.ativa();
                listaLeiloesAtivos.add(leilao);
            }
            return listaLeiloesAtivos;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public List<Leilao> getEncerrados() throws DAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM LEILOES WHERE DATA_FIM < CURRENT TIMESTAMP");
            List<Leilao> listaLeiloesEncerrados = new ArrayList<Leilao>();
            while (resultado.next()) {
                int leilaoId = Integer.parseInt(resultado.getString("LEILAO_ID"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("DATA_INICIO");
                String dataFim = resultado.getString("DATA_FIM");
                double arremate = Double.parseDouble(resultado.getString("ARREMATE"));
                String vencedor = resultado.getString("VENCEDOR");
                String tipo_leilao = resultado.getString("TIPO");
                String tipo_lance = resultado.getString("TIPO_LANCE");

                Leilao leilao = new Leilao(leilaoId, loteId, arremate, vencedor, tipo_lance, tipo_lance, dataIni, dataFim);
                leilao.desativa();
                listaLeiloesEncerrados.add(leilao);

            }
            return listaLeiloesEncerrados;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public List<Leilao> getTipoOferta() throws DAOException, ParseException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM LEILOES WHERE TIPO=?");
            stmt.setString(1, "OFERTA");
            ResultSet resultado = stmt.executeQuery();
            List<Leilao> listaLeiloesOferta = new ArrayList<Leilao>();
            while (resultado.next()) {
                int leilaoId = Integer.parseInt(resultado.getString("LEILAO_ID"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("DATA_INICIO");
                String dataFim = resultado.getString("DATA_FIM");
                double arremate = Double.parseDouble(resultado.getString("ARREMATE"));
                String vencedor = resultado.getString("VENCEDOR");
                String tipo_leilao = resultado.getString("TIPO");
                String tipo_lance = resultado.getString("TIPO_LANCE");

                Leilao leilao = new Leilao(leilaoId, loteId, arremate, vencedor, tipo_lance, tipo_lance, dataIni, dataFim);

                String data_hora_lance = dataFim;
                d_temp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data_hora_lance);
                if (agora.before(d_temp)) {
                    leilao.ativa();
                } else {
                    leilao.desativa();
                }

                listaLeiloesOferta.add(leilao);

            }
            return listaLeiloesOferta;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public List<Leilao> getTipoDemanda() throws DAOException, ParseException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM LEILOES WHERE TIPO=?");
            stmt.setString(1, "DEMANDA");
            ResultSet resultado = stmt.executeQuery();
            List<Leilao> listaLeiloesDemanda = new ArrayList<Leilao>();
            while (resultado.next()) {
                int leilaoId = Integer.parseInt(resultado.getString("LEILAO_ID"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("DATA_INICIO");
                String dataFim = resultado.getString("DATA_FIM");
                double arremate = Double.parseDouble(resultado.getString("ARREMATE"));
                String vencedor = resultado.getString("VENCEDOR");
                String tipo_leilao = resultado.getString("TIPO");
                String tipo_lance = resultado.getString("TIPO_LANCE");

                Leilao leilao = new Leilao(leilaoId, loteId, arremate, vencedor, tipo_lance, tipo_lance, dataIni, dataFim);

                String data_hora_lance = dataFim;
                d_temp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data_hora_lance);
                if (agora.before(d_temp)) {
                    leilao.ativa();
                } else {
                    leilao.desativa();
                }

                listaLeiloesDemanda.add(leilao);

            }
            return listaLeiloesDemanda;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public List<Leilao> getLanceAberto() throws DAOException, ParseException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM LEILOES WHERE TIPO_LANCE=?");
            stmt.setString(1, "ABERTO");
            ResultSet resultado = stmt.executeQuery();
            List<Leilao> listaLeiloesLanceAberto = new ArrayList<Leilao>();
            while (resultado.next()) {
                int leilaoId = Integer.parseInt(resultado.getString("LEILAO_ID"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("DATA_INICIO");
                String dataFim = resultado.getString("DATA_FIM");
                double arremate = Double.parseDouble(resultado.getString("ARREMATE"));
                String vencedor = resultado.getString("VENCEDOR");
                String tipo_leilao = resultado.getString("TIPO");
                String tipo_lance = resultado.getString("TIPO_LANCE");

                Leilao leilao = new Leilao(leilaoId, loteId, arremate, vencedor, tipo_lance, tipo_lance, dataIni, dataFim);

                String data_hora_lance = dataFim;
                d_temp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data_hora_lance);
                if (agora.before(d_temp)) {
                    leilao.ativa();
                } else {
                    leilao.desativa();
                }

                listaLeiloesLanceAberto.add(leilao);

            }
            return listaLeiloesLanceAberto;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public List<Leilao> getLanceFechado() throws DAOException, ParseException {
        try {
        	Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM LEILOES WHERE TIPO_LANCE=?");
            stmt.setString(1, "FECHADO");
            ResultSet resultado = stmt.executeQuery();
            List<Leilao> listaLeiloesLanceFechado = new ArrayList<Leilao>();
            while(resultado.next()) {
            	int leilaoId = Integer.parseInt(resultado.getString("LEILAO_ID"));
                int loteId = Integer.parseInt(resultado.getString("LOTE_ID_foreign_key"));
                String dataIni = resultado.getString("DATA_INICIO");
                String dataFim = resultado.getString("DATA_FIM");
                double arremate = Double.parseDouble(resultado.getString("ARREMATE"));
                String vencedor = resultado.getString("VENCEDOR");
                String tipo_leilao = resultado.getString("TIPO");
                String tipo_lance = resultado.getString("TIPO_LANCE");
                
                Leilao leilao = new Leilao(leilaoId, loteId, arremate, vencedor, tipo_lance, tipo_lance, dataIni, dataFim);
                
                String data_hora_lance=dataFim;
                d_temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data_hora_lance);
                if(agora.before(d_temp)){
                	leilao.ativa();
                }else leilao.desativa();
                
                listaLeiloesLanceFechado.add(leilao);
                
            }
            return listaLeiloesLanceFechado;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
    }

}
