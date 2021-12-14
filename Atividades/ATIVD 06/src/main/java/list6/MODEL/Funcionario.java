package list6.MODEL;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString

@Entity
@Table(name = "Funcionarios")

public class Funcionario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int id;
    @NonNull @Getter @Setter private String cpf;
    @NonNull @Getter @Setter private String matricula;
    @Getter @Setter private String nome;
    @Getter @Setter private String email;
    @Getter @Setter private String telefone;
}

