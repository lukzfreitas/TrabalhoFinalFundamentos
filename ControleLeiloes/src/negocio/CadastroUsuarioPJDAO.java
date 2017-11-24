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
public interface CadastroUsuarioPJDAO {
    boolean adicionarPJ(UsuarioPJ usuarioPJ) throws DAOException;    
    List<Usuario> getTodos() throws DAOException;
}
