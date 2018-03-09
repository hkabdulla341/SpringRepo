package Practise.WalletApp;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import Practice.WalletAppException.WalletException;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WalletTestWithdraw
{
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(RegisterSpring.class);
    WalletService walletService = ctx.getBean("walletService", WalletService.class);
    boolean result;

    @Given("^in WalletWithdrawTest, system has one account with mobile number \"([^\"]*)\", name is \"([^\"]*)\" and amount is (\\d+)$")
    public void in_WalletWithdrawTest_system_has_one_account_with_mobile_number_name_is_and_amount_is(String arg1,
	    String arg2, int arg3) throws Exception
    {
	walletService.createAccount(arg2, arg1, new BigDecimal(arg3));
    }

    @When("^in WalletWithdrawTest, user inputs mobile number (\\d+)$")
    public void in_WalletWithdrawTest_user_inputs_mobile_number(int arg1) throws Exception
    {
	try
	{
	    walletService.deposit("" + arg1, "100");
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    result = message.equals("Mobile number does not exist");
	}
    }

    @Then("^WalletWithdrawTest system should print \"([^\"]*)\"$")
    public void walletwithdrawtest_system_should_print(String arg1) throws Exception
    {
	assertTrue(result);
    }

    @Given("^in WalletWithdrawTest, user wants to access account with mobile number which is not (\\d+) digits$")
    public void in_WalletWithdrawTest_user_wants_to_access_account_with_mobile_number_which_is_not_digits(int arg1)
	    throws Exception
    {
	System.out.println("in WalletWithdrawTest, " + "user wants to access account with mobile number which is not "
		+ arg1 + " digits...");
    }

    @When("^in WalletWithdrawTest, user gives (\\d+) as mobile number OR user gives (\\d+) as mobile number$")
    public void in_WalletWithdrawTest_user_gives_as_mobile_number_OR_user_gives_as_mobile_number(int arg1, int arg2)
	    throws Exception
    {
	boolean lessDigits = false;
	boolean moreDigits = false;

	try
	{
	    walletService.deposit("" + arg1, "100");
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    lessDigits = message.equals("Invalid mobile number");
	}

	try
	{
	    walletService.deposit("" + arg2, "100");
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    moreDigits = message.equals("Invalid mobile number");
	}

	if (lessDigits == true && moreDigits == true)
	{
	    result = true;
	}
    }

    @Given("^in WalletWithdrawTest,user wants to access account with a mobile number that includes non numerical values$")
    public void in_WalletWithdrawTest_user_wants_to_access_account_with_a_mobile_number_that_includes_non_numerical_values()
	    throws Exception
    {
	System.out.println("in WalletWithdrawTest,user wants to " + "access account with a mobile number that "
		+ "includes non numerical values...");
    }

    @When("^in WalletWithdrawTest, user gives \"([^\"]*)\" as mobile number while depositing to a account$")
    public void in_WalletWithdrawTest_user_gives_as_mobile_number_while_depositing_to_a_account(String arg1)
	    throws Exception
    {
	try
	{
	    walletService.deposit(arg1, "100");
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    result = message.equals("Mobile number cannot contain non numerical values");
	}
    }

    @Given("^in WalletWithdrawTest, user wants to access an account$")
    public void in_WalletWithdrawTest_user_wants_to_access_an_account() throws Exception
    {
	System.out.println("In WalletWithdrawTest, user wants to access an account...");
    }

    @When("^in WalletWithdrawTest, user passes NULL as mobile number$")
    public void in_WalletWithdrawTest_user_passes_NULL_as_mobile_number() throws Exception
    {
	try
	{
	    walletService.deposit(null, "100");
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    result = message.equals("Mobile number cannot be NULL");
	}
    }

    @Given("^in WalletWithdrawTest, user wants to deposit less than (\\d+) or more than (\\d+)$")
    public void in_WalletWithdrawTest_user_wants_to_deposit_less_than_or_more_than(int arg1, int arg2) throws Exception
    {
	System.out.println("In WalletWithdrawTest, user wants to deposit less than 0 or more than 100000...");
    }

    @When("^in WalletWithdrawTest, user inputs -(\\d+) OR user inputs (\\d+)$")
    public void in_WalletWithdrawTest_user_inputs_OR_user_inputs(int arg1, int arg2) throws Exception
    {
	boolean lessThanZero = false;
	boolean moreThan100K = false;

	arg1 *= -1;

	try
	{
	    walletService.deposit("12345678", "" + arg1);
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    lessThanZero = message.equals("Deposit is out of limits");
	}

	try
	{
	    walletService.deposit("12345678", "" + arg2);
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    moreThan100K = message.equals("Deposit is out of limits");
	}

	if (lessThanZero == true && moreThan100K == true)
	{
	    result = true;
	}
    }

    @When("^in WalletWithdrawTest, user wants to withdraw (\\d+) for account with mobile number \"([^\"]*)\"$")
    public void in_WalletWithdrawTest_user_wants_to_withdraw_for_account_with_mobile_number(int arg1, String arg2)
	    throws Exception
    {
	try
	{
	    AccountDao acc = walletService.withdraw(arg2, "" + arg1);
	    String message = acc.display();
	    System.out.println(message);
	    result = message.equals("Balance of account " + acc.getMobile() + " is " + acc.getWalletBalance());
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    result = message.equals("Insufficient funds for withdrawal");
	}
    }
}
