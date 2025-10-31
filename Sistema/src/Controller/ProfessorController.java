package Controller;

import Model.Aluno;
import Model.Professor;
import Model.Disciplina;
import Model.ProfessorService;
import View.SistemaView;

import java.util.List;

public class ProfessorController {

    private ProfessorService professorService;
    private SistemaView view;

    // O ID do professor "logado"
    private String idProfessorLogado;

    public ProfessorController() {
        this.professorService = new ProfessorService();
        this.view = new SistemaView();
    }

    // Método de "Login"
    public void setProfessorLogado(String id) {
        this.idProfessorLogado = id;
    }

    public boolean fazerLogin() {
        view.exibirMensagem("\n--- Portal do Professor ---");
        try {
            String idProf = view.solicitarString("Digite seu ID de Professor para logar: ");
            // 1. Chama o Service
            Professor prof = professorService.validarLogin(idProf);

            // 2. Define o professor logado e avisa
            this.setProfessorLogado(prof.getID());
            view.exibirMensagem("Login bem-sucedido! Bem-vindo(a), Prof. " + prof.getNome());
            return true;

        } catch (Exception e) {
            // 3. Exibe o erro
            view.exibirErro(e.getMessage());
            view.aguardarEnter();
            return false;
        }
    }

    // --- Métodos de Orquestração ---

    public void iniciarLancamentoNota() {
        view.exibirMensagem("--- Lançar Nota ---");
        if (idProfessorLogado == null) {
            view.exibirErro("Nenhum professor logado.");
            return;
        }

        try {
            String matriculaAluno = view.solicitarString("Matrícula do aluno: ");
            String codigoDisciplina = view.solicitarString("Código da disciplina: ");
            double nota = view.solicitarDouble("Nota (0 a 10): ");

            professorService.lancarNota(idProfessorLogado, matriculaAluno, codigoDisciplina, nota);

            view.exibirMensagem("SUCESSO: Nota lançada!");
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }


    public void iniciarLancamentoFalta() {
        view.exibirMensagem("--- Lançar Falta ---");
        if (idProfessorLogado == null) {
            view.exibirErro("Nenhum professor logado.");
            return;
        }

        try{
            String matriculaAluno = view.solicitarString("Matricula do aluno: ");
            String codigoDisciplina = view.solicitarString("Código da disciplina: ");

            professorService.lancarFalta(idProfessorLogado,matriculaAluno,codigoDisciplina);
            view.exibirMensagem("Falta cadastrada com sucesso!");
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }

    public void iniciarConsultaAlunosPorDisciplina() {
        view.exibirMensagem("--- Alunos da Disciplina ---");
        try {
            String codigo = view.solicitarString("Código da disciplina: ");

            List<Aluno> alunos = professorService.consultarAlunosDaDisciplina(codigo);

            view.exibirLista(alunos);
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }

    public void iniciarConsultaDisciplinasDoProfessor() {
        view.exibirMensagem("--- Minhas Disciplinas ---");
        if (idProfessorLogado == null) {
            view.exibirErro("Nenhum professor logado.");
            return;
        }

        try {
            List<Disciplina> disciplinas = professorService.consultarDisciplinasDoProfessor(idProfessorLogado);
            view.exibirLista(disciplinas);
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }
}