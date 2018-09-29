package anyparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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

public class InitTest {
    private static final Logger log = LoggerFactory.getLogger(InitTest.class);
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void javaToJsonFile() throws IOException {
        Car car = new Car("yellow", "renault");
        objectMapper.writeValue(new File("target/car.json"), car);
    }

    @Test
    public void javaToJsonString() throws JsonProcessingException {
        Car car = new Car("yellow", "renault");
        String json = objectMapper.writeValueAsString(car);

        assertThat(json).isEqualTo("{\"color\":\"yellow\",\"type\":\"renault\"}");
        log.debug("json : {}", json);
    }

    @Test
    public void jsonStringToJava() throws IOException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Car car = objectMapper.readValue(json, Car.class);

        assertThat(car.getColor()).isEqualTo("Black");
        assertThat(car.getType()).isEqualTo("BMW");
        log.debug("car : {}", car);
    }

    @Test
    public void jsonFileToJava() throws IOException {
        Car car = objectMapper.readValue(new File("target/car.json"), Car.class);

        assertThat(car.getColor()).isEqualTo("yellow");
        assertThat(car.getType()).isEqualTo("renault");
        log.debug("car : {}", car);
    }

    @Test
    public void jsonArrayToJavaList() throws IOException {
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
        assertThat(listCar.get(0).getType()).isEqualTo("BMW");
        log.debug("list : {}", listCar);
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
