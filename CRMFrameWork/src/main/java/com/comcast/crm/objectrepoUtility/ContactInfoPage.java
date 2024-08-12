package com.comcast.crm.objectrepoUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	WebDriver driver;
	public ContactInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "dtlview_Last Name")
	private WebElement verifyLastNameTxtFld;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerText;
	
	public WebElement getHeaderText() {
		return headerText;
	}
	@FindBy(id = "mouseArea_Support End Date")
	private WebElement verifyEndDate;
	
	@FindBy(id = "mouseArea_Support Start Date")
	private WebElement verifyStartDate;
	
	
	public WebElement getVerifyStartDate() {
		return verifyStartDate;
	}

	public WebElement getVerifyLastNameTxtFld() {
		return verifyLastNameTxtFld;
	}
    
   public WebElement getVerifyEndDate() {
	return verifyEndDate;
   }
    }

   
   


	
	
	
	
				
	

