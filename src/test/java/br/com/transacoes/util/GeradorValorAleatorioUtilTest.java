package br.com.transacoes.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class GeradorValorAleatorioUtilTest {

    @Test
    public void quandoGerarValorAleatorio_entaoEsteDeveEstarDentroDosLimites() {
        int min = 1;
        int max = 1000;

        int valorGerado = GeradorValorAleatorioUtil.gerarValorAleatorioEmIntervaloFechado(min, max);
        Assertions.assertThat(valorGerado).isBetween(min, max);
    }

    @Test
    public void quandoGerarListaDeValoresAleatorios_entaoListaDeveTerQuantidadeCorretaDeItens() {
        int min = 1;
        int max = 1000;
        int quantidadeValores = 70;

        List<Integer> listaGerada = GeradorValorAleatorioUtil.gerarListaValoresAleatoriosEmIntervaloFechado(min, max, quantidadeValores);
        Assertions.assertThat(listaGerada).hasSize(quantidadeValores);
    }

    @Test
    public void quandoGerarListaDeValoresAleatorios_entaoElementosDaListaDevemEstarDentroDosLimites() {
        int min = 1;
        int max = 1000;
        int quantidadeValores = 70;

        List<Integer> listaGerada = GeradorValorAleatorioUtil.gerarListaValoresAleatoriosEmIntervaloFechado(min, max, quantidadeValores);
        Assertions.assertThat(listaGerada).allMatch(item -> item >= min && item <= max);
    }
}