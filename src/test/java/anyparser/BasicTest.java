package anyparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicTest {
    private static final Logger log = LoggerFactory.getLogger(BasicTest.class);
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void javaToJsonFile() throws IOException {
        BasicCar basicCar = new BasicCar("yellow", "renault");
        objectMapper.writeValue(new File("target/basicCar.json"), basicCar);
    }

    @Test
    public void javaToJsonString() throws JsonProcessingException {
        BasicCar basicCar = new BasicCar("yellow", "renault");
        String json = objectMapper.writeValueAsString(basicCar);

        assertThat(json).isEqualTo("{\"color\":\"yellow\",\"type\":\"renault\"}");
        log.debug("json : {}", json);
    }

    @Test
    public void jsonStringToJava() throws IOException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        BasicCar basicCar = objectMapper.readValue(json, BasicCar.class);

        assertThat(basicCar.getColor()).isEqualTo("Black");
        assertThat(basicCar.getType()).isEqualTo("BMW");
        log.debug("basicCar : {}", basicCar);
    }

    @Test
    public void jsonFileToJava() throws IOException {
        BasicCar basicCar = objectMapper.readValue(new File("target/basicCar.json"), BasicCar.class);

        assertThat(basicCar.getColor()).isEqualTo("yellow");
        assertThat(basicCar.getType()).isEqualTo("renault");
        log.debug("basicCar : {}", basicCar);
    }

    @Test
    public void jsonToJsonNode() throws IOException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"FIAT\" }";
        JsonNode jsonNode = objectMapper.readTree(json);
        String color = jsonNode.get("color").asText();
        assertThat(color).isEqualTo("Black");
    }

    @Test
    public void jsonArrayToJavaList() throws IOException {
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        List<BasicCar> listBasicCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<BasicCar>>() {
        });
        assertThat(listBasicCar.get(0).getType()).isEqualTo("BMW");
        log.debug("list : {}", listBasicCar);
    }

    @Test
    public void jsonStringToJavaMap() throws IOException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Map<String, Object> map
                = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});

        assertThat(map.get("color")).isEqualTo("Black");
        log.debug("map : {}", map);
    }
}
