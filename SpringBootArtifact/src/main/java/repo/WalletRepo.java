package repo;

import org.springframework.data.jpa.repository.JpaRepository;

import walletApp.Account;
import walletAppException.WalletException;

public interface WalletRepo extends JpaRepository<Account, Integer>
{
    public Account findOneByMobile(String mobile) throws WalletException;
}
