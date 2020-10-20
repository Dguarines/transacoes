package br.com.transacoes.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GeradorDeTextoUtilTest {

    @Test
    public void quandoGerarTexto_entaoOTextoDevePossuirTamanhoDentroDosLimites() {
        int tamanhoMinimo = 10;
        int tamanhoMaximo = 60;

        String textoGerado = GeradorDeTextoUtil.gerarTextoAleatorioLegivel(tamanhoMinimo, tamanhoMaximo);
        Assertions.assertThat(textoGerado).hasSizeBetween(tamanhoMinimo, tamanhoMaximo);
    }
}