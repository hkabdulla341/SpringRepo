package Practice.WalletAppException;

public class WalletException extends RuntimeException
{
    public WalletException(String message)
    {
	super(message);
    }

    @Override
    public String getMessage()
    {
	// TODO Auto-generated method stub
	return super.getMessage();
    }
}
