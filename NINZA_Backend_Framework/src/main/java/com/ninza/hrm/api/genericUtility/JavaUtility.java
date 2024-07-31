package com.ninza.hrm.api.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
   public int getRandonNumber()
   {
	   Random random = new Random();
	   int randomNo = random.nextInt(5000);
	   
	   return randomNo;
   }
   public String getSystemDataYYYYMMDD()
   {
	   Date dateObj = new Date();
	   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	   String date=dateFormat.format(dateObj);
	   
	   return date ;
   }
   public String getRequiredDataYYYYMMDD(int days)
   {
	   Date dateObj = new Date();
	   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	   String actDate = dateFormat.format(dateObj);
	   
	   Calendar cal = dateFormat.getCalendar();
	   cal.add(Calendar.DAY_OF_MONTH,days);
	   String endDate=dateFormat.format(cal.getTime());
	   
	   return endDate ;
   }
}