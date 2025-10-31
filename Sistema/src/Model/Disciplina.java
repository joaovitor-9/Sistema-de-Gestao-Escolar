package Model;

public class Disciplina {

    private final String nome;
    private final String codigo; // Ex: "SI101"

    public Disciplina(String nome, String codigo) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da disciplina não pode ser vazio.");
        }
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código da disciplina não pode ser vazio.");
        }
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Disciplina[Código: " + codigo + ", Nome: " + nome + "]";
    }
}