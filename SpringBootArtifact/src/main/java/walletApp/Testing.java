package walletApp;

import java.math.BigDecimal;

import org.springframework.context.support.GenericXmlApplicationContext;

import walletAppException.WalletException;

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
	// new Testing().run();

	int[][] a = new int[2][];
	
	a[0][0] = 1;
	a[0][1] = 2;
	a[1][0] = 3;
	a[1][1] = 4;
	a[1][2] = 5;
	a[1][3] = 6;
    }
}
