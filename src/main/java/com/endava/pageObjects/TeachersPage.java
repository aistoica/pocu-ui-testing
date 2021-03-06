package com.endava.pageObjects;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paulhammant.ngwebdriver.NgWebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class TeachersPage extends BasePageObject {

	private By addTeacherButton = By.cssSelector( "button[color='primary']" );
	private By teacherListSelector = By.cssSelector( "app-teacher > div > span:last-child" );
	private By editButton = By.cssSelector( ".edit-icon-container" );

	public TeachersPage( WebDriver driver ) {
		super(driver);
	}

	@Deprecated
	public void waitForLoad() {
		throw new UnsupportedOperationException( "Use waitForLoad with int parameter!" );
	}

	public void visit() {
		Properties properties = new Properties();
		try {
			InputStream file = TeachersPage.class.getClassLoader().getResourceAsStream( "env.properties" );
			properties.load( file );
		} catch ( IOException e ) {
			e.printStackTrace();
		}

		driver.get( properties.getProperty( "url" ) );
	}

	public void waitForLoad(int noOfTeachers) {
		NgWebDriver ngWebDriver = new NgWebDriver( (JavascriptExecutor) driver );
		ngWebDriver.waitForAngularRequestsToFinish();

		WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds( 2 ) );
		wait.until(
				ExpectedConditions.numberOfElementsToBeMoreThan(
						teacherListSelector, noOfTeachers )  );
	}

	public int getNumberOfTeachers() {
		return getTeacherNames().size();
	}

	public AddTeacherPopUp clickAddTeacherButton() {
		driver.findElement( addTeacherButton ).click();

		AddTeacherPopUp addTeacherPopUp = new AddTeacherPopUp( driver );
		addTeacherPopUp.waitForLoad();
		return addTeacherPopUp;
	}

	public List<String> getTeacherNames() {
		List<WebElement> teacherList = driver.findElements( teacherListSelector );
		List<String> teacherNameList = teacherList.stream()
				.map( element -> element.getText() )
				.collect( Collectors.toList());
		return teacherNameList;
	}

	public AddTeacherPopUp editTeacher(String name) {
		int indexToSelect = getTeacherNames().indexOf( name );
		driver.findElements( editButton ).get( indexToSelect ).click();

		AddTeacherPopUp addTeacherPopUp = new AddTeacherPopUp( driver );
		addTeacherPopUp.waitForLoad();
		return addTeacherPopUp;
	}
}
