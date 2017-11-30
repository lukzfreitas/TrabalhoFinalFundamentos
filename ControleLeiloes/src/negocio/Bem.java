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
public class Bem {
    private int bemId;
    private int loteId;
    private String descricao;
    private String detalhes;
    private String categoria;

    public Bem(int loteId, String descricao, String detalhes, String categoria) {        
        this.loteId = loteId;
        this.descricao = descricao;
        this.detalhes = detalhes;
        this.categoria = categoria;        
    }

    public Bem(int bemId, int loteId, String descricao, String detalhes, String categoria) {
        this.bemId = bemId;
        this.loteId = loteId;
        this.descricao = descricao;
        this.detalhes = detalhes;
        this.categoria = categoria;        
    }

    public int getBemId() {
        return bemId;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getLoteId() {
        return loteId;
    }

    @Override
    public String toString() {
        String str = "";
        if(bemId > 0) {
           str = str + "BemId: " + bemId;
        }
        if(loteId > 0) {
           str = str + ", LoteId: " + loteId; 
        }
        return str + ", descricao:" + descricao + ", detalhes: " + detalhes + ", categoria: " + categoria;
    }
    
    
}
