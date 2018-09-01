package br.com.mmorais.leiloes;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UsuariosPage {
	
	private WebDriver driver;
	
	public UsuariosPage(WebDriver driver) {
		this.driver = driver;
	}	

	public void visita() {
		this.driver.get("http://localhost:8080/usuarios");
	}
	
	public NovoUsuarioPage novo() {
		WebElement linkNovo = this.driver.findElement(By.linkText("Novo Usuário"));
		linkNovo.click();
		return new NovoUsuarioPage(this.driver);
	}
	
	public boolean existeNaListagem(String nome, String email) {

		return achou("usuario.nome",nome) && achou("usuario.email",email);
	}

	private boolean achou(String elemento, String valor) {
		// Buscando lista de usuarios para verificar se houve inserção correta
		List<WebElement> usuariosCadastrados = this.driver.findElements(By.id(elemento));
		boolean achou = false;
		for (WebElement webElement : usuariosCadastrados) {
			if(webElement.getText().equals(valor)) {
				achou = true;
				break;
			}
		}
		return achou;
	}

	public boolean exibeMensagemErro(String[] mensagensErro) {
		boolean[] achouMensagens = new boolean[mensagensErro.length];
		// Buscando lista de erros na tela para verificar se houve mensagem correta
		List<WebElement> labelsMensagemErro = this.driver.findElements(By.id("errorMessage"));
		for (WebElement webElement : labelsMensagemErro) {
			for (int i=0; i<mensagensErro.length; i++) {
				if(webElement.getText().equals(mensagensErro[i])) {
					achouMensagens[i] = true;
					continue;
				}
			}
		}
		
		// Fazendo assert do resultado
		boolean retorno = true;
		for (boolean achouMensagem : achouMensagens) {
			retorno &= achouMensagem;
		}
		return retorno;
	}

	public void exclui(String nomeTeste) {
		List<WebElement> usuariosListados = this.driver.findElements(By.id("usuario"));
		for (WebElement webElement : usuariosListados) {
			if(webElement.findElement(By.id("usuario.nome")).getText().equals(nomeTeste)) {
				WebElement btnExcluir = webElement.findElement(By.id("btnExcluir"));
				btnExcluir.click();
				// pega o alert que está aberto
				Alert alert = driver.switchTo().alert();
				// confirma
				alert.accept();
			}
		}		
	}
}
