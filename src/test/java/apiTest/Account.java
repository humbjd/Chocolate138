// Pacotes
package apiTest;
//Bibliotecas

import com.google.gson.Gson;
import entities.AccountEntity;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

// Classes
public class Account {
    // Atributos
    String userId;
    // Instanciar Classes Externas
    Gson gson = new Gson(); // Instancia o objeto de conversão classe para json

    // Metodos e Funcoes
    // Metodo #1 - Criar Usuário
    @Test
    public void testCreateUser(){
        // Configura - Arrange
        AccountEntity account = new AccountEntity(); // Instancia a entidade usuário
        account.userName = "charlie307"; // entrada e saida (resultado esperado)
        account.password = "P@ss0rd1"; // entrada

        String jsonBody = gson.toJson(account); // Converte a entidade usuário no formato json


        // Configura - Tradicional
        //String userName = "charlie"; // usuario
        //String password = "123456"; // senha

        // Executa - Act

        // Dado - Quando - Então
        // Given - When - Then
        Response resposta = (Response) given()      // dado
                .contentType("application/json")    // tipo do conteudo
                .log().all()                        // registre tudo
                .body(jsonBody)                     // corpo da msg que será enviada
        .when() // quando
                .post("https://bookstore.toolsqa.com/Account/v1/User")
        // Valida - Assert
        .then() // então
                .log().all()                       // registre tudo na volta
                .statusCode(201)   // valide a comunicação
                .body("username", is(account.userName))
                .extract()
        ; // fim da linha do REST-assured

        // extrair o UserID (identificação do usuário)
         userId = resposta.jsonPath().getString("userID");
         System.out.println("UserID extraido: " + userId);



    } // fim do método de criação de usuário

}
