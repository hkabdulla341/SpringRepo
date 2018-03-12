package walletAppController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletTest
{
    @RequestMapping("/")
    public String returnString()
    {
	return "Hello World";
    }
}
