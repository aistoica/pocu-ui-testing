package com.endava.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddTeacherPopUp extends BasePageObject {

	private By firstNameInput = By.id( "mat-input-0" );
	private By lastNameInput = By.id( "mat-input-1" );
	private By birthDateInput = By.id( "mat-input-2" );
	private By employmentDateInput = By.id( "mat-input-3" );
	private By cnpInput = By.id( "mat-input-4" );
	private By salaryInput = By.id( "mat-input-5" );
	private By submitButton = By.cssSelector( "button[type='submit']" );

	public AddTeacherPopUp( WebDriver driver ) {
		super(driver);
	}

	public void waitForLoad() {
		WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds( 2 ) );
		wait.until( ExpectedConditions.elementToBeClickable( submitButton ) );
	}

	public TeachersPage addNewTeacher(String firstName, String lastName, String birthDate, String employmentDate, String cnp, String salary) {
		driver.findElement( firstNameInput ).sendKeys( firstName );
		driver.findElement( lastNameInput ).sendKeys( lastName );
		driver.findElement( birthDateInput ).sendKeys( birthDate );
		driver.findElement( employmentDateInput ).sendKeys( employmentDate );
		driver.findElement( cnpInput ).sendKeys( cnp );
		driver.findElement( salaryInput ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		driver.findElement( salaryInput ).sendKeys( salary );

		TeachersPage teachersPage = new TeachersPage( driver );
		int noOfTeacher = teachersPage.getNumberOfTeachers();

		driver.findElement( submitButton ).submit();

		teachersPage.waitForLoad( noOfTeacher );
		return teachersPage;
	}
}
