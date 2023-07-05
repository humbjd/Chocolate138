package apiTest;

import com.google.gson.Gson;
import entities.LoanEntity;
import org.testng.annotations.*;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class BookStore {

    String uri = "https://bookstore.toolsqa.com/BookStore/v1/"; // Endereço Base
    String ct = "application/json"; // contentType da API

    Account account = new Account(); // Instancia a classe Account
    Gson gson = new Gson();
    LoanEntity isbn = new LoanEntity(); // Instancia a lista de livros
    public ITestContext context;
    @BeforeClass // Antes da classe e de todos os testes antes dela
    //@BeforeMethod // Antes de cada @Teste
    public void setUp(ITestContext context){

        account.testCreateUser(context);    // Cria um novo usuario
        account.testGerenateToken(context); // Gera um novo token
    }
    @AfterClass // Depois da classe
    //@AfterMethod // Depois de cada @Teste
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
            isbn.userId = context.getAttribute("userID").toString(); // código do usuário
            isbn.collectionOfIsbns = new LoanEntity.ISBN[]{
                    new LoanEntity.ISBN("9781449325862")
            };
            //isbn.isbn = "9781449325862"; // código do livro
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
                    .post(uri + "Books")
            // Valida
            .then()
                    .log().all()
                    .statusCode(201)
                    .body("isbn", is(isbn.isbn))
            ;

        }
        @Test(priority = 3)
        public void testUpdateLoan(ITestContext context){ // Atualizar quem está com qual livro
            // Configura
            // Dados de Entrada
            // userId é extraido do BeforeMethod
            String isbnAntigo = "9781449325862"; // Livro a substituir
            String isbnNovo = "9781449331818"; // Novo livro a ser emprestado


            // alimentar a classe LoanEntity apenas com o codigo do usuario e o isbn
            isbn = new LoanEntity(); // reiniciando o objeto da classe LoanEntity
            isbn.userId = context.getAttribute("userId").toString(); // código do usuário
            isbn.isbn = isbnNovo; // codigo do livro que estava com o usuario

            // Executa
            given()
                    .log().all()
                    .contentType(ct)
                    .header("Authorization", "Bearer " + context.getAttribute("token"))
                    .body(gson.toJson(isbn))

            .when()
                    .put(uri + "Books/" + isbnAntigo)
            // Valida
            .then()
                    .log().all()
                    .statusCode(200)
                    .body("books[0].isbn", is(isbnNovo))
            ;// fim do codigo

        }

}
