package mysystem.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfiguration {


    @Autowired
    private Environment env;


    @Bean(name = "searchDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("search.connection.driver_class"));
        dataSource.setUrl(env.getProperty("search.connection.url"));
        dataSource.setUsername(env.getProperty("search.connection.username"));
        dataSource.setPassword(env.getProperty("search.connection.password"));
        return dataSource;
    }


}
