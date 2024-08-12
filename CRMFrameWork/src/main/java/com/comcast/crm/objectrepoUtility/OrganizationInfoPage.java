package com.comcast.crm.objectrepoUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage 
{
	
	
	public OrganizationInfoPage(WebDriver driver)
	{

		PageFactory.initElements(driver, this);
				
	}
	public WebElement getVerifyOrgName() {
		return verifyOrgName;
	}
	@FindBy(id="dtlview_Organization Name" )
	private  WebElement verifyOrgName;
		
	
	
	@FindBy(className ="dvHeaderText")
	private WebElement headerMsg;
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	
	
	@FindBy(id = "dtlview_Industry")
	private WebElement industryMsg;
	
	@FindBy(id = "dtlview_Type")
	private WebElement typeMsg;
	
	@FindBy(id = "dtlview_Phone")
	private WebElement phoneMsg;
	
	public WebElement getPhoneMsg() {
		return phoneMsg;
	}

	public WebElement getIndustryMsg() {
		return industryMsg;
	}

	public WebElement getTypeMsg() {
		return typeMsg;
	}

	
	

}
