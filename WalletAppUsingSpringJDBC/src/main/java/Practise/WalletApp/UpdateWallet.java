package Practise.WalletApp;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateWallet extends SqlUpdate
{
    private static final String UPDATE_WALLET = "Update wallet set WALLETID = :wallet_id, BALANCE = :balance where "
	    + "id = :id";

    public UpdateWallet(DataSource dataSource)
    {
	super(dataSource, UPDATE_WALLET);
	super.declareParameter(new SqlParameter("wallet_id", Types.INTEGER));
	super.declareParameter(new SqlParameter("balance", Types.DOUBLE));
	super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
