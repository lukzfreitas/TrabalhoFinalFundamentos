/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Camila e Lucas
 */
public class UsuarioPF extends Usuario{
    private String cpf;   

    public UsuarioPF(String cpf, String nome, String email) {
        super(nome, email);
        this.cpf = cpf;        
    }

    public String getCpf() {
        return cpf;
    }    

    @Override
    public String toString() {
        return "cpf: " + cpf + super.toString();
    }
    
    
}
