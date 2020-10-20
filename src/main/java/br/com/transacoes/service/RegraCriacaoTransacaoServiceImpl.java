package br.com.transacoes.service;

import br.com.transacoes.model.RegraCriacaoTransacao;
import br.com.transacoes.model.RegraCriacaoTransacaoMes;
import br.com.transacoes.repository.RegraCriacaoTransacaoRepository;
import br.com.transacoes.service.RegraCriacaoTransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static br.com.transacoes.util.GeradorValorAleatorioUtil.gerarListaValoresAleatoriosEmIntervaloFechado;
import static br.com.transacoes.util.GeradorValorAleatorioUtil.gerarValorAleatorioEmIntervaloFechado;

@Service
@RequiredArgsConstructor
public class RegraCriacaoTransacaoServiceImpl implements RegraCriacaoTransacaoService {

    private static final int JANEIRO = 1;
    private static final int DEZEMBRO = 12;
    private static final int QTD_MINIMA_TRANSACOES_POR_MES = 1;
    private static final int QTD_MAXIMA_TRANSACOES_POR_MES = 6;
    private static final int QTD_MINIMA_MESES_COM_DUPLICIDADE = 3;
    private static final int QTD_MAXIMA_MESES_COM_DUPLICIDADE = 12;

    private final RegraCriacaoTransacaoRepository repository;

    @Override
    public RegraCriacaoTransacao cadastrarRegraCriacaoTransacao(int idUsuario, int ano) {
        RegraCriacaoTransacao regraCriacaoTransacao = new RegraCriacaoTransacao();
        regraCriacaoTransacao.setIdUsuario(idUsuario);
        regraCriacaoTransacao.setAno(ano);
        regraCriacaoTransacao.setRegrasPorMes(gerarRegrasCriacaoTransacaoPorMes(regraCriacaoTransacao));

        return repository.save(regraCriacaoTransacao);
    }

    private List<Integer> gerarMesesQueDeveraoPossuirDuplicidade() {
        int qtdDeMesesQueDevemPossuirDuplicidades = getQtdDeMesesQueDevemPossuirDuplicidades();
        return gerarListaValoresAleatoriosEmIntervaloFechado(JANEIRO, DEZEMBRO, qtdDeMesesQueDevemPossuirDuplicidades);
    }

    private List<RegraCriacaoTransacaoMes> gerarRegrasCriacaoTransacaoPorMes(RegraCriacaoTransacao regraCriacaoTransacao) {
        List<Integer> mesesQueDeveraoPossuirDuplicidade = gerarMesesQueDeveraoPossuirDuplicidade();

        return IntStream.rangeClosed(JANEIRO, DEZEMBRO)
                .mapToObj(mes -> instanciarRegraCriacaoTransacaoMes(mes, regraCriacaoTransacao, mesesQueDeveraoPossuirDuplicidade))
                .collect(Collectors.toList());
    }

    private RegraCriacaoTransacaoMes instanciarRegraCriacaoTransacaoMes(int mes,
                                                                        RegraCriacaoTransacao regraCriacaoTransacao,
                                                                        List<Integer> mesesQueDeveraoPossuirDuplicidade) {
        return RegraCriacaoTransacaoMes.builder()
                .mes(mes)
                .regraCriacaoTransacao(regraCriacaoTransacao)
                .quantidadeTransacoes(getQtdDeTransacoesQueDevemSerCriadas())
                .devePossuirDuplicidade(mesesQueDeveraoPossuirDuplicidade.contains(mes))
                .build();
    }

    private int getQtdDeTransacoesQueDevemSerCriadas() {
        return gerarValorAleatorioEmIntervaloFechado(QTD_MINIMA_TRANSACOES_POR_MES, QTD_MAXIMA_TRANSACOES_POR_MES);
    }

    private int getQtdDeMesesQueDevemPossuirDuplicidades() {
        return gerarValorAleatorioEmIntervaloFechado(QTD_MINIMA_MESES_COM_DUPLICIDADE, QTD_MAXIMA_MESES_COM_DUPLICIDADE);
    }
}
