package Model;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Funcionario {
    private String especialidade;
    private List<Disciplina> disciplinasLecionadas;

    public Professor(String nome, String cpf, int idade, double salario, String especialidade) {
        super(nome, cpf, idade, salario);
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("A especialidade é obrigatória.");
        }
        this.especialidade = especialidade;
        this.disciplinasLecionadas = new ArrayList<>();
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("A especialidade não pode ser nula ou vazia.");
        }
        this.especialidade = especialidade;
    }
    public List<Disciplina> getDisciplinasLecionadas() {
        return this.disciplinasLecionadas;
    }
    public void adicionarDisciplina(Disciplina disciplina) {
        if (disciplina != null) {
            this.disciplinasLecionadas.add(disciplina);
        }
    }
    @Override
    public String toString() {
        return "Professor[" + super.toString() + ", Especialidade: " + especialidade + "]";
    }
}
