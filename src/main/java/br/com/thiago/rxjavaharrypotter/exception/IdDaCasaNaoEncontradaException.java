package br.com.thiago.rxjavaharrypotter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdDaCasaNaoEncontradaException extends RuntimeException {
    public IdDaCasaNaoEncontradaException(String idCasa) {
        super("O id da casa pesquisada, " + idCasa + ", não existe no banco de dados.");

    }
}