package br.com.batalhanaval.dao;

import java.util.List;

/**
 *
 * @author Felipe
 */
public interface JogadorDao extends BaseDao {

    public void imprimirClassificacaoDeJogadores() throws Exception;

    public List<Object> pesquisarJogadoresOrdenadosPorTentativas() throws Exception;
    


}
