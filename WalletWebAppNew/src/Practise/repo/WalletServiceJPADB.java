package Practise.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import Practise.WalletApp.Account;
import Practise.WalletAppException.WalletException;

@Repository
public class WalletServiceJPADB implements WalletRepo
{
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Account save(Account acc)
    {
	Account foundAcc = findOneByMobile(acc.getMobile());

	if (foundAcc.getName().equals("null"))
	{
	    em.persist(acc);
	}
	else
	{
	    em.merge(acc);
	}

	return acc;
    }

    @Transactional
    public Account findOneByMobile(String mobile) throws WalletException
    {
	TypedQuery<Account> query = em.createQuery(
		"SELECT wa FROM Account wa join fetch wa.aWallet w join fetch wa.allAccTxn allTxn where wa.mobile = ?1",
		Account.class);
	List<Account> accountList = query.setParameter(1, mobile).getResultList();
	if (accountList.isEmpty())
	{
	    return new Account("null", "null", null);
	}
	else // if (accountList.size() == 1)
	{
	    return accountList.get(0);
	}
	/*
	 * else { throw new WalletException(
	 * "Error in finding Account : Count of Finding mobile " + mobile +
	 * " = " + accountList.size()); }
	 */

    }

}
