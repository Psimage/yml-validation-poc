package me.yarosbug.ymlvalidationpoc;

import lombok.SneakyThrows;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaClient;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

public class ValidateYaml {

    private static final String ROOT_SCHEMA = "/root-schema.json";

    @Test
    void validYaml() {
        validateYaml("/application.yml");
    }

    @Test
    void invalidYaml() {
        ValidationException ex = catchThrowableOfType(() -> validateYaml("/invalid.yml"), ValidationException.class);

        assertThat(ex).isNotNull();

        System.out.println(ex.toJSON().toString(2));
    }


    @SneakyThrows
    private void validateYaml(String yamlPath) throws ValidationException {
        try (InputStream inputStream = getClass().getResourceAsStream(ROOT_SCHEMA)) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.builder()
                                        .schemaClient(SchemaClient.classPathAwareClient())
                                        .schemaJson(rawSchema)
                                        .resolutionScope("classpath:/") // setting the default resolution scope
                                        .build()
                                        .load().build();
            schema.validate(readYaml(yamlPath));
        }
    }

    @SneakyThrows
    private JSONObject readYaml(String yamlPath) {
        try (InputStream inputStream = getClass().getResourceAsStream(yamlPath)) {
            Map<?, ?> yaml = new Yaml().load(inputStream);
            return new JSONObject(yaml);
        }
    }
}
