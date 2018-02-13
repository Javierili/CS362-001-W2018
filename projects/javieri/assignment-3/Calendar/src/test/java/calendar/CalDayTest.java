package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;

import java.util.GregorianCalendar;


import java.util.LinkedList;
import java.util.Objects;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.*;


public class CalDayTest {

	/* Test get methods
	 * 
	 */
	 @Test
	 	public void test01() throws Throwable {
		 GregorianCalendar today = new GregorianCalendar(2018,01,28);
		 CalDay calDays = new CalDay(today); 
		 CalDay calDay2 = new CalDay(); //valid = false
		 
		 assertTrue(calDays.isValid());
		 assertEquals(28, calDays.getDay());
		 assertEquals(01, calDays.getMonth());
		 assertEquals(2018, calDays.getYear());
		 assertEquals(false,calDay2.isValid());
		 assertEquals(67,calDays.toString().length());
		 assertEquals(0,calDay2.toString().length());
		 
		 
		 
	 }
	 
	 /*Test add and get Appointment methods
	  * 
	  */
	 @Test
	  public void test02()  throws Throwable  {
		 GregorianCalendar today = new GregorianCalendar(2018,01,28);
		 CalDay calDays = new CalDay(today); 
		 
		 int startHour=21;
		 int startMinute=30;
		 int startDay=28;
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
		
		 calDays.addAppt(appt);
		
		LinkedList<Appt> listAppts = new LinkedList<Appt>(); 
		LinkedList<Appt> listAppts2 = new LinkedList<Appt>();
		listAppts.add(appt);
		listAppts2 =  null;
		
		StringBuilder sn = new StringBuilder();
		sn.append("\t --- 00/28/2018 --- \n --- -------- Appointments ------------ --- \n\t00/28/2018 at 9:30pm ,Birthday Party, This is my birthday party.\n\n");
		
		assertTrue(appt.getValid());
		assertEquals(listAppts, calDays.getAppts());
		assertEquals(1,calDays.getSizeAppts());
		
		 
	//	assertTrue(Objects.equals((String) sn.toString(),(String) calDays.toString()));
		 
		 
	 }
	 
	 @Test
	 public void test03()  throws Throwable  {
		 GregorianCalendar today = new GregorianCalendar(2018,02,28);
		 CalDay calDays = new CalDay(today); 
		 CalDay falseDay = new CalDay(); //not valid 
		 
		 int startHour=21;
		 int startMinute=30;
		 int startDay=28;
		 int startMonth=02;
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
		calDays.addAppt(appt);
		
		 startHour=19;
		 title="Birthday Party After Party";
		 description="This is my after party.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt2 = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		calDays.addAppt(appt2);
		
		 startHour=22;
		 title="Crash";
		 description="We done for the night.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt3 = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		calDays.addAppt(appt3);
		
		LinkedList<Appt> listAppts = new LinkedList<Appt>(); //Reverse order bc of [[bug sourcecode line 77]]
		listAppts.add(appt3);
		listAppts.add(appt);
		listAppts.add(appt2);
		
		assertEquals(listAppts.get(0), calDays.getAppts().get(0));
		assertEquals(listAppts.get(1), calDays.getAppts().get(1));
		assertEquals(listAppts.get(2), calDays.getAppts().get(2));
		assertEquals(appt.getStartHour(),calDays.getAppts().get(1).getStartHour());
		
		
		//[[Says iterator() isn't reached for some reason ??]]
		Iterator<Appt> itr = calDays.getAppts().iterator(); 
		assertTrue(itr.hasNext());
		
		calDays.toString();
		
		//Iterator<CalDay> itre = falseDay.iterator();
		//assertEquals(null, itr);
		
	 }
//add more unit tests as you needed	
}
