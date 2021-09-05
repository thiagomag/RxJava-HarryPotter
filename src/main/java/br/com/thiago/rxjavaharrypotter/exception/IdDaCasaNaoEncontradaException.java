package br.com.thiago.rxjavaharrypotter.exception;

public class IdDaCasaNaoEncontradaException extends RuntimeException {
    public IdDaCasaNaoEncontradaException(String idCasa) {
        super("O id da casa pesquisada, " + idCasa + ", não existe no banco de dados.");

    }
}