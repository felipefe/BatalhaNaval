package br.com.batalhanaval.dao;

import java.util.List;

/**
 *
 * @author Felipe
 */
public interface BaseDao {

    public boolean inserir(Object objeto) throws Exception;

    public boolean update(Object objeto) throws Exception;

    public Object pesquisar(Integer id) throws Exception;

    public List<Object> pesquisarTodos() throws Exception;

    public boolean excluir(Integer id) throws Exception;

}
