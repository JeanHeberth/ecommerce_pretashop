package br.com.jeanheberth.basepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static br.com.jeanheberth.core.DriveFactory.getDriver;

public class CarrinhoPage {

    private WebDriver driver;

    private By mensagemShoopingCart = By.cssSelector(".card-block .h1");
    private By nomeDoProduto = By.cssSelector(".product-line-info a");
    private By precoDoProduto = By.cssSelector("span.price");
    private By tamanhoDoProduto = By.xpath("//div[contains(@class,'product-line-grid-body')]//div[3]/span[contains(@class,'value')]");
    private By corDoProduto = By.xpath("//div[contains(@class,'product-line-grid-body')]//div[4]/span[contains(@class,'value')]");
    private By input_quantidadeDoProduto = By.cssSelector("input.js-cart-line-product-quantity");
    private By subTotalDoProduto = By.cssSelector("span.product-price strong");
    private By numeroDeItensTotal = By.cssSelector("span.js-subtotal");
    private By subTotalTotal = By.cssSelector("#cart-subtotal-products span.value");
    private By shippingTotal = By.cssSelector("#cart-subtotal-shipping span.value");
    private By totalTaxaExclTotal = By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(1) span.value");
    private By totalTaxaInclTotal = By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(2) span.value");
    private By totalTaxas = By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(3) span.value");

    public CarrinhoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String obter_nomeProduto() {
        return getDriver().findElement(nomeDoProduto).getText();
    }

    public String obter_precoProduto() {
        return getDriver().findElement(precoDoProduto).getText();
    }

    public String obter_tamanhoProduto() {
        return getDriver().findElement(tamanhoDoProduto).getText();
    }

    public String obter_corProduto() {
        return getDriver().findElement(corDoProduto).getText();
    }

    public String obter_quantidadeProduto() {
        return getDriver().findElement(input_quantidadeDoProduto).getAttribute("value");
    }

    public String obter_subTotalProduto() {
        return getDriver().findElement(subTotalDoProduto).getText();
    }

    public String obter_numeroItens() {
        return getDriver().findElement(numeroDeItensTotal).getText();
    }

    public String obter_subTotalTotalProduto() {
        return getDriver().findElement(subTotalTotal).getText();
    }

    public String obter_shippingTotalProduto() {
        return getDriver().findElement(shippingTotal).getText();
    }

    public String obter_totalTaxaExclTotalProduto() {
        return getDriver().findElement(totalTaxaExclTotal).getText();
    }

    public String obter_totalTaxaInclTotalProduto() {
        return getDriver().findElement(totalTaxaInclTotal).getText();
    }

    public String obter_totalTaxasProduto() {
        return getDriver().findElement(totalTaxas).getText();
    }

    public String obter_mensagemShoopingCart() {
        return getDriver().findElement(mensagemShoopingCart).getText();
    }
}
