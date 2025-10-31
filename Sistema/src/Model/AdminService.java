package Model;

import java.util.List;
// Não precisamos mais de 'import Model.*' ou 'import Model.repository.*'
// pois estamos todos no mesmo pacote.

/**
 * Contém a lógica de negócio para os casos de uso do Administrador.
 */
public class AdminService {

    private RepositorioSistema repositorio;

    public AdminService() {
        // Obtém a instância única do nosso repositório
        // Funciona pois RepositorioSistema está no mesmo pacote
        this.repositorio = RepositorioSistema.getInstancia();
    }

    /**
     * Caso de Uso: Cadastrar Aluno
     */
    public void cadastrarAluno(String nome, int idade, String cpf) throws Exception {
        // 1. Regra de Negócio: Verificar CPF
        if (repositorio.cpfJaCadastrado(cpf)) {
            throw new Exception("ERRO: CPF " + cpf + " já cadastrado no sistema.");
        }

        // 2. Regra de Negócio: Gerar Matrícula
        String novaMatricula = repositorio.gerarNovaMatricula();

        // 3. Criar a entidade (usando o construtor que refatoramos)
        Aluno novoAluno = new Aluno(nome, idade, cpf, novaMatricula);

        // 4. Pedir ao repositório para salvar
        repositorio.adicionarAluno(novoAluno);
    }

    /**
     * Caso de Uso: Cadastrar Professor
     */
    public void cadastrarProfessor(String nome, String cpf, int idade, double salario, String especialidade) throws Exception {
        // 1. Regra de Negócio: Verificar CPF
        if (repositorio.cpfJaCadastrado(cpf)) {
            throw new Exception("ERRO: CPF " + cpf + " já cadastrado no sistema.");
        }

        // 2. Regra de Negócio: Gerar ID
        String novoID = repositorio.gerarNovoIDProfessor();

        // 3. Criar a entidade
        Professor novoProfessor = new Professor(nome, cpf, idade, salario, especialidade);
        novoProfessor.setId(novoID);

        // 4. Pedir ao repositório para salvar
        repositorio.adicionarProfessor(novoProfessor);
    }

    /**
     * Caso de Uso: Criar Disciplina
     */
    public void criarDisciplina(String nome, String codigo) throws Exception {
        if (repositorio.disciplinaExiste(codigo)) {
            throw new Exception("ERRO: Já existe uma disciplina com o código " + codigo);
        }

        Disciplina novaDisciplina = new Disciplina(nome, codigo);
        repositorio.adicionarDisciplina(novaDisciplina);
    }

    /**
     * Caso de Uso: Matricular Aluno em Disciplina
     */
    public void matricularAlunoEmDisciplina(String matriculaAluno, String codigoDisciplina) throws Exception {
        // 1. Buscar as entidades
        Aluno aluno = repositorio.buscarAlunoPorMatricula(matriculaAluno);
        if (aluno == null) {
            throw new Exception("ERRO: Aluno não encontrado.");
        }

        Disciplina disciplina = repositorio.buscarDisciplinaPorCodigo(codigoDisciplina);
        if (disciplina == null) {
            throw new Exception("ERRO: Disciplina não encontrada.");
        }

        // 2. Executar a lógica
        repositorio.matricularAluno(aluno, disciplina);
    }

    /**
     * Caso de Uso (Admin): Consultar Disciplinas (versão que lista TODAS)
     */
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

        // (Aqui você pode adicionar uma regra de negócio, ex: verificar se a disciplina já tem professor) acho que n pois uma diciplina tem mais de um profesor

        repositorio.alocarProfessor(prof, disc);
    }
}