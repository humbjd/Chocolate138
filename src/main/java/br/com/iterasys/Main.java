package br.com.iterasys;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        chamarEncomenda();
        Calculadora.somarInteiros(5, 7);
        Calculadora.subtrairInteiros(8, 4);
        Calculadora.multiplicarInteiros(3, 8);
        chamarDividirInteiros();
    }

    public static void chamarEncomenda(){
        int barras = 30;
        Encomenda.calcularBarrasDeChocolatePorCaixa(barras);
    }
    public static void chamarDividirInteiros(){
        Calculadora.dividirInteiros(28, 2);
    }
}