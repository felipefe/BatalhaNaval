package br.com.batalhanaval;

import java.util.Scanner;

/**
 *
 * @author WILL-PC
 */
public class BatalhaNaval {

    public static final int VAZIO = 0;
    public static final int NAVIO = 1;
    public static final int ERROU_TIRO = 2;
    public static final int ACERTOU_TIRO = 3;

    public static final int POSICAO_X = 0;
    public static final int POSICAO_Y = 1;

    public static String nomeJogador1, nomeJogador2;
    public static int tamanhoX, tamanhoY, quantidadeDeNavios, limiteMaximoDeNavios;
    public static int tabuleiroJogador1[][], tabuleiroJogador2[][];
    public static Scanner input = new Scanner(System.in);
    public static int naviosJogador1, naviosJogador2;

    public static void main(String[] args) throws Exception{
        ManterBatalhaNegocio.obterNomesDosJogadores();
        ManterBatalhaNegocio.obterTamanhoDosTabuleiros();
        ManterBatalhaNegocio.calcularQuantidadeMaximaDeNaviosNoJogo();
        ManterBatalhaNegocio.iniciandoOsTamanhosDosTabuleiros();
        ManterBatalhaNegocio.obterQuantidadeDeNaviosNoJogo();
        ManterBatalhaNegocio.instanciarContadoresDeNaviosDosJogadores();
        ManterBatalhaNegocio.inserirOsNaviosNosTabuleirosDosJogadores();
        boolean jogoAtivo = true;
        ManterBatalhaNegocio.jogandoJogo(jogoAtivo);
        
        ManterBatalhaNegocio.exibirTabuleirosDosJogadores();
        System.out.println("CLASSIFICACAO POR NUMERO DE TENTATIVAS!");
        ManterBatalhaNegocio.exibirResultado();
        input.close();
        
        
        

    }

}
