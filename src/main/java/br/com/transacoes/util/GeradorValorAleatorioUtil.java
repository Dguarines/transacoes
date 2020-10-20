package br.com.transacoes.util;

import br.com.transacoes.exception.QuantidadeForaDoItervaloException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class GeradorValorAleatorioUtil {

    public static int gerarValorAleatorioEmIntervaloFechado(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static List<Integer> gerarListaValoresAleatoriosEmIntervaloFechado(int min, int max, int quantidadeValores) {
        verificarSeQuantidadeEhMaiorQueNumeroDeElementosDoIntervalo(min, max, quantidadeValores);

        Set<Integer> valores = new HashSet<>(quantidadeValores);

        while(valores.size() < quantidadeValores) {
            valores.add(gerarValorAleatorioEmIntervaloFechado(min, max));
        }

        return new ArrayList<>(valores);
    }

    private static void verificarSeQuantidadeEhMaiorQueNumeroDeElementosDoIntervalo(int min, int max, int quantidade) {
        int numeroDeElementosDoIntervalo = max - min + 1;

        if (quantidade > numeroDeElementosDoIntervalo) {
            throw new QuantidadeForaDoItervaloException(quantidade, min, max);
        }
    }
}
