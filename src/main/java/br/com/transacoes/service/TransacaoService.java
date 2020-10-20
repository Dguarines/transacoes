package br.com.transacoes.service;

import br.com.transacoes.model.Transacao;

public interface TransacaoService {

    Iterable<Transacao> buscarTransacoesPorUsuarioAnoMes(int idUsuario, int ano, int mes);
}
