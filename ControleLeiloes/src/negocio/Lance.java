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
public class Lance {

    private int lanceId, leilaoId;
    private String usuarioId, data;
    private double valor;

    public Lance(int lanceId, int leilaoId, String usuarioId, String data, double valor) {
        this.lanceId = lanceId;
        this.leilaoId = leilaoId;
        this.usuarioId = usuarioId;
        this.data = data;
        this.valor = valor;
    }

    public Lance(int leilaoId, String usuarioId, String data, double valor) {
        this.leilaoId = leilaoId;
        this.usuarioId = usuarioId;
        this.data = data;
        this.valor = valor;
    }

    public int getLanceId() {
        return lanceId;
    }

    public int getLeilaoId() {
        return leilaoId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public String getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
    
    
}
