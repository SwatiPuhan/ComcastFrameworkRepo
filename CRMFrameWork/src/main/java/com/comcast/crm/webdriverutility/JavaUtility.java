package com.comcast.crm.webdriverutility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
	public int getRandomNumber() {
		Random random=new Random();
		int randomNum=random.nextInt(5000);
		return randomNum;
	}
	
	public String getSystemDateYYYYMMDD()
	{
		Date date=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		String date1 = sdf.format(date);
		return date1;
				
	}
	public String getRequiredDateYYYYMMDD(int days)
	{
      
		Date date=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("YYYY-MM-dd");
		sim.format(date);
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String reqDate=sim.format(cal.getTime());
		return reqDate;
	}
	
  public String getLocalDateAndTime()
  {
	  return LocalDateTime.now().toString().replace(":", "-");
  }
  
  
  
  
  
  
  
  
  
  
  
  
}
