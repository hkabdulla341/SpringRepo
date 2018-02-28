package Practise.WalletApp;

import Practice.WalletAppException.WalletException;

public interface WalletRepo
{
    public boolean save(Account acc);

    public AccountDao find(String mobile) throws WalletException;
}
