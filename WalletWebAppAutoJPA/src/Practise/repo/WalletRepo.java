package Practise.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import Practise.WalletApp.Account;
import Practise.WalletAppException.WalletException;

public interface WalletRepo extends JpaRepository<Account, Integer>
{
    public Account findOneByMobile(String mobile) throws WalletException;
}
