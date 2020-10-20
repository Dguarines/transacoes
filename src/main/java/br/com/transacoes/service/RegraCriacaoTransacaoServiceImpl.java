package br.com.transacoes.service;

import br.com.transacoes.model.RegraCriacaoTransacao;
import br.com.transacoes.model.RegraCriacaoTransacaoMes;
import br.com.transacoes.repository.RegraCriacaoTransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegraCriacaoTransacaoServiceImpl implements RegraCriacaoTransacaoService {

    private final GeradorValorAleatorio geradorValorAleatorio;
    private final RegraCriacaoTransacaoRepository repository;

    @Override
    public RegraCriacaoTransacao gerarRegraCriacaoTransacao(int idUsuario, int ano) {
        List<Integer> mesesComDuplicidades = gerarMesesQueDeveraoPossuirDuplicidade();
        RegraCriacaoTransacao regraCriacaoTransacao = new RegraCriacaoTransacao();
        regraCriacaoTransacao.setIdUsuario(idUsuario);
        regraCriacaoTransacao.setAno(ano);

        List<RegraCriacaoTransacaoMes> regrasDeCriacaoPorMes;
        regrasDeCriacaoPorMes = gerarRegrasDeCriacaoTransacaoPorMes(regraCriacaoTransacao, mesesComDuplicidades);
        regraCriacaoTransacao.setRegrasPorMes(regrasDeCriacaoPorMes);

        return repository.save(regraCriacaoTransacao);
    }

    public List<Integer> gerarMesesQueDeveraoPossuirDuplicidade() {
        int quantidadeMesesComDuplicidades = geradorValorAleatorio.gerarValorAleatorioEmIntervaloFechado(3, 12);
        return geradorValorAleatorio.gerarListaValoresAleatoriosDiferentesEmIntervaloFechado(1, 12,
                                                                                        quantidadeMesesComDuplicidades);
    }

    public List<RegraCriacaoTransacaoMes> gerarRegrasDeCriacaoTransacaoPorMes(RegraCriacaoTransacao regraCriacaoTransacao, List<Integer> mesesComDuplicidade) {
        List<RegraCriacaoTransacaoMes> regrasPorMes = new ArrayList<>();

        for (int mes = 1; mes <= 12; mes++) {
            RegraCriacaoTransacaoMes regraCriacaoTransacaoMes = new RegraCriacaoTransacaoMes();
            regraCriacaoTransacaoMes.setMes(mes);
            regraCriacaoTransacaoMes.setRegraCriacaoTransacao(regraCriacaoTransacao);
            regraCriacaoTransacaoMes.setQuantidadeTransacoes(geradorValorAleatorio.gerarValorAleatorioEmIntervaloFechado(1, 12));
            regraCriacaoTransacaoMes.setDevePossuirDuplicidade(mesesComDuplicidade.contains(mes));
            regrasPorMes.add(regraCriacaoTransacaoMes);
        }
        return regrasPorMes;
    }
}
