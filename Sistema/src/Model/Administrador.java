package Model;

// Esta é a ENTIDADE. Ela só guarda dados.
public class Administrador extends Funcionario {

    // (Poderia ter campos específicos, como 'nivelAcesso', 'setor', etc.)

    public Administrador(String nome, String cpf, int idade, double salario) {
        super(nome, cpf, idade, salario);
    }
    @Override
    public String toString() {
        // super.toString() chama o toString() de Funcionario
        return "Admin[" + super.toString() + "]";
    }

}