package br.com.batalhanaval.entidade;

import java.util.Date;


/**
 *
 * @author WILL
 */
public class Jogo {

    private Integer id;
    private Integer totalTentativas;
    private Integer totalAcertos;
    private Date dataJogo;
    private Jogador jogador;
    private Computador computador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalTentativas() {
        return totalTentativas;
    }

    public void setTotalTentativas(Integer totalTentativas) {
        this.totalTentativas = totalTentativas;
    }

    public Integer getTotalAcertos() {
        return totalAcertos;
    }

    public void setTotalAcertos(Integer totalAcertos) {
        this.totalAcertos = totalAcertos;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Computador getComputador() {
        return computador;
    }

    public void setComputador(Computador computador) {
        this.computador = computador;
    }

    public Date getData() {
        return dataJogo;
    }

    public void setData(Date data) {
        this.dataJogo = data;
    }

    public Date getDataJogo() {
        return dataJogo;
    }

    public void setDataJogo(Date dataJogo) {
        this.dataJogo = dataJogo;
    }

    @Override
    public String toString() {
        return "Jogo{" + "id=" + id + ", totalTentativas=" + totalTentativas + ", totalAcertos=" + totalAcertos + ", dataJogo=" + dataJogo + ", jogador=" + jogador + ", computador=" + computador + '}';
    }
    
    
    
    

}
