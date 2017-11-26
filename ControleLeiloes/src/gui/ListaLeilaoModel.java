/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import negocio.Leilao;


/**
 *
 * @author Lucas
 */
public class ListaLeilaoModel {
    private List<Leilao> leiloes = new ArrayList<Leilao>();

    public ListaLeilaoModel() {
        super();
    }

    public ListaLeilaoModel(List<Leilao> dados) {
        leiloes.addAll(dados);
    }

    public int getSize() {
        return leiloes.size();
    }

    public Leilao getElementAt(int index) {
        return leiloes.get(index);
    }

    public void add(Leilao leilao) {
        leiloes.add(leilao);
    }
}
