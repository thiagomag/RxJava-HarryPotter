package br.com.thiago.rxjavaharrypotter.service;

import br.com.thiago.rxjavaharrypotter.exception.IdDoAlunoNaoExisteException;
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
             var aluno = alunoRequest.convert(getIdCasa().getIdCasa());
             harryPotterRepository.save(aluno);
             single.onSuccess(new AlunoResponse(aluno));
        });

    }

    private static CasaRequest getIdCasa() {
        String resourceUrl = "https://api-harrypotter.herokuapp.com/sortinghat";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resposta = restTemplate.getForEntity(resourceUrl, String.class);
        Gson gson = new Gson();
        return gson.fromJson(resposta.getBody(), CasaRequest.class);
    }

    public Observable<Object> buscarTodosAlunos() {
        var single = Single.create(emitter -> emitter.onSuccess(harryPotterRepository.findAll().stream()
        .map(AlunoResponse::new).collect(Collectors.toList())));

        return Observable.fromSingle(single);
    }

    public Observable<Object> buscarPorNome(String nome) {
        Single<Object> single = Single.create(emitter -> emitter.onSuccess(harryPotterRepository.findByNome(nome).stream()
                .map(AlunoResponse::new).collect(Collectors.toList())));

        return Observable.fromSingle(single);
    }

    public Observable<Object> buscarPorIdCasa(String idCasa) {
        Single<Object> single = Single.create(emitter -> emitter.onSuccess(harryPotterRepository.findByIdCasa(idCasa).stream()
                .map(AlunoResponse::new).collect(Collectors.toList())));

        return Observable.fromSingle(single);
    }

    public Single<AlunoResponse> buscarPorIdAluno(String idAluno) {
        var aluno = harryPotterRepository.findById(idAluno).orElseThrow(() -> new IdDoAlunoNaoExisteException(idAluno));
        return Single.create(emitter -> emitter.onSuccess(new AlunoResponse(aluno)));
    }
}
