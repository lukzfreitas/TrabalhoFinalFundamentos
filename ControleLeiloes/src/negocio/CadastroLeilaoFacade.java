/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.BemDAOJavaDb;
import dados.DAOException;
import dados.LeilaoDAOJavaDb;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class CadastroLeilaoFacade {

    private CadastroLeilaoDAO dao;

    public CadastroLeilaoFacade() throws DAOException {
        try {
            dao = LeilaoDAOJavaDb.getInstance();
        } catch (DAOException e) {
            throw new DAOException("Falha na criação da fachada", e);
        }
    }

    public Leilao adicionar(
            int loteId,
            double arremate,
            String vencedor,
            String tipo,
            String tipoLance,
            String dataInicio,
            String dataFim
    ) throws DAOException {
        Leilao leilao = new Leilao(loteId, arremate, vencedor, tipo, tipoLance, dataFim, dataFim);
        try {
            boolean ok = dao.adicionar(leilao);
            if (ok) {
                return leilao;
            }
            return null;
        } catch (DAOException e) {
            throw new DAOException("Falha ao adicionar leilão!", e);
        }
    }
    
    public boolean encerrarLeilao(int leilaoId) throws DAOException {
        try {
            return dao.encerrarLeilao(leilaoId);            
        } catch (DAOException e) {
            throw new DAOException("Falha ao encerrar leilão!", e);
        }
    }

    public List<Leilao> getTodos() throws DAOException, ParseException {
        try {
            return dao.getTodos();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar todos leilões", e);
        }
    }

    public Lance darLance(int leilaoId, String usuarioId, double valor) throws DAOException {
        Lance lance = new Lance(leilaoId, usuarioId, valor);
        try {
            boolean retorno = dao.darLance(lance);
            if (retorno) {
                return lance;
            }
            return null;
        } catch (DAOException e) {
            throw new DAOException("Falha ao realizar lance", e);
        }
    }

    public List<Leilao> getAtivos() throws DAOException {
        try {
            return dao.getAtivos();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar", e);
        }
    }

    public List<Leilao> getEncerrados() throws DAOException {
        try {
            return dao.getEncerrados();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar", e);
        }
    }
    
    public List<Leilao> getTipoOferta() throws DAOException, ParseException {
        try {
            return dao.getTipoOferta();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar", e);
        }
    }
    
    public List<Leilao> getTipoDemanda() throws DAOException, ParseException {
        try {
            return dao.getTipoDemanda();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar", e);
        }
    }
    
    public List<Leilao> getLanceAberto() throws DAOException, ParseException {
        try {
            return dao.getLanceAberto();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar", e);
        }
    }    
    
    public List<Leilao> getLanceFechado() throws DAOException, ParseException {
        try {
            return dao.getLanceFechado();
        } catch (DAOException e) {
            throw new DAOException("Falha ao buscar", e);
        }
    }
}
