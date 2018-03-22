package walletApp;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repo.WalletRepo;
import utility.ServiceUtility;
import walletAppException.WalletException;

@Service(value = "walletService")
public class WalletService
{
    @Autowired
    private WalletRepo aWalletServiceDao;

//    public WalletService()
//    {
//
//    }
//
//    public WalletService(WalletRepo repo)
//    {
//	this.aWalletServiceDao = repo;
//    }

    public AccountDao createAccount(String name, String mobile, String amount)
    {
	BigDecimal amt = new BigDecimal(amount);

	ServiceUtility.isNameValid(name);
	ServiceUtility.isAmountValid(amt);
	ServiceUtility.isMobileValid(mobile);

	name = name.trim();

	Account existingAccount = aWalletServiceDao.findOneByMobile(mobile);

	if (existingAccount != null)
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

	Account foundAcct = aWalletServiceDao.findOneByMobile(mobile);

	if (foundAcct == null)
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

	Account acc = aWalletServiceDao.findOneByMobile(mobile);

	if (acc == null)
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
	    acc.addNewTransaction("Deposit", amount);
	}

	aWalletServiceDao.save(acc);

	return new AccountDao(acc);
    }

    public AccountDao withdraw(String mobile, String amount)
    {
	ServiceUtility.isMobileValid(mobile);
	ServiceUtility.isAmountValid(amount);

	Account acc = aWalletServiceDao.findOneByMobile(mobile);

	if (acc == null)
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
	    acc.addNewTransaction("Withdraw", amount);
	}

	aWalletServiceDao.save(acc);

	return new AccountDao(acc);
    }

    public AccountDao viewTransaction(String mobile)
    {
	ServiceUtility.isMobileValid(mobile);

	Account acc = aWalletServiceDao.findOneByMobile(mobile);

	if (acc == null)
	{
	    throw new WalletException("Mobile number does not exist");
	}

	return new AccountDao(acc);
    }
}
