/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Camila e Lucas
 */
public interface CadastroLeilaoDAO {
    boolean adicionar(Leilao leilao) throws DAOException;
    List<Leilao> getTodos() throws DAOException, ParseException;        
    boolean encerrarLeilao(int leilaoId) throws DAOException;
    boolean alterarVencedor(int leilaoId, String usuarioId, double arremate) throws DAOException;
    double getArremate(int leilaoId) throws DAOException;
    List<Leilao> getAtivos() throws DAOException;
    List<Leilao> getEncerrados() throws DAOException;
    List<Leilao> getTipoOferta() throws DAOException, ParseException;
    List<Leilao> getTipoDemanda() throws DAOException, ParseException;
    List<Leilao> getLanceAberto() throws DAOException, ParseException;
    List<Leilao> getLanceFechado() throws DAOException, ParseException;
}
