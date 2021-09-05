package br.com.thiago.rxjavaharrypotter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NomeDoAlunoNaoEncontradoException extends RuntimeException {
    public NomeDoAlunoNaoEncontradoException(String nome) {
        super("O nome de aluno, " + nome + " não se encontra no banco de dados");
    }
}
