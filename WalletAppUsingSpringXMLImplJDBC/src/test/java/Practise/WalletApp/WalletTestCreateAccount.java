package Practise.WalletApp;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;

import org.junit.Assert;
import org.springframework.context.support.GenericXmlApplicationContext;

import Practice.WalletAppException.WalletException;

public class WalletTestCreateAccount
{
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("WalletBeanConfig.xml");
    WalletService walletService = ctx.getBean("walletService", WalletService.class);
    boolean result;

    @Given("^mobile number (\\d+) already exists$")
    public void mobile_number_already_exists(int arg1) throws Exception
    {
	System.out.println("Creating dummy account...");
	walletService.createAccount("john", Integer.toString(arg1), new BigDecimal("123"));
    }

    @When("^the user tries to create the account with (\\d+)$")
    public void the_user_tries_to_create_the_account_with(int arg1) throws Exception
    {
	try
	{
	    walletService.createAccount("john", Integer.toString(arg1), new BigDecimal("123"));
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    result = message.equals("Cannot create account because account with this mobile number already exists");
	}
    }

    @Then("^system should print \"([^\"]*)\"$")
    public void system_should_print(String arg1) throws Exception
    {
	Assert.assertTrue(result);
    }

    @Given("^user wants to create an account$")
    public void user_wants_to_create_an_account() throws Exception
    {
	System.out.println("User wants to create an account...");
    }

    @When("^user passes in NULL as name$")
    public void user_passes_in_NULL_as_name() throws Exception
    {
	try
	{
	    walletService.createAccount(null, Integer.toString(12345678), new BigDecimal("123"));
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    result = message.equals("Name cannot be NULL");
	}
    }

    @Given("^user wants to create an account with a mobile number which is not (\\d+) digits$")
    public void user_wants_to_create_an_account_with_a_mobile_number_which_is_not_digits(int arg1) throws Exception
    {
	System.out.println("User wants to create an account with mobile number not " + arg1 + " digits...");
    }

    @When("^user gives (\\d+) as mobile number or user gives (\\d+) as mobile number$")
    public void user_gives_as_mobile_number_or_user_gives_as_mobile_number(int arg1, int arg2) throws Exception
    {
	boolean lessThanDigit = false;
	boolean moreThanDigit = false;

	try
	{
	    walletService.createAccount("john", Integer.toString(arg1), new BigDecimal("123"));
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    lessThanDigit = message.equals("Invalid mobile number");
	}

	try
	{
	    walletService.createAccount("john", Integer.toString(arg2), new BigDecimal("123"));
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    moreThanDigit = message.equals("Invalid mobile number");
	}

	if (lessThanDigit == true && moreThanDigit == true)
	{
	    result = true;
	}
    }

    @Given("^user wants to create an account with a mobile number that includes non numerical values$")
    public void user_wants_to_create_an_account_with_a_mobile_number_that_includes_non_numerical_values()
	    throws Exception
    {
	System.out.println("User wants to create an account with mobile number which is not numberic only...");
    }

    @When("^user gives \"([^\"]*)\" as mobile number while creating the account$")
    public void user_gives_as_mobile_number_while_creating_the_account(String arg1) throws Exception
    {
	try
	{
	    walletService.createAccount("name", arg1, new BigDecimal("123"));
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    result = message.equals("Mobile number cannot contain non numerical values");
	}
    }

    @When("^user passes NULL as mobile number$")
    public void user_passes_NULL_as_mobile_number() throws Exception
    {
	try
	{
	    walletService.createAccount("name", null, new BigDecimal("123"));
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    result = message.equals("Mobile number cannot be NULL");
	}
    }

    @Given("^user wants to deposit less than zero or more than (\\d+)$")
    public void user_wants_to_deposit_less_than_zero_or_more_than(int arg1) throws Exception
    {
	System.out.println("User wants to deposit less than zero or more than " + arg1 + "...");
    }

    @When("^user inputs -(\\d+)	OR user inputs (\\d+)$")
    public void user_inputs_OR_user_inputs(int arg1, int arg2) throws Exception
    {
	arg1 *= -1;

	boolean lessThanZero = false;
	boolean moreThan100k = false;

	try
	{
	    walletService.createAccount("john", Integer.toString(12345678), new BigDecimal(arg1));
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    lessThanZero = message.equals("Deposit is out of limits");
	}

	try
	{
	    walletService.createAccount("john", Integer.toString(12345678), new BigDecimal(arg2));
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    moreThan100k = message.equals("Deposit is out of limits");
	}

	if (lessThanZero == true && moreThan100k == true)
	{
	    result = true;
	}
    }

    @When("^user inputs an empty string \"([^\"]*)\" OR user inputs only whitespaces \"([^\"]*)\" for name$")
    public void user_inputs_an_empty_string_OR_user_inputs_only_whitespaces_for_name(String arg1, String arg2)
	    throws Exception
    {
	boolean emptyStr = false;
	boolean onlyWhiteSpace = false;

	try
	{
	    walletService.createAccount(arg1, Integer.toString(12345678), new BigDecimal(100));
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    emptyStr = message.equals("Name cannot be empty");
	}

	try
	{
	    walletService.createAccount(arg2, Integer.toString(12345678), new BigDecimal(100));
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    onlyWhiteSpace = message.equals("Name cannot be empty");
	}

	if (emptyStr == true && onlyWhiteSpace == true)
	{
	    result = true;
	}
    }

    @When("^user inputs name that includes special character example \"([^\"]*)\"$")
    public void user_inputs_name_that_includes_special_character_example(String arg1) throws Exception
    {
	try
	{
	    walletService.createAccount(arg1, Integer.toString(12345678), new BigDecimal(100));
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    result = message.equals("Name cannot include special characters");
	}
    }

    @When("^user inputs name \"([^\"]*)\"AND user enters amount (\\d+) AND mobile number (\\d+)$")
    public void user_inputs_name_AND_user_enters_amount_AND_mobile_number(String arg1, int arg2, int arg3)
	    throws Exception
    {

	try
	{
	    AccountDao retAcct = walletService.createAccount(arg1, arg3 + "", new BigDecimal(arg2));
	    result = (retAcct.getName() + " is saved!").equals(arg1 + " is saved!");
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println("valid case exception hit -- > " + message);
	}
    }
}