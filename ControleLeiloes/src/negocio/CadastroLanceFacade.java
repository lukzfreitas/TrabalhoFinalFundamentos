/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import dados.LanceDAOJavaDb;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class CadastroLanceFacade {

    private CadastroLanceDAO dao;

    public CadastroLanceFacade() throws DAOException {
        try {
            dao = LanceDAOJavaDb.getInstance();
        } catch (DAOException e) {
            throw new DAOException("Falha na criação da fachada", e);
        }
    }
    
    public Lance adicionar(
            int leilaoId, 
            String usuarioId, 
            String data, 
            double valor
    ) throws DAOException {
        Lance lance = new Lance(leilaoId, leilaoId, usuarioId, data, valor);
        try {
            boolean ok = dao.adicionar(lance);
            if (ok) {
                return lance;
            }
            return null;
        } catch (DAOException e) {
            throw new DAOException("Falha ao adicionar lance!", e);
        }
    }

    public List<Lance> getTodos() throws DAOException {
        try {
            return dao.getTodos();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar todos lances", e);
        }
    }
    
    public List<Lance> getLancesPorLeilaoID(int leilaoId) throws DAOException {
        try {
            return dao.getLancesPorLeilaoID(leilaoId);
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar todos lances", e);
        }
    }
}
