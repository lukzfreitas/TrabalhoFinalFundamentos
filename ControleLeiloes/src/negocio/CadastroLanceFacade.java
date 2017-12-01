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
 * @author Camila e Lucas
 */
public class CadastroLanceFacade {

    private CadastroLanceDAO dao;
    private CadastroLeilaoDAO leilaoDao;

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
            double valor
    ) throws DAOException {
        Lance lance = new Lance(leilaoId, usuarioId, valor);
        try {
            boolean ok = dao.adicionar(lance);
            if (ok) {
                if (valor > leilaoDao.getArremate(leilaoId)) {
                    leilaoDao.alterarVencedor(leilaoId, usuarioId, valor);
                }
                return lance;
            }
            return null;
        } catch (DAOException e) {
            throw new DAOException("Falha ao adicionar lance!", e);
        }
    }

    public boolean cancelarLance(int lanceId) throws DAOException {
        try {
            return dao.cancelarLance(lanceId);
        } catch (DAOException e) {
            throw new DAOException("Falha ao cancelar lance!", e);
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

    public List<Lance> getLancesPorUsuarioID(String usuarioId) throws DAOException {
        try {
            return dao.getLancesPorUsuarioID(usuarioId);
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar todos lances", e);
        }
    }
}
