// Pacotes
package apiTest;
//Bibliotecas

import com.google.gson.Gson;
import entities.AccountEntity;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

// Classes
public class Account {
    // Atributos
    String userId;
    String ct = "application/json"; // contentType da API
    String jsonBody; // guardar o json que será enviado
    String uri = "https://bookstore.toolsqa.com/Account/v1/"; // Endereço base
    Response resposta; // guardar o retorno da API
    String token; // guardar o token - autenticação do usuario


    // Instanciar Classes Externas
    Gson gson = new Gson(); // Instancia o objeto de conversão classe para json

    // Metodos e Funcoes
    // Metodo #1 - Criar Usuário
    @Test(priority = 1)
    public void testCreateUser(){
        // Configura - Arrange
        AccountEntity account = new AccountEntity(); // Instancia a entidade usuário
        account.userName = "charlie309"; // entrada e saida (resultado esperado)
        account.password = "P@ss0rd1"; // entrada

        jsonBody = gson.toJson(account); // Converte a entidade usuário no formato json


        // Configura - Tradicional
        //String userName = "charlie"; // usuario
        //String password = "123456"; // senha

        // Executa - Act

        // Dado - Quando - Então
        // Given - When - Then
        resposta = (Response) given()      // dado
                .contentType(ct)                    // tipo do conteudo
                .log().all()                        // registre tudo na ida
                .body(jsonBody)                     // corpo da msg que será enviada
        .when() // quando
                .post(uri + "User")
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

    @Test(priority = 2)
    public void testGerenateToken(){
        // Configura
        // --> Dados de Entrada são fornecidos pela AccountEntity
        // --> Resultado Esperado é que ele receba um token

        // Executa
        resposta = (Response) given()
                .contentType(ct)        // tipo de conteudo
                .log().all()            // registre tudo na ida
                .body(jsonBody)         // corpo da msg que será enviado
        .when()
                .post(uri + "GenerateToken")
        .then()
                .log().all()
                .statusCode(200) // valida a comunicação
                .body("status", is("Success")) // Status = sucesso
                .body("result", is("User authorized successfully."))
                .extract()
        ; // fim do codigo testGenerateToken

        // Extração do Token
        token = resposta.jsonPath().getString("token");
        System.out.println("token: " + token);

        // valida
        Assert.assertTrue(token.length() != 0);
    }// fim do metodo de geração de token de identificação do usuario



}
