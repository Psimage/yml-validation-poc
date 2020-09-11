package me.yarosbug.ymlvalidationpoc;

import lombok.extern.slf4j.Slf4j;
import me.yarosbug.ymlvalidationpoc.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class YmlValidationPocApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(YmlValidationPocApplication.class, args);
    }

    @Autowired
    AppConfig config;

    @Override
    public void run(ApplicationArguments args) {
        log.info(config.toString());
    }
}
