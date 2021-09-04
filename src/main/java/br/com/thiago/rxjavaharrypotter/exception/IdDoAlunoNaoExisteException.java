package br.com.thiago.rxjavaharrypotter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdDoAlunoNaoExisteException extends RuntimeException {
    public IdDoAlunoNaoExisteException(String idAluno) {
        super("O id do aluno pesquisado, " + idAluno + ", não existe no banco de dados.");
    }
}