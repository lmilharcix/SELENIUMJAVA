package testRunner;

import java.io.IOException;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.Configuration;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import org.junit.AfterClass;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/testFeatureWeb",
		glue= {"stepDefinationWeb"},
		dryRun = false,
		monochrome = true,
		tags = "@abc1", //Use @apiSmoke for running price quotes vistajet api test cases
		//plugin = {"pretty","json:Reports/cucumber.json","html:Reports/cucumber.json"},
		plugin={"pretty","json:Reports/cucumber.json","html:Reports/cucumber.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		publish = true
		)


public class TestRunner {
	@AfterClass
	public static void reportSetup() throws IOException {
		File reportOutputDirectory = new File("Reports");
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add("Reports/cucumber.json");
		String projectName = "QA Test Automation";
		
		Configuration configuration = new Configuration (reportOutputDirectory,projectName);
		configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles,configuration);
		Reportable result = reportBuilder.generateReports();
	}

}
