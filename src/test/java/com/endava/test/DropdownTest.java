package com.endava.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;

public class DropdownTest {

	//@Test
	@ParameterizedTest()
	@ValueSource( strings = { "Monday", "Tuesday", "Wednesday" })
	public void singleSelectDropdownTest(String dayToSelect) {

		// GIVEN
		//String dayToSelect = "Friday";
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();

		driver.get( "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html" );

		WebElement dropDown = driver.findElement( By.cssSelector( "#select-demo" ) );
		Select select = new Select( dropDown );
		// WHEN
		select.selectByValue( dayToSelect );

		//THEN
		WebElement textElement = driver.findElement( By.cssSelector( ".selected-value" ) );
		assertTrue(textElement.getText().endsWith( dayToSelect ) );

		//Thread.sleep( 2000 );
		driver.close();

	}

	@Test
	public void multiSelectTest() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get( "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html" );

		WebElement dropdown = driver.findElement( By.cssSelector( "#multi-select" ) );
		Select select = new Select( dropdown );

		dropdown.sendKeys( Keys.CONTROL );
		select.selectByValue( "California" );
		select.selectByValue( "Ohio" );

		WebElement printElement = driver.findElement( By.cssSelector( "#printAll" ) );
		printElement.click();

		WebElement textElement = driver.findElement( By.cssSelector( ".getall-selected" ) );
		assertTrue( textElement.getText().contains( "California" ) );
		assertTrue( textElement.getText().contains( "Florida" ) );

		driver.close();
	}
}
