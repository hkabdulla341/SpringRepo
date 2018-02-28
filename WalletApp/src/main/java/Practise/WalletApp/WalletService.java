package Practise.WalletApp;

import java.math.BigDecimal;
import Practice.WalletAppException.WalletException;
import Practise.Utility.ServiceUtility;

public class WalletService
{
    private WalletServiceDao aWalletServiceDao;

    public WalletService()
    {
	aWalletServiceDao = new WalletServiceDao();
    }

    public AccountDao createAccount(String name, String mobile, BigDecimal amt)
    {
	ServiceUtility.isNameValid(name);
	ServiceUtility.isAmountValid(amt);
	ServiceUtility.isMobileValid(mobile);
	
	name = name.trim();

	AccountDao existingAccount = aWalletServiceDao.find(mobile);

	if (!existingAccount.getName().equals("null"))
	{
	    throw new WalletException("Cannot create account because account with this mobile number already exists");
	}

	Account newAcct = new Account(name, mobile, amt);
	AccountDao newAcctDao = new AccountDao(newAcct);
	
	aWalletServiceDao.save(newAcct);
	
	return newAcctDao;
    }
    
    public AccountDao showBalance(String mobile)
    {
	ServiceUtility.isMobileValid(mobile);
	
	AccountDao foundAcct = aWalletServiceDao.find(mobile);
	
	if(foundAcct.getName().equals("null"))
	{
	    throw new WalletException("Mobile number does not exist");
	}
	else
	{
	    return foundAcct;
	}
    }
    
}
