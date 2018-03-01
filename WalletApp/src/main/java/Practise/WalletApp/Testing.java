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
	// String arg1 = "John";
	String mobile = "22334466";
	String amount = "50000";
	String amount2 = "1000";
	String name = "john";

	walletService.createAccount(name, mobile, new BigDecimal(amount));

	try
	{
	    AccountDao acc = walletService.deposit(mobile, amount2);
	    String message = acc.display();
	    System.out.println(message);
	    result = message.equals("Balance of account " + acc.getMobile() + " is " + acc.getWalletBalance());
	}
	catch (WalletException ex)
	{
	    String message = ex.getMessage();
	    System.out.println(message);
	    result = message.equals("Maximum balance can only be 100000");
	}

	System.out.println(result);
    }

    public static void main(String[] args)
    {
	Testing t = new Testing();
	t.run();
    }
}
