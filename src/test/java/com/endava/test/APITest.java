package com.endava.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import com.endava.clients.TeacherClient;
import com.endava.data.DataGenerator;
import com.endava.models.Teacher;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APITest {

	private DataGenerator dataGenerator = new DataGenerator();
	private TeacherClient teacherClient = new TeacherClient();

	@Test
	public void testApi() {
		//GIVEN
		Teacher teacher = dataGenerator.generateAPITeacher();

		//WHEN
		Response response = teacherClient.createTeacher( teacher );

		//THEN
		response.then()
				.statusCode( HttpStatus.SC_OK )
				.header( "Content-Type", is("application/json" ) )
				.body( "id", allOf( notNullValue(), greaterThanOrEqualTo( 0 ) ) )
				.body( "firstName", is( teacher.getFirstName() ) )
				.body( "lastName", is( teacher.getLastName() ) )
				.body( "birthDate", is(teacher.getBirthDate()) )
				.body( "employmentDate", is( teacher.getEmploymentDate()) )
				.body( "cnp",  is( teacher.getCnp() ) )
				.body( "salary", is( Integer.parseInt( teacher.getSalary() ) ) );
	}

	@Test
	public void testGetApi() {

		//GIVEN
		Teacher teacher = dataGenerator.generateAPITeacher();
		Response response = teacherClient.createTeacher( teacher );
		int id = response.body().jsonPath().getInt( "id" );

		//WHEN
		Response getTeacherById = teacherClient.getTeacherById( id );

		//then
		getTeacherById.then().statusCode( HttpStatus.SC_OK );
	}

	@Test
	public void testGetAllApi() {

		Teacher teacher = dataGenerator.generateAPITeacher();

		Response response = given().baseUri( "http://localhost" )
				.port( 8081 )
				.basePath( "catalog" )
				.contentType( ContentType.JSON )
				.body( teacher )
				.when()
				.post( "teacher" );
		int id = response.body().jsonPath().getInt( "id" );

		given().baseUri( "http://localhost" )
				.port( 8081 )
				.basePath( "catalog" )
				.queryParam( "firstName", teacher.getFirstName() )
				.queryParam( "lastName", teacher.getLastName() )
		.when()
				.get("teacher")
		.then()
			.statusCode( HttpStatus.SC_OK )
			.body( "" , hasSize( 1 ) )
			.body( "id[0]" , is( id ) )
			.body( "firstName[0]", is( teacher.getFirstName() ) );
	}
}
