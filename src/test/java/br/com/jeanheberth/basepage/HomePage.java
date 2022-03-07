package br.com.jeanheberth.basepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static br.com.jeanheberth.core.DriveFactory.getDriver;


import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    List<WebElement> listaProdutos = new ArrayList<>();

    private By produtos = By.className("product-description");
    private By descricoesDosProdutos = By.cssSelector(".product-description a");
    private By precoDosProdutos = By.className("price");
    private By textoProdutosNoCarrinho = By.className("cart-products-count");
    private By botaoSignIn = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
    private By userLogado = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
    private By botaoMyStore = By.id("_desktop_logo");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public int contarProdutos() {
        carregarListaProdutos();
        return listaProdutos.size();
    }

    private void carregarListaProdutos() {
        listaProdutos =   getDriver().findElements(produtos);
    }

    public int obterQuantidadeProdutosNoCarrinho() {
        String quantidadeProdutosNoCarrinho = getDriver().findElement(textoProdutosNoCarrinho).getText();
        quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace("(", "");
        quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace(")", "");

        int qtdProdutosNoCarrinho = Integer.parseInt(quantidadeProdutosNoCarrinho);
        return qtdProdutosNoCarrinho;

    }

    public String obterNomeProduto(int indice) {
        return getDriver().findElements(descricoesDosProdutos).get(indice).getText();
    }

    public String obterPrecoProduto(int indice) {
        return getDriver().findElements(precoDosProdutos).get(indice).getText();

    }

    public ProdutoPage clicarProduto(int indice) {
        getDriver().findElements(descricoesDosProdutos).get(indice).click();
        return new ProdutoPage(getDriver());
    }

    public LoginPage clicarBotaoSignIn(){
        getDriver().findElement(botaoSignIn).click();
        return new LoginPage(getDriver());
    }

    public boolean validarUsuarioLogado(String texto){
       return texto.contentEquals(getDriver().findElement(userLogado).getText()) ;
    }

    public void clicarNoBotaoMystore(){
        getDriver().findElement(botaoMyStore).click();

    }
}
