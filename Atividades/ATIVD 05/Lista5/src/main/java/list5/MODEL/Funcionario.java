package list5.MODEL;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Funcionario {
    @NonNull @Getter @Setter private int id;
    @NonNull @Getter @Setter private String cpf;
    @NonNull @Getter @Setter private String matricula;
    @Getter @Setter private String nome;
    @Getter @Setter private String email;
    @Getter @Setter private String telefone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}