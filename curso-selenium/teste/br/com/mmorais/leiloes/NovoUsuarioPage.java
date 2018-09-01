package br.com.mmorais.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NovoUsuarioPage {

	private WebDriver driver;
	
	public NovoUsuarioPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void cadastra(String nome, String email) {
		// Buscando WebElements de entrada
		WebElement inputNome = this.driver.findElement(By.name("usuario.nome"));
		WebElement inputEmail = this.driver.findElement(By.name("usuario.email"));
		WebElement botao = this.driver.findElement(By.id("btnSalvar"));
		
		
		// Enviando dados de teste
		inputNome.sendKeys(nome);
		inputEmail.sendKeys(email);
		botao.click();		
	}
}
