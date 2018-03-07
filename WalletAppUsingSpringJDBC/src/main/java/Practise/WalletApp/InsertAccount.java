package Practise.WalletApp;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertAccount extends SqlUpdate
{
    private static final String INSERT_ACCOUNT = "Insert into walletaccount(id,accountid,name,"
	    + "mobile,walletid) values (:id, :account_id, :name, :mobile, :wallet_id)";

    public InsertAccount(DataSource dataSource)
    {
	super(dataSource, INSERT_ACCOUNT);
	super.declareParameter(new SqlParameter("account_id", Types.VARCHAR));
	super.declareParameter(new SqlParameter("name", Types.VARCHAR));
	super.declareParameter(new SqlParameter("mobile", Types.VARCHAR));
	super.declareParameter(new SqlParameter("id", Types.INTEGER));
	super.declareParameter(new SqlParameter("wallet_id", Types.INTEGER));
    }
}
