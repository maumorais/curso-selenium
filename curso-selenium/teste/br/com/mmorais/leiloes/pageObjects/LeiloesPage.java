package br.com.mmorais.leiloes.pageObjects;

import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import br.com.mmorais.common.ListagemPageObject;
import br.com.mmorais.common.PageObject;
import br.com.mmorais.leiloes.util.URLDaAplicacao;

public class LeiloesPage extends ListagemPageObject {

	public LeiloesPage(WebDriver driver) {
		super(driver, 
			  URLDaAplicacao.getUrlBase() + "/leiloes", 
			  "Novo Leilão",
			  null,				//idTabelaLista
			  "leilao", 
			  null, 			// Link Alterar
			  "exibir", 
			  null, 			// Botão Excluir
			  new NovoLeilaoPage(driver), 
			  new NovoLancePage(driver) 
		);
	}

	@Override
	protected void populaEstrutura() {
		this.estrutura = new LinkedHashMap<String, PageObject.fieldTypes>();
		this.estrutura.put("leilao.nome",PageObject.fieldTypes.label);
		this.estrutura.put("leilao.valorInicial",PageObject.fieldTypes.label);
		this.estrutura.put("leilao.usuario.nome",PageObject.fieldTypes.label);
		this.estrutura.put("leilao.usado",PageObject.fieldTypes.label);
	}



}
