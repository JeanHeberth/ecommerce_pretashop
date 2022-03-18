package br.com.jeanheberth.basepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static br.com.jeanheberth.core.DriveFactory.getDriver;

public class ChekoutPage {

    private WebDriver driver;

    private By totalTaxaInclTotal = By.cssSelector(".cart-total span.value");
    private By botaContinueAdrress = By.name("confirm-addresses");
    private By nomeCliente = By.cssSelector("div.address");
    private By shippingValor = By.cssSelector("span.carrier-price");
    private By botaContinue = By.name("confirmDeliveryOption");
    private By payByCheck = By.id("payment-option-1");
    private By iAgreToTerms = By.id("conditions_to_approve[terms-and-conditions]");
    private By botaoOrderWith = By.id("payment-confirmation");
    private By amountPayByCheck = By.cssSelector("#payment-option-1-additional-information > section > dl > dd:nth-child(2)");


    public ChekoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public String obter_totalTaxaInclTotalProduto() {
        return getDriver().findElement(totalTaxaInclTotal).getText();

    }

    public void clicarNoBotaoContinueAdrress() {
        getDriver().findElement(botaContinueAdrress).click();
    }

    public String obter_nomeCliente() {
        return getDriver().findElement(nomeCliente).getText();
    }

    public String obter_shippingValor() {
        return getDriver().findElement(shippingValor).getText();
    }

    public void clicarNoBotaoContinueShipping() {
        getDriver().findElement(botaContinue).click();
    }

    public void selecionarRadiopayByCheck() {
        getDriver().findElement(payByCheck).click();
    }

    public void clicarNoBotaoiAgreToTerms() {
        getDriver().findElement(iAgreToTerms).click();
    }

    public void clicarNoBotaobotaoOrderWith() {
        getDriver().findElement(botaoOrderWith).click();
    }

    public String obter_mensagem_amountPayByCheck() {
        return getDriver().findElement(amountPayByCheck).getText();

    }
}
