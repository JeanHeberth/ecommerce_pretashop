package br.com.jeanheberth.basepage;

import br.com.jeanheberth.util.Funcoes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static br.com.jeanheberth.core.DriveFactory.getDriver;

public class PedidoPage {

    private WebDriver driver;

    private By textoPedidoConfirmado = By.cssSelector("#content-hook_order_confirmation h3");
    private By textoEmail = By.cssSelector("#content-hook_order_confirmation p");
    private By totalProdutos = By.cssSelector("div.order-confirmation-table div.order-line div.row div.bold");
    private By totalTaxasIncl = By.cssSelector("div.order-confirmation-table table tr.total-value td:nth-child(2)");
    private By metodoPagamento = By.cssSelector("#order-details ul li:nth-child(2)");


    public PedidoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String obter_textoPedidoConfirmado() {
        return getDriver().findElement(textoPedidoConfirmado).getText();
    }

    public String obter_textoEmailConfirmado() {
        String texto = getDriver().findElement(textoEmail).getText();
        texto = Funcoes.removeTexto(texto, "An email has been sent to the ");
        texto = Funcoes.removeTexto(texto, " address.");
        return texto;
    }

    public Double obter_totalProdutos() {
        return Funcoes.removeCifraoDevolveDouble(getDriver().findElement(totalProdutos).getText());
    }

    public Double obter_totalTaxasIncl() {
        return Funcoes.removeCifraoDevolveDouble(getDriver().findElement(totalTaxasIncl).getText());
    }

    public String obter_metodoPagamento(){
        //Payment method: Payments by check
        return Funcoes.removeTexto(getDriver().findElement(metodoPagamento).getText(), "Payment method: Payments by ");
    }
}
