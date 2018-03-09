package Practise.WalletApp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "Practise.WalletApp" })
public class RegisterSpring
{

    @Bean
    public EntityManager em()
    {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

	EntityManager ema = emf.createEntityManager();
	return ema;
    }

}
