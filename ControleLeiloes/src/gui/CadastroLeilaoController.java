/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dados.DAOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import negocio.Bem;
import negocio.CadastroLeilaoDAO;
import negocio.CadastroLeilaoFacade;
import negocio.Lance;
import negocio.Leilao;

/**
 *
 * @author Lucas
 */
public class CadastroLeilaoController {

    private CadastroLeilaoFacade facade;
    private ListaLeilaoModel listaLeilaoModel;
    private ListaLanceModel listaLanceModel;

    public CadastroLeilaoController() throws DAOException, ParseException {
        facade = new CadastroLeilaoFacade();
        listaLeilaoModel = new ListaLeilaoModel(facade.getTodos());
    }

    private List<String> toListString(List<Leilao> listaOrigem) {
        List<String> listaDestino = new ArrayList<String>(listaOrigem.size());
        for (Leilao leilao : listaOrigem) {
            listaDestino.add(leilao.toString());
        }
        return listaDestino;
    }

    public boolean adicionar(
            int loteId,
            double arremate,
            String vencedor,
            String tipo,
            String tipoLance,
            String dataIni,
            String dataFim
    ) throws DAOException {
        Leilao leilao = facade.adicionar(loteId, arremate, vencedor, tipo, tipoLance, dataIni, dataFim);
        if (leilao != null) {
            listaLeilaoModel.add(leilao);
            return true;
        }
        return false;
    }

    public List<String> getTodos() throws DAOException, ParseException {        
        List<String> lista = new ArrayList<String>();
        for (Leilao leilao : facade.getTodos()) {
            lista.add(leilao.toString());
        }
        return lista;
    }

    public boolean darLance(
            int leilaoId,
            String usuarioId,            
            double valor
    ) throws DAOException {
        Lance lance = facade.darLance(leilaoId, usuarioId, valor);
        if (lance != null) {
            listaLanceModel.add(lance);
            return true;
        }
        return false;
    }

    public List<Leilao> getAtivos() throws DAOException {
        List<Leilao> lista = new ArrayList<Leilao>();
        for (Leilao leilao : facade.getAtivos()) {
            lista.add(leilao);
        }
        return lista;
    }

    public List<Leilao> getEncerrados() throws DAOException {
        List<Leilao> lista = new ArrayList<Leilao>();
        for (Leilao leilao : facade.getEncerrados()) {
            lista.add(leilao);
        }
        return lista;
    }

    public List<String> getTipoOferta() throws DAOException, ParseException {
        List<String> lista = new ArrayList<String>();
        for (Leilao leilao : facade.getTipoOferta()) {
            lista.add(leilao.toString());
        }
        return lista;
    }
    
    public List<String> getTipoDemanda() throws DAOException, ParseException {
        List<String> lista = new ArrayList<String>();
        for (Leilao leilao : facade.getTipoDemanda()) {
            lista.add(leilao.toString());
        }
        return lista;
    }

    public List<String> getLanceAberto() throws DAOException, ParseException {
        List<String> lista = new ArrayList<String>();
        for (Leilao leilao : facade.getLanceAberto()) {
            lista.add(leilao.toString());
        }
        return lista;
    }
    
    public List<String> getLanceFechado() throws DAOException, ParseException {
        List<String> lista = new ArrayList<String>();
        for (Leilao leilao : facade.getLanceFechado()) {
            lista.add(leilao.toString());
        }
        return lista;
    }

}
