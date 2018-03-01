package Practise.WalletApp;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Assert;

import Practice.WalletAppException.WalletException;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WalletTestShowBlance
{
    WalletService walletService = new WalletService();
    boolean result;

    @Given("^There exist valid account with mobile number (\\d+) , name as \"([^\"]*)\" and amount is (\\d+)$")
    public void there_exist_valid_account_with_mobile_number_name_as_and_amount_is(int arg1, String arg2, int arg3)
	    throws Exception
    {
	walletService.createAccount(arg2, "" + arg1, new BigDecimal(arg3));
	System.out.println("Created account with mobile number " + arg1 + ", name as " + arg2 + "with amount " + arg3);
    }

    @When("^user inputs mobile number (\\d+)$")
    public void user_inputs_mobile_number(int arg1) throws Exception
    {
	try
	{
	    walletService.showBalance("" + arg1);
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    result = message.equals("Mobile number does not exist");
	}
    }

    @Then("^for showblance system should print \"([^\"]*)\"$")
    public void for_showblance_system_should_print(String arg1) throws Exception
    {
	Assert.assertTrue(result);
    }

    @Given("^user wants to access account with mobile number which is not (\\d+) digits$")
    public void user_wants_to_access_account_with_mobile_number_which_is_not_digits(int arg1) throws Exception
    {
	System.out.println("user wants to access account with mobile number which is not 8 digits...");
    }

    @When("^user gives (\\d+) as mobile number OR user gives (\\d+) as mobile number$")
    public void user_gives_as_mobile_number_OR_user_gives_as_mobile_number(int arg1, int arg2) throws Exception
    {
	boolean lessThanDigits = false;
	boolean moreThanDigitss = false;

	try
	{
	    walletService.showBalance("" + arg1);
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    lessThanDigits = message.equals("Invalid mobile number");
	}

	try
	{
	    walletService.showBalance("" + arg2);
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    moreThanDigitss = message.equals("Invalid mobile number");
	}

	if (lessThanDigits == true && moreThanDigitss == true)
	{
	    result = true;
	}
    }

    @Given("^user wants to access account with a mobile number that includes non numerical values$")
    public void user_wants_to_access_account_with_a_mobile_number_that_includes_non_numerical_values() throws Exception
    {
	System.out.println("user wants to access account with a mobile number that includes non numerical values...");
    }

    @When("^user gives \"([^\"]*)\" as mobile number to show balance of that mobile number$")
    public void user_gives_as_mobile_number_to_show_balance_of_that_mobile_number(String arg1) throws Exception
    {
	try
	{
	    walletService.showBalance("" + arg1);
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println("msg = " + message);
	    result = message.equals("Mobile number cannot contain non numerical values");
	}
    }

    @Given("^user wants to access an account$")
    public void user_wants_to_access_an_account() throws Exception
    {
	System.out.println("user wants to access an account...");
    }

    @Given("^user wants to show the balance of existing account (\\d+) with account balance of (\\d+) and name \"([^\"]*)\"$")
    public void user_wants_to_show_the_balance_of_existing_account_with_account_balance_of_and_name(int arg1, int arg2,
	    String arg3) throws Exception
    {
	try
	{
	    walletService.createAccount(arg3, "" + arg1, new BigDecimal(arg2));
	}
	catch (WalletException ex)
	{
	    System.out.println("Exception caught for scenario ShowBalanceValid (given) : " + ex.getMessage());
	}
    }

    @When("^user inputs (\\d+) to its balance$")
    public void user_inputs_to_its_balance(int arg1) throws Exception
    {
	try
	{
	    AccountDao acc = walletService.showBalance("" + arg1);
	    String displayStr = acc.display();
	    result = displayStr.equals("Balance of account " + acc.getMobile() + " is " + acc.getWalletBalance());
	}
	catch (WalletException ex)
	{
	    System.out.println("Exception caught for scenario ShowBalanceValid (when) : " + ex.getMessage());
	}
    }

    @Given("^user wants to access an account and invoking showbalance$")
    public void user_wants_to_access_an_account_and_invoking_showbalance() throws Exception
    {
	System.out.println("user wants to access an account and invoking showbalance...");
    }

    @When("^user passes NULL as mobile number and to show its balance$")
    public void user_passes_NULL_as_mobile_number_and_to_show_its_balance() throws Exception
    {
	try
	{
	    walletService.showBalance(null);
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    result = message.equals("Mobile number cannot be NULL");
	}
    }

    @Then("^for showbalance system should print \"([^\"]*)\"$")
    public void for_showbalance_system_should_print(String arg1) throws Exception
    {
	assertTrue(result);
    }

}
