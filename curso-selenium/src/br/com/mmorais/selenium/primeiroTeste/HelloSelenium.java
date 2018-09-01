package br.com.mmorais.selenium.primeiroTeste;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {

	public static void main(String[] args) {
//      Driver para firefox
		System.setProperty("webdriver.gecko.driver", "D:/Dev/webdrivers/geckodriver.exe");
//      Driver para Chrome		
		System.setProperty("webdriver.chrome.driver", "D:/Dev/webdrivers/chromedriver.exe");
		WebDriver driver =  new ChromeDriver();
		driver.get("http://www.google.com");
		WebElement campoBusca = driver.findElement(By.name("q"));
		campoBusca.sendKeys("Selenium");
		campoBusca.submit();
		
	}
}
