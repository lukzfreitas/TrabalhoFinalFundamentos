/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import negocio.Lance;

/**
 *
 * @author Lucas
 */
public class ListaLanceModel {

    private List<Lance> lances = new ArrayList<Lance>();

    public ListaLanceModel() {
        super();
    }

    public ListaLanceModel(List<Lance> dados) {
        lances.addAll(dados);
    }

    public int getSize() {
        return lances.size();
    }

    public Lance getElementAt(int index) {
        return lances.get(index);
    }

    public void add(Lance leilao) {
        lances.add(leilao);
    }
}
