package steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class selectProduct {
    // Atributos
    //
    WebDriver driver;

    @BeforeAll // Executa antes de todos os blocos de passos --> usar do Cucumber
    public void before_all(){
        ChromeOptions options = new ChromeOptions();        // Instancia o objeto de Opçoes do ChromeDriver
        options.addArguments("--remote-allow-origins=*");   // Permite qualquer origem remota
        WebDriverManager.chromedriver().setup();            // baixar a versão mais atual do ChromeDriver
        driver = new ChromeDriver(options);                 // Instancia o objeto do Selenium como ChromeDriver

        // Estabelece uma espera implicita de 5 segundos para carregar qualuqer elemento
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        driver.manage().window().maximize();                // Maximiza a janela do navegador

    }

    @AfterAll // Executa apos todos os blocos de passos --> usar do Cucumber
    public void after_all(){
        driver.quit();                                      // Encerrar o Selenium Webdriver
    }


    @Given("I access SauceDemo store")
    public void i_access_sauce_demo_store() {
        driver.get("https://www.saucedemo.com");
    }
    @When("I filled a user {string} and password {string}")
    public void i_filled_a_user_and_password(String user, String password) {
        driver.findElement(By.id("user-name")).sendKeys(user); // Escreve o conteúdo da váriavel user
        driver.findElement(By.id("password")).sendKeys(password); // Escreve o conteúdo da váriavel password
    }
    @When("click in Login")
    public void click_in_login() {
        driver.findElement(By.id("login-button")).click(); // Clica no botão Login
    }
    @Then("show page's title {string}")
    @Then("I verify the page's title {string}")
    public void show_page_s_title(String pageTitle) {
        // Varifica se o titulo da página coincide com o informado na feature
        assertEquals(driver.findElement(By.cssSelector("span.title")).getText(), pageTitle);
    }
    @Then("show cart's link")
    public void show_cart_s_link() {
        // Verifica se o elemento do carrinho de compras está visível
        assertTrue(driver.findElement(By.id("shopping_cart_container")).isDisplayed());
    }
    @When("I click in product {string}")
    public void i_click_in_product(String productId) {
        // Clica no elemento correspondente ao código do produto informado na feature
        driver.findElement(By.id("item_"+ productId +"_title_link")).click();
    }
    @Then("I verify the product title {string}")
    public void i_verify_the_product_title(String productTitle) {
        // Verifica se o titulo do produto corresponde ao informado na feature
        assertEquals(driver.findElement(By.cssSelector("div.inventory_details_name.large_size")).getText(),
                productTitle);
    }
    @Then("I verify the product price {string}")
    public void i_verify_the_product_price(String productPrice) {
        // Verifica se o preço do produto corresponde ao informado
        assertEquals(driver.findElement(By.cssSelector("div.inventory_details_price")).getText(), productPrice);
    }
    @When("I click in Add to Cart")
    public void i_click_in_add_to_cart() {
        // Clica no botão de adicionar no carrinho
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
    }
    @When("I click in Cart icon")
    public void i_click_in_cart_icon() {
        // Clica no icone do carrinho de compras
        driver.findElement(By.id("shopping_cart_container"));
    }

    @Then("I verify the quantity is {string}")
    public void i_verify_the_quantity_is(String quantity) {
        // Verifica se a quantidade corresponde a feature
        assertEquals(driver.findElement(By.cssSelector("div.cart_quantity")).getText(), quantity);
    }
}