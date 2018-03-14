package Practise.repo;

import Practise.WalletApp.Account;
import Practise.WalletAppException.WalletException;

public interface WalletRepo
{

    public Account save(Account acc);

    public Account findOneByMobile(String mobile) throws WalletException;
}
