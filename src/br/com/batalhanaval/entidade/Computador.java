package br.com.batalhanaval.entidade;

/**
 *
 * @author William
 */
public class Computador {

    private Integer id;
    private String nome;
    private Integer tentativas;
    private Integer acertos;

    public Computador() {

    }

    public Computador(Integer id, String nome, Integer tentativas, Integer acertos) {
        this.id = id;
        this.nome = nome;
        this.tentativas = tentativas;
        this.acertos = acertos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTentativas() {
        return tentativas;
    }

    public void setTentativas(Integer tentativas) {
        this.tentativas = tentativas;
    }

    public Integer getAcertos() {
        return acertos;
    }

    public void setAcertos(Integer acertos) {
        this.acertos = acertos;
    }
    

    @Override
    public String toString() {
        return "Jogador{" + "id=" + id + ", nome=" + nome + ", tentativas=" + tentativas + ", acertos=" + acertos + '}';
    }
    
    
    

}
