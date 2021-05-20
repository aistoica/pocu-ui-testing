package com.endava.pageObjects;

import org.openqa.selenium.WebDriver;

public abstract class BasePageObject {

	protected WebDriver driver;

	public BasePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public abstract void waitForLoad();
}
