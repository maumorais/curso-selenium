package br.com.mmorais.leiloes.pageObjects;

import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import br.com.mmorais.common.CadastroPageObject;
import br.com.mmorais.common.PageObject;

public class NovoUsuarioPage extends CadastroPageObject{

	public NovoUsuarioPage(WebDriver driver) {
		super(driver, "http://localhost:8080/usuarios/new", "btnSalvar", "errorMessage");
	}
	
	protected void populaEstrutura() {
		this.estrutura = new LinkedHashMap<String, PageObject.fieldTypes>();
		this.estrutura.put("usuario.nome", PageObject.fieldTypes.input);
		this.estrutura.put("usuario.email", PageObject.fieldTypes.input);
	}
	
}
