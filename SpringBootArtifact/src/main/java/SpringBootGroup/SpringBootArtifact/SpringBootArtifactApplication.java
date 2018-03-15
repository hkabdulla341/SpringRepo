package SpringBootGroup.SpringBootArtifact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(scanBasePackages = { "walletApp", "repo", "oracleRepo", "walletAppController" })
@SpringBootApplication
@EntityScan("walletApp")
@EnableJpaRepositories({ "repo", "oracleRepo" })
@ComponentScan({ "walletAppController", "walletApp"})
public class SpringBootArtifactApplication
{
    public static void main(String[] args)
    {
	SpringApplication.run(SpringBootArtifactApplication.class, args);
    }
}
