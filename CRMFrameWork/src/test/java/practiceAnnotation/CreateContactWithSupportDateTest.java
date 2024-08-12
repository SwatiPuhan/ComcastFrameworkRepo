package practiceAnnotation;

import org.testng.annotations.Test;

import com.comcast.crm.baseClassUtility.BaseClass;
import com.comcast.crm.objectrepoUtility.ContactInfoPage;
import com.comcast.crm.objectrepoUtility.CreateNewContactPage;

public class CreateContactWithSupportDateTest extends BaseClass{
@Test
public void createContactWithSupportDate() throws Throwable {
	

//	String orgname = Eu.getdatafromExcelfile("org", 4, 2)+Ju.getRandomNumber();
	String LastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

	//step1:login to app
	
	CreateNewContactPage Cncp=new CreateNewContactPage(driver);
	Cncp.getLastNameTxtFld();
	Cncp.getSupportStartDate().clear();
	String Startdate = jLib.getSystemDateYYYYMMDD();
	String Enddate = jLib.getRequiredDateYYYYMMDD(30);
	Cncp.getSupportStartDate().clear();
    Cncp.getSupportStartDate().sendKeys(Startdate);
    Cncp.getSupportEndDate().clear();
    Cncp.getSupportEndDate().sendKeys(Enddate);
    Cncp.getSaveBtn().click();

	com.comcast.crm.objectrepoUtility.ContactInfoPage Cip=new ContactInfoPage(driver);
	
	ContactInfoPage cip=new ContactInfoPage(driver);
	String actName = cip.getVerifyLastNameTxtFld().getText();
	
	if(actName.equals(LastName)) {
		System.out.println(actName+ "verfied=pass");
	}
	else {
		System.out.println(actName+ "verfied=Fail");
	}
	
	String ActStartDate = Cncp.getSupportStartDate().getText();
	
	if(ActStartDate.equals(Startdate)) {
		System.out.println(ActStartDate+ "verfied=pass");
	}
	else {
		System.out.println(ActStartDate+ "verfied=Fail");
	}
	String ActEndDate = Cncp.getSupportEndDate().getText();
	if(ActEndDate.equals(Enddate)) {
		System.out.println(ActEndDate+ "verfied=pass");
	}
	else {
		System.out.println(ActEndDate+ "verfied=Fail");
	}
}

}


	