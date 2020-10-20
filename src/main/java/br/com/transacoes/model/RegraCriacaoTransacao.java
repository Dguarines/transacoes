package br.com.transacoes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "regra_criacao_transacao")
public class RegraCriacaoTransacao {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "id_usuario")
    private int idUsuario;

    @JsonIgnore
    private int ano;

    @OneToMany(mappedBy = "regraCriacaoTransacao", cascade = CascadeType.ALL)
    private List<RegraCriacaoTransacaoMes> regrasPorMes;

    public RegraCriacaoTransacaoMes getRegraPorMes(int mes) {
        return regrasPorMes.stream().filter(item -> item.getMes() == mes).findFirst().get();
    }
}
