package Controller;

import Model.AdminService;
import Model.Disciplina;
import View.SistemaView;
import Model.Aluno;
import Model.Nota;
import Model.Frequencia;
import java.util.List;

public class AdminController {
    private AdminService adminService;
    private SistemaView view;

    public AdminController() {
        this.adminService = new AdminService();
        this.view = new SistemaView();
    }
    public void iniciarCadastroAluno() {
        view.exibirMensagem("--- Cadastro de Novo Aluno ---");
        try {
            String nome = view.solicitarString("Digite o nome: ");
            int idade = view.solicitarInt("Digite a idade: ");
            String cpf = view.solicitarString("Digite o CPF: ");

            Aluno novoAluno = adminService.cadastrarAluno(nome, idade, cpf);

            view.exibirMensagem("SUCESSO: Aluno cadastrado!");
            view.exibirMensagem("Nome: " + novoAluno.getNome() + " | Matrícula Gerada: " + novoAluno.getMatricula());

        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }
    public void iniciarCadastroProfessor() {
        view.exibirMensagem("--- Cadastro de Novo Professor ---");
        try {
            String nome = view.solicitarString("Digite o nome: ");
            int idade = view.solicitarInt("Digite a idade: ");
            String cpf = view.solicitarString("Digite o CPF: ");
            double salario = view.solicitarDouble("Digite o salário: ");
            String especialidade = view.solicitarString("Digite a especialidade: ");
            adminService.cadastrarProfessor(nome, cpf, idade, salario, especialidade);
            view.exibirMensagem("SUCESSO: Professor cadastrado!");

        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }
    public void iniciarCriacaoDisciplina() {
        view.exibirMensagem("--- Criação de Nova Disciplina ---");
        try {
            String nome = view.solicitarString("Nome da disciplina: ");
            String codigo = view.solicitarString("Código da disciplina (ex: SI101): ");
            adminService.criarDisciplina(nome, codigo);
            view.exibirMensagem("SUCESSO: Disciplina criada!");
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }
    public void iniciarMatriculaAluno() {
        view.exibirMensagem("--- Matricular Aluno em Disciplina ---");
        try {
            String matricula = view.solicitarString("Matrícula do aluno: ");
            String codigo = view.solicitarString("Código da disciplina: ");
            adminService.matricularAlunoEmDisciplina(matricula, codigo);
            view.exibirMensagem("SUCESSO: Aluno matriculado na disciplina!");
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }
    public void iniciarConsultaDisciplinas() {
        view.exibirMensagem("--- Lista de Todas as Disciplinas ---");
        try {
            List<Disciplina> disciplinas = adminService.consultarTodasDisciplinas();
            view.exibirLista(disciplinas);
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }
    public void iniciarAlocacaoProfessor() {
        view.exibirMensagem("--- Alocar Professor em Disciplina ---");
        try {
            String idProf = view.solicitarString("ID do Professor: ");
            String codigo = view.solicitarString("Código da disciplina: ");
            adminService.alocarProfessorEmDisciplina(idProf, codigo);
            view.exibirMensagem("SUCESSO: Professor alocado!");
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }
    public void iniciarConsultaAlunosPorDisciplina() {
        view.exibirMensagem("--- Consultar Alunos por Disciplina ---");
        try {
            String codigo = view.solicitarString("Digite o código da disciplina: ");
            List<Aluno> alunos = adminService.consultarAlunosDaDisciplina(codigo);
            view.exibirMensagem("Alunos encontrados para a disciplina '" + codigo + "':");
            view.exibirLista(alunos);
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }
    public void iniciarConsultaNotasAluno() {
        view.exibirMensagem("--- Consultar Notas de um Aluno ---");
        try {
            String matricula = view.solicitarString("Digite a matrícula do aluno: ");
            List<Nota> notas = adminService.consultarNotasDoAluno(matricula);
            view.exibirMensagem("Notas encontradas para o aluno '" + matricula + "':");
            view.exibirLista(notas);
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }
    public void iniciarConsultaFaltasAluno() {
        view.exibirMensagem("--- Consultar Faltas de um Aluno ---");
        try {
            String matricula = view.solicitarString("Digite a matrícula do aluno: ");
            List<Frequencia> faltas = adminService.consultarFaltasDoAluno(matricula);
            view.exibirMensagem("Faltas encontradas para o aluno '" + matricula + "':");
            view.exibirLista(faltas);
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }
}
