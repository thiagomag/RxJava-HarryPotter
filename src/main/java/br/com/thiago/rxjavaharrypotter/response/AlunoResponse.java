package br.com.thiago.rxjavaharrypotter.response;

import br.com.thiago.rxjavaharrypotter.entity.Aluno;
import br.com.thiago.rxjavaharrypotter.entity.Casa;
import br.com.thiago.rxjavaharrypotter.service.HarryPotterService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        this.casa = HarryPotterService.pegarDadosCasa(aluno.getIdCasa());
    }
}