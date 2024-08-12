package com.comcast.crm.objectrepoUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgLookUpIcon 
{
	
	public OrgLookUpIcon(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "(//img[@title='Select'])[1]")
	private WebElement orgLookUpIcon;
	
	@FindBy(name="search_text")
	private WebElement searchTxtFld;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	public WebElement getSearchTxtFld() {
		return searchTxtFld;
	}

	public WebElement getSeachBtn() {
		return searchBtn;
	}

	public WebElement getOrgLookUpIcon() {
		return orgLookUpIcon;
	}
	
	public void selectOrg(String orgName , WebDriver driver)
	{
		searchTxtFld.sendKeys(orgName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
	}

}
