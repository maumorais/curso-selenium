package br.com.mmorais.common;

import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class ListagemPageObject extends PageObject{
	
	protected String labelLinkCadastro;
	protected String idTabelaLista;
	protected String idItemLista;
	protected String linkAlterar;
	protected String linkDetalhes;
	protected String botaoExcluir;
	protected CadastroPageObject pageObjectCadastro;
	protected CadastroPageObject pageObjectDetalhes;

	public ListagemPageObject(WebDriver driver, 
							  String url, 
							  String labeLinkCadastro, 
							  String idTabelaLista,
							  String idItemLista,
							  String linkAlterar,
							  String linkDetalhes,
							  String botaoExcluir,
							  CadastroPageObject pageObjectCadastro,
							  CadastroPageObject pageObjectDetalhes) {
		super(driver, url);
		this.labelLinkCadastro = labeLinkCadastro;
		this.pageObjectCadastro = pageObjectCadastro;
		this.pageObjectDetalhes = pageObjectDetalhes;
		this.idTabelaLista = idTabelaLista;
		this.idItemLista = idItemLista;
		this.linkAlterar = linkAlterar;
		this.linkDetalhes = linkDetalhes;
		this.botaoExcluir = botaoExcluir;
	}

	public CadastroPageObject novo() {
		WebElement linkNovo = this.driver.findElement(By.linkText(this.labelLinkCadastro));
		linkNovo.click();
		return this.pageObjectCadastro;
	}
	
	public CadastroPageObject detalhes(String[] dados) {
		WebElement linkDetalhes = buscaItemListagem(dados).findElement(By.id(this.linkDetalhes));
		linkDetalhes.click();
		return this.pageObjectDetalhes;
	}
	
	public CadastroPageObject altera(String[] dados) {
		WebElement linkAlterar = buscaItemListagem(dados).findElement(By.id(this.linkAlterar));
		linkAlterar.click();
		return pageObjectCadastro;
	}
	
	public void exclui(String[] dados, boolean hasAlert) {
		WebElement btnExcluir = buscaItemListagem(dados).findElement(By.id(this.botaoExcluir));
		btnExcluir.click();
		if(hasAlert) {
			// pega o alert que está aberto
			Alert alert = driver.switchTo().alert();
			// confirma
			alert.accept();
		}		
	}
	
	public boolean existeNaListagem(String[] dados, boolean isListagemAtualizadaPorAjax) {
		boolean result = true;
		boolean atualizou = false;
		if(isListagemAtualizadaPorAjax) {
			atualizou = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.textToBePresentInElementLocated(By.id(this.idTabelaLista), dados[0]));
		} 
		if(atualizou || !isListagemAtualizadaPorAjax) {
			for (Entry<String, String> dado : preparaValoresTest(dados).entrySet()) {
				result = result && achou(dado.getKey(),dado.getValue());
			}
		}

		return result;
	}
	
	public boolean existeNaListagemSemIds(String[] dados, boolean isListagemAtualizadaPorAjax) {
		boolean result = true;
		boolean atualizou = false;
		if(isListagemAtualizadaPorAjax) {
			atualizou = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.textToBePresentInElementLocated(By.id(this.idTabelaLista), dados[0]));
		}
		if(atualizou || !isListagemAtualizadaPorAjax) {
			for (Entry<String, String> dado : preparaValoresTest(dados).entrySet()) {
				result = result && driver.getPageSource().contains(dado.getValue());
			}
		}
		
		return result;
	}

	private WebElement buscaItemListagem(String[] dados) {
		WebElement result = null;

		List<WebElement> itensLista = this.driver.findElements(By.id(this.idItemLista));
		for (WebElement webElement : itensLista) {
			boolean match = true;
			for (Entry<String, String> dado : preparaValoresTest(dados).entrySet()) {
				match = match && webElement.findElement(By.id(dado.getKey())).getText().equals(dado.getValue());
			}
			if(match) {
				result = webElement; 
				break;
			}
		}		
		
		return result;
	}

	protected boolean achou(String elemento, String valor) {
		// Buscando lista de usuarios para verificar se houve inserção correta
		List<WebElement> elementos = this.driver.findElements(By.id(elemento));
		boolean achou = false;
		for (WebElement webElement : elementos) {
			if(webElement.getText().equals(valor)) {
				achou = true;
				break;
			}
		}
		return achou;
	}
	
}
