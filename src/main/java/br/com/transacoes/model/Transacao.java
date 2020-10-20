package br.com.transacoes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Transacao {

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @JsonIgnore
    @Column(name = "id_usuario")
    private int idUsuario;

    @JsonIgnore
    private int ano;

    @JsonIgnore
    private int mes;

    private String descricao;

    private long data;

    private int valor;

    private boolean duplicated;
}
