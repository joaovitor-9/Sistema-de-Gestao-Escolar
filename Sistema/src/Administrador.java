import java.util.List;

public class Administrador extends Funcionario {
    public Administrador(String nome, String cpf,int idade,double salario){
        super(nome, cpf, idade, salario);
    }
    public void matricularAluno(){}
    public void criarDisciplina(){}
    public boolean cadastrarAluno(String nome, String cpf, int idade){
        if (RepositorioSistema.cpfJaCadastrado(cpf)) {
            System.out.println("ERRO: CPF " + cpf + " já cadastrado no sistema.");
            return false;
        }

        Aluno novoAluno = new Aluno(nome, cpf, idade);

        String novaMatricula = RepositorioSistema.gerarNovaMatricula();
        novoAluno.setMatricula(novaMatricula);

        RepositorioSistema.adicionarAluno(novoAluno);
        System.out.println("SUCESSO: Aluno " + nome + " cadastrado com a matrícula: " + novaMatricula);
        return true;
    }
    public boolean cadastrarProfessor(String nome, String cpf, int idade, String especialidade,double salario) {
        if  (RepositorioSistema.cpfJaCadastrado(cpf)) {
            System.out.println("ERRO: CPF " +cpf+ " já cadastrado no sistema.");
            return false;
        }
        Professor novoProfessor = new Professor(nome,cpf,idade,salario,especialidade);

        String novoID = RepositorioSistema.gerarNovoIDProfessor();
        novoProfessor.setId(novoID);

        RepositorioSistema.adicionarProfessor(novoProfessor);
        System.out.println("SUCESSO: Professor " + nome + " cadastrado com o ID: " + novoID);
        return true;
    }
    public void consultarProfessor(){
        List<Professor> lista = RepositorioSistema.listarProfessores();
        if (lista.isEmpty()) {
            System.out.println("ERRO:Nenhum Professor encontrado ");
        }else{
            for(Professor p : lista){
                System.out.printf("Nome: %s ID: %S Especialidade: %s\n",p.getNome(),p.getID(),p.getEspecialidade() );
            }
        }
    }
    public void consultarAlunos(){
        List<Aluno> lista  = RepositorioSistema.listarAlunos();
        if  (lista.isEmpty()) {
            System.out.println("ERRO: Nenhum aluno encontrado");
        }
        else {
            for (Aluno a : lista) {
                System.out.printf("Nome: %s Matricula: %s  \n", a.getNome() , a.getMatricula());
            }
        }
    }
    public void consultarNotas(){}
    public void consultarFaltas(){}
}
