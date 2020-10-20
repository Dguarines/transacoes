package br.com.transacoes.repository;

import br.com.transacoes.model.Transacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

    Iterable<Transacao> findByIdUsuarioAndAnoAndMes(int idUsuario, int ano, int mes);
}
