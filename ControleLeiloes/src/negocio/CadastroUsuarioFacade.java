/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import dados.UsuarioPFDAOJavaDb;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class CadastroUsuarioFacade {

    private CadastroUsuarioDAO dao;

    public CadastroUsuarioFacade() throws DAOException {
        try {
            dao = UsuarioPFDAOJavaDb.getInstance();
        } catch (DAOException e) {
            throw new DAOException("Falha na criação da fachada", e);
        }
    }

    public UsuarioPF adicionarUsuarioPF(String cpf, String nome, String email) throws DAOException {
        //TODO: Validar Usuario
        UsuarioPF usuarioPF = new UsuarioPF(cpf, nome, email);
        try {
            boolean adicionado = dao.adicionar(usuarioPF);
            if (adicionado) {
                return usuarioPF;
            }
            return null;
        } catch (DAOException e) {
            throw new DAOException("Falha ao adicionar pessoa!", e);
        }        
    }
    
    public List<UsuarioPF> buscarTodos() throws DAOException {
        try {
            return dao.getTodos();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar Pessoas Físicas", e);
        }
    }
}
