package br.com.mmorais.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriver;

public abstract class PageObject {
	
	public enum fieldTypes{
					input,
					combo,
					check,
					label
				}
	
	protected WebDriver driver;
	protected String url;
	protected LinkedHashMap<String, fieldTypes> estrutura;

	
	public PageObject(WebDriver driver, String url) {
		this.driver = driver;
		this.url = url;
		populaEstrutura();
	}	
	
	public void visita() {
		this.driver.get(this.url);
	}
	
	public Map<String, fieldTypes> getEstrutura(){
		return this.estrutura;
	}
	
	protected Map<String, String> preparaValoresTest(String[] valores) {
		Map<String, String> valoresTeste = new HashMap<String, String>();
		int counter = 0;
		for (Entry<String, PageObject.fieldTypes> itemEstrutura : this.getEstrutura().entrySet()) {
			valoresTeste.put(itemEstrutura.getKey(), valores[counter++]);
		}
		return valoresTeste;
	}
	
	protected abstract void populaEstrutura();

}
