package com.system.gestion.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan({ "com.system.gestion.Application" })
@PropertySource( value={"classpath:application.properties"})
public class ParametersConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public String portPostgres() {
        return environment.getRequiredProperty("jdbc.portPostgres");
    }

    @Bean
    public String databasePostgres() {
        return environment.getRequiredProperty("jdbc.databasePostgres");
    }

    @Bean
    public String hostPostgres() {
        return environment.getRequiredProperty("jdbc.hostPostgres");
    }

    @Bean
    public String usernamePostgres() {
        return environment.getRequiredProperty("jdbc.usernamePostgres");
    }

    @Bean
    public String passwordPostgres() {
        return environment.getRequiredProperty("jdbc.passwordPostgres");
    }

    @Bean
    public String pathBinBackupPG() {
        return environment.getRequiredProperty("backup.postgres.PATH_BIN");
    }

}
