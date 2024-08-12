package practice_Listener;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.baseClassUtility.BaseClass;

//@Listeners(com.comcast.crm.listenerUtility.ListenerImplementation.class)
public class InvoiceTest extends BaseClass{
	
	@Test
	public void createInvoiceTest() {
	System.out.println("execute createInvoiceTest");
	String actTitle=driver.getTitle();
	Assert.assertEquals(actTitle, "Login");
	System.out.println("step_1");
	System.out.println("step_2");
	System.out.println("step_3");
	System.out.println("step_4");
	}
	
	@Test
	public void createInvoiceWithContactTest() {
		System.out.println("Excute createInvoiceWithContactTest");
		System.out.println("step_1");
		System.out.println("step_2");
		System.out.println("step_3");
		System.out.println("step_4");
		
	}
	

}
