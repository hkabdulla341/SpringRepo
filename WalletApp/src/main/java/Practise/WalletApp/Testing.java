package Practise.WalletApp;

import java.math.BigDecimal;

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
	String arg1 = "John";
	String arg2 = "12345678";
	int arg3 = 100;

	walletService.createAccount(arg1, "" + arg2, new BigDecimal(arg3));
	System.out.println("Created account with mobile number " + arg2 + ", name as " + arg1 + "with amount " + arg3);

	String number = "87654321";
	
	try
	{
	    walletService.showBalance("" + number);
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println("msg = " + message);
	    result = message.equals("Mobile number does not exist");
	}
	finally
	{
	    System.out.println(result);
	}
    }

    public static void main(String[] args)
    {
	Testing t = new Testing();
	t.run();
    }
}
