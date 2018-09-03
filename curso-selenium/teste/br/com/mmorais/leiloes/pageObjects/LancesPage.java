package br.com.mmorais.leiloes.pageObjects;

import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import br.com.mmorais.common.ListagemPageObject;
import br.com.mmorais.common.PageObject;

public class LancesPage extends ListagemPageObject {

	public LancesPage(WebDriver driver) {
		super(driver, 
			  null, 			//url
			  null,				//link Cadastro 
			  "lancesDados",
			  "lance", 
			  null, 			// Link Alterar
			  null,				// Link Detalhes 
			  null, 			// Botão Excluir
			  new NovoLancePage(driver), 
			  null				// pageObjectDetalhes 
		);
	}

	@Override
	protected void populaEstrutura() {
		this.estrutura = new LinkedHashMap<String, PageObject.fieldTypes>();
		this.estrutura.put("lance.usuario.nome",PageObject.fieldTypes.label);
		this.estrutura.put("lance.valor",PageObject.fieldTypes.label);
	}

}
