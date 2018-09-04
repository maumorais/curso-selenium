package br.com.mmorais.leiloes.util;

import org.openqa.selenium.WebDriver;

import br.com.mmorais.leiloes.pageObjects.LeiloesPage;
import br.com.mmorais.leiloes.pageObjects.UsuariosPage;

public class CriadorDeCenarios {

    private WebDriver driver;

    public CriadorDeCenarios(WebDriver driver) {
        this.driver = driver;
    }

    public CriadorDeCenarios umUsuario(String nome, String email) {
        UsuariosPage usuarios = new UsuariosPage(driver);
        usuarios.visita();
        String[] dados = {nome, email};
        usuarios.novo().cadastra(dados);

        return this;
    }

    public CriadorDeCenarios umLeilao(String usuario, 
                String produto, 
                String valor, 
                String usado) {
        LeiloesPage leiloes = new LeiloesPage(driver);
        leiloes.visita();
        String[] dados = {produto, valor, usuario, usado};
        leiloes.novo().cadastra(dados);

        return this;
    }

}