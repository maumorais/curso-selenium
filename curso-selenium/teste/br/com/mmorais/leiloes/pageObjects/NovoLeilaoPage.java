package br.com.mmorais.leiloes.pageObjects;

import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import br.com.mmorais.common.CadastroPageObject;
import br.com.mmorais.common.PageObject;

public class NovoLeilaoPage extends CadastroPageObject{
	

	public NovoLeilaoPage(WebDriver driver) {
		super(driver, "http://localhost:8080/leiloes/new", "btnSalvar", "errorMessage");
	}
	
	@Override
	protected void populaEstrutura() {
		this.estrutura = new LinkedHashMap<String, PageObject.fieldTypes>();
		this.estrutura.put("leilao.nome",PageObject.fieldTypes.input);
		this.estrutura.put("leilao.valorInicial",PageObject.fieldTypes.input);
		this.estrutura.put("leilao.usuario.id",PageObject.fieldTypes.combo);
		this.estrutura.put("leilao.usado",PageObject.fieldTypes.check);
	}
	
}
