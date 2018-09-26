package br.com.batalhanaval;

import static br.com.batalhanaval.BatalhaNaval.naviosJogador2;
import static br.com.batalhanaval.BatalhaNaval.tamanhoX;
import br.com.batalhanaval.dao.ComputadorDao;
import br.com.batalhanaval.dao.JogadorDao;
import br.com.batalhanaval.dao.JogoDao;
import br.com.batalhanaval.daoimpl.ComputadorDaoImpl;
import br.com.batalhanaval.daoimpl.JogadorDaoImpl;
import br.com.batalhanaval.daoimpl.JogoDaoImpl;
import br.com.batalhanaval.entidade.Computador;
import br.com.batalhanaval.entidade.Jogador;
import br.com.batalhanaval.entidade.Jogo;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;

/**
 *
 * @author William
 */
public class ManterBatalhaNegocio {

    // VARIAVEIS GLOBAIS DAS INTERFACES //
    public static JogadorDao jogadorDao = new JogadorDaoImpl();
    public static ComputadorDao computadorDao = new ComputadorDaoImpl();
    public static JogoDao jogoDao = new JogoDaoImpl();

    // VARIAVEIS DO JOGADOR //
    public static Jogador JOGADOR_ATUAL;
    public static String NOME_JOGADOR = null;
    public static Integer TENTATIVAS_JOGADOR = 0;
    public static Integer ACERTOS_JOGADOR = 0;

    // VARIAVEIS DO COMPUTADOR // 
    public static Computador COMPUTADOR_ATUAL;
    public static String NOME_COMPUTADOR = null;
    public static Integer TENTATIVAS_COMPUTADOR = 0;
    public static Integer ACERTOS_COMPUTADOR = 0;

    // VARIAVEIS DO JOGO //
    public static Integer TENTATIVAS_TOTAIS_JOGO = 0;
    public static Integer ACERTOS_TOTAIS_JOGO = 0;
    public static Date DATA_JOGO = new Date();

    public static void obterTamanhoDosTabuleiros() {

        for (;;) {
            boolean tudoOk = false;
            try {
                System.out.println("Digite a altura do tabuleiro MAX[9]: ");
                BatalhaNaval.tamanhoX = BatalhaNaval.input.nextInt();
                System.out.println("Digite o comprimento do tabuleiro MAX[9]: ");
                BatalhaNaval.tamanhoY = BatalhaNaval.input.nextInt();
                tudoOk = true;
            } catch (Exception erro) {
                System.out.println("Digite um valor numérico");
                tudoOk = false;
            }
            if (tudoOk) {
                if (BatalhaNaval.tamanhoX <= 9 && BatalhaNaval.tamanhoX > 0 && BatalhaNaval.tamanhoY <= 9 && BatalhaNaval.tamanhoY > 0) {
                    break;
                } else {
                    System.out.println("Numero Invalido do tabuleiro atingido, Digite novamente!");
                }

            }
        }
    }

    public static void obterNomesDosJogadores() {
        System.out.println("Digite seu nome: ");
        BatalhaNaval.nomeJogador1 = BatalhaNaval.input.next();
        NOME_JOGADOR = BatalhaNaval.nomeJogador1;
        String nomeComputador = "Computador";
        BatalhaNaval.nomeJogador2 = nomeComputador;
        NOME_COMPUTADOR = BatalhaNaval.nomeJogador2;
    }

    public static void calcularQuantidadeMaximaDeNaviosNoJogo() {
        BatalhaNaval.limiteMaximoDeNavios = (tamanhoX * BatalhaNaval.tamanhoY) / 5;
    }

    public static void iniciandoOsTamanhosDosTabuleiros() {
        BatalhaNaval.tabuleiroJogador1 = retornarNovoTabuleiroVazio();
        BatalhaNaval.tabuleiroJogador2 = retornarNovoTabuleiroVazio();
    }

    public static int[][] retornarNovoTabuleiroVazio() {
        return new int[tamanhoX][BatalhaNaval.tamanhoY];
    }

