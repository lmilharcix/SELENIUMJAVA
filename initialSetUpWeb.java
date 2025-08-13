package stepDefinationWeb;

import java.awt.*;
import java.io.IOException;

import java.util.Set;


import io.cucumber.java.en.And;
import net.lightbody.bmp.core.har.Har;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.Cookie;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseWeb.WebBrowserManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectModel.JetStudio_Calculator;
import pageObjectModel.JetStudio_DashboardPage;
import pageObjectModel.JetStudio_Login;
import pageObjectModel.JetStudio_UpdateParameters;

public class initialSetUpWeb extends WebBrowserManager {
	//WebAppManager webAppManager= new WebAppManager(WebAppManager.driver);
	JetStudio_Login jetStudio_Login;
	JetStudio_DashboardPage jetStudio_dashboardPage;
	JetStudio_Calculator jetStudio_Calculator; //Lilian

	JetStudio_UpdateParameters jetStudio_UpdateParameters;//Lilian
	Har har;
	WebBrowserManager webManager = new WebBrowserManager();
	
	public initialSetUpWeb() throws IOException {
		super();
	}

	//= PageFactory.initElements(driver, JetStudio_Login.class);
	
	@Given("I login into web app")
	public void i_login_into_web_app() throws IOException, InterruptedException, AWTException {
		driverSetUP();
		jetStudio_Login = PageFactory.initElements(driver, JetStudio_Login.class);
		jetStudio_dashboardPage= PageFactory.initElements(driver, JetStudio_DashboardPage.class);
		jetStudio_Login.getTittle();
	}

	@Given("I login into web app without logging")
	public void i_login_into_web_app_withoutLogs() throws IOException, InterruptedException, AWTException {
		driverSetUPWithoutLogs();
		jetStudio_Login = PageFactory.initElements(driver, JetStudio_Login.class);
		jetStudio_dashboardPage= PageFactory.initElements(driver, JetStudio_DashboardPage.class);
		jetStudio_Login.getTittle();
	}

	@When("user enter email {string} and password {string}")
	public void user_enter_email_and_password(String email, String password) throws InterruptedException {
		jetStudio_Login.clickLoginButton();
		jetStudio_Login.setEmail(email);
		jetStudio_Login.clickContinueButton();
		jetStudio_Login.setPassword(password);
		//jetStudio_Login.clickContinueButtonOnPasswordScreen();
	}

	@When("user click on sign in button")
	public void user_click_on_sign_in_button() throws InterruptedException {
		jetStudio_Login.signIn();
		//har3 = proxy.getHar();
	}

	@Then("verify user login successful and get authorization header")
	public void verify_user_login_successful() throws IOException, ParseException, InterruptedException {
		Assert.assertSame("Dashboard", driver.getTitle());
		Thread.sleep(20000);
		//Har har=proxy.getHar();
		//har = WebBrowserManager.har3;
		//webManager.har1=har.getLog().getEntries();
		//har.writeTo(webManager.harFile);
		Set<Cookie> cki= driver.manage().getCookies();
		/*for(HarEntry harnew :har1){
			//System.out.println(harnew.getRequest().getUrl());
			if(harnew.getRequest().getUrl().contains("http://pricing.dev.jetsmarter.io/api/v1/charter-pricing-backend-api/meta")){
				webManager.har2.add(harnew);
			}
		}
		Object obj=null;
		for(HarEntry harnew :har2){
			//System.out.println(harnew.getRequest().getUrl());
			if(harnew.getRequest().getPostData()!=null){
				String object= harnew.getRequest().getPostData().getText();
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(object);
				obj= Parser.getJSONObject(jsonObject.get("data"),"0");
				System.out.println("Value of e parameter is: "+Parser.getJSONObject(obj,"e"));
				if(Parser.getJSONObject(obj,"e").toString().equalsIgnoreCase("pv")){
					boolean value=true;
					break;
				}
			}
		}*/
		/*String pageSource=driver.getPageSource();
		HttpURLConnection cn=
				(HttpURLConnection)new URL("https://pricing.dev.jetsmarter.io/api/v1/charter-pricing-analytics-backend-api/airports/validate?code=KTEB")
						.openConnection();
		// set the HEAD request
		cn.setRequestMethod("HEAD");
		// connection initiated
		cn.connect();
		int res = cn.getResponseCode();
		System.out.println("Http response code: " + res);*/
	}

	@Then("Navigate to Dashboard page")
	public void navigateToDashboardPage() throws IOException, ParseException, InterruptedException {
		jetStudio_dashboardPage.navigateToDashboardPage();
		jetStudio_dashboardPage.applyDatesAndClickApplyButton();
		Assert.assertSame("Dashboard", driver.getTitle());
		Thread.sleep(20000);
	}

	@Then("Verify {string} MembershipTypes DropDown on pricing calculator page")
	public void validateMemberShipDropDownPage(String memberShipType) throws IOException, ParseException, InterruptedException {
		Assert.assertSame("Dashboard", driver.getTitle());
		jetStudio_dashboardPage.validateMemberShipDropdownForAllCategories(memberShipType);
		Thread.sleep(20000);
		driver.navigate().refresh();
	}

	@Then("Verify {string} MembershipTypes DropDown prices on pricing calculator page")
	public void validateMemberShipPrices(String memberShipType) throws IOException, ParseException, InterruptedException {
		Assert.assertSame("Dashboard", driver.getTitle());
		jetStudio_dashboardPage.validateMemberShipPricesAllCategories(memberShipType);
		Thread.sleep(20000);
		driver.navigate().refresh();
	}

	@Then("Verify {string} MembershipTypes DropDown prices on pricing calculator page without refresh")
	public void validateMemberShipPricesForElac(String memberShipType) throws IOException, ParseException, InterruptedException {
		Assert.assertSame("Dashboard", driver.getTitle());
		jetStudio_dashboardPage.validateMemberShipPricesAllCategories(memberShipType);
		Thread.sleep(20000);
	}


