package br.com.transacoes.repository;

import br.com.transacoes.model.RegraCriacaoTransacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegraCriacaoTransacaoRepository extends CrudRepository<RegraCriacaoTransacao, Long> {
}
