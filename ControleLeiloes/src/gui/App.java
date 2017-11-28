/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dados.DAOException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author Lucas
 */
public class App {
    public static void main(String[] args) throws DAOException, SQLException, FileNotFoundException, ParseException {
//        ImportarSQL.execute("C:\\Users\\Lucas\\OneDrive\\Documentos\\PUCRS\\Fund. Desenv. SW\\TrabalhoFinalFundamentos\\ControleLeiloes\\ScriptsBanco.txt");
        CadastroLeilaoController leilaoController = new CadastroLeilaoController();
        System.out.println(leilaoController.getEncerrados());
    }
}
