package br.com.transacoes.controller;

import br.com.transacoes.model.Transacao;
import br.com.transacoes.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService service;

    @GetMapping("/{id}/transacoes/{ano}/{mes}")
    public Iterable<Transacao> getTransacoes(@PathVariable("id") Integer id,
                                             @PathVariable("ano") Integer ano,
                                             @PathVariable("mes") Integer mes) {
        return service.buscarTransacoesPorUsuarioAnoMes(id, ano, mes);
    }
}
