package br.com.transacoes.util;

import br.com.transacoes.model.Transacao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static br.com.transacoes.util.GeradorValorAleatorioUtil.gerarValorAleatorioEmIntervaloFechado;

public class DuplicadorDeTransacoesUtil {

    private static final int QTD_MINIMA_DUPLICATAS = 1;
    private static final int QTD_MAXIMA_DUPLICATAS = 5;

    public static List<Transacao> duplicarTransacao(Transacao transacao) {
        int qtdDuplicatas = getQtdDuplicatas();

        return IntStream.rangeClosed(1, qtdDuplicatas)
                        .mapToObj(indice -> transacao.toBuilder().duplicated(true).build())
                        .collect(Collectors.toList());
    }

    private static int getQtdDuplicatas() {
        return gerarValorAleatorioEmIntervaloFechado(QTD_MINIMA_DUPLICATAS, QTD_MAXIMA_DUPLICATAS);
    }
}
