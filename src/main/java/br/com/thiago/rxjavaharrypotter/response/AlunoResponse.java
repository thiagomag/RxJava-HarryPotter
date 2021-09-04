package br.com.thiago.rxjavaharrypotter.response;

import br.com.thiago.rxjavaharrypotter.entity.Aluno;
import br.com.thiago.rxjavaharrypotter.entity.Casa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResponse {

    private String id;
    private String nome;
    private Casa casa;

    public AlunoResponse(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.casa = pegarDadosCasa(aluno.getIdCasa());
    }

    private static Casa pegarDadosCasa(String idCasa) {
        String resourceUrl = "https://api-harrypotter.herokuapp.com/house/" + idCasa;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(resourceUrl, Casa.class);
    }
}