package br.com.transacoes.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Me baseei na ideia desse link: https://gist.github.com/sepehr/3371339
 */
@Service
public class GeradorTextoAleatorioLegivelImpl implements GeradorTextoAleatorioLegivel {

    private static final String VOGAIS[] = new String[]{"a", "e", "i", "o", "u"};
    private static final String CONSOANTES_SEM_LETRA_Q[] = new String[]{"b", "c", "d", "f", "g",
                                                            "h", "j", "l", "m",
                                                            "n", "p", "r", "s",
                                                            "t", "v", "x", "z"};

    private static final List<String> SILABAS = gerarSilabas();
    private static final String ESPACO = " ";
    private static final int TAMANHO_MAX_SILABA = 3;
    private static final int TAMANHO_MIN_PALAVRA = 5;
    private static final int TAMANHO_MAX_PALAVRA = 14;


    @Override
    public String gerarTextoAleatorioLegivel(int tamanhoMinimo, int tamanhoMaximo) {
        StringBuilder resultado = new StringBuilder();
        int tamanho = (int) (tamanhoMinimo + Math.random() * (tamanhoMaximo - tamanhoMinimo));

        String palavra = getPalavra();
        resultado.append(palavra);

        if (resultado.length() < tamanho) {
            palavra = getPalavra();

            while (resultado.length() + ESPACO.length() + palavra.length() <= tamanho) {
                resultado.append(ESPACO).append(palavra);
                palavra = getPalavra();
            }
        }

        return resultado.toString();
    }

    private String getPalavra() {
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
        silabas.add("que");
        silabas.add("qui");

        for (int i = 0; i < CONSOANTES_SEM_LETRA_Q.length; i++) {
            for (int j = 0; j < VOGAIS.length; j++) {
                silabas.add(CONSOANTES_SEM_LETRA_Q[i] + VOGAIS[j]);
            }
        }
        return silabas;
    }
}
