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
 * @author Lucas
 */
public interface CadastroLoteDAO {
    boolean adicionar(Lote lote) throws DAOException;
    List<Lote> getTodos() throws DAOException;
    Lote getLote(int loteId) throws DAOException;
}
