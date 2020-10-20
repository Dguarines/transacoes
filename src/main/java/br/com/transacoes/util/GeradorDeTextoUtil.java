package br.com.transacoes.util;

import java.util.ArrayList;
import java.util.List;

import static br.com.transacoes.util.GeradorValorAleatorioUtil.gerarValorAleatorioEmIntervaloFechado;
import static java.util.Arrays.asList;

/**
 * Fonte: https://gist.github.com/sepehr/3371339
 */
public class GeradorDeTextoUtil {

    private static final String VOGAIS[] = new String[]{"a", "e", "i", "o", "u"};
    private static final String CONSOANTES_SEM_LETRA_Q[] = new String[]{"b", "c", "d", "f", "g",
                                                            "h", "j", "l", "m",
                                                            "n", "p", "r", "s",
                                                            "t", "v", "x", "z"};

    private static final String[] SILABAS_QUE_QUI = new String[]{"que", "qui"};
    private static final List<String> SILABAS = gerarSilabas();
    private static final String ESPACO = " ";
    private static final int TAMANHO_MAX_SILABA = 3;
    private static final int TAMANHO_MIN_PALAVRA = 5;
    private static final int TAMANHO_MAX_PALAVRA = 14;


    public static String gerarTextoAleatorioLegivel(int tamanhoMinimo, int tamanhoMaximo) {
        int tamanhoDoTexto = gerarValorAleatorioEmIntervaloFechado(tamanhoMinimo, tamanhoMaximo);
        StringBuilder texto = new StringBuilder();

        String palavra = gerarPalavraAleatoria();
        texto.append(palavra);

        if (texto.length() < tamanhoDoTexto) {
            palavra = gerarPalavraAleatoria();

            while (texto.length() + ESPACO.length() + palavra.length() <= tamanhoDoTexto) {
                texto.append(ESPACO).append(palavra);
                palavra = gerarPalavraAleatoria();
            }
        }

        return texto.toString();
    }

    private static String gerarPalavraAleatoria() {
        int tamanho = (int) (TAMANHO_MIN_PALAVRA + Math.random() * (TAMANHO_MAX_PALAVRA - TAMANHO_MIN_PALAVRA));
        StringBuilder palavra = new StringBuilder();

        while (palavra.length() + TAMANHO_MAX_SILABA <= tamanho) {
            int posicaoSilaba = (int) (Math.random() * SILABAS.size());
            String silaba = SILABAS.get(posicaoSilaba);
            palavra.append(silaba);
        }

        return palavra.toString();
    }

    private static List<String> gerarSilabas() {
        List<String> silabas = new ArrayList<>();
        silabas.addAll(asList(SILABAS_QUE_QUI));

        for (int i = 0; i < CONSOANTES_SEM_LETRA_Q.length; i++) {
            for (int j = 0; j < VOGAIS.length; j++) {
                silabas.add(CONSOANTES_SEM_LETRA_Q[i] + VOGAIS[j]);
            }
        }
        return silabas;
    }
}
