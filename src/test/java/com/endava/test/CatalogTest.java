package com.endava.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogTest {

	private WebDriver driver;

	@BeforeEach
	public void setUpDriver() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 10 ) );
	}

	@AfterEach
	public void after() throws InterruptedException {
		Thread.sleep( 5000 );
		driver.close();
	}

	@Test
	public void addTeacher() {

		//GIVEN
		driver.get( "http://localhost:4200/" );
		WebElement button = driver.findElement( By.cssSelector( "button[color='primary']" ) );
		button.click();

		WebElement firstNameInput = driver.findElement( By.id( "mat-input-0" ) );
		firstNameInput.sendKeys( "Popescu" );

		WebElement lastNameInput = driver.findElement( By.id( "mat-input-1" ) );
		lastNameInput.sendKeys( "Ionescu" );

		WebElement birthDateInput = driver.findElement( By.id( "mat-input-2" ) );
		birthDateInput.sendKeys( "5/18/2021" );

		WebElement employmentDateInput = driver.findElement( By.id( "mat-input-3" ) );
		employmentDateInput.sendKeys( "5/18/2021" );

		WebElement cnpInput = driver.findElement( By.id( "mat-input-4" ) );
		cnpInput.sendKeys( "1562645263256" );

		WebElement salaryInput = driver.findElement( By.id( "mat-input-5" ) );
		salaryInput.sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		salaryInput.sendKeys( "100" );

		// find how many teachers before the submit button
		int noOfTeachers = driver.findElements( By.cssSelector( "app-teacher > div > span:last-child" ) ).size();

		//WHEN
		WebElement submitButton = driver.findElement( By.cssSelector( "button[type='submit']" ) );
		submitButton.submit();

		//THEN
		WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds( 2 ) );
		List<WebElement> teacherList = wait.until(
						ExpectedConditions.numberOfElementsToBeMoreThan(
								By.cssSelector( "app-teacher > div > span:last-child" ), noOfTeachers )  );

		//List<WebElement> teacherList = driver.findElements( By.tagName( "app-teacher" ) );
		List<String> teacherNameList = teacherList.stream()
				.map( element -> element.getText() )
				.collect( Collectors.toList());
		assertTrue(teacherNameList.contains( "Popescu Ionescu" ));
	}
}
