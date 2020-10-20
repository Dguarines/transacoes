package br.com.transacoes.service;

import br.com.transacoes.model.RegraCriacaoTransacao;
import br.com.transacoes.model.RegraCriacaoTransacaoMes;
import br.com.transacoes.model.Transacao;
import br.com.transacoes.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeradorTransacaoImpl implements GeradorTransacao {

    private static final int TAMANHO_MINIMO_DESCRICAO = 10;
    private static final int TAMANHO_MAXIMO_DESCRICAO = 60;
    private static final int VALOR_MINIMO_TRANSACAO = -9999999;
    private static final int VALOR_MAXIMO_TRANSACAO = 9999999;

    private final TransacaoRepository transacaoRepository;
    private final GeradorTextoAleatorioLegivel geradorTexto;
    private final GeradorValorAleatorio geradorValor;
    private final GeradorDataAleatoria geradorData;

    @Override
    public Iterable<Transacao> gerarTransacoesAPartirDeUmaRegraEspecifica(RegraCriacaoTransacaoMes regraCriacaoTransacaoMes) {
        List<Transacao> transacoes = criarTransacoes(regraCriacaoTransacaoMes);

        if (regraCriacaoTransacaoMes.isDevePossuirDuplicidade()) {
            transacoes.addAll(duplicarTransacoes(transacoes));
        }
        return transacaoRepository.saveAll(transacoes);
    }

    private List<Transacao> duplicarTransacoes(List<Transacao> transacoes) {
        int quantidadeTransacoesDuplicadas = geradorValor.gerarValorAleatorioEmIntervaloFechado(1, transacoes.size());
        int minIndice = 0;
        int maxIndice = transacoes.size() - 1;
        List<Integer> indicesParaDuplicar;
        indicesParaDuplicar = geradorValor.gerarListaValoresAleatoriosDiferentesEmIntervaloFechado(minIndice, maxIndice,
                                                                                        quantidadeTransacoesDuplicadas);
        List<Transacao> duplicatas = new ArrayList<>();

        for (int indice : indicesParaDuplicar) {
            Transacao transacao = transacoes.get(indice);
            duplicatas.addAll(duplicarTransacao(transacao));
        }
        return duplicatas;
    }

    private List<Transacao> duplicarTransacao(Transacao transacao) {
        List<Transacao> resultado = new ArrayList<>();

        int qtdDuplicatas = geradorValor.gerarValorAleatorioEmIntervaloFechado(1, 5);

        for (int i = 0; i < qtdDuplicatas; i++) {
            Transacao duplicata = new Transacao();
            BeanUtils.copyProperties(transacao, duplicata);
            duplicata.setDuplicated(true);
            resultado.add(duplicata);
        }
        return resultado;
    }

    private List<Transacao> criarTransacoes(RegraCriacaoTransacaoMes regraCriacaoTransacaoMes) {
        List<Transacao> transacoes = new ArrayList<>();

        for (int i = 0; i < regraCriacaoTransacaoMes.getQuantidadeTransacoes(); i ++) {
            Transacao transacao = criarTransacao(regraCriacaoTransacaoMes);
            transacoes.add(transacao);
        }
        return transacoes;
    }

    private Transacao criarTransacao(RegraCriacaoTransacaoMes regraCriacaoTransacaoMes) {
        RegraCriacaoTransacao regraCriacaoTransacao = regraCriacaoTransacaoMes.getRegraCriacaoTransacao();
        int ano = regraCriacaoTransacao.getAno();
        int mes = regraCriacaoTransacaoMes.getMes();

        LocalDate data = geradorData.gerarDataAleatoriaEmAnoMesEspecifico(ano, mes);

        Transacao transacao = new Transacao();
        transacao.setIdUsuario(regraCriacaoTransacao.getIdUsuario());
        transacao.setAno(ano);
        transacao.setMes(mes);
        transacao.setDescricao(geradorTexto.gerarTextoAleatorioLegivel(TAMANHO_MINIMO_DESCRICAO, TAMANHO_MAXIMO_DESCRICAO));
        transacao.setValor(geradorValor.gerarValorAleatorioEmIntervaloFechado(VALOR_MINIMO_TRANSACAO, VALOR_MAXIMO_TRANSACAO));
        transacao.setData(data.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        transacao.setDuplicated(false);

        return transacao;
    }

    public static void main(String[] args) {
        System.out.println(new Date().getTime());
        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());
        System.out.println(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

}
