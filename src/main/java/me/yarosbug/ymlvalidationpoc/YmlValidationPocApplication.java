package me.yarosbug.ymlvalidationpoc;

import me.yarosbug.ymlvalidationpoc.app.AppConfigProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YmlValidationPocApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(YmlValidationPocApplication.class, args);
    }

    @Autowired
    AppConfigProcessor configProcessor;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(configProcessor.createAppMessage());
    }
}
