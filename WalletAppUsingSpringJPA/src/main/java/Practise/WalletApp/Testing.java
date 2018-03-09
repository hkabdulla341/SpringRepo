package Practise.WalletApp;

import java.math.BigDecimal;
import java.sql.ResultSet;

import javax.persistence.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import Practice.WalletAppException.WalletException;

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

	AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(RegisterSpring.class);
	WalletService walletService = ctx.getBean("walletService", WalletService.class);

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

	System.out.println(result);
    }

    public static void main(String[] args)
    {
	new Testing().run();
    }
}
