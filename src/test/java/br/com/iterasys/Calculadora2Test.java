// Pacotes
package br.com.iterasys;
// Bibliotecas


import juntos.Calculadora2;
import org.testng.Assert;
import org.testng.annotations.Test;

// Classe
public class Calculadora2Test {
    // Atributos

    // Metodos e Funcoes
    @Test
    public void testeSomar(){
        // Configura / Arrange
        double num1 = 5;
        double num2 = 7;


        double resultadoEsperado = 12;


        // Executa / Act
        double resultadoAtual = Calculadora2.somar(num1, num2);


        // Valida / Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }


}
