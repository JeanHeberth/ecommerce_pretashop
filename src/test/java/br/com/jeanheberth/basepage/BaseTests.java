package br.com.jeanheberth.basepage;

import br.com.jeanheberth.pages.HomePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTests {

    private static WebDriver driver;
    protected HomePage homePage;


    @BeforeAll
    public static void inicializa() {
        System.setProperty("webdriver.chrome.driver", "D:\\Documentos\\Tudo refente a testes de software\\Desenvolvimentos TestesAutomatizados\\DriversTestes\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void carregarPaginaInicial() {
        driver.get("https://marcelodebittencourt.com/demoprestashop");
        homePage = new HomePage(driver);
    }

    @AfterAll
    public static void finaliza() {
        driver.quit();
    }


}
