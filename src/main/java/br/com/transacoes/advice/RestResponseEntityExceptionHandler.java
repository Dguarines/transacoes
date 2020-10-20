package br.com.transacoes.advice;

import br.com.transacoes.dto.MensagemErroDTO;
import br.com.transacoes.exception.ErroValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@ControllerAdvice
@AllArgsConstructor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ErroValidacaoException.class })
    protected ResponseEntity<MensagemErroDTO> handleResourceException(ErroValidacaoException e) {
        MensagemErroDTO mensagemErroDTO = MensagemErroDTO.builder()
                .codigo(e.getHttpStatus().value())
                .descricaoCodigo(e.getHttpStatus().getReasonPhrase())
                .mensagens(e.getErros())
                .build();
        return ResponseEntity.status(e.getHttpStatus()).body(mensagemErroDTO);
    }

    @ExceptionHandler(value = { Exception.class })
    private ResponseEntity<MensagemErroDTO> handleException(Exception e) {
        MensagemErroDTO mensagemErroDTO = MensagemErroDTO.builder()
                .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .descricaoCodigo(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .mensagens(Collections.singletonMap("erro", "Erro não esperado"))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensagemErroDTO);
    }
}
