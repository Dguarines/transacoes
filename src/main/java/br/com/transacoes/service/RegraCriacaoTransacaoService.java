package br.com.transacoes.service;

import br.com.transacoes.model.RegraCriacaoTransacao;

public interface RegraCriacaoTransacaoService {

    RegraCriacaoTransacao gerarRegraCriacaoTransacao(int idUsuario, int ano);
}
