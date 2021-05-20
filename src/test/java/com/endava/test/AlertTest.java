package com.endava.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertTest {

	@Test
	public void testAlerts() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get( "https://www.seleniumeasy.com/test/javascript-alert-box-demo.html" );

		WebElement button = driver.findElement(
				By.xpath("//div[text()='Java Script Alert Box']/following-sibling::div//button") );
		button.click();

		Thread.sleep( 2000 );
		driver.switchTo().alert().accept();



	}
}
