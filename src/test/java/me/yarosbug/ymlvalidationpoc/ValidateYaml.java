package me.yarosbug.ymlvalidationpoc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaClient;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.net.URL;

public class ValidateYaml {

    private static final String ROOT_SCHEMA = "/root-schema.json";

    @Test
    void validYaml() {
        validateYaml("/application.yml");
    }

    @Test
    void invalidYaml() {
        ValidationException e = Assertions.catchThrowableOfType(() -> validateYaml("/invalid.yml"), ValidationException.class);
        Assertions.assertThat(e).isNotNull();

        System.out.println(e.toJSON().toString(2));
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
    private JSONObject readYaml(String path) {
        URL resource = this.getClass().getResource(path);
        Object jsonObj = new ObjectMapper(new YAMLFactory()).readValue(resource, Object.class);
        String json = new ObjectMapper().writeValueAsString(jsonObj);
        return new JSONObject(new JSONTokener(json));
    }
}
