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
public interface CadastroLanceDAO {
    boolean adicionar(Lance lance) throws DAOException;    
    boolean cancelarLance(int lanceId) throws DAOException;
    List<Lance> getTodos() throws DAOException;
    List<Lance> getLancesPorLeilaoID(int leilaoId) throws DAOException;    
    List<Lance> getLancesPorUsuarioID(String usuarioId) throws DAOException;    
}
