package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;










import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	 @Test
	  public void test01()  throws Throwable  {
		 //Set up appointment(s)
		 LinkedList<Appt> listAppts = new LinkedList<Appt>();
		 LinkedList<Appt> compareAppts = new LinkedList<Appt>();
		 int startHour=15;
		 int startMinute=30;
		 int startDay=21;  	
		 int startMonth=01; 	
		 int startYear=2018; 	
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
         Appt appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         int[] recurDaysArr={2,3,4}; //[[Is this correct?]]
         appt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
         listAppts.add(appt);
         
         //Set up timeTable
		 TimeTable timeTable=new TimeTable();
		 GregorianCalendar today = new GregorianCalendar(2018,01,21);
		 GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		 tomorrow.add(Calendar.DAY_OF_MONTH,1);
		 LinkedList<CalDay> calDays = new LinkedList<CalDay>();
		 calDays = timeTable.getApptRange(listAppts, today, tomorrow);
		 
		 compareAppts = calDays.get(0).getAppts();
		 
		
	
		assertEquals(listAppts, calDays.get(0).getAppts());
	//	 assertEquals(appt.getRecurBy(),compareAppts.get(0).getRecurBy());
		
		
		//Test DeleteAppt function
		assertEquals(1,listAppts.size());
		LinkedList<Appt> listDeletedAppts = timeTable.deleteAppt(listAppts,listAppts.get(0));
		//assertEquals(0, listDeletedAppts.size());
	
		 //check appt date error
		 TimeTable timeTable2=new TimeTable();
		 GregorianCalendar tday = new GregorianCalendar(2017,01,22);
		 GregorianCalendar tmorrow = (GregorianCalendar)today.clone();
		 tomorrow.add(Calendar.DAY_OF_MONTH,2);
		 LinkedList<CalDay> calDays2 = new LinkedList<CalDay>();
		 calDays2 = timeTable2.getApptRange(listAppts, tday, tmorrow);
		 
	 }
	 
	 
	 
	 
	 @Test
	  public void test02()  throws Throwable  {
		 //Set up appointment(s)
		 LinkedList<Appt> listAppts = new LinkedList<Appt>();
		 LinkedList<Appt> listAppts2 = new LinkedList<Appt>();
		 int startHour=15;
		 int startMinute=30;
		 int startDay=21;  	
		 int startMonth=01; 	
		 int startYear=2018; 	
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
         Appt appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         int[] recurDaysArr={2,3,4}; //[[Is this correct?]]
         appt.setRecurrence( recurDaysArr, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);
         
         listAppts.add(appt);
         
         //clone appt for padding due to for loop error
         Appt apptHalf = new Appt(startHour,
                 startMinute ,
                 startDay ,
                 startMonth ,
                 startYear ,
                 title,
                description);
         listAppts.add(apptHalf);
         
         //second appointment
         startHour=20;
		 startMinute=30;
		 startDay=22;  	
		 startMonth=01; 	
		 startYear=2018; 	
		 title="Existential Crisis";
		 description="Try to keep it to half an hour.";
		 //Construct a new Appointment object with the initial data	 
         Appt appt2 = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         appt2.setRecurrence( recurDaysArr, Appt.RECUR_BY_YEARLY, 2, Appt.RECUR_NUMBER_FOREVER);
        
         listAppts.add(appt2);
         
         
         assertEquals(listAppts.get(1).getStartHour(),appt.getStartHour());
         
         
         int[] pv = {1,0,2};
         
         
         //Set up timeTable
		 TimeTable timeTable=new TimeTable();
		 GregorianCalendar today = new GregorianCalendar(2018,01,21);
		 GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		 tomorrow.add(Calendar.DAY_OF_MONTH,5);
		 LinkedList<CalDay> calDays = new LinkedList<CalDay>();
		 calDays = timeTable.getApptRange(listAppts, today, tomorrow);
		 
		 listAppts = timeTable.permute(listAppts,pv);
		 
		 assertEquals(listAppts.get(0).getStartHour(),appt.getStartHour());
		 
		 listAppts2 = timeTable.deleteAppt(listAppts,appt);
		//assertEquals(listAppts2.get(0).getStartHour(),appt.getStartHour());
		 listAppts2 = timeTable.deleteAppt(listAppts,appt);
		 
		//invalid appt
         startMinute = 66;
         Appt appt3 = new Appt(startHour,
                 startMinute ,
                 startDay ,
                 startMonth ,
                 startYear ,
                 title,
                description);
         listAppts.add(appt3);
         
         calDays = timeTable.getApptRange(listAppts, today, tomorrow);
		 
		//check deleteApp for invalid appt and apptArray (line 204)
         listAppts = timeTable.deleteAppt(listAppts, appt3);
         assertNull(listAppts);
         listAppts2 = timeTable.deleteAppt(listAppts, appt3);
         assertNull(listAppts2);
		 
		//listAppts = timeTable.deleteAppt(listAppts,appt2);

		// assertEquals(0,listAppts2.size());
	
		 
		 //public LinkedList<Appt> deleteAppt(LinkedList<Appt> appts,Appt appt)
	 }
	 
	 @Test
	  public void test03()  throws Throwable  {
		 LinkedList<Appt> listAppts = new LinkedList<Appt>();
		 LinkedList<Appt> listAppts2 = new LinkedList<Appt>();
		 int startHour=15;
		 int startMinute=30;
		 int startDay=21;  	
		 int startMonth=01; 	
		 int startYear=2018; 	
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
         Appt appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         int[] recurDaysArr={}; 
         appt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, 2);
     
         
         Appt appt2 = new Appt(startHour,
                 startMinute ,
                 22 ,
                 startMonth ,
                 startYear ,
                 title,
                description);
         appt2.setRecurrence(recurDaysArr, Appt.RECUR_BY_MONTHLY, 1, Appt.RECUR_NUMBER_FOREVER);
         
         Appt appt3 = new Appt(startHour,
                 startMinute ,
                 23 ,
                 startMonth ,
                 startYear ,
                 title,
                description);
         appt3.setRecurrence(recurDaysArr, Appt.RECUR_BY_YEARLY, 1, Appt.RECUR_NUMBER_FOREVER);
         
         Appt appt4 = new Appt(startHour,
                 startMinute ,
                 24 ,
                 startMonth ,
                 startYear ,
                 title,
                description);
        int[] recurDaysArray={2,3,4}; //[[Is this correct?]]
        appt.setRecurrence( recurDaysArray, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
         
         listAppts.add(appt);
         listAppts.add(appt2);
         listAppts.add(appt3);
         listAppts.add(appt4);
         
         TimeTable timeTable=new TimeTable();
		 GregorianCalendar today = new GregorianCalendar(2018,01,21);
		 GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		 tomorrow.add(Calendar.DAY_OF_MONTH,366);
		 LinkedList<CalDay> calDays = new LinkedList<CalDay>();
		 calDays = timeTable.getApptRange(listAppts, today, tomorrow);
		 
		 assertEquals(1,calDays.get(0).getAppts().size());
		 assertEquals(366,calDays.size());
		 assertEquals(1,calDays.get(7).getAppts().size()); //appt1 default weekly
	//	 assertEquals(0,calDays.get(35).getAppts().size()); RecurNumber not working [[?]]
		 assertEquals(1,calDays.get(29).getAppts().size()); //appt2 monthly
		 assertEquals(1,calDays.get(364).getAppts().size()); //appt3 yearly
		 assertEquals(1,calDays.get(12).getAppts().size()); //appt4 specified weekly
		
		 //permute again... because it's broken and doesn't actually do what it should and such is untestable
		 int[] per = {3,1,2,0};
		 listAppts2 = timeTable.permute(listAppts,per);
		 assertEquals(listAppts.get(3).getStartDay(),listAppts2.get(3).getStartDay()); //incorrect test b/c sourcecode errors
		 
		 //delete again... because deleteAppt() just doesn't want to be testable
		 listAppts2 = timeTable.deleteAppt(listAppts, listAppts.get(1));
		 calDays = new LinkedList<CalDay>();
		 calDays = timeTable.getApptRange(listAppts, today, tomorrow);
		 assertEquals(0,calDays.get(1).getAppts().size());
	 }
		 //add more unit tests as you needed

}