    public static void obterQuantidadeDeNaviosNoJogo() {
        for (;;) {
            Boolean tudoCerto = false;
            try {

                System.out.println("Digite a quantidade de navios do jogo:");
                System.out.println("Max: " + BatalhaNaval.limiteMaximoDeNavios + " navios");
                BatalhaNaval.quantidadeDeNavios = BatalhaNaval.input.nextInt();
                tudoCerto = true;
            } catch (InputMismatchException erro) {
                System.out.println("Digite um valor numérico");
            }
            if (BatalhaNaval.quantidadeDeNavios > BatalhaNaval.limiteMaximoDeNavios) {
                BatalhaNaval.quantidadeDeNavios = BatalhaNaval.limiteMaximoDeNavios;
            }
            if (tudoCerto) {
                if (BatalhaNaval.quantidadeDeNavios > 0) {
                    break;

                } else {
                    System.out.println("A Quantidade navios é invalida , Digite novamente.");
                }
            }

        }
    }

    public static void instanciarContadoresDeNaviosDosJogadores() {
        BatalhaNaval.naviosJogador1 = BatalhaNaval.quantidadeDeNavios;
        BatalhaNaval.naviosJogador2 = BatalhaNaval.quantidadeDeNavios;
    }

    public static int[][] retornarNovoTabuleiroComOsNavios() {
        int novoTabuleiro[][] = retornarNovoTabuleiroVazio();
        int quantidadeRestanteDeNavios = BatalhaNaval.quantidadeDeNavios;
        int x = 0, y = 0;
        Random numeroAleatorio = new Random();
        do {
            x = 0;
            y = 0;
            for (int[] linha : novoTabuleiro) {
                for (int coluna : linha) {
                    if (numeroAleatorio.nextInt(100) <= 10) {
                        if (coluna == BatalhaNaval.VAZIO) {
                            novoTabuleiro[x][y] = BatalhaNaval.NAVIO;
                            quantidadeRestanteDeNavios--;
                            break;
                        }
                        if (quantidadeRestanteDeNavios < 0) {
                            break;
                        }
                    }
                    y++;
                }
                y = 0;
                x++;
                if (quantidadeRestanteDeNavios <= 0) {
                    break;
                }
            }
        } while (quantidadeRestanteDeNavios > 0);
        return novoTabuleiro;
    }

    public static void inserirOsNaviosNosTabuleirosDosJogadores() {
        BatalhaNaval.tabuleiroJogador1 = retornarNovoTabuleiroComOsNavios();
        BatalhaNaval.tabuleiroJogador2 = retornarNovoTabuleiroComOsNavios();
    }

    public static void exibirNumerosDasColunasDosTabuleiros() {
        int numeroDaColuna = 1;
        String numerosDoTabuleiro = "   ";

        for (int i = 0; i < BatalhaNaval.tamanhoY; i++) {
            numerosDoTabuleiro += (numeroDaColuna++) + " ";
        }
        System.out.println(numerosDoTabuleiro);
    }

    public static void exibirTabuleiro(String nomeDoJogador, int[][] tabuleiro, boolean seuTabuleiro) {
        System.out.println("|----- " + nomeDoJogador + " -----|");
        exibirNumerosDasColunasDosTabuleiros();
        String linhaDoTabuleiro = "";
        char letraDaLinha = 65;
        for (int[] linha : tabuleiro) {
            linhaDoTabuleiro = (letraDaLinha++) + " |";

            for (int coluna : linha) {
                switch (coluna) {
                    case BatalhaNaval.VAZIO:
                        linhaDoTabuleiro += " |";
                        break;
                    case BatalhaNaval.NAVIO:
                        if (seuTabuleiro) {
                            linhaDoTabuleiro += "N|";
                            break;
                        } else {
                            linhaDoTabuleiro += " |";
                            break;
                        }
                    case BatalhaNaval.ERROU_TIRO:
                        linhaDoTabuleiro += "X|";
                        break;

                    case BatalhaNaval.ACERTOU_TIRO:
                        linhaDoTabuleiro += "A|";
                        break;
                }
            }
            System.out.println(linhaDoTabuleiro);
        }
    }

