package UNIVERSIDADE.APLICACAO.DAO;

import UNIVERSIDADE.APLICACAO.ENTITY.ENTITY2.nome_email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import UNIVERSIDADE.APLICACAO.ENTITY.Aluno;
import UNIVERSIDADE.APLICACAO.ENTITY.ENTITY2.nome_numeroDisciplinas;

import java.util.Date;
import java.util.List;

@Repository
public interface AlunoDAO extends JpaRepository<Aluno, Integer> {
    @Query("SELECT A FROM Aluno A WHERE A.id = :id")
    public Aluno findAluno(Integer id);
    // Buscar um aluno pelo seu id

    @Query("SELECT A FROM Aluno A WHERE A.nome LIKE %:str%")
    public List<Aluno> findNomeAluno(String str);
    // Buscar aluno que comece por determinado nome

    public void deleteById(Integer id);
    // Apagar aluno pelo seu id

    public List<Aluno> findAll();
    // Buscar todos os alunos

    public Aluno findByCpf(String str);
    // Buscar aluno pelo seu cpf

    @Query(name = "name_and_email")
    public List<nome_email> findAlunoCodMatricula(Integer matricula);
    // Buscar nome e email do aluno pela sua matricula

    @Query(name = "finddata_Aluno")
    public List<Aluno> findAlunoData(Date data);
    // Buscar Aluno nascido em data maior que determinada data

    @Query(name = "nome_and_numero")
    public List<nome_numeroDisciplinas> findAlunoNumeroDisciplinas();
    // Busca o numero de disciplinas que os alunos estão cursando
}