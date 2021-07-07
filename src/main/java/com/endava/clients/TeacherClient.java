package com.endava.clients;

import static io.restassured.RestAssured.given;

import com.endava.models.Teacher;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.Properties;

public class TeacherClient {

	private String getBaseUri() {
		Properties properties = new Properties();
		try {
			properties.load( TeacherClient.class.getClassLoader().getResourceAsStream( "env.properties" ) );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return properties.getProperty( "baseUri" );
	}

	private int getPort() {
		Properties properties = new Properties();
		try {
			properties.load( TeacherClient.class.getClassLoader().getResourceAsStream( "env.properties" ) );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return Integer.parseInt( properties.getProperty( "port" ) );
	}

	private String getBasePath() {
		Properties properties = new Properties();
		try {
			properties.load( TeacherClient.class.getClassLoader().getResourceAsStream( "env.properties" ) );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return properties.getProperty( "basePath" );
	}

	public Response createTeacher( Teacher teacher ) {
		return given().filter( new LogFilter() )
				.baseUri( getBaseUri() )
				.port( getPort() )
				.basePath( getBasePath() )
				.contentType( ContentType.JSON )
				.body( teacher )
		.when()
				.post("teacher");
	}

	public Response getTeacherById( int id ) {
		return given().filter( new LogFilter() )
				.baseUri( getBaseUri() )
				.port( getPort() )
				.basePath( getBasePath() )
				.pathParam( "id", id )
		.when()
				.get("teacher/{id}");
	}
}
