package Controller;

import Model.AlunoService;
import Model.Disciplina;
import Model.Frequencia;
import Model.Nota;
import Model.Aluno;
import View.SistemaView;

import java.util.List;

public class AlunoController {

    private AlunoService alunoService;
    private SistemaView view;

    // Matrícula do aluno "logado"
    private String matriculaAlunoLogado;

    public AlunoController() {
        this.alunoService = new AlunoService();
        this.view = new SistemaView();
    }
    public boolean fazerLogin() {
        view.exibirMensagem("\n--- Portal do Aluno ---");
        try {
            String matricula = view.solicitarString("Digite sua Matrícula para logar: ");
            // 1. Chama o Service para validar
            Aluno aluno = alunoService.validarLogin(matricula);

            // 2. Se deu certo, define o aluno logado e avisa
            this.setAlunoLogado(aluno.getMatricula());
            view.exibirMensagem("Login bem-sucedido! Bem-vindo(a), " + aluno.getNome());
            return true;

        } catch (Exception e) {
            // 3. Se o Service falhou, exibe o erro
            view.exibirErro(e.getMessage());
            view.aguardarEnter();
            return false;
        }
    }

    public void setAlunoLogado(String matricula) {
        this.matriculaAlunoLogado = matricula;
    }

    // --- Métodos de Orquestração ---

    public void iniciarConsultaNotas() {
        view.exibirMensagem("--- Minhas Notas ---");
        if (matriculaAlunoLogado == null) {
            view.exibirErro("Nenhum aluno logado.");
            return;
        }

        try {
            List<Nota> notas = alunoService.consultarNotas(matriculaAlunoLogado);
            view.exibirLista(notas); // (Precisamos garantir que Nota.toString() seja bom)
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }

    public void iniciarConsultaFaltas() {
        view.exibirMensagem("--- Minhas Faltas ---");
        if (matriculaAlunoLogado == null) {
            view.exibirErro("Nenhum aluno logado.");
            return;
        }

        try {
            List<Frequencia> faltas = alunoService.consultarFaltas(matriculaAlunoLogado);
            view.exibirLista(faltas); // (Precisamos garantir que Frequencia.toString() seja bom)
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }

    public void iniciarConsultaDisciplinasMatriculadas() {
        view.exibirMensagem("--- Minhas Disciplinas ---");
        if (matriculaAlunoLogado == null) {
            view.exibirErro("Nenhum aluno logado.");
            return;
        }

        try {
            List<Disciplina> disciplinas = alunoService.consultarDisciplinasMatriculadas(matriculaAlunoLogado);
            view.exibirLista(disciplinas);
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        view.aguardarEnter();
    }
}