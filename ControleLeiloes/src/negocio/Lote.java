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
    private int loteId;
    private double valor;

    public Lote(int loteId, double valor) {
        this.loteId = loteId;        
        this.valor = valor;
    }

    public Lote(double valor) {        
        this.valor = valor;
    }

    public int getLoteId() {
        return loteId;
    }   

    public double getValor() {
        return valor;
    }
}
