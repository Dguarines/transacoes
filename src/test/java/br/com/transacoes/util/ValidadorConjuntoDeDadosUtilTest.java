package br.com.transacoes.util;

import br.com.transacoes.exception.ErroValidacaoException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.transacoes.util.ValidadorConjuntoDeDadosUtil.*;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
public class ValidadorConjuntoDeDadosUtilTest {

    @Test
    public void quandoInformarIdUsuarioInvalido_entaoDeveRetornarException() {
        ErroValidacaoException exception = assertThrows(ErroValidacaoException.class,
            () -> ValidadorConjuntoDeDadosUtil.validarConjuntoDeDados(999, 2020, 1));

        String mensagem = (String) exception.getErros().get("id");
        assertThat(mensagem).isEqualTo(format("Deve ser um valor inteiro entre %d e %d", MENOR_ID_USUARIO, MAIOR_ID_USUARIO));
    }

    @Test
    public void quandoInformarAnoInvalido_entaoDeveRetornarException() {
        ErroValidacaoException exception = assertThrows(ErroValidacaoException.class,
                () -> ValidadorConjuntoDeDadosUtil.validarConjuntoDeDados(1000, 1599, 1));

        String mensagem = (String) exception.getErros().get("ano");
        assertThat(mensagem).isEqualTo(format("Deve ser um valor inteiro maior que %d", MENOR_ANO));
    }

    @Test
    public void quandoInformarMesInvalido_entaoDeveRetornarException() {
        ErroValidacaoException exception = assertThrows(ErroValidacaoException.class,
                () -> ValidadorConjuntoDeDadosUtil.validarConjuntoDeDados(1000, 2020, 13));

        String mensagem = (String) exception.getErros().get("mes");
        assertThat(mensagem).isEqualTo(format("Deve ser um valor inteiro entre %d e %d", MENOR_MES, MAIOR_MES));
    }
}