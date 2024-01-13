package stepDefinations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import junit.framework.Assert;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass{
	@Before
	public void setup() throws IOException {
		//Reading config.properties file
		configprop=new Properties();
		FileInputStream configPropFile = new FileInputStream("config.properties");
		configprop.load(configPropFile);
		
		logger=Logger.getLogger("nopCommerce"); //Added logger
		PropertyConfigurator.configure("log4j.properties");//Added logger
		
		String br=configprop.getProperty("browser");
		if(br.equals("chrome")) {		
	    System.setProperty("webdriver.chrome.driver", configprop.getProperty("chromepath"));
	    driver = new ChromeDriver();
		}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.chrome.driver", configprop.getProperty("firefoxpath"));
		    driver = new FirefoxDriver();
		}else if(br.equals("edge")) {
			System.setProperty("webdriver.chrome.driver", configprop.getProperty("edgepath"));
		    driver = new EdgeDriver();
		}
	    logger.info("******Launching Browser******");
	}

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		
	    lp= new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String URL) {
		logger.info("******Opening URL******");
	   driver.get(URL);
	   driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("******Providing login details******");
	    lp.setUsername(email);
	    lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() {
		logger.info("******Started Login Proccess******");
	    lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {
		
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			logger.info("******Login Passed******");
			Assert.assertTrue(false);
		}else {
			logger.info("******Login Failed******");
			Assert.assertEquals(title, driver.getTitle());
		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
		logger.info("******Click on Logout Link******");
	    lp.clickLogout();
	    Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("******Closing Browser******");
	    driver.quit();
	}
	
	// Customers features step definations.
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
	    addCust=new AddCustomerPage(driver);
	    Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
	    addCust.clickOnCustomersMenu();
	}

	@When("click on Customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
	    Thread.sleep(3000);
	    addCust.customersMenuItem();
	}

	@When("click on Add New button")
	public void click_on_add_new_button() throws InterruptedException {
	    Thread.sleep(3000);
	    addCust.clickOnAddNew();
	}

	@Then("User can View Add new Customers Page")
	public void user_can_view_add_new_customers_page() throws InterruptedException {
		Thread.sleep(3000);
	    Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enter Customers info")
	public void user_enter_customers_info() throws InterruptedException {
		
		logger.info("******Adding New Customer******");
		logger.info("******Providing Customers Details******");
	    String email=randomString()+"@gmail.com";
	    addCust.setEmail(email);
	    addCust.setPassword("test123");
	    addCust.setFName("Harpreet");
	    addCust.setLName("Kaur");
	    addCust.setGender("Male");
	    addCust.setdob("12/10/1993");
	    addCust.setcompanyName("TCS");
	    addCust.setisTaxExempt();
	    addCust.setNewsLetter("Your store name");
	    addCust.setCustomerRoles("Vendors");
	    addCust.setManagerOfVendor("Vendor 2");
	    addCust.setAdminContent("This is for Testing..............");
	}

	@When("Click on Save button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("******Saving Customers Data******");
		addCust.clickOnSave();
	    Thread.sleep(3000);
	}

	@Then("User can view confirmaation message {string}")
	public void user_can_view_confirmaation_message(String string) {
	    Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully."));
	}
	
	// steps for searching a customer using email id..........
	
	@When("Enter customer Email")
	public void enter_customer_email() throws InterruptedException {
		
		logger.info("******Searching a Customer By using email id******");
	   searchCust=new SearchCustomerPage(driver);
	   searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
	    searchCust.clickSearch();
	    Thread.sleep(3000);
	}

	@Then("User should found Email in the Search Table")
	public void user_should_found_email_in_the_search_table() {
	    boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
	    
	    Assert.assertEquals(true, status);
	}
	@When("Enter customer FirstName")
	public void enter_customer_first_name() throws InterruptedException {
		
		logger.info("******Searching Customer By Name******");
	    searchCust=new SearchCustomerPage(driver);
	    searchCust.setFName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() throws InterruptedException {
	    searchCust.setLName("Terces");
	}

	@Then("User should found Name in the Search Table")
	public void user_should_found_name_in_the_search_table() {
		boolean status=searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}

}
