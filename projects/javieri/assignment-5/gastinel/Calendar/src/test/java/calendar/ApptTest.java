package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import java.util.Objects;

import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
	
    /**
     * Test that the gets methods work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
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
	// Valid assertions
		 assertTrue(appt.getValid());
		 assertEquals(21, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());    
		 //default recurrence:
		 assertEquals(false, appt.isRecurring());
		 assertEquals(0,appt.getRecurNumber());
         assertEquals(2,appt.getRecurBy());
         
	
	// InValid Assertions
         //Covers lines 134 ~- 160
		 
		 appt.setStartHour(34);
		 assertEquals(false,appt.getValid());
		 assertNull(appt.toString());
		 for (int x = 0; x <=23 ; x++){
			 appt.setStartHour(x);
			 assertTrue(appt.getValid());
		 }
		 appt.setStartHour(11);
		 assertTrue(appt.getValid());
		 
		 appt.setStartMinute(-2);
		 assertEquals(false,appt.getValid());
		 for (int x = 0; x <=59 ; x++){
			 appt.setStartMinute(x);
			 assertTrue(appt.getValid());
		 }
		 appt.setStartMinute(33);
		 assertTrue(appt.getValid());
		
		 appt.setStartDay(32);
		 assertEquals(false,appt.getValid());
		 for (int x = 1; x <=31 ; x++){
			 appt.setStartDay(x);
			 assertTrue(appt.getValid());
		 }
		 appt.setStartDay(12);
		 assertTrue(appt.getValid());
		
		// appt.setStartMonth(0);     //Array out of range exception
		// assertEquals(false,appt.getValid());
		 for (int x = 1; x <=10 ; x++){  //[[ERROR]] Why does it not work for 11 and 12? //Feb 24: SOLVED : Appt.java line 113
			 appt.setStartMonth(x);
			 assertTrue(appt.getValid());
		 }
		 appt.setStartMonth(10);
		 assertTrue(appt.getValid());
		 
		 appt.setStartMinute(66);
		 appt.setStartYear(2016);
		 assertEquals(false,appt.getValid());

		 
		 
	 }

	 /* Tests Recurrence methods
	  * Assumes passed test01 */
	 @Test
	  public void test02()  throws Throwable  {
		 int startHour=12;
		 int startMinute=00;
		 int startDay=29;
		 int startMonth=01;
		 int startYear=2018;
		 String title="CS Meeting";
		 String description="Meetings monday wednesday and friday.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		 
		 //Test Recurrence [[ERROR: FIX]]
		 //RECUR_BY_WEEKLY = 1
		 //RECUR_NUMBER_FOREVER = 1000
		 int[] recurDaysArr={2,4,7}; //[[Is this correct?]]
         appt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
         
         //Test Recurrence get methods
         
         assertTrue(appt.isRecurring());
         assertEquals(1000,appt.getRecurNumber());
         assertEquals(1,appt.getRecurBy());
         assertEquals(recurDaysArr,appt.getRecurDays());
         assertEquals(2,appt.getRecurIncrement());
		 
	 }
	
// [[ FIX STRING ASSERTIONS SOMEHOW ]]	 
	  /* Tests ToString methods
	  * Assumes passed test01 */
	 @Test
	  public void test03()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startMinute3=25;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 
		 int startHour2=23;
		 int startDay2=20;
		 int startMinute2=35;
		 int startMonth2 = 03;
		 int startYear2=2020;
		 
		 int startHour3 = 2;
		 String title="HELP";
		 String description="HELPME.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		 Appt appt2 = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		 Appt appt3 = new Appt(startHour,
		          startMinute3 ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		 Appt appt4 = new Appt(startHour2,
		          startMinute2 ,
		          startDay2 ,
		          startMonth2 ,
		          startYear2 ,
		          title,
		         description);
		 Appt appt5 = new Appt(startHour3,
		          startMinute2 ,
		          startDay2 ,
		          startMonth2 ,
		          startYear2 ,
		          title,
		         description);
		 
		 
		
		 assertEquals(0,appt.compareTo(appt2));
		 assertEquals(5,appt.compareTo(appt3));
		 assertEquals(-16,appt.compareTo(appt4));
		 String day = "\t01/15/2018 at 9:30pm ,HELP, HELPME.\n";
		 char t = '2';
		 assertEquals(t,appt.toString().charAt(6));
		 assertEquals('9',appt.toString().charAt(14));
		 
		 for(int i=12;i<24;i++){
			 appt.setStartHour(i);
			 if(i>12 && i<22){
			 assertEquals('p',appt.toString().charAt(18));
			 }
			 else {
			assertEquals('p',appt.toString().charAt(19));	 
			 }
		 }
	
		 assertEquals('a',appt5.toString().charAt(18));
		 //String day= this.getStartMonth()+"/"+this.getStartDay()+"/"+this.getStartYear() + " at ";
		 // return "\t"+ day +  this.represntationApp()  + " ," +  getTitle()+ ", "+  getDescription()+"\n";
		 //assertTrue(Objects.equals(day, appt.toString()));
	//	 assertEquals(day, appt.toString());
	 
	 }
//add more unit tests as you needed
	
}
