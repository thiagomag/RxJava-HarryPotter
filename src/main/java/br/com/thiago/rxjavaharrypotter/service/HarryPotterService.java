package br.com.thiago.rxjavaharrypotter.service;

import br.com.thiago.rxjavaharrypotter.entity.Casa;
import br.com.thiago.rxjavaharrypotter.exception.IdDaCasaNaoEncontradaException;
import br.com.thiago.rxjavaharrypotter.exception.IdDoAlunoNaoExisteException;
import br.com.thiago.rxjavaharrypotter.exception.NomeDoAlunoNaoEncontradoException;
import br.com.thiago.rxjavaharrypotter.repository.HarryPotterRepository;
import br.com.thiago.rxjavaharrypotter.request.AlunoRequest;
import br.com.thiago.rxjavaharrypotter.request.CasaRequest;
import br.com.thiago.rxjavaharrypotter.response.AlunoResponse;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HarryPotterService {

    private final HarryPotterRepository harryPotterRepository;

    public Single<AlunoResponse> adicionar(AlunoRequest alunoRequest) {
        return Single.create(single -> {
             var aluno = alunoRequest.convert(getIdCasa());
             harryPotterRepository.save(aluno);
             single.onSuccess(new AlunoResponse(aluno));
        });
    }

    private static String getIdCasa() {
        String resourceUrl = "https://api-harrypotter.herokuapp.com/sortinghat";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resposta = restTemplate.getForEntity(resourceUrl, String.class);
        Gson gson = new Gson();
        var casaRequest = gson.fromJson(resposta.getBody(), CasaRequest.class);
        return casaRequest.getIdCasa();
    }

    public static Casa pegarDadosCasa(String idCasa) {
        String resourceUrl = "https://api-harrypotter.herokuapp.com/house/" + idCasa;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(resourceUrl, Casa.class);
    }

    public Observable<Object> buscarTodosAlunos() {
        var single = Single.create(emitter -> emitter.onSuccess(harryPotterRepository.findAll().stream()
        .map(AlunoResponse::new).collect(Collectors.toList())));

        return Observable.fromSingle(single);
    }

    public Observable<Object> buscarPorNome(String nome) {
        var listaNomes = harryPotterRepository.findByNome(nome);
        if(!listaNomes.isEmpty()) {
            Single<Object> single = Single.create(emitter -> emitter.onSuccess(listaNomes.stream()
                    .map(AlunoResponse::new).collect(Collectors.toList())));
            return Observable.fromSingle(single);
        } else {
            throw new NomeDoAlunoNaoEncontradoException(nome);
        }

    }

    public Observable<Object> buscarPorIdCasa(String idCasa) {
        var listaAlunosPorCasa = harryPotterRepository.findByIdCasa(idCasa);
        if(!listaAlunosPorCasa.isEmpty()) {
            Single<Object> single = Single.create(emitter -> emitter.onSuccess(listaAlunosPorCasa.stream()
                    .map(AlunoResponse::new).collect(Collectors.toList())));
            return Observable.fromSingle(single);
        } else {
            throw new IdDaCasaNaoEncontradaException(idCasa);
        }
    }

    public Single<AlunoResponse> buscarPorIdAluno(String idAluno) {
        var aluno = harryPotterRepository.findById(idAluno).orElseThrow(() -> new IdDoAlunoNaoExisteException(idAluno));
        return Single.create(emitter -> emitter.onSuccess(new AlunoResponse(aluno)));
    }
}
