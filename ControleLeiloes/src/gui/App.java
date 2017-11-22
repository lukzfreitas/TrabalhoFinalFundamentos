/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dados.DAOException;

/**
 *
 * @author Lucas
 */
public class App {
    public static void main(String[] args) throws DAOException {
        CadastroUsuarioController controller = new CadastroUsuarioController();
        controller.adicionarUsuario("026.485.190-10", "Isadora Freitas", "isadora@gmail.com");
        System.out.println(controller.getTodos());
    }
}
