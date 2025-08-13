package stepDefinationWeb;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import baseWeb.WebBrowserManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks extends WebBrowserManager {
	
		
	public Hooks() throws IOException {
		super();
	}

	@After
	public void takeScraenshotOnFailure(Scenario scenario) {
		String scenarioName = scenario.getName();
	if (scenario.isFailed()) {

	TakesScreenshot ts = (TakesScreenshot) driver;

	byte[] src = ts.getScreenshotAs(OutputType.BYTES);
	scenario.attach(src, "image/png", scenarioName);
	}
	}

//	@After(order = 0)
//	public void tearDown() {
//	driver.close();
//
//	}

}
