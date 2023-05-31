package br.com.iterasys;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        chamarEncomenda();
        Calculadora.somarInteiros(5, 7);
        Calculadora.subtrairInteiros(8, 4);
        Calculadora.multiplicarInteiros(3, 8);
        Calculadora.dividirInteiros(28, 2);
    }

    public static void chamarEncomenda(){
        int barras = 30;
        Encomenda.calcularBarrasDeChocolatePorCaixa(barras);
    }
}