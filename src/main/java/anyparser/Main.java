package anyparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        String json = FileRead.read("json1.txt");
        Map<String, Object> data = AnyParser.parseByMap(json);
        log.info("{}", data);
    }
}
