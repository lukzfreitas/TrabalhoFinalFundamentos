/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Camila e Lucas
 */
public class Leilao {

    private int leilaoId, loteId;
    private double arremate;
    private String vencedor, tipo, tipoLance, dataIni, dataFim;
    private boolean status;

    public Leilao(
            int loteId,
            double arremate,
            String vencedor,
            String tipo,
            String tipoLance,
            String dataIni,
            String dataFim            
    ) {
        this.loteId = loteId;
        this.arremate = arremate;
        this.vencedor = vencedor;
        this.tipo = tipo;
        this.tipoLance = tipoLance;
        this.dataIni = dataIni;
        this.dataFim = dataFim;       
    }

    public Leilao(
            int leilaoId,
            int loteId,
            double arremate,
            String vencedor,
            String tipo,
            String tipoLance,
            String dataIni,
            String dataFim            
    ) {
        this.leilaoId = leilaoId;
        this.loteId = loteId;
        this.arremate = arremate;
        this.vencedor = vencedor;
        this.tipo = tipo;
        this.tipoLance = tipoLance;
        this.dataIni = dataIni;
        this.dataFim = dataFim;        
    }

    public void ativa() {
        status = true;
    }

    public void desativa() {
        status = false;
    }

    public int getLeilaoId() {
        return leilaoId;
    }

    public int getLoteId() {
        return loteId;
    }

    public double getArremate() {
        return arremate;
    }

    public String getVencedor() {
        return vencedor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTipoLance() {
        return tipoLance;
    }

    public String getDataIni() {
        return dataIni;
    }

    public String getDataFim() {
        return dataFim;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "código: " + leilaoId + " arremate:" + arremate + " vencedor:" + vencedor + ", tipo: " + tipo + ", tipoLance: " + tipoLance + ", Data Inicio:" + dataIni + ", Data Fim: " + dataFim + " status:" + status + '}';
    }
}
