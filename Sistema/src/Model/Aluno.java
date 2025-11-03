package Model;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
    private final String matricula;
    private List<Disciplina> disciplinasMatriculadas;
    private List<Nota> notas;
    private List<Frequencia> faltas;

    public Aluno(String nome, int idade, String cpf, String matricula) {
        super(nome, idade, cpf);
        
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("A matrícula é obrigatória.");
        }
        this.matricula = matricula;
        this.disciplinasMatriculadas = new ArrayList<>();
        this.notas = new ArrayList<>();
        this.faltas = new ArrayList<>();
    }
    public String getMatricula() {
        return matricula;
    }
    public List<Disciplina> getDisciplinas() {
        return disciplinasMatriculadas;
    }
    public List<Nota> getNotas() {
        return notas;
    }
    public List<Frequencia> getFaltas() {
        return faltas;
    }
    public void adicionarDisciplina(Disciplina d) {
        this.disciplinasMatriculadas.add(d);
    }
    public void adicionarNota(Nota n) {
        this.notas.add(n);
    }
    @Override
    public String toString() {
        return "Aluno[" + super.toString() + ", Matrícula: " + matricula + "]";
    }
}
