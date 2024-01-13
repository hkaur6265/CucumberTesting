package stepDefinations;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {

	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public SearchCustomerPage searchCust;
	public Logger logger;
	public Properties configprop;
	
	//Created Random String for unique email id.
	public static String randomString() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return(generatedString1);
	}
}
