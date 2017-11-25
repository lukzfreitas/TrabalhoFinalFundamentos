/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Lucas
 */
public class Lote {
    private int loteId, bemId;
    private double valor;

    public Lote(int loteId, int bemId, double valor) {
        this.loteId = loteId;
        this.bemId = bemId;
        this.valor = valor;
    }

    public Lote(int bemId, double valor) {
        this.bemId = bemId;
        this.valor = valor;
    }

    public int getLoteId() {
        return loteId;
    }

    public int getBemId() {
        return bemId;
    }

    public double getValor() {
        return valor;
    }
}
