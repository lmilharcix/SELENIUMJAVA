package pageObjectModel;
import java.text.DecimalFormat;
import java.time.Duration;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import baseWeb.WebBrowserManager;
import utils.MongoDBReader;
import static utils.MongoDBReader.*;
public class JetStudio_Calculator {
	
	public WebDriver driver;
	// initElements is a static method in Page Factory class. Using the initElements method, one can initialize all the web elements located by @FindBy annotation.
	public JetStudio_Calculator(WebDriver driver){	
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Leg 1: origin , destination and date
	@FindBy(xpath="//input[@placeholder='Departure ICAO']")
	WebElement origin;
	@FindBy(xpath="//input[@placeholder='Destination ICAO']")
	WebElement destination;
	
	@FindBy(id="datetimepicker-1")
	WebElement departuredate;
	//Leg 2: origin2 ,destination2 and date2
	@FindBy(xpath="(//input[@placeholder='Departure ICAO'])[2]")
	WebElement origin2;
	@FindBy(xpath="(//input[@placeholder='Destination ICAO'])[2]")
	WebElement destination2;
	@FindBy(xpath="(//input[@placeholder='Departure ICAO'])[2]")
	WebElement departuredate2;
	//Add leg button
	@FindBy(xpath="//button[contains(text(),'Add Leg')]")
	WebElement addleg;
	
	//Add Roundtrip Leg button
	@FindBy(xpath="//button[contains(text(),'Add RT Leg')]")
	WebElement addrtleg;
	//Membership types:
	//WebElement membership;      
	//@FindBy(xpath="//div[@class='carbon-dropdownlist-wrap']//span//span")

	@FindBy(xpath="//div[@class='carbon-dropdownlist-wrap']//*[@id='aircraft-category']//span//span")
	WebElement vendorDropdown;

	@FindBy(xpath="//input[@aria-valuemax='999']")
	WebElement paxDropDown;


	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[3]/div[1]/div[3]/div/div/kendo-switch/span[1]/span[1]")
	WebElement deptimechange;
	
	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[3]/div[1]/div[3]/div/div/kendo-switch/span[1]/span[2]")
	WebElement showpricewhendnq;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[3]/div[1]/div[4]/div/div/kendo-switch/span[1]/span[1]")
	WebElement wholesaleretail;

	@FindBy(xpath="//button[contains(text(),'Calculate')]")
	WebElement calculatebutton;

	//Totals
	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/div/pricing-calculator-output/div/div/div/div/div/div[2]/table/tbody/tr[5]/td[2]/div/span[2]")
	WebElement totalxolite;

	@FindBy(xpath="//span[@class='total-price']")
	List<WebElement> prices;

	/** LILIAN 04_30_25
	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/div/pricing-calculator-output/div/div/div/div/div/div[2]/table/tbody/tr[5]/td[3]/div/span[2]")
	WebElement totalcitx;
	*/

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/div/pricing-calculator-output/div/div/div/div/div/div[2]/table/tbody/tr[5]/td[4]/div/span[2]")
	WebElement totalnregsmid;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/div/pricing-calculator-output/div/div/div/div/div/div[2]/table/tbody/tr[5]/td[5]/div/span[2]")
	WebElement totalnregulr;

	//Checkboxes-xojet
	
	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[1]/div/label/input")
	WebElement lite;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[1]/label/input")
	WebElement citx;

	//vistajet
	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")
	WebElement nregsmid;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[3]/label/input")
	WebElement nregulr;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/label/input")
	WebElement cl350;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div[2]/div[2]/label/input")
	WebElement cl604;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div[2]/div[3]/label/input")
	WebElement cl605;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div[2]/div[4]/label/input")
	WebElement cl850;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[2]/div[2]/div[5]/label/input")
	WebElement gl6k;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div[6]/label/input")
	WebElement gl7k;
	
	//AH
	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[2]/div[1]/label/input")
	WebElement p300;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[2]/div[2]/label/input")
	WebElement cxls;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[2]/div[3]/label/input")
	WebElement l500;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[2]/div[3]/label/input")
	WebElement p600;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div[1]/div/div[1]/div[2]/div[5]/label/input")
	WebElement l600;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[2]/div[6]/label/input")
	WebElement l650;

	@FindBy(xpath="/html/body/app-root/div/div/pricing-calculator/div/div/pricing-calculator-input/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div[2]/div[7]/label/input")
	WebElement fa7x;

	@FindBy(xpath="//*[@class='k-label']")
	List<WebElement> elacCategoriesList;

	@FindBy(xpath="//label[contains(text(),'%s')]")
	List<WebElement> categoriesListWithName;

	@FindBy(xpath="//span[contains(text(),'Simple view')]")
	WebElement simpleViewToggle;

	@FindBy(xpath="//span[contains(text(),'Red Eye') or contains(text(),'RZO Surcharge')]/following-sibling::span")
	WebElement redEyeFees;

	@FindBy(xpath="//span[contains(text(),'Dining')]/following-sibling::span")
	List<WebElement> diningFees;

	@FindBy(xpath="//span[contains(text(),'De Icing')]/following-sibling::span")
	List<WebElement> diIcingFees;

	@FindBy(xpath="//span[contains(text(),'Customer Service')]/following-sibling::span")
	List<WebElement> customerServiceFees;

	//LILIAN-NEW
	@FindBy(xpath="//div[@class='carbon-dropdownlist-wrap']//span//span")
	WebElement membershipDropdownCalculator;

	@FindBy(xpath="//span[contains(text(),'Non-Member Fee')]/following-sibling::span")
	List<WebElement> nonMemberFee;


	@FindBy(xpath="//span[contains(text(),'On Demand ETS charge')]/following-sibling::span")
	List<WebElement> etsFee;


	@FindBy(xpath="//span[contains(text(),'Segment Fee/Other Taxes')]/following-sibling::span")
	List<WebElement> segmentFee;


	@FindBy(xpath="//*[@tooltip='Pax']") //LM
	List<WebElement> paxCategories;


	//LM @FindBy(xpath="//span[contains(text(),' Exceeded max pax limit: 7, requested: 20 pax ')]/following-sibling::span")
	//LM List<WebElement> subtotal;
	@FindBy(xpath="//span[contains(text(),'Subtotal')]/following-sibling::span")
	List<WebElement> subtotalvalue;

	@FindBy(xpath="//span[contains(text(),'Portugal Co2')]/following-sibling::span")
	List<WebElement> portugalFee;

	//**LILIAN 05_20_25 begin
	@FindBy(xpath="//div[contains(text(),' Exceeded max pax limit: 7, requested: 20 pax ')]")
	List<WebElement> liteexceededmaxpax;
	
	@FindBy(xpath="//div[contains(text(),' Exceeded max pax limit: 8, requested: 20 pax ')]")
	List<WebElement> nregsmidexceededmaxpax;
	
	@FindBy(xpath="//div[contains(text(),' Exceeded max pax limit: 12, requested: 20 pax ')]")
	List<WebElement> nregulrexceededmaxpax;
	
	//**LILIAN 05_20_25 end
	
	//***LILIAN***

		@FindBy(xpath="//span[@class='item-label' and contains(text(),'Landing')]/following-sibling::span")
		List<WebElement> landingFee;
		
		@FindBy(xpath="//div[contains(text(),' Insufficient runway length.')]")
		List<WebElement> minRunwayMsg;
		
		
		@FindBy(xpath="//span[contains(text(),'Maldives Passenger Tax')]/following-sibling::span")
		List<WebElement> maldivesPassengerTax;
	
		@FindBy(xpath="//span[contains(text(),'VRMM Airport Development Tax')]/following-sibling::span")
		List<WebElement> VRMMairportDevelopmentTax;
		
		@FindBy(xpath="//span[contains(text(),'VRDA Vip Lounge')]/following-sibling::span")
		List<WebElement> VRDAVipLounge;
		
		@FindBy(xpath="//span[contains(text(),'VRMM Vip Lounge')]/following-sibling::span")
		List<WebElement> VRMMVipLounge;
		
	//***LILIAN***


	@FindBy(xpath="//span[contains(text(),'Total')]/following-sibling::span")
	List<WebElement> totalvalue;

	@FindBy(xpath="//span[contains(text(),'Overnight')]/following-sibling::span")
	List<WebElement> overnight;

	@FindBy(xpath="//td[@data-field='segment']//div")
	List<WebElement> cabotage;

	@FindBy(xpath="//span[contains(text(),'Firm')]/following-sibling::span")
	List<WebElement> firmFee;

	@FindBy(xpath="//span[contains(text(),'Service Fee')]/following-sibling::span")
	List<WebElement> ServiceFee;


	//LILIAN 07-03-25 begin
	@FindBy(xpath="//span[contains(text(),'Xo Service Charge')]/following-sibling::span")
	List<WebElement> XoServiceCharge;
	//LILIAN 07-03-25 end


	//LILIAN 06-24-25 begin
	@FindBy(xpath="//span[contains(text(),'Xo Service Charge')]/following-sibling::span")
	List<WebElement> XOServiceFee;
	//LILIAN 06-24-25  end

	@FindBy(xpath="//span[contains(text(),'Other Aircraft Fee')]/following-sibling::span")
	List<WebElement> otherAircraftFee;
	@FindBy(xpath="//span[contains(text(),'Flex')]")
	WebElement firmFeeToggle;
	@FindBy(xpath="//span[@tooltip='Expected Flight Time']")
	WebElement liveTime;
	@FindBy(xpath="//span[@class='item-label' and contains(text(),'Co2')]/following-sibling::span")
	WebElement CO2Fees;
	@FindBy(xpath="//span[@class='item-label' and contains(text(),'Fuel Surcharge')]/following-sibling::span")
	WebElement fuelSurcharge;
	//LILIAN 05-29-25 begin
	//@FindBy(xpath="//span[@class='item-label' and contains(text(),'FET Tax (7.5%)')]/following-sibling::span")
	@FindBy(xpath="//span[@class='item-label' and contains(text(),'FET 7.5% US')]/following-sibling::span")
	//LILIAN 05-29-25 end
	WebElement FETFees;
	@FindBy(xpath="//span[@class='item-label' and contains(text(),'Subtotal')]/following-sibling::span")
	WebElement SubtotalFees;
	//LILIAN 06-04-25 begin
	//LILIAN 07-03-25 begin
	//@FindBy(xpath="//span[@class='item-label' and contains(text(),'Origin Airport Fee')]/following-sibling::span")
	//WebElement originAirportFee;
	@FindBy(xpath="//span[@class='item-label' and contains(text(),'RWAPremiumDepart')]/following-sibling::span") 
	WebElement departureAirportFee;
	
	//@FindBy(xpath="//span[@class='item-label' and contains(text(),'Destination Airport Fee')]/following-sibling::span")
	//WebElement destinationAirportFee;
	@FindBy(xpath="//span[@class='item-label' and contains(text(),'RWAPremiumArrive')]/following-sibling::span") 
	WebElement arriveAirportFee;
	//LILIAN 07-03-25 end
	@FindBy(xpath="//span[@class='item-label' and contains(text(),'Base price')]/following-sibling::span")
	WebElement basePrice;
	
	@FindBy(xpath="//span[@class='total-price']")
	WebElement totalprice;
	//LILIAN 06-04-25 end
	//LILIAN 06-26-25 begin
	@FindBy(xpath="//div[contains(text(),' No price returned because route is banned. ')]")
	List<WebElement> bannedMsg;
	
	
	@FindBy(xpath="//div[contains(text(),' Out of service area for AH LEGACY 600 Guaranteed pricing, no guaranteed availability. ')]")
	List<WebElement> outServiceAreaAHL600Msg;
	
	@FindBy(xpath="//span[contains(text(),'Fuel Stop')]/following-sibling::span")
	List<WebElement> fuelStop;
	
	@FindBy(xpath="//span[contains(text(),'Stacking Discount')]/following-sibling::span")
	List<WebElement> stackingDiscount;
	
	@FindBy(xpath="//div[contains(text(),' Flight time (7.33h) outside the aircraft range (5.00h) ')]")
	List<WebElement> flightTimeAHP300_CITXLSMsg1;
	
	@FindBy(xpath="//div[contains(text(),' Flight time (5.17h) outside the aircraft range (5.00h) ')]")
	List<WebElement> flightTimeAHP300_CITXLSMsg2;
	
	@FindBy(xpath="//div[contains(text(),' Flight time (7.83h) outside the aircraft range (5.00h) ')]")
	List<WebElement> flightTimeAHP300_CITXLSMsg3;
	
	@FindBy(xpath="//div[contains(text(),' Flight time (6.67h) outside the aircraft range (5.00h) ')]")
	List<WebElement> flightTimeAHP300_CITXLSMsg4;
	
	@FindBy(xpath="//div[contains(text(),' Flight time (17.50h) outside the aircraft range (15.00h) ')]")
	List<WebElement> flightTimeAHL650Msg;
	//LILIAN 06-26-25 end
	
	//LILIAN 07-26-25 begin
	@FindBy(xpath="//span[contains(text(),'International Head Tax')]/following-sibling::span")
	List<WebElement> internationalHeadTax;
	//LILIAN 07-26-25 end
	
	//LILIAN 08-03-25 begin
	@FindBy(xpath="//span[contains(text(),'Wifi')]/following-sibling::span")
	List<WebElement> Wifi;
	//LILIAN 08-03-25 end
	
	
	////////////////////////////////////////////////////////////////////////////////////////
	//METHODS
	public void setOrigin(String icaoOrigin)throws InterruptedException {
		origin.clear();
		origin.sendKeys(icaoOrigin);
	}
	public void setDestination(String icaoDestination) throws InterruptedException {
		destination.clear();
		destination.sendKeys(icaoDestination);
	}
	//public void setDepartureDate() throws InterruptedException {
	//departuredate.clear();
	//LocalDate currentDate = LocalDate.now();
	//LocalDate futureDate = currentDate.plusDays(7);
	//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	//String formattedFutureDate = futureDate.format(formatter);
	//String script = "arguments[0].value = arguments[1];";
	//String formattedDateTime = formattedFutureDate + " 12:00 PM";
	//((JavascriptExecutor) driver).executeScript(script, departuredate, formattedDateTime);
	//Thread.sleep(1000);
	//}
	public void setDepartureDate() throws InterruptedException {
		departuredate.clear();
		departuredate.click();
		Thread.sleep(3000);
		Actions ac= new Actions(driver);
		ac.click(departuredate);
		ac.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE);
		Thread.sleep(3000);
		ac.sendKeys("08");
		//ac.sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(3000);
		ac.sendKeys("30");
		//ac.sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(3000);
		ac.sendKeys("2025");
		//ac.sendKeys(Keys.ARROW_RIGHT);
		ac.sendKeys("12");
		ac.sendKeys("00");
		ac.perform();
		//departuredate.sendKeys("4/30/2024 12:00 PM");
		Thread.sleep(1000);
	}
	public void setDepartureDateAndMorningTime() throws InterruptedException {
		departuredate.clear();
		departuredate.click();
		Thread.sleep(3000);
		Actions ac= new Actions(driver);
		ac.click(departuredate);
		ac.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE);
		Thread.sleep(3000);
		ac.sendKeys("08");
		//ac.sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(3000);
		ac.sendKeys("30");
		//ac.sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(3000);
		ac.sendKeys("2025");
		//ac.sendKeys(Keys.ARROW_RIGHT);
		ac.sendKeys("06");
		ac.sendKeys("20");
		ac.sendKeys("AM");
		ac.perform();
		//departuredate.sendKeys("4/30/2024 12:00 PM");
		Thread.sleep(1000);
	}

	public void setDepartureDateAndNightTime() throws InterruptedException {
		departuredate.clear();
		departuredate.click();
		Thread.sleep(3000);
		Actions ac= new Actions(driver);
		ac.click(departuredate);
		ac.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE);
		Thread.sleep(3000);
		ac.sendKeys("08");
		//ac.sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(3000);
		ac.sendKeys("30");
		//ac.sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(3000);
		ac.sendKeys("2025");
		//ac.sendKeys(Keys.ARROW_RIGHT);
		ac.sendKeys("02");
		ac.sendKeys("20");
		ac.sendKeys("AM");
		ac.perform();
		//departuredate.sendKeys("4/30/2024 12:00 PM");
		Thread.sleep(1000);
	}

	public void setDepartureDateAndExtendedNightTime() throws InterruptedException {
		departuredate.clear();
		departuredate.click();
		Thread.sleep(3000);
		Actions ac= new Actions(driver);
		ac.click(departuredate);
		ac.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE);
		Thread.sleep(3000);
		ac.sendKeys("08");
		//ac.sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(3000);
		ac.sendKeys("30");
		//ac.sendKeys(Keys.ARROW_RIGHT);
		Thread.sleep(3000);
		ac.sendKeys("2025");
		//ac.sendKeys(Keys.ARROW_RIGHT);
		ac.sendKeys("11");
		ac.sendKeys("45");
		ac.sendKeys("PM");
		ac.perform();
		//departuredate.sendKeys("4/30/2024 12:00 PM");
		Thread.sleep(1000);
	}
	public void setCalculateButton() throws InterruptedException{
		Thread.sleep(3000);
		calculatebutton.click();
		Thread.sleep(2000);
	
	}
	
	//Leg 2
	public void setOrigin2(String icaoOrigin2) throws InterruptedException{
		//origin2.clear();
		Thread.sleep(500);
		origin2.sendKeys(icaoOrigin2);
		Thread.sleep(500);
	}
	
	public void setDestination2(String icaoDestination2) throws InterruptedException{
		//destination2.clear();
		destination2.sendKeys(icaoDestination2);
		Thread.sleep(500);
	}

	public void setDepartureDateNew() throws InterruptedException {
		departuredate.clear();
		LocalDate currentDate2 = LocalDate.now();
		LocalDate futureDate2 = currentDate2.plusDays(7);
		DateTimeFormatter formatter2 = null;
		DateTimeFormatter.ofPattern("MM/dd/yyyy HH:SS'Z'");
		String formattedFutureDate2 = futureDate2.format(formatter2);
		String script2 = "arguments[0].value = arguments[1]";
		//String formattedDateTime2 = formattedFutureDate2";
		((JavascriptExecutor) driver).executeScript(script2, departuredate,formattedFutureDate2);
		Thread.sleep(1000);
	}

	public void setDepartureDate2() throws InterruptedException{
		//departuredate2.clear();
		departuredate2.click();
		Thread.sleep(1000);
	}
		
	//add leg
	public void setAddLeg() throws InterruptedException{
		addleg.click();
	}
	
	//add round trip leg
	public void setAddRtLeg() throws InterruptedException{
		addrtleg.click();
	}
	
	//Dropdown
	public void setMembership() throws InterruptedException{
		//Select dropdown = new Select(membership);
		//dropdown.selectByVisibleText(membershiptype);
		vendorDropdown.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement optionToClick=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='XO MEMBERSHIP']")));
		optionToClick.click();
	}

	
	//public void setPax(String passenger) {  //***LILIAN 04_30_25
	public void setPax(Integer passenger) {
		String str = Integer.toString(passenger); //***LILIAN 04_30_25
		System.out.println(str);					//***LILIAN 04_30_25

		paxDropDown.click();						//***LILIAN 04_30_25
		paxDropDown.sendKeys(str);
		//paxDropDown.sendKeys(passenger); //***LILIAN 04_30_25
	
	}

	public void setDepTimeChange() throws InterruptedException{
		deptimechange.click();
	}
	public void setShowDnq() throws InterruptedException {
		showpricewhendnq.click();
	}

	public void setWholeSaleRetail() throws InterruptedException {
		wholesaleretail.click();
	}

	//Results
	public void verifyTotalXolite() throws InterruptedException{

		List<Double> allPrices=new ArrayList<Double>();;
		for(int i=0;i<prices.size();i++){
			String prices1=prices.get(i).getText();
			String cleanString1 = prices1.replaceAll("\\$", "").replaceAll(",","");
			// Parse the cleaned string into a double
			double value1 = Double.parseDouble(cleanString1);
			allPrices.add(value1);
			Assert.assertTrue(value1>6000.00,"Value is less than 6000");
		}
	}

	/* LILIAN 04_30_25
	public void verifyTotalCitx() throws InterruptedException {
		// Get the text of the element
		String text2 = totalcitx.getText();//returns the text content inside the field
		// Remove dollar sign from the string
		String cleanString2 = text2.replaceAll("\\$", "").replaceAll(",","");
		// Parse the cleaned string into a double
		double value2 = Double.parseDouble(cleanString2);
		Assert.assertTrue(value2>6000.00,"Value is less than 6000");
	}
	*/
	
	
	public void verifyTotalNregsmid() {
		// Get the text of the element
		String text3 = totalnregsmid.getText();//returns the text content inside the field
		// Remove dollar sign from the string
		String cleanString3 = text3.replaceAll("\\$", "").replaceAll(",","");	
		// Parse the cleaned string into a double
		double value3 = Double.parseDouble(cleanString3);
		Assert.assertTrue(value3>6000.00,"Value is less than 6000");
	}
	public void verifyTotalNregulr() throws InterruptedException {
		// Get the text of the element
		String text4 = totalnregulr.getText();//returns the text content inside the field
		// Remove dollar sign from the string
		String cleanString4 = text4.replaceAll("\\$", "").replaceAll(",","");
		// Parse the cleaned string into a double
		double value4 = Double.parseDouble(cleanString4);
		Assert.assertTrue(value4>6000.00,"Value is less than 6000");
	
	}
		
	//checkboxes
	public void setCatLite() throws InterruptedException {
		lite.click();
	}
	public void setCatCitx() throws InterruptedException {
		citx.click();
	}
	public void setCatNregSmid() throws InterruptedException {
		nregsmid.click();
		}
	
	public void setCatNregUrl() throws InterruptedException {
		nregulr.click();
	}

	public void setCatCl350() throws InterruptedException {
		cl350.click();
	}
	public void setCatCl604() throws InterruptedException {
		cl604.click();
	}
	public void setCatCl605() throws InterruptedException{
		cl605.click();
	}
	public void setCatCl850() throws InterruptedException{
		cl850.click();
	}
	public void setCatGl6k() throws InterruptedException {
		gl6k.click();
	}
	public void seCatGl7k() throws InterruptedException {
		gl7k.click();
	}
	public void setCatP300() throws InterruptedException {
		p300.click();
	}
	public void setCatCxls() throws InterruptedException {
		cxls.click();
	}
	public void setCatL500() throws InterruptedException {
		l500.click();
	}
	public void setCatP600() throws InterruptedException {
		p600.click();
	}
	public void setCatL600() throws InterruptedException {
		l600.click();
	}
	public void setCatL650() throws InterruptedException {
		l650.click();
	}
	public void setCatFa7x() throws InterruptedException {
		fa7x.click();
	}

	public void verifyTotalForAllFlights() throws InterruptedException {
		// Get the text of the element

		String text1;
		String cleanString1;
		double value1;
		// Remove dollar sign from the string
		for (int i = 0; i < prices.size(); i++) {
			text1 = prices.get(i).getText();
			cleanString1 = text1.replaceAll("\\$", "").replaceAll(",", "");
			value1 = Double.parseDouble(cleanString1);
			System.out.println("Prices are: "+value1);
			ExtentCucumberAdapter.addTestStepLog("Prices are: "+value1);
			Assert.assertTrue(value1 > 6000.00, "Value is less than 6000");
		}
	}

		public void verifyTotalForAllFlightsForELAC() throws InterruptedException{

		int elacCatoriessize=elacCategoriesList.size();
		System.out.println("number of categories in Elac are: "+elacCatoriessize );
		String text1;
		String cleanString1;
		double value1;
		// Remove dollar sign from the string
		for(int i=0;i<prices.size();i++){
			text1 = prices.get(i).getText();
			cleanString1 = text1.replaceAll("\\$", "").replaceAll(",","");
			value1 = Double.parseDouble(cleanString1);
			System.out.println("Prices are: "+value1);
			ExtentCucumberAdapter.addTestStepLog("Prices are: "+value1);
			Assert.assertTrue(value1>6000.00,"Value is less than 6000");
		}

		//uncheck all selected categories
			for(int i=0;i<elacCatoriessize-1;i++){
				elacCategoriesList.get(i).click();
				System.out.println("Uncheck category: "+elacCategoriesList.get(i).getText());
			}
		//check last category of Elac
				elacCategoriesList.get(elacCatoriessize-1).click();
			    System.out.println("Selected category as: "+elacCategoriesList.get(elacCatoriessize-1).getText());

				Thread.sleep(5000);
			calculatebutton.click();
			Thread.sleep(5000);
			for(int i=0;i<prices.size();i++){
				text1 = prices.get(i).getText();
				cleanString1 = text1.replaceAll("\\$", "").replaceAll(",","");
				value1 = Double.parseDouble(cleanString1);
				System.out.println("Prices are: "+value1);
				ExtentCucumberAdapter.addTestStepLog("Prices are: "+value1);
				Assert.assertTrue(value1>6000.00,"Value is less than 6000");
			}
	}

	public void verifyTotalForAllFlightsForXoMemberNonMember() throws InterruptedException{

		int elacCatoriessize=elacCategoriesList.size();
		System.out.println("number of categories in Xo Member/ NonMember are: "+elacCatoriessize );
		String text1;
		String cleanString1;
		double value1;
		// Remove dollar sign from the string
		/*for(int i=0;i<prices.size();i++){
			text1 = prices.get(i).getText();
			cleanString1 = text1.replaceAll("\\$", "").replaceAll(",","");
			value1 = Double.parseDouble(cleanString1);
			Assert.assertTrue(value1>6000.00,"Value is less than 6000");
		}*/

		//uncheck all selected categories
			elacCategoriesList.get(0).click();
			elacCategoriesList.get(6).click();
			elacCategoriesList.get(7).click();
			//elacCategoriesList.get(8).click();
		Thread.sleep(5000);
		//check Next Four category of XO NonMember
		elacCategoriesList.get(1).click();
		elacCategoriesList.get(2).click();
		elacCategoriesList.get(3).click();
		elacCategoriesList.get(4).click();

		Thread.sleep(5000);
		calculatebutton.click();
		Thread.sleep(5000);
		for(int i=0;i<prices.size();i++){
			text1 = prices.get(i).getText();
			cleanString1 = text1.replaceAll("\\$", "").replaceAll(",","");
			value1 = Double.parseDouble(cleanString1);
			System.out.println("Prices are: "+value1);
			ExtentCucumberAdapter.addTestStepLog("Prices are: "+value1);
			Assert.assertTrue(value1>6000.00,"Value is less than 6000");
		}
		Thread.sleep(5000);
		//uncheck all selected categories
		elacCategoriesList.get(1).click();
		elacCategoriesList.get(2).click();
		elacCategoriesList.get(3).click();
		elacCategoriesList.get(4).click();

		Thread.sleep(5000);
		//check Next Four category of XO NonMember
		elacCategoriesList.get(5).click();
		elacCategoriesList.get(8).click(); //LILIAN 04_30_25
		elacCategoriesList.get(9).click();
		elacCategoriesList.get(10).click(); //LILIAN 04_30_25
		//elacCategoriesList.get(11).click();
		Thread.sleep(5000);
		calculatebutton.click();
		Thread.sleep(5000);
		for(int i=0;i<prices.size();i++){
			text1 = prices.get(i).getText();
			cleanString1 = text1.replaceAll("\\$", "").replaceAll(",","");
			value1 = Double.parseDouble(cleanString1);
			System.out.println("Prices are: "+value1);
			ExtentCucumberAdapter.addTestStepLog("Prices are: "+value1);
			Assert.assertTrue(value1>6000.00,"Value is less than 6000");
		}
		Thread.sleep(5000);

		//uncheck all selected categories
		elacCategoriesList.get(5).click();
		elacCategoriesList.get(8).click(); //LILIAN 04_30_25
		elacCategoriesList.get(9).click();
		elacCategoriesList.get(10).click(); //LILIAN 04_30_25
		//elacCategoriesList.get(10).click();
		//elacCategoriesList.get(11).click();

		Thread.sleep(5000);
		//check Next Four category of XO NonMember
		//elacCategoriesList.get(12).click(); //InSufficient Runway length error
		elacCategoriesList.get(13).click(); //InSufficient Runway length error
		elacCategoriesList.get(14).click(); //InSufficient Runway length error
		//elacCategoriesList.get(15).click();
		Thread.sleep(5000);
		calculatebutton.click();
		Thread.sleep(5000);
		for(int i=0;i<prices.size();i++){
			text1 = prices.get(i).getText();
			cleanString1 = text1.replaceAll("\\$", "").replaceAll(",","");
			value1 = Double.parseDouble(cleanString1);
			System.out.println("Prices are: "+value1);
			ExtentCucumberAdapter.addTestStepLog("Prices are: "+value1);
			Assert.assertTrue(value1>6000.00,"Value is less than 6000");
		}
		Thread.sleep(5000);
		//uncheck all selected categories
		elacCategoriesList.get(11).click(); //LILIAN 04_30_25
		elacCategoriesList.get(12).click(); //LILIAN 04_30_25
		elacCategoriesList.get(13).click(); //InSufficient Runway length error
		//elacCategoriesList.get(14).click(); //InSufficient Runway length error  //LILIAN 04_30_25
		//elacCategoriesList.get(15).click();
		Thread.sleep(5000);
		//check Next Four category of XO NonMember
		/*elacCategoriesList.get(16).click();
		Thread.sleep(5000);
		calculatebutton.click();
		Thread.sleep(5000);
		for(int i=0;i<prices.size();i++){
			text1 = prices.get(i).getText();
			cleanString1 = text1.replaceAll("\\$", "").replaceAll(",","");
			value1 = Double.parseDouble(cleanString1);
			System.out.println("Prices are: "+value1);
			ExtentCucumberAdapter.addTestStepLog("Prices are: "+value1);
			Assert.assertTrue(value1>6000.00,"Value is less than 6000");
		}*/
		Thread.sleep(5000);
	}

	public void verifyTotalForDefaultFlightsForXoMemberNonMember() throws InterruptedException{

		int elacCatoriessize=elacCategoriesList.size();
		System.out.println("number of categories in Xo Member/ NonMember are: "+elacCatoriessize );
		String text1;
		String cleanString1;
		double value1;
		Thread.sleep(5000);
		for(int i=0;i<prices.size();i++){
			text1 = prices.get(i).getText();
			cleanString1 = text1.replaceAll("\\$", "").replaceAll(",","");
			value1 = Double.parseDouble(cleanString1);
			System.out.println("Prices are: "+value1);
			ExtentCucumberAdapter.addTestStepLog("Prices are: "+value1);
			Assert.assertTrue(value1>6000.00,"Value is less than 6000");
		}
		Thread.sleep(5000);
	}

	public void verifyRedEyeFeesForSelectedCategory(String category) throws InterruptedException{

		MongoDBReader.getMongoDBData();
		int elacCatoriessize=elacCategoriesList.size();
		System.out.println("number of categories in Xo Member/ NonMember are: "+elacCatoriessize );
		if(category.equalsIgnoreCase("VJ N-REG CL300/350")) {
			//uncheck all selected categories
			elacCategoriesList.get(0).click();
			elacCategoriesList.get(6).click();
			elacCategoriesList.get(7).click();
			//elacCategoriesList.get(8).click();
			Thread.sleep(5000);
		}
		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");*/
		//Thread.sleep(5000);
		//Select provided category
		
		driver.findElement(By.xpath(String.format("//label[contains(text(),'%s')]",category))).click();

		Thread.sleep(5000);
		calculatebutton.click();
		Thread.sleep(10000);
		simpleViewToggle.click();
		double redEyeFeesFromUI=Double.parseDouble(redEyeFees.getText().replaceAll("\\$","").replaceAll(",",""));
		int value = (int)redEyeFeesFromUI;
		System.out.println("RedEyeFees fom UI is: "+redEyeFees.getText().replaceAll("\\$",""));
		System.out.println("RedEyeFees fom UI integer is: "+value);
		System.out.println("RedEyeFees fom MongoDB is: "+MongoDBReader.VJNRegLightFees);
		driver.findElement(By.xpath(String.format("//label[contains(text(),'%s')]",category))).click();
		//Verify RedEyeFees
		if(category.equalsIgnoreCase("VJ N-REG GIV-SP/450")){
			Assert.assertTrue(redEyeFeesFromUI== VJNREGGIVSP450Fees);}
		//else if(category.equalsIgnoreCase("VJ N-REG CITATION X")){   //**LILIAN 04_30_25**
			//Assert.assertTrue(redEyeFeesFromUI==VJNRegCitationXFees);} //**LILIAN  04_30_25**
		else if(category.equalsIgnoreCase("VJ N-REG CL300/350")){
			Assert.assertTrue(redEyeFeesFromUI== VJNREGCL300350Fees);}
		Thread.sleep(5000);
	}

	public void verifyRedEyeFeesForSelectedCategoryOnNightTime(String category) throws InterruptedException{

		MongoDBReader.getMongoDBData();
		int elacCatoriessize=elacCategoriesList.size();
		System.out.println("number of categories in Xo Member/ NonMember are: "+elacCatoriessize );
		if(category.equalsIgnoreCase("VJ N-REG CL300/350")) {
			//uncheck all selected categories
			elacCategoriesList.get(0).click();
			elacCategoriesList.get(6).click();
			elacCategoriesList.get(7).click();
			//elacCategoriesList.get(8).click();
			Thread.sleep(5000);
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		//Select provided category
		driver.findElement(By.xpath(String.format("//label[contains(text(),'%s')]",category))).click();
		Thread.sleep(5000);
		calculatebutton.click();
		Thread.sleep(5000);
		simpleViewToggle.click();
		double redEyeFeesFromUI=Double.parseDouble(redEyeFees.getText().replaceAll("\\$","").replaceAll(",",""));
		int value = (int)redEyeFeesFromUI;
		System.out.println("RedEyeFees fom UI is: "+redEyeFees.getText().replaceAll("\\$",""));
		System.out.println("RedEyeFees fom UI integer is: "+value);
		driver.findElement(By.xpath(String.format("//label[contains(text(),'%s')]",category))).click();
		//Verify RedEyeFees
		if(category.equalsIgnoreCase("VJ N-REG GIV-SP/450")){
			System.out.println("RedEyeFees fom MongoDB is: "+VJNREGGIVSP450OverNightFees);
			Assert.assertTrue(redEyeFeesFromUI== VJNREGGIVSP450OverNightFees);}
		//else if(category.equalsIgnoreCase("VJ N-REG CITATION X")){ //**LILIAN 04_30_25**
			//System.out.println("RedEyeFees fom MongoDB is: "+VJNCitationOvernightFees); //**LILIAN 04_30_25**
			//Assert.assertTrue(redEyeFeesFromUI==VJNCitationOvernightFees);} //**LILIAN  04_30_25**
		else if(category.equalsIgnoreCase("VJ N-REG CL300/350")){
			System.out.println("RedEyeFees fom MongoDB is: "+VJNREGCL300350OverNightFees);
			Assert.assertTrue(redEyeFeesFromUI== VJNREGCL300350OverNightFees);}
		Thread.sleep(5000);
	}

	public void unCheckCategories() throws InterruptedException {
			//uncheck all selected categories
			elacCategoriesList.get(0).click();
			elacCategoriesList.get(6).click();
			elacCategoriesList.get(7).click();
			//elacCategoriesList.get(8).click();
			Thread.sleep(5000);
	}
	public void verifyRedEyeFeesForSelectedCategoryOnExtendedNightTime(String category) throws InterruptedException{

		MongoDBReader.getMongoDBData();
		int elacCatoriessize=elacCategoriesList.size();
		System.out.println("number of categories in Xo Member/ NonMember are: "+elacCatoriessize );

			/*//uncheck all selected categories
			elacCategoriesList.get(0).click();
			elacCategoriesList.get(6).click();
			elacCategoriesList.get(7).click();
			//elacCategoriesList.get(8).click();
			Thread.sleep(5000);*/

		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");*/
		//Select provided category
		driver.findElement(By.xpath(String.format("//label[contains(text(),'%s')]",category))).click();

		Thread.sleep(5000);
		calculatebutton.click();
		Thread.sleep(5000);
		simpleViewToggle.click();
		double redEyeFeesFromUI=Double.parseDouble(redEyeFees.getText().replaceAll("\\$","").replaceAll(",",""));
		int value = (int)redEyeFeesFromUI;
		System.out.println("RedEyeFees fom UI is: "+redEyeFees.getText().replaceAll("\\$",""));
		System.out.println("RedEyeFees fom UI integer is: "+value);
		driver.findElement(By.xpath(String.format("//label[contains(text(),'%s')]",category))).click();
		//Verify RedEyeFees
		if(category.equalsIgnoreCase("AH CITATION XLS")){
			System.out.println("RedEyeFees fom MongoDB is: "+AHCitationXLSFees);
			Assert.assertEquals(AHCitationXLSFees, redEyeFeesFromUI);}
		else if(category.equalsIgnoreCase("AH PHENOM 300")){
			System.out.println("RedEyeFees fom MongoDB is: "+AHPhenom300Fees);
			Assert.assertEquals(AHPhenom300Fees, redEyeFeesFromUI);}
		else if(category.equalsIgnoreCase("VJ CL350")){
			System.out.println("RedEyeFees fom MongoDB is: "+VJCL350Fees);
			Assert.assertEquals(VJCL350Fees, redEyeFeesFromUI);}
		else if(category.equalsIgnoreCase("VJ CL604")){
			System.out.println("RedEyeFees fom MongoDB is: "+VJCL604fees);
			Assert.assertEquals(VJCL604fees, redEyeFeesFromUI);}
		else if(category.equalsIgnoreCase("VJ CL605")){
			System.out.println("RedEyeFees fom MongoDB is: "+CL605Fees);
			Assert.assertEquals(CL605Fees, redEyeFeesFromUI);}
		else if(category.equalsIgnoreCase("VJ CL850")){
			System.out.println("RedEyeFees fom MongoDB is: "+VJCL850fees);
			Assert.assertEquals(VJCL850fees, redEyeFeesFromUI);}
		else if(category.equalsIgnoreCase("VJ GLOBAL 6000")){
			System.out.println("RedEyeFees fom MongoDB is: "+VJGLOBAL6000Fees);
			Assert.assertEquals(VJGLOBAL6000Fees, redEyeFeesFromUI);}
		else if(category.equalsIgnoreCase("AH FALCON 7X")){
			System.out.println("RedEyeFees fom MongoDB is: "+AHFalcon7XFees);
			Assert.assertEquals(AHFalcon7XFees, redEyeFeesFromUI);}
		else if(category.equalsIgnoreCase("VJ GLOBAL 7500")){
			System.out.println("RedEyeFees fom MongoDB is: "+VJGLobal7500Fees);
			Assert.assertEquals(VJGLobal7500Fees, redEyeFeesFromUI);}
		else if(category.equalsIgnoreCase("AH LEGACY 500")){
			System.out.println("RedEyeFees fom MongoDB is: "+AHLegacy500Fees);
			Assert.assertEquals(AHLegacy500Fees, redEyeFeesFromUI);}
		else if(category.equalsIgnoreCase("AH PRAETOR 600")){
			System.out.println("RedEyeFees fom MongoDB is: "+AHPraetor600Fees);
			Assert.assertEquals(AHPraetor600Fees, redEyeFeesFromUI);}
		//LILIAN 05-28-25 begin
		//else if(category.equalsIgnoreCase("N-REG LIGHT JETS")){
		else if(category.equalsIgnoreCase("N-REG CIT EXCEL")){	
		//LILIAN 05-28-25 end
			System.out.println("RedEyeFees fom MongoDB is: "+VJNRegLightFees);
			Assert.assertEquals(VJNRegLightFees, redEyeFeesFromUI);}
		else if(category.equalsIgnoreCase("AH LEGACY 600")){
			System.out.println("RedEyeFees fom MongoDB is: "+AHLegacy600Fees);
			Assert.assertEquals(AHLegacy600Fees, redEyeFeesFromUI);}
		else if(category.equalsIgnoreCase("AH LEGACY 650")){
			System.out.println("RedEyeFees fom MongoDB is: "+AHLegacy650Fees);
			Assert.assertEquals(AHLegacy650Fees, redEyeFeesFromUI);}
		Thread.sleep(5000);
	}

	public void uncheckAllCategoriesAndSelectMentionedCategory(String category) throws InterruptedException{
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("document.body.style.transform='scale(0.7)'");
		Thread.sleep(5000);
		int elacCatoriessize=elacCategoriesList.size();
		System.out.println("number of categories in Xo Member/ NonMember are: "+elacCatoriessize );

			//uncheck all selected categories
			elacCategoriesList.get(0).click();
			elacCategoriesList.get(6).click();
			elacCategoriesList.get(7).click();
			//elacCategoriesList.get(8).click();
			Thread.sleep(5000);


		driver.findElement(By.xpath(String.format("//label[contains(text(),'%s')]",category))).click();
		
		Thread.sleep(5000);//LILIAN 05-28-25

		/*   LILIAN 05_09_25 
		Thread.sleep(5000);
		calculatebutton.click();
		Thread.sleep(5000);
		simpleViewToggle.click();
		Thread.sleep(5000);
		*/
	}

	public void verifyDiningFeesIsDisplayedAsPerLeg(double fees) throws InterruptedException{
		double diningFeesForUI;
		int noOfDiningFeesLegs=diningFees.size();
		System.out.println("Number of Dining Fees as per legs are: "+noOfDiningFeesLegs );

		for(int i=0;i<diningFees.size();i++) {
			diningFeesForUI = Double.parseDouble(diningFees.get(i).getText().replaceAll("\\$", "").replaceAll(",", ""));
			System.out.println("Dining Fees fom UI double is: " + diningFeesForUI);
			Assert.assertEquals(fees,diningFeesForUI,"Fees is not equal");
		}
		Thread.sleep(5000);
	}

	public void verifyFET_CO2_FuelSurchargeFees() throws InterruptedException{
		double co2Fees1;
		double fetFees1;
		double FuelSurchargeFees1;
		double subtotalFees1;
		double liveTime1;

		System.out.println("CO2 Fees is: "+CO2Fees.getText());
		System.out.println("Fuel Surcharge Fees is: "+fuelSurcharge.getText());
		System.out.println("FET Fees is: "+FETFees.getText());
		System.out.println("Subtotal fees is: "+SubtotalFees.getText());


		double actualCO2Fees= Double.parseDouble(CO2Fees.getText().replaceAll("\\$",""));
		double actualfetFees= Double.parseDouble(FETFees.getText().replaceAll("\\$","").replaceAll(",",""));
		double actualfuelSurchargeFees= Double.parseDouble(fuelSurcharge.getText().replaceAll("\\$","").replaceAll(",",""));
		double actualliveTime= Double.parseDouble(liveTime.getText().replaceAll("h",""));
		double actualSubtotalFees= Double.parseDouble(SubtotalFees.getText().replaceAll("\\$","").replaceAll(",",""));

		// Calculate the allowable difference, which is 5% of num1
		double CO2tolerance = actualCO2Fees * 0.05;
		double FETtolerance = actualfetFees * 0.05;
		double FuelSurchargetolerance = actualfuelSurchargeFees * 0.05;
		System.out.println("95% of original CO2 value is: "+CO2tolerance);
		System.out.println("95% of original FET value is: "+FETtolerance);
		System.out.println("95% of original Fuel surcharge value is: "+FuelSurchargetolerance);

		// Check if the absolute difference between num1 and num2 is less than or equal to the tolerance
		boolean isCO2FeesEqual= Math.abs(actualCO2Fees - (actualliveTime*49)) <= CO2tolerance;
		//boolean isCO2FeesEqual= actualCO2Fees==actualliveTime*49;
		boolean isFETFeesEqual= Math.abs(actualfetFees - (actualSubtotalFees*(7.5/100))) <= FETtolerance;
		//boolean isFETFeesEqual= actualfetFees==actualSubtotalFees*(7.5/100);
		boolean isFuelSurchargeFeesEqual= Math.abs(actualfuelSurchargeFees - (actualliveTime*1000)) <= FuelSurchargetolerance;
		//boolean isFuelSurchargeFeesEqual= actualfuelSurchargeFees==actualliveTime*1000; //for small category
		Assert.assertTrue(isCO2FeesEqual,"CO2 Fees is not equal");
		Assert.assertTrue(isFETFeesEqual,"FET Fees is not equal");
		Assert.assertTrue(isFuelSurchargeFeesEqual,"Fuel Surcharge Fees is not equal");
		Thread.sleep(5000);
	}

	public void verifyFET_CO2_Fees() throws InterruptedException{
		double co2Fees1;
		double fetFees1;
		double subtotalFees1;
		double liveTime1;

		System.out.println("CO2 Fees is: "+CO2Fees.getText());
		System.out.println("FET Fees is: "+FETFees.getText());
		System.out.println("Subtotal fees is: "+SubtotalFees.getText());


		double actualCO2Fees= Double.parseDouble(CO2Fees.getText().replaceAll("\\$",""));
		double actualfetFees= Double.parseDouble(FETFees.getText().replaceAll("\\$","").replaceAll(",",""));
		double actualliveTime= Double.parseDouble(liveTime.getText().replaceAll("h",""));
		double actualSubtotalFees= Double.parseDouble(SubtotalFees.getText().replaceAll("\\$","").replaceAll(",",""));

		// Calculate the allowable difference, which is 5% of num1
		double CO2tolerance = actualCO2Fees * 0.05;
		double FETtolerance = actualfetFees * 0.05;
		System.out.println("95% of original CO2 value is: "+CO2tolerance);
		System.out.println("95% of original FET value is: "+FETtolerance);

		// Check if the absolute difference between num1 and num2 is less than or equal to the tolerance
		boolean isCO2FeesEqual= Math.abs(actualCO2Fees - (actualliveTime*49)) <= CO2tolerance;
		//boolean isCO2FeesEqual= actualCO2Fees==actualliveTime*49;
		boolean isFETFeesEqual= Math.abs(actualfetFees - (actualSubtotalFees*(7.5/100))) <= FETtolerance;
		//boolean isFETFeesEqual= actualfetFees==actualSubtotalFees*(7.5/100);
		//boolean isFuelSurchargeFeesEqual= actualfuelSurchargeFees==actualliveTime*1000; //for small category
		Assert.assertTrue(isCO2FeesEqual,"CO2 Fees is not equal");
		Assert.assertTrue(isFETFeesEqual,"FET Fees is not equal");
		Thread.sleep(5000);
	}

	public void verifyCO2_FuelSurchargeFees(String category) throws InterruptedException{
		double co2Fees1;

		double FuelSurchargeFees1;
		double subtotalFees1;
		double liveTime1;

		System.out.println("CO2 Fees is: "+CO2Fees.getText());
		System.out.println("Fuel Surcharge Fees is: "+fuelSurcharge.getText());
		System.out.println("Subtotal fees is: "+SubtotalFees.getText());
		
		double actualCO2Fees= Double.parseDouble(CO2Fees.getText().replaceAll("\\$",""));

		double actualfuelSurchargeFees= Double.parseDouble(fuelSurcharge.getText().replaceAll("\\$","").replaceAll(",",""));
		double actualliveTime= Double.parseDouble(liveTime.getText().replaceAll("h",""));
		double actualSubtotalFees= Double.parseDouble(SubtotalFees.getText().replaceAll("\\$","").replaceAll(",",""));

		System.out.println(actualfuelSurchargeFees); //LILIAN 05-29-25
		System.out.println(actualliveTime); //LILIAN 05-29-25
		System.out.println(actualSubtotalFees); //LILIAN 05-29-25
		
		// Calculate the allowable difference, which is 5% of num1
		double CO2tolerance = actualCO2Fees * 0.05;

		double FuelSurchargetolerance = actualfuelSurchargeFees * 0.05;
		System.out.println("95% of original CO2 value is: "+CO2tolerance);

		System.out.println("95% of original Fuel surcharge value is: "+FuelSurchargetolerance);
		double fuelSurchargeFees = 1000;
		if(category.equalsIgnoreCase("AH PHENOM 300")){
			fuelSurchargeFees=500;
		}
		else if (category.equalsIgnoreCase("VJ GLOBAL 6000")|| (category.equalsIgnoreCase("VJ GLOBAL 7500"))){
			fuelSurchargeFees=2000;
		}
		// Check if the absolute difference between num1 and num2 is less than or equal to the tolerance
		boolean isCO2FeesEqual= Math.abs(actualCO2Fees - (actualliveTime*49)) <= CO2tolerance;
		//boolean isCO2FeesEqual= actualCO2Fees==actualliveTime*49;
		
		System.out.println(isCO2FeesEqual); //LILIAN 05-29-25

		boolean isFuelSurchargeFeesEqual= Math.abs(actualfuelSurchargeFees - (actualliveTime*fuelSurchargeFees)) <= FuelSurchargetolerance;
		//boolean isFuelSurchargeFeesEqual= actualfuelSurchargeFees==actualliveTime*1000; //for small category
		
		System.out.println(isFuelSurchargeFeesEqual); //LILIAN 05-29-25

		Assert.assertTrue(isCO2FeesEqual,"CO2 Fees is not equal");
		Assert.assertTrue(isFuelSurchargeFeesEqual,"Fuel Surcharge Fees is not equal");
		Thread.sleep(5000);
	}

	public void verifyDiIcingFeesIsDisplayedAsPerLeg(double fees) throws InterruptedException{
		double diIcingFeesForUI;
		int noOfDiningFeesLegs=diIcingFees.size();
		System.out.println("Number of Di Icing Fees as per legs are: "+noOfDiningFeesLegs );

		for(int i=0;i<diIcingFees.size();i++) {
			diIcingFeesForUI = Double.parseDouble(diIcingFees.get(i).getText().replaceAll("\\$", "").replaceAll(",", ""));
			System.out.println("Di Icing Fees  fom UI double is: " + diIcingFeesForUI);
			Assert.assertEquals(fees,diIcingFeesForUI,"Fees is not equal");
		}
		Thread.sleep(5000);
	}

	public void verifyCustomerServiceFeesIsDisplayedAsPerLeg(double fees) throws InterruptedException{
		double customerServiceFeesForUI;
		int noOfDiningFeesLegs=customerServiceFees.size();
		System.out.println("Number of Customer Service Fees as per legs are: "+noOfDiningFeesLegs );

		for(int i=0;i<customerServiceFees.size();i++) {
			customerServiceFeesForUI = Double.parseDouble(customerServiceFees.get(i).getText().replaceAll("\\$", "").replaceAll(",", ""));
			System.out.println("Customer Service Fees  fom UI double is: " + customerServiceFeesForUI);
			Assert.assertEquals(fees,customerServiceFeesForUI,"Fees is not equal");
		}
		Thread.sleep(5000);
	}

	//LILIAN - NEW
	public void selectMembershipType(String membershiptype) throws InterruptedException{
		membershipDropdownCalculator.click();

		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement optionToClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//span[contains(text(),'%s')]",membershiptype))));
		Thread.sleep(1000);
		optionToClick.click();
		Thread.sleep(500);
	}

	public void clickViewButton() throws InterruptedException{
		simpleViewToggle.click();
		Thread.sleep(2000);
	}


	/*public void verifyNonMemberFees(String fee) throws InterruptedException{
		int noOfNonMemberFeesLegs= nonMemberFee.size();
		System.out.println("Number categories: "+noOfNonMemberFeesLegs );


		for(int i=0;i<nonMemberFee.size();i++) {
			String totalFee = nonMemberFee.get(i).getText();
			String aux = totalFee.replaceAll("\\$", "").replaceAll(",","").replaceAll("\\.\\d+", "");
			System.out.println(aux);
			System.out.println(fee);
			Assert.assertEquals(fee,aux,"NON-MEMBER Fee is not equal");
		}
		Thread.sleep(5000);
	}*/

	/*public void verifyNonMemberFeeIsNotDisplayedForXoMembership() throws InterruptedException{

		int noOfNonMemberFeesLegs= nonMemberFee.size();
		System.out.println("Number categories: "+noOfNonMemberFeesLegs );
		Assert.assertEquals(noOfNonMemberFeesLegs,0,"Xo membership should not have Non Member fee");
		Thread.sleep(500);
	}*/




	public void selectInternationalCategoriesAndVerifiesFee(String feeType,Integer pax, String fee) throws InterruptedException{


		int elacCatoriessize=elacCategoriesList.size();
		System.out.println("number of categories are: "+elacCatoriessize );


		//uncheck all selected categories
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
		//elacCategoriesList.get(8).click();
		//elacCategoriesList.get(9).click();
		//elacCategoriesList.get(10).click();

		Thread.sleep(1000);
		//check Next Four category of XO NonMember
		elacCategoriesList.get(1).click();
		elacCategoriesList.get(2).click();
		elacCategoriesList.get(3).click();
		elacCategoriesList.get(4).click();
		Thread.sleep(1000);

		calculatebutton.click();

		Thread.sleep(1000);
		clickViewButton();

		Thread.sleep(1000);
		System.out.println("Fee Type: "+feeType);

		if (feeType.equalsIgnoreCase("nonMemberFee")){
			//verifyNonMemberFees(fee);
		}else if (feeType.equalsIgnoreCase("etsFee")){
			verifyEtsFees(fee);
		} else if (feeType.equalsIgnoreCase("segmentFee")) {
			verifySegmentFeesInternational(fee);
		}else if (feeType.equalsIgnoreCase("PortugalFee")) {
			verifyPortugalFeesInternational(fee);
		}else if (feeType.equalsIgnoreCase("Cabotage")) {
			verifyCabotage();
		}else if (feeType.equalsIgnoreCase("NonCabotage")) {
			verifyReturnsPrice();
		//***LILIAN***	
		}else if (feeType.equalsIgnoreCase("MinRunway")) {  
			verifyMinimumRunwayMessage();                  
		}else if (feeType.equalsIgnoreCase("MaldivesPassengerTax")) {  
			verifyMaldivesPassengerTax(pax, fee);  
		}else if (feeType.equalsIgnoreCase("VRMMairportDevelopmentTax")) {  
			verifyVRMMairportDevelopmentTax(pax, fee); 
		}else if (feeType.equalsIgnoreCase("VRDA Vip Lounge")) {  
			verifyVRDAVipLounge(pax, fee); 
		}else if (feeType.equalsIgnoreCase("VRMM Vip Lounge")) {  
			verifyVRMMVipLounge(pax, fee); 
		//LILIAN 06-26-25 begin
		}else if (feeType.equalsIgnoreCase("OERSsupport")) {
			verifyReturnsPrice(); 							
		}else if (feeType.equalsIgnoreCase("TurkeyCyprusBanned")) {
			verifyBannedMessage(); 	
		}else if (feeType.equalsIgnoreCase("CyprusToCyprus")) {
			verifyReturnsPrice();
		}else if (feeType.equalsIgnoreCase("TulumAirport")) {
			verifyReturnsPrice();
		//LILIAN 06-26-25 end
		//LILIAN 07-26-25 begin
		}else if (feeType.equalsIgnoreCase("Saudi_Arabia")) {
			verifyReturnsPrice();
		}else if (feeType.equalsIgnoreCase("Different_Subtotal_Total")) {
			verifySubtotalAndTotalValuesAreDifferent();	
		//LILIAN 07-26-25 end
		} 															//LILIAN 05_12_95

		       
		//***LILIAN***

	

		Thread.sleep(1000);
		//uncheck all selected categories
		elacCategoriesList.get(1).click();
		elacCategoriesList.get(2).click();
		elacCategoriesList.get(3).click();
		elacCategoriesList.get(4).click();
		Thread.sleep(1000);
		//check Next Four category of XO NonMember
		elacCategoriesList.get(5).click();
		//elacCategoriesList.get(6).click();
		//elacCategoriesList.get(7).click();
		elacCategoriesList.get(8).click(); //***LILIAN***
		elacCategoriesList.get(9).click();
		elacCategoriesList.get(10).click();
		//elacCategoriesList.get(11).click();//***LILIAN***
		Thread.sleep(1000);
		calculatebutton.click();
		Thread.sleep(1000);
		clickViewButton();
		Thread.sleep(1000);

		if (feeType.equalsIgnoreCase("nonMemberFee")){
			//verifyNonMemberFees(fee);
		}else if (feeType.equalsIgnoreCase("etsFee")){
			verifyEtsFees(fee);
		} else if (feeType.equalsIgnoreCase("segmentFee")) {
			verifySegmentFeesInternational(fee);
		}else if (feeType.equalsIgnoreCase("PortugalFee")) {
			verifyPortugalFeesInternational(fee);
		}else if (feeType.equalsIgnoreCase("Cabotage")) {
			verifyCabotage();
		}else if (feeType.equalsIgnoreCase("NonCabotage")) {
			verifyReturnsPrice();
	//***LILIAN***	
		}else if (feeType.equalsIgnoreCase("MinRunway")) {  
			verifyMinimumRunwayMessage();                  
		}else if (feeType.equalsIgnoreCase("MaldivesPassengerTax")) {  
			verifyMaldivesPassengerTax(pax, fee);  
		}else if (feeType.equalsIgnoreCase("VRMMairportDevelopmentTax")) {  
			verifyVRMMairportDevelopmentTax(pax, fee); 
		}else if (feeType.equalsIgnoreCase("VRDA Vip Lounge")) {  
			verifyVRDAVipLounge(pax, fee); 
		}else if (feeType.equalsIgnoreCase("VRMM Vip Lounge")) {  
			verifyVRMMVipLounge(pax, fee); 
	//***LILIAN***
	//LILIAN 06-26-25 begin
		}else if (feeType.equalsIgnoreCase("OERSsupport")) {
			verifyReturnsPrice(); 							
		}else if (feeType.equalsIgnoreCase("TurkeyCyprusBanned")) {
			verifyBannedMessage(); 	
		}else if (feeType.equalsIgnoreCase("CyprusToCyprus")) {
			verifyReturnsPrice();
		}else if (feeType.equalsIgnoreCase("TulumAirport")) {
			verifyReturnsPrice();
	//LILIAN 06-26-25 end
		//LILIAN 07-26-25 begin
		}else if (feeType.equalsIgnoreCase("Saudi_Arabia")) {
			verifyReturnsPrice();
		}else if (feeType.equalsIgnoreCase("Different_Subtotal_Total")) {
			verifySubtotalAndTotalValuesAreDifferent();
		//LILIAN 07-26-25 end
		} 															//LILIAN 05_12_95

		Thread.sleep(1000);

		//uncheck all selected categories
		elacCategoriesList.get(5).click();
		//elacCategoriesList.get(6).click();
		//elacCategoriesList.get(7).click();
		elacCategoriesList.get(8).click(); //***LILIAN***
		elacCategoriesList.get(9).click();
		elacCategoriesList.get(10).click();
		//elacCategoriesList.get(11).click();//***LILIAN***
		Thread.sleep(1000);
		//check Next Four category of XO NonMember
		elacCategoriesList.get(11).click(); //*LILIAN***
		elacCategoriesList.get(12).click();
		elacCategoriesList.get(13).click();
		//elacCategoriesList.get(14).click();//*LILIAN***
		Thread.sleep(1000);
		calculatebutton.click();
		Thread.sleep(1000);
		clickViewButton();
		Thread.sleep(1000);
		if (feeType.equalsIgnoreCase("nonMemberFee")){
			//verifyNonMemberFees(fee);
		}else if (feeType.equalsIgnoreCase("etsFee")){
			verifyEtsFees(fee);
		} else if (feeType.equalsIgnoreCase("segmentFee")) {
			verifySegmentFeesInternational(fee);
		}else if (feeType.equalsIgnoreCase("PortugalFee")) {
			verifyPortugalFeesInternational(fee);
		}else if (feeType.equalsIgnoreCase("Cabotage")) {
			verifyCabotage();
		}else if (feeType.equalsIgnoreCase("NonCabotage")) {
			verifyReturnsPrice();
//***LILIAN***	
		}else if (feeType.equalsIgnoreCase("MinRunway")) {  
			verifyMinimumRunwayMessage();                  
		}else if (feeType.equalsIgnoreCase("MaldivesPassengerTax")) {  
			verifyMaldivesPassengerTax(pax, fee);  
		}else if (feeType.equalsIgnoreCase("VRMMairportDevelopmentTax")) {  
			verifyVRMMairportDevelopmentTax(pax, fee); 
		}else if (feeType.equalsIgnoreCase("VRDA Vip Lounge")) {  
			verifyVRDAVipLounge(pax, fee); 
		}else if (feeType.equalsIgnoreCase("VRMM Vip Lounge")) {  
			verifyVRMMVipLounge(pax, fee); 
//***LILIAN***
	//LILIAN 06-26-25 begin
		}else if (feeType.equalsIgnoreCase("OERSsupport")) {
			verifyReturnsPrice(); 							
		}else if (feeType.equalsIgnoreCase("TurkeyCyprusBanned")) {
			verifyBannedMessage(); 	
		}else if (feeType.equalsIgnoreCase("CyprusToCyprus")) {
			verifyReturnsPrice();
		}else if (feeType.equalsIgnoreCase("TulumAirport")) {
			verifyReturnsPrice();
	//LILIAN 06-26-25 end
		//LILIAN 07-26-25 begin
		}else if (feeType.equalsIgnoreCase("Saudi_Arabia")) {
			verifyReturnsPrice();
		}else if (feeType.equalsIgnoreCase("Different_Subtotal_Total")) {
			verifySubtotalAndTotalValuesAreDifferent();
		//LILIAN 07-26-25 end
		} 		//LILIAN 05_12_95


		Thread.sleep(1000);


		//uncheck all selected categories
		elacCategoriesList.get(11).click();//***LILIAN***
		elacCategoriesList.get(12).click();
		elacCategoriesList.get(13).click();
		//elacCategoriesList.get(14).click();//***LILIAN***

		Thread.sleep(1000);
		
	}



	//XO MEMBERSHIP
	public void selectOnlyXoLiteCategory() throws InterruptedException{
		//uncheck VJ N-REG CL300/350 and VJ N-REG GIV-SP/450  *LILIAN 04_30_25
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
		//elacCategoriesList.get(8).click();  //*LILIAN 04_30_25
		Thread.sleep(1200);
	}

	/**LILIAN 04_30_25
	public void selectOnlyXoSmidCategory() throws InterruptedException{
		//uncheck XO-LITE, VJ N-REG CL300/350 and VJ N-REG GIV-SP/450
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(7).click();
		elacCategoriesList.get(8).click();
		Thread.sleep(1200);
	}
	LILIAN*/

	public void selectOnlyNregSmidCategory() throws InterruptedException{
		//uncheck XO-LITE, VJ N-REG CITATION X and VJ N-REG GIV-SP/450
		elacCategoriesList.get(0).click();
		//elacCategoriesList.get(6).click(); //LILIAN 05_07_25
		elacCategoriesList.get(7).click(); //LILIAN 05_07_25
		//elacCategoriesList.get(8).click(); //LILIAN 05_07_25
		Thread.sleep(1200);
	}
	public void selectOnlyNregUrlCategory() throws InterruptedException{
		//uncheck XO-LITE, VJ N-REG CITATION X and VJ N-REG CL300/350
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		//elacCategoriesList.get(7).click();//LILIAN 05_07_25
		Thread.sleep(1200);
	}

	public void selectOnlyXoLiteAndXoSmidCategories() throws InterruptedException{
		//uncheck VJ N-REG CL300/350 and VJ N-REG GIV-SP/450
		elacCategoriesList.get(6).click();//**LILIAN 04_30_25
		elacCategoriesList.get(7).click();
		//elacCategoriesList.get(8).click();//**LILIAN 04_30_25
		Thread.sleep(1200);
	}

	/* LILIAN 05_07_25
	public void selectOnlyXoSmidAndNregsCategory() throws InterruptedException{
		//uncheck  XO - LITE
		elacCategoriesList.get(0).click();
		Thread.sleep(1200);
	}*/

	public void selectOnlyNregsCategory() throws InterruptedException{
		//uncheck XO-SMID, XO - LITE
		elacCategoriesList.get(0).click();
		//elacCategoriesList.get(6).click();//**LILIAN 04_30_25
		Thread.sleep(1200);
	}

	public void selectOnlyAHPhenom300Category() throws InterruptedException{
		//check only ah phenom300
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
		//elacCategoriesList.get(8).click(); //LILIAN 05_07_25
		elacCategoriesList.get(1).click();
		Thread.sleep(1200);
	}

	public void selectOnlyAHLegacy600Category() throws InterruptedException{
		//check only ah legacy600
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
		//elacCategoriesList.get(8).click(); //LILIAN 05_07_25
		elacCategoriesList.get(3).click();
		Thread.sleep(1200);
	}

	//LILIAN 06-26-25 begin
	public void selectOnlyAHLegacy650Category() throws InterruptedException{
	
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
		//check only ah legacy650
		elacCategoriesList.get(4).click();
		Thread.sleep(1200);
	}
	//LILIAN 06-26-25 end
	
	//***LILIAN***
	public void selectOnlyCL350_CL605_GL6K_Category() throws InterruptedException{
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
	
		elacCategoriesList.get(8).click();
		elacCategoriesList.get(10).click();
		elacCategoriesList.get(12).click();
		Thread.sleep(1200);
	}
	//***LILIAN***
	//LILIAN 07-26-25 begin
	public void selectOnlyGL6000_GL7500_Categories() throws InterruptedException{
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
	
		elacCategoriesList.get(12).click();
		elacCategoriesList.get(13).click();
		Thread.sleep(1200);
	}
	
	public void selectOnlyAsia_Categories() throws InterruptedException{
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
	
		elacCategoriesList.get(8).click();  //CL350
		elacCategoriesList.get(10).click();  //CL605
		elacCategoriesList.get(12).click();   //GLOBAL 6000
		Thread.sleep(1200);
	}
	
	public void selectOnlyCL605_CL850_GL6000_GL7500_Categories() throws InterruptedException{
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
		
		elacCategoriesList.get(10).click();
		elacCategoriesList.get(11).click();
		elacCategoriesList.get(12).click();
		elacCategoriesList.get(13).click();
		Thread.sleep(1200);
	}
	//LILIAN 07-26-25 end


	//SELECT MEMBERSHIP
	/* LILIAN 05_07_25
	public void selectOnlyXoLiteCategoryforSelectSignatureMembership() throws InterruptedException{
		//uncheck  CITATION X
		//elacCategoriesList.get(1).click(); //LILIAN 05_07_25
		elacCategoriesList.get(0).click();//LILIAN 05_07_25
		Thread.sleep(1200);
	}
	 LILIAN */

	/* LILIAN 04_30_25
	public void selectOnlyCitationXCategoryforSelectSignatureMembership() throws InterruptedException{
		//uncheck  LIGHT JET
		elacCategoriesList.get(0).click();
		Thread.sleep(1200);
	}
	*/


	public void verifyEtsFees(String fee) throws InterruptedException{

		int noOfEtsFeesLegs= etsFee.size();
		System.out.println(noOfEtsFeesLegs);


		for(int i=0;i<etsFee.size();i++) {
			String totalFee = etsFee.get(i).getText();
			String aux = totalFee.replaceAll("\\$", "").replaceAll(",","").replaceAll("\\.\\d+", "");
			System.out.println(aux);
			System.out.println(fee);
			Assert.assertEquals(fee,aux,"ETS Fee is not equal");
		}
		Thread.sleep(5000);
	}


	public void verifyEtsFeesRoundTrip(String fee, int trip) throws InterruptedException{

		int noOfEtsFeesLegs= etsFee.size();
		System.out.println(noOfEtsFeesLegs);

		System.out.println(trip);
		if(trip==noOfEtsFeesLegs){
			for(int i=0;i<etsFee.size();i++) {
				String totalFee = etsFee.get(i).getText();
				String aux = totalFee.replaceAll("\\$", "").replaceAll(",","").replaceAll("\\.\\d+", "");
				System.out.println(aux);
				System.out.println(fee);
				Assert.assertEquals(fee,aux,"ETS Fee is not equal");
			}
		}else {
			Assert.assertNotEquals(noOfEtsFeesLegs,trip,"ETS fee must be per Trip, not per Leg");
		}
		Thread.sleep(5000);
	}

	public void verifyEtsforXojet() throws InterruptedException{
		int noOfEtsFeesLegs= etsFee.size();
		System.out.println(noOfEtsFeesLegs);
		Assert.assertEquals(noOfEtsFeesLegs,0,"XOJET should not have ETS fee");
		Thread.sleep(500);
	}

	public void verifySegmentFeesDomestic(String fee) throws InterruptedException{
		int noOfSegmentFeesLegs= segmentFee.size();
		System.out.println(noOfSegmentFeesLegs);

		for(int i=0;i<segmentFee.size();i++) {
			String totalFee = segmentFee.get(i).getText();
			String aux = totalFee.replaceAll("\\$", "").replaceAll(",","");
			System.out.println(aux);
			System.out.println(fee);

			if(aux.equals("0.00")){
				continue;
			}
			else {
				Assert.assertEquals(fee,aux,"Segment Fee is not equal");
			}
		}
		Thread.sleep(500);
	}


	public void verifySegmentFeesInternational(String fee) throws InterruptedException{
		int noOfSegmentFeesLegs= segmentFee.size();
		System.out.println(noOfSegmentFeesLegs);

		for(int i=0;i<segmentFee.size();i++) {
			String totalFee = segmentFee.get(i).getText();
			String aux = totalFee.replaceAll("\\$", "").replaceAll(",","");
			System.out.println(aux);
			System.out.println(fee);


			Assert.assertEquals(fee,aux,"Segment Fee is not equal");

		}
		Thread.sleep(500);
	}

	public void verifySegmentFeesDomesticMaxPax(double fee) throws InterruptedException{

		int noOfSegmentFeesLegs= segmentFee.size();
		System.out.println(noOfSegmentFeesLegs);

		System.out.println(fee);

		for(int i=0;i<segmentFee.size();i++) {
			String totalFee = segmentFee.get(i).getText();
			String segfeeaux = totalFee.replaceAll("\\$", "").replaceAll(",","");
			System.out.println(segfeeaux);

			double doubleValue = Double.parseDouble(segfeeaux);
			System.out.println(doubleValue);

			String blankpax = paxCategories.get(i).getText();
			System.out.println(blankpax);

			if(blankpax.equals("")){ //If DNQ or Out of Service Area
				continue;
			}else {

				int intValue = Integer.parseInt(blankpax);
				System.out.println(intValue);

				double result = fee * intValue;
				System.out.println("Multiplication result: " + result);

				// result-Format the double value to two decimal places
				DecimalFormat df = new DecimalFormat("#.00");
				String formattedresult = df.format(doubleValue);
				System.out.println(formattedresult);

				Assert.assertEquals(segfeeaux, formattedresult,"Segment Fee is not equal");
			}
		}
		Thread.sleep(500);
	}

	public void verifyPortugalFeesDomestic(String fee) throws InterruptedException{
		int noOfSegmentFeesLegs= portugalFee.size();
		System.out.println(noOfSegmentFeesLegs);

		for(int i=0;i<portugalFee.size();i++) {
			String depaux = origin.getText();
			System.out.println(depaux);


			if(!depaux.equals("EGGW")){  //If departure is Portugal
				String totalFee = portugalFee.get(i).getText();
				String aux = totalFee.replaceAll("\\$", "").replaceAll(",","");
				System.out.println(aux);
				System.out.println(fee);

				Assert.assertEquals(fee,aux,"Portugal Fee is not equal");
			}else {
				Assert.assertEquals(fee,"0.00","Portugal Fee is not equal");
			}

		}
		Thread.sleep(500);
	}

	public void verifyPortugalFeesInternational(String fee) throws InterruptedException{
		int noOfSegmentFeesLegs= portugalFee.size();
		System.out.println("Number categories: "+noOfSegmentFeesLegs );

		for(int i=0;i<portugalFee.size();i++) {
			String depaux = origin.getText();
			System.out.println(depaux);


			if(!depaux.equals("EGGW")){  //If departure is Portugal

				String totalFee = portugalFee.get(i).getText();
				String aux = totalFee.replaceAll("\\$", "").replaceAll(",","");
				System.out.println(aux);
				System.out.println(fee);

				Assert.assertEquals(fee,aux,"Portugal Fee is not equal");

			} else {
				Assert.assertEquals(fee,"0.00","Portugal Fee is not equal");
			}

		}

		Thread.sleep(500);
	}

	public  void userSelectsAircraftCategories(String category) throws InterruptedException{
		int elacCatoriessize=elacCategoriesList.size();
		System.out.println("number of categories are: "+elacCatoriessize );

		if (category.equals("NREGULR")) {

			elacCategoriesList.get(0).click();
			elacCategoriesList.get(6).click();
			//elacCategoriesList.get(7).click();  //LILIAN 05_08_25

		} else {
			if (category.equals("CL850")) {

				elacCategoriesList.get(0).click();
				elacCategoriesList.get(6).click();
				elacCategoriesList.get(7).click();
				//elacCategoriesList.get(8).click(); //LILIAN 05_08_25
				//select CL850
				//elacCategoriesList.get(12).click(); //LILIAN 05_08_25
				elacCategoriesList.get(11).click();
			}
		}

	}

	public void verifyWhitelistAirportsDomestic() throws InterruptedException{

		int noOfLegs= subtotalvalue.size();
		System.out.println(noOfLegs);

		for(int i=0;i<subtotalvalue.size();i++) {
			Assert.assertNotEquals(noOfLegs,"0.00","Airport is not whitelisted, but should be");
		}
	}



	public void verifyOvernightFeeInternational(String fee)  throws InterruptedException{
		int noOfLegs= overnight.size();
		System.out.println(noOfLegs);

		/* LILIAN 05_09_25
		String blankpax = paxCategories.get(0).getText(); 
		System.out.println(blankpax);	
		if(!blankpax.equals("")){ //When DNQ or Out of Coverage 
		*/
		
			for(int i=0;i<overnight.size();i++) {

				String totalfee = overnight.get(i).getText();
				String feeaux = totalfee.replaceAll("\\$", "").replaceAll(",","");
				System.out.println(feeaux);

				Assert.assertEquals(feeaux,fee,"Wrong overnight fee");
			}
		//} //LILIAN 05_09_25
	}

	public void verifyCabotage() throws InterruptedException{

		int noOfLegs= cabotage.size();
		System.out.println(noOfLegs);

		int nototalvalues= totalvalue.size();
		if(nototalvalues == 0){
			Assert.fail("Test failed because it should be cabotage.");
		}
		else {
			for(int i=0;i<cabotage.size();i++) {
				String messageUI = cabotage.get(i).getText();
				System.out.println(messageUI);

				Assert.assertNotEquals("This Flight Violates Cabotage Rules, So Is Not allowed.",messageUI,"It should be cabotage, but it is incorrectly returning price!");
			}
		}

	}


	public void verifyReturnsPrice() throws InterruptedException{
		int noOfLegs= subtotalvalue.size();
		System.out.println(noOfLegs);


		for(int i=0;i<subtotalvalue.size();i++) {

			String value1 = subtotalvalue.get(i).getText();  //LILIAN 05_20_25
			System.out.println(value1);
			
			// Remove dollar sign from the string
			String cleanString1 = value1.replaceAll("\\$", "").replaceAll(",","");
			double value2 = Double.parseDouble(cleanString1);
			System.out.println(value2);
			Assert.assertTrue(value2>0.00,"Value should not be zero");
		}

	}

	public void selectFirmToggle() throws InterruptedException{
		firmFeeToggle.click();
		Thread.sleep(500);
	}

	public void verifyFirmFeesDomestic(String fee) throws InterruptedException{
		int noOfFirmFeesLegs= firmFee.size();
		System.out.println(noOfFirmFeesLegs);

		
		/* LILIAN 05_09_25
		String blankpax = paxCategories.get(0).getText(); 
		System.out.println(blankpax);	
		if(!blankpax.equals("")){ //When DNQ or Out of Coverage 
		*/


			for(int i=0;i<firmFee.size();i++) {
				String totalFee = firmFee.get(i).getText();
				String aux = totalFee.replaceAll("\\$", "").replaceAll(",","");
				System.out.println(aux);
				System.out.println(fee);

				Assert.assertEquals(fee,aux,"Firm Fee is not equal");
			}

		//}  //LILIAN 05_09_25 
		Thread.sleep(500);
	}

	public void verifyServiceFeesDomestic(String fee, Integer trip) throws InterruptedException{
		int noOfServiceFeesLegs= ServiceFee.size();
		System.out.println(noOfServiceFeesLegs);
		System.out.println(trip);

			for(int i=0;i<ServiceFee.size();i++)
			{
			
					String totalFee = ServiceFee.get(i).getText();
					String aux = totalFee.replaceAll("\\$", "").replaceAll(",","");
					System.out.println(aux);
					System.out.println(fee);
					Assert.assertEquals(fee,aux,"Service Fee is not equal");
			}

			Thread.sleep(500);
	}
	
	//LILIAN 06-24-25  end
	//LILIAN 07-03-25 begin
	public void verifyXoServiceChargeDomestic(String fee, Integer trip) throws InterruptedException{
		int noOfServiceFeesLegs= XoServiceCharge.size();
		System.out.println(noOfServiceFeesLegs);
		System.out.println(trip);

			for(int i=0;i<XoServiceCharge.size();i++)
			{
					String totalFee = XoServiceCharge.get(i).getText();
					String aux = totalFee.replaceAll("\\$", "").replaceAll(",","");
					System.out.println(aux);
					System.out.println(fee);
					Assert.assertEquals(fee,aux,"XO Service Charge is not equal");

			}

		Thread.sleep(500);
	}
	//LILIAN 07-03-25 end

	public void verifyOtherAircraftFee(String fee) throws InterruptedException{
		int noOfOtherAircraftFeesLegs= otherAircraftFee.size();
		System.out.println(noOfOtherAircraftFeesLegs);


		/* LILIAN 05_09_25
		String blankpax = paxCategories.get(0).getText(); 
		System.out.println(blankpax);	
		if(!blankpax.equals("")){ //When DNQ or Out of Coverage 
		*/
			for(int i=0;i<otherAircraftFee.size();i++)
			{

				String totalFee = otherAircraftFee.get(i).getText();
				String aux = totalFee.replaceAll("\\$", "").replaceAll(",","");
				System.out.println(aux);
				System.out.println(fee);


				Assert.assertEquals(fee,aux,"Other Aircraft Fee is not equal");

			}
		//} //LILIAN 05_09_25
		Thread.sleep(500);

	}


	//***LILIAN***
		public void verifyMaxPaxCorrespondingCategory(String maxpax) throws InterruptedException{
			System.out.println(maxpax);
			
			int noOfPax= paxCategories.size();
			System.out.println(noOfPax);
			
			/* LILIAN 05_09_25
			String uiPax = paxCategories.get(0).getText();
			System.out.println(uiPax);
		
			if(!uiPax.equals("")){ //If is not DNQ or Out of Service Area
			Assert.assertEquals(uiPax,maxpax,"Max pax is not equal"); 
			*/
		
			//LILIAN 05_09_25
			for(int i=0;i<paxCategories.size();i++)	
			{
				String passenger = paxCategories.get(i).getText();  
				Assert.assertEquals(passenger,maxpax,"Max pax is not equal");	

			} 
			//LILIAN 05_09_25
			Thread.sleep(500);
		}

		


		public void verifyLandingFeesForAsiaAirports(String CL350fee, String CL605fee, String GL6Kfee) throws InterruptedException{
			int noOfLandingfee = landingFee.size();
			System.out.println(noOfLandingfee);

			for(int i=0;i<landingFee.size();i++) {

					String landingfeeUI = landingFee.get(i).getText();
					String aux = landingfeeUI.replaceAll("\\$", "").replaceAll(",","");
					System.out.println(aux);
				
					if (i==0) {
						Assert.assertEquals(CL350fee,aux,"CL350 Fee is not equal");
					}else if (i==1) {
						Assert.assertEquals(CL605fee,aux,"CL605 Fee is not equal");
					}else {
						Assert.assertEquals(GL6Kfee,aux,"Global6000 Fee is not equal");
					}
				
					Thread.sleep(1000);
				}
				Thread.sleep(500);
		}
		
		public void verifyMinimumRunwayMessage() throws InterruptedException{
			int noOfLegs = minRunwayMsg.size();
			System.out.println(noOfLegs);
			
				
			for(int i=0;i<minRunwayMsg.size();i++) {

					String minRunwayUI = minRunwayMsg.get(i).getText();
					System.out.println(minRunwayUI);

					Assert.assertNotEquals("Insufficient runway length",minRunwayUI,"Not returning Minimum Runway Message");

			}
				Thread.sleep(500);
		}
		
		public void verifyMaldivesPassengerTax(int pax, String fee) throws InterruptedException{
			int x = maldivesPassengerTax.size();
			System.out.println(x);
			
			for(int i=0;i<maldivesPassengerTax.size();i++) {
					System.out.println(i);
				
					//Multiplication fee * pax
						double feeaux = Double.parseDouble(fee);
						System.out.println(feeaux);

						double result = feeaux * pax;
						System.out.println("Multiplication result: " + result);
						
					// result-Format the double value to two decimal places
						DecimalFormat df = new DecimalFormat("#.00");
						String formattedresult = df.format(result);
						System.out.println(formattedresult);
						
		
						//UI Maldives fee displayed
						String maldivesUI = maldivesPassengerTax.get(i).getText();
						String maldivesfeeaux = maldivesUI.replaceAll("\\$", "").replaceAll(",","");
						
						System.out.println(maldivesfeeaux);
						
						Assert.assertEquals(formattedresult, maldivesfeeaux,"Maldives Passenger tax is not equal");
				
			}
			Thread.sleep(500);
		}
		

		
		public void verifyVRMMairportDevelopmentTax(int pax, String fee) throws InterruptedException{
			int x = VRMMairportDevelopmentTax.size();
			System.out.println(x);
			
			for(int i=0;i<VRMMairportDevelopmentTax.size();i++) {
	
						System.out.println(i);
						double feeaux = Double.parseDouble(fee);
						System.out.println(feeaux);
							
						double result = feeaux * pax;
						System.out.println("Multiplication result: " + result);
						
						// result-Format the double value to two decimal places
						DecimalFormat df = new DecimalFormat("#.00");
						String formattedresult = df.format(result);
						System.out.println(formattedresult);
							
							
						String maldivesUI = VRMMairportDevelopmentTax.get(i).getText();
						String maldivesfeeaux = maldivesUI.replaceAll("\\$", "");
						System.out.println(maldivesfeeaux);
						
						Assert.assertEquals(formattedresult, maldivesfeeaux,"VRMM Airport Development Tax is not equal");
				
				}
			Thread.sleep(500);
		}


		public void verifyVRDAVipLounge(int pax, String fee) throws InterruptedException{
			
			int x= VRDAVipLounge.size();
			System.out.println(x);
			
			for(int i=0;i<VRDAVipLounge.size();i++) {
		
							System.out.println(i);
							double feeaux = Double.parseDouble(fee);
							System.out.println(feeaux);
						
							double result = feeaux * pax;
							System.out.println("Multiplication result: " + result);
						
						// result-Format the double value to two decimal places
							DecimalFormat df = new DecimalFormat("#.00");
							String formattedresult = df.format(result);
							System.out.println(formattedresult);
							
							
							String VRDALoungeUI = VRDAVipLounge.get(i).getText();
							String VRDAfeeaux = VRDALoungeUI.replaceAll("\\$", "");
							System.out.println(VRDAfeeaux);
						
							Assert.assertEquals(formattedresult, VRDAfeeaux,"VRDA Vip Lounge is not equal");
		
			}
			Thread.sleep(500);
		}
		
		public void verifyVRMMVipLounge(int pax, String fee) throws InterruptedException{

			int x = VRMMVipLounge.size();
			System.out.println(x);

			for(int i=0;i<VRMMVipLounge.size();i++) {
	
							System.out.println(i);
							double feeaux = Double.parseDouble(fee);
							System.out.println(feeaux);
					
							double result = feeaux * pax;
							System.out.println("Multiplication result: " + result);
						
						// result-Format the double value to two decimal places
							DecimalFormat df = new DecimalFormat("#.00");
							String formattedresult = df.format(result);
							System.out.println(formattedresult);
							
							
							String VRMMUI = VRMMVipLounge.get(i).getText();
							String VRMMfeeaux = VRMMUI.replaceAll("\\$", "");
							System.out.println(VRMMfeeaux);
						
							Assert.assertEquals(formattedresult, VRMMfeeaux,"VRMM Vip Lounge is not equal");
			}
			Thread.sleep(500);
		}
	//***LILIAN***
		
	//**LILIAN 05_20_25 begin
	public void verifyExceededMaxPaxLimitmessage(String category, String message) throws InterruptedException{
	
			// if (category.equals("N-REG LIGHT JETS")){ 		// LILIAN 05_22_25
			if (category.equals(" N-REG CIT EXCEL ")){  		// LILIAN 05_22_25
				Assert.assertEquals(" Exceeded max pax limit: 7, requested: 20 pax ",message,"It should show Exceeded Max Pax Limit message, but it is not showing");
			} else if (category.equals("VJ N-REG CL300/350")){
					Assert.assertEquals(" Exceeded max pax limit: 8, requested: 20 pax ",message,"It should show Exceeded Max Pax Limit message, but it is not showing");
					
			} else if (category.equals("VJ N-REG GIV-SP/450")){
					Assert.assertEquals(" Exceeded max pax limit: 12, requested: 20 pax ",message,"It should show Exceeded Max Pax Limit message, but it is not showing");
			} 
	}


	public void verifyPaxInDropdownMatchesReturnedPax(Integer pax) throws InterruptedException{

		String strpax = String.valueOf(pax);
		
		int noOfcategories= paxCategories.size();
		System.out.println(noOfcategories);


		for(int i=0;i<paxCategories.size();i++) {
				String returnedpax =paxCategories.get(i).getText();
				System.out.println(returnedpax);
				Assert.assertEquals(strpax,returnedpax,"Pax is not equal");
		}
		Thread.sleep(5000);
	}	
	//**LILIAN 05_20_25  end
	//} LILIAN 05_20_25

	//LILIAN 06-04-25 begin
	public void verifyOriginDestinationAirportsFees(String departarrive, String test) throws InterruptedException{
		
		int valueUI=0;
		
		System.out.println(departarrive);	
		System.out.println(test);	

		MongoDBReader.getMongoDBDataEngineRWAPremiumAirports();
		
		//LILIAN 07-03-25 begin
		//if (test.equals("origintest")) {
		if (test.equals("departuretest")) {
			//DEPARTURE Airport Fee
			//double departureAirportFeeFromUI=Double.parseDouble(originAirportFee.getText().replaceAll("\\$","").replaceAll(",",""));
			//valueUI = (int)originAirportFeeFromUI;
			//System.out.println(originAirportFeeFromUI);
			
			double departureAirportFeeFromUI=Double.parseDouble(departureAirportFee.getText().replaceAll("\\$","").replaceAll(",",""));
			valueUI = (int)departureAirportFeeFromUI;
			System.out.println(departureAirportFeeFromUI);
			System.out.println(valueUI);
			
		//}else if (test.equals("destinationtest")) {
		}else if (test.equals("arrivetest")) {
			//ARRIVAL Airport Fee
			//double arriveAirportFeeFromUI=Double.parseDouble(destinationAirportFee.getText().replaceAll("\\$","").replaceAll(",",""));
			double arriveAirportFeeFromUI=Double.parseDouble(arriveAirportFee.getText().replaceAll("\\$","").replaceAll(",",""));
			valueUI = (int)arriveAirportFeeFromUI;
			System.out.println(valueUI);
		}
		//LILIAN 07-03-25 end
		
		//Compare UIfee with Mongo db fee
		if(departarrive.equals("KPHX")){
			Assert.assertTrue(valueUI==FeeKPHX,"UI fee is not equal to Mongo db fee");
			
		} else if(departarrive.equals("KSAN")){
			Assert.assertTrue(valueUI== FeeKSAN,"UI fee is not equal to Mongo db fee");
			
		} else if(departarrive.equals("KSFO")){
			Assert.assertTrue(valueUI== FeeKSFO,"UI fee is not equal to Mongo db fee");
		}
		 else if(departarrive.equals("KASE")){
			 System.out.println(FeeKASE);
			 Assert.assertTrue(valueUI==FeeKASE,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KDEN")){
				Assert.assertTrue(valueUI== FeeKDEN,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KEGE")){
				Assert.assertTrue(valueUI== FeeKEGE,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KSBS")){
				Assert.assertTrue(valueUI== FeeKSBS,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KAPF")){
				Assert.assertTrue(valueUI== FeeKAPF,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KBCT")){
				Assert.assertTrue(valueUI== FeeKBCT,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KEYW")){
				Assert.assertTrue(valueUI== FeeKEYW,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KMIA")){
				Assert.assertTrue(valueUI== FeeKMIA,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KOPF")){
				Assert.assertTrue(valueUI== FeeKOPF,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KPBI")){
				Assert.assertTrue(valueUI== FeeKPBI,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KVRB")){
				Assert.assertTrue(valueUI== FeeKVRB,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KATL")){
				Assert.assertTrue(valueUI== FeeKATL,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KPDK")){
				Assert.assertTrue(valueUI== FeeKPDK,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KSAV")){
				Assert.assertTrue(valueUI== FeeKSAV,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KORD")){
				Assert.assertTrue(valueUI== FeeKORD,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KMDW")){
				Assert.assertTrue(valueUI== FeeKMDW,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KDPA")){
				Assert.assertTrue(valueUI== FeeKDPA,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KUGN")){
				Assert.assertTrue(valueUI== FeeKUGN,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KPWK")){
				Assert.assertTrue(valueUI== FeeKPWK,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KALE")){
				Assert.assertTrue(valueUI== FeeKALE,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KCCG")){
				Assert.assertTrue(valueUI== FeeKCCG,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KHHF")){
				Assert.assertTrue(valueUI== FeeKHHF,"UI fee is not equal to Mongo db fee");
			}
		
		 else if(departarrive.equals("KINK")){
				Assert.assertTrue(valueUI== FeeKINK,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KKIP")){
				Assert.assertTrue(valueUI== FeeKKIP,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KMIF")){
				Assert.assertTrue(valueUI== FeeKMIF,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KPEQ")){
				Assert.assertTrue(valueUI== FeeKPEQ,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KPRS")){
				Assert.assertTrue(valueUI== FeeKPRS,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KTDW")){
				Assert.assertTrue(valueUI== FeeKTDW,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KCVG")){
				Assert.assertTrue(valueUI== FeeKCVG,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KBOS")){
				Assert.assertTrue(valueUI== FeeKBOS,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KBED")){
				Assert.assertTrue(valueUI== FeeKBED,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KHYA")){
				Assert.assertTrue(valueUI== FeeKHYA,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KMVY")){
				Assert.assertTrue(valueUI== FeeKMVY,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KDTW")){
				Assert.assertTrue(valueUI== FeeKDTW,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KIMT")){
				Assert.assertTrue(valueUI== FeeKIMT,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KMNM")){
				Assert.assertTrue(valueUI== FeeKMNM,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KOSC")){
				Assert.assertTrue(valueUI== FeeKOSC,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KPLN")){
			 	Assert.assertTrue(valueUI== FeeKPLN,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KSAW")){
				Assert.assertTrue(valueUI== FeeKSAW,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KTVC")){
				Assert.assertTrue(valueUI== FeeKTVC,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KINL")){
				Assert.assertTrue(valueUI== FeeKINL,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KMSP")){
				Assert.assertTrue(valueUI== FeeKMSP,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KRGK")){
				Assert.assertTrue(valueUI== FeeKRGK,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KRRT")){
				Assert.assertTrue(valueUI== FeeKRRT,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KTWM")){
				Assert.assertTrue(valueUI== FeeKTWM,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KSTL")){
				Assert.assertTrue(valueUI== FeeKSTL,"UI fee is not equal to Mongo db fee");
			}
		 else if(departarrive.equals("KEWR")){
				Assert.assertTrue(valueUI== FeeKEWR,"UI fee is not equal to Mongo db fee");
			}
		
		Thread.sleep(5000);

	}

	public void verifyUIMatchesStateToStateExcelBasePrice(String baseprice) throws InterruptedException{

	
		String basepriceaux = basePrice.getText();
		String aux = basepriceaux.replaceAll("\\$", "").replaceAll(",","");
		System.out.println(aux);
	
		System.out.println(baseprice);
		//LILIAN 07-16-25 begin
		//Assert.assertEquals(baseprice,aux,"State to State base price from Excel is not equal to the UI base price");
		Assert.assertEquals(aux, baseprice,"State to State base price from Excel is not equal to the UI base price");
		//LILIAN 07-16-25  end
	}
	
	public void verifyStateToStateBasepriceIsNotCovered(String total) throws InterruptedException{
		
		String totalaux = totalprice.getText();
		String aux = totalaux.replaceAll("\\$", "").replaceAll(",","");
		System.out.println(aux);
	
		System.out.println(total);
	
		Assert.assertEquals(total,aux,"It should show 'Out of Coverage' message When base price = 99999");
	
	}
//LILIAN 06-04-25 end
	//LILIAN 06-26-25 begin
	public void selectOnly_CL604_CL605_categories() throws InterruptedException{
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
	
		elacCategoriesList.get(9).click();
		elacCategoriesList.get(10).click();
		Thread.sleep(1200);
	}
	
	
	public void selectOnly_AHL650_category() throws InterruptedException{
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
	
		elacCategoriesList.get(4).click();
		Thread.sleep(1200);
	}
	
	public void selectOnlyAHPhenom300AHCitationXLSCategories() throws InterruptedException{
		//uncheck domestic categories
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
		
		//check ah phenom300 and ah citation xls+
		elacCategoriesList.get(1).click();
		elacCategoriesList.get(2).click();
		Thread.sleep(1200);
	}
	
	public void selectOnly_AHL600_category() throws InterruptedException{
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
	
		elacCategoriesList.get(3).click();
		Thread.sleep(1200);
	}

	//LILIAN 07-16-25 begin
	public void selectOnly_AHL600_AHL650category() throws InterruptedException{
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
	
		elacCategoriesList.get(3).click();
		elacCategoriesList.get(4).click();
		Thread.sleep(1200);
	}
	//LILIAN 07-16-25
	
	//LILIAN 08-03-25 begin
	public void select_all_AH_categories_and_verify_Wifi_fee_was_removed() throws InterruptedException{
		//uncheck categories
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
	
		
		//check categories
		elacCategoriesList.get(1).click();
		elacCategoriesList.get(2).click();
		elacCategoriesList.get(3).click();
		elacCategoriesList.get(4).click();
		Thread.sleep(1200);
		setCalculateButton();
		clickViewButton();
		
		verifyDoesNotReturnWifi();
		
		
		
		//uncheck categories
		elacCategoriesList.get(1).click();
		elacCategoriesList.get(2).click();
		elacCategoriesList.get(3).click();
		elacCategoriesList.get(4).click();
		
		//check category
		elacCategoriesList.get(5).click();
		setCalculateButton();
		clickViewButton();
		
		verifyDoesNotReturnWifi();
		
	}
	
	public void verifyDoesNotReturnWifi() throws InterruptedException{
		int nrolegs= Wifi.size();
		System.out.println(nrolegs);
		
		Assert.assertTrue(nrolegs == 0,"Should not return Wifi for AH categories");	

	}
	
	
	public void select_all_VJ_categories_and_verify_Wifi_fee_is_displayed() throws InterruptedException{
		//uncheck categories
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
	
		
		//check categories
		elacCategoriesList.get(8).click();
		elacCategoriesList.get(9).click();
		elacCategoriesList.get(10).click();
		elacCategoriesList.get(11).click();
		Thread.sleep(1200);
		setCalculateButton();
		clickViewButton();
		
		verifyReturnsWifi();
		Thread.sleep(2000);
		
		
		//uncheck categories
		elacCategoriesList.get(8).click();
		elacCategoriesList.get(9).click();
		elacCategoriesList.get(10).click();
		elacCategoriesList.get(11).click();
		
		//check category
		elacCategoriesList.get(12).click();
		elacCategoriesList.get(13).click();
		setCalculateButton();
		clickViewButton();
		
		verifyReturnsWifi();
		Thread.sleep(1200);
		
	}
	public void verifyReturnsWifi() throws InterruptedException{
		int nrolegs= Wifi.size();
		System.out.println(nrolegs);
		
		Assert.assertTrue(nrolegs != 0,"Should  return Wifi for VJ categories");	

	}
	
	public void selectOnlyCL350Category() throws InterruptedException{
		//uncheck categories
		elacCategoriesList.get(0).click();
		elacCategoriesList.get(6).click();
		elacCategoriesList.get(7).click();
	
		
		//check categories
		elacCategoriesList.get(8).click();
		Thread.sleep(1200);
	}
	
	public void verifyStackingDiscountforCL350() throws InterruptedException{

		int nroofstackdisc= stackingDiscount.size();
		System.out.println(nroofstackdisc);
		
		for(int i=0;i<stackingDiscount.size();i++) {

			String value1 = stackingDiscount.get(i).getText(); 
			System.out.println(value1);

			// Remove dollar sign from the string
			String cleanString1 = value1.replaceAll("\\$", "").replaceAll(",","");
			double value2 = Double.parseDouble(cleanString1);
			System.out.println(value2);
			Assert.assertTrue(value2==-1500,"Stacking Discount different from -$1500 for CL350");
		}
	
	}
	
	public void verifyReturnsFuelStopForOnlyOneLeg(Double fee) throws InterruptedException{
		int noOfFuelStop= fuelStop.size();
		System.out.println(noOfFuelStop);
		
		
		if (noOfFuelStop == 1) {
			for(int i=0;i<fuelStop.size();i++) {

				double value2 = Double.parseDouble(fuelStop.get(i).getText().replaceAll("\\$", "").replaceAll(",", ""));
				System.out.println(value2);
				System.out.println(fee);
				
				Assert.assertEquals(fee,value2,0.0001);

			}
		} else  {
			Assert.assertTrue(noOfFuelStop != 1,"Wrong amount of fuel stops returned");		
		}
		
	}

	//LILIAN 08-03-25 end
	
	public void verifyFlightTimeOutsideAircraftRangeMessage(String depart, String message) throws InterruptedException{ //***
		
		if (depart.equals("EGGW")){ 
			String value1 = flightTimeAHP300_CITXLSMsg1.get(0).getText(); 
			Assert.assertEquals(value1, message,"It should show outside aircraft range msg, but it is not showing");
	
		} else if (depart.equals("EGKK")){
			String value2 = flightTimeAHP300_CITXLSMsg2.get(0).getText(); 
			Assert.assertEquals(message,value2,"It should show outside aircraft range msg, but it is not showing");
				
		} else if (depart.equals("LEMD")){
			String value3 = flightTimeAHP300_CITXLSMsg3.get(0).getText(); 
			Assert.assertEquals(message,value3,"It should show outside aircraft range msg, but it is not showing");
		
		} else if (depart.equals("LFMN")){
			String value4 = flightTimeAHP300_CITXLSMsg4.get(0).getText(); 
			Assert.assertEquals(message,value4,"It should show outside aircraft range msg, but it is not showing");
		
		
		}else if (depart.equals("KTEB")){
			String value5 = flightTimeAHL650Msg.get(0).getText(); 
			Assert.assertEquals(value5,message,"It should show Flight Time Outside Range msg, but it is not showing");
		}
		
	
		Thread.sleep(500);
	}
	//LILIAN 06-26-25 end
	
	public void verifyBannedMessage() throws InterruptedException{
		int noOfLegs = bannedMsg.size();
		System.out.println(noOfLegs);
		
			
		for(int i=0;i<bannedMsg.size();i++) {
				String bannedUI = bannedMsg.get(i).getText();
				System.out.println(bannedUI);
				Assert.assertNotEquals(" No price returned because route is banned. ",bannedUI,"should return Banned Message");
		}
			Thread.sleep(500);
	}
	
	
	public void verifyReturnsOutOfServiceAreaAHL600msg() throws InterruptedException{
		int noOfLegs = outServiceAreaAHL600Msg.size();
		System.out.println(noOfLegs);
		
			
		for(int i=0;i<outServiceAreaAHL600Msg.size();i++) {
				String outServiceL600UI = outServiceAreaAHL600Msg.get(i).getText();
				System.out.println(outServiceL600UI);
				Assert.assertNotEquals(" Out of service area for AH LEGACY 600 Guaranteed pricing, no guaranteed availability. ",outServiceL600UI,"should return OUt of Service Area Message");
		}
			Thread.sleep(500);
	}
	
	//LILIAN 07-16-25  begin
	/*public void verifyReturnsFuelStop() throws InterruptedException{
		int noOfLegs= fuelStop.size();
		System.out.println(noOfLegs);

			for(int i=0;i<fuelStop.size();i++) {

			String value1 = fuelStop.get(i).getText(); 
			System.out.println(value1);

			// Remove dollar sign from the string
			String cleanString1 = value1.replaceAll("\\$", "").replaceAll(",","");
			double value2 = Double.parseDouble(cleanString1);
			System.out.println(value2);
			Assert.assertTrue(value2==3000,"Fuel Stop different from $3000");

			}
	}*/
	
	public void verifyReturnsFuelStop(double fee) throws InterruptedException{
		int noOfLegs= fuelStop.size();
		System.out.println(noOfLegs);

			for(int i=0;i<fuelStop.size();i++) {

			double value2 = Double.parseDouble(fuelStop.get(i).getText().replaceAll("\\$", "").replaceAll(",", ""));
			System.out.println(value2);
			System.out.println(value2);
			System.out.println(fee);
			Assert.assertEquals(fee,value2,"Fuel Stop Fee is not equal");

			}
	}
	
	public void verifyDoesNotReturnFuelStop() throws InterruptedException{
		int noOfLegs= fuelStop.size();
		System.out.println(noOfLegs);
		
		Assert.assertTrue(noOfLegs == 0,"Should not return Fuel Stop for AHL600 and AHL650, once eft < 7 hrs");	
	
	}
	
	//LILIAN 07-16-25 end
	
	public void setDepartureDateTimeForStackingDiscount(String hour, String min,String period) throws InterruptedException {
		System.out.println(hour);
		System.out.println(min);
		System.out.println(period);
		
		departuredate.clear();
		departuredate.click();
		Thread.sleep(3000);
		
		Actions ac= new Actions(driver);
		ac.click(departuredate);
		
		ac.sendKeys(hour);
		ac.sendKeys(min);
		ac.sendKeys(period);
		
		ac.perform();
		Thread.sleep(1000);
	}
	
	public void verifyStackingDiscount() throws InterruptedException {

			int nroofstackdisc= stackingDiscount.size();
			System.out.println(nroofstackdisc);
			
			if (nroofstackdisc == 2) {

				for(int i=0;i<stackingDiscount.size();i++) {

				String value1 = stackingDiscount.get(i).getText(); 
				System.out.println(value1);

					// Remove dollar sign from the string
					String cleanString1 = value1.replaceAll("\\$", "").replaceAll(",","");
					double value2 = Double.parseDouble(cleanString1);
					System.out.println(value2);
					Assert.assertTrue(value2==-1000,"Stacking Discount different from -$1000");
				}
			} else  {
					Assert.assertTrue(nroofstackdisc != 2,"Wrong amount of stack discounts");		
			}		
	}
	
	public void verifyDoesNotReturnStackingDiscount() throws InterruptedException{
		int nroofstackdisc= stackingDiscount.size();
		System.out.println(nroofstackdisc);
		
		//LILIAN 07-03-25 begin
		//Assert.assertTrue(nroofstackdisc != 0,"Should not return Stacking Discount for selected Time");
		Assert.assertTrue(nroofstackdisc == 0,"Should not return Stacking Discount for selected Time");	
		//LILIAN 07-03-25 end
	}
	
	//LILIAN 06-26-25 end
	//LILIAN 07-26-25 begin
	public void verifySubtotalAndTotalValuesAreDifferent() throws InterruptedException{
		int subtotalvalueaux1= subtotalvalue.size();
		System.out.println(subtotalvalueaux1);
		
		int totalvalueaux1= prices.size();
		System.out.println(totalvalueaux1);

			for(int i=0;i<subtotalvalue.size();i++) {
				//sutotal
				String subtotalvalueaux2 = subtotalvalue.get(i).getText();  //LILIAN 05_20_25
				System.out.println(subtotalvalueaux2);
				// Remove dollar sign from the string
				String cleanString1 = subtotalvalueaux2.replaceAll("\\$", "").replaceAll(",","");
				double subtotalvalueaux3 = Double.parseDouble(cleanString1);
				System.out.println(subtotalvalueaux3);
			
				//total
				String totalvalueaux2 = prices.get(i).getText();  //LILIAN 05_20_25
				System.out.println(totalvalueaux2);
				// Remove dollar sign from the string
				String cleanString2 = totalvalueaux2.replaceAll("\\$", "").replaceAll(",","");
				double totalvalueaux3 = Double.parseDouble(cleanString2);
				System.out.println(totalvalueaux3);
				
				Assert.assertNotEquals(subtotalvalueaux3,totalvalueaux3,"Failed: Subtotal and Total values should be different!");
				

			}
	}
	
	public void verifySubtotalAndTotalValuesAreTheSame() throws InterruptedException{
		int subtotalvalueaux1= subtotalvalue.size();
		System.out.println(subtotalvalueaux1);
		
		int totalvalueaux1= prices.size();
		System.out.println(totalvalueaux1);

			for(int i=0;i<subtotalvalue.size();i++) {
				//sutotal
				String subtotalvalueaux2 = subtotalvalue.get(i).getText();  //LILIAN 05_20_25
				System.out.println(subtotalvalueaux2);
				// Remove dollar sign from the string
				String cleanString1 = subtotalvalueaux2.replaceAll("\\$", "").replaceAll(",","");
				double subtotalvalueaux3 = Double.parseDouble(cleanString1);
				System.out.println(subtotalvalueaux3);
			
				//total
				String totalvalueaux2 = prices.get(i).getText();  //LILIAN 05_20_25
				System.out.println(totalvalueaux2);
				// Remove dollar sign from the string
				String cleanString2 = totalvalueaux2.replaceAll("\\$", "").replaceAll(",","");
				double totalvalueaux3 = Double.parseDouble(cleanString2);
				System.out.println(totalvalueaux3);
				
				Assert.assertEquals(subtotalvalueaux3,totalvalueaux3,"Failed: Subtotal and Total values should be the same! ");
				

			}
	}
	
	
	public void verifyInternationalHeadTax(double tax) throws InterruptedException{
		int nrointheadtax= internationalHeadTax.size();
		System.out.println(nrointheadtax);
		
		System.out.println(tax);
		
		for(int i=0;i<internationalHeadTax.size();i++) {

			double value2 = Double.parseDouble(internationalHeadTax.get(i).getText().replaceAll("\\$", "").replaceAll(",", ""));
			System.out.println(value2);
			System.out.println(tax);
			Assert.assertEquals(tax,value2,"International Head Tax is not equal");
		
		}
	}
	
	
	//LILIAN 07-26-25 end
