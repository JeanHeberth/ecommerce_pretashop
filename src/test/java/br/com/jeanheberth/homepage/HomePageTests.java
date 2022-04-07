package br.com.jeanheberth.homepage;

import br.com.jeanheberth.basepage.*;
import br.com.jeanheberth.core.BaseTests;
import br.com.jeanheberth.util.Funcoes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTests extends BaseTests {

    @Test
    public void testContarProdutos_oitoProdutosDiferente() {
        carregarPaginaInicial();
        assertThat(homePage.contarProdutos(), is(8));
    }

    @Test
    public void testValidarCarrinhoZerado() {
        int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
        // System.out.println("Quantidades: " + produtosNoCarrinho);
        assertThat(produtosNoCarrinho, is(0));
    }

    ProdutoPage produtoPage;
    String nomeProduto_ProdutoPage;

    @Test
    public void testValidarDetalhesDoProduto_DescricaoEValorIguais() {
        int indice = 0;
        String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
        String precoProduto_HomePage = homePage.obterPrecoProduto(indice);

        produtoPage = homePage.clicarProduto(0);

        nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
        String precoProduto_ProdtuoPage = produtoPage.obterPrecoProduto();


        // System.out.println(nomeProduto_HomePage);
        //  System.out.println(nomeProduto_ProdutoPage);
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
        homePage.clicarNoBotaoMystore();
       // carregarPaginaInicial();
    }


    ModalProdutoPage modalProdutoPages;

    @Test
    public void incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso() {

        String tamanhoProduto = "M";
        String corProduto = "Black";
        int quantidadeProduto = 15;


        //Pre condicao
        //usuario logado
        if (!homePage.validarUsuarioLogado("Jean Heberth Souza Vieira")) {
            testLoginComSucesso();
        }
        //Selecioando produto
        testValidarDetalhesDoProduto_DescricaoEValorIguais();

        //Selecionar Tamanho
        List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();
        // System.out.println(listaOpcoes.get(0));
        //  System.out.println("Tamanho da lista: " + listaOpcoes.size());

        produtoPage.selecionarOpcaoDropDown(tamanhoProduto);

        listaOpcoes = produtoPage.obterOpcoesSelecionadas();
        // System.out.println(listaOpcoes.get(0));
        //  System.out.println("Tamanho da lista: " + listaOpcoes.size());

        //Selecionar Cor
        produtoPage.selecionarCorDoProduto();

        //Selecionar Quantidade
        produtoPage.alterarQuantidadeDoProduto(quantidadeProduto);

        //Adicionar no Carrinho
        modalProdutoPages = produtoPage.adicionarProdutoNoCarrinho();

        //Validações
        assertTrue(modalProdutoPages.mensagemDeInseridoComSucesso().endsWith("Product successfully added to your shopping cart"));
        System.out.println();
        assertThat(modalProdutoPages.obterDescricaoProduto().toUpperCase(),
                is(nomeProduto_ProdutoPage.toUpperCase()));

        String precoProdutoString = modalProdutoPages.obterPrecoProduto();
        precoProdutoString = precoProdutoString.replace("$", "");
        Double precoProduto = Double.parseDouble(precoProdutoString);


        String subTotalString = modalProdutoPages.obterSubtotal();
        subTotalString = subTotalString.replace("$", "");
        Double subTotal = Double.parseDouble(subTotalString);

        Double subTotalCalculado = quantidadeProduto * precoProduto;


        assertThat(modalProdutoPages.obterTamanhoProduto(), is(tamanhoProduto));
        assertThat(modalProdutoPages.obterCorProduto(), is(corProduto));
        assertThat(modalProdutoPages.obterQuantidadeProduto(), is(Integer.toString(quantidadeProduto)));
        assertThat(subTotal, is(subTotalCalculado));

    }

    //Valores esperados
    String obter_mensagemShoopingCart = "SHOPPING CART";
    String obter_nomeProduto = "Hummingbird printed t-shirt";
    Double obter_precoProduto = 19.12;
    String obter_tamanhoProduto = "M";
    String obter_corProduto = "Black";
    int obter_quantidadeProduto = 15;
    Double obter_subTotalProduto = obter_precoProduto * obter_quantidadeProduto;

    int obter_numeroItens = obter_quantidadeProduto;
    Double obter_subTotalTotalProduto = obter_subTotalProduto;
    Double obter_shippingTotalProduto = 7.00;
    Double obter_totalTaxaExclTotalProduto = obter_shippingTotalProduto + obter_subTotalTotalProduto;
    Double obter_totalTaxaInclTotalProduto = obter_totalTaxaExclTotalProduto;
    Double obter_totalTaxasProduto = 0.00;
    String esperado_nomeCliente = "Jean Heberth Souza Vieira";

    CarrinhoPage carrinhoPage;

    @Test
    public void IrParaCarrinho_InformacoesPersisitidas() {

        //Pré condições
        //Produto incluído na tela modalPage
        incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso();

        carrinhoPage = modalProdutoPages.clicarBotaoProceedToCheckout();

        // Validar todos elementos da Tela

        System.out.println("**** TELA DO CARRINHO ******");
        System.out.println(carrinhoPage.obter_nomeProduto());
        //Aqui vai acontecer a mudança
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_precoProduto()));
        System.out.println(carrinhoPage.obter_tamanhoProduto());
        System.out.println(carrinhoPage.obter_corProduto());
        System.out.println(carrinhoPage.obter_quantidadeProduto());
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subTotalProduto()));


        System.out.println("**** ITENS TOTAIS ******");
        //Aqui acontece mudança
        System.out.println("Número total de produtos: " + Funcoes.removeTextoItemsDevolveInt(carrinhoPage.obter_numeroItens()));
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subTotalTotalProduto()));
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_shippingTotalProduto()));
        System.out.println("Taxa Excluídas: " + Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxaExclTotalProduto()));
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxaInclTotalProduto()));
        System.out.println(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxasProduto()));

        // Asserções Hamcrast
        assertThat(carrinhoPage.obter_mensagemShoopingCart(), is(obter_mensagemShoopingCart));
        assertThat(carrinhoPage.obter_nomeProduto(), is(obter_nomeProduto));
        assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_precoProduto()), is(obter_precoProduto));
        assertThat(carrinhoPage.obter_tamanhoProduto(), is(obter_tamanhoProduto));
        assertThat(carrinhoPage.obter_corProduto(), is(obter_corProduto));
        assertThat(Integer.parseInt(carrinhoPage.obter_quantidadeProduto()), is(obter_quantidadeProduto));
        assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subTotalProduto()), is(obter_subTotalProduto));

        assertThat(Funcoes.removeTextoItemsDevolveInt(carrinhoPage.obter_numeroItens()), is(obter_numeroItens));
        assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subTotalTotalProduto()), is(obter_subTotalTotalProduto));
        assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_shippingTotalProduto()), is(obter_shippingTotalProduto));
        assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxaExclTotalProduto()), is(obter_totalTaxaExclTotalProduto));
        assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxaInclTotalProduto()), is(obter_totalTaxaInclTotalProduto));
        assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxasProduto()), is(obter_totalTaxasProduto));


        // Asserções Junit
