package View;

import java.util.List;
import java.util.Scanner;

public class SistemaView {
    private Scanner sc;
    public SistemaView() {
        this.sc = new Scanner(System.in);
    }
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
    public void exibirErro(String erro) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!! ERRO: " + erro);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
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
