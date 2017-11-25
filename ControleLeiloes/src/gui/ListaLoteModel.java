/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import negocio.Lote;

/**
 *
 * @author Lucas
 */
public class ListaLoteModel {

    private List<Lote> lotes = new ArrayList<Lote>();

    public ListaLoteModel() {
        super();
    }

    public ListaLoteModel(List<Lote> dados) {
        lotes.addAll(dados);
    }

    public int getSize() {
        return lotes.size();
    }

    public Lote getElementAt(int index) {
        return lotes.get(index);
    }

    public void add(Lote lote) {
        lotes.add(lote);
    }
}
