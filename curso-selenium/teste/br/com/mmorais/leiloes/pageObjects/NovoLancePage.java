package br.com.mmorais.leiloes.pageObjects;

import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import br.com.mmorais.common.CadastroPageObject;
import br.com.mmorais.common.PageObject;

public class NovoLancePage extends CadastroPageObject{

	public NovoLancePage(WebDriver driver) {
		super(driver, "http://http://localhost:8080/leiloes/", "btnDarLance", null);
	}
	
	protected void populaEstrutura() {
		this.estrutura = new LinkedHashMap<String, PageObject.fieldTypes>();
		this.estrutura.put("lance.usuario.id", PageObject.fieldTypes.combo);
		this.estrutura.put("lance.valor", PageObject.fieldTypes.input);
	}
	
}
