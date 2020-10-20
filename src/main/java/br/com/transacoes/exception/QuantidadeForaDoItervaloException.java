package br.com.transacoes.exception;

public class QuantidadeForaDoItervaloException extends RuntimeException {

    public QuantidadeForaDoItervaloException(int quantidade, int min, int max) {
        super("Não é possível gerar " + quantidade + " números aleatórios diferentes " +
                "no intervalo [" + min + ", " + max + "]");
    }
}
