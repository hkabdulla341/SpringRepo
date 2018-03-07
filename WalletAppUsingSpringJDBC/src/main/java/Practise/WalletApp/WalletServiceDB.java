package Practise.WalletApp;

import java.math.BigDecimal;
import java.sql.*;

import Practice.Database.OracleDatabase;
import Practice.WalletAppException.WalletException;

public class WalletServiceDB implements WalletRepo
{
    private Connection conn;

    @Override
    public boolean save(Account acc)
    {
	conn = OracleDatabase.getConnection();

	try
	{
	    PreparedStatement pStmt = conn.prepareStatement("Select * from walletaccount where mobile = ?");
	    pStmt.setInt(1, Integer.parseInt(acc.getMobile()));
	    ResultSet rsWalletID = pStmt.executeQuery();

	    if (!rsWalletID.next())
	    {
		Statement stmt = conn.createStatement();
		ResultSet rsWalletCount = stmt.executeQuery("select PK_WALLETID.NEXTVAL from dual");

		rsWalletCount.next();
		int walletcount = rsWalletCount.getInt("NEXTVAL");
		String insertIntoWalletSQL = "Insert into wallet (id, walletid, balance) values (" + walletcount
			+ ",?,?)";

		pStmt = conn.prepareStatement(insertIntoWalletSQL);
		pStmt.setInt(1, acc.getaWallet().getId());
		pStmt.setDouble(2, acc.getaWallet().getBalance().doubleValue());
		int responseFromDBInsertWallet = pStmt.executeUpdate();

		ResultSet rsAccountCount = stmt.executeQuery("select PK_AccountID.NEXTVAL from dual");
		rsAccountCount.next();
		int acctcount = rsAccountCount.getInt("NEXTVAL");
		String insertIntoAcctSQL = "Insert into walletaccount(id,accountid,name,mobile,walletid) values ("
			+ acctcount + ",?,?,?," + walletcount + ")";

		pStmt = conn.prepareStatement(insertIntoAcctSQL);
		pStmt.setString(1, acc.getAccountid().toString());
		pStmt.setString(2, acc.getName());
		pStmt.setInt(3, Integer.parseInt(acc.getMobile()));
		int responseFromDBInsertWalletAcct = pStmt.executeUpdate();

		if (responseFromDBInsertWallet == 1 && responseFromDBInsertWalletAcct == 1)
		{
		    return true;
		}
		else if (responseFromDBInsertWallet == 0)
		{
		    throw new WalletException("Error saving to DB : Failed during saving to wallet (Insert)");
		}
		else if (responseFromDBInsertWalletAcct == 0)
		{
		    throw new WalletException("Error saving to DB : Failed during saving to walletaccount (Insert)");
		}

	    }
	    else
	    {
		int[] responseFromDBfromSql = new int[2];

		String updateWalletAccSQL = "Update walletaccount set accountid = ?, name = ? ,MOBILE = ? where mobile = ?";

		PreparedStatement pStmt2 = conn.prepareStatement(updateWalletAccSQL);
		pStmt2.setString(1, acc.getAccountid().toString());
		pStmt2.setString(2, acc.getName());
		pStmt2.setInt(3, Integer.parseInt(acc.getMobile()));
		pStmt2.setInt(4, Integer.parseInt(acc.getMobile()));
		// pStmt2.addBatch();
		responseFromDBfromSql[0] = pStmt2.executeUpdate();

		int id = rsWalletID.getInt(5);
		String updateWalletSQL = "Update wallet set WALLETID = ?, BALANCE = ? where id = ?";

		pStmt2 = conn.prepareStatement(updateWalletSQL);
		pStmt2.setInt(1, acc.getaWallet().getId());
		pStmt2.setDouble(2, acc.getaWallet().getBalance().doubleValue());
		pStmt2.setInt(3, id);
		// pStmt2.addBatch();
		responseFromDBfromSql[1] = pStmt2.executeUpdate();

		// responseFromDBfromSql = pStmt2.executeBatch();

		if (responseFromDBfromSql[0] == 1 && responseFromDBfromSql[1] == 1)
		{
		    return true;
		}
		else if (responseFromDBfromSql[0] == 0)
		{
		    throw new WalletException("Error saving to DB : Failed during saving to wallet (Update)");
		}
		else if (responseFromDBfromSql[1] == 0)
		{
		    throw new WalletException("Error saving to DB : Failed during saving to walletaccount (Update)");
		}
	    }

	    return true;
	}
	catch (SQLException e)
	{
	    throw new WalletException(
		    "Error with save : " + e.getMessage() + System.lineSeparator() + e.getStackTrace().toString());
	}
    }

    @Override
    public Account find(String mobile) throws WalletException
    {
	conn = OracleDatabase.getConnection();

	try
	{
	    PreparedStatement pStmt = conn.prepareStatement("Select * from walletaccount where mobile = ?");
	    pStmt.setString(1, mobile);
	    ResultSet rsWalletAccount = pStmt.executeQuery();

	    if (!rsWalletAccount.next())
	    {
		return new Account("null", "null", null);
	    }
	    else
	    {
		String accountid, name, mobileAcc;
		BigDecimal amt;

		accountid = rsWalletAccount.getString(2);
		name = rsWalletAccount.getString(3);
		mobileAcc = rsWalletAccount.getString(4);

		int walletUniqueID = rsWalletAccount.getInt(5);
		PreparedStatement pStmt2 = conn.prepareStatement("Select * from wallet where id = ?");
		pStmt2.setInt(1, walletUniqueID);
		ResultSet rsWallet = pStmt2.executeQuery();
		rsWallet.next();

		amt = new BigDecimal(rsWallet.getDouble(3));

		Account newAcct = new Account(name, mobileAcc, amt);
		newAcct.setAccountid(accountid);
		return newAcct;
	    }
	}
	catch (SQLException e)
	{
	    throw new WalletException(
		    "Error with save : " + e.getMessage() + System.lineSeparator() + e.getStackTrace().toString());
	}
    }

}
