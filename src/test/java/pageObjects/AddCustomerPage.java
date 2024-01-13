package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	public WebDriver ldriver;

	public AddCustomerPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	By lnkCustomers_menu=By.xpath("//ul[@role='menu']/li[4]/a[1]");
	By lnkCustomers_menuitem=By.xpath("//a[@href='/Admin/Customer/List']");
	
	By btnAddnew=By.xpath("//a[@href='/Admin/Customer/Create']");
	
	By txtEmail=By.xpath("//input[@id='Email']");
	By txtPassword=By.xpath("//input[@id='Password']");
	By txtFName=By.xpath("//input[@id='FirstName']");
	By txtLName=By.xpath("//input[@id='LastName']");
	
	By rdMaleGender=By.xpath("//input[@id='Gender_Male']");
	By rdFemaleGender=By.xpath("//input[@id='Gender_Female']");
	
	By dateOfBirth=By.xpath("//input[@id='DateOfBirth']");
	By txtcompanyName=By.xpath("//input[@id='Company']");
	
	By isTaxExempt=By.xpath("//input[@id='IsTaxExempt']");
	By selectNewsLetter=By.xpath("//input[@aria-describedby='SelectedNewsletterSubscriptionStoreIds_taglist']");	
	By listItemYourStoreName=By.xpath("//li[text()='Your store name']");
	By listItemTestStore2=By.xpath("//li[text()='Test store 2']");
	
	By txtCustomerRoles=By.xpath("//input[@aria-describedby='SelectedCustomerRoleIds_taglist']");
	By listItemAdministrator=By.xpath("//li[text()='Administrator']");
	By listItemForumModerators=By.xpath("//li[text()='Forum Moderators']");
	By listItemGuests=By.xpath("//li[text()='Guests']");
	By listItemKavitaBrahmankar=By.xpath("//li[text()='Kavita Brahmankar']");
	By listItemRegistered=By.xpath("//li[text()='Registered']");
	By listItemVendors=By.xpath("//li[text()='Vendors']");
	
	
	By managerOfVendor=By.xpath("//select[@id='VendorId']");
	
	By AdminComment=By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave=By.xpath("//button[@name='save']");
	
	//Action Methods
	
	public String getPageTitle() {
		return ldriver.getTitle();
	}
	
	public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCustomers_menu).click();
	}
	
	public void customersMenuItem() {
		ldriver.findElement(lnkCustomers_menuitem).click();
	}
	public void clickOnAddNew() {
		ldriver.findElement(btnAddnew).click();
	}
	public void setEmail(String email) {
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	public void setPassword(String password) {
		ldriver.findElement(txtPassword).sendKeys(password);
	}
	public void setFName(String fname) {
		ldriver.findElement(txtFName).sendKeys(fname);
	}
	public void setLName(String lname) {
		ldriver.findElement(txtLName).sendKeys(lname);
	}
	public void setcompanyName(String companyName) {
		ldriver.findElement(txtcompanyName).sendKeys(companyName);
	}
	public void setisTaxExempt() {
		ldriver.findElement(isTaxExempt).click();
	}
	public void setNewsLetter(String nl) throws InterruptedException {
		
		//JavascriptExecutor js = (JavascriptExecutor)ldriver;
		//js.executeScript("arguments[0].click()", selectNewsLetter);4
		Thread.sleep(3000);
		ldriver.findElement(selectNewsLetter).click();
		Thread.sleep(3000);
		
		WebElement listItem = null;
		
		if(nl.equals("Your store name")) {
			listItem=ldriver.findElement(listItemYourStoreName);
		}
		if(nl.equals("Test store 2")) {
			listItem=ldriver.findElement(listItemTestStore2);
		}
		listItem.click();
	}
	public void setCustomerRoles(String role) throws InterruptedException {
		if(!role.equals("Vendors")) {
			ldriver.findElement(By.xpath("//span[@title='delete']")).click();
		}
		Thread.sleep(3000);
		ldriver.findElement(txtCustomerRoles).click();
		
		WebElement listItem = null;
		
		Thread.sleep(3000);
		
		if(role.equals("Administrators")) {
			listItem=ldriver.findElement(listItemAdministrator);
		}else if(role.equals("Guest")) {
			listItem=ldriver.findElement(listItemGuests);
		} else if(role.equals("Forum Moderators")) {
			listItem=ldriver.findElement(listItemForumModerators);
		} else if(role.equals("Kavita Brahmankar")) {
			listItem=ldriver.findElement(listItemKavitaBrahmankar);
		} else if(role.equals("Registered")) {
			listItem=ldriver.findElement(listItemRegistered);
		} else if(role.equals("Vendors")) {
			listItem=ldriver.findElement(listItemVendors);
		} 
		
		listItem.click();
		
		//JavascriptExecutor js = (JavascriptExecutor)ldriver;
		//js.executeScript("arguments[0].click()", listItem);
	}
	public void setManagerOfVendor(String value) {
		Select drp= new Select(ldriver.findElement(managerOfVendor));
		drp.selectByVisibleText(value);
	}
	public void setGender(String gender) {
		if(gender.equals("Male")) {
			ldriver.findElement(rdMaleGender).click();
		}else if(gender.equals("Female")) {
			ldriver.findElement(rdFemaleGender).click();
		}else {
			ldriver.findElement(rdMaleGender).click();
		}
	}
	public void setdob(String dob) {
		ldriver.findElement(dateOfBirth).sendKeys(dob);
	}
	public void setAdminContent(String content) {
		ldriver.findElement(AdminComment).sendKeys(content);
	}
	public void clickOnSave() {
		ldriver.findElement(btnSave).click();
	}
	
}
