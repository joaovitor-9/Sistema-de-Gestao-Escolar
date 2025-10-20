public class Aluno extends Pessoa {
    private String matricula;
    public Aluno(String nome, String cpf,int idade ) {
        super(nome, idade, cpf);
    }
    public void consultarNotas(){
        return ;
    };
    public void consultarFaltas(){
        return ;
    };
    public void consultarDisciplinas(){
        return ;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
