package Exercicios;
import java.util.Scanner;

public class Armazen3D {
    private static final String[][][] armazem = new String[3][4][5];

    public static void main(String[] args) {
        inicializarArmazem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> exibirEstadoArmazem();
                case 2 -> adicionarProduto(scanner);
                case 3 -> removerProduto(scanner);
                case 4 -> moverProduto(scanner);
                case 5 -> listarProdutosAbaixoDoMinimo(10);
                case 6 -> {
                    System.out.println("Encerrando o sistema...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void inicializarArmazem() {
        for (int andar = 0; andar < 3; andar++) {
            for (int corredor = 0; corredor < 4; corredor++) {
                for (int secao = 0; secao < 5; secao++) {
                    armazem[andar][corredor][secao] = "vazio,0";
                }
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Exibir estado do armazém");
        System.out.println("2. Adicionar produto");
        System.out.println("3. Remover produto");
        System.out.println("4. Mover produto");
        System.out.println("5. Listar produtos abaixo da quantidade mínima");
        System.out.println("6. Sair");
    }

    private static void exibirEstadoArmazem() {
        for (int andar = 0; andar < 3; andar++) {
            System.out.println("Andar " + (andar + 1) + ":");
            for (int corredor = 0; corredor < 4; corredor++) {
                for (int secao = 0; secao < 5; secao++) {
                    System.out.print(armazem[andar][corredor][secao] + "\t");
                }
                System.out.println();
            }
        }
    }

    private static void adicionarProduto(Scanner scanner) {
        System.out.println("Digite andar, corredor e seção (ex: 0 1 2):");
        int andar = scanner.nextInt();
        int corredor = scanner.nextInt();
        int secao = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o nome do produto:");
        String produto = scanner.nextLine();

        System.out.println("Digite a quantidade a adicionar:");
        int quantidade = scanner.nextInt();

        String[] posicao = armazem[andar][corredor][secao].split(",");
        String produtoAtual = posicao[0];
        int quantidadeAtual = Integer.parseInt(posicao[1]);

        if (produtoAtual.equals("vazio") || produtoAtual.equals(produto)) {
            int novaQuantidade = quantidadeAtual + quantidade;
            armazem[andar][corredor][secao] = produto + "," + novaQuantidade;
            System.out.println("Produto adicionado com sucesso!");
        } else {
            System.out.println("Outro produto já está armazenado nesta posição.");
        }
    }

    private static void removerProduto(Scanner scanner) {
        System.out.println("Digite andar, corredor e seção (ex: 0 1 2):");
        int andar = scanner.nextInt();
        int corredor = scanner.nextInt();
        int secao = scanner.nextInt();

        System.out.println("Digite a quantidade a remover:");
        int quantidade = scanner.nextInt();

        String[] posicao = armazem[andar][corredor][secao].split(",");
        int quantidadeAtual = Integer.parseInt(posicao[1]);

        if (quantidadeAtual >= quantidade) {
            int novaQuantidade = quantidadeAtual - quantidade;
            armazem[andar][corredor][secao] = posicao[0] + "," + novaQuantidade;
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Quantidade insuficiente na posição.");
        }
    }

    private static void moverProduto(Scanner scanner) {
        System.out.println("Digite a posição de origem (ex: 0 1 2):");
        int origemAndar = scanner.nextInt();
        int origemCorredor = scanner.nextInt();
        int origemSecao = scanner.nextInt();

        System.out.println("Digite a posição de destino (ex: 0 1 3):");
        int destinoAndar = scanner.nextInt();
        int destinoCorredor = scanner.nextInt();
        int destinoSecao = scanner.nextInt();

        armazem[destinoAndar][destinoCorredor][destinoSecao] = armazem[origemAndar][origemCorredor][origemSecao];
        armazem[origemAndar][origemCorredor][origemSecao] = "vazio,0";
        System.out.println("Produto movido com sucesso!");
    }

    private static void listarProdutosAbaixoDoMinimo(int minimo) {
        System.out.println("Produtos abaixo de " + minimo + " unidades:");
        for (int andar = 0; andar < 3; andar++) {
            for (int corredor = 0; corredor < 4; corredor++) {
                for (int secao = 0; secao < 5; secao++) {
                    String[] posicao = armazem[andar][corredor][secao].split(",");
                    int quantidade = Integer.parseInt(posicao[1]);
                    if (quantidade > 0 && quantidade < minimo) {
                        System.out.println("Andar " + andar + ", Corredor " + corredor + ", Seção " + secao +
                                " -> " + posicao[0] + ": " + quantidade);
                    }
                }
            }
        }
    }
}
