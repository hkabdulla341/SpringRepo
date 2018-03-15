package walletAppController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Practise.WalletApp.AccountDao;
import Practise.WalletApp.WalletService;

@RestController
public class WalletController
{
    @Autowired
    private WalletService walletService;

    @RequestMapping(value = "/")
    public String test(String name, String mobile, String amount)
    {
	return "hello world";
    }

    @RequestMapping(value = "/CreateAccount")
    public AccountDao createNewAcct(String name, String mobile, String amount)
    {
	return walletService.createAccount(name, mobile, amount);
    }

    @RequestMapping(value = "/ShowBalance")
    public AccountDao showAcctBal(String mobile)
    {

	AccountDao acctDao = walletService.showBalance(mobile);

	return acctDao;
    }

    @RequestMapping(value = "/Deposit")
    public AccountDao depositToAcct(String mobile, String amount)
    {
	return walletService.deposit(mobile, amount);
    }

    @RequestMapping(value = "/Withdraw")
    public AccountDao withdrawFromAcct(String mobile, String amount)
    {
	return walletService.withdraw(mobile, amount);
    }

}
