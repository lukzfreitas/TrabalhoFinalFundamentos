/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dados.DAOException;
import java.util.ArrayList;
import java.util.List;
import negocio.CadastroUsuarioFacade;
import negocio.UsuarioPF;

/**
 *
 * @author Lucas
 */
public class CadastroUsuarioController {

    private CadastroUsuarioFacade facade;
    private ListaUsuariosModel model;

    public CadastroUsuarioController() throws DAOException {
        facade = new CadastroUsuarioFacade();
        model = new ListaUsuariosModel(facade.buscarTodos());;
    }

    private List<String> toListString(List<UsuarioPF> listaOrigem) {
        List<String> listaDestino = new ArrayList<String>(listaOrigem.size());
        for (UsuarioPF usuarioPF : listaOrigem) {
            listaDestino.add(usuarioPF.getNome());
        }
        return listaDestino;
    }

    public boolean adicionarUsuario(String cpf, String nome, String email) throws DAOException {
        UsuarioPF usuarioPF = facade.adicionarUsuarioPF(cpf, nome, email);
        if (usuarioPF != null) {
            model.add(usuarioPF);
            return true;
        }
        return false;
    }

    public List<String> getTodos() throws DAOException {
        return toListString(facade.buscarTodos());
    }
}
