package com.endava.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

public class CheckboxTest {

	@Test
	public void testCheckbox() {

		String optionToSelect = "Option";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get( "https://www.seleniumeasy.com/test/basic-checkbox-demo.html" );

		//List<WebElement> checkBoxes = driver.findElements( By.cssSelector( ".cb1-element" ) );
		WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds( 2 ) );
		List<WebElement> checkBoxes = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(
								By.cssSelector( ".checkbox label" )
						 ) );
		System.out.println(checkBoxes.size());
		checkBoxes.stream()
				.peek( checkBox -> System.out.println(checkBox.getText()) )
				.filter( checkbox -> checkbox.getText().contains( optionToSelect ) )
				.peek( checkBox -> System.out.println(checkBox.getText()) )
				.forEach( checkbox -> checkbox.click() );
	}
}
