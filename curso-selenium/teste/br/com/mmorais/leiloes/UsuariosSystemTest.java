package br.com.mmorais.leiloes;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsuariosSystemTest {
	
	private WebDriver driver;
	private UsuariosPage usuarios;
	
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
		
		this.usuarios.novo().cadastra(nomeTeste, emailTeste);
		
		assertTrue(this.usuarios.existeNaListagem(nomeTeste, emailTeste));
		
	}
	
	@Test
	public void naoDeveIncluirUmUsuarioSemNome() {
		
		String nomeTeste = "";
		String emailTeste = "axavier@empresa.com.br";		
		String[] mensagensErro = {"Nome obrigatorio!"};
		
		this.usuarios.novo().cadastra(nomeTeste, emailTeste);
		
		assertTrue(this.usuarios.exibeMensagemErro(mensagensErro));

	}

	@Test
	public void naoDeveIncluirUmUsuarioSemEmail() {
		
		String nomeTeste = "Adriano Xavier";
		String emailTeste = "";		
		String[] mensagensErro = {"E-mail obrigatorio!"};
		
		this.usuarios.novo().cadastra(nomeTeste, emailTeste);
		
		assertTrue(this.usuarios.exibeMensagemErro(mensagensErro));

	}
	
	@Test
	public void naoDeveIncluirUmUsuarioSemNomeEEmail() {
		
		
		String nomeTeste = "";
		String emailTeste = "";		
		String[] mensagensErro = {"Nome obrigatorio!", "E-mail obrigatorio!"};
		
		this.usuarios.novo().cadastra(nomeTeste, emailTeste);
		
		assertTrue(this.usuarios.exibeMensagemErro(mensagensErro));
	}
	
	@Test
	public void deveExcluirUmUsuario() {
		String nomeTeste = "João da Silva";
		String emailTeste = "jsilva@empresa.com.br";	
		this.usuarios.novo().cadastra(nomeTeste, emailTeste);
		assertTrue(this.usuarios.existeNaListagem(nomeTeste, emailTeste));
		usuarios.exclui(nomeTeste);
		assertTrue(!this.usuarios.existeNaListagem(nomeTeste, emailTeste));
	}
}
