package br.com.jeanheberth.basepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static br.com.jeanheberth.core.DriveFactory.getDriver;

public class ProdutoPage {

    private WebDriver driver;

    private By nomeProduto = By.className("h1");
    private By precoProduto = By.cssSelector(".current-price span:nth-child(1)");
    private By tamanhoProduto = By.id("group_1");
    private By corDoProduto = By.xpath("//ul[@id='group_2']//input[@value='11']");
    private By quantidadeDoProduto = By.id("quantity_wanted");
    private By clicarBotaoAdiconarNoCarrinho = By.className("add-to-cart");

    public ProdutoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String obterNomeProduto() {
        return getDriver().findElement(nomeProduto).getText();
    }

    public String obterPrecoProduto() {
        return getDriver().findElement(precoProduto).getText();
    }

    public void selecionarOpcaoDropDown(String opcao) {
        encontarDropDownSize().selectByVisibleText(opcao);
    }

    public List<String> obterOpcoesSelecionadas() {
        List<WebElement> elementosSelecionados = encontarDropDownSize().getAllSelectedOptions();

        List<String> listaOpcoesSelecionadas = new ArrayList<>();
        for (WebElement elemento : elementosSelecionados) {
            listaOpcoesSelecionadas.add(elemento.getText());
        }
        return listaOpcoesSelecionadas;
    }

    public Select encontarDropDownSize() {
        return new Select(getDriver().findElement(tamanhoProduto));
    }

    public void selecionarCorDoProduto() {
        driver.findElement(corDoProduto).click();
    }

    public void alterarQuantidadeDoProduto(int quantidade) {
        getDriver().findElement(quantidadeDoProduto).clear();
        getDriver().findElement(quantidadeDoProduto).sendKeys(Integer.toString(quantidade));
    }

    public ModalProdutoPages adicionarProdutoNoCarrinho() {
        getDriver().findElement(clicarBotaoAdiconarNoCarrinho).click();
        return new ModalProdutoPages(getDriver());
    }
}
