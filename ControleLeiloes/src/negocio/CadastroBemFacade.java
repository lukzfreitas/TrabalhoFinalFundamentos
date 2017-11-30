/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.BemDAOJavaDb;
import dados.DAOException;
import dados.UsuarioPFDAOJavaDb;
import dados.UsuarioPJDAOJavaDb;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class CadastroBemFacade {
    
    private CadastroBemDAO dao;
    
    public CadastroBemFacade() throws DAOException {
        try {
            dao = BemDAOJavaDb.getInstance();            
        } catch (DAOException e) {
            throw new DAOException("Falha na criação da fachada", e);
        }
    }

    public Bem adicionarBem(            
            int loteId,
            String descricao, 
            String detalhes, 
            String categoria
    ) throws DAOException {        
        Bem bem = new Bem(loteId, descricao, detalhes, categoria);
        try {
            boolean adicionado = dao.adicionar(bem);
            if (adicionado) {
                return bem;
            }
            return null;
        } catch (DAOException e) {
            throw new DAOException("Falha ao adicionar Bem!", e);
        }        
    }

    public List<Bem> buscarTodos() throws DAOException {
        try {
            return dao.getTodos();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar bens", e);
        }
    }
    
    public Bem buscarBem(int bemId) throws DAOException {
        try {
            return dao.getBem(bemId);
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar bem", e);
        }
    }

    
}
