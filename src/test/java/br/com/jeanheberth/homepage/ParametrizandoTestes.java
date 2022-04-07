package br.com.jeanheberth.homepage;

import br.com.jeanheberth.basepage.LoginPage;
import br.com.jeanheberth.core.BaseTests;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParametrizandoTestes extends BaseTests {
    LoginPage loginPage;

    @ParameterizedTest
    @CsvFileSource(resources = "/MassaTeste_Login.csv", numLinesToSkip = 1, delimiter = ';')
    public void testLogin_UsuarioLogadoComDadosValidos(String nomeTeste, String email, String password,
                                                       String nomeUsuario, String resultado) {

        // Clicar no botão Sign na Home page
        loginPage = homePage.clicarBotaoSignIn();

        //Preencher usuario e senha
        loginPage.preencherEmail(email);
        loginPage.preencherPassword(password);

        // Preencher botão Sign in para logar
        loginPage.clicarBotaoSignIn();

        boolean esperado_loginOK;
        if (resultado.equals("positivo")) {
            esperado_loginOK = true;
        } else {
            esperado_loginOK = false;
        }

        // Validar se o usuário está logado
        assertThat(homePage.validarUsuarioLogado(nomeUsuario), is(esperado_loginOK));

        if (esperado_loginOK) {
            homePage.clicarBotaoSignOut();
        }

        //Acessar a pagina inicial
        // homePage.clicarNoBotaoMystore();
        carregarPaginaInicial();
    }
}