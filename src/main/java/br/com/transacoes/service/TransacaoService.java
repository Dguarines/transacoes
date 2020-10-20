package br.com.transacoes.service;

import br.com.transacoes.model.Transacao;

public interface TransacaoService {

    Iterable<Transacao> getTransacoesPorUsuarioAnoMes(int idUsuario, int ano, int mes);
}
