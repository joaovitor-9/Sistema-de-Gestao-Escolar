package Model;
//import Model.Aluno;
import java.util.List;

/**
 * Contém a lógica de negócio para os casos de uso do Aluno.
 */
public class AlunoService {

    private RepositorioSistema repositorio;

    public AlunoService() {
        this.repositorio = RepositorioSistema.getInstancia();
    }

    public Aluno validarLogin(String matricula) throws Exception {
        Aluno aluno = repositorio.buscarAlunoPorMatricula(matricula);
        if (aluno == null) {
            throw new Exception("Login falhou: Matrícula não encontrada.");
        }
        return aluno;
    }
    /**
     * Caso de Uso: Consultar Notas
     */
    public List<Nota> consultarNotas(String matriculaAluno) throws Exception {
        List<Nota> notas = repositorio.buscarNotasDoAluno(matriculaAluno);

        if (notas.isEmpty()) {
            throw new Exception("Nenhuma nota encontrada para este aluno.");
        }
        return notas;
    }

    /**
     * Caso de Uso: Consultar Faltas
     */
    public List<Frequencia> consultarFaltas(String matriculaAluno) throws Exception {
        List<Frequencia> faltas = repositorio.buscarFaltasDoAluno(matriculaAluno);

        if (faltas.isEmpty()) {
            throw new Exception("Nenhuma falta registrada para este aluno.");
        }
        return faltas;
    }

    /**
     * Caso de Uso (Aluno): Consultar Disciplinas (específico do aluno)
     */
    public List<Disciplina> consultarDisciplinasMatriculadas(String matriculaAluno) throws Exception {
        List<Disciplina> disciplinas = repositorio.buscarDisciplinasDoAluno(matriculaAluno);

        if (disciplinas.isEmpty()) {
            throw new Exception("Aluno não está matriculado em nenhuma disciplina.");
        }
        return disciplinas;
    }
}