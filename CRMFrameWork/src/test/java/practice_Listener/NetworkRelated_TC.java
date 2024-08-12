package practice_Listener;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NetworkRelated_TC {
	
	@Test(retryAnalyzer = com.comcast.crm.listenerUtility.RetryImplementation.class)
	public void activateSim_TC() {
	
		System.out.println("execute activateSim_TC");
		Assert.assertEquals("" ,"Login");
		System.out.println("step_1");
		System.out.println("step_2");
		System.out.println("step_3");
		System.out.println("step_4");
		}
}
