package UNIVERSIDADE.APLICACAO.ENTITY;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NamedQueries(
        {
                @NamedQuery(name = "name_and_email", query = "SELECT A.nome as nome, A.email as email FROM Aluno A WHERE A.matricula = :matricula"),
                // Pega o nome e o email do aluno pela sua matricula
                @NamedQuery(name = "finddata_Aluno", query = "SELECT A FROM Aluno A WHERE A.dataNasc > :data"),
                // Pega os alunos que nasceram em datas superiores a data escolhida
                @NamedQuery(name = "nome_and_numero", query = "SELECT A.nome as nome, size(A.disciplinas) as count FROM Aluno A")
                // Pega o nome e o numero de disciplinas que os alunos estão cursando
        }
)

@Entity
@Table(name = "alunos")
@AllArgsConstructor
@NoArgsConstructor

public class Aluno {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Getter @Setter private int id;
     @Getter @Setter private String cpf;
     @Column(unique = true)
     @Getter @Setter private Integer matricula;
     @Column(name = "nome_do_aluno")
     @Setter @Getter private String nome;
     @Column(unique = true)
     @Setter @Getter private String email;
     @Column(name = "data_de_nascimento")
     @Setter @Getter private Date dataNasc;

     @ManyToMany(mappedBy = "alunos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     @Setter @Getter private List<Disciplina> disciplinas;

     public String toString(){
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("ID: ").append(id).append(", CPF: ").append(cpf).append(", Matrícula: ")
                  .append(matricula).append(", Nome: ").append(nome).append(", Email: ").append(email)
                  .append(", Data de Nascimento: ").append(dataNasc).append("\nDisciplinas: \n");
          for (Disciplina disciplina : disciplinas) {
               stringBuilder.append("\t").append(disciplina).append("\n");
          }
          return stringBuilder.toString();
     }
}