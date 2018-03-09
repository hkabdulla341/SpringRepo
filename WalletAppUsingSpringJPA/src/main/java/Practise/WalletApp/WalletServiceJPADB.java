package Practise.WalletApp;

import java.util.List;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Practice.WalletAppException.WalletException;

@Repository
public class WalletServiceJPADB implements WalletRepo
{
    @Autowired
    private EntityManager em;

    public WalletServiceJPADB(EntityManager em)
    {
	this.em = em;
    }

    public boolean save(Account acc)
    {
	em.getTransaction().begin();
	em.persist(acc);
	em.getTransaction().commit();

	return true;
    }

    public Account find(String mobile) throws WalletException
    {

	TypedQuery<Account> query = em.createQuery("SELECT wa FROM Account wa where wa.mobile = ?1", Account.class);
	List<Account> accountList = query.setParameter(1, mobile).getResultList();
	if (accountList.isEmpty())
	{
	    return new Account("null", "null", null);
	}
	else if (accountList.size() == 1)
	{
	    return accountList.get(0);
	}
	else
	{
	    throw new WalletException(
		    "Error in finding Account : Count of Finding mobile " + mobile + " = " + accountList.size());
	}

    }

}
