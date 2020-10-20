package br.com.transacoes.service;

import br.com.transacoes.exception.QuantidadeForaDoItervaloException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GeradorValorAleatorioImpl implements GeradorValorAleatorio {

    private static final Random random = new Random();

    @Override
    public int gerarValorAleatorioEmIntervaloFechado(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    @Override
    public List<Integer> gerarListaValoresAleatoriosDiferentesEmIntervaloFechado(int min, int max, int quantidade) {
        if (quantidade > (max - min + 1)) {
            throw new QuantidadeForaDoItervaloException(quantidade, min, max);
        }
        Set<Integer> valores = new HashSet<>(quantidade);
        do {
            valores.add(gerarValorAleatorioEmIntervaloFechado(min, max));
        } while (valores.size() < quantidade);

        return new ArrayList<>(valores);
    }
}
