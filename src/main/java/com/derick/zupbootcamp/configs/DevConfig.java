package com.derick.zupbootcamp.configs;

import com.derick.zupbootcamp.services.EmailService;
import com.derick.zupbootcamp.services.SMTPEmailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {

    @Bean
    public EmailService emailService(){
        return new SMTPEmailService();
    }

    @Override
    public void run(String... args) throws Exception {    }
}
