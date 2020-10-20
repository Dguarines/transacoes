package br.com.transacoes.service;

import br.com.transacoes.model.RegraCriacaoTransacaoMes;
import br.com.transacoes.model.Transacao;

public interface GeradorTransacao {

    Iterable<Transacao> gerarTransacoesAPartirDeUmaRegraEspecifica(RegraCriacaoTransacaoMes regraCriacaoTransacaoMes);
}
