package Practise.WalletApp;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateAccount extends SqlUpdate
{
    private static final String UPDATE_ACCOUNT = "Update walletaccount set accountid = :account_id, name = :name ,"
	    + "MOBILE = :mobile where mobile = :mobile";

    public UpdateAccount(DataSource dataSource)
    {
	super(dataSource, UPDATE_ACCOUNT);
	super.declareParameter(new SqlParameter("account_id", Types.VARCHAR));
	super.declareParameter(new SqlParameter("name", Types.VARCHAR));
	super.declareParameter(new SqlParameter("mobile", Types.VARCHAR));
	
    }
}
