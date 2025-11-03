package Model;

import java.time.LocalDate;

public class Frequencia {
    private final Aluno aluno;
    private final Disciplina disciplina;
    private final LocalDate data;

    public Frequencia(Aluno aluno, Disciplina disciplina) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.data = LocalDate.now();
    }
    public Aluno getAluno() {
        return aluno;
    }
    public Disciplina getDisciplina() {
        return disciplina;
    }
    public LocalDate getData() {
        return data;
    }
    @Override
    public String toString() {
        return "Falta[Disciplina: " + disciplina.getNome() + ", Aluno: " + aluno.getNome() + ", Data: " + data + "]";
    }
}
