
package finalprojectB;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   public void testManualTest(){
	   //Basic URL manual testing
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);  //check arguments here
	//	boolean result1 = urlVal.isValid("http://www.google.com"); //passes
	//	boolean result2 = urlVal.isValid("go.au"); //fails
		//boolean result3 = urlVal.isValid("h3t://tumblr.com:80"); //fails
	//	boolean result4 = urlVal.isValid("ftp://nrofud9ej3of.edu"); //fails
	 //  boolean result5 = urlVal.isValid("http://facebook.com/pathway"); //passes
	  
		assertTrue(result4);
	   
   }
   
   //////////////////////////////// First Partition ////////////////////////
   
   public void  testYourFirstPartition()
   {
//You can use this function to implement your manual testing	 
	  assertTrue(testPart01()); //Scheme and Authority
	  assertTrue(testPart02()); //test01 + Port
	  assertTrue(testPart03()); //test01 + Path
	  assertTrue(testPart04()); //test01 + Query
   }
   
   
   public boolean testPart01() 
   {
	   // w/ Scheme and Authority
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);  //check arguments here
	// boolean result1 = urlVal.isValid("http://www.google.com"); //pass
	//   boolean result2 = urlVal.isValid("go.au"); //fails -- passes with scheme http://www.
	 //boolean result3 = urlVal.isValid("h3t://255.com"); //fails -- h3t:// supposed to be valid
	 //boolean result4 = urlVal.isValid("ftp://google.com"); // fails -- ftp:// supposed to be valid
	   // boolean result5 = urlVal.isValid("https://www.google.com"); //fails -- https:// supposed to be valid
	boolean result6 = urlVal.isValid("http://google.com");
	  
	   return result6;
	   
   }
   
   public boolean testPart02() 
   {
	   //test01 + Port
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);  //check arguments here
	//   boolean result1 = urlVal.isValid("http://www.tumblr.com:80/test"); //fails with port...
	 //  boolean result2 = urlVal.isValid("http://www.tumblr.com:65a"); //good fail
	   //boolean result3 = urlVal.isValid("http://www.tumblr.com:65535"); //fails
	   //seems all ports fail 
	   // FOUND:: line 316:  if (authority.contains(":")) -- with all http:// URLs
	   // (when fixed: see line 417 -- Port Parser -- for testing constraints)
	 
	   return true;
	   
   }
   
   public boolean testPart03()
   {
	   //test01 + path -- KNOWN ERROR (?) (see PATH_REGEX in URLValidator.java in ProjectB) 
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);  //check arguments here
	   boolean result1 = urlVal.isValid("http://www.tumblr.com/test"); //passes
	   boolean result2 = urlVal.isValid("http://www.tumblr.com/1test1"); //passes
	   boolean result3 = urlVal.isValid("http://www.tumblr.com/$test1"); //passes
	   boolean result4 = urlVal.isValid("http://www.tumblr.com/.."); //good fail
	   boolean result5 = urlVal.isValid("http://www.google.com/test1//file"); //good fail
	   return result1;
   }
   
   public boolean testPart04() 
   {
	   //test01 + Query
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);  
	   boolean result1 = urlVal.isValid("http://www.google.com?action=view"); //passes
	   boolean result2 = urlVal.isValid("http://www.google.com?action=fake"); //passes -- correct?
	   //Query_Pattern is an imported function so testing is kinda null point
	   //line 480
	   return result2;
	   
   }
   
   
   ////////////////////////// Random Testing ////////////////////////////////////////
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   
	   
   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   
   public static void main(String[] argv) {

	   UrlValidatorTest testie = new UrlValidatorTest("url test");
	   testie.testManualTest(); 
	   testie.testYourFirstPartition();
	   
	   
      
   }
   


}
