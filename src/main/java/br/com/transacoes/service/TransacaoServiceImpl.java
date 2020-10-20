package br.com.transacoes.service;

import br.com.transacoes.model.RegraCriacaoTransacao;
import br.com.transacoes.model.RegraCriacaoTransacaoMes;
import br.com.transacoes.model.Transacao;
import br.com.transacoes.repository.RegraCriacaoTransacaoMesRepository;
import br.com.transacoes.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {

    private static final int MENOR_VALOR_TRANSACAO = -9999999;
    private static final int MAIOR_VALOR_TRANSACAO = 9999999;

    private final RegraCriacaoTransacaoService regraCriacaoTransacaoService;
    private final RegraCriacaoTransacaoMesRepository regraCriacaoTransacaoMesRepository;
    private final TransacaoRepository transacaoRepository;
    private final GeradorTransacao geradorTransacao;

    @Override
    @Cacheable(value = "transacoes")
    public Iterable<Transacao> getTransacoesPorUsuarioAnoMes(int idUsuario, int ano, int mes) {
        Iterable<Transacao> transacoes = transacaoRepository.findByIdUsuarioAndAnoAndMes(idUsuario, ano, mes);

        if (!transacoes.iterator().hasNext()) {
            Optional<RegraCriacaoTransacaoMes> optRegraCriacaoTransacaoMes;
            optRegraCriacaoTransacaoMes = carregarRegraParaCriarTransacao(idUsuario, ano, mes);

            RegraCriacaoTransacaoMes regraCriacaoTransacaoMes = null;

            if (!optRegraCriacaoTransacaoMes.isPresent()) {
                RegraCriacaoTransacao regraCriacaoTransacao;
                regraCriacaoTransacao = regraCriacaoTransacaoService.gerarRegraCriacaoTransacao(idUsuario, ano);
                regraCriacaoTransacaoMes = regraCriacaoTransacao.getRegraPorMes(mes);
            }
            else {
                regraCriacaoTransacaoMes = optRegraCriacaoTransacaoMes.get();
            }
            transacoes = geradorTransacao.gerarTransacoesAPartirDeUmaRegraEspecifica(regraCriacaoTransacaoMes);
        }

        return transacoes;
    }

    private Optional<RegraCriacaoTransacaoMes> carregarRegraParaCriarTransacao(int idUsuario, int ano, int mes) {
        Optional<RegraCriacaoTransacaoMes> optRegraCriacaoTransacaoMes;
        optRegraCriacaoTransacaoMes = regraCriacaoTransacaoMesRepository.findByUsuarioAnoMes(idUsuario, ano, mes);
        return optRegraCriacaoTransacaoMes;
    }
}
