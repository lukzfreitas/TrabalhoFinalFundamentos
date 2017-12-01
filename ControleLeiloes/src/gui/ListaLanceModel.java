/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import negocio.Lance;

/**
 *
 * @author Camila e Lucas
 */
public class ListaLanceModel extends AbstractListModel<Lance>{

    private List<Lance> lances = new ArrayList<Lance>();

    public ListaLanceModel() {
        super();
    }

    public ListaLanceModel(List<Lance> dados) {
        lances.addAll(dados);
    }

    @Override
    public int getSize() {
        return lances.size();
    }

    @Override
    public Lance getElementAt(int index) {
        return lances.get(index);
    }

    public void add(Lance leilao) {
        lances.add(leilao);
    }
}
