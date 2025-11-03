package Model;

import java.util.List;

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
    public void lancarNota(String idProfessor, String matriculaAluno, String codigoDisciplina, double valorNota) throws Exception {
        if (!repositorio.professorLecionaDisciplina(idProfessor, codigoDisciplina)) {
            throw new Exception("ERRO: Professor não tem permissão para lançar nota nesta disciplina.");
        }
        Aluno aluno = repositorio.buscarAlunoPorMatricula(matriculaAluno);
        Disciplina disciplina = repositorio.buscarDisciplinaPorCodigo(codigoDisciplina);
        Nota novaNota = new Nota(aluno, disciplina, valorNota);
        repositorio.salvarNota(novaNota);
    }
    public void lancarFalta(String idProfessor, String matriculaAluno, String codigoDisciplina) throws Exception {
        if (!repositorio.professorLecionaDisciplina(idProfessor, codigoDisciplina)) {
            throw new Exception("ERRO: Professor não tem permissão para lançar falta nesta disciplina.");
        }
        Aluno aluno = repositorio.buscarAlunoPorMatricula(matriculaAluno);
        Disciplina  disciplina = repositorio.buscarDisciplinaPorCodigo(codigoDisciplina);
        Frequencia novaFalta = new Frequencia(aluno, disciplina );
        repositorio.salvarFalta(novaFalta);
    }
    public List<Aluno> consultarAlunosDaDisciplina(String codigoDisciplina) throws Exception {
        List<Aluno> alunos = repositorio.buscarAlunosPorDisciplina(codigoDisciplina);
        if (alunos.isEmpty()) {
            throw new Exception("Nenhum aluno matriculado nesta disciplina.");
        }
        return alunos;
    }
    public List<Disciplina> consultarDisciplinasDoProfessor(String idProfessor) throws Exception {
        List<Disciplina> disciplinas = repositorio.buscarDisciplinasPorProfessor(idProfessor);
        if (disciplinas.isEmpty()) {
            throw new Exception("Professor não está alocado em nenhuma disciplina.");
        }
        return disciplinas;
    }
    public List<Nota> consultarNotasDaDisciplina(String idProfessor, String codigoDisciplina) throws Exception {
        if (!repositorio.professorLecionaDisciplina(idProfessor, codigoDisciplina)) {
            throw new Exception("ERRO: Professor não tem permissão para consultar notas desta disciplina.");
        }
        Disciplina disciplina = repositorio.buscarDisciplinaPorCodigo(codigoDisciplina);
        if (disciplina == null) {
            throw new Exception("ERRO: Disciplina não encontrada.");
        }
        List<Nota> notas = repositorio.buscarNotasPorDisciplina(codigoDisciplina);
        if (notas.isEmpty()) {
            throw new Exception("Nenhuma nota foi lançada para esta disciplina ainda.");
        }
        return notas;
    }
    public List<Frequencia> consultarFaltasDaDisciplina(String idProfessor, String codigoDisciplina) throws Exception {
        if (!repositorio.professorLecionaDisciplina(idProfessor, codigoDisciplina)) {
            throw new Exception("ERRO: Professor não tem permissão para consultar faltas desta disciplina.");
        }
        Disciplina disciplina = repositorio.buscarDisciplinaPorCodigo(codigoDisciplina);
        if (disciplina == null) {
            throw new Exception("ERRO: Disciplina não encontrada.");
        }
        List<Frequencia> faltas = repositorio.buscarFaltasPorDisciplina(codigoDisciplina);
        if (faltas.isEmpty()) {
            throw new Exception("Nenhuma falta foi registrada para esta disciplina ainda.");
        }
        return faltas;
    }
}
