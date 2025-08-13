package pageObjectModel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseWeb.WebBrowserManager;


public class JetStudio_Login {
	public WebDriver driver;
	public JetStudio_Login(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void getTittle(){
	    //System.out.println(driver.getTitle());
	    Assert.assertSame("Dashboard", driver.getTitle());
	}
	@FindBy(xpath="//input[@id='username']")
	WebElement email;

	@FindBy(xpath="//button[text()='Login']")
	WebElement loginButton;
	
	@FindBy(xpath="//input[@name='passwd']")
	WebElement password;

	@FindBy(xpath="//button[@value='default']")
	WebElement continueButton;

	@FindBy(xpath="//button[contains(@class,'button-login')]")
	WebElement continueButtonOnPasswordScreen;
	
	@FindBy(xpath="//input[@id='idSIButton9']")
	WebElement signIn;

	@FindBy(xpath="//input[@id='idSIButton9']")
	WebElement yesButtonOnSignInPopup;
	
//	public WebElement getEmail() {
//		return email;
//		
//	}
	public void setEmail(String emailId) {
		email.sendKeys(emailId);
	}
	public void clickContinueButton() throws InterruptedException {
		Thread.sleep(15000);
		continueButton.click();
	}
	public void clickContinueButtonOnPasswordScreen() {
		continueButtonOnPasswordScreen.click();
	}

	public void clickLoginButton() {
		loginButton.click();
	}
	public void setPassword(String passwordKey) {
		password.sendKeys(passwordKey);
	}
	public void signIn() throws InterruptedException {
		signIn.submit();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(5000);
		yesButtonOnSignInPopup.click();
	}
}
