import Controller.AdminController;
import Controller.AlunoController;
import Controller.ProfessorController;
import View.SistemaView;

public class Main {
    private static SistemaView view = new SistemaView();
    private static AdminController adminController = new AdminController();
    private static ProfessorController professorController = new ProfessorController();
    private static AlunoController alunoController = new AlunoController();
    public static void main(String[] args) {
        view.exibirMensagem("--- BEM-VINDO AO SISTEMA DE GESTÃO ESCOLAR ---");
        loopPrincipal();
        view.exibirMensagem("--- OBRIGADO POR USAR O SISTEMA ---");
    }
    private static void loopPrincipal() {
        boolean executando = true;
        while (executando) {
            view.exibirMensagem("\n--- MENU PRINCIPAL ---");
            view.exibirMensagem("1. Portal do Administrador");
            view.exibirMensagem("2. Portal do Professor");
            view.exibirMensagem("3. Portal do Aluno");
            view.exibirMensagem("0. Sair do Sistema");

            int opcao = view.solicitarInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    menuAdmin();
                    break;
                case 2:
                    menuProfessor();
                    break;
                case 3:
                    menuAluno();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    view.exibirErro("Opção inválida. Tente novamente.");
            }
        }
    }
    private static void menuAdmin() {
        view.exibirMensagem("\n--- Portal do Administrador ---");
        boolean executando = true;
        while (executando) {
            view.exibirMensagem("1. Cadastrar Aluno");
            view.exibirMensagem("2. Cadastrar Professor");
            view.exibirMensagem("3. Criar Disciplina");
            view.exibirMensagem("4. Matricular Aluno em Disciplina");
            view.exibirMensagem("5. Consultar Todas as Disciplinas");
            view.exibirMensagem("6. Alocar Professor em Disciplina");
            view.exibirMensagem("7. Consultar Alunos por Disciplina");
            view.exibirMensagem("8. Consultar Notas de um Aluno");
            view.exibirMensagem("9. Consultar Faltas de um Aluno");
            view.exibirMensagem("0. Voltar ao Menu Principal");

            int opcao = view.solicitarInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    adminController.iniciarCadastroAluno();
                    break;
                case 2:
                    adminController.iniciarCadastroProfessor();
                    break;
                case 3:
                    adminController.iniciarCriacaoDisciplina();
                    break;
                case 4:
                    adminController.iniciarMatriculaAluno();
                    break;
                case 5:
                    adminController.iniciarConsultaDisciplinas();
                    break;
                case 6:
                    adminController.iniciarAlocacaoProfessor();
                    break;
                case 7:
                    adminController.iniciarConsultaAlunosPorDisciplina();
                    break;
                case 8:
                    adminController.iniciarConsultaNotasAluno();
                    break;
                case 9:
                    adminController.iniciarConsultaFaltasAluno();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    view.exibirErro("Opção inválida.");
            }
        }
    }
    private static void menuProfessor() {
        boolean loginSucesso = professorController.fazerLogin();
        if (!loginSucesso) {
            return;
        }
        boolean executando = true;
        while (executando) {
            view.exibirMensagem("1. Lançar Nota");
            view.exibirMensagem("2. Lançar Falta");
            view.exibirMensagem("3. Consultar Alunos da Disciplina");
            view.exibirMensagem("4. Consultar Minhas Disciplinas");
            view.exibirMensagem("5. Consultar Notas da Disciplina");
            view.exibirMensagem("6. Consultar Faltas da Disciplina");
            view.exibirMensagem("0. Voltar (Logout)");

            int opcao = view.solicitarInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    professorController.iniciarLancamentoNota();
                    break;
                case 2:
                    professorController.iniciarLancamentoFalta();
                    break;
                case 3:
                    professorController.iniciarConsultaAlunosPorDisciplina();
                    break;
                case 4:
                    professorController.iniciarConsultaDisciplinasDoProfessor();
                    break;
                case 5:
                    professorController.iniciarConsultaNotasDaDisciplina();
                    break;
                case 6:
                    professorController.iniciarConsultaFaltasDaDisciplina();
                    break;
                case 0:
                    executando = false;
                    professorController.setProfessorLogado(null);
                    break;
                default:
                    view.exibirErro("Opção inválida.");
            }
        }
    }
    private static void menuAluno() {
        boolean loginSucesso = alunoController.fazerLogin();
        if (!loginSucesso) {
            return;
        }
        boolean executando = true;
        while (executando) {
            view.exibirMensagem("1. Consultar Minhas Notas");
            view.exibirMensagem("2. Consultar Minhas Faltas");
            view.exibirMensagem("3. Consultar Minhas Disciplinas");
            view.exibirMensagem("0. Voltar (Logout)");

            int opcao = view.solicitarInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    alunoController.iniciarConsultaNotas();
                    break;
                case 2:
                    alunoController.iniciarConsultaFaltas();
                    break;
                case 3:
                    alunoController.iniciarConsultaDisciplinasMatriculadas();
                    break;
                case 0:
                    executando = false;
                    alunoController.setAlunoLogado(null);
                    break;
                default:
                    view.exibirErro("Opção inválida.");
            }
        }
    }
}
