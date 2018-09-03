package br.com.mmorais.common;

import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class CadastroPageObject extends PageObject{
	
	protected String botaoSalvar;
	protected String idMensageErro;
	
	public CadastroPageObject(WebDriver driver, String url, String botaoSalvar, String idMensagemErro) {
		super(driver, url);
		this.botaoSalvar = botaoSalvar;
		this.idMensageErro = idMensagemErro;
	}

	public void cadastra(String[] dados) {
		
		for (Entry<String, String> dado : preparaValoresTest(dados).entrySet()) {
			PageObject.fieldTypes tipoCampo = estrutura.get(dado.getKey());
			WebElement campo = this.driver.findElement(By.name(dado.getKey()));
			if(tipoCampo.equals(PageObject.fieldTypes.input)){
				campo.clear();
				campo.sendKeys(dado.getValue());
			} else if(tipoCampo.equals(PageObject.fieldTypes.check)){
				if(campo.isSelected()) {
					campo.click();
				}
				if(dado.getValue().equals("Sim")) {
					campo.click();					
				}
			} else if(tipoCampo.equals(PageObject.fieldTypes.combo)){
				Select combo = new Select(campo);
				combo.selectByVisibleText(dado.getValue());
			} 
		}
		
		// Buscando WebElements de entrada
		WebElement botao = this.driver.findElement(By.id(this.botaoSalvar));
		botao.click();		
	}
	
	public boolean exibeMensagemErro(String[] mensagensErro) {
		boolean[] achouMensagens = new boolean[mensagensErro.length];
		// Buscando lista de erros na tela para verificar se houve mensagem correta
		List<WebElement> labelsMensagemErro = this.driver.findElements(By.id(this.idMensageErro));
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

}