/*

        //assertEquals("SHOPPING CART", obter_mensagemShoopingCart);
        //assertEquals(carrinhoPage.obter_mensagemShoopingCart(), is(obter_mensagemShoopingCart));
        assertEquals(carrinhoPage.obter_nomeProduto().toString(), is(obter_nomeProduto));
        assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_precoProduto()), is(obter_precoProduto));
        assertEquals(carrinhoPage.obter_tamanhoProduto(), is(obter_tamanhoProduto));
        assertEquals(carrinhoPage.obter_corProduto(), is(obter_corProduto));
        assertEquals(Integer.parseInt(carrinhoPage.obter_quantidadeProduto()), is(obter_quantidadeProduto));
        assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subTotalProduto()), is(obter_subTotalProduto));

        assertEquals(Funcoes.removeTextoItemsDevolveInt(carrinhoPage.obter_numeroItens()), is(obter_numeroItens));
        assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subTotalTotalProduto()), is(obter_subTotalTotalProduto));
        assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_shippingTotalProduto()), is(obter_shippingTotalProduto));
        assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxaExclTotalProduto()), is(obter_totalTaxaExclTotalProduto));
        assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxaInclTotalProduto()), is(obter_totalTaxaInclTotalProduto));
        assertEquals(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_totalTaxasProduto()), is(obter_totalTaxasProduto));
*/
        // assertThat(carrinhoPage.MensagemShoopingCart(),is("SHOPPING CART"));
    }

    ChekoutPage chekoutPage;

    @Test
    public void IrParaCheckout_FreteMeioPagamentoEnderecoListadosOk() {
        //Pre-Condicoes
        //Produto disponivel no carrinho de compras
        IrParaCarrinho_InformacoesPersisitidas();


        // Teste
        //Clicar no botao  Proceed Checkout
        chekoutPage = carrinhoPage.clicarNoBotaoProceedCheckout();

        // Preencher informacoes


        // Validar informacoes na tela.
        assertThat(Funcoes.removeCifraoDevolveDouble(chekoutPage.obter_totalTaxaInclTotalProduto()), is(obter_totalTaxaInclTotalProduto));
        assertTrue(chekoutPage.obter_nomeCliente().startsWith(esperado_nomeCliente));
        chekoutPage.clicarNoBotaoContinueAdrress();


        String encontrado_shippingValor = chekoutPage.obter_shippingValor();
        encontrado_shippingValor = Funcoes.removeTexto(encontrado_shippingValor, " tax excl.");
        Double encontrado_shippingValorDouble = Funcoes.removeCifraoDevolveDouble(encontrado_shippingValor);
        assertThat(encontrado_shippingValorDouble, is(obter_shippingTotalProduto));
        chekoutPage.clicarNoBotaoContinueShipping();

        //Clicar no input paybyCheck
        chekoutPage.selecionarRadiopayByCheck();

        //Validação
        String encontrado_amountPayByCheck = chekoutPage.obter_mensagem_amountPayByCheck();
        encontrado_amountPayByCheck = Funcoes.removeTexto(encontrado_amountPayByCheck, " (tax incl.)");
        Double encontrado_amountPayByCheck_Double = Funcoes.removeCifraoDevolveDouble(encontrado_amountPayByCheck);
        assertThat(encontrado_amountPayByCheck_Double, is(obter_totalTaxaInclTotalProduto));

        //CLicar no I agree to the terms
        chekoutPage.selecionarIAgreToTerms();
        assertTrue(chekoutPage.estaSelecionadoIAgreToTerms());


    }


//Corrigir o errro do AssertThat
    //Iniciar a aula 22


}


