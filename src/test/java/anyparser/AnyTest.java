package anyparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class AnyTest {
    private static final Logger log = LoggerFactory.getLogger(AnyTest.class);

    @Test
    public void newName() throws IOException {
        final String json = "{\n" +
                "\t\"k\": 1,\n" +
                "\t\"j\": {\n" +
                "\t\t\"l1\": true,\n" +
                "\t\t\"l2\": false,\n" +
                "\t\t\"l3\": true\n" +
                "\t},\n" +
                "\t\"m\": \"n\",\n" +
                "\t\"o\": {\n" +
                "\t\t\"p\": {\n" +
                "\t\t\t\"q1\": 1.2,\n" +
                "\t\t\t\"q2\": 2.3,\n" +
                "\t\t\t\"q3\": 3.4,\n" +
                "\t\t\t\"q4\": 4.5,\n" +
                "\t\t\t\"q5\": 5.5\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"p\": {\n" +
                "\t\t\"q\": {\n" +
                "\t\t\t\"q1\": \"먀1\",\n" +
                "\t\t\t\"q2\": \"먀2\",\n" +
                "\t\t\t\"q3\": \"먀3\"\n" +
                "\t\t},\n" +
                "\t\t\"r\": {\n" +
                "\t\t\t\"q1\": \"a\",\n" +
                "\t\t\t\"q2\": \"b\",\n" +
                "\t\t\t\"q3\": \"c\",\n" +
                "\t\t\t\"q4\": \"d\",\n" +
                "\t\t\t\"q5\": \"e\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        MapType type = mapper.getTypeFactory().constructMapType(
                Map.class, String.class, Object.class);
        // 1. public MapType constructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) 이 호출되어서
        // JavaType 객체로 변환되어 2. 로 넘어감
        //
        // 2. public MapType constructMapType(Class<? extends Map> mapClass, JavaType keyType, JavaType valueType)
        // 검증과 바인딩 단계
        //
        // 3. TypeBindings bindings = TypeBindings.createIfNeeded(mapClass, new JavaType[]{keyType, valueType});
        // TypeBindings.createIfNeeded 을 통해 바인딩된 MapType 리턴

        Map<String, Object> data = mapper.readValue(json, type);
        // public <T> T readValue(String content, JavaType valueType) 을 통해 Map<String, Object> 오브젝트로 역직렬화

        log.debug("data : {}", data);
    }
}
