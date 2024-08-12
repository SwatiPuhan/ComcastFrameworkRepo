package practiceAnnotation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CreateContact_Annotation_Test 
{
	
	@BeforeSuite
	public void configBS() {
		System.out.println("Excute BS");
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
	public void createContact()
	{
		System.out.println("Excute cc");
	}
	
	@Test
	public void createContactWithSupportDate()
	{
		System.out.println("Excute ccsd");
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
