package br.com.transacoes.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class ErroValidacaoException extends RuntimeException {

    private HttpStatus httpStatus;
    private Map erros;

    public ErroValidacaoException(Map erros) {
        this.erros = erros;
        this.httpStatus = HttpStatus.NOT_ACCEPTABLE;
    }
}
