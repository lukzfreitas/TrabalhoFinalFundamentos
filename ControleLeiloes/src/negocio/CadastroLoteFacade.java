/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import dados.LoteDAOJavaDb;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class CadastroLoteFacade {

    private CadastroLoteDAO dao;

    public CadastroLoteFacade() throws DAOException {
        try {
            dao = LoteDAOJavaDb.getInstance();
        } catch (DAOException e) {
            throw new DAOException("Falha na criação da fachada", e);
        }
    }

    public Lote adicionarLote(double valor) throws DAOException {
        Lote lote = new Lote(valor);
        try {
            boolean adicionado = dao.adicionar(lote);
            if (adicionado) {
                return lote;
            }
            return null;
        } catch (DAOException e) {
            throw new DAOException("Falha ao adicionar Lote!", e);
        }
    }

    public List<Lote> buscarTodos() throws DAOException {
        try {
            return dao.getTodos();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar lotes", e);
        }
    }

    public Lote buscarLote(int loteId) throws DAOException {
        try {
            return dao.getLote(loteId);
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar lote", e);
        }
    }
}
