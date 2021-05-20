package com.endava.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

import com.endava.models.Teacher;
import com.endava.pageObjects.AddTeacherPopUp;
import com.endava.pageObjects.TeachersPage;

public class EditTeacherTest extends TestBaseClass {

	@Test
	public void editTeacherTest() {
		//GIVEN
		TeachersPage teachersPage = new TeachersPage( driver );
		teachersPage.visit();

		AddTeacherPopUp addTeacherPopUp = teachersPage.clickAddTeacherButton();
		Teacher teacher = dataGenerator.generateTeacher();
		TeachersPage newTeacherPage = addTeacherPopUp.addNewTeacher( teacher );

		AddTeacherPopUp editTeacherPopUp = newTeacherPage.editTeacher(teacher.getFirstName() + " " + teacher.getLastName());
		Teacher newTeacher = dataGenerator.generateTeacher();

		// WHEN
		TeachersPage teachersPageAfterEdit = editTeacherPopUp.editTeacher( newTeacher );

		// THEN
		assertThat( teachersPageAfterEdit.getTeacherNames(), hasItem(newTeacher.getFirstName() + " " + newTeacher.getLastName()) );

	}
}
