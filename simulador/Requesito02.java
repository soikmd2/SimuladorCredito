package simulador;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Requesito02 {

	@Test
	public void test() {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://eliasnogueira.com/uninassau/financiamento/index.html");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("residencial")).click();
		driver.findElement(By.id("valor_imovel")).sendKeys("80.000,00");
		driver.findElement(By.id("valor_entrada")).sendKeys("30.001,00");
		driver.findElement(By.name("prazo")).sendKeys("24");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		
		assertEquals("O valor da entrada deve ser inferior a R$ 30.000,00",
				driver.findElement(By.cssSelector(".alert.alert-danger.inferior")).getText()
				);
		
		
		driver.quit();
	}

}
