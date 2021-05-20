package com.endava.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

public abstract class BasePageObject {

	protected WebDriver driver;

	public BasePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public abstract void waitForLoad();

	protected Object executeScript( String script, Object... objects ) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object result = js.executeScript( script, objects );
		return result;
	}
}
