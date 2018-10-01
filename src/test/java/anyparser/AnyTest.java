package anyparser;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class AnyTest {
    private static final Logger log = LoggerFactory.getLogger(AnyTest.class);

    @Test
    public void JsonToStringObjectMapDeserialize() throws IOException {
        final String json = FileRead.read("json1.txt");
        Map<String, Object> data = AnyParser.parseByMap(json);

        assertThat(data.get("k")).isEqualTo(1);
        assertThat(data).containsKeys("k", "j", "m", "o", "p");
        assertThat(data).contains(entry("m", "n"));
        assertThat(data.get("j")).extracting("l2").contains(3);
        assertThat(data.get("j")).extracting("l1").contains(true);
        assertThat(data.get("j")).extracting("l3").contains(false);

        log.debug("data : {}", data);
    }
}
