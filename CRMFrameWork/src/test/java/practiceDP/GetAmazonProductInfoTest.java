package practiceDP;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generifilecutility.ExcelUtility;

public class GetAmazonProductInfoTest {
	@Test(dataProvider = "getData")
	public void getAmazonProductInfoTest(String brandName , String productName)
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");

		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);

		//capture product info
		String x="(//span[text()='"+productName+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]/span[2]/span[2])[1]";

		String productPrice=driver.findElement(By.xpath(x)).getText();
		System.out.println(productPrice); 

	}
	@DataProvider
	public Object[][] getData() throws Throwable
	{

		ExcelUtility eLib=new ExcelUtility();
		int rowCount=eLib.getRowCount("product");
         System.out.println(rowCount);
		Object[][] objArr=new Object[rowCount][2]; //3 mention no. of time exceution , 3 metion is arg.


		for(int i=1;i<rowCount;i++)
		{

			objArr[i][0]=eLib.getDataFromExcel("product", i, 0);
			objArr[i][1]=eLib.getDataFromExcel("product", i, 1);
			}
		return objArr;
	}
}


