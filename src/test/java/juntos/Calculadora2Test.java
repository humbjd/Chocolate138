// Pacote
package juntos;

// Bibliotecas

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

// Classes

public class Calculadora2Test {
    // Atributos

    // Por enquanto sem atributos

    // Métodos e Funções

    // Uso Compartilhado
    @DataProvider(name = "MassaMultiplicar")
    public Object[][] massaMultiplicar(){
        return new Object[][]{
                { 5, 7, 35  },
                { 2, 10, 20 },
                { 20, 0, 0  }
        }; // Fecha o return
    }



    // Testes em si



    @Test
    public void testeSomar() {
        // Arrange
        double num1 = 5;
        double num2 = 7;
        double resultadoEsperado = 12;

        // Act
        double resultadoAtual = Calculadora2.somar(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeSubtrair() {
        // Arrange
        double num1 = 7;
        double num2 = 5;
        double resultadoEsperado = 2;

        // Act
        double resultadoAtual = Calculadora2.subtrair(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeMultiplicar() {
        // Arrange
        double num1 = 7;
        double num2 = 5;
        double resultadoEsperado = 35;

        // Act
        double resultadoAtual = Calculadora2.multiplicar(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeDividir() {
        // Arrange
        double num1 = 8;
        double num2 = 2;
        double resultadoEsperado = 4;

        // Act
        double resultadoAtual = Calculadora2.dividir(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeDividirPorZero() {
        // Arrange
        double num1 = 8;
        double num2 = 0;
        double resultadoEsperado = Double.NaN;

        // Act
        double resultadoAtual = Calculadora2.dividir(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }
}