	//Lilian- domestic one way
	@Given("user is on the calculator page")
	public void user_is_on_the_calculator_page() throws IOException, InterruptedException {
		jetStudio_Calculator = PageFactory.initElements(driver, JetStudio_Calculator.class);
	}
	@When("user enters origin {string} destination {string}")
	public void user_enters_origin_destination(String origin, String destination) throws IOException, ParseException, InterruptedException{
		driver.navigate().refresh();//LILIAN
		Thread.sleep(500);  //LILIAN
		jetStudio_Calculator.setOrigin(origin);
		jetStudio_Calculator.setDestination(destination);
		//jetStudio_Calculator.setDepartureDate(); LILIAN 06-26-25

		//jetStudio_Calculator.setDepartureDateNew();
		//jetStudio_Calculator.setMembership();
		//jetStudio_Calculator.setPax();
		//jetStudio_Calculator.setDepTimeChange();
		//jetStudio_Calculator.setShowDnq();
		//jetStudio_Calculator.setWholeSaleRetail();
	}

	//LILIAN 07-03-25 begin
	 @Then ("set departure date for ELAC")
	 public void  set_departure_date_for_ELAC() throws IOException, InterruptedException {
		 jetStudio_Calculator.setDepartureDate();
	 }
	//LILIAN 07-03-25 end

