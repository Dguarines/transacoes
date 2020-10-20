package br.com.transacoes.service;

import java.util.List;

public interface GeradorValorAleatorio {

    int gerarValorAleatorioEmIntervaloFechado(int min, int max);

    List<Integer> gerarListaValoresAleatoriosDiferentesEmIntervaloFechado(int min, int max, int quantidadeValores);
}
