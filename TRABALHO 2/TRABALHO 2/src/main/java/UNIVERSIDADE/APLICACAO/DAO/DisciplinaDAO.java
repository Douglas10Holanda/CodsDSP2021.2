package UNIVERSIDADE.APLICACAO.DAO;

import UNIVERSIDADE.APLICACAO.ENTITY.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import UNIVERSIDADE.APLICACAO.ENTITY.Aluno;

import java.util.List;

@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, Integer> {
    @Query("SELECT D FROM Disciplina D WHERE D.id = :id")
    public Disciplina findDisciplina(Integer id);
    // Busca uma Disciplina pelo seu id

    public void deleteById(Integer id);
    // Apaga uma disciplina pelo seu id

    public List<Disciplina> findAll();
    // Lista todos as disciplinas

    public Disciplina findByCodigo(Integer inteiro);
    // Busca disciplina pelo seu codigo

    @Query(name = "findCod")
    public List<Aluno> findCod(Integer cod);
    // Lista alunos pelo codigo da disciplina
}