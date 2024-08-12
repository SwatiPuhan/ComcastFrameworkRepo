package com.comcast.crm.objectrepoUtility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage 
{
	@FindBy(xpath = "//input[@alt='Create Product...']")
	private WebElement createProductImgBtn;

	public WebElement getCreateProductImgBtn() {
		return createProductImgBtn;
	}

}
