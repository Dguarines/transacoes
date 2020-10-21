package br.com.transacoes.service;

import br.com.transacoes.model.RegraCriacaoTransacao;
import br.com.transacoes.model.RegraCriacaoTransacaoMes;
import br.com.transacoes.model.Transacao;
import br.com.transacoes.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static br.com.transacoes.util.DuplicadorDeTransacoesUtil.duplicarTransacao;
import static br.com.transacoes.util.GeradorDataAleatoriaUtil.gerarDataAleatoriaEmAnoMesEspecifico;
import static br.com.transacoes.util.GeradorDeTextoUtil.gerarTextoAleatorioLegivel;
import static br.com.transacoes.util.GeradorValorAleatorioUtil.gerarListaValoresAleatoriosEmIntervaloFechado;
import static br.com.transacoes.util.GeradorValorAleatorioUtil.gerarValorAleatorioEmIntervaloFechado;

@Service
@RequiredArgsConstructor
public class GeradorTransacaoImpl implements GeradorTransacao {

    private static final int VALOR_MINIMO_TRANSACAO = -9999999;
    private static final int VALOR_MAXIMO_TRANSACAO = 9999999;

    private final TransacaoRepository transacaoRepository;

    @Override
    public Iterable<Transacao> gerarTransacoesAPartirDeUmaRegraEspecifica(RegraCriacaoTransacaoMes regraCriacaoTransacaoMes) {
        List<Transacao> transacoes = criarTransacoes(regraCriacaoTransacaoMes);

        if (regraCriacaoTransacaoMes.isDevePossuirDuplicidade()) {
            transacoes.addAll(duplicarTransacoes(transacoes));
        }
        return transacaoRepository.saveAll(transacoes);
    }

    private List<Transacao> duplicarTransacoes(List<Transacao> transacoes) {
        int quantidadeTransacoesDuplicadas = gerarValorAleatorioEmIntervaloFechado(1, transacoes.size());
        int minIndice = 0;
        int maxIndice = transacoes.size() - 1;
        List<Integer> indicesParaDuplicar;
        indicesParaDuplicar = gerarListaValoresAleatoriosEmIntervaloFechado(minIndice, maxIndice, quantidadeTransacoesDuplicadas);
        List<Transacao> duplicatas = new ArrayList<>();

        for (int indice : indicesParaDuplicar) {
            Transacao transacao = transacoes.get(indice);
            duplicatas.addAll(duplicarTransacao(transacao));
        }
        return duplicatas;
    }

    private List<Transacao> criarTransacoes(RegraCriacaoTransacaoMes regraCriacaoTransacaoMes) {
        return IntStream.rangeClosed(1, regraCriacaoTransacaoMes.getQuantidadeTransacoes())
                        .mapToObj(item -> criarTransacao(regraCriacaoTransacaoMes))
                        .collect(Collectors.toList());
    }

    private Transacao criarTransacao(RegraCriacaoTransacaoMes regraCriacaoTransacaoMes) {
        RegraCriacaoTransacao regraCriacaoTransacao = regraCriacaoTransacaoMes.getRegraCriacaoTransacao();
        return Transacao.builder()
                .idUsuario(regraCriacaoTransacao.getIdUsuario())
                .ano(regraCriacaoTransacao.getAno())
                .mes(regraCriacaoTransacaoMes.getMes())
                .descricao(gerarTextoAleatorioLegivel())
                .valor(gerarValorAleatorioEmIntervaloFechado(VALOR_MINIMO_TRANSACAO, VALOR_MAXIMO_TRANSACAO))
                .data(getDataAleatoriaParaNovaTransacao(regraCriacaoTransacaoMes, regraCriacaoTransacao))
                .build();
    }

    private long getDataAleatoriaParaNovaTransacao(RegraCriacaoTransacaoMes regraCriacaoTransacaoMes, RegraCriacaoTransacao regraCriacaoTransacao) {
        int ano = regraCriacaoTransacao.getAno();
        int mes = regraCriacaoTransacaoMes.getMes();
        LocalDate data = gerarDataAleatoriaEmAnoMesEspecifico(ano, mes);
        return data.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
