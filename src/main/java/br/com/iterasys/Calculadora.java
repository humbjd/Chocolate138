// Pacotes
package br.com.iterasys;
// Bibliotecas e Métodos

// Classes
public class Calculadora {
    // Atributos
    public static int somarInteiros(int num1, int num2){
        int somar = num1 + num2;
        System.out.println("Soma de " + num1 + " + " + num2 + " = " + somar);
        return somar;
    }
    public static int subtrairInteiros(int num1, int num2){
        int subtrair = num1 - num2;
        System.out.println("Subtração de " + num1 + " - " + num2 + " = " + subtrair);
        return subtrair;
    }
    public static int multiplicarInteiros(int num1, int num2){
        int multiplicar = num1 * num2;
        System.out.println("Multiplicação de " + num1 + " x " + num2 + " = " + multiplicar);
        return multiplicar;
    }
    public static int dividirInteiros(int num1, int num2){
        int dividir = num1 / num2;
        System.out.println("Dividir de " + num1 + " / " + num2 + " = " + dividir);
        return dividir;
    }

}
