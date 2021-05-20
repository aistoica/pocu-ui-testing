package com.endava.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.endava.data.DataGenerator;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public abstract class TestBaseClass {

	protected WebDriver driver;
	protected DataGenerator dataGenerator = new DataGenerator();

	@BeforeEach
	public void setUpDriver() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 10 ) );
		//driver.get( "http://localhost:4200/" );
	}

	@AfterEach
	public void after() throws InterruptedException {
		Thread.sleep( 5000 );
		driver.close();
	}
}
