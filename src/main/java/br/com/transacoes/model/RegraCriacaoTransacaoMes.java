package br.com.transacoes.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "regra_criacao_transacao_mes")
public class RegraCriacaoTransacaoMes {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_regra_criacao_transacao")
    private RegraCriacaoTransacao regraCriacaoTransacao;

    private int mes;

    @Column(name = "quantidade_transacoes")
    private int quantidadeTransacoes;

    @Column(name = "deve_possuir_duplicidade")
    private boolean devePossuirDuplicidade;
}
