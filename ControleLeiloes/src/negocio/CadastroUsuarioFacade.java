/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import dados.UsuarioPFDAOJavaDb;
import dados.UsuarioPJDAOJavaDb;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class CadastroUsuarioFacade {

    private CadastroUsuarioPFDAO daoPF;
    private CadastroUsuarioPJDAO daoPJ;

    public CadastroUsuarioFacade() throws DAOException {
        try {
            daoPF = UsuarioPFDAOJavaDb.getInstance();
            daoPJ = UsuarioPJDAOJavaDb.getInstance();
        } catch (DAOException e) {
            throw new DAOException("Falha na criação da fachada", e);
        }
    }

    public Usuario adicionarUsuario(String id, String nome, String email) throws DAOException {
        if (id.length() == 14) {
            UsuarioPF usuarioPF = new UsuarioPF(id, nome, email);
            try {
                boolean adicionado = daoPF.adicionarPF(usuarioPF);
                if (adicionado) {
                    return usuarioPF;
                }
                return null;
            } catch (DAOException e) {
                throw new DAOException("Falha ao adicionar usuário PF!", e);
            }
        } else {
            UsuarioPJ usuarioPJ = new UsuarioPJ(id, nome, email);
            try {
                boolean adicionado = daoPJ.adicionarPJ(usuarioPJ);
                if (adicionado) {
                    return usuarioPJ;
                }
                return null;
            } catch (DAOException e) {
                throw new DAOException("Falha ao adicionar usuário PJ!", e);
            }
        }
    }

    public List<Usuario> buscarTodosPF() throws DAOException {
        try {
            return daoPF.getTodos();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar usuários", e);
        }
    }

    public List<Usuario> buscarTodosPJ() throws DAOException {
        try {
            return daoPJ.getTodos();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar usuários", e);
        }
    }
}
