package br.com.transacoes.service;

import java.time.LocalDate;

public interface GeradorDataAleatoria {
    
    LocalDate gerarDataAleatoriaEmAnoMesEspecifico(int ano, int mes);
}
