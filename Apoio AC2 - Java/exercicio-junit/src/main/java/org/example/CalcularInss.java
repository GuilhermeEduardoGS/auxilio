package org.example;

public class CalcularInss {

    private static final double SALARIO_MINIMO = 1518.00; // exemplo

    public double calcularInss(double salario) {
        if (salario < SALARIO_MINIMO) {
            throw new SalarioInvalidoException("Salário não pode ser menor que o salário mínimo");
        }

        double taxa;

        if (salario <= 2000.00) {
            taxa = 0.10;
        } else if (salario <= 3000.00) {
            taxa = 0.15;
        } else {
            taxa = 0.20;
        }

        return salario * taxa;
    }

}
