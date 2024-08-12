package com.comcast.crm.objectrepoUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage 
{
	
	WebDriver driver; 
	public CreateNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver; 
		PageFactory.initElements(driver ,this);
	}
	@FindBy(name="accountname")
	private WebElement orgNameEdit;
	
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement industryDropDown;
	
	@FindBy(id ="phone")
	private WebElement phoneTxtFld;
	
	
	
	public WebElement getPhoneTxtFld() {
		return phoneTxtFld;
	}

	public void setPhoneTxtFld(WebElement phoneTxtFld) {
		this.phoneTxtFld = phoneTxtFld;
	}
	@FindBy(name="accounttype")
	private WebElement typeDropDown;

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName , String phoneNo)
	
	{
		orgNameEdit.sendKeys(orgName);
		phoneTxtFld.sendKeys(phoneNo);
		saveBtn.click();
		
	}
    public void createOrg(String orgName , String industry , String type)
	
	{
		orgNameEdit.sendKeys(orgName);
		Select sel=new Select(industryDropDown);
		sel.selectByVisibleText(industry);
  		
		Select sel1=new Select(typeDropDown);
  		sel1.selectByVisibleText(type);
  		saveBtn.click();
		
		
	}
    
    
   

}
