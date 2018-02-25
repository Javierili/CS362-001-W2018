package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;












import static org.junit.Assert.*;



/**
 * Random Test Generator  for TimeTable class.
 * For methods: deleteAppt, getApptRange
 */

public class TimeTableRandomTest {
	
    /**
     * Generate Random Tests that tests TimeTable Class.
     */
		private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
		private static final int NUM_TESTS=100;
		 public static int RandomSelectRecur(Random random){
		        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

		    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
		        return RecurArray[n] ; // return the value of the  appointments to recur 
		        }	
			/**
			 * Return a randomly selected appointments to recur forever or Never recur  !.
			 */
		    public static int RandomSelectRecurForEverNever(Random random){
		        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

		    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
		        return RecurArray[n] ; // return appointments to recur forever or Never recur 
		        }	
	
	 @Test
	  public void radnomtest()  throws Throwable  {
		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing TimeTable...");
		
		 try {
		 for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
			//	System.out.println(" Seed:"+randomseed ); 
				Random random = new Random(randomseed);
				
				for (int i = 0; i < NUM_TESTS; i++) {
					 LinkedList<Appt> listAppts = new LinkedList<Appt>();
					 LinkedList<Appt> listAppts2 = new LinkedList<Appt>();
					
					 //create appointments within a year, all valid
				   	 int startHour=ValuesGenerator.getRandomIntBetween(random,0,23);
					 int startMinute=ValuesGenerator.getRandomIntBetween(random,0,59);
					 int startDay=ValuesGenerator.RandInt(random);
					 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12); 
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
					//set reccurence for appt
					   int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
					   int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
					   int recur=ApptRandomTest.RandomSelectRecur(random);
					   int recurIncrement = ValuesGenerator.RandInt(random);
					   int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
					  
					   appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
					   
					   //appt2
					   startHour=ValuesGenerator.getRandomIntBetween(random,0,23);
					   startMinute=ValuesGenerator.getRandomIntBetween(random,0,59);
					   startDay=ValuesGenerator.RandInt(random);
					   startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12); 
					 
						 //Construct a new Appointment object with the initial data	 
						 Appt appt2 = new Appt(startHour,
						          startMinute ,
						          startDay ,
						          startMonth ,
						          startYear ,
						          title,
						         description);
					   
						//appt3
						 startHour=ValuesGenerator.getRandomIntBetween(random,0,23);
						   startMinute=ValuesGenerator.getRandomIntBetween(random,0,59);
						   startDay=ValuesGenerator.RandInt(random);
						   startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12); 
							 //Construct a new Appointment object with the initial data	 
							 Appt appt3 = new Appt(startHour,
							          startMinute ,
							          startDay ,
							          startMonth ,
							          startYear ,
							          title,
							         description);
							 listAppts.add(appt);
							 listAppts.add(appt2);
							 listAppts.add(appt3);
							 
							 Appt appt4 = null;
					   
					   //Set up timeTable
						 TimeTable timeTable=new TimeTable();
						 GregorianCalendar today = new GregorianCalendar(2018,03,21);
						 GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
						 tomorrow.add(Calendar.DAY_OF_MONTH,366);
						 LinkedList<CalDay> calDays = new LinkedList<CalDay>();
						 calDays = timeTable.getApptRange(listAppts, today, tomorrow);
					   
						 listAppts2 = timeTable.deleteAppt(listAppts,appt2);
					
						 listAppts2 = timeTable.deleteAppt(listAppts,appt4);
						 assertNull(listAppts2);
						 listAppts2 = timeTable.deleteAppt(listAppts2,appt4);
						 
						 listAppts.add(appt2);
						 
						 listAppts2 = timeTable.deleteAppt(listAppts,appt2);
				}//For num tests
				
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			        
		 } //For TestTimeout
		 
		 }catch(NullPointerException e){
				
			}
		 
	 }
}
