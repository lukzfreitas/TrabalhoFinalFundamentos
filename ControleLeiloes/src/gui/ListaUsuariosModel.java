/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import negocio.Usuario;
import negocio.UsuarioPF;

/**
 *
 * @author Lucas
 */
public class ListaUsuariosModel {
    
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public ListaUsuariosModel() {
        super();
    }    
    
    public ListaUsuariosModel(List<Usuario> dados) {
        usuarios.addAll(dados);
    }    
    
    public int getSize() {
        return usuarios.size();
    }
    
    public Usuario getElementAt(int index) {
        return usuarios.get(index);
    }
    
    public void add(Usuario usuario) {
        usuarios.add(usuario);        
    }    
}
