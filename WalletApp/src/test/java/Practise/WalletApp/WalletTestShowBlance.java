package Practise.WalletApp;

import java.math.BigDecimal;

import org.junit.Assert;

import Practice.WalletAppException.WalletException;
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
	    System.out.println("msg = " + message);
	    result = message.equals("Mobile number does not exist");
	    System.out.println("result = " + result);
	}
    }

    @Then("^for showblance system should print \"([^\"]*)\"$")
    public void for_showblance_system_should_print(String arg1) throws Exception
    {
	Assert.assertTrue(result);
    }
}
