package calendar;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import org.junit.Test;










import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    //public static String RandomSelectMethod(Random random){
     //   String[] methodArray = new String[] {"setTitle","setRecurrence"};// The list of the of methods to be tested in the Appt class

    //	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
    //    return methodArray[n] ; // return the method name 
     //   }
	
    
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

		 
		 System.out.println("Start testing CalDay...");
		 
		 
		 //TESTS:
		 //PRODUCE RANDO NUM APPTS (<100) + LinkedList
		 //RANDO DATES FOR ALL
		 //KEEP TRACK OF VALID/INVALID NUM
		 //MAKE SURE CORRECT NUM APPTS ADDED (MUST BE VALID)
		 //???
			try{ 
				for (int iteration = 0; elapsed < TestTimeout; iteration++) { //[[this is a fuckton more than 30s wtf]]
					long randomseed =System.currentTimeMillis(); //10
			//		System.out.println(" Seed:"+randomseed );
					Random random = new Random(randomseed);
					
				//	System.out.println("elapsed: "+elapsed ); //comment plz
					
					
			
			//add !calDay.getValid() here
			for (int i = 0; i < NUM_TESTS; i++) {
				 GregorianCalendar today = new GregorianCalendar(2018,01,01);
				 CalDay calDays = new CalDay(today); 
				 
				 int numinValid = 0;
				 int startHour=ValuesGenerator.RandInt(random);
				 int startMinute=ValuesGenerator.RandInt(random);
				 int startDay=1;
				 int startMonth=1;
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
				 if(!appt.getValid()) numinValid++;
				 calDays.addAppt(appt);
				 
				 startHour=ValuesGenerator.RandInt(random);
				 startMinute=ValuesGenerator.RandInt(random);
				 Appt appt2 = new Appt(startHour,
				          startMinute ,
				          startDay ,
				          startMonth ,
				          startYear ,
				          title,
				         description); 
				 if(!appt2.getValid()) numinValid++;
				 calDays.addAppt(appt2);
				 startHour=ValuesGenerator.RandInt(random);
				 startMinute=ValuesGenerator.RandInt(random);
				 Appt appt3 = new Appt(startHour,
				          startMinute ,
				          startDay ,
				          startMonth ,
				          startYear ,
				          title,
				         description); 
				 if(!appt3.getValid()) numinValid++;
				 calDays.addAppt(appt3);
				 
				 assertEquals((3 - numinValid),calDays.getSizeAppts());
						
					} //for numTests
					
			 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
		        if((iteration%10000)==0 && iteration!=0 )
		              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
				} // For TestTimeOut
			
			}catch(NullPointerException e){
				
			}
		 
			 System.out.println("Done testing...");
	 } //randomTest


	
} //public Class
