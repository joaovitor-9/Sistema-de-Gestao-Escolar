public class Professor extends Funcionario{
    String especialidade;
    public Professor(String nome, String cpf, int idade, double salario,String id, String especialidade){
        super(nome,cpf,idade,salario,id);
        this.especialidade = especialidade;
    }
    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    public void consultarFaltas(){
        return ;
    }
    public void lancarFaltas(){}
    public void consultarNotas(){
        return;
    }
    public void lancarNotas(){
        return;
    }
    public void consultarDisciplinas(){}
    public void consultarAlunos(){}
}
