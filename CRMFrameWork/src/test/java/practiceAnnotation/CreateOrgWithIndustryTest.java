package practiceAnnotation;

import org.testng.annotations.Test;

import com.comcast.crm.baseClassUtility.BaseClass;

import com.comcast.crm.objectrepoUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepoUtility.HomePage;

import com.comcast.crm.objectrepoUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepoUtility.OrganizationPage;

public class CreateOrgWithIndustryTest extends BaseClass {
	@Test
	public void createOrgWithIndustry() throws Throwable {

		// Read the test script data from excel file
		// 4 row 2,3,4 column

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

		// step5: click on save button
		// cnop.getSaveBtn();

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
