/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dados.DAOException;
import java.util.ArrayList;
import java.util.List;
import negocio.CadastroLoteFacade;
import negocio.Lote;

/**
 *
 * @author Camila e Lucas
 */
public class CadastroLoteController {
    
    private CadastroLoteFacade facade;
    private ListaLoteModel model;

    public CadastroLoteController() throws DAOException {
        facade = new CadastroLoteFacade();
        model = new ListaLoteModel(facade.buscarTodos());
    }

    private List<Double> toListString(List<Lote> listaOrigem) {
        List<Double> listaDestino = new ArrayList<Double>(listaOrigem.size());
        for (Lote lote : listaOrigem) {
            listaDestino.add(lote.getValor());
        }
        return listaDestino;
    }

    public boolean adicionarLote(double valor) throws DAOException {
        Lote lote = facade.adicionarLote(valor);        
        if (lote != null) {
            model.add(lote);
            return true;
        }
        return false;
    }

    public List<Double> getTodos() throws DAOException {
        List<Double> lista = new ArrayList<Double>();
        for (Lote lote : facade.buscarTodos()) {
            lista.add(lote.getValor());
        }        
        return lista;
    }
    
    public List<Integer> getIds() throws DAOException {
        List<Integer> lista = new ArrayList<Integer>();
        for (Lote lote : facade.buscarTodos()) {
            lista.add(lote.getLoteId());
        }        
        return lista;
    }
    
    public Lote getLote(int loteId) throws DAOException {
        return facade.buscarLote(loteId);
    }
    
    public int getUltimoLoteId() throws DAOException {
        return getIds().get(getIds().size()-1);
    }
}
