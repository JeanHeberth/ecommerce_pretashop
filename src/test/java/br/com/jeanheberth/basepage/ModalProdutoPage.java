package br.com.jeanheberth.basepage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static br.com.jeanheberth.core.DriveFactory.getDriver;

public class ModalProdutoPage {

    private WebDriver driver;

    private By mensagemProdutoAdicionadoComSucesso = By.id("myModalLabel");
    private By descricaoProduto = By.className("product-name");
    private By precoProduto = By.cssSelector("div.modal-body p.product-price");
    private By listaTamanhoProdutos = By.cssSelector("div.divide-right .col-md-6:nth-child(2) span strong");
    private By subTotal = By.cssSelector(".cart-content p:nth-child(2) span.value");
    private By botaoProceedToCheckout = By.cssSelector("div .cart-content-btn a.btn-primary");

    public ModalProdutoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String mensagemDeInseridoComSucesso() {
        FluentWait wait = new FluentWait(getDriver()).withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensagemProdutoAdicionadoComSucesso));
        return getDriver().findElement(mensagemProdutoAdicionadoComSucesso).getText();
    }

    public String obterDescricaoProduto() {
        return getDriver().findElement(descricaoProduto).getText();
    }

    public String obterPrecoProduto() {
        return getDriver().findElement(precoProduto).getText();
    }

    public String obterTamanhoProduto() {
        return getDriver().findElements(listaTamanhoProdutos).get(0).getText();
    }

    public String obterCorProduto() {
        return getDriver().findElements(listaTamanhoProdutos).get(1).getText();
    }

    public String obterQuantidadeProduto() {
        return getDriver().findElements(listaTamanhoProdutos).get(2).getText();
    }

    public String obterSubtotal() {
        return getDriver().findElement(subTotal).getText();
    }

    public CarrinhoPage clicarBotaoProceedToCheckout(){
         getDriver().findElement(botaoProceedToCheckout).click();
         return new CarrinhoPage(getDriver());
    }

}
