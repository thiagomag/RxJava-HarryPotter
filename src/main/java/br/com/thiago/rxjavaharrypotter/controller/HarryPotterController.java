package br.com.thiago.rxjavaharrypotter.controller;

import br.com.thiago.rxjavaharrypotter.service.HarryPotterService;
import br.com.thiago.rxjavaharrypotter.request.AlunoRequest;
import br.com.thiago.rxjavaharrypotter.response.AlunoResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class HarryPotterController {

    private final HarryPotterService harryPotterService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/adicionar")
    public Single<AlunoResponse> adicionarAluno(@RequestBody AlunoRequest alunoRequest) {
        return harryPotterService.adicionar(alunoRequest);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/buscarPorId/{idAluno}")
    public Single<AlunoResponse> buscarPorIdAluno(@PathVariable String idAluno) {
        return harryPotterService.buscarPorIdAluno(idAluno);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/buscarTodos")
    public Observable<Object> buscarTodosAlunos() {
        return harryPotterService.buscarTodosAlunos();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/buscarPorNome/{nome}")
    public Observable<Object> buscarPorNome(@PathVariable String nome) {
        return harryPotterService.buscarPorNome(nome);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("buscarPorIdCasa/{idCasa}")
    public Observable<Object> buscarPorIdCasa(@PathVariable String idCasa) {
        return harryPotterService.buscarPorIdCasa(idCasa);
    }
}
