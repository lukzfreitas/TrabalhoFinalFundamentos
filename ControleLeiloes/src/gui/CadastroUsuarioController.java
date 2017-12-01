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
import negocio.Usuario;
import negocio.UsuarioPF;

/**
 *
 * @author Camila e Lucas
 */
public class CadastroUsuarioController {

    private CadastroUsuarioFacade facade;
    private ListaUsuariosModel model;

    public CadastroUsuarioController() throws DAOException {
        facade = new CadastroUsuarioFacade();
        model = new ListaUsuariosModel(facade.buscarTodosPF());;
    }

    private List<String> toListString(List<UsuarioPF> listaOrigem) {
        List<String> listaDestino = new ArrayList<String>(listaOrigem.size());
        for (UsuarioPF usuarioPF : listaOrigem) {
            listaDestino.add(usuarioPF.getNome());
        }
        return listaDestino;
    }

    public boolean adicionarUsuario(String id, String nome, String email) throws DAOException {
        Usuario usuario = facade.adicionarUsuario(id, nome, email);
        if (usuario != null) {
            model.add(usuario);
            return true;
        }
        return false;
    }

    public List<Usuario> getTodos() throws DAOException {
        List<Usuario> lista = new ArrayList<Usuario>();
        for (Usuario usuario : facade.buscarTodosPF()) {
            lista.add(usuario);
        }
        for (Usuario usuario: facade.buscarTodosPJ()) {
            lista.add(usuario);
        }
        return lista;
    }   
    
}
