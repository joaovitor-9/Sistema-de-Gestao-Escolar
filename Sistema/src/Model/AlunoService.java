package Model;

import java.util.List;

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
    public List<Nota> consultarNotas(String matriculaAluno) throws Exception {
        List<Nota> notas = repositorio.buscarNotasDoAluno(matriculaAluno);

        if (notas.isEmpty()) {
            throw new Exception("Nenhuma nota encontrada para este aluno.");
        }
        return notas;
    }
    public List<Frequencia> consultarFaltas(String matriculaAluno) throws Exception {
        List<Frequencia> faltas = repositorio.buscarFaltasDoAluno(matriculaAluno);

        if (faltas.isEmpty()) {
            throw new Exception("Nenhuma falta registrada para este aluno.");
        }
        return faltas;
    }
    public List<Disciplina> consultarDisciplinasMatriculadas(String matriculaAluno) throws Exception {
        List<Disciplina> disciplinas = repositorio.buscarDisciplinasDoAluno(matriculaAluno);

        if (disciplinas.isEmpty()) {
            throw new Exception("Aluno não está matriculado em nenhuma disciplina.");
        }
        return disciplinas;
    }
}
