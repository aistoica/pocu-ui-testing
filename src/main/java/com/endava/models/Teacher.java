package com.endava.models;

import java.util.Objects;

public class Teacher {

	private String firstName;
	private String lastName;
	private String birthDate;
	private String employmentDate;
	private String cnp;
	private String salary;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate( String birthDate ) {
		this.birthDate = birthDate;
	}

	public String getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate( String employmentDate ) {
		this.employmentDate = employmentDate;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp( String cnp ) {
		this.cnp = cnp;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary( String salary ) {
		this.salary = salary;
	}

	@Override
	public boolean equals( Object o ) {
		if ( this == o )
			return true;
		if ( o == null || getClass() != o.getClass() )
			return false;
		Teacher teacher = (Teacher) o;
		return Objects.equals( firstName, teacher.firstName ) &&
				Objects.equals( lastName, teacher.lastName ) &&
				Objects.equals( birthDate, teacher.birthDate ) &&
				Objects.equals( employmentDate, teacher.employmentDate ) &&
				Objects.equals( cnp, teacher.cnp ) &&
				Objects.equals( salary, teacher.salary );
	}

	@Override
	public int hashCode() {
		return Objects.hash( firstName, lastName, birthDate, employmentDate, cnp, salary );
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", birthDate='" + birthDate + '\'' +
				", employmentDate='" + employmentDate + '\'' +
				", cnp='" + cnp + '\'' +
				", salary='" + salary + '\'' +
				'}';
	}
}
