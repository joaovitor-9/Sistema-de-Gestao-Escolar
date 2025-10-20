import java.util.Scanner;
public class Main {
    private static Administrador adm = new Administrador("ADMprincipa","11122233344",45,6000);
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        adm.setId("ADM001");

    }
    public static void exibirMenuPrincipal(){
        int opcao;
        do {
            System.out.println("\n--- SISTEMA DE GESTÃO ESCOLAR (Terminal) ---");
            System.out.println("1. Acessar como administrador ");
            System.out.println("2. Acessar como Professor ");
            System.out.println("3. Acessar como Aluno ");
            System.out.println("4. Sair");
            System.out.print(">> Escolha uma opção: ");

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1: menuAdiminstrador(); break;
                    case 2: /*menuProfessor();*/ break;
                    case 3: /*menuAluno(); */break;
                    case 4:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Digite um número.");
                sc.nextLine();
                opcao = 0;
            }
        } while (opcao != 3);
    }
    public static void menuAdiminstrador(){
        int opcao;
        do {
            System.out.println("\n--- SISTEMA DE GESTÃO ESCOLAR (Terminal) ---");
            System.out.println("1. Gerenciar Alunos");
            System.out.println("2. Gerenciar Professores");
            System.out.println("3. Sair");
            System.out.print(">> Escolha uma opção: ");

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1: menuGerenciarAlunos(); break;
                    case 2: menuGerenciarProfessores(); break;
                    case 3:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Digite um número.");
                sc.nextLine();
                opcao = 0;
            }
        } while (opcao != 3);
    }

    // -----------------------------------------------------------------
    // Menu: Gerenciar Alunos
    // -----------------------------------------------------------------

    private static void menuGerenciarAlunos() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Alunos ---");
            System.out.println("1. Cadastrar Novo Aluno");
            System.out.println("2. Consultar Todos Alunos");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print(">> Escolha uma opção: ");

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1: cadastrarAluno(); break;
                    case 2: adm.consultarAlunos(); break; // Chama Caso de Uso
                    case 3: return;
                    default: System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Entrada inválida. Digite um número.");
                sc.nextLine();
                opcao = 0;
            }
        } while (opcao != 3);
    }

    // Lógica para coletar dados e chamar o Caso de Uso de Cadastro de Aluno
    private static void cadastrarAluno() {
        System.out.println("\n--- Cadastro de Aluno ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Idade: ");

        if (sc.hasNextInt()) {
            int idade = sc.nextInt();
            sc.nextLine();

            // CHAMA O CASO DE USO NO ADMINISTRADOR
            adm.cadastrarAluno(nome, cpf, idade);
        } else {
            System.out.println("Idade inválida. Cadastro cancelado.");
            sc.nextLine();
        }
    }

    // -----------------------------------------------------------------
    // Menu: Gerenciar Professores
    // -----------------------------------------------------------------

    private static void menuGerenciarProfessores() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Professores ---");
            System.out.println("1. Cadastrar Novo Professor");
            System.out.println("2. Consultar Todos Professores");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print(">> Escolha uma opção: ");

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1: cadastrarProfessor(); break;
                    case 2: adm.consultarProfessor(); break; // Chama Caso de Uso
                    case 3: return; // Volta ao menu principal
                    default: System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Entrada inválida. Digite um número.");
                sc.nextLine();
                opcao = 0;
            }
        } while (opcao != 3);
    }

    // Lógica para coletar dados e chamar o Caso de Uso de Cadastro de Professor
    private static void cadastrarProfessor() {
        System.out.println("\n--- Cadastro de Professor ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Idade: ");
        int idade = (sc.hasNextInt() ? sc.nextInt() : -1);
        sc.nextLine();
        System.out.print("Salário: ");
        double salario = (sc.hasNextDouble() ? sc.nextDouble() : -1.0);
        sc.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = sc.nextLine();

        if (idade > 0 && salario >= 0) {
            // CHAMA O CASO DE USO NO ADMINISTRADOR
            adm.cadastrarProfessor(nome, cpf, idade, especialidade,salario);
        } else {
            System.out.println("Dados numéricos inválidos. Cadastro cancelado.");
        }
    }

}