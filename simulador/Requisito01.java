package simulador;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Requisito01 {
	
	//Requisito 01: O Prazo de Financimento deve ser entre 12 e 120 meses
	@SuppressWarnings("unused")
	
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
		driver.findElement(By.id("valor_entrada")).sendKeys("25.000,00");
		//Prazo de financiamento
		driver.findElement(By.name("prazo")).sendKeys("11");
		//Calcular crédito imobiliário
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		
		//Verifica se o alert-message foi exibido de acordo com o requisito
		assertEquals("O prazo deve ser de de 12 a 120 meses",
				driver.findElement(By.cssSelector(".alert.alert-danger.meses")).getText()
				);
		

		//Testes utilizando técnica Valores Fronteira
		
		//Prazo: 121 meses
		driver.get("http://eliasnogueira.com/uninassau/financiamento/do.php?tipoImovel=residencial&imovel=80.000%2C00&entrada=25.000%2C00&prazo=121");
		assertEquals("O prazo deve ser de de 12 a 120 meses",
				driver.findElement(By.cssSelector(".alert.alert-danger.meses")).getText());
		
		//Prazo: 12 meses
		driver.get("http://eliasnogueira.com/uninassau/financiamento/do.php?tipoImovel=residencial&imovel=80.000%2C00&entrada=25.000%2C00&prazo=12");
		Boolean isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.meses")).size() > 0;
		
		//Prazo: 120 meses
		driver.get("http://eliasnogueira.com/uninassau/financiamento/do.php?tipoImovel=residencial&imovel=80.000%2C00&entrada=25.000%2C00&prazo=120");
		isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.meses")).size() > 0;
		
		driver.quit();
	}

}