	@When("user enters origin {string} destination {string} with time between 6:00AM to 6:59AM")
	public void user_enters_origin_destination_with_morning_time(String origin, String destination) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.setOrigin(origin);
		jetStudio_Calculator.setDestination(destination);
		jetStudio_Calculator.setDepartureDateAndMorningTime();
	}

	@When("user enters origin {string} destination {string} with time between 00:00AM to 05:59AM")
	public void user_enters_origin_destination_with_night_time(String origin, String destination) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.setOrigin(origin);
		jetStudio_Calculator.setDestination(destination);
		jetStudio_Calculator.setDepartureDateAndNightTime();
	}

	@When("user enters origin {string} destination {string} with time between 23:00PM to 05:59AM")
	public void user_enters_origin_destination_with_Extended_Night_Time(String origin, String destination) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.setOrigin(origin);
		jetStudio_Calculator.setDestination(destination);
		jetStudio_Calculator.setDepartureDateAndExtendedNightTime();
	}

	@Then("user clicks calculate button")
	public void user_clicks_calculate_button() throws IOException, ParseException, InterruptedException {
		Thread.sleep(3000); //LILIAN 05_09_25
		jetStudio_Calculator.setCalculateButton();
		//Thread.sleep(500);  //LILIAN 05_09_25
		Thread.sleep(3000); //LILIAN 05_09_25
	}

	@Then("verify totals are higher than 6000")
	public void verify_totals_are_higher_than_6000() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyTotalXolite();
		//jetStudio_Calculator.verifyTotalCitx();
		//jetStudio_Calculator.verifyTotalNregsmid();
		//jetStudio_Calculator.verifyTotalNregulr();
	}
	//Lilian - domestic round trip
	@When("user enters {string} and {string} and {string} and {string} for roundtrip")
	public void user_enters_depart_and_arrive_and_depart2_and_arrive2_for_roundtrip(String origin,String destination, String origin2, String destination2) throws IOException, ParseException, InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(500);
		jetStudio_Calculator.setOrigin(origin);
		jetStudio_Calculator.setDestination(destination);
		//jetStudio_Calculator.setDepartureDate(); LILIAN 06-26-25

		Thread.sleep(300);
		jetStudio_Calculator.setAddRtLeg();
	}

	//Lilian - domestic round trip
	@When("user enters {string} and {string} for two roundtrip")
	public void user_enters_two_rountrips(String origin,String destination) throws IOException, ParseException, InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(500);
		jetStudio_Calculator.setOrigin(origin);
		jetStudio_Calculator.setDestination(destination);
		//LILIAN 06-04-25 jetStudio_Calculator.setDepartureDate();
		Thread.sleep(300);
		jetStudio_Calculator.setAddRtLeg();
		jetStudio_Calculator.setAddRtLeg();
	}

	@When("user enters {string} and {string} for three roundtrip")
	public void user_enters_three_rountrips(String origin,String destination) throws IOException, ParseException, InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(500);
		jetStudio_Calculator.setOrigin(origin);
		jetStudio_Calculator.setDestination(destination);
		//LILIAN 06-04-25 jetStudio_Calculator.setDepartureDate();
		Thread.sleep(300);
		jetStudio_Calculator.setAddRtLeg();
		jetStudio_Calculator.setAddRtLeg();
		jetStudio_Calculator.setAddRtLeg();
	}

	//Lilian - domestic multileg
	@When("user enters {string} and {string} and {string} and {string} for multileg")
	public void user_enters_depart_and_arrive_and_depart2_and_arrive2_for_multileg(String origin,String destination, String origin2, String destination2) throws IOException, ParseException, InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(500);
		jetStudio_Calculator.setOrigin(origin);
		jetStudio_Calculator.setDestination(destination);
		jetStudio_Calculator.setDepartureDate();
		Thread.sleep(300);
		jetStudio_Calculator.setAddLeg();
		jetStudio_Calculator.setOrigin2(origin2);
		jetStudio_Calculator.setDestination2(destination2);
		jetStudio_Calculator.setDepartureDate2();
		Thread.sleep(300);
	}

	@Then("verify totals for all flights are greater than 6000")
	public void verify_totals_are_higher_than_6000_for_all_flights() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyTotalForAllFlights();
	}

	@And("verify totals for all flights are greater than 6000 for ELAC")
	public void verify_totals_are_higher_than_6000_for_all_flights_In_ELAC() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyTotalForAllFlightsForELAC();
	}

	@And("verify totals for all flights are greater than 6000 for XO Member Non Member")
	public void verify_totals_are_higher_than_6000_for_all_flights_In_XOMember() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyTotalForAllFlightsForXoMemberNonMember();
	}

	@And("verify totals for default flights are greater than 6000 for XO Member Non Member")
	public void verify_totals_are_higher_than_6000_for_default_flights_In_XOMember() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyTotalForDefaultFlightsForXoMemberNonMember();
	}

	@And("verify Red Eye Fees with MongoDB categories with {string}")
	public void verify_redEyeFees_With_MongoDB(String category) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyRedEyeFeesForSelectedCategory(category);
	}
	@And("verify Red Eye Fees with MongoDB categories with {string} in night time")
	public void verify_redEyeFees_With_MongoDB_NightTime(String category) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyRedEyeFeesForSelectedCategoryOnNightTime(category);
	}


	@And("unCheck Selected categories")
	public void uncheckSelectedCategories() throws InterruptedException {
		jetStudio_Calculator.unCheckCategories();
	}
	@And("verify Red Eye Fees with MongoDB categories with {string} in extended night time")
	public void verify_redEyeFees_With_MongoDB_ExtendedNightTime(String category) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyRedEyeFeesForSelectedCategoryOnExtendedNightTime(category);
	}

	@And("verify all categories unselected and select mentioned {string} category")
	public void unSelectAllCategoriesAndSelectMentionedCategory(String category) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.uncheckAllCategoriesAndSelectMentionedCategory(category);
	}

	@Then("verify dining fees is {double} for all legs")
	public void verifyDiningFeesOnPricingCalculator(double fees) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyDiningFeesIsDisplayedAsPerLeg(fees);
	}

	@Then("verify FET,CO2 and Fuel Surchange Fees")
	public void verifyFet_CO2_FuelSurcharge() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyFET_CO2_FuelSurchargeFees();
	}

	@Then("verify CO2 and Fuel Surchange Fees for {string}")
	public void verifyCO2_FuelSurcharge(String category) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyCO2_FuelSurchargeFees(category);
	}

	@Then("verify FET,CO2 Fees")
	public void verifyFet_CO2_Fees() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyFET_CO2_Fees();
	}

	@Then("verify De Icing fees is {double} for all legs")
	public void verifyDiIcingFeesOnPricingCalculator(double fees) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyDiIcingFeesIsDisplayedAsPerLeg(fees);
	}

	@Then("verify Customer Service fees is {double} for all legs")
	public void verifyCustomerServiceFeesOnPricingCalculator(double fees) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyCustomerServiceFeesIsDisplayedAsPerLeg(fees);
	}
		//***LILIAN***
		@Then("verify Max Pax {string} Corresponding Category")
		public void verifyMaxPaxCorrespondingCategory(String maxpax) throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyMaxPaxCorrespondingCategory(maxpax);
		}
		
		@And ("select Only CL350_CL605_GL6K Category")
		public void select_Only_CL350_CL605_GL6K_Category() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.selectOnlyCL350_CL605_GL6K_Category();

		}

		@Then ("user verifies Landing fee for {string} {string} {string}")
		public void user_verifies_Landing_fee_for(String CL350fee, String CL605fee, String GL6kfee) throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyLandingFeesForAsiaAirports(CL350fee, CL605fee,  GL6kfee);
			Thread.sleep(500);
		}
		@Then("verify minimum Runway message")
		public void verify_minimum_Runway_message()  throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyMinimumRunwayMessage();
			Thread.sleep(500);
		}

		//***LILIAN***

		//LILIAN 07-26-25 begin
	@And ("select Only GL6000_GL7500 Categories")
	public void select_Only_GL6000_GL7500_Categories() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.selectOnlyGL6000_GL7500_Categories();

	}
	
	@And ("select Only Asia Categories")
	public void select_Only_Asia_Categories() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.selectOnlyAsia_Categories();

	}
	
	@And ("select Only CL605_CL850_GL6000_GL7500 Categories")
	public void select_Only_CL605_CL850_GL6000_GL7500_Categories() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.selectOnlyCL605_CL850_GL6000_GL7500_Categories();

	}
	//LILIAN 07-26-25 end
	
		//LILIAN 05_20_25 begin
		@Then("verify Exceeded Max Pax Limit message {string} {string}")
		public void verifyExceededMaxPaxLimitmessage(String category, String message) throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyExceededMaxPaxLimitmessage(category, message);
		}
		
		@Then ("verify pax in dropdown {int} matches returned pax for domestic categories")
		public void verifyPaxInDropdownMatchesReturnedPax(int pax) throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyPaxInDropdownMatchesReturnedPax(pax);
		}
		//LILIAN 05_20_25 end

	@Then("Close the browser")
	public void close_browser() throws IOException, ParseException, InterruptedException {
		driver.quit();
	}

	//*********************************************UPDATE PARAMETERS**********************************************************



	@Given("user is on dashboard page")
	public void user_is_on_dashboard_page()throws IOException, ParseException, InterruptedException{

		jetStudio_Calculator = PageFactory.initElements(driver, JetStudio_Calculator.class);
		jetStudio_dashboardPage= PageFactory.initElements(driver, JetStudio_DashboardPage.class);
		jetStudio_UpdateParameters = PageFactory.initElements(driver, JetStudio_UpdateParameters.class);
		Thread.sleep(1000);

		driver.get("https://dev.dev.v1sta.io/dashboard");

		Thread.sleep(6500);
		jetStudio_UpdateParameters.dashStartDate();
		Thread.sleep(1500);
		jetStudio_UpdateParameters.dashEndDate();
		Thread.sleep(1000);
		jetStudio_UpdateParameters.applyButton();
		Thread.sleep(6000);

	}


	@When("user clicks update parameters button")
	public void user_clicks_update_parameters_button() throws IOException, ParseException, InterruptedException{
		Thread.sleep(3000);

		jetStudio_UpdateParameters.updateParametersButton();
		Thread.sleep(5000);
	}

	@Then("user closes update parameters dialog")
	public void user_closes_update_parameters_dialog() throws IOException, ParseException, InterruptedException{
		Thread.sleep(2000);
		jetStudio_UpdateParameters.eventCloseDialog();
		Thread.sleep(3500);
	}


	//*********************************************EVENT  TAB**********************************************************

	@Then("user clicks on event fee tab")
	public void user_clicks_on_event_fee_tab() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.clickEventFeeTab();
		Thread.sleep(500);

	}

	//Show expired/ Hide expired
	@Then ("user clicks show expired button")
	public void user_clicks_show_expired_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.showExpiredCommand();
		Thread.sleep(1000);
	}


	@Then ("user clicks hide expired button")
	public void user_clicks_hide_expired_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.hideExpiredCommand();
		Thread.sleep(1000);
	}

	////ADD EVENT

	@Then("user clicks on event add new button")
	public void user_clicks_on_event_add_new_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.eventFeeAddNewButton();
		Thread.sleep(3000);
	}

	@Then("user clicks on cancel button")
	public void user_clicks_on_cancel_button()  throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.eventCancelButtonCommand();
		Thread.sleep(2500);

	}

	@Then("user clicks on cancel airport button")
	public void user_clicks_on_cancel_airport_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.airportCancelButtonCommand();
		Thread.sleep(2000);
	}

	@Then("user enters event details {string} {string} {string} then event is added")
	public void user_enters_event_details_then_event_is_added(String eventname, String location, String fee) throws IOException, ParseException, InterruptedException{

		jetStudio_UpdateParameters.addEventDetailsMain(eventname,location,fee);
		Thread.sleep(1500);


		if	(eventname.equals(("Event3"))) {
			jetStudio_UpdateParameters.eventCloseDialog();
		}

	}

	//FILTER ADDED EVENT
	@Then("user clicks on event filter button for EventName and selects {string} and {string}")
	public void user_clicks_on_event_filter_button_for_EventName_and_selects(String filterOption,String event) throws IOException, ParseException, InterruptedException{

		String mainWindowHandle = driver.getWindowHandle();  //save main update parameters window, then open filter window

		Thread.sleep(1500);
		jetStudio_UpdateParameters.eventFilterMenuCommand(filterOption,event);
		Thread.sleep(1500);

		driver.switchTo().window(mainWindowHandle); //go back to main update parameters window

	}

	@Then("verify filtered event {string} is displayed")
	public void verify_filtered_event_is_displayed(String event)  throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.eventFilterResult(event);
		Thread.sleep(3500);
	}

	@Then ("user clicks on clear filters button")
	public void user_clicks_on_clear_filters_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.clearFiltersResult();
		Thread.sleep(5000);
	}



	@Then("user clicks event filter button and selects {string} {string} and {string} {string}")
	public void user_clicks_event_filter_button_and_selects(String filterOption1,String event1,String filterOption2, String event2) throws IOException, ParseException, InterruptedException{

		String mainWindowHandle = driver.getWindowHandle();  //save main update parameters window, then open filter window

		Thread.sleep(1500);
		jetStudio_UpdateParameters.eventFilterMenuCommandTwoOptions(filterOption1,event1, filterOption2, event2);
		Thread.sleep(500);

		driver.switchTo().window(mainWindowHandle); //go back to main update parameters window


	}

	//MODIFY EVENT FEE
	@Then("user clicks on event edit button and event fee is updated")
	public void user_clicks_on_event_edit_button_and_event_fee_is_updated() throws IOException, ParseException, InterruptedException{
		Thread.sleep(2000);
		jetStudio_UpdateParameters.eventEdit();
		Thread.sleep(3500);
		jetStudio_UpdateParameters.addEventDetailsFee("900");
		Thread.sleep(3000);
		jetStudio_UpdateParameters.eventUpdateCommand();
		Thread.sleep(3000);
	}

	//FILTER MODIFIED EVENT
	@Then("user clicks on event filter button and selects {string} event fee")
	public void user_clicks_on_event_filter_button_and_selects_event_fee(String fee) throws IOException, ParseException, InterruptedException{
		String mainWindowHandle = driver.getWindowHandle();  //save main update parameters window, then open filter window

		Thread.sleep(1500);
		jetStudio_UpdateParameters.eventFeeFilterMenu(fee);
		Thread.sleep(1000);

		driver.switchTo().window(mainWindowHandle);
	}

	@Then("verify filtered event fee {string} is displayed")
	public void verify_filtered_event_fee_is_displayed(String fee) throws IOException, ParseException, InterruptedException{
		Thread.sleep(1500);
		jetStudio_UpdateParameters.eventFilterModifiedFeeResult(fee);

	}

	//REMOVE EVENT
	@Then("user clicks on event remove button")
	public void user_clicks_on_event_remove_button() throws IOException, ParseException, InterruptedException{
		Thread.sleep(3000);
		jetStudio_UpdateParameters.eventRemoveCommand();
		Thread.sleep(3000);

	}

	//FILTER DELETED EVENT
	@Then("user clicks on event filter button and verify event {string} is deleted")
	public void user_clicks_on_event_filter_button_and_verify_event_is_deleted(String event) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.eventFilterDeleted(event);
		Thread.sleep(1500);

	}

	@Then("verify filtered event does not display {string}")
	public void verify_filtered_event_is_not_displayed(String event)  throws IOException, ParseException, InterruptedException{
		//jetStudio_UpdateParameters.eventFilterResultRemoved(event);
		jetStudio_UpdateParameters.FilterResultRemoved(event);

	}


	//REMOVE ALL
	@Then("user clicks remove all button")
	public void user_clicks_remove_all_button() throws IOException, ParseException, InterruptedException{
		Thread.sleep(3000);
		jetStudio_UpdateParameters.RemoveAllCommand();
		Thread.sleep(3000);


	}

	@Then("user clicks cancel all button")
	public void user_clicks_cancel_all_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.RemoveAllTheCancelButton();
		Thread.sleep(3000);
	}

	@Then("user confirms removal and verify returned message")
	public void user_confirms_removal_and_verify_returned_message() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.RemoveAllRemoveButton();
		Thread.sleep(3000);

		
		jetStudio_UpdateParameters.popupMessage();
		Thread.sleep(3000);
		
	}



	//COPY PARAMETERS CLICKING AT ALL BUTTONS
	@Then("user clicks copy parameters button")
	public void user_clicks_copy_parameters_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.copyparametersCommand();
	}


	@Then("user clicks copytoac button")
	public void user_clicks_copytoac_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.copytoacCommand();
		Thread.sleep(500);
	}

	@Then ("user verifies returned message when no rows selected")
	public void user_verifies_returned_message_when_no_rows_selected() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.popupMessageCopyAc();
		Thread.sleep(5000);
	}

	@Then("user clicks select all button")
	public void user_clicks_select_all_button() throws IOException, ParseException, InterruptedException{
		Thread.sleep(1000);
		jetStudio_UpdateParameters.selectallCommand();
		Thread.sleep(3000);
	}


	@Then("user clicks clear all button")
	public void user_clicks_clear_all_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.clearallCommand();
		Thread.sleep(2500);
	}


	@Then("user clicks cancel button")
	public void user_clicks_cancel_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.cancelallCommand();
		Thread.sleep(4000);
	}

	@Then ("user clicks copytoac button and copies the rows to {string} {string}")
	public void user_clicks_copytoac_button_and_copies_the_rows_to(String vendorcp, String category ) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.copyParametersAllCommand(vendorcp, category);
		Thread.sleep(500);
		jetStudio_UpdateParameters.clickCopyParametersButton();
		jetStudio_UpdateParameters.popupMessageCopyAcSuccessfull(); //**LILIAN 05_02_25


	}

	@Then ("verify copy parameters confirmation message is displayed")
	public void verify_copy_parameters_confirmation_message_is_displayed() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.popupMessageCopyAcSuccessfull();
		Thread.sleep(5000);
	}

	@Then ("copy parameters to {string} {string} and verify message maximum rows")
	public void copy_parameters_to_and_verify_message_maximum_rows(String vendorcp, String category) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.popupMessageMaxRows(vendorcp, category);
		Thread.sleep(1000);
	}

	@Then ("user copies to multiple categories")
	public void user_copies_to_multiple_categories() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.copyToMultipleCategories();
		Thread.sleep(500);
	}

	//change dashboard to category - CL605
	@Then ("change to {string} and {string} category in dashboard")
	public void change_to_vendor_and_category_in_dashboard(String vendorAux, String categoryAux) throws IOException, ParseException, InterruptedException{

		Thread.sleep(5500);
		jetStudio_UpdateParameters.dashStartDate();
		Thread.sleep(1000);
		jetStudio_UpdateParameters.dashEndDate();
		Thread.sleep(1000);

		jetStudio_UpdateParameters.dashVendor(vendorAux);
		Thread.sleep(5000);

		jetStudio_UpdateParameters.dashCategory(categoryAux);
		Thread.sleep(5000);

		jetStudio_UpdateParameters.applyButton();
		Thread.sleep(5000);

	}


	//*********************************************EVENTBAN TAB**********************************************************



	//ADD EVENT BAN
	@Then("user clicks on event ban tab")
	public void user_clicks_on_event_ban_tab() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.clickEventBanTab();
		Thread.sleep(1500);

	}

	@Then ("user clicks on event ban add new button")
	public void user_clicks_on_event_ban_add_new_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.clickAddNewButtonEventBan();
		Thread.sleep(1500);
	}

	@Then ("user enters event ban details {string} {string} then event ban is added")
	public void user_enters_event_ban_details_then_event_ban_is_added(String name, String location) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.addEventBanDetailsPart1(name, location);
		Thread.sleep(5000);


		if (name.equals(("EventBan3"))) {
			jetStudio_UpdateParameters.eventCloseDialog();
		}

	}

	//FILTER ADDED EVENT BAN
	@Then("user clicks on event ban filter button and selects {string} event")
	public void user_clicks_on_event_ban_filter_button_and_selects_event(String event) throws IOException, ParseException, InterruptedException{
		Thread.sleep(1500);
		String mainWindowHandle = driver.getWindowHandle();  //save main update parameters window, then open filter window

		Thread.sleep(1500);
		jetStudio_UpdateParameters.eventBanFilterMenu(event);
		Thread.sleep(1500);

		driver.switchTo().window(mainWindowHandle); //go back to main update parameters window
		Thread.sleep(2000);

	}

	@Then("verify filtered banned event {string} is displayed")
	public void verify_filtered_banned_event_is_displayed(String event) throws IOException, ParseException, InterruptedException{
		Thread.sleep(2500);
		jetStudio_UpdateParameters.eventFilterResult(event);
		Thread.sleep(2500);


	}

	//MODIFY EVENT BAN
	@Then("user clicks on event edit button and modify event ban name")
	public void user_clicks_on_event_edit_button_and_modify_event_ban_name() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.eventEdit();
		Thread.sleep(3000);
		jetStudio_UpdateParameters.updateEventBanName();
		Thread.sleep(3000);
		jetStudio_UpdateParameters.eventUpdateCommand();
		Thread.sleep(3500);
	}



	//*********************************************AIRPORT TAB**********************************************************


	//ADD AIRPORT
	@Then("user clicks on airport tab")
	public void user_clicks_on_airport_tab() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.clickAirportTab();
		Thread.sleep(1500);

	}

	@Then("user enters airport details {string} {string} then airport is added")
	public void user_enters_airport_details_then_airport_is_added(String airport, String fee) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.addAirportDetails(airport, fee);
		Thread.sleep(1500);
	}

	@Then ("user enters airport details {string} {string} and selects discard charges button")
	public void user_enters_airport_details_and_selects_discard_charges_button(String airport, String fee) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.airportNameCommand(airport);
		Thread.sleep(1500);

		jetStudio_UpdateParameters.airportFeeCommand(fee);
		Thread.sleep(1500);

		jetStudio_UpdateParameters.airportDiscardChangesCommand();
		Thread.sleep(1500);
	}


	@Then ("user enters invalid airport {string}")
	public void user_enters_invalid_airport(String airport) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.airportInvalidIcao(airport);
		Thread.sleep(1500);
	}


	@Then ("user enters blank airport {string}")
	public void user_enters_blank_airport(String airport) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.airportBlankIcao(airport);
		Thread.sleep(1500);
	}

	//FILTER ADDED AIRPORT
	@Then("user clicks on airport filter button and selects {string} and {string}")
	public void user_clicks_on_airport_filter_button_and_selects_and(String dropAux, String airport) throws IOException, ParseException, InterruptedException{
		String mainWindowHandle = driver.getWindowHandle();  //save main update parameters window, then open filter window

		Thread.sleep(1500);
		jetStudio_UpdateParameters.aiportFilterMenuCommand(dropAux,airport);
		Thread.sleep(500);

		driver.switchTo().window(mainWindowHandle); //go back to main update parameters window

	}

	@Then("verify filtered airport {string} is displayed")
	public void verify_filtered_airport_is_displayed(String airport)  throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.eventFilterResult(airport);

	}


	//MODIFY AIRPORT FEE
	@Then("user clicks on airport edit button and airport fee is updated")
	public void user_clicks_on_airport_edit_button_and_airport_fee_is_updated() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.eventEdit();
		Thread.sleep(2000);
		jetStudio_UpdateParameters.airportModifyFee("111");
		Thread.sleep(2000);

	}

	//MODIFY FROM AIRPORT FEE TO BANNED
	@Then ("user clicks on airport edit button and change from airport fee to banned")
	public void user_clicks_on_airport_edit_button_and_change_from_airport_fee_to_banned() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.eventEdit();
		Thread.sleep(2000);

		jetStudio_UpdateParameters.airportModifyFromFeeToBanned();
		Thread.sleep(2000);
	}

	@Then ("verify record is overriden")
	public void verify_record_is_overriden() throws IOException, ParseException, InterruptedException{

		//jetStudio_UpdateParameters.eventEdit();
		//Thread.sleep(2000);

		jetStudio_UpdateParameters.verifyBannedIsCheckedOn();
		Thread.sleep(2000);


	}


	//FILTER MODIFIED AIRPORT
	@Then ("user clicks on airport tab filter button and selects {string} airport fee")
	public void user_clicks_on_airport_tab_filter_button_and_selects_airport_fee(String fee) throws IOException, ParseException, InterruptedException{
		String mainWindowHandle = driver.getWindowHandle();  //save main update parameters window, then open filter window

		Thread.sleep(1500);

		jetStudio_UpdateParameters.airportFeeFilterMenu(fee);

		Thread.sleep(500);

		driver.switchTo().window(mainWindowHandle);
	}

	@Then ("verify filtered airport fee {string} is displayed")
	public void  verify_filtered_airport_fee_is_displayed(String fee) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.airportFeeFilterModifiedResult(fee);

	}

	//REMOVE AIRPORT
	@Then("user clicks on airport remove button")
	public void user_clicks_on_airport_remove_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.eventRemoveCommand();
		Thread.sleep(3000);

	}

	//FILTER DELETED AIRPORT
	@Then("verify airport {string} is not displayed")
	public void verify_airport_is_not_displayed(String airport)  throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.FilterResultRemoved(airport);

	}

	@Then ("user scrolls page down and up")
	public void user_scrolls_page_down_and_up() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.scrollPageDownAndUp();
		Thread.sleep(1000);

	}

	@Then ("ascending sort is verified")
	public void ascending_sort_is_verified() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.ascendingSort();
		Thread.sleep(1000);
	}

	@Then ("click on airport arrow descending")
	public void click_on_airport_arrow_descending() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.clickOnArrowDown();
		Thread.sleep(1000);
	}

	@Then ("descending sort is verified")
	public void descending_sort_is_verified() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.descendingSort();
		Thread.sleep(1000);
	}


	/*public void selectMultipleOptions() {
	        Select select = new Select(dropdownElement);
	        if (select.isMultiple()) {
	            select.selectByVisibleText("Option 1");
	            select.selectByVisibleText("Option 2");
	            // Alternatively, use selectByValue or selectByIndex
	            // select.selectByValue("value1");
	            // select.selectByIndex(1);
	        } else {
	            System.out.println("The dropdown is not a multi-select dropdown.");
	        }
	    }*/



	//*********************************************COUNTRY TAB**********************************************************


	@Then("user clicks on country tab")
	public void user_clicks_on_country_tab() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.clickCountryTab();

		Thread.sleep(1500);

	}

	@Then("user enters country details {string} {string} and country is added")
	public void user_enters_country_details_and_country_is_added(String country, String fee) throws IOException, ParseException, InterruptedException{

		jetStudio_UpdateParameters.addCountryDetails(country, fee);
		Thread.sleep(1500);


		if (country.equals(("AL"))) {
			jetStudio_UpdateParameters.eventCloseDialog();
		}


	}

	@Then ("user enters country details {string} {string} and selects cancel button")
	public void user_enters_country_details_and_selects_cancel_button(String country, String fee) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.countryNameCommand(country);
		Thread.sleep(2000);

		jetStudio_UpdateParameters.countryFeeCommand(fee);
		Thread.sleep(3000);

		jetStudio_UpdateParameters.countryCancelCommand();
		Thread.sleep(1500);

	}

	@Then ("user enters blank country {string}")
	public void user_enters_blank_country(String country) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.countryBlank(country);
		Thread.sleep(1500);
	}


	//FILTER ADDED COUNTRY
	@Then("user clicks on country filter button and selects {string} and {string}")
	public void user_clicks_on_country_filter_button_and_selects_and(String dropAux,String country) throws IOException, ParseException, InterruptedException{
		String mainWindowHandle = driver.getWindowHandle();  //save main update parameters window, then open filter window

		Thread.sleep(1500);
		jetStudio_UpdateParameters.countryFilterMenuCommand(dropAux,country);
		Thread.sleep(500);

		driver.switchTo().window(mainWindowHandle); //go back to main update parameters window

	}

	@Then("verify filtered country name {string} is displayed")
	public void verify_filtered_country_name_is_displayed(String country)  throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.eventFilterResult(country);
		Thread.sleep(2000);
	}



	//MODIFY CONTRY FEE
	@Then("user clicks on country edit button and country is updated")
	public void user_clicks_on_country_edit_button_and_country_is_updated() throws IOException, ParseException, InterruptedException{
		Thread.sleep(1000);
		jetStudio_UpdateParameters.eventEdit();
		Thread.sleep(2500);
		jetStudio_UpdateParameters.countryModifyFee("55");
		Thread.sleep(2500);

	}

	//FILTER MODIFIED COUNTRY
	@Then("user clicks on country fee filter button and selects {string} fee")
	public void user_clicks_on_country_fee_filter_button_and_selects_fee(String fee) throws IOException, ParseException, InterruptedException{
		String mainWindowHandle = driver.getWindowHandle();  //save main update parameters window, then open filter window

		Thread.sleep(1500);
		jetStudio_UpdateParameters.countryFeeFilterMenu(fee);
		Thread.sleep(500);

		driver.switchTo().window(mainWindowHandle);
	}

	@Then("verify filtered country fee {string} is displayed")
	public void verify_filtered_country_fee_is_displayed(String fee) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.countryFeeFilterModifiedResult(fee);

	}


	//REMOVE COUNTRY
	@Then("user clicks on country remove button")
	public void user_clicks_on_country_remove_button() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.eventRemoveCommand();
		Thread.sleep(3000);

	}

	//FILTER DELETED COUNTRY
	@Then("verify {string} is not displayed")
	public void verify_is_not_displayed(String name)  throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.FilterResultRemoved(name);

	}


	//CALLOUT DAYS
	@Then("user clicks on callout days")
	public void user_clicks_on_callout_days() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.calloutdaysClick();
		Thread.sleep(100);
	}



	@Then("user clicks on arrow up")
	public void user_clicks_on_arrow_up() throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.calloutArrowUpCommand();
		Thread.sleep(100);
	}


	@Then("user clicks on arrow down")
	public void user_clicks_on_arrow_down()  throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.calloutArrowDownCommand();
	}

	@Then("user updates callout value to {string}")
	public void user_updates_callout_value(String aux) throws IOException, ParseException, InterruptedException{

		jetStudio_UpdateParameters.calloutAssignment(aux);
		Thread.sleep(1500);

	}

	@Then ("verify filtered updated callout {string} is displayed")
	public void  verify_filtered_updated_callout_is_displayed(String numberAux) throws IOException, ParseException, InterruptedException{
		jetStudio_UpdateParameters.calloutVerifyValue(numberAux);
		Thread.sleep(1500);
	}

	//LILIAN - NEW
	@And ("user selects {string} for membershiptype dropdown")
	public void user_selects_for_membershiptype_dropdown(String membershiptype)throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectMembershipType(membershiptype);
	}
	@Then ("user clicks view button")
	public void user_clicks_view_button() throws IOException, ParseException, InterruptedException {
		Thread.sleep(1500);//LILIAN 05_07_25
		jetStudio_Calculator.clickViewButton();

	}

 
	@Then ("user selects international categories and verifies {string} fee {int} {string}")
	public void user_selects_international_categories_and_verifies_fee(String feeType, int pax, String fee) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectInternationalCategoriesAndVerifiesFee(feeType, pax, fee);
	}



	@And ("select Only XoLite Category")
	public void select_Only_XoLite_Category() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectOnlyXoLiteCategory();
	}

	/*LILIAN 04_30_25
	@And ("select Only XoSmid Category")
	public void select_Only_XoSmid_Category() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectOnlyXoSmidCategory();
	}
	LILIAN*/

	@And ("select Only NregSmid Category")
	public void select_Only_NregSmid_Category() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectOnlyNregSmidCategory();
	}

	@And ("select Only NregUrl Category")
	public void select_Only_NregUrl_Category() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectOnlyNregUrlCategory();
	}

