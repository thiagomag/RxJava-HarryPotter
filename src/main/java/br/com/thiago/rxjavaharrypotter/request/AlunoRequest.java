package br.com.thiago.rxjavaharrypotter.request;

import br.com.thiago.rxjavaharrypotter.entity.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoRequest {

    private String nome;

    public Aluno convert(String idCasa) {

        return Aluno.builder()
                .id(UUID.randomUUID().toString())
                .nome(this.nome)
                .idCasa(idCasa)
                .build();
    }
}
