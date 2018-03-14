package Practise.WalletApp;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Assert;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import Practice.WalletAppException.WalletException;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WalletTestTransaction
{
    /*
     * AbstractApplicationContext ctx = new
     * AnnotationConfigApplicationContext(RegisterSpring.class); WalletService
     * walletService = ctx.getBean("walletService", WalletService.class);
     */
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("WalletBeanConfig.xml");
    WalletService walletService = ctx.getBean("walletService", WalletService.class);
    boolean result;

    @Given("^in WalletTransaction, there is account with mobile \"([^\"]*)\" and name \"([^\"]*)\" and amount \"([^\"]*)\"$")
    public void in_WalletTransaction_there_is_account_with_mobile_and_name_and_amount(String arg1, String arg2,
	    String arg3) throws Exception
    {
	// Write code here that turns the phrase above into concrete actions
	throw new PendingException();
    }

    @When("^in WalletTransaction, user inputs mobile number (\\d+)$")
    public void in_WalletTransaction_user_inputs_mobile_number(int arg1) throws Exception
    {
	// Write code here that turns the phrase above into concrete actions
	throw new PendingException();
    }

    @Then("^WalletTransaction system should print \"([^\"]*)\"$")
    public void wallettransaction_system_should_print(String arg1) throws Exception
    {
	// Write code here that turns the phrase above into concrete actions
	throw new PendingException();
    }

    @Given("^in WalletTransaction, user wants to access account with mobile number which is not (\\d+) digits$")
    public void in_WalletTransaction_user_wants_to_access_account_with_mobile_number_which_is_not_digits(int arg1)
	    throws Exception
    {
	// Write code here that turns the phrase above into concrete actions
	throw new PendingException();
    }

    @When("^in WalletTransaction, user gives (\\d+) as mobile number OR user gives (\\d+) as mobile number$")
    public void in_WalletTransaction_user_gives_as_mobile_number_OR_user_gives_as_mobile_number(int arg1, int arg2)
	    throws Exception
    {
	// Write code here that turns the phrase above into concrete actions
	throw new PendingException();
    }

    @Given("^in WalletTransaction,user wants to access account with a mobile number that includes non numerical values$")
    public void in_WalletTransaction_user_wants_to_access_account_with_a_mobile_number_that_includes_non_numerical_values()
	    throws Exception
    {
	// Write code here that turns the phrase above into concrete actions
	throw new PendingException();
    }

    @When("^in WalletTransaction, user gives \"([^\"]*)\" as mobile number while depositing to a account$")
    public void in_WalletTransaction_user_gives_as_mobile_number_while_depositing_to_a_account(String arg1)
	    throws Exception
    {
	// Write code here that turns the phrase above into concrete actions
	throw new PendingException();
    }

    @Given("^in WalletTransaction, user wants to access an account$")
    public void in_WalletTransaction_user_wants_to_access_an_account() throws Exception
    {
	// Write code here that turns the phrase above into concrete actions
	throw new PendingException();
    }

    @When("^in WalletTransaction, user passes NULL as mobile number$")
    public void in_WalletTransaction_user_passes_NULL_as_mobile_number() throws Exception
    {
	// Write code here that turns the phrase above into concrete actions
	throw new PendingException();
    }

    @When("^in WalletTransaction, account with mobile \"([^\"]*)\", deposit (\\d+) times of amount \"([^\"]*)\" and withdraw (\\d+) times of amount \"([^\"]*)\"$")
    public void in_WalletTransaction_account_with_mobile_deposit_times_of_amount_and_withdraw_times_of_amount(
	    String arg1, int arg2, String arg3, int arg4, String arg5) throws Exception
    {
	// Write code here that turns the phrase above into concrete actions
	throw new PendingException();
    }
}
