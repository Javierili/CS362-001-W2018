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
		 tomorrow.add(Calendar.DAY_OF_MONTH,10);
		 LinkedList<CalDay> calDays = new LinkedList<CalDay>();
		 calDays = timeTable.getApptRange(listAppts, today, tomorrow);
		 
		 //separate calDay and listAppts to compare 
		 CalDay calCompare = new CalDay(today);
		 calCompare.addAppt(appt);
		 compareAppts = calDays.get(0).getAppts();
		 
		 
		assertEquals(calCompare, calDays.get(0));
		assertEquals(listAppts, calDays.get(0).getAppts());
		 assertEquals(appt.getRecurBy(),compareAppts.get(0).getRecurBy());
	
		 
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
         
         
         int[] pv = {1,0};
         
         
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
		 
		assertEquals(listAppts2.get(0).getStartHour(),appt2.getStartHour());
		 
		//listAppts = timeTable.deleteAppt(listAppts,appt2);

	//	 assertEquals(0,listAppts2.size());
	
		 
		 //public LinkedList<Appt> deleteAppt(LinkedList<Appt> appts,Appt appt)
	 }
//add more unit tests as you needed
}
