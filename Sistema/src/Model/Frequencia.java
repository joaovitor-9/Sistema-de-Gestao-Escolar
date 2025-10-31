package Model;

import java.time.LocalDate;

// Esta classe representa UM dia de falta
public class Frequencia {

    private final Aluno aluno;
    private final Disciplina disciplina;
    private final LocalDate data;

    public Frequencia(Aluno aluno, Disciplina disciplina) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.data = LocalDate.now(); // Registra a falta no dia de hoje
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