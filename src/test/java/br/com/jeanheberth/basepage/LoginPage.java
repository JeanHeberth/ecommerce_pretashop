package br.com.jeanheberth.basepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static br.com.jeanheberth.core.DriveFactory.getDriver;



public class LoginPage {

    private WebDriver driver;

    private By email = By.name("email");
    private By password = By.name("password");
    private By botaSignIn = By.id("submit-login");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherEmail(String texto) {
        getDriver().findElement(email).sendKeys(texto);
    }

    public void preencherPassword(String texto) {
        getDriver().findElement(password).sendKeys(texto);
    }

    public void clicarBotaoSignIn() {
        getDriver().findElement(botaSignIn).click();
    }


}

