package Practise.WalletApp;

import java.math.BigDecimal;

import org.springframework.context.support.GenericXmlApplicationContext;

import Practise.WalletAppException.WalletException;

public class Testing
{
    String name = "john";
    String mobile = "12345678";
    BigDecimal bigDec = new BigDecimal("100");
    boolean result = false;

    public Testing()
    {
    }

    public void run()
    {

	String arg1 = "John Two";
	String arg2 = "1000";
	String arg3 = "12312312";

	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("WalletBeanConfig.xml");
	WalletService walletService = ctx.getBean("walletService", WalletService.class);

	walletService.createAccount(arg1, arg3, arg2);

	try
	{
	    AccountDao acc = walletService.deposit(arg3, "" + arg2);
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
	new Testing().run();
    }
}
