package Controller;

import Model.AdminService;
import Model.Disciplina;
import View.SistemaView;

import java.util.List;

/**
 * Esta é a camada de Controle (Controller).
 * Responsabilidade: Orquestrar. Conectar a View aos Services.
 * Conhece a View e o Service.
 * É o ÚNICO que usa try...catch para os serviços.
 */
public class AdminController {

    private AdminService adminService;
    private SistemaView view;

    public AdminController() {
        this.adminService = new AdminService(); // O Model
        this.view = new SistemaView();           // A View
    }

    // --- Métodos de Orquestração (Casos de Uso) ---

    public void iniciarCadastroAluno() {
        view.exibirMensagem("--- Cadastro de Novo Aluno ---");
        try {
            // 1. Coleta dados com a View
            String nome = view.solicitarString("Digite o nome: ");
            int idade = view.solicitarInt("Digite a idade: ");
            String cpf = view.solicitarString("Digite o CPF: ");

            // 2. Chama o Service (Model)
            adminService.cadastrarAluno(nome, idade, cpf);

            // 3. Responde ao usuário pela View
            view.exibirMensagem("SUCESSO: Aluno cadastrado!");

        } catch (Exception e) {
            // 4. Se o Service falhar, exibe o erro pela View
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }

    public void iniciarCadastroProfessor() {
        view.exibirMensagem("--- Cadastro de Novo Professor ---");
        try {
            // 1. Coleta
            String nome = view.solicitarString("Digite o nome: ");
            int idade = view.solicitarInt("Digite a idade: ");
            String cpf = view.solicitarString("Digite o CPF: ");
            double salario = view.solicitarDouble("Digite o salário: ");
            String especialidade = view.solicitarString("Digite a especialidade: ");

            // 2. Chama Service
            adminService.cadastrarProfessor(nome, cpf, idade, salario, especialidade);

            // 3. Responde
            view.exibirMensagem("SUCESSO: Professor cadastrado!");

        } catch (Exception e) {
            // 4. Captura erro
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
            // 1. Chama o Service
            List<Disciplina> disciplinas = adminService.consultarTodasDisciplinas();
            // 2. Manda a View exibir a lista
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
}