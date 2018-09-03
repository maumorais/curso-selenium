package br.com.mmorais.leiloes;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.mmorais.common.CadastroPageObject;
import br.com.mmorais.leiloes.pageObjects.UsuariosPage;

public class UsuariosSystemTest {
	
	private WebDriver driver;
	private UsuariosPage usuarios;
	
	@BeforeClass
	public static void limparBase() {
		System.setProperty("webdriver.chrome.driver", "D:/Dev/webdrivers/chromedriver.exe");
		new ChromeDriver().get("http://localhost:8080/apenas-teste/limpa");
	}
	
	@Before
	public void inicializa() {
		// Configurando Driver
		System.setProperty("webdriver.chrome.driver", "D:/Dev/webdrivers/chromedriver.exe");
		this.driver =  new ChromeDriver();		
		this.usuarios = new UsuariosPage(this.driver);
		this.usuarios.visita();
	}
	
	@After
	public void finaliza() {
		this.driver.close();
	}

	@Test
	public void deveIncluirUmUsuario() {
		
		String nomeTeste = "Adriano Xavier";
		String emailTeste = "axavier@empresa.com.br";	
		
		String[] valoresTeste = {nomeTeste, emailTeste};
		
		this.usuarios.novo().cadastra(valoresTeste);
		assertTrue(this.usuarios.existeNaListagem(valoresTeste,false));
		
	}

	@Test
	public void deveAlterarUmUsuario() {
		
		String nomeTeste = "José de Arimatea";
		String emailTeste = "jarimatea@empresa.com.br";	
		String novoNomeTeste = "Maria Madalena";
		String novoEmailTeste = "mmadalena@empresa.com.br";
		
		String[] valoresTeste = {nomeTeste, emailTeste};
		String[] novosValoresTeste = {novoNomeTeste, novoEmailTeste};
		
		this.usuarios.novo().cadastra(valoresTeste);
		assertTrue(this.usuarios.existeNaListagem(valoresTeste,false));
		usuarios.altera(valoresTeste).cadastra(novosValoresTeste);
		assertTrue(this.usuarios.existeNaListagem(novosValoresTeste,false));
		
	}

	@Test
	public void naoDeveIncluirUmUsuarioSemNome() {
		
		String nomeTeste = "";
		String emailTeste = "axavier@empresa.com.br";		
		String[] mensagensErro = {"Nome obrigatorio!"};
		
		String[] valoresTeste = {nomeTeste, emailTeste};
		
		CadastroPageObject novoUsuario = this.usuarios.novo();
		novoUsuario.cadastra(valoresTeste);
		assertTrue(novoUsuario.exibeMensagemErro(mensagensErro));

	}

	@Test
	public void naoDeveIncluirUmUsuarioSemEmail() {
		
		String nomeTeste = "Adriano Xavier";
		String emailTeste = "";		
		String[] mensagensErro = {"E-mail obrigatorio!"};
		
		String[] valoresTeste = {nomeTeste, emailTeste};
		
		CadastroPageObject novoUsuario = this.usuarios.novo();
		novoUsuario.cadastra(valoresTeste);
		assertTrue(novoUsuario.exibeMensagemErro(mensagensErro));

	}
	
	@Test
	public void naoDeveIncluirUmUsuarioSemNomeEEmail() {
		
		String nomeTeste = "";
		String emailTeste = "";		
		String[] mensagensErro = {"Nome obrigatorio!", "E-mail obrigatorio!"};
		
		String[] valoresTeste = {nomeTeste, emailTeste};
		
		CadastroPageObject novoUsuario = this.usuarios.novo();
		novoUsuario.cadastra(valoresTeste);
		assertTrue(novoUsuario.exibeMensagemErro(mensagensErro));
	}
	
	@Test
	public void deveExcluirUmUsuario() {
		String nomeTeste = "João da Silva";
		String emailTeste = "jsilva@empresa.com.br";	
		
		String[] valoresTeste = {nomeTeste, emailTeste};
		
		this.usuarios.novo().cadastra(valoresTeste);
		assertTrue(this.usuarios.existeNaListagem(valoresTeste,false));
		usuarios.exclui(valoresTeste, true);
		assertTrue(!this.usuarios.existeNaListagem(valoresTeste,false));
	}
		
}
