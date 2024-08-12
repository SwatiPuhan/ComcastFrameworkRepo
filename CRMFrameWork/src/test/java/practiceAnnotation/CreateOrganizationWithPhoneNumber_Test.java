package practiceAnnotation;

import org.testng.annotations.Test;

import com.comcast.crm.baseClassUtility.BaseClass;

import com.comcast.crm.objectrepoUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepoUtility.HomePage;

import com.comcast.crm.objectrepoUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepoUtility.OrganizationPage;

public class CreateOrganizationWithPhoneNumber_Test extends BaseClass {
	@Test
	public void createOrganizationWithPhoneNumber() throws Throwable {

		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String PhoneNo = eLib.getDataFromExcel("org", 7, 3) + jLib.getRandomNumber();

		// step2: Navigate to Organisation module
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		// step3: click on Create Organisation button
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
}
