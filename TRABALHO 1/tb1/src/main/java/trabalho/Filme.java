package trabalho;

public class Filme implements java.io.Serializable{
    private String nome;
    private String genero;
    private String ano_lancamento;
    private String nota;

    @Override
    public String toString() {
        return "Filme{" +
                "nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", ano_lancamento='" + ano_lancamento + '\'' +
                ", nota='" + nota + '\'' +
                '}';
    }

    public Filme(String nome, String genero, String ano_lancamento, String nota) {
        this.nome = nome;
        this.genero = genero;
        this.ano_lancamento = ano_lancamento;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAno_lancamento() {
        return ano_lancamento;
    }

    public void setAno_lancamento(String ano_lancamento) {
        this.ano_lancamento = ano_lancamento;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
