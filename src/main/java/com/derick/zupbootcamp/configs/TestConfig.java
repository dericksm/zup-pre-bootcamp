package com.derick.zupbootcamp.configs;

import com.derick.zupbootcamp.services.EmailService;
import com.derick.zupbootcamp.services.MockMailService;
import com.derick.zupbootcamp.services.SMTPEmailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Bean
    public EmailService emailService() {
        return new MockMailService();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }

    @Override
    public void run(String... args) throws Exception {}
}
