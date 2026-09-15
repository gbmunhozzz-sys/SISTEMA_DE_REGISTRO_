package org.example;

import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
    class SistemaRegistro {
    public static void main(String[] args) {
        // Configura o Scanner para aceitar o padrão brasileiro de números (vírgula)
        Scanner scanner = new Scanner(System.in, "UTF-8");
        scanner.useLocale(new Locale("pt", "BR"));

        // Variáveis de controle e estatísticas
        int quantidadePedidos = 0;
        double valorTotal = 0.0;
        double maiorValor = 0.0;
        double menorValor = 0.0;

        // Variáveis para os Desafios Extras
        int comprasAcimaDe50 = 0;
        String nomeMaiorCompra = "";

        int opcao = 0;

        System.out.println("=== SISTEMA DE PEDIDOS ===");

        do {
            // 1. Solicitar o nome do cliente
            System.out.print("\nNome do cliente: ");
            String nome = scanner.nextLine();

            // 2. Solicitar o valor da compra com validação (deve ser maior que zero)
            double valorCompra = 0;
            boolean valorValido = false;

            while (!valorValido) {
                System.out.print("Valor da compra: R$ ");
                if (scanner.hasNextDouble()) {
                    valorCompra = scanner.nextDouble();
                    scanner.nextLine(); // Limpa o buffer do teclado

                    if (valorCompra > 0) {
                        valorValido = true;
                    } else {
                        System.out.println("Erro: O valor da compra deve ser maior que zero!");
                    }
                } else {
                    System.out.println("Erro: Digite um valor numérico válido (ex: 35,50).");
                    scanner.next(); // Descarta a entrada inválida
                    scanner.nextLine(); // Limpa o buffer
                }
            }

            // Atualização de contadores e acumuladores
            quantidadePedidos++;
            valorTotal += valorCompra;

            // Lógica para definir maior e menor compra, além do cliente destaque
            if (quantidadePedidos == 1) {
                maiorValor = valorCompra;
                menorValor = valorCompra;
                nomeMaiorCompra = nome;
            } else {
                if (valorCompra > maiorValor) {
                    maiorValor = valorCompra;
                    nomeMaiorCompra = nome;
                }
                if (valorCompra < menorValor) {
                    menorValor = valorCompra;
                }
            }

            // Desafio Extra 1: Contabilizar compras superiores a R$ 50,00
            if (valorCompra > 50.0) {
                comprasAcimaDe50++;
            }

            // 4. Perguntar ao operador se deseja cadastrar um novo pedido
            System.out.println("\nCadastrar novo pedido?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            System.out.print("Escolha uma opção: ");

            while (true) {
                if (scanner.hasNextInt()) {
                    opcao = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer
                    if (opcao == 1 || opcao == 2) {
                        break;
                    }
                } else {
                    scanner.next(); // Descarta entrada inválida
                }
                System.out.print("Opção inválida. Digite 1 para Sim ou 2 para Não: ");
            }

            // 5. Repetir o processo até o usuário escolher encerrar (2)
        } while (opcao == 1);

        // Exibição do Relatório Final
        System.out.println("\n===== RELATÓRIO FINAL =====");

        if (quantidadePedidos > 0) {
            double ticketMedio = valorTotal / quantidadePedidos;

            System.out.printf("Quantidade de pedidos: %d\n", quantidadePedidos);
            System.out.printf("Valor total vendido: R$ %.2f\n", valorTotal);
            System.out.printf("Ticket médio: R$ %.2f\n", ticketMedio);
            System.out.printf("Maior compra: R$ %.2f (Cliente: %s)\n", maiorValor, nomeMaiorCompra);
            System.out.printf("Menor compra: R$ %.2f\n", menorValor);
            System.out.printf("Compras acima de R$ 50,00: %d\n", comprasAcimaDe50);
        } else {
            System.out.println("Nenhum pedido foi cadastrado hoje.");
        }

        scanner.close();
    }
}
