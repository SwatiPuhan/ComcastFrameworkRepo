package com.comcast.crm.Org_Assertion_Test;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.comcast.crm.baseClassUtility.BaseClass;
import com.comcast.crm.objectrepoUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepoUtility.HomePage;

import com.comcast.crm.objectrepoUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepoUtility.OrganizationPage;
import com.comcast.crm.webdriverutility.UtilityClassObject;

@Listeners(com.comcast.crm.listenerUtility.ListenerImplementation.class)
public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createOrganization() throws Throwable {

		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");

		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		// step2: Navigate to Organisation module

		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Organisation module");
		HomePage homePage = new HomePage(driver);
		homePage.getOrganizationLink().click();

		// step3: click on Create Organisation button
		UtilityClassObject.getTest().log(Status.INFO, "click on Create Organisation button");
		OrganizationPage createNewPage = new OrganizationPage(driver);
		createNewPage.getCreateNewOrgBtn().click();

		// step4: Enter the values to the Organisation name textfield
		UtilityClassObject.getTest().log(Status.INFO, " Enter the values to the Organisation name textfield");
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName, orgName);

		UtilityClassObject.getTest().log(Status.INFO, orgName + "=====>create a new org");

		// save it
		cnop.getSaveBtn();

		// verify Headermsg ExpectedREsult
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();

		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + "name is verified");
		} else {
			System.out.println(orgName + "name is not verified");
		}
	}

	@Test(groups = "RegressionTest")
	public void createOrganizationWithPhoneNumber() throws Throwable {

		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String PhoneNo = eLib.getDataFromExcel("org", 7, 3) + jLib.getRandomNumber();

		// step2: Navigate to Organisation module
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		// step3: click on Create Organisation look up icon
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// step4: Enter all the details to the Organisation name textfield

		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName, PhoneNo);

		// verify phone number
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actPhoneNo = oip.getPhoneMsg().getText();

		if (actPhoneNo.contains(PhoneNo)) {
			System.out.println(PhoneNo + "name is verified");
		} else {
			System.out.println(PhoneNo + "name is not verified");
			
		}
		
	}

	@Test(groups = "RegressionTest")
	public void createOrgWithIndustry() throws Throwable {

		// Read the test script data from excel file
		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		// step2: Navigate to Organisation module
		HomePage homePage = new HomePage(driver);
		homePage.getOrganizationLink().click();

		// step3: click on Create Organisation button
		OrganizationPage createNewPage = new OrganizationPage(driver);
		createNewPage.getCreateNewOrgBtn().click();

		// step4: Enter all the details to the Organisation name textfield
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		
		// cnop.createOrg(orgName);

		// before saving we need to select dropdown
		cnop.createOrg(orgName, industry, type);

		// verify the dropdown industry and type info
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actIndustryName = oip.getIndustryMsg().getText();
		if (actIndustryName.contains(industry)) {
			System.out.println(industry + "name is verified");
		} else {
			System.out.println(industry + "name is not verified");
		}

		// type dropdown verify

		String actTypeName = oip.getTypeMsg().getText();
		if (actTypeName.contains(type)) {
			System.out.println(type + "name is verified");
		} else {
			System.out.println(type + "name is not verified");
		}
		

	}
}
