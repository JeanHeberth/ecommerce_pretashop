package br.com.jeanheberth.core;

import br.com.jeanheberth.basepage.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static br.com.jeanheberth.core.DriveFactory.getDriver;
import static br.com.jeanheberth.core.DriveFactory.killDriver;

public class BaseTests {

  //  private static WebDriverManager driverManager;
  //  private static WebDriver driver;
    protected HomePage homePage;



    @BeforeEach
    public void carregarPaginaInicial() {
        homePage = new HomePage(getDriver());

    }

    @AfterAll
    public static void finaliza() {
        killDriver();
    }


}
