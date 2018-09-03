package br.com.mmorais.leiloes;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.mmorais.common.CadastroPageObject;
import br.com.mmorais.common.ListagemPageObject;
import br.com.mmorais.leiloes.pageObjects.LeiloesPage;
import br.com.mmorais.leiloes.pageObjects.UsuariosPage;

public class LeiloesSystemTest {

	private WebDriver driver;
	private ListagemPageObject leiloes;

	@BeforeClass
	public static void limparBase() {
		System.setProperty("webdriver.chrome.driver", "D:/Dev/webdrivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/apenas-teste/limpa");
		driver.close();
	}
	
	@Before
	public void inicializa() {
		// Configurando Driver
		System.setProperty("webdriver.chrome.driver", "D:/Dev/webdrivers/chromedriver.exe");
		this.driver =  new ChromeDriver();		
		this.leiloes = new LeiloesPage(this.driver);
		this.leiloes.visita();
	}
	
	@After
	public void finaliza() {
		this.driver.close();
	}

	@Test
	public void deveIncluirUmLeilao() {
		
		String nomeTeste = "Geladeira";
		String valorTeste = "123.0";
		String usuarioTeste = "Paulo Henrique";	
		String usadoTest = "Sim";
		String emailTeste = "phenrique@somecompany.com";
		
		String[] valoresTesteUsuario = {usuarioTeste, emailTeste};
		String[] valoresTesteLeilao = {nomeTeste, valorTeste, usuarioTeste, usadoTest};
		incluirUsuarioDeTeste(valoresTesteUsuario);
		
		CadastroPageObject novoLeilao = this.leiloes.novo();
		novoLeilao.cadastra(valoresTesteLeilao);
		assertTrue(this.leiloes.existeNaListagem(valoresTesteLeilao,false));
		
	}

	@Test
	public void naoDeveIncluirUmLeilaoSemNome() {
		
		String nomeTeste = "";
		String valorTeste = "123.0";
		String usuarioTeste = "Paulo Henrique";	
		String usadoTest = "Sim";
		String[] mensagensErro = {"Nome obrigatorio!"};
		
		String[] valoresTesteLeilao = {nomeTeste, valorTeste, usuarioTeste, usadoTest};
		
		CadastroPageObject novoLeilao = this.leiloes.novo();
		novoLeilao.cadastra(valoresTesteLeilao);
		assertTrue(novoLeilao.exibeMensagemErro(mensagensErro));

	}

	@Test
	public void naoDeveIncluirUmLeilaoSemValorInicial() {
		
		String nomeTeste = "Geladeira";
		String valorTeste = "";
		String usuarioTeste = "Paulo Henrique";	
		String usadoTest = "Sim";
		String[] mensagensErro = {"Valor inicial deve ser maior que zero!"};
		
		String[] valoresTesteLeilao = {nomeTeste, valorTeste, usuarioTeste, usadoTest};
		
		CadastroPageObject novoLeilao = this.leiloes.novo();
		novoLeilao.cadastra(valoresTesteLeilao);
		assertTrue(novoLeilao.exibeMensagemErro(mensagensErro));
	}
	
	@Test
	public void naoDeveIncluirUmLeilaoSemNomeEValorInicial() {
		
		String nomeTeste = "";
		String valorTeste = "";
		String usuarioTeste = "Paulo Henrique";	
		String usadoTest = "Sim";
		String[] mensagensErro = {"Nome obrigatorio!","Valor inicial deve ser maior que zero!"};
		
		String[] valoresTesteLeilao = {nomeTeste, valorTeste, usuarioTeste, usadoTest};
		
		CadastroPageObject novoLeilao = this.leiloes.novo();
		novoLeilao.cadastra(valoresTesteLeilao);
		assertTrue(novoLeilao.exibeMensagemErro(mensagensErro));
	}

	
	private void incluirUsuarioDeTeste(String[] valoresTesteUsuario) {
		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		usuarios.novo().cadastra(valoresTesteUsuario);
		this.leiloes.visita();
	}
	
}
