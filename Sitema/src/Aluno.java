public class Aluno extends Pessoa {
    private String matricula;
    public Aluno(String nome, String cpf,int idade, String matricula) {
        super(nome, idade, cpf);
        this.matricula = matricula;
    }
    public String getMatricula() }
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public void consultarFaltas(){
        return;
    }
    public void consultarDisciplinas(){
        return;
    }
    public void consultarNotas() {
        return;
    }
}
