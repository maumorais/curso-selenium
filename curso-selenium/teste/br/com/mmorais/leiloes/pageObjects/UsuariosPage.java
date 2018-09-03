package br.com.mmorais.leiloes.pageObjects;

import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import br.com.mmorais.common.ListagemPageObject;
import br.com.mmorais.common.PageObject;

public class UsuariosPage extends ListagemPageObject{
	
	public UsuariosPage(WebDriver driver) {
		super(driver,
			  "http://localhost:8080/usuarios", 
			  "Novo Usuário",
			  null,				//idTabelaLista
			  "usuario", 
			  "editar",
			  "exibir",
			  "btnExcluir", 
			  new NovoUsuarioPage(driver),
			  null				//pageObjectDetalhes
		);
	}	


	@Override
	protected void populaEstrutura() {
		this.estrutura = new LinkedHashMap<String, PageObject.fieldTypes>();
		this.estrutura.put("usuario.nome", PageObject.fieldTypes.input);
		this.estrutura.put("usuario.email", PageObject.fieldTypes.input);
	}
	
}
