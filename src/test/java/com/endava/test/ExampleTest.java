package com.endava.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExampleTest {

	@BeforeAll
	public static void before() {
		System.out.println("BEFORE ALL");
	}

	@AfterAll
	public static void after() {
		System.out.println("AFTER ALL");
	}

	@BeforeEach
	public void beforeEach() {
		System.out.println("Before each");
	}

	@AfterEach
	public void afterEach() {
		System.out.println("After each");
	}

	@Test
	public void firstTest() {
		// GIVEN
		int a = 5;
		int b = 4;

		// WHEN
		int c = a+b;

		// THEN
		assertEquals(9, c);
	}
}
