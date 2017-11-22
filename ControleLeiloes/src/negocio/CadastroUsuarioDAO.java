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
public interface CadastroUsuarioDAO {
    boolean adicionar(UsuarioPF usuario) throws DAOException;
    List<UsuarioPF> getTodos() throws DAOException;
}
