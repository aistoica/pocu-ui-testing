package com.endava.data;

import com.endava.models.Teacher;
import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DataGenerator {

	private Faker faker = new Faker( Locale.GERMAN );

	public Teacher generateTeacher() {

		DateFormat dateFormat = new SimpleDateFormat( "MM/dd/yyyy" );

		Teacher teacher = new Teacher();
		teacher.setFirstName( faker.name().firstName());
		teacher.setLastName( faker.name().lastName() );
		teacher.setBirthDate( dateFormat.format( faker.date().birthday() ) );
		teacher.setEmploymentDate( dateFormat.format( faker.date().past( 5*365, TimeUnit.DAYS ) ) );
		teacher.setCnp( faker.number().digits( 13 ) );
		teacher.setSalary( faker.number().numberBetween( 100, 300 ) + "" );

		return teacher;
	}
}
