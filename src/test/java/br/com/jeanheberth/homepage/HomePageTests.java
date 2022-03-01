package br.com.jeanheberth.homepage;

import br.com.jeanheberth.basepage.BaseTests;
import br.com.jeanheberth.pages.LoginPage;
import br.com.jeanheberth.pages.ProdutoPage;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HomePageTests extends BaseTests {

    @Test
    public void testContarProdutos_oitoProdutosDiferente() {
        carregarPaginaInicial();
        assertThat(homePage.contarProdutos(), is(8));
    }

    @Test
    public void testValidarCarrinhoZerado() {
        int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
        // System.out.println("Quantidades: " +produtosNoCarrinho);
        assertThat(produtosNoCarrinho, is(0));
    }

    ProdutoPage produtoPage;

    @Test
    public void testValidarDetalhesDoProduto_DescricaoEValorIguais() {
        int indice = 0;
        String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
        String precoProduto_HomePage = homePage.obterPrecoProduto(indice);

        produtoPage = homePage.clicarProduto(indice);

        String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
        String precoProduto_ProdtuoPage = produtoPage.obterPrecoProduto();


        System.out.println(nomeProduto_HomePage);
        System.out.println(nomeProduto_ProdutoPage);
        assertThat(nomeProduto_HomePage.toUpperCase(), is(nomeProduto_ProdutoPage.toUpperCase()));
        assertThat(precoProduto_HomePage, is(precoProduto_ProdtuoPage));

    }

    LoginPage loginPage;

    @Test
    public void testLoginComSucesso() {
        // Clicar no botão Sign na Home page
        loginPage = homePage.clicarBotaoSignIn();

        //Preencher usuario e senha
        loginPage.preencherEmail("jeanheberth19@gmail.com");
        loginPage.preencherPassword("Teste123@#");

        // Preencher botão Sign in para logar
        loginPage.clicarBotaoSignIn();

        // Validar se o usuário está logado
        assertThat(homePage.validarUsuarioLogado("Jean Heberth Souza Vieira"), is(true));

        //Acessar a pagina inicial
        carregarPaginaInicial();
    }

    @Test
    public void incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso() {
        //Pre condicao
        //usuario logado
        if (!homePage.validarUsuarioLogado("Jean Heberth Souza Vieira")) {
            testLoginComSucesso();
        }
        //Selecioando produto
        testValidarDetalhesDoProduto_DescricaoEValorIguais();

        //Selecionar Tamanho
        List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();
        System.out.println(listaOpcoes.get(0));
        System.out.println("Tamanho da lista: " + listaOpcoes.size());

        produtoPage.selecionarOpcaoDropDown("M");

        listaOpcoes = produtoPage.obterOpcoesSelecionadas();
        System.out.println(listaOpcoes.get(0));
        System.out.println("Tamanho da lista: " + listaOpcoes.size());

        //Selecionar Cor
        produtoPage.selecionarCorDoProduto();

        //Selecionar Quantidade
        produtoPage.alterarQuantidadeDoProduto(105);

        //Adicionar no Carrinho
        produtoPage.adicionarProdutoNoCarrinho();


    }

}
