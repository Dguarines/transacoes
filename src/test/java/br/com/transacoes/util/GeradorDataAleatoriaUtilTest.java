package br.com.transacoes.util;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class GeradorDataAleatoriaUtilTest {

    @Test
    public void quandoGerarDataAleatoria_entaoEstaDeveEstarNoRangeDoMesAno() {
        int ano = 2020;
        int mes = 12;
        LocalDate inicioPeriodo = LocalDate.of(ano, mes, 1);
        LocalDate fimPeriodo = LocalDate.of(ano, mes, 31);

        LocalDate dataGerada = GeradorDataAleatoriaUtil.gerarDataAleatoriaEmAnoMesEspecifico(ano, mes);

        assertThat(dataGerada).isBetween(inicioPeriodo, fimPeriodo);
    }
}