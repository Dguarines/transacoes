package br.com.transacoes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class MensagemErroDTO implements Serializable {

    private Integer codigo;

    @JsonProperty("descricao_codigo")
    private String descricaoCodigo;

    private Map mensagens;
}
