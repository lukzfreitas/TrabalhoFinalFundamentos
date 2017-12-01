/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import negocio.Leilao;


/**
 *
 * @author Camila e Lucas
 */
public class ListaLeilaoModel extends AbstractListModel<Leilao>{
    
    private List<Leilao> leiloes = new ArrayList<Leilao>();

    public ListaLeilaoModel() {
        super();
    }

    public ListaLeilaoModel(List<Leilao> dados) {
        leiloes.addAll(dados);
    }

    @Override
    public int getSize() {
        return leiloes.size();
    }

    @Override
    public Leilao getElementAt(int index) {
        return leiloes.get(index);
    }

    public void add(Leilao leilao) {
        leiloes.add(leilao);
    }
}
