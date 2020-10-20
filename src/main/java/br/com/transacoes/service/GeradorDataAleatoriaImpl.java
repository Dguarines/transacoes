package br.com.transacoes.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class GeradorDataAleatoriaImpl implements GeradorDataAleatoria {

    @Override
    public LocalDate gerarDataAleatoriaEmAnoMesEspecifico(int ano, int mes) {
        LocalDate inicio = gerarDataInicioDoIntervalo(ano, mes);
        LocalDate fim = gerarDataFimDoIntervalo(ano, mes);

        return between(inicio, fim);
    }

    /**
     * Fonte: https://www.baeldung.com/java-random-dates
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    private LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    private LocalDate gerarDataInicioDoIntervalo(int ano, int mes) {
        return LocalDate.of(ano, mes, 1);
    }

    private LocalDate gerarDataFimDoIntervalo(int ano, int mes) {
        return LocalDate.of(ano, mes, 1)
                        .plusMonths(1);
    }
}
