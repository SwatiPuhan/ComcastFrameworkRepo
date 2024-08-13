package com.comcast.crm.objectrepoUtility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage 
{
	@FindBy(xpath = "//input[@alt='Create Product...']")
	private WebElement createProductImgBtn;
	
	@FindBy(name = "search")
	private WebElement ele2;

	public WebElement getEle2() {
		return ele2;
	}

	public WebElement getCreateProductImgBtn() {
		return createProductImgBtn;
	}

}
