package br.com.transacoes.service;

import br.com.transacoes.exception.ErroValidacaoException;
import br.com.transacoes.model.RegraCriacaoTransacaoMes;
import br.com.transacoes.model.Transacao;
import br.com.transacoes.repository.RegraCriacaoTransacaoMesRepository;
import br.com.transacoes.repository.TransacaoRepository;
import br.com.transacoes.util.ValidadorConjuntoDeDadosUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static br.com.transacoes.util.ValidadorConjuntoDeDadosUtil.validarConjuntoDeDados;

@Service
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {

    private final RegraCriacaoTransacaoService regraCriacaoTransacaoService;
    private final RegraCriacaoTransacaoMesRepository regraCriacaoTransacaoMesRepository;
    private final TransacaoRepository transacaoRepository;
    private final GeradorTransacao geradorTransacao;

    @Override
    @Cacheable(value = "transacoes")
    public Iterable<Transacao> buscarTransacoesPorUsuarioAnoMes(int idUsuario, int ano, int mes) {
        validarConjuntoDeDados(idUsuario, ano, mes);

        return transacaoRepository.findByIdUsuarioAndAnoAndMes(idUsuario, ano, mes)
                                  .orElse(cadastrarTransacoes(idUsuario, ano, mes));
    }

    private Iterable<Transacao> cadastrarTransacoes(int idUsuario, int ano, int mes) {
        Optional<RegraCriacaoTransacaoMes> regraCriacaoTransacaoMes = carregarRegraCriacaoTransacaoMes(idUsuario, ano, mes);

        if (regraCriacaoTransacaoMes.isPresent()) {
            return geradorTransacao.gerarTransacoesAPartirDeUmaRegraEspecifica(regraCriacaoTransacaoMes.get());
        }
        else {
            return geradorTransacao.gerarTransacoesAPartirDeUmaRegraEspecifica(cadastrarRegraCriacaoTransacao(idUsuario, ano, mes));
        }
    }

    private Optional<RegraCriacaoTransacaoMes> carregarRegraCriacaoTransacaoMes(int idUsuario, int ano, int mes) {
        return regraCriacaoTransacaoMesRepository.findByUsuarioAnoMes(idUsuario, ano, mes);
    }

    private RegraCriacaoTransacaoMes cadastrarRegraCriacaoTransacao(int idUsuario, int ano, int mes) {
        return regraCriacaoTransacaoService.cadastrarRegraCriacaoTransacao(idUsuario, ano)
                                           .getRegraPorMes(mes);
    }
}
