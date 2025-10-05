public abstract class Funcionario extends Pessoa {
    private String ID;
    private double salario;
   public Funcionario(String nome, String cpf,int idade,double salario, String ID) {
        super(nome, idade,cpf);
        this.ID = ID;
        this.salario = salario;
    }

}
