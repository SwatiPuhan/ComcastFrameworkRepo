package practiceAnnotation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CreateOrg_Annotation_Test 
{
	
	@BeforeSuite
	public void configBS() {
		System.out.println(" Excute BS");
	}
	
	@BeforeClass
	public void configBC() {
		System.out.println("Excute BC");
	}
	@BeforeMethod
	public void configBM()
	{
		System.out.println("Excute BM");
	}
	@Test
	public void createOrg()
	{
		System.out.println("Excute createOrg");
	}
	
	@Test
	public void createOrgWithIndustry()
	{
		System.out.println("execute createOrgWithIndustry");
	}
	
	@AfterMethod
	public void configAM() {
		System.out.println("Excute AM");
	}
	
	@AfterClass
	public void configAC() {
		System.out.println("Excute AC");
	}
	
	@AfterSuite
	public void configAS() {
		System.out.println("Excute AS");
	}

}
