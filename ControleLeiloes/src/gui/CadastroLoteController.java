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
 * @author Lucas
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

    public boolean adicionarLote(int bemId, double valor) throws DAOException {
        Lote lote = facade.adicionarLote(bemId, valor);
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
    
    public Lote getLote(int loteId) throws DAOException {
        return facade.buscarLote(loteId);
    }
}
