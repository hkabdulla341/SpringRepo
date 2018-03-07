package Practice.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Practice.WalletAppException.WalletException;

public class OracleDatabase
{

    public static Connection getConnection()
    {

	Connection con;

	try
	{
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	    con = DriverManager.getConnection(jdbcURL, "hr", "hr");
	}
	catch (ClassNotFoundException | SQLException e)
	{
	    throw new WalletException("Error in Database connection : " + e.getMessage());
	}

	return con;
    }

}
