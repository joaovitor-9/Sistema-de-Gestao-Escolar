package Model;

public class Administrador extends Funcionario {
    
    public Administrador(String nome, String cpf, int idade, double salario) {
        super(nome, cpf, idade, salario);
    }
    @Override
    public String toString() {
        return "Admin[" + super.toString() + "]";
    }
}
