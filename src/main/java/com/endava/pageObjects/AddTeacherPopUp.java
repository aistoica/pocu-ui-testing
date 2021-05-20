package com.endava.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.endava.models.Teacher;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AddTeacherPopUp extends BasePageObject {

	private By firstNameInput = By.cssSelector( "input[name='firstname']" );
	private By lastNameInput = By.cssSelector( "input[name='lastname']" );
	private By birthDateInput = By.cssSelector( "input[name='birthdate']" );
	private By employmentDateInput = By.cssSelector( "input[name='employmentdate']" );
	private By cnpInput = By.cssSelector( "input[name='cnp']" );
	private By salaryInput = By.cssSelector( "input[name='salary']" );
	private By submitButton = By.cssSelector( "button[type='submit']" );

	public AddTeacherPopUp( WebDriver driver ) {
		super(driver);
	}

	public void waitForLoad() {
		WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds( 2 ) );
		wait.until( ExpectedConditions.elementToBeClickable( submitButton ) );
	}

	public TeachersPage addNewTeacher( Teacher teacher ) {
		driver.findElement( firstNameInput ).sendKeys( teacher.getFirstName() );
		driver.findElement( lastNameInput ).sendKeys( teacher.getLastName() );
		driver.findElement( birthDateInput ).sendKeys( teacher.getBirthDate() );
		driver.findElement( employmentDateInput ).sendKeys( teacher.getEmploymentDate() );
		driver.findElement( cnpInput ).sendKeys( teacher.getCnp() );
		driver.findElement( salaryInput ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		driver.findElement( salaryInput ).sendKeys( teacher.getSalary() );

		TeachersPage teachersPage = new TeachersPage( driver );
		int noOfTeacher = teachersPage.getNumberOfTeachers();

		driver.findElement( submitButton ).submit();

		teachersPage.waitForLoad( noOfTeacher );
		return teachersPage;
	}

	public void addNewInvalidTeacher( Teacher teacher ) {
		driver.findElement( firstNameInput ).sendKeys( teacher.getFirstName() );
		driver.findElement( lastNameInput ).sendKeys( teacher.getLastName() );
		driver.findElement( birthDateInput ).sendKeys( teacher.getBirthDate() );
		driver.findElement( employmentDateInput ).sendKeys( teacher.getEmploymentDate() );
		driver.findElement( cnpInput ).sendKeys( teacher.getCnp() );
		driver.findElement( salaryInput ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		driver.findElement( salaryInput ).sendKeys( teacher.getSalary() );

		driver.findElement( submitButton ).submit();
	}


	public TeachersPage editTeacher( Teacher teacher ) {
		driver.findElement( firstNameInput ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		driver.findElement( firstNameInput ).sendKeys( teacher.getFirstName() );
		driver.findElement( lastNameInput ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		driver.findElement( lastNameInput ).sendKeys( teacher.getLastName() );
		driver.findElement( birthDateInput ).sendKeys( Keys.CONTROL, "A" );
		driver.findElement( birthDateInput ).sendKeys( Keys.DELETE );
		driver.findElement( birthDateInput ).sendKeys( teacher.getBirthDate() );
		driver.findElement( employmentDateInput ).sendKeys( Keys.CONTROL, "A" );
		driver.findElement( employmentDateInput ).sendKeys(  Keys.DELETE );
		driver.findElement( employmentDateInput ).sendKeys( teacher.getEmploymentDate() );
		driver.findElement( cnpInput ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		driver.findElement( cnpInput ).sendKeys( teacher.getCnp() );
		driver.findElement( salaryInput ).sendKeys( Keys.CONTROL, "A", Keys.DELETE );
		driver.findElement( salaryInput ).sendKeys( teacher.getSalary() );

		TeachersPage teachersPage = new TeachersPage( driver );
		int noOfTeacher = teachersPage.getNumberOfTeachers();

		driver.findElement( submitButton ).submit();

		teachersPage.waitForLoad( noOfTeacher - 1  );
		return teachersPage;
	}

	public List<String> getInvalidFields() {
		List<String> invalidFields = new ArrayList<>(  );
		if( driver.findElement( firstNameInput ).getAttribute( "aria-invalid" ).equals( "true" ) ) {
			invalidFields.add( driver.findElement( firstNameInput ).getAttribute( "name" ) );
		}
		if( driver.findElement( lastNameInput ).getAttribute( "aria-invalid" ).equals( "true" ) ) {
			invalidFields.add( driver.findElement( lastNameInput ).getAttribute( "name" ) );
		}
		if( driver.findElement( cnpInput ).getAttribute( "aria-invalid" ).equals( "true" ) ) {
			invalidFields.add( driver.findElement( cnpInput ).getAttribute( "name" ) );
		}

		return invalidFields;
	}
}
