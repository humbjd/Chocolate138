package apiTest;

import com.google.gson.Gson;
import entities.LoanEntity;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class BookStore {

    String uri = "https://bookstore.toolsqa.com/BookStore/v1/"; // Endereço Base
    String ct = "application/json"; // contentType da API

    Account account = new Account(); // Instancia a classe Account
    Gson gson = new Gson();
    LoanEntity isbn = new LoanEntity(); // Instancia a lista de livros
    public ITestContext context;
    @BeforeMethod // Antes de cada @Teste
    public void setUp(ITestContext context){

        account.testCreateUser(context);    // Cria um novo usuario
        account.testGerenateToken(context); // Gera um novo token
    }

    @AfterMethod // Depois de cada @Teste
    public void tearDown(){
        account.testDeleteUser(); // excluir o usuário

    }


    @Test(priority = 1)
    public void testResearchBooks(ITestContext context){
        // Configura
        // Dados de entrada não são requeridos - basta chamar o endpoint
        // Resultado esperado é apenas o status code 200 e um json com uma lista de livros

        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authentication", "Bearer " + context.getAttribute("token"))
        .when()
                .get(uri + "Books") // consultar a lista com todos os livros
        // Valida
        .then()
                .log().all()
                .statusCode(200)
        ;// fim do código

    }

        @Test(priority = 2)
        public void testLoanBooks(ITestContext context){ // Emprestar livros
            // Configura
            // Dados de entrada
            // userId virá pelo context
            isbn.userId = context.getAttribute("userID").toString();
            isbn.code[0] = "9781449325862";
            // Dados de saida
            // statusCode = 201
            // Retorne o isbn do livro emprestado (echo)

            // Executa
            given()
                    .log().all()
                    .contentType(ct)
                    .header("Authorization", "Bearer " + context.getAttribute("token"))
                    .body(gson.toJson(isbn))
            .when()
                    .post(uri)

            // Valida




        }



}
