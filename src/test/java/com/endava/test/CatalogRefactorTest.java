package com.endava.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Test;

import com.endava.models.Teacher;
import com.endava.pageObjects.AddTeacherPopUp;
import com.endava.pageObjects.TeachersPage;

public class CatalogRefactorTest extends TestBaseClass {

	@Test
	public void addTeacherTest() {

		//GIVEN
		TeachersPage teachersPage = new TeachersPage( driver );
		teachersPage.visit();
		AddTeacherPopUp addTeacherPopUp = teachersPage.clickAddTeacherButton();

		//AddTeacherPopUp addTeacherPopUp = new AddTeacherPopUp( driver );
		Teacher teacher = dataGenerator.generateTeacher();

		//WHEN
		TeachersPage newTeacherPage = addTeacherPopUp.addNewTeacher( teacher );

		//THEN
		//assertTrue( newTeacherPage.getTeacherNames().contains( teacher.getFirstName()  + "  " + teacher.getLastName() ) );
		assertThat( newTeacherPage.getTeacherNames(), hasItem( teacher.getFirstName()  + " " + teacher.getLastName() ) );
	}

	@Test
	public void addTeacherWithoutFirstNameTest() {

		//GIVEN
		TeachersPage teachersPage = new TeachersPage( driver );
		teachersPage.visit();
		AddTeacherPopUp addTeacherPopUp = teachersPage.clickAddTeacherButton();

		//AddTeacherPopUp addTeacherPopUp = new AddTeacherPopUp( driver );
		Teacher teacher = dataGenerator.generateTeacher();
		teacher.setFirstName( "" );

		//WHEN
		addTeacherPopUp.addNewInvalidTeacher( teacher );

		//THEN
		assertThat( addTeacherPopUp.getInvalidFields(),  hasSize( 1 )  );
		assertThat( addTeacherPopUp.getInvalidFields(),  hasItem( "firstname" )  );
	}
}
