package br.com.jeanheberth.homepage;

import br.com.jeanheberth.basepage.BaseTests;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HomePageTests extends BaseTests {

    @Test
    public void testContarProdutos_oitoProdutosDiferente() {
        carregarPaginaInicial();
        assertThat(homePage.contarProdutos(), is(8));
    }

    @Test
    public void testValidarCarrinhoZerado(){
        int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
       // System.out.println("Quantidades: " +produtosNoCarrinho);
        assertThat(produtosNoCarrinho, is(0));
    }

    @Test
    public void validarDetalhesDoProduto_DescricaoEValorIguais(){
        int indice = 0;
        String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
        String precoProduto_HomePage = homePage.obterPrecoProduto(indice);

        System.out.println(nomeProduto_HomePage);
        System.out.println(precoProduto_HomePage);

    }

}
