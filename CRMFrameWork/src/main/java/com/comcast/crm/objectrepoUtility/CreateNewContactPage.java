package com.comcast.crm.objectrepoUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	
	WebDriver driver; 
	public CreateNewContactPage(WebDriver driver)
	{
		this.driver=driver; 
		PageFactory.initElements(driver ,this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lastNameTxtFld;
	
	@FindBy(xpath = "(//img[@title='Select'])[1]")
	private WebElement contactLookUpIcon;
	
	@FindBy(name="support_start_date")
	private WebElement supportStartDate;
	
	@FindBy(name="support_end_date")
	private WebElement supportEndDate;

	public WebElement getOrgLookUpIcon() {
		return contactLookUpIcon;
	}

	public WebElement getLastNameTxtFld() {
		return lastNameTxtFld;
	}
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	
	public WebElement getSupportStartDate() {
		return supportStartDate;
	}

	public WebElement getSupportEndDate() {
		return supportEndDate;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
   public void createContact(String LastName )
	
	{
	   lastNameTxtFld.sendKeys(LastName);
       saveBtn.click();
		
	}
	
	
	
	
	

}
