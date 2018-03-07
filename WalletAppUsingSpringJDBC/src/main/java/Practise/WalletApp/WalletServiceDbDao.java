package Practise.WalletApp;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import Practice.WalletAppException.WalletException;

public class WalletServiceDbDao  implements WalletRepo
{
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private UpdateAccount updateAccount;
    private UpdateWallet updateWallet;
    private InsertAccount insertAccount;
    private InsertWallet insertWallet;

    public void setDataSource(DataSource dataSource)
    {
	this.dataSource = dataSource;
	this.jdbcTemplate = new JdbcTemplate(dataSource);
	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.updateAccount = new UpdateAccount(dataSource);
	this.updateWallet = new UpdateWallet(dataSource);
	this.insertAccount = new InsertAccount(dataSource);
	this.insertWallet = new InsertWallet(dataSource);
    }

    private Account systemFind(String mobile)
    {
	String sql = "select wa.ACCOUNTID, wa.name, wa.mobile, w.balance from walletaccount wa "
		+ "join wallet w on wa.walletid = w.id where wa.mobile = '" + mobile + "'";

	return jdbcTemplate.query(sql, new ResultSetExtractor<Account>()
	{
	    public Account extractData(ResultSet rs) throws SQLException, DataAccessException
	    {
		Account tempAcct = new Account("null", "null", null);
		while (rs.next())
		{
		    String accoountid = rs.getString(1);
		    String name = rs.getString(2);
		    String mobile = rs.getString(3);
		    String amount = rs.getString(4);

		    tempAcct = new Account(name, mobile, new BigDecimal(amount));
		    tempAcct.setAccountid(accoountid);
		}

		return tempAcct;
	    }
	});
    }

    private void systemInsertAccountDb(Account acc)
    {
	int idForWalletDb = getNextWalletSequence();
	systemInsertWalletDb(acc.getaWallet(), idForWalletDb);

	int idForAccountDb = getNextAccountSequence();
	Map<String, Object> paramMap = new HashMap<String, Object>();
	paramMap.put("account_id", acc.getAccountid());
	paramMap.put("name", acc.getName());
	paramMap.put("mobile", acc.getMobile());
	paramMap.put("id", idForAccountDb);
	paramMap.put("wallet_id", idForWalletDb);

	insertAccount.updateByNamedParam(paramMap);
    }

    private void systemInsertWalletDb(Wallet getaWallet, int idForWalletDb)
    {
	Map<String, Object> paramMap = new HashMap<String, Object>();
	paramMap.put("wallet_id", getaWallet.getId());
	paramMap.put("balance", getaWallet.getBalance());
	paramMap.put("id", idForWalletDb);

	insertWallet.updateByNamedParam(paramMap);
    }

    private void systemUpdateAccountDb(Account acc)
    {
	int idForDb = getWalletIdForUpdate(acc.getMobile());
	systemUpdateWallet(acc.getaWallet(), idForDb);

	Map<String, Object> paramMap = new HashMap<String, Object>();
	paramMap.put("account_id", acc.getAccountid());
	paramMap.put("name", acc.getName());
	paramMap.put("mobile", acc.getMobile());

	updateAccount.updateByNamedParam(paramMap);
    }

    private void systemUpdateWallet(Wallet getaWallet, int idForDb)
    {
	Map<String, Object> paramMap = new HashMap<String, Object>();
	paramMap.put("wallet_id", getaWallet.getId());
	paramMap.put("balance", getaWallet.getBalance());
	paramMap.put("id", idForDb);

	updateWallet.updateByNamedParam(paramMap);
    }

    private int getWalletIdForUpdate(String mobile)
    {
	String sql = "select walletid from walletaccount where mobile = :mobile";
	Map<String, Object> namedParameter = new HashMap<String, Object>();
	namedParameter.put("mobile", mobile);
	return namedParameterJdbcTemplate.queryForObject(sql, namedParameter, Integer.class);
    }

    private int getNextAccountSequence()
    {
	String sql = "select PK_AccountID.NEXTVAL from dual";
	Map<String, Object> namedParameter = new HashMap<String, Object>();
	return namedParameterJdbcTemplate.queryForObject(sql, namedParameter, Integer.class);
    }

    public int getNextWalletSequence()
    {
	String sql = "select PK_WALLETID.NEXTVAL from dual";
	Map<String, Object> namedParameter = new HashMap<String, Object>();
	return namedParameterJdbcTemplate.queryForObject(sql, namedParameter, Integer.class);
    }

    @Override
    public boolean save(Account acc)
    {
	Account systemFoundAccount = systemFind(acc.getMobile());

	if (!systemFoundAccount.getName().equals("null"))
	{
	    systemUpdateAccountDb(acc);
	}
	else
	{
	    systemInsertAccountDb(acc);
	}

	return false;
    }

    @Override
    public Account find(String mobile)
    {
	Account systemFoundAccount = systemFind(mobile);

	if (systemFoundAccount.getName().equals("null"))
	{
	    return new Account("null", "null", null);
	}
	else
	{
	    return systemFoundAccount;
	}
    }

}
