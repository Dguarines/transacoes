package br.com.transacoes.service;

import br.com.transacoes.model.RegraCriacaoTransacao;

public interface RegraCriacaoTransacaoService {

    RegraCriacaoTransacao cadastrarRegraCriacaoTransacao(int idUsuario, int ano);
}
