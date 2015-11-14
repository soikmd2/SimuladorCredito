package simulador;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Requisito03 {

		//Requisito 03: Se o Tipo de Imóvel for residencial o valor deve ser maior que R$ 62.000 (considerando >=)
		@SuppressWarnings("unused")
		
		@Test
		public void test() {
			WebDriver driver = new FirefoxDriver();
			driver.get("http://eliasnogueira.com/uninassau/financiamento/index.html");
			driver.manage().window().maximize();
			
			//Selecionar tipo do imóvel
			driver.findElement(By.id("residencial")).click();
			//Valor do imóvel
			driver.findElement(By.id("valor_imovel")).sendKeys("60.000,00");
			//Valor da entrada
			driver.findElement(By.id("valor_entrada")).sendKeys("25.000,00");
			//Prazo de financiamento
			driver.findElement(By.name("prazo")).sendKeys("12");
			//Calcular crédito imobiliário
			driver.findElement(By.cssSelector(".btn.btn-primary")).click();
			
			//Verifica se o alert-message foi exibido de acordo com o requisito
			assertEquals("O valor do imóvel deve ser maior que R$ 62.000,00",
					driver.findElement(By.cssSelector(".alert.alert-danger.menor")).getText());
			

			//Testes utilizando técnica Valores Fronteira
			
			//Valor do imóvel: 61.999,99
			driver.get("http://eliasnogueira.com/uninassau/financiamento/do.php?tipoImovel=residencial&imovel=61.999%2C99&entrada=25.000%2C00&prazo=12");
			Boolean isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.menor")).size() > 0;
			
			//Valor do imóvel: 62.000,00
			driver.get("http://eliasnogueira.com/uninassau/financiamento/do.php?tipoImovel=residencial&imovel=62.000%2C00&entrada=25.000%2C00&prazo=12");
			assertEquals("Estamos processando a solicitação!",
					driver.findElement(By.cssSelector(".alert.alert-success.triangulo")).getText());
			
						
			driver.quit();
	}

}
