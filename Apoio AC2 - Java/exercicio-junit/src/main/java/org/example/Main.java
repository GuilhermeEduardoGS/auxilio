package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CalcularInss service = new CalcularInss();

        System.out.print("Digite o salário do colaborador: ");
        double salario = sc.nextDouble();

        try {
            double desconto = service.calcularInss(salario);
            double salarioLiquido = salario - desconto;

            System.out.printf("Desconto do INSS: R$ %.2f%n", desconto);
            System.out.printf("Salário líquido: R$ %.2f%n", salarioLiquido);

        } catch (SalarioInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
