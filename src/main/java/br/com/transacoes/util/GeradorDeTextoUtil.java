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

    private static final List<String> SILABAS = gerarSilabas();
    private static final String ESPACO = " ";
    private static final int TAMANHO_MIN_PALAVRA = 6;
    private static final int TAMANHO_MAX_PALAVRA = 14;
    private static final int TAMANHO_MINIMO_TEXTO = 10;
    private static final int TAMANHO_MAXIMO_TEXTO = 60;


    public static String gerarTextoAleatorioLegivel() {
        int tamanhoDoTexto = gerarValorAleatorioEmIntervaloFechado(TAMANHO_MINIMO_TEXTO, TAMANHO_MAXIMO_TEXTO);
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
        if (texto.length() < TAMANHO_MINIMO_TEXTO) {
            texto.append(ESPACO).append(gerarPalavraAleatoria());
        }

        return texto.toString();
    }

    private static String gerarPalavraAleatoria() {
        int tamanho = (int) (TAMANHO_MIN_PALAVRA + Math.random() * (TAMANHO_MAX_PALAVRA - TAMANHO_MIN_PALAVRA));
        StringBuilder palavra = new StringBuilder();

        String silaba = getSilabaAleatoria();
        palavra.append(silaba);

        silaba = getSilabaAleatoria();
        while (palavra.length() + silaba.length() <= tamanho) {
            palavra.append(silaba);
            silaba = getSilabaAleatoria();
        }

        return palavra.toString();
    }

    private static String getSilabaAleatoria() {
        int indiceSilaba = gerarValorAleatorioEmIntervaloFechado(0, SILABAS.size() -1);
        return SILABAS.get(indiceSilaba);
    }

    private static List<String> gerarSilabas() {
        List<String> silabas = new ArrayList<>();

        for (int i = 0; i < CONSOANTES_SEM_LETRA_Q.length; i++) {
            for (int j = 0; j < VOGAIS.length; j++) {
                silabas.add(CONSOANTES_SEM_LETRA_Q[i] + VOGAIS[j]);
            }
        }
        return silabas;
    }
}
