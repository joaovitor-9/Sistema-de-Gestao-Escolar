package Model;

public class Nota {

    private final Aluno aluno;
    private final Disciplina disciplina;
    private double valorNota;

    public Nota(Aluno aluno, Disciplina disciplina, double valorNota) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        setValorNota(valorNota);
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public double getValorNota() {
        return valorNota;
    }

    public void setValorNota(double valorNota) {
        if (valorNota < 0 || valorNota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
        this.valorNota = valorNota;
    }

    @Override
    public String toString() {
        return "Nota[Disciplina: " + disciplina.getNome() + ", Aluno: " + aluno.getNome() + ", Valor: " + valorNota + "]";
    }
}