package Model;

public abstract class Funcionario extends Pessoa {
    private String id;
    private double salario;
    public Funcionario(String nome, String cpf,int idade,double salario, String id) {
        super(nome, idade,cpf);
        this.id = id;
        this.salario = salario;
    }
    public Funcionario(String nome, String cpf, int idade, double salario) {
        super(nome, idade,cpf);
        this.salario = salario;
    }
    public String getID() {
        return id;
    }
    public double getSalario() {
        return salario;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setSalario(double salario) {
        if (salario >= 0) {
            this.salario = salario;
        }else {
            System.out.println("Salário inválido.");
        }
    }
}
