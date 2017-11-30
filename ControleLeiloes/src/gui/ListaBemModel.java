/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import negocio.Bem;
import negocio.Usuario;

/**
 *
 * @author Lucas
 */
public class ListaBemModel extends AbstractListModel<Bem>{

    private List<Bem> bens = new ArrayList<Bem>();

    public ListaBemModel() {
        super();
    }    
    
    public ListaBemModel(List<Bem> dados) {
        bens.addAll(dados);
    }    
    
    @Override
    public int getSize() {
        return bens.size();
    }
    
    @Override
    public Bem getElementAt(int index) {
        return bens.get(index);
    }
    
    public void add(Bem bem) {
        bens.add(bem);        
    }        
    
    public List<Bem> getList() {
        return bens;
    }
}
