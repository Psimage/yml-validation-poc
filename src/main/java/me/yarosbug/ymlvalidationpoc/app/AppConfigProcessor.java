package me.yarosbug.ymlvalidationpoc.app;

import lombok.RequiredArgsConstructor;
import me.yarosbug.ymlvalidationpoc.config.AppConfig;
import me.yarosbug.ymlvalidationpoc.config.Item;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppConfigProcessor {
    private final AppConfig appConfig;

    public String createAppMessage() {
        return appConfig.getGreetings() +
                "\n" +
                appConfig.getItems().stream()
                         .map(Item::toString)
                         .collect(Collectors.joining(",")) +
                "\n";
    }
}
