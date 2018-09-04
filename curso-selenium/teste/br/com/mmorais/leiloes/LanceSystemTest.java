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
import br.com.mmorais.leiloes.pageObjects.LancesPage;
import br.com.mmorais.leiloes.pageObjects.LeiloesPage;
import br.com.mmorais.leiloes.util.CriadorDeCenarios;

public class LanceSystemTest {
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
		setup();
		this.leiloes.visita();
	}
	
	@After
	public void finaliza() {
		this.driver.close();
	}

	private void setup() {
		String usuarioTeste1 = "Paulo Henrique";	
		String emailTeste1 = "phenrique@somecompany.com";
		String usuarioTeste2 = "Maria Madalena";	
		String emailTeste2 = "mmadalena@somecompany.com";
		
		String nomeTeste = "Geladeira";
		String valorTeste = "123.0";
		String usadoTeste = "Sim";
		
/*		String[] valoresTesteUsuario1 = {usuarioTeste1, emailTeste1};
		String[] valoresTesteUsuario2 = {usuarioTeste2, emailTeste2};
		String[] valoresTesteLeilao = {nomeTeste, valorTeste, usuarioTeste1, usadoTeste};
		incluirUsuarioDeTeste(valoresTesteUsuario1);
		incluirUsuarioDeTeste(valoresTesteUsuario2);
		incluirLeilaoTeste(valoresTesteLeilao);*/
		new CriadorDeCenarios(driver)
			.umUsuario(usuarioTeste1, emailTeste1)
			.umUsuario(usuarioTeste2, emailTeste2)
			.umLeilao(usuarioTeste1, nomeTeste, valorTeste, usadoTeste);
	}

	@Test
	public void deveFazerUmLance() {
		
		String nomeLeilaoTeste = "Geladeira";
		String valorLeilaoTeste = "123.0";
		String usuarioLeilaoTeste = "Paulo Henrique";	
		String usadoLeilaoTest = "Sim";
		
		String usuarioTeste = "Maria Madalena";	
		String valorTeste = "13.0";
		
		String[] valoresLeilaoTeste = {nomeLeilaoTeste, valorLeilaoTeste, usuarioLeilaoTeste, usadoLeilaoTest};
		String[] valoresLanceTeste = {usuarioTeste, valorTeste};
		
		CadastroPageObject lance = leiloes.detalhes(valoresLeilaoTeste);
		ListagemPageObject lances = new LancesPage(driver);
		lance.cadastra(valoresLanceTeste);

		assertTrue(lances.existeNaListagemSemIds(valoresLanceTeste,true));
		
	}
	
/*	private void incluirUsuarioDeTeste(String[] valoresTesteUsuario) {
		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		usuarios.novo().cadastra(valoresTesteUsuario);
		this.leiloes.visita();
	}

	private void incluirLeilaoTeste(String[] valoresTesteLeilao) {
		ListagemPageObject leiloes = new LeiloesPage(this.driver);
		leiloes.visita();
		CadastroPageObject novoLeilao = leiloes.novo();
		novoLeilao.cadastra(valoresTesteLeilao);
	}
*/
}
