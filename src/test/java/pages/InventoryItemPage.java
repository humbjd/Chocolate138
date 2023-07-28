package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItemPage extends BasePage{
    // Elementos
    @FindBy(css = "inventory_details_name large_size")
    WebElement lblTituloProduto;

    @FindBy(css = "inventory_details_price")
    WebElement lblPrecoProduto;

    @FindBy(css = "btn btn_primary btn_small btn_inventory")
    WebElement btnAdicionarOuRemoverNoCarrinho;




    // Construtor
    public InventoryItemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Funcoes e Metodos
    public String lerTituloDoProduto(){
        return lblTituloProduto.getText();
    }

    public String lerPrecoDoPrduto(){
        return lblPrecoProduto.getText();
    }


}
