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
    private String descricao;
    private String detalhes;
    private String categoria;

    public Bem(String descricao, String detalhes, String categoria) {        
        this.descricao = descricao;
        this.detalhes = detalhes;
        this.categoria = categoria;        
    }

    public Bem(int bemId, String descricao, String detalhes, String categoria) {
        this.bemId = bemId;
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
}
