package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
	public WebDriver ldriver;
	WaitHelper waitHelper;
	
	public SearchCustomerPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
		waitHelper= new WaitHelper(ldriver);
	}
	
	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;
	
	@FindBy(how = How.ID, using = "SearchLastActivityFrom")
	@CacheLookup
	WebElement txtLastActivityFrom;
	
	@FindBy(how = How.ID, using = "SearchLastActivityTo")
	@CacheLookup
	WebElement txtLastActivityTo;
	
	@FindBy(how = How.ID, using = "SearchCompany")
	@CacheLookup
	WebElement txtCompanyName;
	
	@FindBy(how = How.ID, using = "SearchMonthOfBirth")
	@CacheLookup
	WebElement txtMonthOfBirth;
	
	@FindBy(how = How.ID, using = "SearchDayOfBirth")
	@CacheLookup
	WebElement txtDayOfBirth;
	
	@FindBy(how = How.ID, using = "SearchIpAddress")
	@CacheLookup
	WebElement txtIPAddress;
	
	
	@FindBy(how = How.XPATH, using = "//input[@aria-describedby='SelectedCustomerRoleIds_taglist']")
	@CacheLookup
	WebElement txtCustomerRoles;
	
	@FindBy(how = How.XPATH, using = "//span[@class='k-icon k-i-close']")
	@CacheLookup
	WebElement closeRegistered;
	
	@FindBy(how = How.XPATH, using = "//li[text()='Administrators']")
	@CacheLookup
	WebElement listItemAdministrators;
	
	@FindBy(how = How.XPATH, using = "//li[text()='Forum Moderators']")
	@CacheLookup
	WebElement listItemForumModerators;
	
	@FindBy(how = How.XPATH, using = "//li[text()='Guests']")
	@CacheLookup
	WebElement listItemGuests;
	
	@FindBy(how = How.XPATH, using = "//li[text()='Registered']")
	@CacheLookup
	WebElement listItemRegistered;
	
	@FindBy(how = How.XPATH, using = "//li[text()='Vendors']")
	@CacheLookup
	WebElement listItemVendors;
	
	
	@FindBy(how = How.ID, using = "SearchRegistrationDateFrom")
	@CacheLookup
	WebElement txtRegistrationDateFrom;
	
	@FindBy(how = How.ID, using = "SearchRegistrationDateTo")
	@CacheLookup
	WebElement txtRegistrationDateTo;
	
	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	@CacheLookup
	WebElement table;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']/tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']/tbody/tr/td")
	@CacheLookup
	List<WebElement> tableColumns;
	
	public void setEmail(String email) throws InterruptedException {
		Thread.sleep(3000);
		//waitHelper.WaitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFName(String fname) throws InterruptedException {
		Thread.sleep(3000);
		//waitHelper.WaitForElement(txtFirstName, 30);
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}
	
	public void setLName(String lname) throws InterruptedException {
		Thread.sleep(3000);
		//waitHelper.WaitForElement(txtLastName, 30);
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}
	
	public void clickSearch() throws InterruptedException {
		btnSearch.click();
		Thread.sleep(3000);
		//waitHelper.WaitForElement(btnSearch, 30);
	}
	
	public int getNoOfRows() {
		return(tableRows.size());
	}
	public int getNoOfColumns() {
		return(tableColumns.size());
	}
	public boolean searchCustomerByEmail(String email) {
		boolean flag=false;
		
		for(int i=1; i<=getNoOfRows(); i++) {
			String emailId=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
			System.out.println(emailId);
			
			if(emailId.equals("victoria_victoria@nopCommerce.com")) {
				flag=true;
			}
		}
		return flag;
	}
	
	public boolean searchCustomerByName(String name) {
		boolean flag=false;
		
		for(int i = 1; i<=getNoOfRows(); i++) {
			String name1=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();
			String names[]=name1.split(" "); //separating fname and lname.
			if(names[0].equals("Victoria") && names[1].equals("Terces")) {
				flag=true;
			}
		}
		return flag;
	}
}