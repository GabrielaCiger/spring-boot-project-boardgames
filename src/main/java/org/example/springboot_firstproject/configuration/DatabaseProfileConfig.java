package org.example.springboot_firstproject.configuration;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import javax.sql.DataSource;


@Configuration
public class DatabaseProfileConfig {
//
//    @Bean
//    @Profile("h2")
//    public DataSource dataSourceH2() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setJdbcUrl("jdbc:h2:mem:springbootdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE;MODE=MySQL;");
//        dataSource.setUsername("root");
//        dataSource.setPassword("1234");
//        return dataSource;
//    }
//
//    @Bean
//    @Profile("mysql")
//    public DataSource dataSourceMySql() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/springbootdb");
//        dataSource.setUsername("root");
//        dataSource.setPassword("1234");
//        return dataSource;
//    }

}
