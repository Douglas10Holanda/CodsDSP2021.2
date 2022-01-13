package UNIVERSIDADE.APLICACAO.ENTITY;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@NamedQueries(
        {
                @NamedQuery(name = "findCod", query = "SELECT D.alunos as alunos FROM Disciplina D WHERE D.codigo = :cod")
                // Busca os alunos que estao cursando a disciplina pelo seu codigo
        }
)

@Entity
@Table(name = "disciplinas")
@AllArgsConstructor
@NoArgsConstructor

public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int id;
    @Column(unique = true, name = "codigo_da_disciplina")
    @Getter @Setter private Integer codigo;
    @Column(name = "nome_da_disciplina")
    @Getter @Setter private String nome;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    @JoinTable(name = "Lista",
                joinColumns = @JoinColumn(name = "ID_Disciplina"),
                inverseJoinColumns = @JoinColumn(name = "ID_Aluno"))
    @Getter @Setter private List<Aluno> alunos;

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ID_Disciplina: ").append(id).append(", Código_Disciplina: ").append(codigo).append(", Nome_Disciplina: ").append(nome).append("\n");
        return stringBuilder.toString();
    }
}