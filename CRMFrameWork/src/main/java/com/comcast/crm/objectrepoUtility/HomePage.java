package com.comcast.crm.objectrepoUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	
	WebDriver driver; 
	public HomePage(WebDriver driver)
	{
		this.driver=driver; 
		PageFactory.initElements(driver ,this);
	}
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement signOutLink;
	
	@FindBy(xpath = "//a[text()='Organizations']")
	private WebElement organizationLink;
	
	@FindBy(xpath = "//a[text()='Contacts']")
	private WebElement contactsLink;
	
	@FindBy(xpath = "//a[text()='Opportunities']")
	private WebElement OpportunitiesLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement CampaignLink;
	

	@FindBy(linkText = "More")
	private WebElement MoreLink;
	
	@FindBy(xpath = "(//td[@class='small']/img)[1]")
	private WebElement adminImg;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	
	


	public WebElement getContactLink() {
		return contactLink;
	}



	public WebElement getAdminImg() {
		return adminImg;
	}



	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getCampaignLink() {
		return CampaignLink;
	}

	public WebElement getSignOutPage() {
		return signOutLink;
	}

	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getOpportunitiesLink() {
		return OpportunitiesLink;
	}
	//business Logic
	public void navigateToCampaignPage()
	{
		Actions act=new Actions(driver);
		act.moveToElement(MoreLink).perform();
		CampaignLink.click();
	}
	public void navigateToContactModule()
	{
        contactLink.click();
	}
	
	public void logOut() {
	adminImg.click();
	signOutLink.click();
	
	
	}
}
	
		
	
	
	
	
		
	



