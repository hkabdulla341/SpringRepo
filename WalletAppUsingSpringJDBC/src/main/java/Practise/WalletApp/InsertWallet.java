package Practise.WalletApp;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertWallet extends SqlUpdate
{
    private static final String INSERT_WALLET = "Insert into wallet (id, walletid, balance) values (:id, :wallet_id, :balance)";

    public InsertWallet(DataSource dataSource)
    {
	super(dataSource, INSERT_WALLET);
	super.declareParameter(new SqlParameter("wallet_id", Types.INTEGER));
	super.declareParameter(new SqlParameter("balance", Types.DOUBLE));
	super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
