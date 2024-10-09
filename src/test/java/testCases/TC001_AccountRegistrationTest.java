package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	
	//now @Beforeclass and @Afterclass it is sued most often so we are placing it in BaseCLass
	//place baseclass  in testBase package
	
	//WebDriver driver;
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		//2nd lecture
		
		logger.info("********* Starting TC001_AccountRegistrationTest *******");
		
		try
		{
		HomePage hp = new HomePage(driver); 
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link");
		
		hp.clickRegister();
		logger.info("Clicked on Register link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details...");
		//now if you want to execute multiple times we need to generate some 
		//random function which is extending using BaseClass
		
		//regpage.setFirstName("John"); 
		regpage.setFirstName(randomString().toUpperCase());
		
		//regpage.setLastName("David");
		regpage.setLastName(randomString().toUpperCase());
		
		//regpage.setEmail("abcjohndavif@gmail.com"); // randomly generated the email
		regpage.setEmail(randomString()+"@gmail.com");
		
		regpage.setTelephone(randomNumber());
		
		//String password=randomAlphaNumeric();
		
		String password=randomAlphaNumberic();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy(); 
		regpage.clickContinue();
		
		logger.info("Validating expected message...");
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		
		else
		{
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		

		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		

		logger.info("********* Final TC001_AccountRegistrationTest *******");
		
	}
	
	
}
