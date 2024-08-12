package com.comcast.crm.objectrepoUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage 
{
	
	WebDriver driver; 
	public OrganizationPage(WebDriver driver)
	{
		this.driver=driver; 
		PageFactory.initElements(driver ,this);
	}
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createNewOrgBtn;

	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	
	@FindBy(name="search_text")
	private WebElement searchTxtFld;
	
	@FindBy(name="submit")
	private WebElement searchNowBtn;
	
	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}

	public WebElement getSearchTxtFld() {
		return searchTxtFld;
	}
	
	@FindBy(name = "search_field")
	private WebElement searchDropDwon;
	
	public WebElement getSearchDropDwon() {
		return searchDropDwon;
	}
	
	

}
