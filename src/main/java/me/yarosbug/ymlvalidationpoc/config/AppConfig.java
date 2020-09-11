package me.yarosbug.ymlvalidationpoc.config;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "myapp")
@Data
public class AppConfig {
    @Schema(allowableValues = {"Hello, World!", "Ayayaya!", "Boom!"},
            defaultValue = "Hello, World!",
            description = "Greetings message")
    private String greetings;

    @ArraySchema(arraySchema = @Schema(required = true), minItems = 1)
    private List<Item> items = new ArrayList<>();

    private Map<String, Item> itemsByKey = new HashMap<>();

    @Data
    public static class Item {
        private String name;
        private int price;
    }
}
