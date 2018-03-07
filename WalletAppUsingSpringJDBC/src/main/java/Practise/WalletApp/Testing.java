package Practise.WalletApp;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import Practice.Database.OracleDatabase;
import Practice.WalletAppException.WalletException;

public class Testing
{
    public WalletService walletService;
    public boolean result;

    public Testing()
    {
	// @SuppressWarnings("resource")
	// GenericXmlApplicationContext ctx = new
	// GenericXmlApplicationContext("WalletBeanConfig.xml");
	// walletService = ctx.getBean("walletService", WalletService.class);

	DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
	WalletServiceDbDao repo = new WalletServiceDbDao();
	repo.setDataSource(ds);
	walletService = new WalletService(repo);

	result = false;
    }

    public void run()
    {
	// String arg1 = "John";
	String mobile = "11122233";
	String amount = "50000";
	String amount2 = "987";
	String name = "john six";

	try
	{
	    AccountDao retAcct = walletService.withdraw(mobile, amount2);
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
