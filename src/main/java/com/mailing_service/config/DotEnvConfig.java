package com.mailing_service.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotEnvConfig {

    static {

        Dotenv dotenv = Dotenv.load();
        System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));
        System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
    }
}
