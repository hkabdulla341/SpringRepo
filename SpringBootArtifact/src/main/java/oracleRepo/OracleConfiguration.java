package oracleRepo;

import java.sql.SQLException;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@ConfigurationProperties("oracle")
public class OracleConfiguration
{
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String url;

    public OracleConfiguration()
    {
    }

    public void setUsername(String username)
    {
	this.username = username;
    }

    public void setPassword(String password)
    {
	this.password = password;
    }

    public void setUrl(String url)
    {
	this.url = url;
    }

    @Bean
    public OracleDataSource dataSource(DataSourceProperties properties)
    {
	OracleDataSource ds = (OracleDataSource) properties.initializeDataSourceBuilder().type(OracleDataSource.class)
		.build();
	ds.setDataSourceName(properties.getName());
	return ds;
    }
}
