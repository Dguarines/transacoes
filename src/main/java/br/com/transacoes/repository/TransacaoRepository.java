package br.com.transacoes.repository;

import br.com.transacoes.model.Transacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

    Optional<Iterable<Transacao>> findByIdUsuarioAndAnoAndMes(int idUsuario, int ano, int mes);
}
