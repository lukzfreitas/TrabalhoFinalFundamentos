/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Lucas
 */
public class UsuarioPF extends Usuario{
    private String cpf;
    private String nome;
    private String email;

    public UsuarioPF(String cpf, String nome, String email) {
        super(nome, email);
        this.cpf = cpf;        
    }

    public String getCpf() {
        return cpf;
    }    
}
