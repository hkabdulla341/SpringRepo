package Practise.Utility;

import java.math.BigDecimal;

import Practice.WalletAppException.WalletException;

public class ServiceUtility
{
    private ServiceUtility()
    {

    }

    static public boolean containInvalidNamePatten(String name)
    {
	boolean valid = name.matches("[a-zA-Z']+");

	if (valid)
	    return false;
	else
	    return true;
    }

    static public boolean isNameValid(String name)
    {
	if (name == null)
	{
	    throw new WalletException("Name cannot be NULL");
	}

	name = name.trim();

	if (name.isEmpty())
	{
	    throw new WalletException("Name cannot be empty");
	}

	if (ServiceUtility.containInvalidNamePatten(name))
	{
	    throw new WalletException("Name cannot include special characters");
	}

	return true;
    }

    static public boolean isAmountValid(BigDecimal amt)
    {
	BigDecimal maxBalance = new BigDecimal("100000");

	if (amt.compareTo(BigDecimal.ZERO) < 0 || maxBalance.compareTo(amt) < 0)
	{
	    throw new WalletException("Deposit is out of limits");
	}

	return true;
    }

    static public boolean isAmountValid(String amt)
    {
	BigDecimal amtDec = new BigDecimal(amt);
	return isAmountValid(amtDec);
    }

    static public boolean isMobileValid(String mobile)
    {
	if (mobile == null)
	{
	    throw new WalletException("Mobile number cannot be NULL");
	}

	try
	{
	    Integer.parseInt(mobile);
	}
	catch (NumberFormatException ex)
	{
	    throw new WalletException("Mobile number cannot contain non numerical values");
	}

	if (mobile.length() != 8)
	{
	    throw new WalletException("Invalid mobile number");
	}

	return true;
    }
}
