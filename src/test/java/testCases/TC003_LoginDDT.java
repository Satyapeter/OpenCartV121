package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid Data  -- login success - test pass - logout
						 login failed - test fail
						
Data is invalid - login success - test fail -logout 
				  login failed - test pass */


public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")
	public void verify_loginDDT(String email, String pwd, String exp)

	{
		try
		{
			logger.info("*********Started TC_003_LoginDDT");
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();

		//Login
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);//pass from Config Properties
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		

		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
			
		logger.info("*********Finished TC_003_LoginDDT");


	}
}
