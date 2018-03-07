package Practise.WalletApp;

import java.math.BigDecimal;
import java.sql.ResultSet;

import Practice.Database.OracleDatabase;
import Practice.WalletAppException.WalletException;

public class Testing
{
    public WalletService walletService;
    public boolean result;

    public Testing()
    {
	walletService = new WalletService();
	result = false;
    }

    public void run()
    {
	// String arg1 = "John";
	String mobile = "22334466";
	String amount = "50000";
	String amount2 = "1000";
	String name = "john six";

	try
	{
	    AccountDao retAcct = walletService.createAccount(name, Integer.toString(12345678), new BigDecimal(100));
	    result = (retAcct.getName() + " is saved!").equals(name + " is saved!");
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	}

	System.out.println(result);
    }

    public static void main(String[] args)
    {
	new Testing().run();
    }
}
