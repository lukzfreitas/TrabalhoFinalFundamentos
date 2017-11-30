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
import negocio.UsuarioPJ;

/**
 *
 * @author Lucas
 */
public class ListaUsuariosModel extends AbstractListModel<Usuario>{
    
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public ListaUsuariosModel() {
        super();
    }    
    
    public ListaUsuariosModel(List<Usuario> dados) {
        usuarios.addAll(dados);
    }    
    
    @Override
    public int getSize() {
        return usuarios.size();
    }
    
    @Override
    public Usuario getElementAt(int index) {
        return usuarios.get(index);
    }
    
    public String getCpfOuCnpjSelecionado(int index) {
        if (usuarios.get(index) instanceof UsuarioPF) {
            UsuarioPF usuarioPF = (UsuarioPF)usuarios.get(index);
            return usuarioPF.getCpf();
        } else {
            UsuarioPJ usuarioPJ = (UsuarioPJ)usuarios.get(index);
            return usuarioPJ.getCnpj();
        }
    }
    
    public void add(Usuario usuario) {
        usuarios.add(usuario);        
    }    
}
