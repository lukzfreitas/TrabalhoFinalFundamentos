/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dados.DAOException;
import java.util.ArrayList;
import java.util.List;
import negocio.CadastroLanceFacade;
import negocio.Lance;

/**
 *
 * @author Lucas
 */
public class CadastroLanceController {
    
    private CadastroLanceFacade facade;    
    private ListaLanceModel listaLanceModel;

    public CadastroLanceController() throws DAOException{
        facade = new CadastroLanceFacade();
        listaLanceModel = new ListaLanceModel(facade.getTodos());
    }

    private List<String> toListString(List<Lance> listaOrigem) {
        List<String> listaDestino = new ArrayList<String>(listaOrigem.size());
        for (Lance lance : listaOrigem) {
            listaDestino.add(lance.toString());
        }
        return listaDestino;
    }
    
    public boolean adicionar(
            int leilaoId, 
            String usuarioId, 
            String data, 
            double valor
    ) throws DAOException {
        Lance lance = new Lance(leilaoId, usuarioId, data, valor);
        if (lance != null) {
            listaLanceModel.add(lance);
            return true;
        }
        return false;
    }
    
    public List<String> getTodos() throws DAOException{
        List<String> lista = new ArrayList<String>();
        for (Lance lance : facade.getTodos()) {
            lista.add(lance.toString());
        }
        return lista;
    }
    
    public List<String> getLancesPorLeilaoID(int leilaoId) throws DAOException {
        List<String> lista = new ArrayList<String>();
        for (Lance lance : facade.getLancesPorLeilaoID(leilaoId)) {
            lista.add(lance.toString());
        }
        return lista;
    }
}
