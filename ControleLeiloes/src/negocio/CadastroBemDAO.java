/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import java.util.List;

/**
 *
 * @author Camila e Lucas
 */
public interface CadastroBemDAO {
    boolean adicionar(Bem bemId) throws DAOException;
    List<Bem> getTodos() throws DAOException;
    List<Bem> getBensPorLoteId(int loteId) throws DAOException;
    Bem getBem(int bemId) throws DAOException;
    
}
