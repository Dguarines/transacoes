package br.com.transacoes.util;

import br.com.transacoes.model.Transacao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
class DuplicadorDeTransacoesUtilTest {

    @Test
    public void quandoDuplicarTransacao_entaoDeveGerarPeloMenosUmaDuplicata() {
        Transacao transacao = Transacao.builder()
                .idUsuario(1).ano(2020)
                .mes(1).descricao("Teste")
                .valor(100).data(123456).build();

        assertThat(DuplicadorDeTransacoesUtil.duplicarTransacao(transacao).size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void quandoDuplicarTransacao_entaoTodasDuplicatasDevemPossuirCampoDuplicatedIgualATrue() {
        Transacao transacao = Transacao.builder()
                .idUsuario(1).ano(2020)
                .mes(1).descricao("Teste")
                .valor(100).data(123456).build();

        List<Transacao> duplicatas = DuplicadorDeTransacoesUtil.duplicarTransacao(transacao);

        assertThat(duplicatas).allMatch(item -> item.isDuplicated());
    }
}