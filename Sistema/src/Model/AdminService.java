package Model;

import java.util.List;

public class AdminService {
    private RepositorioSistema repositorio;
    public AdminService() {
        this.repositorio = RepositorioSistema.getInstancia();
    }
    public void cadastrarAluno(String nome, int idade, String cpf) throws Exception {
        if (repositorio.cpfJaCadastrado(cpf)) {
            throw new Exception("ERRO: CPF " + cpf + " já cadastrado no sistema.");
        }
        String novaMatricula = repositorio.gerarNovaMatricula();
        Aluno novoAluno = new Aluno(nome, idade, cpf, novaMatricula);
        repositorio.adicionarAluno(novoAluno);
    }
    public void cadastrarProfessor(String nome, String cpf, int idade, double salario, String especialidade) throws Exception {
        if (repositorio.cpfJaCadastrado(cpf)) {
            throw new Exception("ERRO: CPF " + cpf + " já cadastrado no sistema.");
        }
        String novoID = repositorio.gerarNovoIDProfessor();
        Professor novoProfessor = new Professor(nome, cpf, idade, salario, especialidade);
        novoProfessor.setId(novoID);
        repositorio.adicionarProfessor(novoProfessor);
    }
    public void criarDisciplina(String nome, String codigo) throws Exception {
        if (repositorio.disciplinaExiste(codigo)) {
            throw new Exception("ERRO: Já existe uma disciplina com o código " + codigo);
        }
        Disciplina novaDisciplina = new Disciplina(nome, codigo);
        repositorio.adicionarDisciplina(novaDisciplina);
    }
    public void matricularAlunoEmDisciplina(String matriculaAluno, String codigoDisciplina) throws Exception {
        Aluno aluno = repositorio.buscarAlunoPorMatricula(matriculaAluno);
        if (aluno == null) {
            throw new Exception("ERRO: Aluno não encontrado.");
        }
        Disciplina disciplina = repositorio.buscarDisciplinaPorCodigo(codigoDisciplina);
        if (disciplina == null) {
            throw new Exception("ERRO: Disciplina não encontrada.");
        }
        repositorio.matricularAluno(aluno, disciplina);
    }
    public List<Disciplina> consultarTodasDisciplinas() throws Exception {
        List<Disciplina> disciplinas = repositorio.listarDisciplinas();
        if (disciplinas.isEmpty()) {
            throw new Exception("Nenhuma disciplina cadastrada.");
        }
        return disciplinas;
    }
    public void alocarProfessorEmDisciplina(String idProfessor, String codigoDisciplina) throws Exception {
        Professor prof = repositorio.buscarProfessorPorId(idProfessor);
        if (prof == null) {
            throw new Exception("ERRO: Professor não encontrado.");
        }
        Disciplina disc = repositorio.buscarDisciplinaPorCodigo(codigoDisciplina);
        if (disc == null) {
            throw new Exception("ERRO: Disciplina não encontrada.");
        }
        repositorio.alocarProfessor(prof, disc);
    }
}
