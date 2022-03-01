package br.com.jeanheberth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

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
        return driver.findElement(nomeProduto).getText();
    }

    public String obterPrecoProduto() {
        return driver.findElement(precoProduto).getText();
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
        return new Select(driver.findElement(tamanhoProduto));
    }

    public void selecionarCorDoProduto() {
        driver.findElement(corDoProduto).click();
    }

    public void alterarQuantidadeDoProduto(int quantidade) {
        driver.findElement(quantidadeDoProduto).clear();
        driver.findElement(quantidadeDoProduto).sendKeys(Integer.toString(quantidade));
    }

    public void adicionarProdutoNoCarrinho() {
        driver.findElement(clicarBotaoAdiconarNoCarrinho).click();
    }
}
