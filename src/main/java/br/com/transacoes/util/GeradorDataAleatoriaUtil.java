package br.com.transacoes.util;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class GeradorDataAleatoriaUtil {

    public static LocalDate gerarDataAleatoriaEmAnoMesEspecifico(int ano, int mes) {
        LocalDate inicio = getDataInicioDoIntervalo(ano, mes);
        LocalDate fim = getDataFimDoIntervalo(ano, mes);

        return between(inicio, fim);
    }

    /**
     * Fonte: https://www.baeldung.com/java-random-dates
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    private static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    private static LocalDate getDataInicioDoIntervalo(int ano, int mes) {
        return LocalDate.of(ano, mes, 1);
    }

    private static LocalDate getDataFimDoIntervalo(int ano, int mes) {
        return LocalDate.of(ano, mes, 1)
                        .plusMonths(1);
    }
}
