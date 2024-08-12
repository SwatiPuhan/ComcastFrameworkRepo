package com.comcast.crm.contact_Assertion_Test;

import java.util.Set;
/**
 * 
 * 
 * @author gangu
 * 
 * 
 * 
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.baseClassUtility.BaseClass;
import com.comcast.crm.objectrepoUtility.ContactInfoPage;
import com.comcast.crm.objectrepoUtility.ContactPage;
import com.comcast.crm.objectrepoUtility.CreateNewContactPage;
import com.comcast.crm.objectrepoUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepoUtility.HomePage;
import com.comcast.crm.objectrepoUtility.OrgLookUpIcon;
import com.comcast.crm.objectrepoUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepoUtility.OrganizationPage;

public class CreateContactTest extends BaseClass {

	@Test(groups ="smokeTest")
	public void createContact() throws Throwable {

		String LastName = eLib.getDataFromExcel("contact", 6, 3) + jLib.getRandomNumber();

		
	    /* Navigate to contactModule */
		HomePage hp = new HomePage(driver);
		hp.navigateToContactModule();
		hp.getContactLink().click();

		// Navigate to contact create page
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// enter mandatory details & click on save button

		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createContact(LastName);

		// verify lastname Txt Fld
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actHeaderMsg=cip.getHeaderText().getText();
		boolean status=actHeaderMsg.contains(LastName);
		Assert.assertEquals(status, true);

		String actLastName = cip.getVerifyLastNameTxtFld().getText();
		SoftAssert assertobj=new SoftAssert();
		assertobj.assertEquals(actLastName, LastName);
	}

	@Test(groups = "RegressionTest")
	public void createContactWithSupportDate() throws Throwable {

//			String orgname = Eu.getdatafromExcelfile("org", 4, 2)+Ju.getRandomNumber();
		String LastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// step1:login to app
		HomePage hp = new HomePage(driver);
		hp.navigateToContactModule();
		hp.getContactLink().click();
		
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		CreateNewContactPage Cncp = new CreateNewContactPage(driver);
		Cncp.getLastNameTxtFld().sendKeys(LastName);
		Cncp.getSupportStartDate().clear();
		String Startdate = jLib.getSystemDateYYYYMMDD();
		String Enddate = jLib.getRequiredDateYYYYMMDD(30);
		Cncp.getSupportStartDate().clear();
		Cncp.getSupportStartDate().sendKeys(Startdate);
		Cncp.getSupportEndDate().clear();
		Cncp.getSupportEndDate().sendKeys(Enddate);
		Cncp.getSaveBtn().click();
		
		//wLib.switchToAlertAndAccept(driver);
		

		com.comcast.crm.objectrepoUtility.ContactInfoPage Cip = new ContactInfoPage(driver);

		ContactInfoPage cip = new ContactInfoPage(driver);
		String actName = cip.getVerifyLastNameTxtFld().getText();

		if (actName.equals(LastName)) {
			System.out.println(actName + "verfied=pass");
		} else {
			System.out.println(actName + "verfied=Fail");
		}

		String ActStartDate = Cncp.getSupportStartDate().getText();

		if (ActStartDate.equals(Startdate)) {
			System.out.println(ActStartDate + "verfied=pass");
		} else {
			System.out.println(ActStartDate + "verfied=Fail");
		}
		
		String ActEndDate = Cncp.getSupportEndDate().getText();
		if (ActEndDate.equals(Enddate)) {
			System.out.println(ActEndDate + "verfied=pass");
		} else {
			System.out.println(ActEndDate + "verfied=Fail");
		}
	}
	@Test(groups = "RegressionTest")
	public void createContactWithOrgTest() throws Throwable {
		String lastName = eLib.getDataFromExcel("contact", 1, 2);

		int nn = jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.getOrgNameEdit().sendKeys(eLib.getDataFromExcel("org", 1, 2) + nn);
		cnop.getSaveBtn().click();

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String Heading = oip.getHeaderMsg().getText();
		if (Heading.contains(eLib.getDataFromExcel("org", 1, 2) + nn)) {
			System.out.println("verified");
		} else {
			System.out.println("not as expected");
		}

		String organizationname = oip.getVerifyOrgName().getText();

		if (organizationname.equals(eLib.getDataFromExcel("org", 1, 2) + nn)) {
			System.out.println("organization name verified");
		} else {
			System.out.println("name is verified");
		}

		hp.getContactLink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastNameTxtFld().sendKeys(lastName + nn);
		cncp.getOrgLookUpIcon().click();

		// switch to child window

		Set<String> wid = driver.getWindowHandles();
		wLib.switchNewBrowserTab(driver, "module=Account&action");

		Set<String> set = driver.getWindowHandles();
		wLib.switchNewBrowserTab(driver, "module=Accounts&action");
		OrgLookUpIcon olp = new OrgLookUpIcon(driver);
		olp.selectOrg(eLib.getDataFromExcel("Org", 1, 2) + nn, driver);
		wLib.switchToTabOnTitle(driver, "module=Contacts&action");
		cncp.getSaveBtn().click();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String heading1 = cip.getVerifyLastNameTxtFld().getText();
		if (heading1.contains(lastName + nn)) {
			System.out.println("contact is created");
		} else {
			System.out.println("contact is not created");
		}
		String lastname1 = cip.getVerifyLastNameTxtFld().getText();
		if (lastname1.equals(lastName + nn)) {

			System.out.println("last name is verified");
		} else {
			System.out.println("last name is not verified");
		}
		// String orglink=cip.getVerifyOrgName().getText();
		// if(orglink.equals(eLib.getDataFromExcel("Org",1,2)+nn))
		{
			System.out.println("organization verified");
		}
		// else
		{
			System.out.println("organization not verified");
		}

	}


}


