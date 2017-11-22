/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

/**
 *
 * @author Lucas
 */
public class DAOException extends Exception{

    public DAOException() {
    }

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
