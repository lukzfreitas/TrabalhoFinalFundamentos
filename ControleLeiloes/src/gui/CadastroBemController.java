/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dados.DAOException;
import java.util.ArrayList;
import java.util.List;
import negocio.Bem;
import negocio.CadastroBemFacade;
import negocio.Usuario;
import negocio.UsuarioPF;

/**
 *
 * @author Lucas
 */
public class CadastroBemController {

    private CadastroBemFacade facade;
    private ListaBemModel model;

    public CadastroBemController() throws DAOException {
        facade = new CadastroBemFacade();
        model = new ListaBemModel(facade.buscarTodos());
    }

    private List<String> toListString(List<Bem> listaOrigem) {
        List<String> listaDestino = new ArrayList<String>(listaOrigem.size());
        for (Bem bem : listaOrigem) {
            listaDestino.add(bem.toString());
        }
        return listaDestino;
    }

    public Bem adicionarBem(            
            int loteId,
            String descricao,
            String detalhes,
            String categoria
    ) throws DAOException {
        Bem bem = facade.adicionarBem(loteId, descricao, detalhes, categoria);
        if (bem != null) {
            model.add(bem);
            return bem;
        }
        return null;
    }

    public List<String> getTodos() throws DAOException {
        List<String> lista = new ArrayList<String>();
        for (Bem bem : facade.buscarTodos()) {
            lista.add(bem.toString());
        }        
        return lista;
    }
    
    public Bem getBem(int bemId) throws DAOException {
        return facade.buscarBem(bemId);
    }

}
