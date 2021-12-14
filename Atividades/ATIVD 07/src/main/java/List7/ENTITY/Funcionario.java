package List7.ENTITY;

import lombok.*;

import javax.persistence.*;

@NamedQueries({
		@NamedQuery(name = "funcionarioPorCpf", query = "select f from Funcionario f where f.cpf = :cpf")
})

@Entity
@Table(name = "funcionarios")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Funcionario {
	@Id @Getter @Setter @NonNull @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
	@Getter @Setter @NonNull private String cpf;
	@Getter @Setter @NonNull private String matricula;
	@Getter @Setter @NonNull private String nome;
	@Getter @Setter private String email;
	@Getter @Setter private String telefone;
}
