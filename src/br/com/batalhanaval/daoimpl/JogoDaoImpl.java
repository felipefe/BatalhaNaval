package br.com.batalhanaval.daoimpl;

import br.com.batalhanaval.dao.ComputadorDao;
import br.com.batalhanaval.dao.JogadorDao;
import br.com.batalhanaval.dao.SessionFactory;
import br.com.batalhanaval.dao.JogoDao;
import br.com.batalhanaval.entidade.Computador;
import br.com.batalhanaval.entidade.Jogador;
import br.com.batalhanaval.entidade.Jogo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WILLIAM
 */
public class JogoDaoImpl implements JogoDao {

    private Connection conexao;

    public static JogadorDao JOGADOR_DAO = new JogadorDaoImpl();
    public static ComputadorDao COMPUTADOR_DAO = new ComputadorDaoImpl();

    @Override
    public boolean inserir(Object obj) throws Exception {
        Jogo jogo = (Jogo) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement("insert into jogo (totalTentativas, totalAcertos , dataJogo , id_jogador, id_computador) values (?, ?, ? , ? , ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, jogo.getTotalTentativas());
            statement.setInt(2, jogo.getTotalAcertos());
            statement.setDate(3, new Date(jogo.getData().getTime()));
            if (jogo.getJogador() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, jogo.getJogador().getId());
            }
            if (jogo.getComputador() == null) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, jogo.getComputador().getId());
            }
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Integer idInserido = rs.getInt(1);
                jogo.setId(idInserido);
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
        Jogo jogo = (Jogo) obj;
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "update jogo set totalTentativas = ?, totalAcertos = ?, dataJogo = ? , id_Jogador = ? , id_Computador = ? where id = ? ");
            statement.setInt(1, jogo.getTotalTentativas());
            statement.setInt(2, jogo.getTotalAcertos());
            statement.setDate(3, new Date(jogo.getData().getTime()));
            if (jogo.getJogador() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, jogo.getJogador().getId());
            }
            if (jogo.getComputador() == null) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, jogo.getComputador().getId());
            }
            statement.setInt(6, jogo.getId());
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
    public Jogo pesquisar(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "select * from jogo where id = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer totalTentativas = rs.getInt("totalTentativas");
                Integer totalAcertos = rs.getInt("totalAcertos");
                Date dataJogo = rs.getDate("dataJogo");
                Integer idJogador = rs.getInt("id_Jogador");
                Integer idComputador = rs.getInt("id_Computador");
                Jogo jogo = new Jogo();
                jogo.setTotalAcertos(totalAcertos);
                jogo.setTotalTentativas(totalTentativas);
                jogo.setJogador((Jogador) JOGADOR_DAO.pesquisar(idJogador));
                jogo.setComputador((Computador) COMPUTADOR_DAO.pesquisar(idComputador));
                jogo.setId(id);
                return jogo;
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
        List<Object> jogos = new ArrayList<>();
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "select * from jogo");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer idJogo = rs.getInt("id");
                Integer totalTentativas = rs.getInt("totalTentativas");
                Integer totalAcertos = rs.getInt("totalAcertos");
                Date dataJogo = rs.getDate("dataJogo");
                Integer idJogador = rs.getInt("id_Jogador");
                Integer idComputador = rs.getInt("id_Computador");
                Jogo jogo = new Jogo();
                jogo.setTotalAcertos(totalAcertos);
                jogo.setTotalTentativas(totalTentativas);
                jogo.setJogador((Jogador) JOGADOR_DAO.pesquisar(idJogador));
                jogo.setComputador((Computador) COMPUTADOR_DAO.pesquisar(idComputador));
                jogo.setId(idJogo);
                jogos.add(jogo);
            }
            return jogos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.close();
        }
        return jogos;
    }

    @Override
    public boolean excluir(Integer id) throws Exception {
        try {
            conexao = SessionFactory.getConnection();
            PreparedStatement statement = conexao.prepareStatement(
                    "delete from jogo where id = ? ");
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