    public static void exibirTabuleirosDosJogadores() {
        exibirTabuleiro(BatalhaNaval.nomeJogador1, BatalhaNaval.tabuleiroJogador1, true);
        exibirTabuleiro(BatalhaNaval.nomeJogador2, BatalhaNaval.tabuleiroJogador2, false);
    }

    public static boolean validarPosicoesInseridasPeloJogador(int[] posicoes) {
        boolean retorno = true;
        if (posicoes[0] > BatalhaNaval.tamanhoX - 1) {
            retorno = false;
            System.out.println("A posicao das letras não pode ser maior que " + (char) (BatalhaNaval.tamanhoX + 64));
        }

        if (posicoes[1] > BatalhaNaval.tamanhoY) {
            retorno = false;
            System.out.println("A posicao numérica não pode ser maior que " + BatalhaNaval.tamanhoY);
        }

        return retorno;
    }

    public static String receberValorDigitadoPeloJogador() {
        System.out.println("Digite a posição do seu tiro:");
        return BatalhaNaval.input.next();
    }

    public static boolean validarTiroDoJogador(String tiroDoJogador) {
        int quantidadeDeNumeros = (BatalhaNaval.tamanhoY >= 9) ? 2 : 1;
        String expressaoDeVerificacao = "^[A-Z-a-z]{1}[0-9]{"
                + quantidadeDeNumeros + "}$";
        String linha = tiroDoJogador.substring(0, 1);
        String coluna = tiroDoJogador.substring(1);
        if (!linha.matches("^[A-Z-a-z]")) {
            return false;
        }
        try {
            Integer col = Integer.valueOf(coluna);
            if (col < 1 || col > BatalhaNaval.tamanhoX) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static int[] retornarPosicoesDigitadasPeloJogador(String tiroDoJogador) {
        String tiro = tiroDoJogador.toLowerCase();
        int[] retorno = new int[2];
        retorno[BatalhaNaval.POSICAO_X] = tiro.charAt(0) - 97;
        retorno[BatalhaNaval.POSICAO_Y] = Integer.parseInt(tiro.substring(1)) - 1;
        return retorno;
    }

    public static void inserirValoresDaAcaoNoTabuleiro(int[] posicoes, int numeroDoJogador) {
        if (numeroDoJogador == 1) {
            if (BatalhaNaval.tabuleiroJogador2[posicoes[BatalhaNaval.POSICAO_X]][posicoes[BatalhaNaval.POSICAO_Y]] == BatalhaNaval.NAVIO) {
                BatalhaNaval.tabuleiroJogador2[posicoes[BatalhaNaval.POSICAO_X]][posicoes[BatalhaNaval.POSICAO_Y]] = BatalhaNaval.ACERTOU_TIRO;
                BatalhaNaval.naviosJogador2--;
                ACERTOS_JOGADOR++;
                ACERTOS_TOTAIS_JOGO++;
                System.out.println("Você acertou um navio!");
            } else {
                BatalhaNaval.tabuleiroJogador2[posicoes[BatalhaNaval.POSICAO_X]][posicoes[BatalhaNaval.POSICAO_Y]] = BatalhaNaval.ERROU_TIRO;
                System.out.println("Você errou o tiro!");
            }
        } else {
            if (BatalhaNaval.tabuleiroJogador1[posicoes[BatalhaNaval.POSICAO_X]][posicoes[BatalhaNaval.POSICAO_Y]] == BatalhaNaval.NAVIO) {
                BatalhaNaval.tabuleiroJogador1[posicoes[BatalhaNaval.POSICAO_X]][posicoes[BatalhaNaval.POSICAO_Y]] = BatalhaNaval.ACERTOU_TIRO;
                BatalhaNaval.naviosJogador1--;
                System.out.println("O Computador acertou um navio!");
                ACERTOS_COMPUTADOR++;
                ACERTOS_TOTAIS_JOGO++;
            } else {
                BatalhaNaval.tabuleiroJogador1[posicoes[BatalhaNaval.POSICAO_X]][posicoes[BatalhaNaval.POSICAO_Y]] = BatalhaNaval.ERROU_TIRO;
                System.out.println("O Computador errou o tiro!");
            }
        }
    }

    public static boolean acaoDoJogador() {
        boolean acaoValida = true;
        String tiroDoJogador = receberValorDigitadoPeloJogador();
        if (validarTiroDoJogador(tiroDoJogador)) {
            int[] posicoes = retornarPosicoesDigitadasPeloJogador(tiroDoJogador);
            if (validarPosicoesInseridasPeloJogador(posicoes)) {
                inserirValoresDaAcaoNoTabuleiro(posicoes, 1);
                TENTATIVAS_JOGADOR++;
                TENTATIVAS_TOTAIS_JOGO++;
            } else {
                acaoValida = false;
            }
        } else {
            System.out.println("Posição Invalida, JOGUE NOVAMENTE!");
            System.out.println("######################--NOVA RODADA --#######################");
            acaoValida = false;
        }
        return acaoValida;
    }

    public static void acaoDoComputador() {
        int[] posicoes = retornarJogadaDoComputador();
        inserirValoresDaAcaoNoTabuleiro(posicoes, 2);
        TENTATIVAS_COMPUTADOR++;
        TENTATIVAS_TOTAIS_JOGO++;
    }

    public static int[] retornarJogadaDoComputador() {
        int[] posicoes = new int[2];
        posicoes[BatalhaNaval.POSICAO_X] = retornarJogadaAleatoriaDoComputador(tamanhoX);
        posicoes[BatalhaNaval.POSICAO_Y] = retornarJogadaAleatoriaDoComputador(BatalhaNaval.tamanhoY);
        return posicoes;
    }

    public static int retornarJogadaAleatoriaDoComputador(int limite) {
        Random jogadaDoComputador = new Random();
        int numeroGerado = jogadaDoComputador.nextInt(limite);
        return (numeroGerado == limite) ? --numeroGerado : numeroGerado;
    }

    public static void jogandoJogo(boolean jogoAtivo) throws Exception {
        do {
            ManterBatalhaNegocio.exibirTabuleirosDosJogadores();
            if (ManterBatalhaNegocio.acaoDoJogador()) {
                if (naviosJogador2 <= 0) {
                    System.out.println(BatalhaNaval.nomeJogador1 + " venceu o jogo!");
                    COMPUTADOR_ATUAL = colocandoComputadorNoBanco();
                    JOGADOR_ATUAL = colocandoJogadorNoBanco();
                    colocandoJogoNoBanco();
                    break;
                }
                // Verifico fim do jogo
                ManterBatalhaNegocio.acaoDoComputador();
                if (BatalhaNaval.naviosJogador1 <= 0) {
                    System.out.println(BatalhaNaval.nomeJogador2 + " venceu o jogo!");
                    JOGADOR_ATUAL = colocandoJogadorNoBanco();
                    COMPUTADOR_ATUAL = colocandoComputadorNoBanco();
                    colocandoJogoNoBanco();
                    break;
                }
            }

        } while (jogoAtivo);

    }

    public static Jogador colocandoJogadorNoBanco() throws Exception {
        Jogador jog = new Jogador();
        jog.setNome(NOME_JOGADOR);
        jog.setTentativas(TENTATIVAS_JOGADOR);
        jog.setAcertos(ACERTOS_JOGADOR);
        jogadorDao.inserir(jog);
        return jog;
    }

    public static Computador colocandoComputadorNoBanco() throws Exception {
        Computador comp = new Computador();
        comp.setNome(NOME_COMPUTADOR);
        comp.setTentativas(TENTATIVAS_COMPUTADOR);
        comp.setAcertos(ACERTOS_COMPUTADOR);
        computadorDao.inserir(comp);
        return comp;
    }

    public static void colocandoJogoNoBanco() throws Exception {
        Jogo jogo = new Jogo();
        jogo.setData(DATA_JOGO);
        jogo.setComputador(COMPUTADOR_ATUAL);
        jogo.setJogador(JOGADOR_ATUAL);
        jogo.setTotalAcertos(ACERTOS_TOTAIS_JOGO);
        jogo.setTotalTentativas(TENTATIVAS_TOTAIS_JOGO);
        jogoDao.inserir(jogo);
    }

    public static void exibirResultado() throws Exception {
        jogadorDao.imprimirClassificacaoDeJogadores();
    }

}
