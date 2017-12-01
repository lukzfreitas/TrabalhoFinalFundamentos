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
public class UsuarioPJ extends Usuario{
    
    private String cnpj;

    public UsuarioPJ(String cnpj, String nome, String email) {
        super(nome, email);
        this.cnpj = cnpj;        
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String toString() {
        return "cnpj: " + cnpj + super.toString();
    }
}
