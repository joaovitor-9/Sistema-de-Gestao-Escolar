public class Professor extends Funcionario{
    String especialidade;
    public Professor(String nome, String cpf, int idade, double salario,String ID, String especialidade){
        super(nome,cpf,idade,salario,ID);
        this.especialidade = especialidade;
    }
    public void consultarFaltas(){
        return ;
    }
    public void lancarFaltas(){
    }
    public void consultarNotas(){
        return;
    }
    public void lancarNotas(){
        return;
    }
    public void consultarDisciplinas(){}
    public void consultarAlunos(){}
}
