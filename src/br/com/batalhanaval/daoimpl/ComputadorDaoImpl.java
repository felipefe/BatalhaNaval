package br.com.batalhanaval.daoimpl;

import br.com.batalhanaval.dao.SessionFactory;
import br.com.batalhanaval.dao.ComputadorDao;
import br.com.batalhanaval.entidade.Computador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WILLIAM
 */
public class ComputadorDaoImpl implements ComputadorDao {

    private Connection conexao;

    @Override
    public boolean inserir(Object obj) throws Exception {
        Computador comp = (Computador) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement("insert into computador (nome, tentativas, acertos) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, comp.getNome());
            statement.setInt(2, comp.getTentativas());
            statement.setInt(3, comp.getAcertos());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                comp.setId(idInserido);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return false;
    }

    @Override
    public boolean update(Object obj) throws Exception {
        Computador comp = (Computador) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "update computador set nome = ?, tentativas = ?, acertos = ? where id = ? ");
            statement.setString(1, comp.getNome());
            statement.setInt(2, comp.getTentativas());
            statement.setInt(3, comp.getAcertos());
            statement.setInt(4, comp.getId());
            int linhasAtualizadas = statement.executeUpdate();
            return linhasAtualizadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return false;
    }

    @Override
    public Computador pesquisar(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "select * from computador where id = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                Integer tentativas = rs.getInt("tentativas");
                Integer acertos = rs.getInt("acertos");
                Computador comp = new Computador();
                comp.setNome(nome);
                comp.setTentativas(tentativas);
                comp.setAcertos(acertos);
                comp.setId(id);
                return comp;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return null;
    }

    @Override
    public List<Object> pesquisarTodos() throws Exception {
        List<Object> computadors = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "select * from computador");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer idComputador = rs.getInt("id");
                String nome = rs.getString("nome");
                Integer tentativas = rs.getInt("tentativas");
                Integer acertos = rs.getInt("acertos");
                Computador comp = new Computador();
                comp.setNome(nome);
                comp.setTentativas(tentativas);
                comp.setAcertos(acertos);
                comp.setId(idComputador);
                computadors.add(comp);
            }
            return computadors;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return computadors;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "delete from computador where id = ? ");
            statement.setInt(1, id);
            int executeUpdate = statement.executeUpdate();
            return executeUpdate != 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return false;
    }

}
