package Model;

// Importações para as listas
import java.util.ArrayList;
import java.util.List;

// 1. A classe Aluno herda de Pessoa
public class Aluno extends Pessoa {

    // 2. A matrícula agora é 'final'.
    //    Ela será definida UMA VEZ no construtor e NUNCA MAIS.
    private final String matricula;

    // 3. Os dados que o Aluno "tem" (em vez de "consultar")
    //    Estes são os dados que os serviços vão preencher.
    private List<Disciplina> disciplinasMatriculadas;
    private List<Nota> notas;
    private List<Frequencia> faltas;

    // 4. O Construtor foi ATUALIZADO
    //    Ele agora recebe a 'matricula' e usa o construtor original de 'Pessoa'
    public Aluno(String nome, int idade, String cpf, String matricula) {

        // 5. Chama o construtor que você manteve em Pessoa
        super(nome, idade, cpf);

        // 6. Validação da Matrícula (regra de negócio no Model)
        //    Lança uma exceção em vez de usar System.out
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("A matrícula é obrigatória.");
        }
        this.matricula = matricula;

        // 7. Inicializa as listas para evitar NullPointerException
        this.disciplinasMatriculadas = new ArrayList<>();
        this.notas = new ArrayList<>();
        this.faltas = new ArrayList<>();
    }

    // 8. O 'getMatricula' permanece
    public String getMatricula() {
        return matricula;
    }

    // 9. O 'setMatricula' foi REMOVIDO. (Pois a matrícula é final)


    // 10. Os métodos 'consultar' foram trocados por 'get'
    //     Eles agora simplesmente retornam as listas que o Aluno TEM.
    public List<Disciplina> getDisciplinas() {
        return disciplinasMatriculadas;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public List<Frequencia> getFaltas() {
        return faltas;
    }

    // Métodos "set" ou "add" para as listas, que podem ser
    // usados por um Service para carregar os dados do Aluno.
    public void adicionarDisciplina(Disciplina d) {
        this.disciplinasMatriculadas.add(d);
    }

    public void adicionarNota(Nota n) {
        this.notas.add(n);
    }
    @Override
    public String toString() {
        // super.toString() chama o toString() de Pessoa
        return "Aluno[" + super.toString() + ", Matrícula: " + matricula + "]";
    }

    // (Você pode adicionar outros métodos 'add' ou 'set' conforme necessário)
}