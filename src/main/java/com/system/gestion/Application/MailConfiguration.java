package com.system.gestion.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

@Configuration
@ComponentScan({ "com.system.gestion.Application" })
@PropertySource( value={"classpath:application.properties"})
public class MailConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public JavaMailSender MailService() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setJavaMailProperties(getJavaMailProperties());
        sender.setDefaultEncoding("UTF-8");
        sender.setHost(environment.getRequiredProperty("email.host"));
        sender.setPort(Integer.parseInt(environment.getRequiredProperty("email.port")));
        sender.setProtocol(environment.getRequiredProperty("email.protocol"));
        sender.setUsername(environment.getRequiredProperty("email.username"));
        sender.setPassword(environment.getRequiredProperty("email.password"));
        return sender;
    }

    private Properties getJavaMailProperties(){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.timeout", 5000);
        properties.put("mail.smtp.connectiontimeout", 5000);
        properties.put("mail.smtp.starttls.enable", true);
        return properties;
    }

}