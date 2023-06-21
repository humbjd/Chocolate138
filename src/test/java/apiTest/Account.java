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
    static String token; // guardar o token - autenticação do usuario


    // Instanciar Classes Externas
    Gson gson = new Gson(); // Instancia o objeto de conversão classe para json
    AccountEntity account = new AccountEntity(); // Instancia a entidade usuário
    // Metodos e Funcoes
    // Metodo #1 - Criar Usuário
    @Test(priority = 1)
    public void testCreateUser(){
        // Configura - Arrange

        account.userName = "charlie3311"; // entrada e saida (resultado esperado)
        account.password = "P@ss0rd1"; // entrada

        jsonBody = gson.toJson(account); // Converte a entidade usuário no formato json


        // Configura - Tradicional
        //String userName = "charlie"; // usuario
        //String password = "123456"; // senha

        // Executa - Act

        // Dado - Quando - Então
        // Given - When - Then
        resposta = (Response) given()               // dado
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
         System.out.println("UserId extraido: " + userId);



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

    @Test(priority = 3)
    public void testAuthorized(){
        // Configura
        // Dados de Entrada
        // --> Fornecidos pela AccountEntity através do metodo testCreatUser - priority 1

        // Dados de Saída / Resultado Esperado
        // StatusCode = 200
        // Response Body = true

        // Dados de Saida

        // Executa
        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri + "Authorized")

        // Valida
        .then()
                .log().all()
                .statusCode(200)
                //.body(true) // ToDo: como validar o retorno do body apenas como true
        ; // fim do codigo testAuthorized

    } // fim do testAuthorized


    @Test(priority = 4)
    public void testResearchUserNotAuthorized(){
        // Configura
        // Dados de Entrada
        // userID foi extraido no metodo testCreateUser e está na memória
        // Dados de Saída / Resultado Esperado
        // Status Code = 401, Code = 1200 e Message = User not authorized!



        // Executa
        given()                                             // Dado // Comandos do Rest - Assured
                .contentType(ct)                            // Formato da mensagem
                .log().all()                                // Exibir tudo que acontece na ida
        .when()                                             // Quando
                .get(uri + "User/" + userId)           // Consulta o ususario pelo userId
        // Valida
        .then()                                             // Então
                .log().all()                                // Exibir tudo o que acontece
                .statusCode(401)            // Valida se nao esta autorizado
                .body("code", is("1200"))        // Valida o codigo de mensagem nao autorizado
                .body("message", is("User not authorized!"))
    ;// fim do codigo testResearchUser
    }


    @Test(priority = 5)
    public void testResearchUser(){
        // Configura
        // Dados de Entrada
        // userID foi extraido no metodo testCreateUser e está na memória
        // Dados de Saída / Resultado Esperado
        // userName virá da classe Account e o status code deve ser 200



        // Executa
        given()                                             // Dado // Comandos do Rest - Assured
                .contentType(ct)                            // Formato da mensagem
                .log().all()                                // Exibir tudo que acontece na ida
                .header("Authorization", "Bearer " + token)
        .when()                                             // Quando
                .get(uri + "User/" + userId)           // Consulta o ususario pelo userId
                // Valida
        .then()                                             // Então
                .log().all()                                // Exibir tudo o que acontece
                .statusCode(200)            // Valida se a conexao teve sucesso
                //.body("userId", is(userId))
                .body("username", is(account.userName)) // Valida o nome do usuário
        ;// fim do codigo testResearchUser
    }

    @Test(priority = 20)
    public void testDeleteUser(){
        // Configura
        // Dados de entrada vem do método de teste da criação do usuário (userId)
        // Resultado esperado é o código e mensagem de sucesso na exclusão do usuário

        // Executa
        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + token)
        .when()
                .delete(uri + "User/" + userId)

        // Valida
        .then()
                .log().all()
                .statusCode(204)
        ;// fim do código

    }

}
