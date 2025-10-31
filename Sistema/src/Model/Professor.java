package Model;

// Importações necessárias para as listas
import java.util.ArrayList;
import java.util.List;

// Assumindo que Funcionario herda de Pessoa
public class Professor extends Funcionario {

    private String especialidade;

    // Esta é a lista de dados que o Professor "TEM".
    // A responsabilidade de "consultar" (buscar no banco)
    // para popular esta lista é de um Serviço.
    private List<Disciplina> disciplinasLecionadas;

    public Professor(String nome, String cpf, int idade, double salario, String especialidade) {
        super(nome, cpf, idade, salario);

        // 1. ADICIONADO: Validação da regra de negócio no construtor
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("A especialidade é obrigatória.");
        }
        this.especialidade = especialidade;

        // 2. ADICIONADO: Inicializa a lista para evitar NullPointerException
        this.disciplinasLecionadas = new ArrayList<>();
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        // 3. ADICIONADO: Validação também no setter
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("A especialidade não pode ser nula ou vazia.");
        }
        this.especialidade = especialidade;
    }

    // --- Métodos de Ação Removidos ---
    // A lógica de "lançar" e "consultar" (que envolve repositórios)
    // deve ser movida para classes de Serviço (ex: AcademicoService).
    //
    // public void consultarFaltas(){...}
    // public void lancarFaltas(){...}
    // public void consultarNotas(){...}
    // public void lancarNotas(){...}
    // public void consultarAlunos(){...}


    // 4. SUBSTITUÍDO: "consultarDisciplinas" virou "getDisciplinasLecionadas"
    // Ele agora é um getter padrão para a lista que a entidade Professor possui.
    public List<Disciplina> getDisciplinasLecionadas() {
        return this.disciplinasLecionadas;
    }

    // 5. ADICIONADO: Um método para um Serviço poder construir o objeto
    public void adicionarDisciplina(Disciplina disciplina) {
        if (disciplina != null) {
            this.disciplinasLecionadas.add(disciplina);
        }
    }
    @Override
    public String toString() {
        // super.toString() chama o toString() de Funcionario
        return "Professor[" + super.toString() + ", Especialidade: " + especialidade + "]";
    }
}