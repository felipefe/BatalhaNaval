package br.com.batalhanaval.daoimpl;

import br.com.batalhanaval.dao.SessionFactory;
import br.com.batalhanaval.dao.JogadorDao;
import br.com.batalhanaval.entidade.Jogador;
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
public class JogadorDaoImpl implements JogadorDao {

    private Connection conexao;

    @Override
    public boolean inserir(Object obj) throws Exception {
        Jogador jog = (Jogador) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement("insert into jogador (nome, tentativas, acertos) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, jog.getNome());
            statement.setInt(2, jog.getTentativas());
            statement.setInt(3, jog.getAcertos());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                jog.setId(idInserido);
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
        Jogador jog = (Jogador) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "update jogador set nome = ?, tentativas = ?, acertos = ? where id = ? ");
            statement.setString(1, jog.getNome());
            statement.setInt(2, jog.getTentativas());
            statement.setInt(3, jog.getAcertos());
            statement.setInt(4, jog.getId());
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
    public Jogador pesquisar(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "select * from jogador where id = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                Integer tentativas = rs.getInt("tentativas");
                Integer acertos = rs.getInt("acertos");
                Jogador jog = new Jogador();
                jog.setNome(nome);
                jog.setTentativas(tentativas);
                jog.setAcertos(acertos);
                jog.setId(id);
                return jog;
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
        List<Object> jogadors = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "select * from jogador");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer idJogador = rs.getInt("id");
                String nome = rs.getString("nome");
                Integer tentativas = rs.getInt("tentativas");
                Integer acertos = rs.getInt("acertos");
                Jogador jog = new Jogador();
                jog.setNome(nome);
                jog.setTentativas(tentativas);
                jog.setAcertos(acertos);
                jog.setId(idJogador);
                jogadors.add(jog);
            }
            return jogadors;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return jogadors;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "delete from jogador where id = ? ");
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

    @Override
    public List<Object> pesquisarJogadoresOrdenadosPorTentativas() throws Exception {
        List<Object> jogadors = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "select * from jogador order by tentativas asc");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer idJogador = rs.getInt("id");
                String nome = rs.getString("nome");
                Integer tentativas = rs.getInt("tentativas");
                Integer acertos = rs.getInt("acertos");
                Jogador jog = new Jogador();
                jog.setNome(nome);
                jog.setTentativas(tentativas);
                jog.setAcertos(acertos);
                jog.setId(idJogador);
                jogadors.add(jog);
            }
            return jogadors;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return jogadors;
    }

    @Override
    public void imprimirClassificacaoDeJogadores() throws Exception {
        List<Object> obj = pesquisarJogadoresOrdenadosPorTentativas();
        List<Jogador> JOGADORES = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            Jogador get = (Jogador) obj.get(i);
            JOGADORES.add(get);
        }
        System.out.println("P  ||Nome || Tentativas || Acertos ");
        for (int i = 0; i < JOGADORES.size(); i++) {
            Jogador get = JOGADORES.get(i);

            System.out.println(i + 1 + " )  " + get.getNome() + "       " + get.getTentativas() + "           " + get.getAcertos());
        }

    }

}
