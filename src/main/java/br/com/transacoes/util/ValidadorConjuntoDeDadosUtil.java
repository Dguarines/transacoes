package br.com.transacoes.util;

import br.com.transacoes.exception.ErroValidacaoException;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class ValidadorConjuntoDeDadosUtil {

    private static final int MENOR_ID_USUARIO = 1000;
    private static final int MAIOR_ID_USUARIO = 100000000;
    private static final int MENOR_ANO = 1900;
    private static final int MENOR_MES = 1;
    private static final int MAIOR_MES = 12;

    public static void validarConjuntoDeDados(int idUsuario, int ano, int mes) {
        Map<String, String> erros = new HashMap<>();

        validarIdUsuario(idUsuario, erros);
        validarAno(ano, erros);
        validarMes(mes, erros);

        if (!erros.isEmpty()) {
            throw new ErroValidacaoException(erros);
        }
    }

    private static void validarMes(int mes, Map<String, String> erros) {
        if (mes < MENOR_MES || mes > MAIOR_MES) {
            erros.put("mes", format("Deve ser um valor inteiro entre %d e %d", MENOR_MES, MAIOR_MES));
        }
    }

    private static void validarAno(int ano, Map<String, String> erros) {
        if (ano < MENOR_ANO) {
            erros.put("ano", format("Deve ser um valor inteiro maior que %d", MENOR_ANO));
        }
    }

    private static void validarIdUsuario(int idUsuario, Map<String, String> erros) {
        if (idUsuario < MENOR_ID_USUARIO || idUsuario > MAIOR_ID_USUARIO) {
            erros.put("id", format("Deve ser um valor inteiro entre %d e %d", MENOR_ID_USUARIO, MAIOR_ID_USUARIO));
        }
    }
}
