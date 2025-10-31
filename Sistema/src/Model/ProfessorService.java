package Model;
import Model.Professor;
import java.util.List;

/**
 * Contém a lógica de negócio para os casos de uso do Professor.
 */
public class ProfessorService {

    private RepositorioSistema repositorio;

    public ProfessorService() {
        this.repositorio = RepositorioSistema.getInstancia();
    }

    public Professor validarLogin(String idProfessor) throws Exception {
        Professor prof = repositorio.buscarProfessorPorId(idProfessor);
        if (prof == null) {
            throw new Exception("Login falhou: ID de Professor não encontrado.");
        }
        return prof;
    }
    /**
     * Caso de Uso: Lançar Notas e Faltas (dividido em dois)
     */
    public void lancarNota(String idProfessor, String matriculaAluno, String codigoDisciplina, double valorNota) throws Exception {
        // 1. Validar se o professor pode lançar nota nesta disciplina
        if (!repositorio.professorLecionaDisciplina(idProfessor, codigoDisciplina)) {
            throw new Exception("ERRO: Professor não tem permissão para lançar nota nesta disciplina.");
        }

        // 2. Buscar Aluno e Disciplina
        Aluno aluno = repositorio.buscarAlunoPorMatricula(matriculaAluno);
        Disciplina disciplina = repositorio.buscarDisciplinaPorCodigo(codigoDisciplina);

        // 3. Criar a nota e salvar
        Nota novaNota = new Nota(aluno, disciplina, valorNota);
        repositorio.salvarNota(novaNota);
    }

    /**
     * Caso de Uso: Lançar Notas e Faltas (dividido em dois)
     */
    public void lancarFalta(String idProfessor, String matriculaAluno, String codigoDisciplina) throws Exception {

        if (!repositorio.professorLecionaDisciplina(idProfessor, codigoDisciplina)) {
            throw new Exception("ERRO: Professor não tem permissão para lançar falta nesta disciplina.");
        }
        Aluno aluno = repositorio.buscarAlunoPorMatricula(matriculaAluno);
        Disciplina  disciplina = repositorio.buscarDisciplinaPorCodigo(codigoDisciplina);

        Frequencia novaFalta = new Frequencia(aluno, disciplina );
        repositorio.salvarFalta(novaFalta);
    }

    /**
     * Caso de Uso: Consultar Alunos da Disciplina
     */
    public List<Aluno> consultarAlunosDaDisciplina(String codigoDisciplina) throws Exception {
        List<Aluno> alunos = repositorio.buscarAlunosPorDisciplina(codigoDisciplina);

        if (alunos.isEmpty()) {
            throw new Exception("Nenhum aluno matriculado nesta disciplina.");
        }
        return alunos;
    }

    /**
     * Caso de Uso (Professor): Consultar Disciplinas (específico do professor)
     */
    public List<Disciplina> consultarDisciplinasDoProfessor(String idProfessor) throws Exception {
        List<Disciplina> disciplinas = repositorio.buscarDisciplinasPorProfessor(idProfessor);

        if (disciplinas.isEmpty()) {
            throw new Exception("Professor não está alocado em nenhuma disciplina.");
        }
        return disciplinas;
    }
}