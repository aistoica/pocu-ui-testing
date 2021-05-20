package com.endava.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.endava.pageObjects.AddTeacherPopUp;
import com.endava.pageObjects.TeachersPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class CatalogRefactorTest {

	private WebDriver driver;

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

	@Test
	public void addTeacherTest() {

		TeachersPage teachersPage = new TeachersPage( driver );
		teachersPage.visit();
		AddTeacherPopUp addTeacherPopUp = teachersPage.clickAddTeacherButton();

		//AddTeacherPopUp addTeacherPopUp = new AddTeacherPopUp( driver );
		TeachersPage newTeacherPage = addTeacherPopUp.addNewTeacher( "Andrei", "Stoica", "5/18/2021", "5/18/2021", "1232323232323", "100" );
		assertTrue( newTeacherPage.getTeacherNames().contains( "Andrei Stoica" ) );
	}
}
