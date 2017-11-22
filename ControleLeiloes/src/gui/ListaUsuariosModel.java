/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import negocio.UsuarioPF;

/**
 *
 * @author Lucas
 */
public class ListaUsuariosModel {
    
    private List<UsuarioPF> usuariosPF = new ArrayList<UsuarioPF>();

    public ListaUsuariosModel() {
        super();
    }    
    
    public ListaUsuariosModel(List<UsuarioPF> dados) {
        usuariosPF.addAll(dados);
    }    
    
    public int getSize() {
        return usuariosPF.size();
    }
    
    public UsuarioPF getElementAt(int index) {
        return usuariosPF.get(index);
    }
    
    public void add(UsuarioPF usuarioPF) {
        usuariosPF.add(usuarioPF);        
    }    
}
