package Practise.Utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;

import Practise.WalletApp.Account;
//import Practise.WalletApp.WalletService;
//import Practise.WalletApp.WalletServiceDao;

@Configuration
@ComponentScan(basePackages = { "Practise.WalletApp" })
public class AppConfig
{
    // @Bean(name = "walletService")
    // public WalletService walletService()
    // {
    // return new WalletService();
    // }
    //
    // @Bean(name = "walletServiceDao")
    // public WalletServiceDao walletServiceDao()
    // {
    // return new WalletServiceDao();
    // }

    @Bean(name = "allAccounts")
    public Map<String, Account> getResourceMap()
    {
	return new HashMap<String, Account>();
    }
}
