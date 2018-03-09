package Practise.WalletApp;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import Practice.WalletAppException.WalletException;
import Practise.Utility.ServiceUtility;

@Service(value = "walletService")
public class WalletService
{
    private WalletRepo aWalletServiceDao;

    public WalletService(WalletRepo repo)
    {
	this.aWalletServiceDao = repo;
    }

    public AccountDao createAccount(String name, String mobile, BigDecimal amt)
    {
	ServiceUtility.isNameValid(name);
	ServiceUtility.isAmountValid(amt);
	ServiceUtility.isMobileValid(mobile);

	name = name.trim();

	Account existingAccount = aWalletServiceDao.find(mobile);

	if (!existingAccount.getName().equals("null"))
	{
	    throw new WalletException("Cannot create account because account with this mobile number already exists");
	}

	Account newAcct = new Account(name, mobile, amt);
	newAcct.addNewTransaction("Create Account", amt.toString());

	AccountDao newAcctDao = new AccountDao(newAcct);

	aWalletServiceDao.save(newAcct);

	return newAcctDao;
    }

    public AccountDao showBalance(String mobile)
    {
	ServiceUtility.isMobileValid(mobile);

	Account foundAcct = aWalletServiceDao.find(mobile);

	if (foundAcct.getName().equals("null"))
	{
	    throw new WalletException("Mobile number does not exist");
	}
	else
	{
	    return new AccountDao(foundAcct);
	}
    }

    public AccountDao deposit(String mobile, String amount)
    {
	ServiceUtility.isMobileValid(mobile);
	ServiceUtility.isAmountValid(amount);

	Account acc = aWalletServiceDao.find(mobile);

	if (acc.getName().equals("null"))
	{
	    throw new WalletException("Mobile number does not exist");
	}

	BigDecimal totalBal = acc.getaWallet().getBalance().add(new BigDecimal(amount));

	if (totalBal.compareTo(new BigDecimal("100000")) > 0)
	{
	    throw new WalletException("Maximum balance can only be 100000");
	}
	else
	{
	    acc.getaWallet().setBalance(totalBal);
	    acc.addNewTransaction("Deposit", totalBal.doubleValue() + "");
	}

	aWalletServiceDao.save(acc);

	return new AccountDao(acc);
    }

    public AccountDao withdraw(String mobile, String amount)
    {
	ServiceUtility.isMobileValid(mobile);
	ServiceUtility.isAmountValid(amount);

	Account acc = aWalletServiceDao.find(mobile);

	if (acc.getName().equals("null"))
	{
	    throw new WalletException("Mobile number does not exist");
	}

	BigDecimal totalBal = acc.getaWallet().getBalance().subtract(new BigDecimal(amount));

	if (totalBal.compareTo(BigDecimal.ZERO) < 0)
	{
	    throw new WalletException("Insufficient funds for withdrawal");
	}
	else
	{
	    acc.getaWallet().setBalance(totalBal);
	    acc.addNewTransaction("Withdraw", totalBal.doubleValue() + "");
	}

	aWalletServiceDao.save(acc);

	return new AccountDao(acc);
    }
}
