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
	return super.getMessage();
    }
}
