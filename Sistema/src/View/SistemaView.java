package View;

import java.util.List;
import java.util.Scanner;

/**
 * Esta é a camada de Visão.
 * ÚNICA responsabilidade: Exibir dados e ler entradas.
 * NÃO PODE ter lógica de negócio (if/else de regra, try/catch de serviço).
 * NÃO PODE conhecer o Service ou o Repositório.
 */
public class SistemaView {

    private Scanner sc;

    public SistemaView() {
        this.sc = new Scanner(System.in);
    }

    // --- Métodos de Saída ---

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirErro(String erro) {
        // Formata o erro para se destacar
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!! ERRO: " + erro);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    /**
     * Um método genérico para exibir qualquer lista de objetos.
     * Ele confia no método .toString() dos objetos (Aluno, Professor, etc.)
     */
    public void exibirLista(List<?> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Nenhum item encontrado.");
            return;
        }

        System.out.println("--- Lista de Itens ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + ": " + lista.get(i).toString());
        }
        System.out.println("------------------------");
    }

    public void aguardarEnter() {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }

    // --- Métodos de Entrada ---

    public String solicitarString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public int solicitarInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                exibirErro("Entrada inválida. Por favor, digite um número inteiro.");
            }
        }
    }

    public double solicitarDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                exibirErro("Entrada inválida. Por favor, digite um número (ex: 8.5).");
            }
        }
    }
}