/*  **LILIAN 04_30_25
	 public void select_Only_xojet_lite_and_xojet_smid_Categories() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectOnlyXoLiteAndXoSmidCategories();
	}
	LILIAN */

	@And ("select Only Nregs Category")
	public void select_Only_Nregs_Category() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectOnlyNregsCategory();
	}

	/* LILIAN 04_30_25
	@And ("select Only XoSmid and Nregs Category")
	public void select_Only_XoSmid_And_Nregs_Category() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectOnlyXoSmidAndNregsCategory();
	}
	LILIAN */

	@And ("select Only AHPhenom300 Category")
	public void select_Only_AHPhenom300_Category() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectOnlyAHPhenom300Category();
	}

	@And ("select Only AHLegacy600 Category")
	public void select_Only_AHLegacy600_Category() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectOnlyAHLegacy600Category();

	}

	//LILIAN 07-16-25 begin
	@And("select Only AHL600 and AHL650 Categories")
	public void select_Only_AHL600_and_AHL650_Categories() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectOnly_AHL600_AHL650category();

	}
	//LILIAN 07-16-25 end
	
	//LILIAN 08-03-25 begin
		@Then("select all AH categories and verify Wifi fee was removed")
		public void select_all_AH_categories_and_verify_Wifi_fee_was_removed() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.select_all_AH_categories_and_verify_Wifi_fee_was_removed();

		}
		
		@Then("select all VJ categories and verify Wifi fee is displayed")
		public void select_all_VJ_categories_and_verify_Wifi_fee_is_displayed() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.select_all_VJ_categories_and_verify_Wifi_fee_is_displayed();
		}

		@And ("select Only CL350 Category")
		public void select_Only_CL350_Category() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.selectOnlyCL350Category();
		}
		
		@Then ("verify it returns Stacking Discount for CL350")
		public void verify_it_returns_Stacking_Discount_for_CL350() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyStackingDiscountforCL350();
		}
		
		@Then("verify it returns fuel Stop {double} for only one leg")
		public void verify_it_returns_fuel_Stop_for_only_one_leg(double fee) throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyReturnsFuelStopForOnlyOneLeg(fee);
		}
		//LILIAN 08-03-25 end

		

	//SELECT MEMBERSHIP
	/* LILIAN 05_07_25
	@And ("select Only XoLite Category for Select_Signature Memberships")
	public void select_Only_XoLite_Category_for_Select_Signature_Memberships() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectOnlyXoLiteCategoryforSelectSignatureMembership();
	}
	LILIAN */
	
	/* LILIAN 04_30_25
	@And ("select Only CitationX Category for Select_Signature Memberships")
	public void select_Only_CitationX_Category_for_Select_Signature_Memberships() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectOnlyCitationXCategoryforSelectSignatureMembership();
	}
	LILIAN */


	/*@And("verify NonMember fee {string} for the domestic categories")
	public void verify_NonMember_fee_for_the_domestic_categories(String fee) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyNonMemberFees(fee);

	}

	@Then ("verify NonMember fee is not displayed for xo membership")
	public void verify_NonMember_fee_is_not_displayed_for_xo_membership() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyNonMemberFeeIsNotDisplayedForXoMembership();
	}*/



	@And("verify ETS fee {string} for the domestic categories")
	public void verify_ETS_fee_for_the_domestic_categories(String fee) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyEtsFees(fee);
	}

	@And("verify ETS fee {string} for the domestic categories trip {int} round trip")
	public void verify_ETS_fee_for_the_domestic_categories_trip_round_trip(String fee, int trip) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyEtsFeesRoundTrip(fee, trip);
	}

	@Then ("verify Ets fee is not displayed for Xojet")
	public void verify_Ets_fee_is_not_displayed_for_Xojet() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyEtsforXojet();
	}

	@And ("user selects {int} number")
	public void setPax(int passenger) throws IOException, ParseException, InterruptedException {
		//@And ("user selects {string} number")
		//public void setPax(String passenger) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.setPax(passenger);
	}

	@Then("verify Segment fee {string} for the domestic categories")
	public void verify_Segment_fee_for_the_domestic_categories(String fee) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifySegmentFeesDomestic(fee);
	}

	@Then ("verify Segment fee {double} for the domestic categories Max Pax")
	public void verify_Segment_fee_for_the_domestic_categories_Max_Pax(double fee) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifySegmentFeesDomesticMaxPax(fee);
	}


	@Then("verify Portugal fee {string} for the domestic categories")
	public void verify_Portugal_fee_for_the_domestic_round_trip_categories(String fee1) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyPortugalFeesDomestic(fee1);
	}

	@Then ("user selects aircraft categories {string}")
	public void user_selects_aircraft_categories(String category) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.userSelectsAircraftCategories(category);
	}



	@Then("verify whitelist airports for domestic categories")
	public void verify_whitelist_airports_for__domestic_round_trip_categories() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyWhitelistAirportsDomestic();
	}

	@Then("verify overnight fee {string} for international category")
	public void verify_overnight_fee_for_international_category(String fee) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyOvernightFeeInternational(fee);
	}



	@Then("verify it returns price for the categories")
	public void verify_it_returns_price_for_the_categories() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyReturnsPrice();
	}


	@And("user selects Firm Toggle")
	public void user_selects_Firm_Toggle() throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.selectFirmToggle();
	}

	@Then("verify Firm fee {string} for the domestic categories")
	public void verify_Firm_fee_for_the_domestic_categories(String fee) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyFirmFeesDomestic(fee);
	}

	@Then("verify Service fee {string} for the domestic categories trip {int}")
	public void verify_Service_fee_for_the_domestic_categories_trip(String fee, int trip) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyServiceFeesDomestic(fee,trip);
	}

	//LILIAN 07-03-25 begin
	@Then("verify Xo Service Charge {string} for the domestic categories trip {int}")
	public void verify_Xo_Service_Charge_for_the_domestic_categories_trip(String fee, int trip) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyXoServiceChargeDomestic(fee,trip);
	}

	@Then("verify Other Aircraft fee {string}")
	public void verify_Other_Aircraft_fee(String fee) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyOtherAircraftFee(fee);
	}

	//LILIAN 06-04-25 begin
	@Then ("verify Origin Destination Airports with UI and MongoDB {string} {string}")
	public void verify_Origin_Destination_Airports_Fees(String arrivedepart, String test)  throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyOriginDestinationAirportsFees(arrivedepart, test);
	}
	@Then ("verify UI matches State to State Excel base price {string}")
	public void verify_UI_matches_State_to_State_Excel_base_price(String baseprice)  throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyUIMatchesStateToStateExcelBasePrice(baseprice);
	}	
	
	@Then ("verify State to State Excel base price is not covered {string}") 
	public void verify_State_to_State_Excel_baseprice_is_not_covered(String total) throws IOException, ParseException, InterruptedException {
		jetStudio_Calculator.verifyStateToStateBasepriceIsNotCovered(total);
	}
	//LILIAN 06-04-25 end

	//LILIAN 06-26-25 begin
		@And ("select Only CL604 and CL605 categories")
		public void select_Only_CL604_CL605_categories() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.selectOnly_CL604_CL605_categories();
		}
		
		@And ("select Only AH L650 category")
		public void select_Only_AHL650_category() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.selectOnly_AHL650_category();
		}
		
		@And ("select Only AH L600 category")
		public void select_Only_AHL600_category() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.selectOnly_AHL600_category();
		}
		
		@And ("select Only AHPhenom300 and AH CITATION XLS+ Categories")
		public void select_Only_AHPhenom300_AHCitationXLS_Categories() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.selectOnlyAHPhenom300AHCitationXLSCategories();
		}
		
		@Then("verify Flight Time Outside The Aircraft Range Message {string} {string}")
		public void verifyFlightTimeOutsideTheAircraftRangeMessage(String depart, String message) throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyFlightTimeOutsideAircraftRangeMessage(depart, message);
		}
		
	/*	@Then("verify Outside The Aircraft Range Message {string} {string}")
		public void verifyOutsideTheAircraftRangeMessage(String depart, String message) throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyOutsideAircraftRangeMessage(depart, message);
		}*/
		
		 @Then ("verify it returns Out of Service Area for AH LEGACY 600 message")
		 public void  verifyItReturnsOutofServiceAreaforAHLEGACY600message() throws IOException, ParseException, InterruptedException {
			 jetStudio_Calculator.verifyReturnsOutOfServiceAreaAHL600msg();
		 }
		 
	 //LILIAN 07-16-25 begin
		/*
		 @Then("verify it returns fuel Stop")
		public void verify_it_returns_fuel_Stop() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyReturnsFuelStop();
		}*/
		 
		@Then("verify it returns fuel Stop {double}")
		public void verify_it_returns_fuel_Stop(double fee) throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyReturnsFuelStop(fee);
		}
		
		@Then ("verify it does not return fuel Stop fee")
		public void verify_does_not_return_Fuel_Stop_fee() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyDoesNotReturnFuelStop();
		}
		 //LILIAN 07-16-25 end
		
		@ And ("user sets date and time for stacking discount {string} {string} {string}")
		public void user_sets_date_and_time_for_stacking_discount(String hour, String min,String period) throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.setDepartureDateTimeForStackingDiscount(hour, min, period);
		}
		
		@Then ("verify it returns Stacking Discount")
		public void verify_it_returns_Stacking_Discount() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyStackingDiscount();
		}
		
		@Then ("verify it does not return Stacking Discount")
		public void verify_it_does_not_return_Stacking_Discount() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyDoesNotReturnStackingDiscount();
		}
	
		@When("user enters {string} {string} {string} {string} {string} and {string} {string} {string} {string} {string} for stacking discount")
		public void user_enters_depart_and_arrive_and_depart2_and_arrive2_for_stackingdiscount(String depart,String arrive, String hour1, String min1, String period1, String depart2, String arrive2,String hour2, String min2, String period2) throws IOException, ParseException, InterruptedException {
			driver.navigate().refresh();
			Thread.sleep(500);
			
			jetStudio_Calculator.setOrigin(depart);
			jetStudio_Calculator.setDestination(arrive);
			jetStudio_Calculator.setDepartureDateTimeForStackingDiscount(hour1, min1, period1);
			Thread.sleep(300);
			jetStudio_Calculator.setAddRtLeg();
			jetStudio_Calculator.setDepartureDateTimeForStackingDiscount(hour1, min1, period1);
		}
		
		@And ("select Only AHLegacy650 Category")
		public void select_Only_AHLegacy650_Category() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.selectOnlyAHLegacy650Category();

		}	
//LILIAN 06-26-25 end

//LILIAN 07-26-25 begin
		@Then ("verify subtotal and total values are different")
		public void verify_subtotal_and_total_values_are_different() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifySubtotalAndTotalValuesAreDifferent();
		}
		
		@Then ("verify subtotal and total values are the same")
		public void verify_subtotal_and_total_values_are_the_same() throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifySubtotalAndTotalValuesAreTheSame();
		}
		
		@Then ("verify International Head Tax {double}")
		public void verify_International_Head_tax(double tax) throws IOException, ParseException, InterruptedException {
			jetStudio_Calculator.verifyInternationalHeadTax(tax);
		}
		
		//LILIAN 07-26-25 end
}




