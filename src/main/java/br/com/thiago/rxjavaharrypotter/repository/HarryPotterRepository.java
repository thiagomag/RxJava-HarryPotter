package br.com.thiago.rxjavaharrypotter.repository;

import br.com.thiago.rxjavaharrypotter.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarryPotterRepository extends JpaRepository<Aluno, String> {
    List<Aluno> findByNome(String nome);
    List<Aluno> findByIdCasa(String id);
}
