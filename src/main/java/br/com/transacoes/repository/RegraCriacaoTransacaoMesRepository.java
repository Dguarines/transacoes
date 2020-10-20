package br.com.transacoes.repository;

import br.com.transacoes.model.RegraCriacaoTransacaoMes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegraCriacaoTransacaoMesRepository extends CrudRepository<RegraCriacaoTransacaoMes, Long> {

    @Query(
        value = "SELECT " +
                "   regraMes " +
                "FROM " +
                "   RegraCriacaoTransacaoMes regraMes " +
                "   JOIN regraMes.regraCriacaoTransacao regra " +
                "WHERE " +
                "   regra.idUsuario = :idUsuario " +
                "   AND regra.ano = :ano " +
                "   AND regraMes.mes = :mes "
    )
    Optional<RegraCriacaoTransacaoMes> findByUsuarioAnoMes(int idUsuario, int ano, int mes);
}
