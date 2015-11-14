package simulador;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Requisito02 {

	//Requisito 02: O Valor de Entrada deve ser inferior a R$ 30.000 (não considerar os centavos)
	
	@Test
	public void test() {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://eliasnogueira.com/uninassau/financiamento/index.html");
		driver.manage().window().maximize();
		
		//Selecionar tipo do imóvel
		driver.findElement(By.id("residencial")).click();
		//Valor do imóvel
		driver.findElement(By.id("valor_imovel")).sendKeys("80.000,00");
		//Valor da entrada
		driver.findElement(By.id("valor_entrada")).sendKeys("30.001,00");
		//Prazo de financiamento
		driver.findElement(By.name("prazo")).sendKeys("12");
		//Calcular crédito imobiliário
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		
		//Verifica se o alert-message foi exibido de acordo com o requisito
		assertEquals("O valor da entrada deve ser inferior a R$ 30.000,00",
				driver.findElement(By.cssSelector(".alert.alert-danger.inferior")).getText()
				);
		
		
		driver.quit();
	}

}
