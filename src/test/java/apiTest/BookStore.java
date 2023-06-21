package apiTest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BookStore {

    String uri = "https://bookstore.toolsqa.com/BookStore/v1/"; // Endereço Base
    String ct = "application/json"; // contentType da API
    @Test(priority = 1)
    public void testResearchBooks(){
        // Configura
        // Dados de entrada não são requeridos - basta chamar o endpoint
        // Resultado esperado é apenas o status code 200 e um json com uma lista de livros

        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authentication", "Bearer " + Account.token)





        // Valida


    }





}
